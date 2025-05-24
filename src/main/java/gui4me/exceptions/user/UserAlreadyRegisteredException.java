package gui4me.exceptions.user;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException() {
        super("User already registered.");
    }
}
