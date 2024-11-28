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

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "pages/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "pages/register";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User userLog){
        User user = userRepository.findByEmail(userLog.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(userLog.getPassword(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return "pages/dashboard";
        }
        return "pages/login";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute User UserReg){
        Optional<User> user = userRepository.findByEmail(UserReg.getEmail());

        if(user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(UserReg.getPassword()));
            newUser.setEmail(UserReg.getEmail());
            userRepository.save(newUser);

            String token = tokenService.generateToken(newUser);
            return "pages/dashboard";
        }
        return "pages/register";
    }
}