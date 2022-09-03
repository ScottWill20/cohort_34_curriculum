package solarfarm.models;

public class SolarPanel {

    private int id;
    private String section;
    private int row;
    private int column;
    private int yearInstalled;
    private SolarPanelMaterial material;
    private boolean isTracking;

    private SolarPanelKey key;

    // constructors

    public SolarPanel(int id,String section, int row, int column, int yearInstalled, SolarPanelMaterial material, boolean isTracking) {
        this.id = id;
        this.section = section;
        this.row = row;
        this.column = column;
        this.yearInstalled = yearInstalled;
        this.material = material;
        this.isTracking = isTracking;
        this.key = new SolarPanelKey(section,row,column);
    }

    public boolean isMatch(SolarPanelKey key) {
        return this.equals(key);
    }

    public SolarPanel() {}

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getYearInstalled() {
        return yearInstalled;
    }

    public void setYearInstalled(int yearInstalled) {
        this.yearInstalled = yearInstalled;
    }

    public SolarPanelMaterial getMaterial() {
        return material;
    }

    public void setMaterial(SolarPanelMaterial material) {
        this.material = material;
    }

    public boolean isTracking() {
        return isTracking;
    }

    public void setTracking(boolean tracking) {
        isTracking = tracking;
    }

    @Override
    public String toString() {
        return String.format(
                "SolarPanel{ section: %s%n row: %s%n column %s%n yearInstalled %s%n material: %s%n isTracking %s%n",
                section,row,column,yearInstalled,material,isTracking);
    }
}
