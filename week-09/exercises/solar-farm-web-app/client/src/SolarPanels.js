import { useState, useEffect } from 'react';

const SOLAR_PANEL_DEFAULT ={
    section: '',
    row: 0,
    column: 0, 
    yearInstalled: new Date().getFullYear(), 
    material: 'POLY_SI',
    tracking: false
}

function SolarPanels() {
    const endpoint = "http://localhost:8080/api/solarpanel";
    // setting up our states
    const [solarPanel, setSolarPanel] = useState(SOLAR_PANEL_DEFAULT);
    const [editSolarPanelId, setEditSolarPanelId] = useState(0);
    const [solarPanels, setSolarPanels] = useState([]);
    const [currentView, setCurrentView] = useState('List');
    const [errors, setErrors] = useState([]);



    useEffect(() => {
        getSolarPanels();
    }, []);


    const getSolarPanels = () => {
        fetch(endpoint).then(res => {
            if (res.status === 200) {
                return res.json();
            } else {
                return Promise.reject(`Unexpected status code ${res.status}`);
            }
        }).then(data => {
            setSolarPanels(data);
        }).catch(console.error);
    }


    /**Function under this line */
    const handleChange = (event) =>{
        // make a copy of the object 
        const newSolarPanel = {...solarPanel};
        // if this is a checkbox
        if(event.target.type === 'checkbox'){
            // if its checked it's true
            newSolarPanel[event.target.name] = event.target.checked;
        }else{
            // else its false
            newSolarPanel[event.target.name] = event.target.value;
        }
        setSolarPanel(newSolarPanel);
    }

    const handleSubmit = (event) =>{
        event.preventDefault();
        if(editSolarPanelId === 0) {
            // ADD A SOLAR PANEL
            addSolarPanel();

        }else{
            // EDIT A SOLAR PANEL
            // assign an id 
            solarPanel.id = editSolarPanelId;
            updateSolarPanel();
        }
    };

    const updateSolarPanel = () => {
        solarPanel.id = editSolarPanelId;
        const init = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(solarPanel)
        };
    
        fetch(`${endpoint}/${editSolarPanelId}`, init)
        .then(res => {
            if(res.status === 204){
                return null;
            }else if(res.status === 400){
                return res.json();
            }else{
                return Promise.reject(`Unexpected status code: ${res.status}`);
            }
        })
        .then(data =>{
            if(!data){
                getSolarPanels();
                resetState();
            }else{
                setErrors(data);
            }
        })
        .catch(console.log);
    };

    const addSolarPanel = () => {
        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(solarPanel)
        };
        fetch(endpoint, init)
        .then(res =>{
    
            if(res.status === 201 || res.status === 400){
                return res.json();
            }else{
                return Promise.reject(`Unexpected status code: ${res.status}`);
            }
        })
        .then(data =>{
            if(data.id){
                getSolarPanels();
                resetState();
            }else{
                setErrors(data);
            }
        })
        .catch(error => console.log(error))
    };


    const resetState = () => {
        setSolarPanel(SOLAR_PANEL_DEFAULT);
        setEditSolarPanelId(0);
        setCurrentView('List');
        setErrors([]);
    };

    /**Handle edit and delete below */
    const handleEditPanel = (solarPanelId) =>{
        //update state variable to solar panel we would like to edit 
        setEditSolarPanelId(solarPanelId);

        // find panel in array of panels 
        const solarPanel = solarPanels.find(solarPanel => solarPanel.id === solarPanelId);

        // create a copy of the solar panel to edit 
        const editSolarPanel = { ...solarPanel };

        // update the solar panel state variable with the solar panel object that we need to edit
        setSolarPanel(editSolarPanel);

        // update our current view to display edit form 
        setCurrentView('Edit');
    };

    const handleDeletePanel = (solarPanelId) =>{
        const solarPanel = solarPanels.find( solarPanel => solarPanel.id === solarPanelId);

        if(window.confirm(`Delete Solar Panel ${solarPanel.section} - ${solarPanel.row} - ${solarPanel.column} ?`))  {
            const init = {
                method: 'DELETE'
            };
        
            fetch(`${endpoint}/${solarPanelId}`, init)
            .then(response => {
                if(response.status === 204){
                    resetState();
                    getSolarPanels();
                }else{
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .catch(console.log);
        }
    };

    return (
       <>
        {(currentView === 'Add' || currentView === 'Edit') && (
            <>
            <h2>{editSolarPanelId > 0 ? 'Update Solar Panel' : 'Add Solar Panel'}</h2>

                {errors.length > 0 && (<div>
                    <h3>The following errors occured:</h3>
                    <ul>
                        {errors.map((error) => {
                            return <li>{error}</li>;
                        })}
                    </ul>
                </div>
                )}

            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="section">Section:</label>
                    <input id="section" 
                    name="section" 
                    type="text" 
                    className="form-control"
                    value={solarPanel.section}
                    onChange={handleChange}   
                    required 
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="row">Row:</label>
                    <input id="row" 
                    name="row" 
                    type="number" 
                    className="form-control"
                    value={solarPanel.row}
                    onChange={handleChange} 
                    required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="row">Column:</label>
                    <input id="column" 
                    name="column" 
                    type="number" 
                    className="form-control"
                    value={solarPanel.column}
                    onChange={handleChange} 
                    required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="yearInstalled">Year Installed:</label>
                    <input id="yearInstalled" 
                    name="yearInstalled" 
                    type="number" 
                    className="form-control"
                    value={solarPanel.yearInstalled}
                    onChange={handleChange} 
                    required
                    min="1954"
                    max= {new Date().getFullYear()}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="material">Material:</label>
                    <select id="material"
                    name="material"
                    className="form-control"
                    value={solarPanel.material}
                    onChange={handleChange}
                    >
                        <option>POLY_SI</option>
                        <option>MONO_SI</option>
                        <option>A_SI</option>
                        <option>CD_TE</option>
                        <option>CIGS</option>
                    </select>
                </div>
                <div className="form-group">
                    <label htmlFor="tracking">Is Tracking?:</label>
                    <input id="tracking"
                    name="tracking"
                    type="checkbox"
                    checked={solarPanel.tracking}
                    onChange={handleChange}
                    />
                </div>
                <div className="mt-4">
                    <button className="btn btn-success" type="submit">{editSolarPanelId > 0 ? 'Update Solar Panel' : 'Add Solar Panel'}</button>
                    <button className="btn btn-warning" type="button" onClick={resetState}>Cancel</button>
                </div>
            </form>
        </>
        )}
        {currentView === 'List' && (
            <>
                <h2>Solar Panels</h2>
                <button className="btn btn-primary" onClick={ () => setCurrentView('Add')}>Add Solar Panel</button>
                <table>
                    <thead>
                        <tr>
                            <th>Section</th>
                            <th>Row-Column</th>
                            <th>Year Installed</th>
                            <th>Material</th>
                            <th>Is Tracking?</th>
                            <th>&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody>
                        {solarPanels.map(solarPanel => (
                            <tr key={solarPanel.id}>
                                <td>{solarPanel.section}</td>
                                <td>{solarPanel.row} - {solarPanel.column}</td>
                                <td>{solarPanel.yearInstalled}</td>
                                <td>{solarPanel.material}</td>
                                <td>{solarPanel.tracking ? 'Yes' : 'No'}</td>
                                <td className="buttonContainer">
                                    <button className="btn btn-primary" onClick={()=> handleEditPanel(solarPanel.id)}>Edit</button>
                                    <button className="btn btn-danger" onClick={() => handleDeletePanel(solarPanel.id)}>Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </>
        )}
        </>
    );
}

export default SolarPanels;