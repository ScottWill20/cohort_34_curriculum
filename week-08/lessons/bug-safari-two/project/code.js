// const data = {
//     "bugType": "Mosquito",
//     "description": "mosquitos are jerks",
//     "date": "2020-07-04",
//     "interest": 0.0
// };

// // First fetch arg
// const url = "http://localhost:8080/sighting";

// // Second fetch arg
// const init = {
//     method: "POST",
//     headers: {
//         "Content-Type": "application/json",
//         "Accept": "application/json"
//     },
//     body: JSON.stringify(data)
// };

// fetch("http://localhost:8080")
//     .then(handleSuccess) // callback executes on success
//     .catch(handleError); // callback executes on failure

// const promise = fetch(url, init);

// fetch("http://localhost:8080/sighting")
//     .then(response => response.text())        // text() returns a promise
//     .then(bodyText => console.log(bodyText)); // handle the body as text

// fetch("http://localhost:8080/sighting")
//     .then(response => response.json())        // json() returns a promise
//     .then(bodyJson => console.log(bodyJson)); // handle the body as json

// fetch("http://localhost:8080/sighting")
//     .then(response => {
//         if(response.status !== 200) {
//             // Stop (reject) the promise chain.
//             return Promise.reject("response is not 200 OK");
//         }
//         return response.json();               // json() returns a promise
//     })      
//     .then(bodyJson => console.log(bodyJson)); // handle the body as json


    
// async function getAll() {

//     const init = {
//         method: "GET",
//         headers: {
//             "Accept": "application/json"
//         }
//     };

//     fetch("http://localhost:8080/sighting", init)
//         .then(response => {
//             if (response.status !== 200) {
//                 console.log(`Bad status: ${response.status}`);
//                 return Promise.reject("response is not 200 OK");
//             }
//             return response.json();
//         })
//         .then(json => console.log(json));
// }

// CAN ALSO BE WRITTEN AS ?

// async function getAll() {

//     const init = {
//         method: "GET",
//         headers: {
//             "Accept": "application/json"
//         }
//     };

//     const response = await fetch("http://localhost:8080/sighting", init);
//     if (response.status !== 200) {
//         console.log(`Bad status: ${response.status}`);
//         return Promise.reject("response is not 200 OK");
//     }
//     const json = await response.json();
//     console.log(json);
// }

async function getAll() {
    const init = {
        method: "GET",
        headers: {
            "Accept": "application/json"
        }
    };

    const response = await fetch("http://localhost:8080/sighting", init);
    if (response.status !== 200) {
        console.log(`Bad status: ${response.status}`);
        return Promise.reject("response is not 200 OK");
    }
    const json = await response.json();

    // Add data to the DOM.
    let html = "";
    for (const sighting of json) {
        html += `<div><strong>${sighting.bugType}</strong> ${sighting.description}</div>`
    }
    document.getElementById("results").innerHTML = html;
}

async function getById() {

    const init = {
        method: "GET",
        headers: {
            "Accept": "application/json"
        }
    };

    // includes a sightingId
    fetch("http://localhost:8080/sighting/2")
        .then(response => {
            if (response.status === 404) {
                console.log("That sighting doesn't exist.");
                return;
            } else if (response.status !== 200) {
                console.log(`Bad status: ${response.status}`);
                return Promise.reject("response is not 200 OK");
            }
            return response.json();
        }).then(console.log);
}

// CAN ALSO BE WRITTEN AS ?

// async function getById() {

//     const init = {
//         method: "GET",
//         headers: {
//             "Accept": "application/json"
//         }
//     };

//     // includes a sightingId
//     const response = await fetch("http://localhost:8080/sighting/12", init);
//     if (response.status === 404) {
//         console.log("That sighting doesn't exist.");
//         return;
//     } else if (response.status !== 200) {
//         console.log(`Bad status: ${response.status}`);
//         return Promise.reject("response is not 200 OK");
//     }
//     const json = await response.json();
//     console.log(json);
// }

async function post() {

    // 1. An object to send with the request.
    const sighting = {
        "bugType": "Mosquito",
        "description": "mosquitos are jerks",
        "date": "2020-07-04",
        "order": "Diptera",
        "interest": 0.0
    };

    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(sighting) // 2.
    };

    fetch("http://localhost:8080/sighting", init)
        .then(response => {
            // Many services return 201 Created for adds/inserts. 
            // If that's the case, only a 201 status indicates success.
            if (response.status !== 201) {
                console.log("Sighting is not valid.");
                return Promise.reject("response is not 200 OK");
            }
            return response.json();
        }).then(json => console.log("New sighting:", json));
}

// CAN ALSO BE WRITTEN AS ?

// async function post() {

//     const sighting = {
//         "bugType": "Mosquito",
//         "description": "mosquitos are jerks",
//         "date": "2020-08-04",
//         "order": "Diptera",
//         "interest": 0.0
//     };

//     const init = {
//         method: "POST",
//         headers: {
//             "Content-Type": "application/json",
//             "Accept": "application/json"
//         },
//         body: JSON.stringify(sighting)
//     };

//     const response = await fetch("http://localhost:8080/sighting", init);
//     if (response.status !== 201) {
//         console.log("Failed to save the sighting.");
//         return Promise.reject("response is not 200 OK");
//     }
//     const json = await response.json();
//     console.log(json);
// }

async function put() {

    const sighting = {
        "sightingId": 4,
        "bugType": "Mosquito",
        "description": "mosquitos are jerks",
        "date": "2020-08-04",
        "order": "Diptera",
        "interest": 0.0
    };

    const init = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(sighting)
    };

    fetch("http://localhost:8080/sighting/4", init)
        .then(response => {
            if (response.status === 404) {
                console.log("Sighting not found.");
            } else if (response.status === 204) {
                console.log("Sighting updated!");
            } else {
                console.log(`Sighting id ${sighting.sightingId} update failed with status ${response.status}.`);
            }
        });
}

// CAN ALSO BE WRITTEN AS ?

// async function put() {

//     const sighting = {
//         "sightingId": 4,
//         "bugType": "Mosquito",
//         "description": "mosquitos are jerks",
//         "date": "2020-08-04",
//         "order": "Diptera",
//         "interest": 0.0
//     };

//     const init = {
//         method: "PUT",
//         headers: {
//             "Content-Type": "application/json",
//             "Accept": "application/json"
//         },
//         body: JSON.stringify(sighting)
//     };

//     const response = await fetch("http://localhost:8080/sighting/4", init);

//     if (response.status === 404) {
//         console.log("Sighting not found.");
//     } else if (response.status === 204) {
//         console.log("Sighting updated!");
//     } else {
//         console.log(`Sighting id ${sighting.sightingId} update failed with status ${response.status}.`);
//     }
// }

// `delete` is a JavaScript keyword
// so we use `doDelete` instead.
async function doDelete() {
    fetch("http://localhost:8080/sighting/4", { method: "DELETE" })
        .then(response => {
            if (response.status === 204) {
                console.log("Delete success.");
            } else if (response.status === 404) {
                console.log("Sighting not found.");
            } else {
                console.log(`Delete failed with status: ${response.status}`);
            }
        })
}

// CAN ALSO BE WRITTEN AS ?

// async function doDelete() {
//     const response = await fetch("http://localhost:8080/sighting/4", { method: "DELETE" });
//     if (response.status === 204) {
//         console.log("Delete success.");
//     } else if (response.status === 404) {
//         console.log("Sighting not found.");
//     } else {
//         console.log(`Delete failed with status: ${response.status}`);
//     }
// }