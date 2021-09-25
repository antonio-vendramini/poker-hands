package com.exasol.challenge.pokerHands;

public class HandRank {

    public enum HandRankValue {

        HIGH_CARD("Highest card"),
        ONE_PAIR("One pair"),
        TWO_PAIRS("Two pairs"),
        THREE_OF_A_KIND("Three of a kind"),
        STRAIGHT("Straight"),
        FLUSH("Flush"),
        FULL_HOUSE("Full house"),
        FOUR_OF_A_KIND("Four of a kind"),
        STRAIGHT_FLUSH("Straight flush"),
        ROYAL_FLUSH("Royal Flush");

        private String description;

        HandRankValue(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
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
