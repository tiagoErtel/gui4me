@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message
@import gui4me.user.User
@import java.util.Map

@param Message message = null
@param User currentUser
@param CsrfHiddenInput csrfHiddenInput
@param Map<String, String> fieldErrors = java.util.Collections.emptyMap()

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
message = message,
currentUser = currentUser,
content = @`

    @template.partials.back-button(href = "/dashboard")

    <div class="list-header">
        <h2 class="title">Register Purchase</h2>
        <p class="subtitle">Scan the invoice or input the link</p>
    </div>

    <label for="cameraSelect">Choose camera:</label>
    <select id="cameraSelect"></select>

    <div id="qrcode-reader"></div>

    <p id="showInvoiceLink" class="mt-2">
        Having trouble scanning?
        <a class="link" onclick="showManualInput()">
            Click here to paste the invoice link manually.
        </a>
    </p>

    <script>
        function showManualInput() {
            document.getElementById('invoiceForm').classList.remove('hidden');
        }
    </script>

    <div id="loadingOverlay" class="loading-overlay">
        <div class="loading-box">
            <span class="spinner"></span>
            <span>Loading invoice, please wait...</span>
        </div>
    </div>

    <form action="/invoice/register" method="POST" id="invoiceForm" class="mt-2 hidden">
        ${csrfHiddenInput}

        @template.partials.input(type = "text", id = "invoiceUrl", name = "invoiceUrl", placeholder = "Paste the invoice link here", fieldErrors = fieldErrors)

        <button type="submit" class="btn btn-primary">
            Submit Link
        </button>
    </form>

    <script src="https://unpkg.com/html5-qrcode" type="text/javascript"></script>
    <script src="/js/invoiceScanner.js" type="text/javascript"></script>
`)
