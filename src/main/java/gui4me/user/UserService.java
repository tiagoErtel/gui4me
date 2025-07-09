package gui4me.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import gui4me.email.BrevoService;
import gui4me.email.OnboardingTemplate;
import gui4me.email.RecoverAccountTemplate;
import gui4me.exceptions.user.IncorrectCurrentPasswordException;
import gui4me.exceptions.user.PasswordsDoNotMatchException;
import gui4me.exceptions.user.UserAlreadyRegisteredException;
import gui4me.exceptions.user.UserNotFoundException;
import gui4me.exceptions.user.WeakPasswordException;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    BrevoService brevoService;

    @Autowired
    UserVerificationTokenService userVerificationTokenService;

    @Value("${app.base-url}")
    private String baseUrl;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void register(String username, String email, String newPassword, String confirmPassword) {
        User user = new User();

        if (existsByEmail(email)) {
            throw new UserAlreadyRegisteredException();
        }

        if (!newPassword.equals(confirmPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        user.setUsername(username);
        user.setEmail(email);
        user.setAuthProvider(AuthProvider.LOCAL);

        try {
            user = setUserPassword(user, newPassword);
        } catch (WeakPasswordException e) {
            e.setRedirect("/register");
            throw e;
        }

        user = save(user);

        sendVerificationEmail(user);
    }

    public User registerOAuth2User(String email, String name, AuthProvider authProvider) {

        User user = new User();
        user.setEmail(email);
        user.setUsername(name != null ? name : email.split("@")[0]);
        user.setPassword("");
        user.setEmailVerified(true);
        user.setAuthProvider(authProvider);

        return save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User setUserPassword(User user, String password) {
        if (!isStrongPassword(password)) {
            throw new WeakPasswordException();
        }

        user.setPassword(passwordEncoder.encode(password));

        return user;
    }

    public void updateUsername(User user, String newUsername) {
        user.setUsername(newUsername);

        save(user);
    }

    public void updatePassword(User user, String password, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IncorrectCurrentPasswordException();
        }

        try {
            user = setUserPassword(user, newPassword);
        } catch (WeakPasswordException e) {
            e.setRedirect("/user/settings");
            throw e;
        }

        save(user);
    }

    public void sendVerificationEmail(User user) {
        UserVerificationToken userVerificationToken = userVerificationTokenService.generateUserVerificationToken(user);

        String link = baseUrl + "/user/verify?token=" + userVerificationToken.getToken();

        OnboardingTemplate template = new OnboardingTemplate(user.getUsername(), link);

        brevoService.send(template, user.getEmail());
    }

    public void verifyUserVerificationToken(String token) {
        UserVerificationToken userToken = userVerificationTokenService.findValidToken(token);

        User user = userToken.getUser();
        user.setEmailVerified(true);
        save(user);
    }

    public boolean isStrongPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        return password != null && password.matches(regex);
    }

    public void sendRecoverAccountEmail(String email) {
        User user = findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("/user/recover"));

        UserVerificationToken userVerificationToken = userVerificationTokenService.generateUserVerificationToken(user);

        String link = baseUrl + "/user/reset-password?token=" + userVerificationToken.getToken();

        System.out.println(link);

        RecoverAccountTemplate template = new RecoverAccountTemplate(user.getUsername(), link);

        brevoService.send(template, user.getEmail());
    }

    public User findUserToken(String token) {
        return userVerificationTokenService.findValidToken(token).getUser();
    }

    public void resetPassword(
            String token,
            String email,
            String newPassword,
            String confirmPassword) {

        User user = findUserToken(token);

        if (!newPassword.equals(confirmPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        try {
            user = setUserPassword(user, newPassword);
        } catch (WeakPasswordException e) {
            e.setRedirect("/login");
            throw e;
        }

        save(user);
    }

    public void resendVerificationEmail(String email) {
        User user = findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("/user/resend-verification-email"));

        sendVerificationEmail(user);
    }
}
