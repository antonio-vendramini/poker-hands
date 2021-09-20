package com.exasol.challenge.pokerHands;

public enum HandRank {

    HIGH_CARD((byte)0),
    ONE_PAIR((byte)1),
    TWO_PAIRS((byte)2),
    THREE_OF_A_KIND((byte)3),
    STRAIGHT((byte)4),
    FLUSH((byte)5),
    FULL_HOUSE((byte)6),
    FOUR_OF_A_KIND((byte)7),
    STRAIGHT_FLUSH((byte)8),
    ROYAL_FLUSH((byte)9);

    private byte value;

    HandRank(byte value) {
        this.value = value;
    }
}
