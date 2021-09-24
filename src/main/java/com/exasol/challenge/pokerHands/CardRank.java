package com.exasol.challenge.pokerHands;

import java.util.HashMap;
import java.util.Map;

public enum CardRank{

    TWO((byte)2, '2'),
    THREE((byte)3, '3'),
    FOUR((byte)4, '4'),
    FIVE((byte)5, '5'),
    SIX((byte)6, '6'),
    SEVEN((byte)7, '7'),
    EIGHT((byte)8, '8'),
    NINE((byte)9, '9'),
    TEN((byte)10, 'T'),
    JACK((byte)11, 'J'),
    QUEEN((byte)12, 'Q'),
    KING((byte)13, 'K'),
    ACE((byte)14, 'A');

    private byte order;
    private char value;

    CardRank(byte order, char value) {
        this.order = order;
        this.value = value;
    }

    public char getValue(){
        return this.value;
    }

    private static final Map<Character, CardRank> lookup = new HashMap<>();

    static {
        for (CardRank cardRank : CardRank.values()) {
            lookup.put(cardRank.getValue(), cardRank);
        }
    }

    private CardRank(char value) {
        this.value = value;
    }

    public static CardRank get(char value) {
        return lookup.get(value);
    }

}
