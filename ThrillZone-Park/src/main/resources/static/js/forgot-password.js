document.addEventListener('DOMContentLoaded', function() {
    const forgotForm = document.getElementById('forgotPassForm');
    const password = document.getElementById('newPass');
    const confirmPass = document.getElementById('confirmPass');
    const submitBtn = document.getElementById('submitBtn');

    const passError = document.getElementById('passError');
    const confirmError = document.getElementById('confirmError');

    const isPassValid = () => {
        const val = password.value;
        return val.length >= 8 &&
               /[A-Z]/.test(val) &&
               /[a-z]/.test(val) &&
               /[!@#$%^&*()]/.test(val);
    };

    const isConfirmValid = () => {
        return confirmPass.value === password.value && confirmPass.value !== "";
    };

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
        submitBtn.disabled = !(isPassValid() && isConfirmValid());
    }

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

   forgotForm.addEventListener('submit', async function(event) {
       event.preventDefault();

       const email = localStorage.getItem('userEmail');
       const newPass = password.value;

       const params = new URLSearchParams();
       params.append('email', email);
       params.append('newPass', newPass);

       try {
           const response = await fetch('/reset-password', {
               method: 'POST',
               body: params,
               headers: {
                   'Content-Type': 'application/x-www-form-urlencoded'
               }
           });

           if (response.ok) {
               alert("Password updated successfully!");
               window.location.href = "/login-form";
           } else {
               alert("Error: " + response.status);
           }
       } catch (error) {
           alert("Network error.");
       }
   });
});

function togglePass(inputId, iconElement) {
    const input = document.getElementById(inputId);
    if (input.type === "password") {
        input.type = "text";
        iconElement.innerText = "🙈";
    } else {
        input.type = "password";
        iconElement.innerText = "👁️";
    }
}