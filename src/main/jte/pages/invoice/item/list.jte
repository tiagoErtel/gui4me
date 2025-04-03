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
    <div class="min-h-screen bg-gray-100">
        <main class="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
            <div class="bg-white shadow rounded-lg p-6">
                <h2 class="text-2xl font-semibold text-gray-800 mb-6">Invoices Items List</h2>

                <!-- Botão para voltar -->
                <div class="mb-4">
                    <a href="/invoice/list" class="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600">
                        ← Back to Invoices
                    </a>
                </div>

                <div class="mt-6">
                    <!-- Results Section -->
                    <table class="w-full text-left border-collapse">
                        <thead>
                        <tr>
                            <th class="border-b py-2 px-4">Product Name</th>
                            <th class="border-b py-2 px-4">Quantity</th>
                            <th class="border-b py-2 px-4">Unit</th>
                            <th class="border-b py-2 px-4">Unit Price</th>
                            <th class="border-b py-2 px-4">Total Price</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if (invoiceItemsList == null)
                            <tr>
                                <td colspan="4" class="text-center text-gray-500 py-4">
                                    <h3 class="text-lg font-semibold">No items found</h3>
                                </td>
                            </tr>
                        @else
                            @for (InvoiceItem invoiceItem : invoiceItemsList)
                                <tr>
                                    <td class="py-2 px-4">${invoiceItem.getProduct().getName()}</td>
                                    <td class="py-2 px-4">${invoiceItem.getQuantity()}</td>
                                    <td class="py-2 px-4">${invoiceItem.getUnit()}</td>
                                    <td class="py-2 px-4">${invoiceItem.getUnitPrice()}</td>
                                    <td class="py-2 px-4">${invoiceItem.getTotalPrice()}</td>
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
