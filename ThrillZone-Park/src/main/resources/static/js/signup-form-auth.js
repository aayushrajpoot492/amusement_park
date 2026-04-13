const urlParams = new URLSearchParams(window.location.search);
const mode = urlParams.get('mode') || 'signup';

document.addEventListener("DOMContentLoaded", () => {
    const title = document.querySelector('h2');
    const subtitle = document.getElementById('subtitle');
    const submitBtn = document.querySelector('.btn');

    if (mode === 'forgot') {
        title.innerText = "Reset Password";
        subtitle.innerText = "Enter registered email to receive OTP";
        submitBtn.innerText = "Continue";
    } else {
        title.innerText = "Sign Up";
        subtitle.innerText = "Enter your email to receive OTP";
        submitBtn.innerText = "Send OTP";
    }
});

async function sendOtp() {
    const emailInput = document.getElementById('email');
    const email = emailInput.value.trim();
    const submitBtn = document.querySelector('.btn');
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!email || !emailRegex.test(email)) {
        alert('Please enter a valid email address');
        return;
    }

    const originalText = submitBtn.innerText;
    submitBtn.innerText = "Sending...";
    submitBtn.disabled = true;
    submitBtn.style.opacity = "0.7";
    submitBtn.style.cursor = "not-allowed";

    try {
        const response = await fetch(`/api/auth/send-otp?email=${encodeURIComponent(email)}&mode=${mode}`, {
            method: 'POST'
        });

        if (response.ok) {
            localStorage.setItem('userEmail', email);
            localStorage.setItem('authMode', mode);
            window.location.href = "/verify-otp-model";
        }
        else if (response.status === 409 || response.status === 404) {
            showErrorModal(response.status);
            resetButton(submitBtn, originalText);
        }
        else {
            alert("Something went wrong. Status: " + response.status);
            resetButton(submitBtn, originalText);
        }
    } catch (error) {
        console.error("Fetch Error:", error);
        alert("Network error. Please check your connection.");
        resetButton(submitBtn, originalText);
    }
}

function showErrorModal(status) {
    const modal = document.getElementById('customModal');
    const modalTitle = modal.querySelector('h3');
    const modalText = modal.querySelector('p');
    const modalIcon = modal.querySelector('.modal-icon');

    if (status === 409) {
        modalIcon.innerText = "⚠️";
        modalTitle.innerText = "Account Exists";
        modalText.innerText = "This email is already registered. Please login or use another email.";
    }
    else if (status === 404) {
        modalIcon.innerText = "🔍";
        modalTitle.innerText = "User Not Found";
        modalText.innerText = "We couldn't find an account with this email. Please check or Sign Up.";
    }

    modal.style.setProperty('display', 'flex', 'important');
}

function resetButton(btn, text) {
    btn.innerText = text;
    btn.disabled = false;
    btn.style.opacity = "1";
    btn.style.cursor = "pointer";
}

function closeModal() {
    const modal = document.getElementById('customModal');
    modal.style.setProperty('display', 'none', 'important');
}