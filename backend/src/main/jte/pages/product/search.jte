@import gui4me.product.dto.ProductAnalyse
@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message
@import java.util.List
@import gui4me.user.User

@param Message message = null
@param User currentUser
@param CsrfHiddenInput csrfHiddenInput
@param List<ProductAnalyse> products = null

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
message = message,
currentUser = currentUser,
content = @`
    @template.partials.back-button(href = "/dashboard")

    <div class="list-header">
        <h2 class="title">Search Products</h2>
        <p class="subtitle">Find items in out database</p>
    </div>

    <form action="/product/search" method="GET" class="search-container">
        <input
            type="text"
            name="productName"
            placeholder="Search by product name..."
            required
        />
        <button type="submit"><i class="fa fa-search"></i></button>
    </form>

    @if (products == null || products.isEmpty())
        <div class="no-items">
            <p>No products found. Try a different search term.</p>
        </div>
    @else
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
    @endif
`)
