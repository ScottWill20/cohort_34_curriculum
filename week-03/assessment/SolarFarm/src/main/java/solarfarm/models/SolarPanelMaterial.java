package solarfarm.models;

public enum SolarPanelMaterial {

    MULTICRYSTALLINE_SILICON("POLYSI"),
    MONOCRYSTALLINE_SILICON("MONOSI"),
    AMORPHOUS_SILICON("AMSI"),
    CADMIUM_TELLURIDE("CDTE"),
    COPPER_INDIUM_GALLIUM_SELENIDE("CIGS");


    private String displayText;
    SolarPanelMaterial(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}

