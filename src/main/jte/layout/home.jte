@import gui4me.utils.Message

@param gg.jte.Content content
@param Message message = null

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gui4Me</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="font-sans bg-gray-900 text-gray-300">

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
