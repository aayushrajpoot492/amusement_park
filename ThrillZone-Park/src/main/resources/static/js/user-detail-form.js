document.addEventListener('DOMContentLoaded', function() {
    const detailForm = document.getElementById('detailForm');
    const uname = document.getElementById('uname');
    const phoneNo = document.getElementById('phoneNo');
    const password = document.getElementById('password');
    const confirmPass = document.getElementById('confirmPass');
    const submitBtn = document.getElementById('submitBtn');

    const nameError = document.getElementById('nameError');
    const phoneError = document.getElementById('phoneError');
    const passError = document.getElementById('passError');
    const confirmError = document.getElementById('confirmError');

    const isNameValid = () => uname.value.trim() !== "";
    const isPhoneValid = () => phoneNo.value.length >= 10;
    const isPassValid = () => {
        const val = password.value;
        return val.length >= 8 && /[A-Z]/.test(val) && /[a-z]/.test(val) && /[!@#$%^&*()]/.test(val);
    };
    const isConfirmValid = () => confirmPass.value === password.value && confirmPass.value !== "";

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
        submitBtn.disabled = !(isNameValid() && isPhoneValid() && isPassValid() && isConfirmValid());
    }


    uname.addEventListener('input', () => {
        toggleError(uname, nameError, isNameValid(), "Name cannot be empty.");
        updateButtonState();
    });

    phoneNo.addEventListener('input', () => {
        toggleError(phoneNo, phoneError, isPhoneValid(), "Phone number must be at least 10 digits.");
        updateButtonState();
    });

    password.addEventListener('input', () => {
        toggleError(password, passError, isPassValid(), "Min 8 chars, 1 Upper, 1 Lower & 1 Special.");
        if (confirmPass.value !== "") {
            toggleError(confirmPass, confirmError, isConfirmValid(), "Passwords do not match!");
        }
        updateButtonState();
    });

    confirmPass.addEventListener('input', () => {
        toggleError(confirmPass, confirmError, isConfirmValid(), "Passwords do not match!");
        updateButtonState();
    });

    detailForm.addEventListener('submit', async function(event) {
            event.preventDefault();

            const formData = new FormData(detailForm);
            const params = new URLSearchParams(formData);

            try {
                const response = await fetch('/user/save-user-details', {
                    method: 'POST',
                    body: params,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                });

                console.log("Response Status:", response.status);

                if (response.ok) {
                    window.location.href = "/login-form";
                }
                else if (response.status === 409) {
                    toggleError(phoneNo, phoneError, false, "This phone number is already registered!");
                    phoneNo.focus();
                }
                else if (response.status === 401) {
                    alert("Session expired. Please sign up again.");
                    window.location.href = "/signup-form";
                }
                else {
                    alert("Something went wrong! Error: " + response.status);
                }

            } catch (error) {
                console.error("Fetch Error:", error);
                alert("Network error. Please try again later.");
            }
        });

    updateButtonState();
});
function togglePass(inputId, iconElement) {
    const passwordInput = document.getElementById(inputId);

    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        iconElement.innerText = "🙈";
    } else {
        passwordInput.type = "password";
        iconElement.innerText = "👁️";
    }
}
