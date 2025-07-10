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
            <h2 class="title">Recover your account</h2>

            <form action="/user/recover" method="POST">
                ${csrfHiddenInput}

                @template.partials.input(type = "email", id = "email", name = "email", placeholder = "Your email", fieldErrors = fieldErrors)

                <button type="submit" class="btn btn-primary">Recover account</button>
            </form>
        </div>

    </div>
`)
