package com.exasol.challenge.pokerHands.engine;

import com.exasol.challenge.pokerHands.log.Log;
import com.exasol.challenge.pokerHands.model.Card;
import com.exasol.challenge.pokerHands.model.CardHand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerHandEngine {

    private static final int HAND_MIN_NUMBER_OF_PLAYERS = 2;
    private static final int HAND_MAX_NUMBER_OF_PLAYERS = 9;

    public void playPoker(String[] args) {
        printPokerHandResult(calculateHand(args));
    }

    public List<CardHand> calculateHand(String[] args) {
        List<CardHand> cardsHands = new ArrayList<>();
        if (validateHand(args)) {

            for (int i = 1; i <= args.length; i++) {
                cardsHands.add(new CardHand(i, args[i - 1]));
            }

            Collections.sort(cardsHands);

        }
        return cardsHands;
    }


    private void printPokerHandResult(List<CardHand> cardsHands) {
        Log.info("Ranking:");
        int ranking = 1;
        for (int i = 1; i <= cardsHands.size(); i++) {
            final CardHand cardHand = cardsHands.get(i - 1);
            Log.info("        " + ranking + "     Player " + cardHand.getPlayerNumber() + "    " +
                    cardHand + "     " + cardHand.getRank());
            ranking++;
        }
        Log.info(" ");
        Log.info("Player " + cardsHands.get(cardsHands.size() - 1).getPlayerNumber() + " wins.");
    }


    private boolean validateHand(String[] args) {
        if (args == null || args.length == 0) {
            Log.error("The hand is empty");
            return false;
        }

        if (args.length < HAND_MIN_NUMBER_OF_PLAYERS || args.length > HAND_MAX_NUMBER_OF_PLAYERS) {
            Log.error("There can be a min of " + HAND_MIN_NUMBER_OF_PLAYERS +
                    " and a maximun of " + HAND_MAX_NUMBER_OF_PLAYERS);
            return false;
        }

        for (String arg : args) {
            return validateHandCards(arg);
        }

        return true;
    }

    private boolean validateHandCards(String handString) {
        final String[] cards = handString.split(CardHand.CARD_HAND_SEPARATOR, CardHand.CARD_HAND_CARD_NUMBER);
        if (cards.length < CardHand.CARD_HAND_CARD_NUMBER ||
                cards[CardHand.CARD_HAND_CARD_NUMBER - 1].length() > 2) {
            Log.error("An hand must be made of exactly " + CardHand.CARD_HAND_CARD_NUMBER + " cards: [" + handString + "]");
            return false;
        }
        for (String cardString : handString.split(CardHand.CARD_HAND_SEPARATOR, CardHand.CARD_HAND_CARD_NUMBER)) {
            final Card card = Card.getCard(cardString);
            if (card.getCardRank() == null) {
                Log.error("Invalid card rank: [" + cardString + "]");
                return false;
            }
            if (card.getSuit() == null) {
                Log.error("Invalid card suit: [" + cardString + "]");
                return false;
            }
        }
        return true;
    }
}
