import { useEffect, useState } from "react";
import { useHistory, Link, useParams } from "react-router-dom";

const SOLAR_PANEL_DEFAULT ={
    section: '',
    row: 0,
    column: 0, 
    yearInstalled: new Date().getFullYear(), 
    material: 'POLY_SI',
    tracking: false
}

function SolarPanelForm() {
    const endpoint = "http://localhost:8080/api/solarpanel";
    // setting up our states
    const [solarPanel, setSolarPanel] = useState(SOLAR_PANEL_DEFAULT);
    const [editSolarPanelId, setEditSolarPanelId] = useState(0);
    const [errors, setErrors] = useState([]);
    const history = useHistory();
    const { id } = useParams();

    useEffect(() => {
        if (id) {
            setEditSolarPanelId(id);
            fetch(`${endpoint}/${id}`)
            .then((response) => response.json())
            .then((data) => setSolarPanel(data));
        }
    }, []);


    const handleChange = (event) => {
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
                resetState();
                history.push("/");
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
                resetState();
                history.push("/");
            }else{
                setErrors(data);
            }
        })
        .catch(error => console.log(error))
    };


    const resetState = () => {
        setSolarPanel(SOLAR_PANEL_DEFAULT);
        setEditSolarPanelId(0);
        setErrors([]);
    };
    
        return (<>
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
                <button className="btn btn-success mr-4" 
                type="submit">
                    {editSolarPanelId > 0 ? 'Update Solar Panel' : 'Add Solar Panel'}
                </button>
                <Link className="btn btn-warning" to="/">Cancel</Link>
            </div>
        </form>
    </>
)
}

export default SolarPanelForm;