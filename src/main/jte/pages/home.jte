@template.layout.main(
navbar = null,
content = @`
    <div class="min-h-screen bg-gray-100 flex items-center justify-center">
        <div class="max-w-md w-full bg-white p-8 rounded-lg shadow-md">
            <h1 class="text-4xl font-extrabold text-center text-indigo-600 mb-4">Welcome to Gui4me</h1>
            <p class="text-gray-600 text-center mb-6">
                Discover amazing features by logging in or creating an account. Start your journey now!
            </p>
            <div class="flex justify-center space-x-4">
                <a href="/login"
                   class="w-1/2 px-4 py-2 bg-indigo-600 text-white font-medium rounded-md text-center hover:bg-indigo-700">
                    Login
                </a>
                <a href="/register"
                   class="w-1/2 px-4 py-2 bg-green-500 text-white font-medium rounded-md text-center hover:bg-green-600">
                    Register
                </a>
            </div>
        </div>
    </div>
`)
