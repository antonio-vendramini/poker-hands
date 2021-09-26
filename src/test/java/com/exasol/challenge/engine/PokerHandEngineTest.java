package com.exasol.challenge.engine;

import com.exasol.challenge.pokerHands.engine.PokerHandEngine;
import org.junit.jupiter.api.Test;

public class PokerHandEngineTest {

    @Test
    public void should() {
        PokerHandEngine pokerHandEngine = new PokerHandEngine();
        pokerHandEngine.playPoker(new String[]{"2D 9C AS AH AC", "3D 6D 7D TD QD", "2C 5C 7C 8S QH"});
    }

}
