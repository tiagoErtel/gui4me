package gui4me.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import gui4me.email.EmailService;
import gui4me.exceptions.user.IncorrectCurrentPasswordException;
import gui4me.exceptions.user.PasswordsDoNotMatchException;
import gui4me.exceptions.user.UserAlreadyRegisteredException;
import gui4me.exceptions.user.UserNotFoundException;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    @Autowired
    UserVerificationTokenService userVerificationTokenService;

    @Value("${app.base-url}")
    private String baseUrl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(UserNotFoundException::new);
    }

    public void save(User user) {
        if (existsByEmail(user.getEmail())) {
            throw new UserAlreadyRegisteredException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        sendVerificationEmail(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void updateUsername(User user, String newUsername) {
        user.setUsername(newUsername);

        userRepository.save(user);
    }

    public void updatePassword(User user, String password, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IncorrectCurrentPasswordException();
        }

        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);

        sendVerificationEmail(user);
    }

    public void sendVerificationEmail(User user) {
        UserVerificationToken userVerificationToken = userVerificationTokenService.generateUserVerificationToken(user);

        String link = baseUrl + "/user/verify?token=" + userVerificationToken.getToken();
        String subject = "Please verify your email";
        String body = "Click this link to verify your account:\n" + link;

        System.out.println(link);

        emailService.send(user.getEmail(), subject, body);
    }

    public void verifyUserVerificationToken(String token) {
        UserVerificationToken userToken = userVerificationTokenService.findValidToken(token);

        User user = userToken.getUser();
        user.setEmailVerified(true);
        userRepository.save(user);
    }
}
