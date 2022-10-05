// Initialization

const CAPSULE_COUNT = 100;

function init() {
    const capsuleContainer = document.getElementById("capsules");
    let html = "";
    for (let i = 0; i < CAPSULE_COUNT; i++) {
        html += `<div>
            <span id="capsuleLabel${i + 1}" class="badge badge-pill badge-success">Capsule #${i + 1}</span>
            &nbsp;<span id="guest${i + 1}">Unoccupied</span>
        </div>`
    }
    capsuleContainer.innerHTML = html;
}

init();


// ==============================================

// User Stories

// Book a Guest Form
    // Enter a guest's name in "Guest" input and remember their name ✔
    // Enter a capsule number in "Capsule #" input and remember number ✔
    // On form submit...
        // I want to take the saved name and number and insert them ✔
        // in to the Capsules column in the correct place ✔

// Check Out Form
    // Enter a capsule number in the "Capsule #" input and remember it
    // On form submit...
        // I want to find the correct Capsule in the Capsules column and
        // set that column back to "Unoccupied"

// Messages
    // For checking in...
        // When Book a Guest is successful...
            // Display a success message ✔
        // When Book a Guest is not successful...
            // VALIDATIONS
                // Already checked in ✔
                // Name OR Capsule is blank ✔
                // Already Occupied ✔
                // Capsule does not exist ✔
            // Unsuccessful message, reason why according to validation ✔
    // For checking out...
        // When checkout is successful...
            // Display a success message
        // When checkout is not successful...
            // VALIDATIONS
                // Capsule is blank
                // Already unoccupied
                // Capsule does not exist
            // Unsuccessful message, reason why according to validation

// ==============================================

// Elements
const bookForm = document.getElementById("book-guest-form");
const checkOutForm = document.getElementById("checkout-guest-form")
const bookGuestNameInput = document.getElementById("guest");
const bookCapsuleInput = document.getElementById("bookingCapsule");
const capsulesColumn = document.getElementById("capsules");
const messagesContainer = document.getElementById("messages");

// Global Variables
let currentGuestName = bookGuestNameInput.value;
let currentBookCapsule = bookCapsuleInput.value;

// Helper Function

const handleMessageUpdate = (message) => {
    messagesContainer.innerText = message;
}

// Functions
const handleBookGuestNameChange = (event) => {
    currentGuestName = event.target.value;
}

const handleBookCapsuleChange = (event) => {
    currentBookCapsule = event.target.value;
}

const handleBookSubmit = (event) => {
    event.preventDefault();

    const capsuleRow = document.getElementById("guest" + currentBookCapsule);
    const capsulePill = document.getElementById("capsuleLabel" + currentBookCapsule);

    if (capsuleRow == null) {
        handleMessageUpdate("ERROR: Capsule is not in range.");
    } else {
        if (capsuleRow.innerText == "Unoccupied") {
    
            [...capsulesColumn.children].forEach(capsule => {

                if (currentGuestName == "") {
                    handleMessageUpdate("ERROR: Guest name cannot be blank.");
                } 
                
                else if (capsule.innerText.includes(currentGuestName)) {
                    handleMessageUpdate(`ERROR: ${currentGuestName} is already checked in.`);
                }
                else {
                    capsuleRow.innerText = currentGuestName;
                    capsulePill.style.backgroundColor = "#E54242";
                    handleMessageUpdate(`SUCCESS: ${currentGuestName} is checked in!`);
                }
            })
    
        } else {
            handleMessageUpdate("ERROR: This room is already occupied.");
        }
    }

}

const handleCheckoutSubmit = (event) => {
    event.preventDefault();

    const capsuleRow = document.getElementById("guest" + currentBookCapsule);
    const capsulePill = document.getElementById("capsuleLabel" + currentBookCapsule);

    if (capsuleRow == null) {
        handleMessageUpdate("Error: Capsule is not in range.");
    } else {

        if (capsuleRow.innerText == "Unoccupied") {
            handleMessageUpdate("Error: Capsule is unoccupied.");
        } else {
            capsuleRow.innerText = "Unoccupied";
            capsulePill.style.backgroundColor = "#28a745";
            handleMessageUpdate(`SUCCESS: ${currentGuestName} checked out of capsule #${currentBookCapsule}!`);
        }
    }
    
}


// Events
bookGuestNameInput.addEventListener("change", handleBookGuestNameChange);
bookCapsuleInput.addEventListener("change", handleBookCapsuleChange);
bookForm.addEventListener("submit", handleBookSubmit);
checkOutForm.addEventListener("submit", handleCheckoutSubmit)

// QUESTIONS \\
// why dont we need an EventListener for messagesContainer?

