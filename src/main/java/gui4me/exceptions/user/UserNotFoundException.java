package gui4me.exceptions.user;

public class UserNotFoundException extends RuntimeException {

    private final String redirect;

    public UserNotFoundException(String redirect) {
        super("User not found");
        this.redirect = redirect;
    }

    public String getRedirect() {
        return redirect;
    }
}
