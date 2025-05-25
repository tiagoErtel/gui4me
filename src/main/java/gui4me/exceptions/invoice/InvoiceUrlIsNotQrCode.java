package gui4me.exceptions.invoice;

public class InvoiceUrlIsNotQrCode extends RuntimeException {

    private final String invoiceKey;

    public InvoiceUrlIsNotQrCode(String invoiceKey) {
        super("The link in the invoice QrCode is invalid. Please access the invoice page and copy the QrCode (additional information), then paste the link in the input bellow");
        this.invoiceKey = invoiceKey;
    }

    public String getInvoiceKey() {
        return invoiceKey;
    }
}
