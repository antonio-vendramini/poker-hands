package com.exasol.challenge.pokerHands;

import java.util.ArrayList;
import java.util.List;

class PokerHands {

    public static void main(String[] args) {

        if (args == null || args.length < 2) {
            System.out.println("The minimun number of players in the poker game is 2");
        }

        System.out.println("Ranking:");

        List<CardHand> cardsHands = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            cardsHands.add(new CardHand(args[i]));
//            System.out.println("Player " + (i + 1) + " " + args[i]);
        }




    }

}
