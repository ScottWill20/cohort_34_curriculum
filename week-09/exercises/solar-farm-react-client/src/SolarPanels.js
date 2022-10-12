import { useState } from "react";
import SolarPanel from "./SolarPanel";

const SOLAR_PANEL_DATA = [
    {
        id: 1,
        section: 'The Ridge',
        row: 1,
        column: 1,
        yearInstalled: 2020,
        material: 'POLY_SI',
        tracking: true
    },
    {
        id: 2,
        section: 'The Ridge',
        row: 1,
        column: 2,
        yearInstalled: 2021,
        material: 'CIGS',
        tracking: true
    },
    {
        id: 13,
        section: 'Treeline',
        row: 1,
        column: 1,
        yearInstalled: 1999,
        material: 'MONO_SI',
        tracking: false
    }
];

const SOLAR_PANEL_DEFAULT = {
    section: '',
    row: 0,
    column: 0,
    yearInstalled: 0,
    material: 'POLY_SI',
    tracking: false
}

function SolarPanels () {
    // setting up our states
    const [solarPanel, setSolarPanel] = useState(SOLAR_PANEL_DEFAULT);
    const [editSolarPanelId, setEditSolarPanelId] = useState(0);
    const [solarPanels, setSolarPanels] = useState(SOLAR_PANEL_DATA);
    const [currentView, setCurrentView] = useState('List');

    /* Function under this line*/
    const handleChange = (event) => {
        // make a copy of the object
        const newSolarPanel = {...solarPanel};

        // if this is a checkbox
        if (event.target.type === 'checkbox') {
            // if its checked === true
            newSolarPanel[event.target.name] = event.target.checked;
        } else {
            // false
            newSolarPanel[event.target.name] = event.target.value;
        }
        
        setSolarPanel(newSolarPanel);
    }

    const handleSubmit = (event) => {
        event.preventDefaut();
        if (editSolarPanelId === 0) {
            // assign an id - this is temporary, will change when working with database
            solarPanel.id = Math.floor(Math.random() * 100000);
            // create a copy of the solar panels array
            const newSolarPanels = [...solarPanels];
            // add the new solar panel to it
            newSolarPanels.push(solarPanel);
            // could also use:
            // const newSolarPanels = [...solarPanels, solarPanel]

            //update the solarPanel state variable
            setSolarPanels(newSolarPanels);
        } else {
            // assign an ID
            solarPanel.id = editSolarPanelId;

            // create a copy of the solar panels array
            const newSolarPanels = [...solarPanels];

            // we need to determine the index
            const indexToUpdate = newSolarPanels.findIndex(
                solarPanel => solarPanel.id == editSolarPanelId);

            // we need to update the solar panel at that index
            newSolarPanels[indexToUpdate] = solarPanel;

            // update the state variable
            setSolarPanels(newSolarPanels);
        }
        resetState();
    }

    const resetState = () => {
        setSolarPanel(SOLAR_PANEL_DEFAULT);
        setEditSolarPanelId(0);
        setCurrentView('List');
    }

    return (
        <>
        {(currentView === 'Add' || currentView === 'Edit') && (
            <>
            <h2>{editSolarPanelId > 0 ? 'Update Solar Panel' : 'Add Solar Panel'}</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="section">Section</label>
                    <input id="section" 
                    name="section" 
                    type="text" 
                    clasName="form-control"
                    value={solarPanel.section}
                    onChange={handleChange}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="row">Row</label>
                    <input id="row" 
                    name="row" 
                    type="number" 
                    clasName="form-control"
                    value={solarPanel.row}
                    onChange={handleChange}
                    />  
                </div>
                <div className="form-group">
                    <label htmlFor="column">Column</label>
                    <input id="column" 
                    name="column" 
                    type="number" 
                    clasName="form-control"
                    value={solarPanel.column}
                    onChange={handleChange}
                    />  
                </div>
                <div className="form-group">
                    <label htmlFor="yearInstalled">Year Installed</label>
                    <input id="yearInstalled" 
                    name="yearInstalled" 
                    type="number" 
                    clasName="form-control"
                    value={solarPanel.yearInstalled}
                    onChange={handleChange}
                    />  
                </div>
                <div className="form-group">
                    <label htmlFor="material">Material</label>
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
                    <label htmlFor="tracking">Is Tracking?</label>
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
        </>
    );
}

export default SolarPanels;