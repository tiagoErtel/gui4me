@import gui4me.utils.Message
@import gui4me.security.CsrfHiddenInput
@import java.util.Map

@param Message message = null
@param CsrfHiddenInput csrfHiddenInput
@param Map<String, String> fieldErrors = java.util.Collections.emptyMap()

@template.layout.default(
message = message,
content = @`
    <div class="center">
        <div class="login-container panel">
            <h2 class="title">Sign in to your account</h2>
            <p class="subtitle">Enter your credentials to access your account.</p>

            <form action="/login" method="POST">
                ${csrfHiddenInput}

                @template.partials.input(type = "email", id = "email", name = "email", placeholder = "Email", fieldErrors = fieldErrors)

                @template.partials.input(type = "password", id = "password", name = "password", placeholder = "Password", fieldErrors = fieldErrors)

                <button type="submit" class="btn btn-primary">Sign in</button>
            </form>

            @template.partials.oauth()

            <p class="mt-2">
                Don't have an account?
                <a href="/register" class="link">Create one here</a>
            </p>
            <p class="mt-2">
                Forgot your account?
                <a href="/user/recover" class="link">Recover your account</a>
            </p>
        </div>

    </div>
`)
