@import gui4me.invoice.Invoice
@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message
@import java.util.List

@param Message message = null
@param String username = ""
@param String email = null
@param CsrfHiddenInput csrfHiddenInput
@param List<Invoice> invoiceList = null

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
message = message,
username = username,
content = @`
    <section class="list-container">
        <div>
            <a href="/dashboard" class="back-button">
                <i class="fas fa-arrow-left"></i>
            </a>

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

