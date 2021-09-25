package com.exasol.challenge.pokerHands;

public class HandRank {

    public enum HandRankValue {

        HIGH_CARD(0),
        ONE_PAIR(1000),
        TWO_PAIRS(2000),
        THREE_OF_A_KIND(3000),
        STRAIGHT(4000),
        FLUSH(5000),
        FULL_HOUSE(6000),
        FOUR_OF_A_KIND(7000),
        STRAIGHT_FLUSH(8000),
        ROYAL_FLUSH(9000);

        private int value;

        HandRankValue(int value) {
            this.value = value;
        }
    }

    private HandRankValue handRankValue;
    private char[] handType;
    private char highestCard;

    public HandRank(HandRankValue handRankValue, char[] handType, char highestCard) {
        this.handRankValue = handRankValue;
        this.handType = handType;
        this.highestCard = highestCard;
    }

    public HandRank(HandRankValue handRankValue, char handType, char highestCard) {
        this.handRankValue = handRankValue;
        this.handType = new char[] { handType };
        this.highestCard = highestCard;
    }

    public HandRank(HandRankValue handRankValue, char[] handType) {
        this.handRankValue = handRankValue;
        this.handType = handType;
        this.highestCard = 0;
    }

    public HandRank(HandRankValue handRankValue, char handType) {
        this.handRankValue = handRankValue;
        this.handType = new char[] { handType };
        this.highestCard = 0;
    }

    public HandRankValue getHandRankValue() {
        return handRankValue;
    }

    public void setHandRankValue(HandRankValue handRankValue) {
        this.handRankValue = handRankValue;
    }

    public char[] getHandType() {
        return handType;
    }

    public void setHandType(char[] handType) {
        this.handType = handType;
    }

    public char getHighestCard() {
        return highestCard;
    }

    public void setHighestCard(char highestCard) {
        this.highestCard = highestCard;
    }
}
