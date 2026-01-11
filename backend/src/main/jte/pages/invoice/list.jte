@import gui4me.invoice.Invoice
@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message
@import java.util.List
@import gui4me.user.User

@param Message message = null
@param User currentUser
@param CsrfHiddenInput csrfHiddenInput
@param List<Invoice> invoiceList = null
@param String sort = "issuanceDate,desc"

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
message = message,
currentUser = currentUser,
content = @`
    @template.partials.back-button(href = "/dashboard")

    <div class="list-header">
        <h2 class="title">Invoices List</h2>
        <p class="subtitle">Explore the details of your invoices</p>
    </div>

    @if (invoiceList == null || invoiceList.isEmpty())
        <div class="no-items">
            <p>No invoices found.</p>
        </div>
    @else
        <form method="get" action="/invoice/list" class="sort-by-form">
            <label for="sort">Sort by:</label>
            <select id="sort" name="sort" onchange="this.form.submit()">
                <option hidden selected="true">
                    ${sort.contains("issuanceDate") ? "Date" : sort.contains("totalPrice") ? "Total Price" : ""}
                    ${sort.contains("asc") ? "▲" : "▼"}
                </option>
                <option
                    value="${sort.equals("issuanceDate,desc") ? "issuanceDate,asc" : "issuanceDate,desc"}">
                    Date
                </option>
                <option
                    value="${sort.equals("totalPrice,desc") ? "totalPrice,asc" : "totalPrice,desc"}">
                    Total Price
                </option>

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
`)

