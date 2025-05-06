@import gui4me.utils.Message
@import gui4me.security.CsrfHiddenInput

@param Message message = null
@param CsrfHiddenInput csrfHiddenInput

@template.layout.default(
content = @`
    <h2>Create your account</h2>
    <p>Please fill in the details below to get started.</p>
    <br>

    @if(message != null && message.isError())
        <div class="message error">
            ${message.getMessage() != null ? message.getMessage() : "An error occurred."}
        </div>
    @endif

    <form action="/register" method="POST" id="register-form">
        ${csrfHiddenInput}

        <div>
            <input id="username" name="username" type="text" required
                   placeholder="Full Name"
                   class="${message != null && message.isError() ? "input-error" : ""}">
        </div>

        <div>
            <input id="email" name="email" type="email" required
                   placeholder="Email"
                   class="${message != null && message.isError() ? "input-error" : ""}">
        </div>

        <div>
            <input id="password" name="password" type="password" required
                   placeholder="Password"
                   class="${message != null && message.isError() ? "input-error" : ""}">
        </div>

        <button type="submit" class="button btn-purple">Register</button>
    </form>

    <div>
        <p>Already have an account? <a href="/login">Sign in here</a></p>
    </div>
`)
