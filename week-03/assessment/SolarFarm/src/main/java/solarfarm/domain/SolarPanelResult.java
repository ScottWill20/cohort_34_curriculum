package solarfarm.domain;

import solarfarm.models.SolarPanel;

import java.sql.Array;
import java.util.ArrayList;

public class SolarPanelResult {

    private final ArrayList<String> messages = new ArrayList<>();
    private SolarPanel solarPanel;

    public ArrayList<String> getMessages() {
        return messages;
    }

    public SolarPanel getSolarPanel() {
        return solarPanel;
    }

    public void setSolarPanel(SolarPanel solarPanel) {
        this.solarPanel = solarPanel;
    }

    // HELPER

    public void addMessage(String message) {
        messages.add(message);
    }

    public boolean isSuccess() {
        return messages.size() == 0;
    }
}
