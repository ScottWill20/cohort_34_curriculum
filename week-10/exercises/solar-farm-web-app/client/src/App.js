import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import About from "./components/About";
import Contact from "./components/Contact";
import Navigation from "./components/Navigation";
import SolarPanelList from "./components/SolarPanelList";
import SolarPanelForm from "./components/SolarPanelForm";
import NotFound from "./components/NotFound";

function App() {
  return (
    <Router>
      <h1>Welcome To The Solar Farm</h1>
      <Navigation />
      <Switch>
        <Route path="/" exact>
          <SolarPanelList />
        </Route>
        <Route path="/about">
          <About />
        </Route>
        <Route path="/contact">
          <Contact />
        </Route>
        <Route path={["/solarpanels/add", "/solarpanels/edit/:id"]}>
          <SolarPanelForm />
        </Route>
        <Route path="*">
          <NotFound />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
