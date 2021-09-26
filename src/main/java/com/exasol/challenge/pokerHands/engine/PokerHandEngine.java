package com.exasol.challenge.pokerHands.engine;

import com.exasol.challenge.pokerHands.log.Log;
import com.exasol.challenge.pokerHands.model.CardHand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerHandEngine {

    public void playPoker(String[] args){

        Log.info("Ranking:");

        List<CardHand> cardsHands = new ArrayList<>();
        assert args != null;
        for (int i = 1; i<=args.length; i++) {
            cardsHands.add(new CardHand(i, args[i-1]));
        }

        Collections.sort(cardsHands);

        Log.info("Ranking:");
        for (int i=1; i<=cardsHands.size(); i++) {
            CardHand cardHand = cardsHands.get(i-1);
            Log.info("        " + i + "     Player " + cardHand.getPlayerNumber() + "    " + cardHand  + "     " + cardHand.getRank().getHandRankValue().name() + ", " + cardHand.getRank());
        }
        Log.info("Player " + cardsHands.get(0).getPlayerNumber() + " wins.");

    }

}
