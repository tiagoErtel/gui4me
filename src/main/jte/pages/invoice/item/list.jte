@import gui4me.invoice.Invoice
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
    <div class="bg-gray-100 py-6 px-4 sm:px-6 lg:px-8">
        <div class="w-full max-w-3xl p-6 bg-white rounded-lg shadow-md">
            <div class="mb-6 text-center">
                <h2 class="text-2xl font-bold text-gray-900">Invoice Items</h2>
                <p class="text-sm text-gray-600">Details of the selected invoice</p>
            </div>

            <div class="mb-4">
                <a href="/invoice/list"
                   class="inline-block bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600 text-sm">
                    ← Back to Invoices
                </a>
            </div>

            <div class="overflow-x-auto">
                <table class="w-full text-sm text-left border-collapse">
                    <thead class="bg-gray-50 text-gray-700 uppercase">
                    <tr>
                        <th class="px-4 py-3">Product Name</th>
                        <th class="px-4 py-3">Quantity</th>
                        <th class="px-4 py-3">Unit</th>
                        <th class="px-4 py-3">Unit Price</th>
                        <th class="px-4 py-3">Total Price</th>
                    </tr>
                    </thead>
                    <tbody class="text-gray-700">
                    @if (invoiceItemsList == null || invoiceItemsList.isEmpty())
                        <tr>
                            <td colspan="5" class="text-center py-6 text-gray-500">
                                No items found.
                            </td>
                        </tr>
                    @else
                        @for (InvoiceItem item : invoiceItemsList)
                            <tr class="border-t">
                                <td class="px-4 py-2">${item.getProduct().getName()}</td>
                                <td class="px-4 py-2">${item.getQuantity()}</td>
                                <td class="px-4 py-2">${item.getUnit()}</td>
                                <td class="px-4 py-2">R$ ${item.getUnitPrice()}</td>
                                <td class="px-4 py-2">R$ ${item.getTotalPrice()}</td>
                            </tr>
                        @endfor
                    @endif
                    </tbody>
                </table>
            </div>
        </div>
    </div>
`)
