@import gui4me.invoice.Invoice
@import gui4me.product.ProductSearchResultDTO
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
    <div class="min-h-screen bg-gray-100">
        <main class="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
            <div class="bg-white shadow rounded-lg p-6">
                <h2 class="text-2xl font-semibold text-gray-800 mb-6">Invoices List</h2>

                <div class="mt-6">
                    <!-- Results Section -->
                    <table class="w-full text-left border-collapse">
                        <thead>
                        <tr>
                            <th class="border-b py-2 px-4">Invoice Key</th>
                            <th class="border-b py-2 px-4">Store Name</th>
                            <th class="border-b py-2 px-4">Total Price</th>
                            <th class="border-b py-2 px-4">Purchase Date</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if (invoiceList == null)
                            <tr>
                                <td colspan="4" class="text-center text-gray-500 py-4">
                                    <h3 class="text-lg font-semibold">No products found</h3>
                                    <p>Try searching for a different product.</p>
                                </td>
                            </tr>
                        @else
                            @for (Invoice invoice : invoiceList)
                                <tr>
                                    <td class="py-2 px-4">${invoice.getChave()}</td>
                                    <td class="py-2 px-4">${invoice.getStore().getName()}</td>
                                    <td class="py-2 px-4">${invoice.getTotalPrice()}</td>
                                    <td class="py-2 px-4">${invoice.getFormattedIssuanceDate()}</td>
                                    <td class="py-2 px-4">
                                        <a href="/invoice/item/list?invoiceId=${invoice.getId()}" class="bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-600 inline-block text-center">View Items</a>
                                    </td>
                                </tr>
                            @endfor
                        @endif
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
    </div>
`)
