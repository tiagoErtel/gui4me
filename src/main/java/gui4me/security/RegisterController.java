package gui4me.security;

import gui4me.customUserDetails.CustomUserDetails;
import gui4me.customUserDetails.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String register(CustomUserDetails user){
        try {
            customUserDetailsService.save(user);
            return "redirect:login";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
