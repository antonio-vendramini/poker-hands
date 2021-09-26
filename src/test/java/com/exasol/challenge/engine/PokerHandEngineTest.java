package com.exasol.challenge.engine;

import com.exasol.challenge.pokerHands.engine.PokerHandEngine;
import com.exasol.challenge.pokerHands.model.Hand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerHandEngineTest {

    @Test
    public void shouldChooseTheHighestCardInFourOfKindUnordered() {
        playAndCheckUniqueWinner(2, "2D 2C 2S 2H 7C", "8D 9D 9C 9S 9H");
    }


    @Test
    public void shouldChooseTheRoyalFlush() {
        playAndCheckUniqueWinner(1, "TC JC QC KC AC", "8D 9D 9C 9S 9H");
    }

    @Test
    public void shouldChooseTheHighestCardInTwoPairsUnordered() {
        playAndCheckUniqueWinner(1, "2D 2C 9D 9C JC", "2S 2H 3D 9D 9S");
    }

    @Test
    public void shouldChooseTheFirstBestPairInTwoPairs() {
        playAndCheckUniqueWinner(1, "7D AD 8S AS 7C", "8S KS 9D 8H KD");
    }

    @Test
    public void shouldChooseTheSecondBestPairInTwoPairs() {
        playAndCheckUniqueWinner(2, "7D AD 8S AS 7C", "8S AC 9D 8H AH");
    }

    @Test
    public void shouldChooseTheHighestCardInTwoPairs() {
        playAndCheckUniqueWinner(2, "7D AD 8S AS 7C", "7S AC 9D 7H AH");
    }

    @Test
    public void shouldHaveTwoTieInTwoPairs() {
        playAndCheckTie(2, "7D 7C 9S AD AS", "7S 7H 9D AC AH", "2H 3H 4D 7S KD");
    }

    @Test
    public void shouldChooseTheFirstPairInOnePairs() {
        playAndCheckUniqueWinner(1, "7C 8S 9D AD AS", "9D QS QH KC AH", "2H 3H 4D 7S KD");
    }

    @Test
    public void shouldChooseTheHighestCardInOnePair() {
        playAndCheckUniqueWinner(2, "9C JS QD AD AS", "2D 3H KC AC AH", "2H 3H 4D 7S KD");
    }

    @Test
    public void shouldChooseTheHighestCardInHighCard() {
        playAndCheckUniqueWinner(2, "9D KD 8S AS 7C", "AC KC 9D QH AH", "KD 7S 4D 2H 3H");
    }

    @Test
    public void shouldHaveTwoTiesInHighCard() {
        playAndCheckTie(2, "7C 8S 9D KD AS", "7D 8H 9H KS AD", "KD 7S 4D 2H 3H");
    }

    private void playAndCheckUniqueWinner(int expectedWinner, String... cardsHand) {
        PokerHandEngine pokerHandEngine = new PokerHandEngine();
        List<Hand> ranking = pokerHandEngine.calculateHand(cardsHand);

        assertEquals(expectedWinner, ranking.get(ranking.size() - 1).getPlayerNumber());
    }

    private void playAndCheckTie(int expectedNumberOfWinners, String... cardsHand) {
        PokerHandEngine pokerHandEngine = new PokerHandEngine();
        List<Hand> ranking = pokerHandEngine.calculateHand(cardsHand);

        final int bestRanking = ranking.get(ranking.size() - 1).getRank().getHighestCard().getCardRank().getWeight();
        int numberOfWinners = 0;
        for (int i = ranking.size() - 1; i >= 0; i--) {
            if( bestRanking == ranking.get(i).getRank().getHighestCard().getCardRank().getWeight()){
                numberOfWinners++;
            }
        }

        assertEquals(expectedNumberOfWinners, numberOfWinners);

    }

}
