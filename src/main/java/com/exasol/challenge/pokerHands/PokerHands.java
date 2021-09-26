package com.exasol.challenge.pokerHands;

import com.exasol.challenge.pokerHands.engine.PokerHandEngine;
import com.exasol.challenge.pokerHands.log.Log;

class PokerHands {

    public static void main(String[] args) {

        if (args == null || args.length < 2) {
            Log.error("The min number of players in the poker game is 2");
            return;
        }

        PokerHandEngine pokerHandEngine = new PokerHandEngine();
        pokerHandEngine.playPoker(args);
    }
}
