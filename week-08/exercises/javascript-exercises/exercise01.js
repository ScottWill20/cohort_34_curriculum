// MANAGER FEATURE REQUESTS
// You have three managers: A, B, and C.
// Occasionally, they ask you to add features to your company software.
// Use if/else statements to enforce the following rules:
// - If all three ask for the feature, print "Feature in progress."
// - If any two of the three ask, print "Adding feature to schedule."
// - If only one of the three ask, print "Going to hold off for a bit."
// - If none of the managers ask, print "Nothing to do..."

const managerAAsked = true;
const managerBAsked = false;
const managerCAsked = false;

// 1. Add decisions statements to cover all scenarios.

if (!managerAAsked && !managerBAsked && !managerCAsked) {
    console.log("Nothing to do...");
} else if (managerAAsked && !managerBAsked && !managerCAsked) { // only A asked
    console.log("Going to hold off for a bit.")
} else if (!managerAAsked && managerBAsked && !managerCAsked) { // only B asked
    console.log("Going to hold off for a bit.")
} else if (!managerAAsked && !managerBAsked && managerCAsked) { // only C asked
    console.log("Going to hold off for a bit.")
} else if (managerAAsked && managerBAsked && !managerCAsked) { // A and B asked
    console.log("Adding feature to schedule.")
} else if (managerAAsked && !managerBAsked && managerCAsked) { // A and C asked
    console.log("Adding feature to schedule.")
} else if (!managerAAsked && managerBAsked && managerCAsked) { // B and C asked
    console.log("Adding feature to schedule.")
} else {
    console.log("Feature in progress.")
}

// 2. Change manager variables to test all scenarios.