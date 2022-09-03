package solarfarm.models;

public enum SolarPanelMaterial {

    MULTICRYSTALLINE_SILICON(""),
    MONOCRYSTALLINE_SILICON(""),
    AMORPHOUS_SILICON(""),
    CADMIUM_TELLURIDE(""),
    COPPER_INDIUM_GALLIUM_SELENIDE("Copper");


    private String displayText;
    SolarPanelMaterial(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}

