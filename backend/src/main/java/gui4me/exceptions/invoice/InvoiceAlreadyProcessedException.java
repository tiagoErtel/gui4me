package gui4me.exceptions.invoice;

public class InvoiceAlreadyProcessedException extends RuntimeException {
    private final String invoiceKey;

    public InvoiceAlreadyProcessedException(String invoiceKey) {
        this.invoiceKey = invoiceKey;
    }

    public String getInvoiceKey() {
        return invoiceKey;
    }
}
