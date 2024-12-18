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
    public String login(HttpServletRequest request, Model model, String error, String logout) {

        if (error != null) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "Invalid email or password");
        }

        return "pages/login";
    }

    @GetMapping("/login-error")
    public String loginError(RedirectAttributes redirectAttributes) {
        Message message = new Message(MessageType.ERROR, "Invalid email or password");
        redirectAttributes.addFlashAttribute("message", message);

        // Redirect back to the login page
        return "redirect:/login";
    }

    @GetMapping("/")
    public String home() {
        return "pages/home";
    }
}