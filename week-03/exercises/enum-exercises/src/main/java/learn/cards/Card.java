package learn.cards;

import learn.cards.Rank;
import learn.cards.Suit;
public class Card {

    // 1. Add a Suit and Rank field to the Card class.
    public String suit;
    public String rank;

    // 2. Add a constructor that accepts a Suit and Rank and sets the appropriate fields.

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }


    // 3. Add getters for both suit and rank.

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Card getName(String suit, String rank) {

        }


        // 4. Complete the getName method.
        // Given a card's suit and rank, getName returns a String in the format:
        // "[rank] of [suit]"

        // Examples:
        // Ace of Clubs
        // 5 of Diamonds
        // King of Hearts
        // 9 of Spades

        // Note: it's unlikely you'll be able to use the enum name directly since enum naming conventions
        // don't match the required output.
        return null;
    }
}
