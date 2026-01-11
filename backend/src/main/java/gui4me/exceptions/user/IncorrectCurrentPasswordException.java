package gui4me.exceptions.user;

public class IncorrectCurrentPasswordException extends RuntimeException {

    public IncorrectCurrentPasswordException() {
        super("Current password is incorrect!");
    }
}
