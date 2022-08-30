package learn.poker;

import learn.cards.Card;

public class TwoCardHand implements Comparable<TwoCardHand> {

    private final Card one;
    private final Card two;

    public TwoCardHand(Card one, Card two) {
        this.one = one;
        this.two = two;
    }

    public Card getOne() {
        return one;
    }

    public Card getTwo() {
        return two;
    }

    // Helper methods

    // Check if something is a pair

    private boolean isPair() {
        if (one.getRank() == two.getRank()) {
            return true;
        }
        return false;
    }

    // card vs card

    private int cardVsCard(Card cardOne, Card cardTwo) {
        if (cardOne.getRank().getPoint() > cardTwo.getRank().getPoint()) {
            return 1;
        } else if (cardOne.getRank().getPoint() < cardTwo.getRank().getPoint()) {
            return -1;
        } else {
            return 0;
        }
    }

    // grabHighestPoint

    private int grabHighestPoint() {
        int onePoint = one.getRank().getPoint();
        int twoPoint = one.getRank().getPoint();
        if (onePoint >= twoPoint) {
            return onePoint;
        } else {
            return twoPoint;
        }
    }

    // grabLowestPoint

    private Card grabLowestCard() {
        int onePoint = one.getRank().getPoint();
        int twoPoint = one.getRank().getPoint();
        if (onePoint <= twoPoint) {
            return one;
        } else {
            return two;
        }
    }



    @Override
    public int compareTo(TwoCardHand o) {
        // 1. Complete the compareTo method.
        // If the current TwoCardHand(`this`) has a lower score than the TwoCardHand parameter, compareTo returns
        // an int less than 0.
        // If `this` has a higher score than the TwoCardHand parameter, compareTo returns an int greater than 0.
        // If `this` and the TwoCardHand parameter have the same score, compareTo returns 0.
        // See Exercise04.md for scoring rules.
        if (this.isPair() && !o.isPair()) {
            return 1;
        } else if (!this.isPair() && o.isPair()) {
            return -1;
        } else if (this.isPair() && o.isPair()) {
            return cardVsCard(one, o.getOne());
        } else {
            if (this.grabHighestPoint() > o.grabHighestPoint()) {
                return 1;
            }
            if (this.grabHighestPoint() < o.grabHighestPoint()) {
                return -1;
            }
            return cardVsCard(this.grabLowestCard(), o.grabLowestCard());
        }
    }
}
