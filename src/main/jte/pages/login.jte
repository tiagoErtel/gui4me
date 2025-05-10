@import gui4me.utils.Message
@import gui4me.security.CsrfHiddenInput

@param Message message = null
@param CsrfHiddenInput csrfHiddenInput

@template.layout.default(
content = @`
    <div class="panel">
        <h2 class="title">Sign in to your account</h2>
        <p class="subtitle">Enter your credentials to access your account.</p>

        @if(message != null && message.isError())
            <div class="message error">${message.getMessage()}</div>
        @endif

        <form action="/login" method="POST">
            ${csrfHiddenInput}
            <input name="email" type="email" required placeholder="Email"
                   class="${message != null && message.isError() ? "input-error" : ""}" />
            <input name="password" type="password" required placeholder="Password"
                   class="${message != null && message.isError() ? "input-error" : ""}" />

            <button type="submit" class="btn btn-primary">Sign in</button>
        </form>

        <p class="mt-2">
            Don't have an account?
            <a href="/register" class="link">Create one here</a>
        </p>
    </div>
`)
