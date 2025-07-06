package gui4me.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gui4me.utils.Message;
import gui4me.utils.MessageType;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class OAuth2ErrorController {

    @GetMapping("/auth/error")
    public String showAuthError(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String errorMessage = (String) request.getSession().getAttribute("error");

        request.getSession().removeAttribute("error");
        redirectAttributes.addFlashAttribute("message",
                new Message(MessageType.ERROR, errorMessage));
        return "redirect:/login";
    }
}
