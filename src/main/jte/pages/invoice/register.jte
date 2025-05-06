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
    <h2>Register Purchase</h2>

    <br>

    <div id="reader"></div>

    <form action="/invoice/register" method="POST" id="invoiceForm">
        ${csrfHiddenInput}
        <label for="invoiceUrl">
            Or input the link to the invoice here:
        </label>
        <input
                type="text"
                id="invoiceUrl"
                name="invoiceUrl"
                placeholder="Paste the invoice link here...">
        <button type="submit" class="button btn-purple">
            Submit Link
        </button>
    </form>

    <script src="https://unpkg.com/html5-qrcode" type="text/javascript"></script>
    <script src="/js/invoiceScanner.js" type="text/javascript"></script>
`)
