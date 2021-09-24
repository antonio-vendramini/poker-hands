package com.exasol.challenge.pokerHands;

public enum Suit {

    CLUBS('C'),
    DIAMONDS('D'),
    HEARTS('H'),
    SPADES('S');

    private char value;

    Suit(char value){
        this.value = value;
    }

}
