function moveNext(current, nextIndex) {
    if (current.value.length === 1) {
        const inputs = document.querySelectorAll('.otp-boxes input');
        if (inputs[nextIndex]) inputs[nextIndex].focus();
    }
}

function handleNavigation(event, currentIndex) {
    const inputs = document.querySelectorAll('.otp-boxes input');
    if (event.key === "ArrowLeft" && inputs[currentIndex - 1]) {
        inputs[currentIndex - 1].focus();
    } else if (event.key === "ArrowRight" && inputs[currentIndex + 1]) {
        inputs[currentIndex + 1].focus();
    } else if (event.key === "Backspace" && event.target.value === "" && inputs[currentIndex - 1]) {
        inputs[currentIndex - 1].focus();
    }
}

async function verifyOtp() {
    const email = localStorage.getItem('userEmail');
    const mode = localStorage.getItem('authMode') || 'signup';
    const otpInputs = document.querySelectorAll('.otp-boxes input');
    let otp = '';
    otpInputs.forEach(input => otp += input.value.toUpperCase());

    if (!email) {
        alert("Session expired. Please start again.");
        window.location.href = "/signup-form";
        return;
    }

   try {
           const response = await fetch(`/api/auth/verify-otp?email=${encodeURIComponent(email)}&otp=${otp}`, {
               method: 'POST'
           });

           if (response.ok) {
               console.log("Current Mode:", mode);

               if (mode === 'forgot') {
                   window.location.href = "/forgot-pass-form";
               } else {
                   window.location.href = "/user-details";
               }
           } else {
               const errorMsg = await response.text();
               alert(errorMsg || 'Invalid OTP. Please try again.');
           }
       } catch (error) {
           console.error("Error:", error);
           alert("Verification failed. Check console.");
       }
}
const mode = localStorage.getItem('authMode') || 'signup';
function backToEmail() {
    window.location.href = `/signup-form${mode === 'forgot' ? '?mode=forgot' : ''}`;
}