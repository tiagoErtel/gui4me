package gui4me.exceptions.user;

import org.springframework.security.core.AuthenticationException;

import gui4me.user.AuthProvider;

public class ProviderMismatchException extends AuthenticationException {

    public ProviderMismatchException(AuthProvider provider) {
        super("You signed up with " + provider.toString() + ". Please use that provider to log in.");

    }
}
