package gui4me.security;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gui4me.exceptions.user.ProviderMismatchException;
import gui4me.exceptions.user.UserNotVerifiedException;
import gui4me.utils.Link;
import gui4me.utils.Message;
import gui4me.utils.MessageType;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginErrorController {

    private static final Logger logger = LoggerFactory.getLogger(LoginErrorController.class);

    @GetMapping("login-error")
    public String showLoginError(HttpServletRequest request, RedirectAttributes redirectAttributes) {

        AuthenticationException exception = (AuthenticationException) request.getSession().getAttribute("exception");

        logger.warn("Exception class: {}", exception.getClass().getName());

        Throwable cause = exception;
        while (cause != null) {
            logger.warn("Exception in chain: {}", cause.getClass().getName());
            if (cause instanceof BadCredentialsException || cause instanceof UserNotVerifiedException
                    || cause instanceof ProviderMismatchException) {
                break;
            }
            cause = cause.getCause();
        }

        Message message = new Message(MessageType.ERROR);
        Map<String, String> fieldErrors = new HashMap<>();

        if (cause instanceof UserNotVerifiedException) {
            message.setMessage(cause.getMessage());
            message.setLink(new Link("/user/resend-verification-email", "click here to resend the email."));
        } else if (cause instanceof BadCredentialsException) {
            message.setMessage("Incorrect email or password!");
            fieldErrors.put("email", "");
            fieldErrors.put("password", "");
        } else if (cause instanceof ProviderMismatchException) {
            message.setMessage(cause.getMessage());
        } else {
            message.setMessage("Unknow error, please try again!");
        }

        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("fieldErrors", fieldErrors);

        return "redirect:/login";

    }
}
