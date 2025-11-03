@import java.util.List
@import gui4me.product.dto.ProductAnalyse

@param List<ProductAnalyse> products

<div class="card-list">
    @for (ProductAnalyse product : products)
        <div class="card-item" onclick="location.assign('/product/analyse?productId=${product.getId()}')">
            <div>
                <h3 class="card-title">${product.getNormalizedName()}</h3>
                <p class="card-details">Name: ${product.getName()}</p>
                <p class="card-details">Max: R$ ${String.format("%.2f", product.getMaxPrice())}</p>
                <p class="card-details">Min: R$ ${String.format("%.2f", product.getMinPrice())}</p>
                <p class="card-details">Avg: R$ ${String.format("%.2f", product.getAvgPrice())}</p>
                <p class="card-details">Times Sold: ${product.getTimesSold()}</p>
                <p class="card-details">Stores Cnt: ${product.getStoresCount()}</p>
            </div>
        </div>
    @endfor
</div>


