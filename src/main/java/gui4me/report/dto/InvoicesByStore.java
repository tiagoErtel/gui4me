package gui4me.report.dto;

public interface InvoicesByStore {
    String getStoreName();

    Long getInvoiceCount();

    Double getTotalPrice();
}
