package gui4me.product.dto;

import java.math.BigDecimal;

public interface ProductAnalyseByStore {
    String getProductName();

    String getStoreName();

    BigDecimal getAvgPrice();

    BigDecimal getMinPrice();

    BigDecimal getMaxPrice();

    Long getTimesSold();
}
