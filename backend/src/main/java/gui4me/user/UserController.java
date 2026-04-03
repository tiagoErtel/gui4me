package gui4me.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(Map.of(
                "username", user.getUsername(),
                "email", user.getEmail()));
    }

    @PostMapping("/settings/username")
    public ResponseEntity<?> updateUsername(
            @AuthenticationPrincipal User user,
            @RequestBody Map<String, String> request) {

        String newUsername = request.get("newUsername");
        userService.updateUsername(user, newUsername);

        return ResponseEntity.ok(Map.of("message", "Username updated!"));
    }

    @PostMapping("/settings/password")
    public ResponseEntity<?> updatePassword(
            @AuthenticationPrincipal User user,
            @RequestBody Map<String, String> request) {

        userService.updatePassword(
                user,
                request.get("currentPassword"),
                request.get("newPassword"),
                request.get("confirmPassword"));

        return ResponseEntity.ok(Map.of("message", "Password updated successfully!"));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam String token) {
        userService.verifyUserVerificationToken(token);
        return ResponseEntity.ok(Map.of("message", "User verified! You can now log in."));
    }

    @PostMapping("/recover")
    public ResponseEntity<?> recoverUser(@RequestBody Map<String, String> request) {
        userService.sendRecoverAccountEmail(request.get("email"));
        return ResponseEntity.ok(Map.of("message", "We sent you an email with the recovery link!"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        userService.resetPassword(
                request.get("token"),
                request.get("email"),
                request.get("newPassword"),
                request.get("confirmPassword"));

        return ResponseEntity.ok(Map.of("message", "Your password was updated"));
    }

    @PostMapping("/resend-verification-email")
    public ResponseEntity<?> resendVerification(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        userService.resendVerificationEmail(email);

        return ResponseEntity.ok(Map.of(
                "message", "We sent an email with the verification link"));
    }
}
