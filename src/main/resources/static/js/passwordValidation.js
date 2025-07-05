document.addEventListener("DOMContentLoaded", () => {
    const passwordInput = document.getElementById("newPassword");
    const confirmPasswordInput = document.getElementById("confirmPassword");

    const passwordFeedback = document.getElementById("newPasswordFeedback");
    const confirmPasswordFeedback = document.getElementById("confirmPasswordFeedback");
    const passwordForm = document.getElementById("passwordForm");

    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;

    passwordInput.addEventListener("input", () => {
        const value = passwordInput.value;

        if (!passwordRegex.test(value)) {
            passwordFeedback.textContent = "Password must be at least 8 characters, with uppercase, lowercase, and a number.";
            passwordInput.classList.add("error");
        } else {
            passwordInput.classList.remove("error", "warning");
            passwordFeedback.textContent = "";
        }

        checkPasswordMatch();
    });

    confirmPasswordInput.addEventListener("input", checkPasswordMatch);

    function checkPasswordMatch() {
        const pass = passwordInput.value;
        const confirm = confirmPasswordInput.value;

        confirmPasswordInput.classList.remove("error", "match");

        if (!confirm) {
            confirmPasswordFeedback.textContent = "";
            return;
        }

        if (pass === confirm) {
            confirmPasswordInput.classList.remove("error");
            confirmPasswordFeedback.textContent = "";
        } else {
            confirmPasswordInput.classList.add("error");
            confirmPasswordFeedback.textContent = "Passwords do not match.";
        }
    }

    passwordForm.addEventListener("submit", (e) => {
      const value = passwordInput.value;
      if (!passwordRegex.test(value)) {
        e.preventDefault();
        passwordInput.focus();
      }
    });
});

