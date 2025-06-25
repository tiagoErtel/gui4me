package gui4me.security;

import gui4me.user.User;
import gui4me.user.UserService;
import gui4me.utils.Message;
import gui4me.utils.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        try {
            userService.save(user);
            return "redirect:login";
        } catch (Exception e) {
            Message message = new Message(MessageType.ERROR, e.getMessage());
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:register";
        }
    }
}
