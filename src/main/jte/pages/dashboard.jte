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
                    <h1 class="text-2xl font-semibold text-gray-800 mb-4">Welcome to Your Dashboard, ${username}!</h1>

                    <p class="text-gray-700 mb-6">What would you like to do today?</p>

                    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                        <!-- Action Cards -->
                        <a href="/product/search" class="block p-6 bg-gray-50 shadow rounded-lg hover:shadow-md transition">
                            <h2 class="text-lg font-medium text-gray-800">Search for Products</h2>
                            <p class="text-gray-600 mt-2">Find the products you need quickly and efficiently.</p>
                        </a>

                        <a href="/invoice/register" class="block p-6 bg-gray-50 shadow rounded-lg hover:shadow-md transition">
                            <h2 class="text-lg font-medium text-gray-800">Register a Purchase</h2>
                            <p class="text-gray-600 mt-2">Add new purchases and keep your records up to date.</p>
                        </a>

                        <a href="/invoice/list" class="block p-6 bg-gray-50 shadow rounded-lg hover:shadow-md transition">
                            <h2 class="text-lg font-medium text-gray-800">List Invoices</h2>
                            <p class="text-gray-600 mt-2">List your invoices and drill down the information.</p>
                        </a>

                        <a href="/reports/view" class="block p-6 bg-gray-50 shadow rounded-lg hover:shadow-md transition">
                            <h2 class="text-lg font-medium text-gray-800">View Reports</h2>
                            <p class="text-gray-600 mt-2">Analyze your data with detailed reports.</p>
                        </a>

                        <a href="/settings/preferences" class="block p-6 bg-gray-50 shadow rounded-lg hover:shadow-md transition">
                            <h2 class="text-lg font-medium text-gray-800">Manage Preferences</h2>
                            <p class="text-gray-600 mt-2">Customize your settings and preferences.</p>
                        </a>
                    </div>
                </div>
            </div>
        </main>
    </div>
`)
