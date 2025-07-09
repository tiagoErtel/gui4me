package gui4me.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gui4me.exceptions.user.ProviderMismatchException;
import gui4me.exceptions.user.UserNotVerifiedException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!user.getAuthProvider().equals(AuthProvider.LOCAL)) {
            throw new ProviderMismatchException(user.getAuthProvider());
        }

        if (!user.isEmailVerified()) {
            throw new UserNotVerifiedException();
        }
        return new UserPrincipal(user);
    }
}
