@import java.util.Map

@param String type
@param String id
@param String name
@param String value = ""
@param String placeholder = ""
@param Map<String, String> fieldErrors

<div>
    <input
        type="${type}"
        id="${id}"
        name="${name}"
        value="${value}"
        placeholder = "${placeholder}"
        required
        class="${fieldErrors.containsKey(name) ? "input-error" : ""}" />

    @if (fieldErrors.containsKey(name))
        <div class="error-message">${fieldErrors.get(name)}</div>
    @endif
</div>
