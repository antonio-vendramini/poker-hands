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
        int ranking = 1;
        for (int i=1; i<=cardsHands.size(); i++) {
            final CardHand cardHand = cardsHands.get(i-1);
            Log.info("        " + ranking + "     Player " + cardHand.getPlayerNumber() + "    " +
                    cardHand  + "     " + cardHand.getRank());
            ranking++;
        }
        Log.info("Player " + cardsHands.get(cardsHands.size()-1).getPlayerNumber() + " wins.");

    }

}
