package com.exasol.challenge.pokerHands.model;

public class HandRank {

    public enum HandRankValue {

        HIGH_CARD("Highest card", 0),
        ONE_PAIR("One pair", 1),
        TWO_PAIRS("Two pairs", 2),
        THREE_OF_A_KIND("Three of a kind", 3),
        STRAIGHT("Straight", 4),
        FLUSH("Flush", 5),
        FULL_HOUSE("Full house", 6),
        FOUR_OF_A_KIND("Four of a kind", 7),
        STRAIGHT_FLUSH("Straight flush", 8),
        ROYAL_FLUSH("Royal Flush", 9);

        private String description;
        private int order;

        HandRankValue(String description, int order) {
            this.description = description;
            this.order = order;
        }

        public String getDescription() {
            return description;
        }

        public int getOrder() {
            return order;
        }
    }

    private HandRankValue handRankValue;
    private Card[] handValuableCards;

    public static HandRank getHandRankForRoyalFlush(Card highestCard) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(HandRankValue.ROYAL_FLUSH);
        handRank.setHandValuableCards(new Card[]{highestCard});
        return handRank;
    }

    public static HandRank getHandRankForStraightFlush(Card highestCard) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(HandRankValue.STRAIGHT_FLUSH);
        handRank.setHandValuableCards(new Card[]{highestCard});
        return handRank;
    }

    public static HandRank getHandRankForFourOfAKind(Card valuableCard) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(HandRankValue.FOUR_OF_A_KIND);
        handRank.setHandValuableCards(new Card[]{valuableCard});
        return handRank;
    }

    public static HandRank getHandRankForFullHouse(Card valuableCard) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(HandRankValue.FULL_HOUSE);
        handRank.setHandValuableCards(new Card[]{valuableCard});
        return handRank;
    }

    public static HandRank getHandRankForFlush(Card highestCard) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(HandRankValue.FLUSH);
        handRank.setHandValuableCards(new Card[]{highestCard});
        return handRank;
    }

    public static HandRank getHandRankForStraight(Card highestCard) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(HandRankValue.STRAIGHT);
        handRank.setHandValuableCards(new Card[]{highestCard});
        return handRank;
    }

    public static HandRank getHandRankForThreeOfAKind(Card highestCard) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(HandRankValue.THREE_OF_A_KIND);
        handRank.setHandValuableCards(new Card[]{highestCard});
        return handRank;
    }

    public static HandRank getHandRankForTwoPairs(Card[] handValuableCards, Card highestCard) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(HandRankValue.TWO_PAIRS);
        handRank.setHandValuableCards(new Card[]{handValuableCards[0], handValuableCards[1], highestCard});
        return handRank;
    }

    public static HandRank getHandRankForOnePair(Card handValuableCards, Card highestCard) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(HandRankValue.ONE_PAIR);
        handRank.setHandValuableCards(new Card[]{handValuableCards, highestCard});
        return handRank;
    }

    public static HandRank getHandRankForHighCard(Card highestCard) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(HandRankValue.HIGH_CARD);
        handRank.setHandValuableCards(new Card[]{highestCard});
        return handRank;
    }

    public HandRankValue getHandRankValue() {
        return handRankValue;
    }

    public void setHandRankValue(HandRankValue handRankValue) {
        this.handRankValue = handRankValue;
    }

    public Card[] getHandValuableCards() {
        return handValuableCards;
    }

    public void setHandValuableCards(Card[] handValuableCards) {
        this.handValuableCards = handValuableCards;
    }

    public Card getHighestCard() {
        return getHandValuableCards()[getHandValuableCards().length - 1];
    }

    @Override
    public String toString() {
        String handRank = this.getHandRankValue().getDescription();
        if (HandRankValue.ROYAL_FLUSH == this.getHandRankValue() ||
                HandRankValue.STRAIGHT_FLUSH == this.getHandRankValue() ||
                HandRankValue.FLUSH == this.getHandRankValue()) {
            return handRank + ", " + this.getHighestCard().getSuit();
        }

        if (HandRankValue.FOUR_OF_A_KIND == this.getHandRankValue() ||
                HandRankValue.FULL_HOUSE == this.getHandRankValue() ||
                HandRankValue.STRAIGHT == this.getHandRankValue() ||
                HandRankValue.THREE_OF_A_KIND == this.getHandRankValue() ||
                HandRankValue.HIGH_CARD == this.getHandRankValue()) {
            return handRank + ", " + this.getHighestCard().getCardRank();
        }
        if (HandRankValue.TWO_PAIRS == this.getHandRankValue()) {
            return handRank + ", " + this.getHandValuableCards()[0].getCardRank() +
                    " and " + this.getHandValuableCards()[1].getCardRank();
        }
        if (HandRankValue.ONE_PAIR == this.getHandRankValue()) {
            return handRank + ", " + this.getHandValuableCards()[0].getCardRank();
        }
        return "Unknown Hand Rank!";
    }
}
