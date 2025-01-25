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

                    <!-- QR Code Scanner -->
                    <script type="text/javascript" src="/js/instascan.min.js"></script>
                    <video id="preview" style="width: 100%; max-width: 400px;"></video>

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

                    <script type="text/javascript">
                        // Initialize the scanner
                        let scanner = new Instascan.Scanner({ video: document.getElementById('preview') });

                        // Handle scanned content
                        scanner.addListener('scan', function (content) {
                            console.log('Scanned content:', content);

                            // Set the scanned content as the value of the invoiceUrl input
                            const invoiceInput = document.getElementById('invoiceUrl');
                            invoiceInput.value = content;

                            // Automatically submit the form
                            const invoiceForm = document.getElementById('invoiceForm');
                            invoiceForm.submit();
                        });

                        // Start the scanner if cameras are available
                        Instascan.Camera.getCameras().then(function (cameras) {
                            if (cameras.length > 0) {
                                scanner.start(cameras[0]);
                            } else {
                                console.error('No cameras found.');
                            }
                        }).catch(function (e) {
                            console.error(e);
                        });
                    </script>
                </div>
            </div>
        </main>
    </div>
`)
