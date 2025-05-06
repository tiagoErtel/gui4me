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
    <div>
        <h2>Invoices List</h2>
        <p>Details of your invoices</p>
    </div>

    <a href="/dashboard"
       class="button btn-purple back-button">
        ←
    </a>

    <table>
        <thead>
        <tr>
            <th>Store Name</th>
            <th>Total Price</th>
            <th>Purchase Date</th>
        </tr>
        </thead>
        <tbody>
        @if (invoiceList == null || invoiceList.isEmpty())
            <tr>
                <td colspan="5">
                    No invoices found.
                </td>
            </tr>
        @else
            @for (Invoice invoice : invoiceList)
                <tr class="table-row clickable" onclick="location.assign('/invoice/item/list?invoiceId=${invoice.getId()}')">
                    <td>${invoice.getStore().getName()}</td>
                    <td>R$ ${invoice.getTotalPrice()}</td>
                    <td>${invoice.getFormattedIssuanceDate()}</td>
                </tr>
            @endfor
        @endif
        </tbody>
    </table>
`)
