package gui4me.product.dto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProductSearchResult {
    private String productName;
    private Double unitPrice;
    private String storeName;
    private LocalDateTime lastIssuanceDate;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public ProductSearchResult(String productName, Double unitPrice, String storeName,
            LocalDateTime lastIssuanceDate) {
        this.productName = productName;
        this.storeName = storeName;
        this.lastIssuanceDate = lastIssuanceDate;
        this.unitPrice = unitPrice;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getStoreName() {
        return storeName;
    }

    public LocalDateTime getLastIssuanceDate() {
        return lastIssuanceDate;
    }

    public String getFormattedLastIssuanceDate() {
        return lastIssuanceDate != null ? lastIssuanceDate.format(FORMATTER) : "N/A";
    }

    public String getTimeAgo() {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(this.lastIssuanceDate, now);

        long seconds = duration.getSeconds();

        if (seconds < 60) {
            return seconds + " seconds ago";
        }

        long minutes = seconds / 60;
        if (minutes < 60) {
            return minutes + " minutes ago";
        }

        long hours = minutes / 60;
        if (hours < 24) {
            return hours + " hours ago";
        }

        long days = hours / 24;
        return days + " days ago";
    }
}
