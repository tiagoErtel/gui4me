package gui4me.user;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gui4me.exceptions.user.UserVerificationTokenDoNotExistsException;
import gui4me.exceptions.user.UserVerificationTokenExpiredException;

@Service
public class UserVerificationTokenService {

    @Autowired
    UserVerificationTokenRepository userVerificationTokenRepository;

    public UserVerificationToken generateUserVerificationToken(User user) {
        UserVerificationToken userVerificationToken = new UserVerificationToken();

        userVerificationToken.setUser(user);
        userVerificationToken.setToken(UUID.randomUUID().toString());
        userVerificationToken.setCreatedAt(LocalDateTime.now());
        userVerificationToken.setExpiresAt(LocalDateTime.now().plusHours(24));

        save(userVerificationToken);

        return userVerificationToken;
    }

    public void save(UserVerificationToken userVerificationToken) {
        userVerificationTokenRepository.save(userVerificationToken);
    }

    public Optional<UserVerificationToken> findByToken(String token) {
        return userVerificationTokenRepository.findByToken(token);
    }

    public UserVerificationToken findValidToken(String token) {
        UserVerificationToken userToken = userVerificationTokenRepository.findByToken(token)
                .orElseThrow(UserVerificationTokenDoNotExistsException::new);

        if (userToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new UserVerificationTokenExpiredException();
        }

        return userToken;
    }
}
