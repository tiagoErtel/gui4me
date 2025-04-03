package gui4me.exceptions;

public class InvoiceParseErrorException extends RuntimeException {
    public InvoiceParseErrorException(String message) {super("Invoice parse error." + message);}
}