package gui4me.custom_user_details;

import gui4me.exceptions.UserAlreadyRegisteredException;
import gui4me.exceptions.UserNotFoundException;
import gui4me.invoice.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

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
        if (existsByEmail(user.getEmail())){
            throw new UserAlreadyRegisteredException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return customUserDetailsRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        return customUserDetailsRepository.existsByEmail(email);
    }
}
