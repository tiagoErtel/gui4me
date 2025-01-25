package gui4me.security;

import gui4me.utils.Message;
import gui4me.utils.MessageType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model, Message message) {
        model.addAttribute("message", message);
        return "pages/login";
    }

    @GetMapping("/login-error")
    public String loginError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", new Message(MessageType.ERROR, "Invalid email or password"));
        return "redirect:/login";
    }

    @GetMapping("/")
    public String home() {
        return "pages/home";
    }
}