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
    const otpInputs = document.querySelectorAll('.otp-boxes input');
    let otp = '';
    otpInputs.forEach(input => otp += input.value.toUpperCase());

    if (!email) {
        alert("Session expired. Please start again.");
        window.location.href = "/signup-form";
        return;
    }

    const response = await fetch(`/api/auth/verify-otp?email=${email}&otp=${otp}`, { method: 'POST' });

    if (response.ok) {
        window.location.href = "/user-details";
    } else {
        alert('Invalid OTP. Please try again.');
    }
}