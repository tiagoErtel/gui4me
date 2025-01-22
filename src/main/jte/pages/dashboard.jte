@import org.springframework.security.core.GrantedAuthority
@import java.util.Collection
@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message

@param Message message = null
@param String username = ""
@param String email = null
@param Collection<? extends GrantedAuthority> authorities = null
@param CsrfHiddenInput csrfHiddenInput

@template.layout.main(
navbar = @`
    <nav class="bg-white shadow-sm">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div class="flex justify-between h-16">
                <div class="flex items-center">
                    <h1 class="text-xl font-semibold">Dashboard</h1>
                </div>
                <div class="flex items-center space-x-4">
                    <span class="text-gray-700">Welcome, ${username}</span>
                    @if(email != null)
                        <span class="text-gray-500 text-sm">${email}</span>
                    @endif
                    <form action="/logout" method="post">
                        ${csrfHiddenInput}
                        <button type="submit"
                                class="px-4 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700">
                            Logout
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </nav>
`,
content = @`
<div class="min-h-screen bg-gray-100">
    <main class="max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">

        @if(message != null && message.isError())
            <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4" role="alert">
                    <span class="block sm:inline">
                        ${message.getMessage() != null ? message.getMessage() : "An error occurred."}
                    </span>
            </div>
        @endif

        @if(message != null && message.isSuccess())
            <div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mb-4" role="alert">
                    <span class="block sm:inline">
                        ${message.getMessage() != null ? message.getMessage() : "Operation successful!"}
                    </span>
            </div>
        @endif

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
