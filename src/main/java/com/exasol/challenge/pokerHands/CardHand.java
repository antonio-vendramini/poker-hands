package com.exasol.challenge.pokerHands;

import com.exasol.challenge.pokerHands.exception.PokerHandException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CardHand implements Comparable<CardHand> {

    private int playerNumber;
    private List<Card> cards;
    private HandRank rank;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public HandRank getRank() {
        return rank;
    }

    public CardHand(int playerNumber, String handString) {
        List<Card> cards = new ArrayList<>();
        for (String card : handString.split(" ")) {
            cards.add(new Card(card.toCharArray()));
        }

        Collections.sort(cards);

        this.playerNumber = playerNumber;
        this.cards = cards;
        this.rank = calculateHandRank(cards);
    }

    @Override
    public int compareTo(CardHand o) {
        if (this.rank.getHandRankValue().ordinal() == o.rank.getHandRankValue().ordinal()){
            if (HandRank.HandRankValue.ROYAL_FLUSH == this.rank.getHandRankValue()){
                return 0;
            }
            if (HandRank.HandRankValue.STRAIGHT_FLUSH == this.rank.getHandRankValue()){
                return Integer.compare(this.rank.getHighestCard(), o.rank.getHighestCard());
            }
        } else {
            return Integer.compare(this.rank.getHandRankValue().ordinal(), o.rank.getHandRankValue().ordinal());
        }

        return 0;
    }

    private HandRank calculateHandRank(List<Card> cards) {
        SortedMap<CardRank, Integer> valuesCount = new TreeMap<>();
        Map<Suit, Integer> suitsCount = new HashMap<>();
        cards.forEach(card -> {
            valuesCount.put(card.getCardRank(), valuesCount.getOrDefault(card.getCardRank(), 0) + 1);
            suitsCount.put(card.getSuit(), suitsCount.getOrDefault(card.getSuit(), 0) + 1);
        });

        Card highestCard = cards.get(cards.size() - 1);
        if (suitsCount.size() == 1 && cards.get(0).getCardRank() == CardRank.TEN) {
            return new HandRank(HandRank.HandRankValue.ROYAL_FLUSH, cards.get(0).getSuit().getValue(),
                    highestCard.getCardRank().getValue());
        }

        boolean areConsecutiveValues = cards.get(0).getCardRank().getValue() + cards.size() - 1 == cards.get(cards.size() - 1).getCardRank().getValue();

        if (areConsecutiveValues && suitsCount.size() == 1) {
            return new HandRank(HandRank.HandRankValue.STRAIGHT_FLUSH, highestCard.getSuit().getValue(),
                    highestCard.getCardRank().getValue());
        }

        if (valuesCount.size() == 2 && valuesCount.containsValue(4)) {
            return new HandRank(HandRank.HandRankValue.FOUR_OF_A_KIND,
                    calculateHandType(valuesCount, 4));
        }

        if (suitsCount.size() == 2 && suitsCount.containsValue(3)) {
            return new HandRank(HandRank.HandRankValue.FULL_HOUSE,
                    calculateHandType(valuesCount, 3));
        }

        if (!areConsecutiveValues && suitsCount.size() == 1) {
            return new HandRank(HandRank.HandRankValue.FLUSH, highestCard.getSuit().getValue());
        }

        if (areConsecutiveValues) {
            return new HandRank(HandRank.HandRankValue.STRAIGHT, highestCard.getCardRank().getValue());
        }

        if (suitsCount.containsValue(3)) {
            return new HandRank(HandRank.HandRankValue.THREE_OF_A_KIND,
                    calculateHandType(valuesCount, 3));
        }

        if (suitsCount.size() == 3 && valuesCount.size() == 3) {
            char[] calculateHandTypeAndHighestCardTwoPairsResult = calculateHandTypeAndHighestCardTwoPairs(valuesCount);
            return new HandRank(HandRank.HandRankValue.TWO_PAIRS,
                    new char[]{calculateHandTypeAndHighestCardTwoPairsResult[0],
                            calculateHandTypeAndHighestCardTwoPairsResult[1]},
                    calculateHandTypeAndHighestCardTwoPairsResult[2]);
        }

        if (suitsCount.size() == 4 && valuesCount.size() == 4) {
            char[] handTypeAndHighestCard = calculateHandTypeAndHighestCardOnePair(valuesCount);
            return new HandRank(HandRank.HandRankValue.ONE_PAIR,
                    handTypeAndHighestCard[0],
                    handTypeAndHighestCard[1]);
        }

        if (valuesCount.size() == 5) {
            return new HandRank(HandRank.HandRankValue.HIGH_CARD, highestCard.getCardRank().getValue());
        }

        throw new PokerHandException("Unable to calculate the hand rank of this one :" + cards);
    }

    private char calculateHandType(Map<CardRank, Integer> valuesCount, int indexOfHandType) {
        for (var entry : valuesCount.entrySet()) {
            if (entry.getValue() == indexOfHandType) {
                return entry.getKey().getValue();
            }
        }
        return 0;
    }

    private char[] calculateHandTypeAndHighestCardOnePair(Map<CardRank, Integer> valuesCount) {
        char[] cards = new char[2];
        for (var entry : valuesCount.entrySet()) {
            cards[entry.getValue() == 2 ? 0 : 1] = entry.getKey().getValue();
        }
        return cards;
    }

    private char[] calculateHandTypeAndHighestCardTwoPairs(Map<CardRank, Integer> valuesCount) {
        char[] cards = new char[3];
        for (var entry : valuesCount.entrySet()) {
            if (entry.getValue() == 1) {
                cards[2] = entry.getKey().getValue();
            } else {
                cards[cards[0] == 0 ? 0 : 1] = entry.getKey().getValue();
            }
        }
        return cards;
    }

    @Override
    public String toString() {
        return Arrays.toString(cards.toArray());
    }

}
