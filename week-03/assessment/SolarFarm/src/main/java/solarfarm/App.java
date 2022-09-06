package solarfarm;

import solarfarm.data.SolarPanelFileRepository;
import solarfarm.domain.SolarPanelService;
import solarfarm.ui.Controller;
import solarfarm.ui.View;

public class App {
    public static void main(String[] args) {
        SolarPanelFileRepository repository = new SolarPanelFileRepository("./data/solar-panels.csv");
        View view = new View();
        SolarPanelService service = new SolarPanelService(repository);
        Controller controller = new Controller(view,service);

        controller.run();

    }
}
