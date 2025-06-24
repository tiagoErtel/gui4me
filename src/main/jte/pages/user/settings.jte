@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message
@import java.util.Map

@param Message message = null
@param String username = ""
@param String email = null
@param CsrfHiddenInput csrfHiddenInput
@param Map<String, String> fieldErrors = java.util.Collections.emptyMap()


@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
message = message,
username = username,
content = @`
    @template.partials.message(message = message)

    @template.partials.back-button(href = "/dashboard")

    <div class="list-header">
        <h2 class="title">Settings</h2>
    </div>

    <div class="grid">

        <div class="panel">
            <h3 class="subtitle">Account Info</h3>
            <form method="post" action="/user/settings/username">
                ${csrfHiddenInput}

                <div>
                    <label for="newUsername">Username</label>
                    <input type="text" id="newUsername" name="newUsername" value="${username}" required />
                </div>

                <button type="submit" class="btn btn-primary">Update Info</button>
            </form>
        </div>

        <div class="panel">
            <h3 class="subtitle">Change Password</h3>
            <form method="post" action="/user/settings/password">
                ${csrfHiddenInput}

                @template.partials.input(type = "password", id = "currentPassword", name = "currentPassword", fieldErrors = fieldErrors)
                @template.partials.input(type = "password", id = "newPassword", name = "newPassword", fieldErrors = fieldErrors)
                @template.partials.input(type = "password", id = "confirmPassword", name = "confirmPassword", fieldErrors = fieldErrors)

                <button type="submit" class="btn btn-primary">Update Password</button>
            </form>
        </div>
    </div>
`)

