@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message

@param gg.jte.Content content
@param CsrfHiddenInput csrfHiddenInput
@param Message message = null
@param String username = ""
@param String email = null

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gui4Me</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="font-sans bg-gray-100 text-gray-900">

<!-- Navbar -->
<nav class="bg-white shadow-md">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between h-16 items-center">
            <div class="flex items-center space-x-4">
                <a href="/dashboard" class="text-xl font-bold text-indigo-600 hover:text-indigo-800">Gui4Me</a>
            </div>
            <div class="flex items-center space-x-4">
                <div class="text-sm text-gray-700 hidden sm:block">
                    Welcome, <span class="font-medium">${username}</span>
                    @if(email != null)
                        <span class="block text-gray-500 text-xs">${email}</span>
                    @endif
                </div>
                <form action="/logout" method="post">
                    ${csrfHiddenInput}
                    <button type="submit"
                            class="px-4 py-2 bg-indigo-600 text-white text-sm rounded-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500">
                        Logout
                    </button>
                </form>
            </div>
        </div>
    </div>
</nav>

<!-- Alerts -->
@if(message != null)
<div class="max-w-4xl mx-auto mt-4 px-4">
    @if(message.isError())
        <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
            <span>${message.getMessage() != null ? message.getMessage() : "An error occurred."}</span>
        </div>
    @endif

    @if(message.isSuccess())
        <div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-4">
            <span>${message.getMessage() != null ? message.getMessage() : "Operation successful!"}</span>
        </div>
    @endif
</div>
@endif

<!-- Page Content -->
<main class="max-w-4xl mx-auto px-4 py-6">
    ${content}
</main>

</body>
</html>
