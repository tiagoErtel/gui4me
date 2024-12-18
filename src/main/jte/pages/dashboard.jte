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
                    <h2 class="text-2xl font-semibold text-gray-800 mb-4">Register purchase</h2>
                    <button
                            class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-400">
                        Scan QR Code
                    </button>

                    <!-- Or Input Invoice Link -->
                    <form action="/invoice/register" method="POST" class="flex flex-col space-y-2">
                        ${csrfHiddenInput}
                        <label for="invoiceLink" class="text-gray-700 font-medium">
                            Or input the link to the invoice here:
                        </label>
                        <input
                                type="text"
                                id="invoiceUrl"
                                name="invoiceUrl"
                                class="border-gray-300 border rounded-lg px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400"
                                placeholder="Paste the invoice link here..."
                        />
                        <!-- Submit Button -->
                        <button
                                type="submit"
                                class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-green-400">
                            Submit Link
                        </button>
                    </form>
                </div>
            </div>
        </main>
    </div>
`)