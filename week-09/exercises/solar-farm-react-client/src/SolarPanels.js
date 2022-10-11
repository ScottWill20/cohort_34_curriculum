import { useState } from "react";
import SolarPanel from "./SolarPanel";

function SolarPanels () {

    const PANEL_DATA = [
        {id: 1, section: 'The Ridge', row: 1, column: 1, yearInstalled: 2020, material: 'POLY_SI', tracking: true},
        {id: 2, section: 'The Ridge', row: 1, column: 2, yearInstalled: 2019, material: 'MONO_SI', tracking: true},
        {id: 3, section: 'Flats', row: 1, column: 1, yearInstalled: 2017, material: 'A_SI', tracking: true},
        {id: 4, section: 'Flats', row: 2, column: 6, yearInstalled: 2017, material: 'CD_TE', tracking: true},
        {id: 5, section: 'Flats', row: 3, column: 7, yearInstalled: 2000, material: 'CIGS', tracking: false},
    ];

    return (
        <div>
        <h2>Solar Panels</h2>
            <table>
                <tr>
                    <th>Section</th>
                    <th>Row</th>
                    <th>Column</th>
                    <th>Year Installed</th>
                    <th>Material</th>
                    <th>Tracking</th>
                    <th>&nbsp;</th>
                </tr>
                {PANEL_DATA.map((panel, key) => {
                    return (
                        <tr key = {key}>
                            <td>{panel.section}</td>
                            <td>{panel.row}</td>
                            <td>{panel.column}</td>
                            <td>{panel.yearInstalled}</td>
                            <td>{panel.material}</td>
                            <td>{panel.tracking ? 'Yes' : 'No'}</td>
                        </tr>
                        )
                    })}
                <tbody id='tableRows'></tbody>
            </table>
        </div>
    );
}

export default SolarPanels;