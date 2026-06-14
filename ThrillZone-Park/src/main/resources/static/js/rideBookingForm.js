document.addEventListener('DOMContentLoaded', function() {
    let ticketsInput = document.getElementById("tickets");
    let priceInput = document.getElementById("price");
    let total = document.getElementById("total");

    function calculateTotal() {
        let tickets = parseInt(ticketsInput.value) || 0;
        let price = parseFloat(priceInput.value) || 0;

        if (tickets > 0 && price > 0) {
            total.innerText = (tickets * price).toFixed(2);
        } else {
            total.innerText = "0";
        }
    }
    if (ticketsInput) {
        ticketsInput.addEventListener("input", calculateTotal);
    }
    calculateTotal();
});