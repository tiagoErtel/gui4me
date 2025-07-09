package gui4me.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gui4me.utils.Message;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, Message message) {
        model.addAttribute("message", message);
        return "pages/login";
    }

    @GetMapping("/login-error")
    public String showAuthError(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Message message = (Message) request.getSession().getAttribute("message");

        request.getSession().removeAttribute("message");

        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String home() {
        return "pages/home";
    }
}
