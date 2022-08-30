package learn.cards;

public enum Suit {

    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES;

    public String getStringSuit() {
        switch (this) {
            case CLUBS:
                return "Clubs";
            case DIAMONDS:
                return "Diamonds";
            case HEARTS:
                return "Hearts";
            case SPADES:
                return "Spades";
        }
        return "This is the worst";
    }
}
