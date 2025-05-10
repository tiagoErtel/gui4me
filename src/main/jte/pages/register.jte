@import gui4me.utils.Message
@import gui4me.security.CsrfHiddenInput

@param Message message = null
@param CsrfHiddenInput csrfHiddenInput

@template.layout.default(
content = @`
    <div class="panel">
        <h2 class="title">Create your account</h2>
        <p class="subtitle">Please fill in the details below to get started.</p>

        @if(message != null && message.isError())
            <div class="message error">
                ${message.getMessage() != null ? message.getMessage() : "An error occurred."}
            </div>
        @endif

        <form action="/register" method="POST">
            ${csrfHiddenInput}
            <input id="username" name="username" type="text" required
                   placeholder="Full Name"
                   class="${message != null && message.isError() ? "input-error" : ""}" />

            <input id="email" name="email" type="email" required
                   placeholder="Email"
                   class="${message != null && message.isError() ? "input-error" : ""}" />

            <input id="password" name="password" type="password" required
                   placeholder="Password"
                   class="${message != null && message.isError() ? "input-error" : ""}" />

            <button type="submit" class="btn btn-primary">Register</button>
        </form>

        <p class="mt-2">
            Already have an account?
            <a href="/login" class="link">Sign in here</a>
        </p>
    </div>
`)
