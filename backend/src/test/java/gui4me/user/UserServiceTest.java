
package gui4me.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import gui4me.email.BrevoService;
import gui4me.email.OnboardingTemplate;
import gui4me.exceptions.user.PasswordsDoNotMatchException;
import gui4me.exceptions.user.UserAlreadyRegisteredException;
import gui4me.exceptions.user.WeakPasswordException;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private BrevoService brevoService;

    @Mock
    private UserVerificationTokenService tokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldRegisterUserSuccessfully() {
        String username = "user";
        String email = "user@example.com";
        String password = "StrongPass1";
        String encodedPassword = "encoded";

        when(userRepository.existsByEmail(email)).thenReturn(false);
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));
        when(tokenService.generateUserVerificationToken(any())).thenReturn(
                new UserVerificationToken());

        User result = userService.register(username, email, password, password);

        assertEquals(username, result.getUsername());
        assertEquals(email, result.getEmail());
        assertEquals(encodedPassword, result.getPassword());
        verify(brevoService).send(any(OnboardingTemplate.class), eq(email));
    }

    @Test
    void shouldThrowWhenEmailAlreadyRegistered() {
        when(userRepository.existsByEmail("user@example.com")).thenReturn(true);

        assertThrows(UserAlreadyRegisteredException.class,
                () -> userService.register("user", "user@example.com", "Password1", "Password1"));
    }

    @Test
    void shouldThrowWhenPasswordsDoNotMatch() {
        when(userRepository.existsByEmail("user@example.com")).thenReturn(false);

        assertThrows(PasswordsDoNotMatchException.class,
                () -> userService.register("user", "user@example.com", "Password1", "Mismatch"));
    }

    @Test
    void shouldThrowWhenPasswordIsWeak() {
        when(userRepository.existsByEmail("user@example.com")).thenReturn(false);

        assertThrows(WeakPasswordException.class,
                () -> userService.register("user", "user@example.com", "weak", "weak"));
    }

    @Test
    void shouldRegisterUserWithOAuth2Successfully() {
        String username = "user";
        String email = "user@example.com";
        AuthProvider authProvider = AuthProvider.GOOGLE;

        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));
        when(tokenService.generateUserVerificationToken(any())).thenReturn(
                new UserVerificationToken());

        User result = userService.registerOAuth2User(email, username, authProvider);

        assertEquals(username, result.getUsername());
        assertEquals(email, result.getEmail());
        assertEquals(authProvider, result.getAuthProvider());
    }
}
