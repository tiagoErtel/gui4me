@import java.util.Map

@param String type
@param String id
@param String name
@param String value = ""
@param String placeholder = ""
@param Map<String, String> fieldErrors = java.util.Collections.emptyMap()


<div class="input-container">
    <input
        type="${type}"
        id="${id}"
        name="${name}"
        value="${value}"
        placeholder = "${placeholder}"
        required
        class="${fieldErrors.containsKey(name) ? "error" : ""}"
    />

        <div class="input-feedback" id="${id}Feedback">${fieldErrors.containsKey(name) ? fieldErrors.get(name) : ""}</div>
</div>
