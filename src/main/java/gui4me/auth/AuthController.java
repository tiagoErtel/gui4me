package gui4me.auth;

import gui4me.infra.TokenService;
import gui4me.user.User;
import gui4me.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @GetMapping
    public String home() {
        return "pages/home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "pages/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "pages/register";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User userLog, Model model) {
        try {
            User user = userRepository.findByEmail(userLog.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (passwordEncoder.matches(userLog.getPassword(), user.getPassword())) {
                String token = tokenService.generateToken(user);

                return "redirect:/dashboard";
            } else {
                throw new RuntimeException("Invalid password");
            }
        } catch (RuntimeException e) {
            // Add error parameters to the model
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", e.getMessage());
            return "pages/login"; // Return the login page template
        }
    }


    @PostMapping("/register")
    public String register(Model model, @ModelAttribute User userReg) {
        try {
            Optional<User> user = userRepository.findByEmail(userReg.getEmail());

            if (user.isEmpty()) {
                User newUser = new User();
                newUser.setPassword(passwordEncoder.encode(userReg.getPassword()));
                newUser.setEmail(userReg.getEmail());
                userRepository.save(newUser);

                String token = tokenService.generateToken(newUser);
                // Redirect to /dashboard on successful registration
                return "redirect:/dashboard";
            } else {
                throw new RuntimeException("User already exists");
            }
        } catch (RuntimeException e){
            // Add error parameters to the model
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", e.getMessage());
            return "pages/register"; // Return the register page template
        }
    }
}
