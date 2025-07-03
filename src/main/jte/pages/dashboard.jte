@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message
@import gui4me.user.User

@param Message message = null
@param User currentUser
@param CsrfHiddenInput csrfHiddenInput

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
message = message,
currentUser = currentUser,
content = @`
    @template.partials.message(message = message)

    <div class="grid">
        <a href="/product/search" class="card">
            <h2>🔍 Search Products</h2>
            <p>Quickly find any product you need.</p>
        </a>
        <a href="/invoice/register" class="card">
            <h2>🧾 Register Purchase</h2>
            <p>Record your recent shopping.</p>
        </a>
        <a href="/invoice/list" class="card">
            <h2>📄 List Invoices</h2>
            <p>View and manage your invoice history.</p>
        </a>
        <a href="/shopping-list" class="card">
            <h2>🛒 Shopping Lists</h2>
            <p>Create and track shopping plans.</p>
        </a>
        <a href="/report/dashboard" class="card">
            <h2>📊 Reports</h2>
            <p>Analyze your purchases and patterns.</p>
        </a>
    </div>
`)
