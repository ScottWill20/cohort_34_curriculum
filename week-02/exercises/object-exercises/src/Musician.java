public class Musician {
    private String name;
    private int rating;

    public Musician() {
    }


    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public void setName(String newName) {
        this.name = newName;
    }
    public void setRating(int rating) {
        if (rating > 0) ;
        this.rating = rating;
    }


    public Musician(String name, int rating) {
        this.name = name;
        this.rating = rating;

    }

}
