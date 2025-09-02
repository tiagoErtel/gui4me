@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message
@import java.util.List
@import gui4me.product.dto.ProductAnalyseByStore
@import gui4me.user.User

@param Message message = null
@param User currentUser
@param CsrfHiddenInput csrfHiddenInput
@param List<ProductAnalyseByStore> products = null

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
message = message,
currentUser = currentUser,
content = @`
    @template.partials.back-button(href = "/product/search")

    <div class="list-header">
        <h2 class="title">Products Analyse: ${!products.isEmpty() ? products.get(0).getProductNormalizedName() : ""}</h2>
        <p class="subtitle">Analyse the product</p>
    </div>

    @if (products == null || products.isEmpty())
        <div class="no-items">
            <p>No Analyse to do. Try a different search product.</p>
        </div>
    @else
        <div class="card-list">
            @for (ProductAnalyseByStore product : products)
                <div class="card-item">
                    <div>
                        <h3 class="card-title">${product.getStoreName()}</h3>
                        <p class="card-details">Max: R$ ${String.format("%.2f", product.getMaxPrice())}</p>
                        <p class="card-details">Min: R$ ${String.format("%.2f", product.getMinPrice())}</p>
                        <p class="card-details">Avg: R$ ${String.format("%.2f", product.getAvgPrice())}</p>
                        <p class="card-details">Times Sold: ${product.getTimesSold()}</p>
                    </div>
                </div>
            @endfor
        </div>
    @endif
`)
