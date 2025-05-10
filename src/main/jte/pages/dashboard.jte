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
    <section class="dashboard">
        <h1 class="title">Welcome back, ${username}!</h1>

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
            <a href="/reports/view" class="card">
                <h2>📊 Reports</h2>
                <p>Analyze your purchases and patterns.</p>
            </a>
            <a href="/settings/preferences" class="card">
                <h2>⚙️ Preferences</h2>
                <p>Customize your settings.</p>
            </a>
        </div>
    </section>
`)
