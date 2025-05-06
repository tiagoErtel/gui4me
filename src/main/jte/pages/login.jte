@import gui4me.utils.Message
@import gui4me.security.CsrfHiddenInput

@param Message message = null
@param CsrfHiddenInput csrfHiddenInput

@template.layout.default(
    content = @`
    <h2>Sign in to your account</h2>
    <p>Enter your credentials to access your account.</p>
    <br>

    @if(message != null && message.isError())
        <div class="message error">
            ${message.getMessage()}
        </div>
    @endif

    <form action="/login" method="POST">
        ${csrfHiddenInput}
        <div>
            <input name="email" type="text" required
                   placeholder="Email"
                   class="${message != null && message.isError() ? "input-error" : ""}"
                   id="email">
        </div>
        <div>
            <input name="password" type="password" required
                   placeholder="Password"
                   class="${message != null && message.isError() ? "input-error" : ""}"
                   id="password">
        </div>

        <button type="submit" class="button btn-purple">
            Sign in
        </button>
    </form>

    <p>
        Don't have an account?
        <a href="/register">
            Create one here
        </a>
    </p>
`)