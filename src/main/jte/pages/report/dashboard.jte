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
    <h1>There is nothing to see here at the moment :(</h1>
`)
