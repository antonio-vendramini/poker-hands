package com.exasol.challenge.pokerHands.model;

public class Card implements Comparable<Card> {

    private CardRank cardRank;
    private Suit suit;

    Card(char[] card){
        this.cardRank = CardRank.get(card[0]);
        this.suit = Suit.get(card[1]);
    }

    public Card(char card){
        this.cardRank = CardRank.get(card);
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(this.getCardRank().getWeight(), o.getCardRank().getWeight());
    }

    @Override
    public String toString() {
        return "" + cardRank.getValue() + (suit!=null?suit.getValue():"");
    }

}