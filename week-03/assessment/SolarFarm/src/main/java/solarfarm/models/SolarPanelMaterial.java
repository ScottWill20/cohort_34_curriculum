package solarfarm.models;

public enum SolarPanelMaterial {

    POLYSI("PolySi"),
    MONOSI("MonoSi"),
    AMSI("AmSi"),
    CDTE("CdTe"),
    CIGS("CIGS");


    private String displayText;
    SolarPanelMaterial(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}

