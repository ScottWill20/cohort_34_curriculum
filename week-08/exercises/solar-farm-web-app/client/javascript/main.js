const endpoint = 'http://localhost:8080/api/solarpanel';
let solarPanels = [];
let editSolarPanelId = 0;

function displayList(){
    setCurrentView('List');
    getSolarPanels()
    .then(data =>{
        solarPanels = data;
        renderList(data)
    });
}


function getSolarPanels(){
    // will get the solar panels from the api 
    return fetch(endpoint) // this returns a promise
    .then(response =>{
        return response.json(); // this returns a promise 
    });
}

function setCurrentView(view){
    const formContainerElement = document.getElementById('formContainer');
    const listContainerElement = document.getElementById('listContainer');

    switch(view){
        case 'List':
            formContainerElement.style.display = 'none';
            listContainerElement.style.display = 'block';
            break;
        case 'Form':
            formContainerElement.style.display = 'block';
            listContainerElement.style.display = 'none';
    }
}

function renderList(solarPanels){
    // will render my panels from the API 
   
    const solarPanelsHTML = solarPanels.map(sp => {
                return `
                <tr>
                    <td>${sp.section}</td>
                    <td>${sp.row} - ${sp.column}</td>
                    <td>${sp.yearInstalled}</td>
                    <td>${sp.material}</td>
                    <td>${sp.tracking ? 'Yes' : 'No'}</td>
                    <td>
                        <button onclick="handleEditPanel(${sp.id})">Edit</button>
                        <button onclick="handleDeletePanel(${sp.id})">Delete</button>
                    </td>
                </tr>
                `;
            }); 
            const tableBodyElement = document.getElementById('tableRows');
            tableBodyElement.innerHTML = solarPanelsHTML.join('');
}

// handling submit
function handleSubmit(event){
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

    if(editSolarPanelId > 0){
        doPut(solarPanel);
    }else{
        doPost(solarPanel);
    }
}
// handleAdd
function handleAddPanel(){
    setCurrentView('Form');
}

// update 
function handleEditPanel(solarPanelId){
    const solarPanel = solarPanels.find(solarPanel => solarPanel.id === solarPanelId);

    document.getElementById('section').value = solarPanel.section;
    document.getElementById('row').value = solarPanel.row;
    document.getElementById('column').value = solarPanel.column;
    document.getElementById('yearInstalled').value = solarPanel.yearInstalled;
    document.getElementById('material').value = solarPanel.material;
    document.getElementById('tracking').checked = solarPanel.tracking;

    document.getElementById('formHeading').innerText = "Update Solar Panel";
    document.getElementById('formSubmitButton').innerText = "Update Solar Panel";

    editSolarPanelId = solarPanelId;
    setCurrentView('Form');
}


//delete
function handleDeletePanel(solarPanelId){
    const solarPanel = solarPanels.find(solarPanel => solarPanel.id === solarPanelId);
    if(confirm(`Delete the panel at location ${solarPanel.section} - ${solarPanel.row} - ${solarPanel.column} ?`)){
        // if this is true 
        const init = {
            method: 'DELETE'
        };

        fetch(`${endpoint}/${solarPanelId}`, init)
        .then(response => {
            if(response.status === 204){
                displayList();
                resetState();
            }else{
                return Promise.reject(`Unexpected status code: ${response.status}`);
            }
        })
        .catch(console.log);
    }
}

// handling errors 
function renderErrors(errors){
    const errorsHTML = errors.map(e => `<li>${e}</li>`);
    const errorsHTMLString = `
        <p>The following errors were found:</p>
        <ul>
            ${errorsHTML.join('')}
        </ul>
    `;

    document.getElementById('errors').innerHTML = errorsHTMLString;
}

// handle posts and puts 

function doPost(solarPanel){
    const init = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(solarPanel)
    };
    fetch('http://localhost:8080/api/solarpanel', init)
    .then(response =>{

        if(response.status === 201 || response.status === 400){
            return response.json();
        }else{
            return Promise.reject(`Unexpected status code: ${reponse.status}`);
        }
    })
    .then(data =>{
        if(data.id){
            // happy path
            displayList();
            resetState();
        }else{
            renderErrors(data);
        }
    })
    .catch(error => console.log(error))
}

function doPut(solarPanel){
    solarPanel.id = editSolarPanelId;

    const init = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(solarPanel)
    };

    fetch(`http://localhost:8080/api/solarpanel/${editSolarPanelId}`, init)
    .then(response => {
        if(response.status === 204){
            return null;
        }else if(response.status === 400){
            return response.json();
        }else{
            return Promise.reject(`Unexpected status code: ${response.status}`);
        }
    })
    .then(data =>{
        if(!data){
            displayList();
            resetState();
        }else{
            renderErrors(data);
        }
    })
    .catch(console.log);
}

function resetState(){
    document.getElementById('form').reset();
    document.getElementById('formSubmitButton').innerText = 'Add Solar Panel';
    document.getElementById('errors').innerHTML = '';
    editSolarPanelId = 0;
    setCurrentView('List');
}

displayList();




