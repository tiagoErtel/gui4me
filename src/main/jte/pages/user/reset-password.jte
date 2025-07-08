@import gui4me.utils.Message
@import gui4me.security.CsrfHiddenInput
@import java.util.Map

@param Message message = null
@param String email
@param String token
@param CsrfHiddenInput csrfHiddenInput
@param Map<String, String> fieldErrors = java.util.Collections.emptyMap()

@template.layout.default(
content = @`

    @template.partials.message(message = message)

    <div class="center">
        <div class="login-container panel">
            <h2 class="title">Reset your password</h2>

            <form action="/user/reset-password" method="POST">
                ${csrfHiddenInput}

                @template.partials.input(type = "email", id = "email", name = "email", value = email, hidden = true)

                @template.partials.input(type = "text", id = "token", name = "token", value = token, hidden = true)

                @template.partials.input(type = "password", id = "newPassword", name = "newPassword", placeholder = "Password", fieldErrors = fieldErrors)

                @template.partials.input(type = "password", id = "confirmPassword", name = "confirmPassword", placeholder = "Confirm Password", fieldErrors = fieldErrors)

                <button type="submit" class="btn btn-primary">Reset password</button>
            </form>
        </div>

    </div>
`)
