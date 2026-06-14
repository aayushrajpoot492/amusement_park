document.addEventListener('DOMContentLoaded', function() {
    let participantsInput = document.getElementById("participants");
    let priceInput = document.getElementById("price");
    let total = document.getElementById("total");

    function calculateTotal() {
        let participants = parseInt(participantsInput.value) || 0;
        let price = parseFloat(priceInput.value) || 0;

        if (participants > 0 && price > 0) {
            total.innerText = (participants * price).toFixed(2);
        } else {
            total.innerText = "0";
        }
    }

    if (participantsInput) {
        participantsInput.addEventListener("input", calculateTotal);
    }
    calculateTotal();
});