const endpoint = 'http://localhost:8080/api/solarpanel';

function init(){
    getSolarPanels().then(data => renderList(data));
}

init();

function getSolarPanels() {
    // will get the solar panels from the API
    return fetch(endpoint) // this returns a promise
    .then(response => {
        return response.json();
    });
}

function renderList(solarPanels) {
    // will render my panels from the API

    const solarPanelsHTML = solarPanels.map(sp =>{
        return `
        <tr>
            <td>${sp.section}</td>
            <td>${sp.row} - ${sp.column}</td>
            <td>${sp.yearInstalled}</td>
            <td>${sp.material}</td>
            <td>${sp.tracking ? 'Yes' : 'No'}</td>
            <td>
                <button>Edit</button>
                <button>Delete</button>
            </td>
        <tr>
         `
    });
    const tableBodyElement = document.getElementById('tableRows');
    tableBodyElement.innerHTML = solarPanelsHTML.join('');
}

function handleSubmit(event) {
    event.preventDefault();

    const section = document.getElementById('section').value;
    const row = document.getElementById('row').value;
    const column = document.getElementById('column').value;
    const yearInstalled = document.getElementById('yearInstalled').value;
    const material = document.getElementById('material').value;
    const tracking = document.getElementById('tracking').checked;

    const solarPanel = {
        section,
        row: row ? parseInt(row) : 0,
        column: column ? parseInt(column) : 0,
        yearInstalled: yearInstalled ? parseInt(yearInstalled) : 0,
        material,
        tracking
    };

    const init = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(solarPanel)
    };

    fetch('http://localhost:8080/api/solarpanel', init)
    .then(response => {

        if (response.status === 201 || response.status === 400) {
            return response.json();
        } else {
            return Promise.reject(`Unexpected status code: ${response.status}`);
        }
    })
    .then(data => {
        if (data.id) {
            // happy path
            console.log(data);
        } else {
            // unhappy path
            console.log(data);

        }
    })
    .catch(error => console.log(error));
}




