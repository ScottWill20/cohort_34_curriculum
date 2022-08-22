public class NationalForest {
    private String name;
    private String location;
    private int acres;

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getAcres() {
        return acres;
    }

    public void setAcres(int acres) {
        if (acres > 0) {
            this.acres = acres;
        }
    }

    public NationalForest(String name, String location, int acres) {
        this.name = name;
        this.location = location;
        this.acres = acres;
    }

    public int getSquareKilometers() {
        return (int) (this.acres / 247.1);
    }
    public String toLine() {
        return String.format("name: %s, location: %s, acres: %s, km^2: %s", name, location, acres, getSquareKilometers());
    }

    // If no constructor is defined, the compiler adds one.
    // NEW CODE
    public NationalForest() {
        this("Unknown", "Unknown", -1);
    }

    // NEW CODE
    public NationalForest(String name) {
        this(name, "Unknown", -1);
    }

    // NEW CODE
    public NationalForest(String name, int acres) {
        this(name, "Unknown", acres);
    }
}