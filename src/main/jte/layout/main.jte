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
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="icon" type="image/x-icon" href="/images/logo.png">
</head>
<body>

<nav>
    <a href="/dashboard" class="logo">Gui4Me</a>
</nav>


@if(message != null)
    @if(message.isError())
        <div class="message error">
            ${message.getMessage()}
        </div>
    @else
        <div class="message success">
            ${message.getMessage()}
        </div>
    @endif
@endif

<main>
    <div class="panel">
        ${content}
    </div>
</main>

</body>
</html>
