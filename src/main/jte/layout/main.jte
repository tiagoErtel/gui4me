@import org.springframework.security.web.csrf.CsrfToken

@param gg.jte.Content navbar
@param gg.jte.Content content

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
@if (navbar != null)
    <div>
        ${navbar}
    </div>
@endif
<!--  Main Content  -->
<main>
    ${content}
</main>
</body>
</html>
