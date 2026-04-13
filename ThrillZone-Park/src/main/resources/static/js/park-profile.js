document.addEventListener("DOMContentLoaded", function () {

    const slides = document.getElementById("slideTrack");
    if (!slides) return;

    let scrollAmount = 0;
    let speed = 100; // 👈 pixels per second (increase for speed)

    let isPaused = false;
    let lastTime = 0;

    function autoSlide(currentTime) {

        if (!lastTime) lastTime = currentTime;
        let deltaTime = (currentTime - lastTime) / 1000; // seconds
        lastTime = currentTime;

        if (!isPaused) {
            scrollAmount += speed * deltaTime;

            if (scrollAmount >= slides.scrollWidth / 2) {
                scrollAmount -= slides.scrollWidth / 2;
            }

            slides.style.transform = `translateX(-${scrollAmount}px)`;
        }

        requestAnimationFrame(autoSlide);
    }

    requestAnimationFrame(autoSlide);

    slides.addEventListener("mouseenter", () => {
        isPaused = true;
    });

    slides.addEventListener("mouseleave", () => {
        isPaused = false;
    });

});

/*history*/

const data = [
    {
        year: "2010",
        text: "Park started as a small green garden.\nIt was a peaceful place surrounded by trees and nature.\nFamilies visited for relaxation and fresh air.\nChildren enjoyed open spaces and simple activities.\nIt became a local favorite spot for evening walks.\nThe foundation of a bigger vision began here.",
        img: "/images/ParkProfile/history/parkview.jpg"
    },
    {
        year: "2015",
        text: "Added rides and kids play zone.\nNew attractions brought excitement for children.\nColorful play areas and swings were introduced.\nFamilies started spending more quality time together.\nThe park slowly transformed into a fun destination.\nVisitor count increased significantly during this phase.",
        img: "/images/ParkProfile/history/park.jpg"
    },
    {
        year: "2020",
        text: "Renovated with modern facilities.\nAdvanced rides and safety measures were added.\nClean pathways and seating areas were redesigned.\nFood stalls and refreshment zones were introduced.\nThe park became more attractive and comfortable.\nIt started gaining popularity beyond the local area.",
        img: "/images/ParkProfile/history/parkview.jpg"
    },
    {
        year: "2025",
        text: "Now a complete entertainment destination.\nOffers fun, adventure, and relaxation for all ages.\nIncludes events, shows, and seasonal activities.\nModern infrastructure enhances visitor experience.\nPerfect place for families, friends, and tourists.\nA dream project that continues to grow every year.",
        img: "/images/ParkProfile/history/park.jpg"
    }
];

let index = 0;

function updateSlide() {
    document.getElementById("historyYear").innerText = data[index].year;
    document.getElementById("historyText").innerText = data[index].text;
    document.getElementById("historyImg").src = data[index].img;
}

function nextSlide() {
    index = (index + 1) % data.length;
    updateSlide();
}

function prevSlide() {
    index = (index - 1 + data.length) % data.length;
    updateSlide();
}

