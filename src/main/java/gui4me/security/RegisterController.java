package gui4me.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gui4me.user.UserService;
import gui4me.utils.Message;
import gui4me.utils.MessageType;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String register(Model model, Message message) {
        model.addAttribute("message", message);
        return "pages/register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            RedirectAttributes redirectAttributes) {

        userService.register(username, email, newPassword, confirmPassword);

        redirectAttributes.addFlashAttribute("message",
                new Message(MessageType.SUCCESS,
                        "User registered, please check your email to verify your email address"));

        return "redirect:/login";
    }
}
