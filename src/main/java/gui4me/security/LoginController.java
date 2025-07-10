package gui4me.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gui4me.utils.Message;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, Message message) {
        model.addAttribute("message", message);
        return "pages/login";
    }

    @GetMapping("/")
    public String home() {
        return "pages/home";
    }
}
