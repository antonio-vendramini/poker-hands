package com.exasol.challenge.pokerHands;

import java.util.List;

public class CardHand implements Comparable {

    private List<Card> cards;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
