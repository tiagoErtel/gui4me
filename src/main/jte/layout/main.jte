@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message
@import gui4me.user.User

@param gg.jte.Content content
@param CsrfHiddenInput csrfHiddenInput
@param Message message = null
@param User currentUser

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gui4Me Dashboard</title>
        <link rel="stylesheet" href="/css/styles.css">
        <link rel="icon" href="/images/logo.png" type="image/png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    </head>
    <body>

        <nav class="navbar">
            <a href="/dashboard" class="logo">Gui4Me</a>

            <div class="profile">
                <p>${currentUser.getUsername()}</p>

                <details class="dropdown">
                    <summary class="dropdown-toggle">&#x22EE;</summary>
                    <ul class="dropdown-menu">
                        <li onclick="document.getElementById('logout-form').submit();">
                            Logout
                        </li>
                        <li onclick="location.href='/user/settings'">Settings</li>
                    </ul>
                </details>
            </div>
        </nav>

        <main class="main-container">
            ${content}
        </main>

        <form id="logout-form" action="/logout" method="post" class="hidden">
            ${csrfHiddenInput}
        </form>

        <script src="/js/passwordValidation.js" type="text/javascript"></script>
        <script src="/js/showPassword.js" type="text/javascript"></script>
        <script src="/js/dropdownButton.js" type="text/javascript"></script>
    </body>
</html>
