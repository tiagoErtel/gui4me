@import gui4me.utils.Message

@param gg.jte.Content content
@param Message message = null

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gui4Me</title>
        <link rel="stylesheet" href="/css/styles.css">
        <link rel="icon" type="image/x-icon" href="/images/logo.png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    </head>
    <body>
        <main class="main-container">
            ${content}
        </main>
        <script src="/js/passwordValidation.js" type="text/javascript"></script>
        <script src="/js/showPassword.js" type="text/javascript"></script>
    </body>
</html>
