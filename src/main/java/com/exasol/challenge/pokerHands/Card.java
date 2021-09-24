package com.exasol.challenge.pokerHands;

public class Card {

    private char value;
    private char suit;

    Card(char[] card){
        this.value = card[0];
        this.suit = card[1];
    }

    public Card() {

    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public char getSuit() {
        return suit;
    }

    public void setSuit(char suit) {
        this.suit = suit;
    }
}
