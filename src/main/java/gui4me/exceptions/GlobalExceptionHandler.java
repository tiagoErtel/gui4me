package gui4me.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gui4me.exceptions.invoice.InvoiceAlreadyProcessedException;
import gui4me.exceptions.invoice.InvoiceParseErrorException;
import gui4me.exceptions.invoice.InvoiceUrlIsNotQrCode;
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

        logger.warn("Invoice already processed: {}", e.getMessage());

        redirectAttributes.addFlashAttribute("message",
                new Message(MessageType.ERROR, "This invoice has already been processed."));

        return "redirect:/invoice/register";
    }

    @ExceptionHandler(InvoiceParseErrorException.class)
    public String handleInvoiceParseError(InvoiceParseErrorException e, RedirectAttributes redirectAttributes) {

        logger.error("Invoice parse error: {}", e.getMessage());

        redirectAttributes.addFlashAttribute("message",
                new Message(MessageType.ERROR, "Failed to parse the invoice. Please check the URL and try again."));

        return "redirect:/invoice/register";
    }

    @ExceptionHandler(InvoiceUrlIsNotQrCode.class)
    public String handleInvoiceUrlIsNotQrCode(InvoiceUrlIsNotQrCode e, RedirectAttributes redirectAttributes) {

        logger.error(e.getMessage());

        redirectAttributes.addFlashAttribute("message",
                new Message(MessageType.ERROR, "Failed to parse the invoice. Please check the URL and try again."));

        return "redirect:/invoice/register";
    }
}
