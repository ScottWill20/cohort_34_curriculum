import { useState, useEffect } from "react";
import { Link } from "react-router-dom";

function SolarPanelList() {
    const endpoint = "http://localhost:8080/api/solarpanel";
    const [solarPanels, setSolarPanels] = useState([]);
    const [errors, setErrors] = useState([]);

    useEffect(() => {
        getSolarPanels();
    }, []);


    const getSolarPanels = () => {
        fetch(endpoint).then(response => {
            if (response.status === 200) {
                return response.json();
            } else {
                return Promise.reject(`Unexpected status code ${response.status}`);
            }
        }).then(data => {
            setSolarPanels(data);
        }).catch(console.error);
    };

    /**Handle edit and delete below */
    // const handleEditPanel = (solarPanelId) =>{
    //     //update state variable to solar panel we would like to edit 
    //     setEditSolarPanelId(solarPanelId);

    //     // find panel in array of panels 
    //     const solarPanel = solarPanels.find(solarPanel => solarPanel.id === solarPanelId);

    //     // create a copy of the solar panel to edit 
    //     const editSolarPanel = { ...solarPanel };

    //     // update the solar panel state variable with the solar panel object that we need to edit
    //     setSolarPanel(editSolarPanel);

    //     // update our current view to display edit form 
    //     setCurrentView('Edit');
    // };

    const handleDeletePanel = (solarPanelId) =>{
        const solarPanel = solarPanels.find( solarPanel => solarPanel.id === solarPanelId);

        if(window.confirm(`Delete Solar Panel ${solarPanel.section} - ${solarPanel.row} - ${solarPanel.column} ?`))  {
            const init = {
                method: 'DELETE'
            };
        
            fetch(`${endpoint}/${solarPanelId}`, init)
            .then(response => {
                if(response.status === 204){
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
        <h2>Solar Panels</h2>

        <Link className="btn btn-primary" to="/solarpanels/add">Add Solar Panel</Link>
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
                            <Link className="btn btn-primary" 
                            to={`solarpanels/edit/${solarPanel.id}`}>
                                Edit
                            </Link>
                            <button className="btn btn-danger" 
                            onClick={() => handleDeletePanel(solarPanel.id)}>
                                Delete
                            </button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    </>
    );
}

export default SolarPanelList;