package gui4me.exceptions.user;

public class UserVerificationTokenExpiredException extends RuntimeException {

    public UserVerificationTokenExpiredException() {
        super("User verification token has expired");
    }

}
