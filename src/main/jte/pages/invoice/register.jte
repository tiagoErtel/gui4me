@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message

@param Message message = null
@param String username = ""
@param String email = null
@param CsrfHiddenInput csrfHiddenInput

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
message = message,
username = username,
content = @`
<div class="min-h-screen bg-gray-100">
    <main class="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">

        <div class="px-4 py-6 sm:px-0">
            <div class="bg-white shadow rounded-lg p-6">
                <h2 class="text-2xl font-semibold text-gray-800 mb-4">Register Purchase</h2>

                <!-- QR Code Scanner using html5-qrcode -->
                <div id="reader" style="width: 400px;"></div>

                <!-- Or Input Invoice Link -->
                <form action="/invoice/register" method="POST" class="flex flex-col space-y-2 mt-6" id="invoiceForm">
                    ${csrfHiddenInput}
                    <label for="invoiceLink" class="text-gray-700 font-medium">
                        Or input the link to the invoice here:
                    </label>
                    <input
                            type="text"
                            id="invoiceUrl"
                            name="invoiceUrl"
                            class="border-gray-300 border rounded-lg px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400"
                            placeholder="Paste the invoice link here...">
                    <button type="submit"
                            class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-green-400">
                        Submit Link
                    </button>
                </form>

                <script src="https://unpkg.com/html5-qrcode" type="text/javascript"></script>
                <script src="/js/invoice-scanner.js" type="text/javascript"></script>
            </div>
        </div>
    </main>
</div>
`)
