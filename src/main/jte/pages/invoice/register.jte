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
    @template.partials.message(message = message) 

    @template.partials.back-button(href = "/dashboard")

    <div class="list-header">
        <h2 class="title">Register Purchase</h2>
        <p class="subtitle">Scan the invoice or input the link</p>
    </div>

    <label for="cameraSelect">Choose camera:</label>
    <select id="cameraSelect"></select>

    <div id="reader" class="mt-2"></div>

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

    <form action="/invoice/register" method="POST" id="invoiceForm" class="mt-2 hidden">
        ${csrfHiddenInput}
        <input
                type="text"
                id="invoiceUrl"
                name="invoiceUrl"
                placeholder="Paste the invoice link here"
                required
        />
        <button type="submit" class="btn btn-primary">
            Submit Link
        </button>
    </form>

    <script src="https://unpkg.com/html5-qrcode" type="text/javascript"></script>
    <script src="/js/invoiceScanner.js" type="text/javascript"></script>
`)
