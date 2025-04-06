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
    <div class="py-6 px-4 sm:px-6 lg:px-8 bg-gray-100">
        <div class="w-full max-w-3xl p-6 bg-white rounded-lg shadow-md">
            <div class="mb-6 text-center">
                <h2 class="text-2xl font-bold text-gray-900">Invoices List</h2>
                <p class="text-sm text-gray-600">Details of your invoices</p>
            </div>

            <div class="mb-4">
                <a href="/dashboard"
                   class="inline-block bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600 text-sm">
                    ← Back to Dashboard
                </a>
            </div>

            <div class="overflow-x-auto">
                <table class="w-full text-sm text-left border-collapse">
                    <thead class="bg-gray-50 text-gray-700 uppercase">
                    <tr>
                        <th class="px-4 py-3">Store Name</th>
                        <th class="px-4 py-3">Total Price</th>
                        <th class="px-4 py-3">Purchase Date</th>
                        <th class="px-4 py-3">Actions</th>
                    </tr>
                    </thead>
                    <tbody class="text-gray-700">
                    @if (invoiceList == null || invoiceList.isEmpty())
                        <tr>
                            <td colspan="5" class="text-center py-6 text-gray-500">
                                No invoices found.
                            </td>
                        </tr>
                    @else
                        @for (Invoice invoice : invoiceList)
                            <tr class="border-t">
                                <td class="px-4 py-2">${invoice.getStore().getName()}</td>
                                <td class="px-4 py-2">R$ ${invoice.getTotalPrice()}</td>
                                <td class="px-4 py-2">${invoice.getFormattedIssuanceDate()}</td>
                                <td class="px-4 py-2">
                                    <a href="/invoice/item/list?invoiceId=${invoice.getId()}"
                                       class="bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-600 inline-block text-center">
                                        View Items
                                    </a>
                                </td>
                            </tr>
                        @endfor
                    @endif
                    </tbody>
                </table>
            </div>
        </div>
    </div>
`)
