package solarfarm.models;

import java.util.Objects;

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


    @Override
    public int hashCode() {
        return Objects.hash(section,row,column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolarPanelKey solarPanelKey = (SolarPanelKey) o;
        return Objects.equals(section, solarPanelKey.section) &&
                Objects.equals(row, solarPanelKey.row) &&
                Objects.equals(column, solarPanelKey.column);
    }


    @Override
    public String toString() {
        return String.format("%s-%s-%s",section,row,column);
    }
}
