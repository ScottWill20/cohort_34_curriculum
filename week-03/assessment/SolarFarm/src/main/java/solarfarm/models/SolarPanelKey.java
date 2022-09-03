package solarfarm.models;

public class SolarPanelKey { // might make it easier to validate in domain that keys are the same
    private final String section;
    private final int row;
    private final int column;


    public SolarPanelKey(String section, int row, int column) {
        this.section = section;
        this.row = row;
        this.column = column;
    }


    public String getSection() {
        return section;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    // TODO OPTIONAL rework hashCode, equals

    /*
    @Override
    public int hashCode(Object obj) {
        return Object.hash(section,row,column);
    }

    @Override
    public boolean equals(Object obj) {
        return Object;
    }

     */

    @Override
    public String toString() {
        return String.format("%s - %s - %s",section,row,column);
    }
}
