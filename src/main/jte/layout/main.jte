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
	<title>Gui4Me Dashboard</title>
	<link rel="stylesheet" href="/css/styles.css">
	<link rel="icon" href="/images/logo.png" type="image/png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

	<nav class="navbar">
		<a href="/dashboard" class="logo">Gui4Me</a>
	</nav>

	<main class="main-container">
		${content}
	</main>

</body>
</html>