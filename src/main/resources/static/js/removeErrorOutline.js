const emailInput = document.getElementById("email");
const passwordInput = document.getElementById("password");
const usernameInput = document.getElementById("username");

function removeErrorOutline(event) {
    if (event.target.value) {
        event.target.classList.remove("input-error");
    }
}

emailInput.addEventListener("input", removeErrorOutline);
passwordInput.addEventListener("input", removeErrorOutline);
usernameInput.addEventListener("input", removeErrorOutline);