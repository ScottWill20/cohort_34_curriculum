import { useEffect, useState } from "react";
import Sighting from "./Sighting";

function Home() {
  const [sightings, setSightings] = useState([]);

  debugger; // NEW
  useEffect(() => {
    debugger; // NEW
    fetch("http://localhost:8080/sighting")
      .then(response => response.json())
      .then(data => {
        debugger; // NEW
        setSightings(data);
      });
    debugger; // NEW
  }, []); // this will happen only once when the component is loaded
  debugger; // NEW

  return sightings.map((sighting) => (
    <Sighting key={sighting.sightingId} sighting={sighting} />
  ));
}

export default Home;
