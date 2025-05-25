package gui4me.custom_user_details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import gui4me.exceptions.user.UserAlreadyRegisteredException;
import gui4me.exceptions.user.UserNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    CustomUserDetailsRepository customUserDetailsRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customUserDetailsRepository.findByEmail(username)
                .orElseThrow(UserNotFoundException::new);
    }

    public CustomUserDetails save(CustomUserDetails user) {
        if (existsByEmail(user.getEmail())) {
            throw new UserAlreadyRegisteredException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return customUserDetailsRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        return customUserDetailsRepository.existsByEmail(email);
    }
}
