@import gui4me.invoice_item.InvoiceItem
@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message
@import java.util.List

@param Message message = null
@param String username = ""
@param String email = null
@param CsrfHiddenInput csrfHiddenInput
@param List<InvoiceItem> invoiceItemsList = null

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
message = message,
username = username,
content = @`
    <section class="list-container">
        <div>
            @template.partials.back-button(href = "/invoice/list")

            <div class="list-header">
                <h2 class="title">Invoice Items</h2>
                <p class="subtitle">Details of the selected invoice</p>
            </div>
        </div>

        @if (invoiceItemsList == null || invoiceItemsList.isEmpty())
            <div class="no-items">
                <p>No items found.</p>
            </div>
        @else
            <div class="card-list">
                @for (InvoiceItem item : invoiceItemsList)
                    <div class="card-item">
                        <div class="card-content">
                            <h3 class="card-title">${item.getProduct().getName()}</h3>
                            <p class="card-details">${item.getQuantity()} ${item.getUnit()}</p>
                            <p class="card-details">Unit: R$ ${item.getUnitPrice()}</p>
                            <p class="card-details">Total: R$ ${item.getTotalPrice()}</p>
                        </div>
                    </div>
                @endfor
            </div>
        @endif
    </section>
`)
