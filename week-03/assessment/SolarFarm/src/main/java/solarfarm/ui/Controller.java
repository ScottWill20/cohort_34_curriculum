package solarfarm.ui;

import solarfarm.data.DataAccessException;
import solarfarm.domain.SolarPanelResult;
import solarfarm.domain.SolarPanelService;
import solarfarm.models.SolarPanel;

import java.util.List;

public class Controller {
    private final View view;
    private final SolarPanelService service;

    public Controller(View view, SolarPanelService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        view.displayHeader("Welcome to Solar Farm!");
        try {
            runMenu();
        } catch (DataAccessException ex) {
            view.displayText("Something went wrong.");
            view.displayText(ex.getMessage());
        }
        view.displayText("Goodbye!");
    }

    private void runMenu() throws DataAccessException {
        boolean exit = false;
        while (!exit) {
            int selection = view.getMenuOption();
            switch (selection) {
                case 1:
                    panelsBySection();
                    break;
                case 2:
                    addSolarPanel();
                    break;
                case 3:
                    updateSolarPanel();
                    break;
                case 4:
                    deleteSolarPanel();
                    break;
                case 5:
                    exit = true;
                    break;
            }
        }
    }

    private void panelsBySection() {
        List<SolarPanel> solarPanels = service.findBySection();
    }

    private void addSolarPanel() throws DataAccessException {
        SolarPanel solarPanel = view.makeSolarPanel();

        SolarPanelResult result = service.create(solarPanel);
        if (result.isSuccess()) {
            view.displayText("Your panel was created successfully!");
        }
    }


}
