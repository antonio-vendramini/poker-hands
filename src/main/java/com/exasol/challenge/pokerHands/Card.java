package com.exasol.challenge.pokerHands;

public class Card implements Comparable<Card> {

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

    @Override
    public int compareTo(Card o) {
        return Character.compare(this.getValue(), o.getValue());
    }

    @Override
    public String toString() {
        return "" + value + suit;
    }

}
