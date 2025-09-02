package gui4me.product.dto;

import java.math.BigDecimal;

public interface ProductAnalyse {
    String getId();

    String getName();

    String getNormalizedName();

    BigDecimal getAvgPrice();

    BigDecimal getMinPrice();

    BigDecimal getMaxPrice();

    Long getTimesSold();

    Long getStoresCount();
}
