document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    const email = document.getElementById('email');
    const password = document.getElementById('password');
    const loginBtn = document.getElementById('loginBtn');

    const emailError = document.getElementById('emailError');
    const passError = document.getElementById('passError');
    const eyeIcon = document.querySelector('.toggle-password'); // Eye span element

    const isEmailValid = () => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email.value);
    const isPassValid = () => password.value.trim() !== "";

    function toggleError(input, errorSpan, isValid, message) {
        if (!isValid) {
            errorSpan.innerText = message;
            input.classList.add('input-error');
        } else {
            errorSpan.innerText = "";
            input.classList.remove('input-error');
        }
    }

    function updateButtonState() {
        loginBtn.disabled = !(isEmailValid() && isPassValid());
    }

    password.addEventListener('focus', () => {
        toggleError(password, passError, isPassValid(), "Password cannot be empty");
        if (email.value.trim() === "") {
            emailError.innerText = "";
            email.classList.remove('input-error');
        }
    });

    email.addEventListener('focus', () => {
        passError.innerText = "";
        password.classList.remove('input-error');
    });

    email.addEventListener('input', () => {
        toggleError(email, emailError, isEmailValid(), "Please enter a valid email address.");
        updateButtonState();
    });

    password.addEventListener('input', () => {
        toggleError(password, passError, isPassValid(), "Password cannot be empty");
        updateButtonState();
    });

    if (eyeIcon) {
        eyeIcon.addEventListener('click', function() {
            const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
            password.setAttribute('type', type);
            this.innerText = type === 'password' ? '👁️' : '🙈';
        });
    }

    loginForm.addEventListener('submit', async function(event) {
        event.preventDefault();

        const formData = new FormData(loginForm);
        const params = new URLSearchParams(formData);

        emailError.innerText = "";
        passError.innerText = "";
        email.classList.remove('input-error');
        password.classList.remove('input-error');

        loginBtn.innerText = "Logging...";
        loginBtn.disabled = true;
        loginBtn.style.opacity = "0.7";
        loginBtn.style.cursor = "not-allowed";

        try {
            const response = await fetch('/login-submit', {
                method: 'POST',
                body: params,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
            });

            if (response.ok) {
                const data = await response.json();
                window.location.href = data.redirectUrl;
            }
            else if (response.status === 401) {
                toggleError(email, emailError, false, "Invalid email or password.");
                toggleError(password, passError, false, "");
                password.value = "";
            }
            else {
                alert("Server error. Status: " + response.status);
            }

        } catch (error) {
            console.error("Fetch Error:", error);
            alert("Network error. Please try again later.");
        } finally {
             if (!window.location.pathname.startsWith("/user/") && !window.location.pathname.startsWith("/admin/")) {
                 loginBtn.innerText = "Login";
                 loginBtn.disabled = false;
                 loginBtn.style.opacity = "1";
                 loginBtn.style.cursor = "pointer";
                 updateButtonState();
             }
        }
    });

    updateButtonState();
});