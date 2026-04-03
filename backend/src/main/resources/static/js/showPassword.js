function togglePassword(id) {
    const input = document.getElementById(id);
    const icon = document.getElementById(id + '-password-icon');

    if (input.type === "password") {
        input.type = "text";
        icon.className = "fa fa-eye";
    } else {
        input.type = "password";
        icon.className = "fa fa-eye-slash";
    }
}
