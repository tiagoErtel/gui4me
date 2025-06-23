@import gui4me.security.CsrfHiddenInput
@import gui4me.utils.Message

@param Message message = null
@param String username = ""
@param String email = null
@param CsrfHiddenInput csrfHiddenInput

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
            <form method="post" action="/settings/username">
                ${csrfHiddenInput}

                <div>
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" value="${username}" required />
                </div>

                <button type="submit" class="btn btn-primary">Update Info</button>
            </form>
        </div>

        <div class="panel">
            <h3 class="subtitle">Change Password</h3>
            <form method="post" action="/settings/password">
                ${csrfHiddenInput}

                <div>
                    <label for="currentPassword">Current Password</label>
                    <input type="password" id="currentPassword" name="currentPassword" required />
                </div>

                <div>
                    <label for="newPassword">New Password</label>
                    <input type="password" id="newPassword" name="newPassword" required />
                </div>

                <div>
                    <label for="confirmPassword">Confirm New Password</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required />
                </div>

                <button type="submit" class="btn btn-primary">Update Password</button>
            </form>
        </div>
    </div>
`)

