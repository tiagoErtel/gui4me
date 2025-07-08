package gui4me.exceptions.user;

public class UserVerificationTokenDoNotExistsException extends RuntimeException {

    public UserVerificationTokenDoNotExistsException() {
        super("Token not found");
    }

}
