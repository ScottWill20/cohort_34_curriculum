public class Balloon {
    private String color;
    private double psi;

    public Balloon(String color) {
        this.color = color;
    }

    public double getPsi() {
        return psi;
    }
    public String getColor() {
        return color;
    }

    public void inflate(){
        this.psi = this.psi + Math.random() * 5.0;
    }

    public boolean isExploded() {
        return psi > 16;
    }

}



