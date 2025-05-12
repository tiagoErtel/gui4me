package gui4me.exceptions;

public class InvoiceParseErrorException extends RuntimeException {
    public InvoiceParseErrorException() {super("An error occurred during the invoice parsing.");}
}
