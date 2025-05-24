package gui4me.exceptions.invoice;

public class InvoiceAlreadyProcessedException extends RuntimeException {
    private final String invoiceKey;

    public InvoiceAlreadyProcessedException(String invoiceKey) {
        super("Invoice already processed.");
        this.invoiceKey = invoiceKey;
    }

    public String getInvoiceKey() {
        return invoiceKey;
    }
}
