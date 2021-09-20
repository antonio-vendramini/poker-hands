package com.exasol.challenge.pokerHands;

public enum CardRank{

    TWO((byte)2),
    THREE((byte)3),
    FOUR((byte)4),
    FIVE((byte)5),
    SIX((byte)6),
    SEVEN((byte)7),
    EIGHT((byte)8),
    NINE((byte)9),
    TEN((byte)10),
    JACK((byte)11),
    QUEEN((byte)12),
    KING((byte)13),
    ACE((byte)14);

    private byte value;

    CardRank(byte value) {
        this.value = value;
    }
}
