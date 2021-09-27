package com.exasol.challenge.pokerHands.model;

import com.exasol.challenge.pokerHands.exception.PokerHandException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.exasol.challenge.pokerHands.model.HandRank.HandRankValue.FLUSH;
import static com.exasol.challenge.pokerHands.model.HandRank.HandRankValue.FOUR_OF_A_KIND;
import static com.exasol.challenge.pokerHands.model.HandRank.HandRankValue.FULL_HOUSE;
import static com.exasol.challenge.pokerHands.model.HandRank.HandRankValue.HIGH_CARD;
import static com.exasol.challenge.pokerHands.model.HandRank.HandRankValue.ONE_PAIR;
import static com.exasol.challenge.pokerHands.model.HandRank.HandRankValue.ROYAL_FLUSH;
import static com.exasol.challenge.pokerHands.model.HandRank.HandRankValue.STRAIGHT;
import static com.exasol.challenge.pokerHands.model.HandRank.HandRankValue.STRAIGHT_FLUSH;
import static com.exasol.challenge.pokerHands.model.HandRank.HandRankValue.THREE_OF_A_KIND;
import static com.exasol.challenge.pokerHands.model.HandRank.HandRankValue.TWO_PAIRS;
import static com.exasol.challenge.pokerHands.model.HandRank.getHandRank;

public class Hand implements Comparable<Hand> {

    public static final String CARD_HAND_SEPARATOR = " ";
    public static final int CARD_HAND_CARD_NUMBER = 5;

    private int playerNumber;

    private List<Card> cards;
    private HandRank rank;

    public int getPlayerNumber() {
        return playerNumber;
    }

    public HandRank getRank() {
        return rank;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Hand(int playerNumber, String handString) {
        List<Card> cards = new ArrayList<>();
        for (String card : handString.split(CARD_HAND_SEPARATOR)) {
            cards.add(Card.getCard(card));
        }

        Collections.sort(cards);

        this.playerNumber = playerNumber;
        this.cards = cards;
        this.rank = calculateHandRank(cards);
    }

    @Override
    public int compareTo(Hand other) {
        if (this.rank.getHandRankValue().getOrder() == other.rank.getHandRankValue().getOrder()) {
            return compareValuableCards(other);
        } else {
            return Integer.compare(this.rank.getHandRankValue().getOrder(), other.rank.getHandRankValue().getOrder());
        }
    }

    @Override
    public String toString() {
        return cards.stream().map(Object::toString).collect(Collectors.joining(" "));
    }

    private int compareValuableCards(Hand other) {
        for (int i = 0; i < this.getRank().getHandValuableCards().length; i++) {
            final int thisCardWeight = this.getRank().getHandValuableCards()[i].getCardRank().getWeight();
            final int otherCardWeight = other.getRank().getHandValuableCards()[i].getCardRank().getWeight();
            if (thisCardWeight != otherCardWeight) {
                return Integer.compare(thisCardWeight, otherCardWeight);
            }
        }
        return 0;
    }

    private HandRank calculateHandRank(List<Card> cards) {
        SortedMap<Card, Integer> valuesCount = new TreeMap<>();
        Map<Suit, Integer> suitsCount = new HashMap<>();
        cards.forEach(card -> {
            valuesCount.put(card, valuesCount.getOrDefault(card, 0) + 1);
            suitsCount.put(card.getSuit(), suitsCount.getOrDefault(card.getSuit(), 0) + 1);
        });

        final Card highestCard = cards.get(cards.size() - 1);
        if (suitsCount.size() == 1 && cards.get(0).getCardRank() == CardRank.TEN) {
            return getHandRank(ROYAL_FLUSH, cards.get(0));
        }

        final boolean isListOfConsecutiveCards = calculateIsListOfConsecutiveCards(cards);

        if (isListOfConsecutiveCards && suitsCount.size() == 1) {
            return getHandRank(STRAIGHT_FLUSH, highestCard);
        }

        if (valuesCount.size() == 2 && valuesCount.containsValue(4)) {
            return getHandRank(FOUR_OF_A_KIND, calculateHandType(valuesCount, 4));
        }

        if (valuesCount.size() == 2 && valuesCount.containsValue(2) && valuesCount.containsValue(3)) {
            return getHandRank(FULL_HOUSE, calculateHandType(valuesCount, 3));
        }

        if (!isListOfConsecutiveCards && suitsCount.size() == 1) {
            return getHandRank(FLUSH, highestCard);
        }

        if (isListOfConsecutiveCards) {
            return getHandRank(STRAIGHT, highestCard);
        }

        if (valuesCount.containsValue(3)) {
            return getHandRank(THREE_OF_A_KIND, calculateHandType(valuesCount, 3));
        }

        if (valuesCount.size() == 3) {
            return getHandRank(TWO_PAIRS, calculateHandTypeAndHighestCardTwoPairs(valuesCount, cards));
        }

        if (valuesCount.size() == 4) {
            return getHandRank(ONE_PAIR, calculateHandTypeAndHighestCardOnePair(valuesCount, cards));
        }

        if (valuesCount.size() == 5) {
            return getHandRank(HIGH_CARD, calculateHandTypeAndHighestCard(cards));
        }

        throw new PokerHandException("Unable to calculate the hand rank of this one :" + cards);
    }

    private Card calculateHandType(Map<Card, Integer> valuesCount, int indexOfHandType) {
        for (var entry : valuesCount.entrySet()) {
            if (entry.getValue() == indexOfHandType) {
                return entry.getKey();
            }
        }
        return null;
    }

    private Card[] calculateHandTypeAndHighestCard(List<Card> cards) {
        List<Card> clonedCards = cards.subList(0, cards.size());
        Collections.reverse(clonedCards);
        return clonedCards.toArray(Card[]::new);
    }

    private Card[] calculateHandTypeAndHighestCardOnePair(Map<Card, Integer> valuesCount, List<Card> cards) {
        Card[] calculatedCards = new Card[4];

        int i = 3;
        for (Card card : cards) {
            if (valuesCount.get(card) == 2) {
                calculatedCards[0] = card;
            } else {
                calculatedCards[i] = card;
                i--;
            }
        }

        return calculatedCards;
    }

    private Card[] calculateHandTypeAndHighestCardTwoPairs(Map<Card, Integer> valuesCount, List<Card> cards) {
        Card[] calculatedCards = new Card[3];

        int i = 1;
        for (Card card : cards) {
            if (valuesCount.get(card) == 1) {
                calculatedCards[2] = card;
            } else {
                if (calculatedCards[i] == null || calculatedCards[i] != null && calculatedCards[i].getCardRank().getWeight() == card.getCardRank().getWeight()) {
                    calculatedCards[i] = card;
                } else {
                    i--;
                }
            }
        }
        return calculatedCards;
    }

    private boolean calculateIsListOfConsecutiveCards(List<Card> cards) {
        final int firstCardOrder = cards.get(0).getCardRank().getWeight();
        final int lastCardOrder = cards.get(cards.size() - 1).getCardRank().getWeight();
        return firstCardOrder + cards.size() - 1 == lastCardOrder;
    }

}
