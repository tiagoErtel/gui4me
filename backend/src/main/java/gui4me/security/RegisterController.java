package gui4me.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gui4me.security.dto.RegisterRequest;
import gui4me.user.UserService;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        userService.register(
                request.username(),
                request.email(),
                request.password(),
                request.confirmPassword());
        return ResponseEntity.ok(Map.of("message", "User registered. Please check your email."));
    }
}
