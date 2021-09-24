package com.exasol.challenge.pokerHands;

import com.exasol.challenge.pokerHands.exception.PokerHandException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CardHand implements Comparable<CardHand> {

    private List<Card> cards;
    private HandRank rank;
    private Card handType;
    private Card highestCard;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    CardHand(String handString) {
        List<Card> cards = new ArrayList<>();
        for (String card : handString.split(" ")) {
            cards.add(new Card(card.toCharArray()));
        }
        this.cards = cards;
        this.rank = calculateHandRank(cards);
    }

    @Override
    public int compareTo(CardHand o) {
        return 0;
    }

    private static HandRank calculateHandRank(List<Card> cards) {
        SortedMap<Character, Integer> valuesCount = new TreeMap();
        Map<Character, Integer> suitsCount = new HashMap<>();
        cards.forEach(card -> {
            valuesCount.put(card.getValue(), valuesCount.getOrDefault(card.getValue(), 0) + 1);
            suitsCount.put(card.getSuit(), suitsCount.getOrDefault(card.getSuit(), 0) + 1);
        });

        if (suitsCount.size() == 1 && cards.get(0).getValue() == CardRank.TEN.getValue()) {
            return new HandRank(HandRank.HandRankValue.ROYAL_FLUSH, CardRank.ACE, CardRank.ACE);
        }

        boolean areConsecutiveValues = cards.get(0).getValue() + cards.size() - 1 == cards.get(cards.size() - 1).getValue();
        CardRank highestCard = CardRank.get(cards.get(cards.size() - 1).getValue());

        if (areConsecutiveValues && suitsCount.size() == 1) {
            return new HandRank(HandRank.HandRankValue.STRAIGHT_FLUSH, highestCard, highestCard);
        }

        if (suitsCount.size() == 2 && suitsCount.containsValue(4)) {
            return new HandRank(HandRank.HandRankValue.FOUR_OF_A_KIND, CardRank.get(calculateHandType(suitsCount, 4)));
        }

        if (suitsCount.size() == 2 && suitsCount.containsValue(3)) {
            return new HandRank(HandRank.HandRankValue.FULL_HOUSE, CardRank.get(calculateHandType(suitsCount, 3)));
        }

        if (!areConsecutiveValues && suitsCount.size() == 1) {
            return new HandRank(HandRank.HandRankValue.FLUSH, highestCard);
        }

        if (areConsecutiveValues) {
            return new HandRank(HandRank.HandRankValue.STRAIGHT, highestCard);
        }

        if (suitsCount.containsValue(3)) {
            return new HandRank(HandRank.HandRankValue.THREE_OF_A_KIND, CardRank.get(calculateHandType(suitsCount, 3)));
        }

        if (suitsCount.size() == 3 && valuesCount.size() == 3) {
            //// ???????
            return new HandRank(HandRank.HandRankValue.TWO_PAIRS, highestCard, highestCard);
        }

        if (suitsCount.size() == 4 && valuesCount.size() == 4) {
            //// ???????
            return new HandRank(HandRank.HandRankValue.ONE_PAIR, highestCard, highestCard);
        }

        if (suitsCount.size() == 5 && valuesCount.size() == 5) {
            return new HandRank(HandRank.HandRankValue.HIGH_CARD, highestCard);
        }

        throw new PokerHandException("Unable to calculate the hand rank of this one :" + cards);
    }

    private static char[] calculateHandTypeAndHighestValue(Map<Character, Integer> suitsCount,
                                                  int indexOfHandType, int indexOfHighestCard) {
        char[] result = new char[2];
        for (var entry : suitsCount.entrySet()) {
            if (entry.getValue() == indexOfHandType) {
                result[0] = entry.getKey();
            }
            if (entry.getValue() == indexOfHighestCard) {
                result[1] = entry.getKey();
            }
        }
        return result;
    }

    private static char calculateHandType(Map<Character, Integer> suitsCount, int indexOfHandType) {
        for (var entry : suitsCount.entrySet()) {
            if (entry.getValue() == indexOfHandType) {
                return entry.getKey();
            }
        }
        return 0;
    }
}
