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
    private CardRank handType;
    private CardRank highestCard;

    public HandRank(HandRankValue handRankValue, CardRank handType, CardRank highestCard) {
        this.handRankValue = handRankValue;
        this.handType = handType;
        this.highestCard = highestCard;
    }

    public HandRank(HandRankValue handRankValue, CardRank handType) {
        this.handRankValue = handRankValue;
        this.handType = handType;
        this.highestCard = null;
    }
}
