@import java.util.Map

@param String type
@param String id
@param String name
@param String value = ""
@param String placeholder = ""
@param boolean hidden = false
@param Map<String, String> fieldErrors = java.util.Collections.emptyMap()

<div class="${hidden ? "hidden" : ""}">
    <div class="input-box">
        <input
            type="${type}"
            id="${id}"
            name="${name}"
            value="${value}"
            placeholder="${placeholder}"
            required
            class="${fieldErrors.containsKey(name) ? "error" : ""}"
        />

        @if (type == "password")
            <span
                class="password-icon"
                onclick="togglePassword('${id}')"
            >
                <i id="${id}-password-icon" class="fa fa-eye-slash" aria-hidden="true"></i>
            </span>
        @endif
    </div>

    <div class="input-feedback" id="${id}Feedback">
        ${fieldErrors.containsKey(name) ? fieldErrors.get(name) : ""}
    </div>
</div>
