package com.exasol.challenge.engine;

import com.exasol.challenge.pokerHands.engine.PokerHandEngine;
import com.exasol.challenge.pokerHands.model.Hand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerHandEngineTest {

    @Test
    public void shouldChooseTheHighestCardInFourOfKindUnordered() {
        playAndCheckWinner(2, "2D 2C 2S 2H 7C", "8D 9D 9C 9S 9H");
    }

    @Test
    public void shouldChooseTheHighestCardInTwoPairsUnordered() {
        playAndCheckWinner(1, "2D 2C 9D 9C JC", "2S 2H 3D 9D 9S");
    }

    @Test
    public void shouldChooseTheBestHighestBestPair() {
        playAndCheckWinner(1, "7D AD 8S AS 7C", "8S KS 9D 8H KD");
    }


    private void playAndCheckWinner(int expectedWinner, String... cardsHand) {
        PokerHandEngine pokerHandEngine = new PokerHandEngine();
        List<Hand> res = pokerHandEngine.calculateHand(cardsHand);

        assertEquals(expectedWinner, res.get(res.size() - 1).getPlayerNumber());
    }

}
