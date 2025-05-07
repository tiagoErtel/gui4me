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
    <h2>Search Products</h2>

    <a href="/dashboard" class="button btn-purple back-button">
        ←
    </a>

    <form action="/product/search" method="GET">
        <input
                type="text"
                name="productName"
                placeholder="Search by product name..."
        />
        <button class="button btn-purple"
                type="submit"
        >
            Search
        </button>
    </form>

    <table>
        <thead>
        <tr>
            <th>Product Name</th>
            <th>Unit Price</th>
            <th>Store</th>
            <th>Last Purchase Date</th>
        </tr>
        </thead>
        <tbody>
        @if (productList == null)
            <tr>
                <td colspan="4">
                    <h3>No products found</h3>
                    <p>Try searching for a different product.</p>
                </td>
            </tr>
        @else
            @for (ProductSearchResultDTO product : productList)
                <tr>
                    <td>${product.getProductName()}</td>
                    <td>R$ ${product.getUnitPrice()}</td>
                    <td>${product.getStoreName()}</td>
                    <td>${product.getFormattedLastIssuanceDate()}</td>
                </tr>
            @endfor
        @endif
        </tbody>
    </table>
`)
