package gui4me.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import gui4me.exceptions.user.ProviderMismatchException;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    UserService userService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oauth2User.getAttributes();

        String rawEmail = (String) attributes.get("email");
        String rawName = (String) attributes.get("name");

        if (rawEmail == null && "github".equals(userRequest.getClientRegistration().getRegistrationId())) {
            rawEmail = fetchPrimaryEmailFromGitHub(userRequest);
        }

        if (rawName == null) {
            rawName = (String) attributes.get("login");
        }

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        AuthProvider provider = AuthProvider.fromString(registrationId);

        String email = rawEmail;
        String name = rawName;

        User user = userService.findByEmail(email)
                .orElseGet(() -> userService.registerOAuth2User(email, name, provider));

        if (user.getAuthProvider() != provider) {
            throw new ProviderMismatchException(user.getAuthProvider());
        }

        return new UserPrincipal(user, attributes);
    }

    private String fetchPrimaryEmailFromGitHub(OAuth2UserRequest userRequest) {
        String uri = "https://api.github.com/user/emails";
        String token = userRequest.getAccessToken().getTokenValue();

        RequestEntity<Void> request = RequestEntity
                .get(uri)
                .header("Authorization", "Bearer " + token)
                .build();

        ResponseEntity<List> response = restTemplate.exchange(request, List.class);
        if (response.getBody() == null)
            return null;

        for (Object obj : response.getBody()) {
            if (obj instanceof Map<?, ?> emailMap) {
                Boolean primary = (Boolean) emailMap.get("primary");
                Boolean verified = (Boolean) emailMap.get("verified");
                String email = (String) emailMap.get("email");

                if (Boolean.TRUE.equals(primary) && Boolean.TRUE.equals(verified)) {
                    return email;
                }
            }
        }

        return null;
    }
}
