package gui4me.custom_user_details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gui4me.utils.Message;
import gui4me.utils.MessageType;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomUserDetailsService userService;

    @GetMapping("/settings")
    public String setting(Model model) {
        return "pages/user/settings";
    }

    @PostMapping("/settings/username")
    public String updateUsername(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("currentUser") CustomUserDetails user,
            @RequestParam String newUsername) {

        userService.updateUsername(user, newUsername);

        Message message = new Message(MessageType.SUCCESS, "Username updated!");
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/user/settings";
    }

    @PostMapping("/settings/password")
    public String updatePassword(
            @RequestParam String currentPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            @ModelAttribute("currentUser") CustomUserDetails user,
            RedirectAttributes redirectAttributes) {

        userService.updatePassword(user, currentPassword, newPassword, confirmPassword);

        Message message = new Message(MessageType.SUCCESS, "Password updated succesfully!");
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/user/settings";
    }
}
