@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message
@import java.util.Map
@import gui4me.user.User

@param Message message = null
@param User currentUser
@param CsrfHiddenInput csrfHiddenInput
@param Map<String, String> fieldErrors = java.util.Collections.emptyMap()

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
message = message,
currentUser = currentUser,
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

                @template.partials.input(type = "text", id = "newUsername", name = "newUsername", placeholder = "New username", value = currentUser.getUsername(), fieldErrors = fieldErrors)

                <button type="submit" class="btn btn-primary">Update Info</button>
            </form>
        </div>

        <div class="panel">
            <h3 class="subtitle">Change Password</h3>
            <form method="post" action="/user/settings/password" id="passwordForm">
                ${csrfHiddenInput}

                @template.partials.input(type = "password", id = "currentPassword", name = "currentPassword", placeholder = "Current password", fieldErrors = fieldErrors)
                @template.partials.input(type = "password", id = "newPassword", name = "newPassword", placeholder = "New password", fieldErrors = fieldErrors)
                @template.partials.input(type = "password", id = "confirmPassword", name = "confirmPassword", placeholder = "Conirm the password", fieldErrors = fieldErrors)

                <button type="submit" class="btn btn-primary">Update Password</button>
            </form>
        </div>
    </div>
`)

