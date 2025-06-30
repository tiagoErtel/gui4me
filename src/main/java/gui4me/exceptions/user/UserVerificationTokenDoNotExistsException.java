package gui4me.exceptions.user;

public class UserVerificationTokenDoNotExistsException extends RuntimeException {

    public UserVerificationTokenDoNotExistsException() {
        super("User verification token do not exists");
    }

}
