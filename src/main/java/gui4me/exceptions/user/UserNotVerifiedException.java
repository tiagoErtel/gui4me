package gui4me.exceptions.user;

import org.springframework.security.core.AuthenticationException;

public class UserNotVerifiedException extends AuthenticationException {
    public UserNotVerifiedException() {
        super("Your email is not verified. Please verify your email before logging in.");
    }
}
