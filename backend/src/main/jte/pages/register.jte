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
            <h2 class="title">Create your account</h2>
            <p class="subtitle">Please fill in the details below to get started.</p>

            <form action="/register" method="POST" id="passwordForm">
                ${csrfHiddenInput}

                @template.partials.input(type = "text", id = "username", name = "username", placeholder = "Name", fieldErrors = fieldErrors)

                @template.partials.input(type = "email", id = "email", name = "email", placeholder = "Email", fieldErrors = fieldErrors)

                @template.partials.input(type = "password", id = "newPassword", name = "newPassword", placeholder = "Password", fieldErrors = fieldErrors)

                @template.partials.input(type = "password", id = "confirmPassword", name = "confirmPassword", placeholder = "Confirm Password", fieldErrors = fieldErrors)

                <button type="submit" class="btn btn-primary">Register</button>
            </form>

            @template.partials.oauth()

            <p class="mt-2">
                Already have an account?
                <a href="/login" class="link">Sign in here</a>
            </p>
        </div>
    </div>
`)
