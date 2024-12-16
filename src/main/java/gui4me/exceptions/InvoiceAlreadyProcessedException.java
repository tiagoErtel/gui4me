package gui4me.exceptions;

public class InvoiceAlreadyProcessedException extends RuntimeException {
    public InvoiceAlreadyProcessedException() {
        super("Invoice already processed.");
    }
}