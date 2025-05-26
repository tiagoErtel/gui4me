package gui4me.exceptions.invoice;

public class InvoiceUrlIsNotQrCode extends RuntimeException {

    private final String invoiceKey;

    public InvoiceUrlIsNotQrCode(String invoiceKey) {
        this.invoiceKey = invoiceKey;
    }

    public String getInvoiceKey() {
        return invoiceKey;
    }
}
