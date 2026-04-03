async function sendOtp() {
    const email = document.getElementById('email').value;
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
            const response = await fetch(`/api/auth/send-otp?email=${email}`, {
                method: 'POST'
            });


            if (response.ok) {
                localStorage.setItem('userEmail', email);
                window.location.href = "/verify-otp-model";
            }
            else if (response.status === 409) {
                const modal = document.getElementById('customModal');
                modal.style.setProperty('display', 'flex', 'important');
                resetButton(submitBtn, originalText);
            }
            else {
                alert("Server Error: " + response.status);
                resetButton(submitBtn, originalText);
            }
        } catch (error) {
            console.error("Fetch Error:", error);
            alert("Network error. Please try again.");
            resetButton(submitBtn, originalText);
        }
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
        document.getElementById('email').value = "";
}