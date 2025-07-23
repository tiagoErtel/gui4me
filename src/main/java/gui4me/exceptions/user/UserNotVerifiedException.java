package gui4me.exceptions.user;

import org.springframework.security.core.AuthenticationException;

import gui4me.user.User;

public class UserNotVerifiedException extends AuthenticationException {

    private final User user;

    public UserNotVerifiedException(User user) {
        super("Your email is not verified. Please verify your email before logging in.");
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
