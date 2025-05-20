package gui4me.exceptions;

import gui4me.utils.Message;

public class InvoiceParseErrorException extends RuntimeException {

    private Message customMessage;

    public InvoiceParseErrorException() {
        super("An error occurred during the invoice parsing.");
    }

    public InvoiceParseErrorException(String message) {
        super(message);
    }

    public InvoiceParseErrorException(Message customMessage) {
        this.customMessage = customMessage;
    }

    public Message getCustomMessage() {
        return customMessage;
    }
}
