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
    <div class="card-grid">
        <!-- Action Cards -->
        <a href="/product/search" class="card">
            <h2>Search for Products</h2>
            <p>Find the products you need quickly and efficiently.</p>
        </a>

        <a href="/invoice/register" class="card">
            <h2>Register a Purchase</h2>
            <p>Add new purchases and keep your records up to date.</p>
        </a>

        <a href="/invoice/list" class="card">
            <h2>List Invoices</h2>
            <p>List your invoices and drill down the information.</p>
        </a>

        <a href="/shopping-list" class="card">
            <h2>Your Shopping Lists</h2>
            <p>Add and manage your shopping lists</p>
        </a>

        <a href="/reports/view" class="card">
            <h2>View Reports</h2>
            <p>Analyze your data with detailed reports.</p>
        </a>

        <a href="/settings/preferences" class="card">
            <h2>Manage Preferences</h2>
            <p>Customize your settings and preferences.</p>
        </a>
    </div>
`)
