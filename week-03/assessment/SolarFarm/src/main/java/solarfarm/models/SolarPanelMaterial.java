package solarfarm.models;

public enum SolarPanelMaterial {

    MULTICRYSTALLINE_SILICON("MultiSi"),
    MONOCRYSTALLINE_SILICON("MonoSi"),
    AMORPHOUS_SILICON("AmSi"),
    CADMIUM_TELLURIDE("CdTe"),
    COPPER_INDIUM_GALLIUM_SELENIDE("CIGS");


    private String displayText;
    SolarPanelMaterial(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}

