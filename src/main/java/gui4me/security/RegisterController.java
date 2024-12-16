package gui4me.security;

import gui4me.custom_user_details.CustomUserDetails;
import gui4me.custom_user_details.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @GetMapping("/register")
    public String register(Model model, String error, String errorMessage) {

        if (error != null) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", errorMessage);
        }

        return "pages/register";
    }

    @PostMapping("/register")
    public String register(CustomUserDetails user, RedirectAttributes redirectAttributes){
        try {
            customUserDetailsService.save(user);
            return "redirect:login";
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", true);
            redirectAttributes.addAttribute("errorMessage", e.getMessage());
            return "redirect:register";
        }
    }
}
