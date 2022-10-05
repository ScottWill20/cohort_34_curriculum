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


// *********************************

// User Stories
        // enter a guests name in Guest input and remember their name 
        // enter a capsule number in "Capsule #" input and remember number
        // On form submit...
            // I want to take the save name and number and insert them
            // in to the Capsules column in the correct place

// Check Out Form
    // enter a capsule # in the "Capsule #" input and remember it
    // On form submit...
        // find correct capsule in the capsule's column and
        // set that column back to "Unoccupied"

// Messages - Check In
    // When book a guest is successful...
        // display a success message
    // when book a guest is unsuccessful...
        // Validations
            // already checked in
            // empty name/capsule
            // capsule already booked
        // Unsuccessful message, according to our validations

// Messages - Check Out
    // when checkout is successful...
        // display a success message
    // when checkout is unsuccessful
        // Validations
            // already unoccupied
            // empty name/capsule
            // incorrect name/capsule combo
            // no guest with that name

// *********************************

// Elements
const bookForm = document.getElementById("book-guest-form")
const bookGuestNameInput = document.getElementById("guest")
const bookCapsuleInput = document.getElementById("bookingCapsule")
const capsulesColumn = document.getElementById("capsules")
const messagesContainer = document.getElementById("messages")

// Global Variables
let currentGuestName = bookGuestNameInput.value;
let currentBookCapsule = bookCapsuleInput.value;


// Helper Functions

const handleMessageUpdate = (message) => {
    messagesContainer.innerText = message;
}

// Functions

const handlebookGuestNameChange = (event) => {
    currentGuestName = event.target.value;
}

const handleBookCapsuleChange = (event) => {
    currentBookCapsule = event.target.value;
}

const handleBookSubmit = (event) => {
    event.preventDefault();
    // need to find row with span#guest+currentBookCapsule

    const capsuleRow = document.getElementById("guest" + currentBookCapsule);
    const capsulePill = document.getElementById("capsuleLabel" + currentBookCapsule);

    if (capsuleRow == null) {
        handleMessageUpdate("ERROR: capsule does not exist.")
    } else {
        if (capsuleRow.innerText == "Unoccupied") {
            if (currentGuestName == "") {
                handleMessageUpdate("ERROR: Guest name cannot be blank.")
            } 
    
            [...capsulesColumn.children].forEach(capsule => {
                if (capsule.innerText.includes(currentGuestName)) {
                    handleMessageUpdate("ERROR: Guest is already checked in.")
                } 
                else {
                    capsuleRow.innerText = currentGuestName;
                    capsulePill.style.backgroundColor = "#843edc";
                    handleMessageUpdate(`SUCCESS: ${currentGuestName} is checked in!`)
                }
    
            })
        } else {
            handleMessageUpdate("ERROR: This room is already occupied!")
        }
    }
}

// Events

bookForm.addEventListener("submit", handleBookSubmit);
bookGuestNameInput.addEventListener("change", handlebookGuestNameChange);
bookCapsuleInput.addEventListener("change", handleBookCapsuleChange);