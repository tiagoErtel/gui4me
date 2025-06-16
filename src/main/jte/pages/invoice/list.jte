@import gui4me.invoice.Invoice
@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message
@import java.util.List

@param Message message = null
@param String username = ""
@param String email = null
@param CsrfHiddenInput csrfHiddenInput
@param List<Invoice> invoiceList = null
@param String sort = "issuanceDate,desc"

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
message = message,
username = username,
content = @`
    <section class="list-container">
        <div>
            @template.partials.back-button(href = "/dashboard")

            <div class="list-header">
                <h2 class="title">Invoices List</h2>
                <p class="subtitle">Explore the details of your invoices</p>
            </div>
        </div>

        @if (invoiceList == null || invoiceList.isEmpty())
            <div class="no-items">
                <p>No invoices found.</p>
            </div>
        @else

            <form method="get" action="/invoice/list">
                <label for="sort">Sort by:</label>
                <select id="sort" name="sort" onchange="this.form.submit()">
                    <option value="issuanceDate,desc" selected="${"issuanceDate,desc".equals(sort)}">Date ↓</option>
                    <option value="issuanceDate,asc" selected="${"issuanceDate,asc".equals(sort)}">Date ↑</option>
                    <option value="totalPrice,desc" selected="${"totalPrice,desc".equals(sort)}">Total Price ↓</option>
                    <option value="totalPrice,asc" selected="${"totalPrice,asc".equals(sort)}">Total Price ↑</option>
                </select>
            </form>

            <div class="card-list">
                @for (Invoice invoice : invoiceList)
                    <div class="card-item" onclick="location.assign('/invoice/item/list?invoiceId=${invoice.getId()}')">
                        <div class="card-content">
                            <h3 class="card-title">${invoice.getStore().getName()}</h3>
                            <p class="card-details">R$ ${invoice.getTotalPrice()}</p>
                            <p class="card-details"> ${invoice.getFormattedIssuanceDate()}</p>
                        </div>

                    </div>
                @endfor
            </div>
        @endif
    </section>
`)

