package gui4me.utils;

import gui4me.custom_user_details.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute
    public void addCommonAttributes(Model model, Authentication authentication, HttpServletRequest request) {
        // Add username and authorities
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("authorities", userDetails.getAuthorities());
        }

        // Add CSRF token
        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrf != null) {
            model.addAttribute("csrf", csrf);
        }
    }

    @ModelAttribute("message")
    public Message messageFromRedirect(Model model) {
        return (Message) model.asMap().get("message");
    }

    @ModelAttribute("currentUser")
    public CustomUserDetails currentUser(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails;
        }
        return null;
    }

}
