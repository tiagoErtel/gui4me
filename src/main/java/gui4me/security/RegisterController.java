package gui4me.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gui4me.user.User;
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
    public String register(User user, RedirectAttributes redirectAttributes) {
        userService.register(user);

        redirectAttributes.addFlashAttribute("message",
                new Message(MessageType.SUCCESS,
                        "User registered, please check your email to verify your email address"));

        return "redirect:/login";
    }
}
