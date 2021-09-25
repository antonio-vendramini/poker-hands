package com.exasol.challenge.pokerHands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PokerHands {

    public static void main(String[] args) {

        if (args == null || args.length < 2) {
            System.out.println("The minimun number of players in the poker game is 2");
        }

        System.out.println("Ranking:");

        List<CardHand> cardsHands = new ArrayList<>();
        for (int i=1; i<=args.length; i++) {
            cardsHands.add(new CardHand(i, args[i]));
        }

        Collections.sort(cardsHands);

        System.out.println("Ranking:");
        for (int i=1; i<=cardsHands.size(); i++) {
            CardHand cardHand =cardsHands.get(i-1);
            System.out.println("        " + i + "     Player " + cardHand.getPlayerNumber() + "    " + cardHand  + "     " + cardHand.getRank().getHandRankValue().name() + ", " + cardHand.getRank().getHandType());
        }
        System.out.println("Player " + cardsHands.get(0).getPlayerNumber() + " wins.");



    }

}
