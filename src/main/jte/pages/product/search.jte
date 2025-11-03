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

    <div style="display: flex; justify-content: space-between; align-items: center;">
        <div class="list-header">
            <h2 class="title">Search Products</h2>
            <p class="subtitle">Find items in out database</p>
        </div>

        <div id="show-form-btn" class="btn btn-primary">
            <i class="fas fa-filter"></i>
        </div>
    </div>

    <form
        hx-get="/product/search"
        hx-target="#products-cards"
        hx-trigger="submit"
        hx-push-url="true"
    >
        <div id="filter-container">
            <div class="panel filter-container hidden">
                @template.partials.input(type = "date", id = "minDate", name = "minDate", required = false, label = "First purchase date")

                @template.partials.input(type = "date", id = "maxDate", name = "maxDate", required = false, label = "Last purchase date")

                @template.partials.input(type = "number", id = "distance", name = "distance", placeholder = "KM from you", required = false, label = "Distance from You (km)")

                @template.partials.input(type = "double", id = "latitude", name = "latitude", required = false, hidden = true)
                @template.partials.input(type = "double", id = "longitude", name = "longitude", required = false, hidden = true)

                <button type="submit" class="btn btn-primary">
                    <i class="fa fa-search"></i> Filtrar
                </button>
            </div>
        </div>

        <div class="search-container">
            <input
                type="text"
                name="productName"
                placeholder="Search by product name..."
            />
            <button type="submit"><i class="fa fa-search"></i></button>
        </div>
    </form>

    <div id="products-cards">
        @if (products == null || products.isEmpty())
            <div class="no-items">
                <p>No products found. Try a different search term.</p>
            </div>
        @else
            @template.pages.product._analyse_card(products=products)
        @endif
    </div>

    <script>
    document.addEventListener("DOMContentLoaded", () => {
        const latitudeInput = document.getElementById("latitude");
        const longitudeInput = document.getElementById("longitude");

        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    latitudeInput.value = position.coords.latitude;
                    longitudeInput.value = position.coords.longitude;
                },
                (error) => {
                    console.warn("Location not available:", error.message);
                }
            );
        } else {
            console.warn("Geolocation not supported by this browser.");
        }

        const filterContainer = document.getElementById("filter-container")
        const showFilterContainerBtn = document.getElementById("show-form-btn")

        if (showFilterContainerBtn && filterContainer) {
            showFilterContainerBtn.addEventListener("click", () => {
                filterContainer.classList.toggle("hidden");
            });
        }
    });
    </script>
`)
