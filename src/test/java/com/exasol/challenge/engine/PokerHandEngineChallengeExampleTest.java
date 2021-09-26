package com.exasol.challenge.engine;

import com.exasol.challenge.pokerHands.engine.PokerHandEngine;
import com.exasol.challenge.pokerHands.model.Hand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerHandEngineChallengeExampleTest {

    @Test
    public void playerTwoWins() {
        playAndCheckWinner(2, "2D 9C AS AH AC", "3D 6D 7D TD QD", "2C 5C 7C 8S QH");
    }

    @Test
    public void challengeExampleOne() {
        playAndCheckWinner(2, "5H 5C 6S 7S KD", "2C 3S 8S 8D TD", "5D 8C 9S JS AC");
    }

    @Test
    public void challengeExampleTwo() {
        playAndCheckWinner(1, "5D 8C 9S JS AC", "2C 5C 7D 8S QH", "2C 5C 7D 8S KH");
    }

    @Test
    public void challengeExampleThree() {
        playAndCheckWinner(2, "2D 9C AS AH AC", "3D 6D 7D TD QD", "2C 5C 7D 8S QH");
    }

    @Test
    public void challengeExampleFour() {
        playAndCheckWinner(1, "4D 6S 9H QH QC", "3D 6D 7H QD QS", "2C 5C 7D 8S QH");
    }

    @Test
    public void challengeExampleFive() {
        playAndCheckWinner(1, "2H 2D 4C 4D 4S", "3C 3D 3S 9S 9D", "2C 5C 7D 8S QH");
    }

    private void playAndCheckWinner(int expectedWinner, String... cardsHand) {
        PokerHandEngine pokerHandEngine = new PokerHandEngine();
        List<Hand> res = pokerHandEngine.calculateHand(cardsHand);

        assertEquals(expectedWinner, res.get(res.size() - 1).getPlayerNumber());
    }

}
