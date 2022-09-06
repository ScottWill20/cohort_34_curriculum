package solarfarm.ui;

import solarfarm.models.SolarPanel;
import solarfarm.models.SolarPanelMaterial;

import java.util.List;
import java.util.Scanner;

public class View {

    Scanner console = new Scanner(System.in);

    // READ
    public int getMenuOption() {
        displayHeader("Main Menu");
        displayText("1. Find Panels by Section");
        displayText("2. Add a Solar Panel");
        displayText("3. Update a Solar Panel");
        displayText("4. Delete a Solar Panel");
        displayText("5. Exit the Program");
        return readInt("Select [1-5]:", 1, 5);
    }

    public void displayHeader(String header) {
        System.out.println("");
        System.out.println(header);
        System.out.println("=".repeat(header.length()));
    }

    public void displayText(String line) {
        System.out.println(line);
    }

    public void displayErrors(List<String> errors) {
        displayHeader("Error: ");
        for (String error : errors) {
            displayText(error);
        }
        displayText("");
    }

    public void printSolarPanels(String sectionName, List<SolarPanel> solarPanels) { // display panels in a section with the section name
        displayText("Solar Panels in " + sectionName);
        displayText("Row Col Year Material Tracking");
        for (SolarPanel solarPanel : solarPanels) {
                System.out.printf("%-3s %-3s %-4s %-8s %-8s%n",
                        solarPanel.getRow(),
                        solarPanel.getColumn(),
                        solarPanel.getYearInstalled(),
                        solarPanel.getMaterial().getDisplayText(),
                        solarPanel.isTracking());
            }

        }

//    public void chooseSolarPanel(String sectionName, List<SolarPanel> solarPanels) { // choose a panel from a list of options (useful for update and delete)
//        displayText("Solar Panels in " + sectionName);
//        displayText("Row Col Year Material Tracking");
//        if (solarPanels.size() == 0) {
//            displayText("No Solar Panels found.");
//        } else {
//
//        }
//    }

    // CREATE
    public SolarPanel makeSolarPanel() {
        displayHeader("Create a Solar Panel");
        SolarPanel result = new SolarPanel();
        result.setSection(readRequiredString("Section: "));
        result.setRow(readInt("Row: ", 1, 250));
        result.setColumn(readInt("Column: ", 1, 250));
        result.setYearInstalled(readInt("Year Installed: ", 0, 2022));
        result.setMaterial(readSolarPanelMaterial("Solar Panel Material: "));
        result.setTracking(readBoolean("Tracked [y/n]: "));
        return result;
    }

    // UPDATE

    public void updateSolarPanel(SolarPanel solarPanel) {
        System.out.printf("Editing %s-%s-%s%n",solarPanel.getSection(),solarPanel.getRow(),solarPanel.getColumn());
        System.out.println("");
        String section = readRequiredString("Section ("+ solarPanel.getSection() + "): ");
        if (section.trim().length() > 0) {
            solarPanel.setSection(section);
        }
        int row = readInt("Row (" + solarPanel.getRow() +"): ",1,250);
        if (row >= 1) {
            solarPanel.setRow(row);
        }
        int col = readInt("Column (" + solarPanel.getColumn() +"): ",1,250);
        if (col >= 1) {
            solarPanel.setColumn(col);
        }
        int year = readInt("Year (" + solarPanel.getYearInstalled() +"): ",1,2022);
        if (year > 0) {
            solarPanel.setYearInstalled(year);
        }
        String material = String.valueOf(readSolarPanelMaterial("Row (" + solarPanel.getMaterial() + "): "));
        if (material.trim().length() > 0) {
            solarPanel.setMaterial(SolarPanelMaterial.valueOf(material));
        }
        boolean track = readBoolean("Tracked ("+ solarPanel.isTracking() + ") [true/false]: ");
        solarPanel.setTracking(track);

    }

    // READ METHODS


    public String readSection() { // reads a section name
        String prompt = readRequiredString("Section: ");
        return prompt;
    }


    private String readString(String prompt) {
        displayText(prompt);
        return console.nextLine();

    }

    private String readRequiredString(String prompt) {
        String result = null;
        do {
            result = readString(prompt);
            if (result.length() == 0) {
                System.out.println("Value is required.");
            }
        } while (result.length() == 0);
        return result;

    }

    private int readInt(String prompt) {
        int result = 0;
        boolean isValid = false;
        do {
            String value = readRequiredString(prompt);
            try {
                result = Integer.parseInt(value);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Value must be a number.");
            }
        } while (!isValid);
        return result;
    }

    public int readInt(String prompt, int min, int max) {
        int result = 0;

        do {
            result = readInt(prompt);
            if (result < min || result > max) {
                System.out.printf("Value must be between %s and %s.%n", min, max);
            }
        } while (result < min || result > max);
        return result;
    }

    private SolarPanelMaterial readSolarPanelMaterial(String prompt) {
        displayHeader("Solar Panel Materials");
        for (SolarPanelMaterial material : SolarPanelMaterial.values()) {
            displayText(material.getDisplayText());
        }
        while (true) {
            String selection = readString(prompt);
            selection = selection.toUpperCase().trim();
            try {
                return SolarPanelMaterial.valueOf(selection);
            } catch (IllegalArgumentException ex) {
                System.out.printf("%s is not a valid panel material.%n", selection);
            }
        }
    }

    private boolean readBoolean(String prompt) {
        String result = readString(prompt);
        if (result.equalsIgnoreCase("y") || result.equalsIgnoreCase("yes")) {
            return true;
        } else if (result.equalsIgnoreCase("n") || result.equalsIgnoreCase("no")) {
            return false;
        } else {
            System.out.println("You must enter yes (y) or no (n)");
            return readBoolean(prompt);
        }
    }

} // closes class
