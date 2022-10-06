const endpoint = 'http://localhost:8080/api/solarpanel';
// fetch(endpoint) // this returns a promise

// .then(response => {
//     return response.json(); // this returns another promise
// }).then(data => {
//     console.log(data);
//     const tableBodyElement = document.getElementById('tableRows');

//     const solarPanelsHTML = data.map(sp =>{
//         return `
//         <tr>
//         <td>${sp.section}</td>
//         <td>${sp.row} - ${sp.column}</td>
//         <td>${sp.yearInstalled}</td>
//         <td>${sp.material}</td>
//         <td>${sp.tracking}</td>
//         <td>
//             <button>Edit</button>
//             <button>Delete</button>
//         </td>
//     <tr>
//     `
//     });

//     console.log(solarPanelsHTML);

//     tableBodyElement.innerHTML = solarPanelsHTML.join(''); // takes all the elements in an array and combines them as a string

// });


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




