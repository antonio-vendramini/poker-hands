package com.exasol.challenge.pokerHands;

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
    private Card highestCard;

    public static HandRank getHandRankForRoyalFlush(Card highestCard) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(HandRankValue.ROYAL_FLUSH);
        handRank.setHighestCard(highestCard);
        return handRank;
    }

    public static HandRank getHandRankForStraightFlush(Card highestCard) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(HandRankValue.STRAIGHT_FLUSH);
        handRank.setHighestCard(highestCard);
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
        handRank.setHandRankValue(HandRankValue.THREE_OF_A_KIND);
        handRank.setHandValuableCards(new Card[]{valuableCard});
        return handRank;
    }

    public static HandRank getHandRankForFlush(Card highestCard) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(HandRankValue.FLUSH);
        handRank.setHighestCard(highestCard);
        return handRank;
    }

    public static HandRank getHandRankForStraight(Card highestCard) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(HandRankValue.STRAIGHT);
        handRank.setHighestCard(highestCard);
        return handRank;
    }

    public static HandRank getHandRankForThreeOfAKind(Card highestCard) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(HandRankValue.THREE_OF_A_KIND);
        handRank.setHighestCard(highestCard);
        return handRank;
    }

    public static HandRank getHandRankForTwoPairs(Card[] handValuableCards, Card highestCard) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(HandRankValue.TWO_PAIRS);
        handRank.setHandValuableCards(handValuableCards);
        handRank.setHighestCard(highestCard);
        return handRank;
    }

    public static HandRank getHandRankForOnePair(Card handValuableCards, Card highestCard) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(HandRankValue.ONE_PAIR);
        handRank.setHandValuableCards(new Card[]{handValuableCards});
        handRank.setHighestCard(highestCard);
        return handRank;
    }

    public static HandRank getHandRankForHighCard(Card highestCard) {
        HandRank handRank = new HandRank();
        handRank.setHandRankValue(HandRankValue.HIGH_CARD);
        handRank.setHighestCard(highestCard);
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
        return highestCard;
    }

    public void setHighestCard(Card highestCard) {
        this.highestCard = highestCard;
    }

}
