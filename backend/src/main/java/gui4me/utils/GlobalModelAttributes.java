package gui4me.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import gui4me.user.User;
import gui4me.user.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute("currentUser")
    public User currentUser(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal principal) {
            return principal.getUser();
        }
        return null;
    }

    @ModelAttribute("csrf")
    public CsrfToken csrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }

    @ModelAttribute("message")
    public Message messageFromRedirect(Model model) {
        Object message = model.asMap().get("message");
        if (message instanceof Message typedMessage) {
            return typedMessage;
        }
        return null;
    }
}
