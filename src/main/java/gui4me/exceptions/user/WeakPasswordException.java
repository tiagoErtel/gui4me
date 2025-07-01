package gui4me.exceptions.user;

public class WeakPasswordException extends RuntimeException {

    private String redirect;

    public WeakPasswordException() {
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

}
