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
        if (this.rank.getHandRankValue().getOrder() == o.rank.getHandRankValue().getOrder()) {
            if (HandRank.HandRankValue.ROYAL_FLUSH == this.rank.getHandRankValue()) {
                return 0;
            }
            if (HandRank.HandRankValue.STRAIGHT_FLUSH == this.rank.getHandRankValue()) {
                return Integer.compare(this.rank.getHighestCard().getCardRank().getNumber(), o.rank.getHighestCard().getCardRank().getNumber());
            }
        } else {
            return Integer.compare(this.rank.getHandRankValue().getOrder(), o.rank.getHandRankValue().getOrder());
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
            return HandRank.getHandRankForRoyalFlush(cards.get(0));
        }

        final boolean isListOfConsecutiveCards = calculateIsListOfConsecutiveCards(cards);

        if (isListOfConsecutiveCards && suitsCount.size() == 1) {
            return HandRank.getHandRankForStraightFlush(highestCard);
        }

        if (valuesCount.size() == 2 && valuesCount.containsValue(4)) {
            return HandRank.getHandRankForFourOfAKind(calculateHandType(valuesCount, 4));
        }

        if (suitsCount.size() == 2 && suitsCount.containsValue(3)) {
            return HandRank.getHandRankForFullHouse(calculateHandType(valuesCount, 3));
        }

        if (!isListOfConsecutiveCards && suitsCount.size() == 1) {
            return HandRank.getHandRankForFlush(highestCard);
        }

        if (isListOfConsecutiveCards) {
            return HandRank.getHandRankForStraight(highestCard);
        }

        if (suitsCount.containsValue(3)) {
            return HandRank.getHandRankForThreeOfAKind(calculateHandType(valuesCount, 3));
        }

        if (suitsCount.size() == 3 && valuesCount.size() == 3) {
            final Card[] calculateHandTypeAndHighestCardTwoPairsResult = calculateHandTypeAndHighestCardTwoPairs(valuesCount);
            return HandRank.getHandRankForTwoPairs(
                    new Card[]{calculateHandTypeAndHighestCardTwoPairsResult[0],
                            calculateHandTypeAndHighestCardTwoPairsResult[1]},
                    calculateHandTypeAndHighestCardTwoPairsResult[2]);
        }

        if (valuesCount.size() == 4) {
            final Card[] handTypeAndHighestCard = calculateHandTypeAndHighestCardOnePair(valuesCount);
            return HandRank.getHandRankForOnePair(handTypeAndHighestCard[0], handTypeAndHighestCard[1]);
        }

        if (valuesCount.size() == 5) {
            return HandRank.getHandRankForHighCard(highestCard);
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

    private Card[] calculateHandTypeAndHighestCardOnePair(Map<Card, Integer> valuesCount) {
        Card[] cards = new Card[2];
        for (var entry : valuesCount.entrySet()) {
            cards[entry.getValue() == 2 ? 0 : 1] = entry.getKey();
        }
        return cards;
    }

    private Card[] calculateHandTypeAndHighestCardTwoPairs(Map<Card, Integer> valuesCount) {
        Card[] cards = new Card[3];
        for (var entry : valuesCount.entrySet()) {
            if (entry.getValue() == 1) {
                cards[2] = entry.getKey();
            } else {
                cards[cards[0] == null ? 0 : 1] = entry.getKey();
            }
        }
        return cards;
    }

    @Override
    public String toString() {
        return Arrays.toString(cards.toArray());
    }

    private boolean calculateIsListOfConsecutiveCards(List<Card> cards) {
        final int firstCardOrder = cards.get(0).getCardRank().getNumber();
        final int lastCardOrder = cards.get(cards.size() - 1).getCardRank().getNumber();
        return firstCardOrder + cards.size() - 1 == lastCardOrder;
    }

}
