package com.exasol.challenge.pokerHands.model;

import java.util.HashMap;
import java.util.Map;

public enum Suit {

    CLUBS('C'),
    DIAMONDS('D'),
    HEARTS('H'),
    SPADES('S');

    private char value;

    Suit(char value){
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    private static final Map<Character, Suit> lookup = new HashMap<>();

    static {
        for (Suit suit : Suit.values()) {
            lookup.put(suit.getValue(), suit);
        }
    }

    public static Suit get(char value) {
        return lookup.get(value);
    }

}
