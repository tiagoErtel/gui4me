@import java.util.Map

@param String type
@param String id
@param String name
@param String value = ""
@param Map<String, String> fieldErrors

<div>
    <label for="${id}">${name}</label>
    <input
        type="${type}"
        id="${id}"
        name="${name}"
        value="${value}"
        required
        class="${fieldErrors.containsKey(name) ? "input-error" : ""}" />

    @if (fieldErrors.containsKey(name))
        <div class="error-message">${fieldErrors.get(name)}</div>
    @endif
</div>
