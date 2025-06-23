package gui4me.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gui4me.exceptions.invoice.InvoiceAlreadyProcessedException;
import gui4me.exceptions.invoice.InvoiceParseErrorException;
import gui4me.exceptions.invoice.InvoiceUrlIsNotQrCode;
import gui4me.exceptions.user.IncorrectCurrentPasswordException;
import gui4me.exceptions.user.PasswordsDoNotMatchException;
import gui4me.utils.Link;
import gui4me.utils.Message;
import gui4me.utils.MessageType;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception e, RedirectAttributes redirectAttributes) {

        logger.error("Unexpected error", e);

        redirectAttributes.addFlashAttribute("message",
                new Message(MessageType.ERROR, "An unexpected error occurred. Please try again later."));

        return "redirect:/dashboard";
    }

    @ExceptionHandler(InvoiceAlreadyProcessedException.class)
    public String handleInvoiceAlreadyProcessed(InvoiceAlreadyProcessedException e,
            RedirectAttributes redirectAttributes) {

        logger.warn("Invoice already processed: {}", e.getInvoiceKey());

        redirectAttributes.addFlashAttribute("message",
                new Message(MessageType.ERROR, "This invoice has already been processed."));

        return "redirect:/invoice/register";
    }

    @ExceptionHandler(InvoiceParseErrorException.class)
    public String handleInvoiceParseError(InvoiceParseErrorException e, RedirectAttributes redirectAttributes) {

        logger.error("Invoice parse error: {}, message: {}", e.getInvoiceUrl(), e.getMessage());

        redirectAttributes.addFlashAttribute("message",
                new Message(MessageType.ERROR, "Failed to parse the invoice."));

        return "redirect:/invoice/register";
    }

    @ExceptionHandler(InvoiceUrlIsNotQrCode.class)
    public String handleInvoiceUrlIsNotQrCode(InvoiceUrlIsNotQrCode e, RedirectAttributes redirectAttributes) {

        logger.warn("Invoice URL is not qr code: {}", e.getInvoiceKey());

        String redirectUrl = "https://www.sefaz.rs.gov.br/dfe/Consultas/ConsultaPublicaDfe?chaveAcessoDfe="
                + e.getInvoiceKey();

        Link link = new Link(redirectUrl, "Click here to access the official invoice page.");

        redirectAttributes.addFlashAttribute("message",
                new Message(MessageType.ERROR,
                        "Invoice QR Code is invalid, please access the official invoice page and copy the QR Code link (additional information tab), then paste the link in the invoice link field.",
                        link));

        return "redirect:/invoice/register";
    }

    @ExceptionHandler(PasswordsDoNotMatchException.class)
    public String handlePasswordsDoNotMatchException(PasswordsDoNotMatchException e,
            RedirectAttributes redirectAttributes) {

        logger.warn("Passwords do not match");

        redirectAttributes.addFlashAttribute("message",
                new Message(MessageType.ERROR, "Passwords do not match"));
        return "redirect:/user/settings";
    }

    @ExceptionHandler(IncorrectCurrentPasswordException.class)
    public String handleIncorrectCurrentPasswordException(IncorrectCurrentPasswordException e,
            RedirectAttributes redirectAttributes) {

        logger.warn("Incorrect current password");

        redirectAttributes.addFlashAttribute("message",
                new Message(MessageType.ERROR, "Incorrect password!"));
        return "redirect:/user/settings";
    }

}
