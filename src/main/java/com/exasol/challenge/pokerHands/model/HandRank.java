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
    private String handValuableCardsString;

    public static HandRank getHandRank(HandRankValue handRankValue, Card... handValuableCards) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(handRankValue);
        handRank.setHandValuableCards(handValuableCards);
        handRank.setHandValuableCardsString(calculateHandValuableCardsString(handValuableCards));
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

    public String getHandValuableCardsString() {
        return handValuableCardsString;
    }

    public void setHandValuableCardsString(String handValuableCardsString) {
        this.handValuableCardsString = handValuableCardsString;
    }

    public Card getHighestCard() {
        return getHandValuableCards()[0];
    }

    private static String calculateHandValuableCardsString(Card... handValuableCards) {
        StringBuilder handValuableCardsString = new StringBuilder();
        for (Card card : handValuableCards) {
            handValuableCardsString.append(card.getCardRank().getValue());
        }

        return handValuableCardsString.toString();
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
