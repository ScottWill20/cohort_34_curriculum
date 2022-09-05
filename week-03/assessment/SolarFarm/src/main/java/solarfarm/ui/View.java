package solarfarm.ui;

import solarfarm.models.SolarPanel;
import solarfarm.models.SolarPanelMaterial;

import java.util.List;
import java.util.Scanner;

public class View {

    Scanner console = new Scanner(System.in);

    public int getMenuOption() {
        displayHeader("Main Menu");
        displayText("1. Find Panels by Section");
        displayText("2. Add a Solar Panel");
        displayText("3. Update a Solar Panel");
        displayText("4. Delete a Solar Panel");
        displayText("5. Exit the Program");
        return readInt("Select [1-5]",1,5);
    }

    // CREATE
    public SolarPanel makeSolarPanel() {
        SolarPanel result = new SolarPanel();
        result.setSection(readString("Section: "));
        result.setRow(readInt("Row: ",1,250));
        result.setColumn(readInt("Column: ",1,250));
        result.setYearInstalled(readInt("Year Installed: ",0,2022));
        result.setMaterial(readSolarPanelMaterial("Solar Panel Material: "));
        result.setTracking(Boolean.parseBoolean(readString("Tracking? ")));
        return result;
    }

    public void displayBySection(List<SolarPanel> solarPanels) {
        displayHeader("Find Panels by Section");

        for (SolarPanel solarPanel : solarPanels) {
            displayText(String.format());
        }
    }

    // Helper Methods
    public void displayHeader(String header) {
        System.out.println("");
        System.out.println(header);
        System.out.println("=".repeat(header.length()));
    }
    public void displayText(String line) {
        System.out.println(line);
    }
    public void displayErrors(List<String> errors) {
        displayHeader("Errors: ");
        for (String error : errors) {
            displayText(error);
        }
        displayText("");
    }

    // READ METHODS

    public int readInt(String prompt, int min, int max) {
        while (true) {
            String value = readString(prompt);
        }
    }

    public String readString(String prompt) {
        displayText(prompt);
        String string = console.nextLine();
        if(string == null || string.isBlank()) {
            System.out.println("You must enter a value.");
            readString(prompt);
        }
        return string;
    }

    public SolarPanelMaterial readSolarPanelMaterial(String prompt) {
        displayHeader("Solar Panel Materials");
        for (SolarPanelMaterial material :SolarPanelMaterial.values()) {
            displayText(material.getDisplayText());
        }
        while (true) {
            String selection = readString(prompt);
            selection = selection.toUpperCase().trim();
            try {
                return SolarPanelMaterial.valueOf(selection);
            } catch (IllegalArgumentException ex) {
                System.out.printf("%s is not a valid panel material.%n",selection);
            }
        }
    }

} // closes class
