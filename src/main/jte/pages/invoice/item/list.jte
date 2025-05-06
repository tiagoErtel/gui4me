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
    <div>
        <h2>Invoice Items</h2>
        <p>Details of the selected invoice</p>
    </div>

    <a href="/invoice/list"
       class="button btn-purple back-button">
        ←
    </a>

    <table>
        <thead>
        <tr>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Unit</th>
            <th>Unit Price</th>
            <th>Total Price</th>
        </tr>
        </thead>
        <tbody>
        @if (invoiceItemsList == null || invoiceItemsList.isEmpty())
            <tr>
                <td colspan="5">
                    No items found.
                </td>
            </tr>
        @else
            @for (InvoiceItem item : invoiceItemsList)
                <tr class="table-row">
                    <td>${item.getProduct().getName()}</td>
                    <td>${item.getQuantity()}</td>
                    <td>${item.getUnit()}</td>
                    <td>R$ ${item.getUnitPrice()}</td>
                    <td>R$ ${item.getTotalPrice()}</td>
                </tr>
            @endfor
        @endif
        </tbody>
    </table>
`)
