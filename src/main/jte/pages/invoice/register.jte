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
    <a href="/dashboard" class="back-button">
        <i class="fas fa-arrow-left"></i>
    </a>

    <div class="list-header">
        <h2 class="title">Register Purchase</h2>
        <p class="subtitle">Scan the invoice or input the link</p>
    </div>

    <div id="reader"></div>

    <form action="/invoice/register" method="POST" id="invoiceForm" class="mt-2">
        ${csrfHiddenInput}
        <label for="invoiceUrl" class="label">Or input the link to the invoice here:</label>
        <input
                type="text"
                id="invoiceUrl"
                name="invoiceUrl"
                placeholder="Paste the invoice link here..."
                required
        />
        <button type="submit" class="btn btn-primary">
            Submit Link
        </button>
    </form>

    <script src="https://unpkg.com/html5-qrcode" type="text/javascript"></script>
    <script src="/js/invoiceScanner.js" type="text/javascript"></script>
`)
