@import gui4me.product.ProductSearchResultDTO
@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message
@import java.util.List

@param Message message = null
@param String username = ""
@param String email = null
@param CsrfHiddenInput csrfHiddenInput
@param List<ProductSearchResultDTO> productList = null

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
message = message,
username = username,
content = @`
    @template.partials.back-button(href = "/dashboard")

    <div class="list-header">
        <h2 class="title">Search Products</h2>
        <p class="subtitle">Find items you've bought before</p>
    </div>

    <form action="/product/search" method="GET" class="panel form-card">
        <input
                type="text"
                name="productName"
                placeholder="Search by product name..."
                required
        />
        <button class="btn btn-primary" type="submit">
            Search
        </button>
    </form>

    @if (productList == null || productList.isEmpty())
        <div class="no-items">
            <p>No products found. Try a different search term.</p>
        </div>
    @else
        <div class="card-list">
            @for (ProductSearchResultDTO product : productList)
                <div class="card-item">
                    <div class="card-content">
                        <h3 class="card-title">${product.getProductName()}</h3>
                        <p class="card-details">R$ ${product.getUnitPrice()}</p>
                        <p class="card-details">${product.getStoreName()}</p>
                        <p class="card-details">${product.getFormattedLastIssuanceDate()}</p>
                        <p class="card-details">${product.getTimeAgo()}</p>
                    </div>
                </div>
            @endfor
        </div>
    @endif
`)
