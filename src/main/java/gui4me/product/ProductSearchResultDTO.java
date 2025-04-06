package gui4me.product;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProductSearchResultDTO {
    private String productName;
    private Double unitPrice;
    private String storeName;
    private LocalDateTime lastIssuanceDate;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public ProductSearchResultDTO(String productName, Double unitPrice, String storeName, LocalDateTime lastIssuanceDate) {
        this.productName = productName;
        this.storeName = storeName;
        this.lastIssuanceDate = lastIssuanceDate;
        this.unitPrice = unitPrice;
    }

    public ProductSearchResultDTO(){}

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public LocalDateTime getLastIssuanceDate() {
        return lastIssuanceDate;
    }

    public String getFormattedLastIssuanceDate() {
        return lastIssuanceDate != null ? lastIssuanceDate.format(FORMATTER) : "N/A";
    }

    public void setLastIssuanceDate(LocalDateTime lastIssuanceDate) {
        this.lastIssuanceDate = lastIssuanceDate;
    }
}
