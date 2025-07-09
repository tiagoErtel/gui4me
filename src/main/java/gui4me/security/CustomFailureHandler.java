package gui4me.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import gui4me.exceptions.user.UserNotVerifiedException;
import gui4me.utils.Link;
import gui4me.utils.Message;
import gui4me.utils.MessageType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException {

        logger.warn("Exception class: {}", exception.getClass().getName());

        Throwable cause = exception.getCause();

        while (cause != null) {
            logger.warn("Underlying cause class: {}", cause.getClass().getName());
            if (cause instanceof UserNotVerifiedException) {
                break;
            }
            cause = cause.getCause();
        }

        if (cause instanceof UserNotVerifiedException) {
            Message message = new Message(MessageType.ERROR, cause.getMessage());
            message.setLink(new Link("/user/resend-verification-email", "resend verification email."));
            request.getSession().setAttribute("message", message);
        } else {
            Message message = new Message(MessageType.ERROR, exception.getMessage());
            request.getSession().setAttribute("message", message);
        }

        logger.warn("Auth failure reason: {}", exception.getMessage());

        response.sendRedirect("/login-error");
    }
}
