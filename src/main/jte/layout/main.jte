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
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="font-sans bg-gray-900">

<nav class="bg-white shadow-sm">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between h-16">
            <div
                    class="flex items-center">
                <a href="/dashboard" class="text-xl font-semibold">Dashboard</a>
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
<!--  Main Content  -->
<main>
    ${content}
</main>
</body>
</html>
