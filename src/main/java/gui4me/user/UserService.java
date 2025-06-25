package gui4me.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(UserNotFoundException::new);
    }

    public User save(User user) {
        if (existsByEmail(user.getEmail())) {
            throw new UserAlreadyRegisteredException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
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
    }
}
