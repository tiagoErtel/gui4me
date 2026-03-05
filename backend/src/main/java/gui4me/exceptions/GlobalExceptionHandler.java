package gui4me.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import gui4me.exceptions.email.EmailSendingException;
import gui4me.exceptions.invoice.InvoiceAlreadyProcessedException;
import gui4me.exceptions.invoice.InvoiceParseErrorException;
import gui4me.exceptions.invoice.InvoiceUrlIsNotQrCode;
import gui4me.exceptions.user.IncorrectCurrentPasswordException;
import gui4me.exceptions.user.PasswordsDoNotMatchException;
import gui4me.exceptions.user.UserAlreadyRegisteredException;
import gui4me.exceptions.user.UserNotFoundException;
import gui4me.exceptions.user.UserNotVerifiedException;
import gui4me.exceptions.user.UserVerificationTokenDoNotExistsException;
import gui4me.exceptions.user.UserVerificationTokenExpiredException;
import gui4me.exceptions.user.WeakPasswordException;
import gui4me.utils.Link;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleBadCredentialsException(BadCredentialsException ex) {
        logger.warn("Authentication failed: {}", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
                        "message", "Invalid email or password"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception e) {

        logger.error("Unexpected error", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "message", "An unexpected error occurred. Please try again later."));
    }

    @ExceptionHandler(InvoiceAlreadyProcessedException.class)
    public ResponseEntity<Map<String, String>> handleInvoiceAlreadyProcessed(InvoiceAlreadyProcessedException e) {

        logger.warn("Invoice already processed: {}", e.getInvoiceKey());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "message", "This invoice has already been processed."));
    }

    @ExceptionHandler(InvoiceParseErrorException.class)
    public ResponseEntity<Map<String, String>> handleInvoiceParseError(InvoiceParseErrorException e) {

        logger.error("Invoice parse error: {}, message: {}", e.getInvoiceUrl(), e.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(Map.of(
                        "message", "Failed to parse the invoice."));
    }

    @ExceptionHandler(InvoiceUrlIsNotQrCode.class)
    public ResponseEntity<Map<String, Object>> handleInvoiceUrlIsNotQrCode(InvoiceUrlIsNotQrCode e) {

        logger.warn("Invoice URL is not qr code: {}", e.getInvoiceKey());

        String redirectUrl = "https://www.sefaz.rs.gov.br/dfe/Consultas/ConsultaPublicaDfe?chaveAcessoDfe="
                + e.getInvoiceKey();

        Link link = new Link(redirectUrl, "Click here to access the official invoice page.");

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Invoice QR Code is invalid...");
        response.put("link", link);

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    @ExceptionHandler(PasswordsDoNotMatchException.class)
    public ResponseEntity<Map<String, Object>> handlePasswordsDoNotMatch(PasswordsDoNotMatchException e) {
        logger.warn("Passwords do not match validation triggered");

        Map<String, String> fieldErrors = new HashMap<>();
        fieldErrors.put("password", "Passwords do not match");
        fieldErrors.put("confirmPassword", "Passwords do not match");

        Map<String, Object> body = new HashMap<>();
        body.put("message", "Validation failed: Passwords do not match.");
        body.put("fieldErrors", fieldErrors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(body);
    }

    @ExceptionHandler(IncorrectCurrentPasswordException.class)
    public ResponseEntity<Map<String, Object>> handleIncorrectCurrentPassword(IncorrectCurrentPasswordException e) {
        logger.warn("Incorrect current password attempt for user settings");

        Map<String, String> fieldErrors = new HashMap<>();
        fieldErrors.put("currentPassword", "The password you entered is incorrect.");

        Map<String, Object> body = new HashMap<>();
        body.put("message", "Could not update settings: Incorrect password.");
        body.put("fieldErrors", fieldErrors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(body);
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException e) {

        logger.warn("User already registered");

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("message", "User already registered!"));
    }

    @ExceptionHandler(UserVerificationTokenDoNotExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserVerificationTokenDoNotExistsException(
            UserVerificationTokenDoNotExistsException e) {

        logger.warn("Token do no exists");

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message",
                        "This verification link is invalid. It may have already been used or was never created."));
    }

    @ExceptionHandler(UserVerificationTokenExpiredException.class)
    public ResponseEntity<Map<String, Object>> handleUserVerificationTokenExpired(
            UserVerificationTokenExpiredException e) {
        logger.warn("Token expired for verification attempt");

        return ResponseEntity
                .status(HttpStatus.GONE)
                .body(Map.of(
                        "message", "Verification token has expired! Please request a new one.",
                        "errorCode", "TOKEN_EXPIRED",
                        "canResend", true));
    }

    @ExceptionHandler(EmailSendingException.class)
    public ResponseEntity<Map<String, String>> handleEmailSending(EmailSendingException ex) {
        logger.error("Email sending error: {}", ex.getMessage(), ex);

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of(
                        "message", "We couldn't send you a confirmation email. Please try again later.",
                        "error", "EMAIL_SERVICE_DOWN"));
    }

    @ExceptionHandler(WeakPasswordException.class)
    public ResponseEntity<Map<String, Object>> handleWeakPassword(WeakPasswordException ex) {
        logger.warn("Weak password validation failed");

        Map<String, String> fieldErrors = Map.of(
                "password", "Create a stronger password");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "message",
                        "Password must be at least 8 characters long and include uppercase, lowercase, and a number.",
                        "fieldErrors", fieldErrors));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFoundException ex) {
        logger.warn("User not found: {}", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<Map<String, String>> handleInternalAuthServiceException(
            InternalAuthenticationServiceException ex) {

        if (ex.getCause() instanceof UserNotVerifiedException) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", ex.getMessage()));
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Authentication service error"));
    }
}
