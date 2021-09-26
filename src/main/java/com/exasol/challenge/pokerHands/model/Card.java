package com.exasol.challenge.pokerHands.model;

import java.util.Objects;

public class Card implements Comparable<Card> {

    private CardRank cardRank;
    private Suit suit;

    private Card(char[] card) {
        this.cardRank = CardRank.get(card[0]);
        this.suit = Suit.get(card[1]);
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public Suit getSuit() {
        return suit;
    }

    public static Card getCard(String cardString) {
        return new Card(cardString.toCharArray());
    }

    @Override
    public int compareTo(Card other) {
        return Integer.compare(this.getCardRank().getWeight(), other.getCardRank().getWeight());
    }

    @Override
    public String toString() {
        return "" + cardRank.getValue() + (suit != null ? suit.getValue() : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return getCardRank().getWeight() == card.getCardRank().getWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardRank());
    }
}
