@import gui4me.utils.Message
@import gui4me.security.CsrfHiddenInput

@param Message message = null
@param CsrfHiddenInput csrfHiddenInput

@template.layout.home(
    message = message,
    content = @`
    <div class="min-h-screen flex items-center justify-center bg-gray-100">
        <div class="max-w-md w-full p-8 bg-white rounded-lg shadow-md">
            <div class="mb-6 text-center">
                <h2 class="text-3xl font-extrabold text-gray-900">
                    Sign in to your account
                </h2>
                <p class="mt-2 text-sm text-gray-600">
                    Enter your credentials to access your account.
                </p>
            </div>

            @if(message != null && message.isError())
                <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4" role="alert">
                    <span class="block sm:inline">
                        ${message.getMessage() != null ? message.getMessage() : "An error occurred."}
                    </span>
                </div>
            @endif

            <!-- Login Form -->
            <form action="/login" method="POST" class="space-y-6">
                ${csrfHiddenInput}
                <div class="space-y-4">
                    <div>
                        <input name="email" type="text" required
                               class="appearance-none rounded-md block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                               placeholder="Email">
                    </div>
                    <div>
                        <input name="password" type="password" required
                               class="appearance-none rounded-md block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                               placeholder="Password">
                    </div>
                </div>

                <div>
                    <button type="submit"
                            class="w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                        Sign in
                    </button>
                </div>
            </form>

            <div class="mt-4 text-center">
                <p class="text-sm text-gray-600">
                    Don't have an account?
                    <a href="/register" class="text-indigo-600 hover:underline">
                        Create one here
                    </a>
                </p>
            </div>
        </div>
    </div>
`)
