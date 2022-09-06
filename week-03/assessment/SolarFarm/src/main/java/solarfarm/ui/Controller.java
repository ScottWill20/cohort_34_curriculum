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
                    viewBySection();
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

    // READ

    private void viewBySection() throws DataAccessException {
        String section = view.readSection();
        List<SolarPanel> solarPanels = service.findBySection(section);
        if (solarPanels.size() == 0) {
            System.out.printf("There are no panels in %s.%n", section);
        } else {
            view.printSolarPanels(section, solarPanels);
        }
    }

    // CREATE
    private void addSolarPanel() throws DataAccessException {
        SolarPanel solarPanel = view.makeSolarPanel();

        SolarPanelResult result = service.create(solarPanel);
        if (result.isSuccess()) {
            view.displayText("Your panel was created successfully!");
        } else {
            view.displayErrors(result.getMessages());
        }
    }

    private void updateSolarPanel() throws DataAccessException {
        view.displayHeader("Update a Panel");
        String section = view.readSection();
        int row = view.readInt("Row: ",1,250);
        int column = view.readInt("Column: ",1,250);
        SolarPanel solarPanel = service.findByKey(section,row,column);

        view.updateSolarPanel(solarPanel);

        SolarPanelResult result = service.update(solarPanel);
        if (result.isSuccess()) {
            System.out.println("");
            view.displayText("Success!");
            System.out.printf("Panel %s-%s-%s has been updated.",section,row,column);
            System.out.println("");
        } else {
            view.displayErrors(result.getMessages());
        }

    }

    private void deleteSolarPanel() throws DataAccessException {
        view.displayHeader("Delete a Panel");
        String section = view.readSection();
        int row = view.readInt("Row: ",1,250);
        int column = view.readInt("Column: ",1,250);
        SolarPanel solarPanel = service.findByKey(section,row,column);

        SolarPanelResult result = service.deleteById(solarPanel.getId());
        if (result.isSuccess()) {
            System.out.println("");
            view.displayText("Success!");
            System.out.printf("Panel %s-%s-%s has been deleted.",section,row,column);
            System.out.println("");
        } else {
            view.displayErrors(result.getMessages());
        }


    }


}
