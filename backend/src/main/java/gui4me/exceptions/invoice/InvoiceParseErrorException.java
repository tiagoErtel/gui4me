package gui4me.exceptions.invoice;

public class InvoiceParseErrorException extends RuntimeException {

    private final String invoiceUrl;

    public InvoiceParseErrorException(String invoiceUrl) {
        this.invoiceUrl = invoiceUrl;
    }

    public InvoiceParseErrorException(String message, String invoiceUrl) {
        super(message);
        this.invoiceUrl = invoiceUrl;
    }

    public String getInvoiceUrl() {
        return invoiceUrl;
    }
}
