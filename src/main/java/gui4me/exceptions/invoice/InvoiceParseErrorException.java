package gui4me.exceptions.invoice;

public class InvoiceParseErrorException extends RuntimeException {

    public InvoiceParseErrorException() {
        super("An error occurred during the invoice parsing.");
    }

    public InvoiceParseErrorException(String message) {
        super(message);
    }
}
