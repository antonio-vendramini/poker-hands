package com.exasol.challenge.pokerHands.engine;

import com.exasol.challenge.pokerHands.log.Log;
import com.exasol.challenge.pokerHands.model.Card;
import com.exasol.challenge.pokerHands.model.CardRank;
import com.exasol.challenge.pokerHands.model.Hand;
import com.exasol.challenge.pokerHands.model.Suit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.exasol.challenge.pokerHands.model.Hand.CARD_HAND_CARD_NUMBER;
import static com.exasol.challenge.pokerHands.model.Hand.CARD_HAND_SEPARATOR;

public class PokerHandEngine {

    private static final int HAND_MIN_NUMBER_OF_PLAYERS = 2;
    private static final int HAND_MAX_NUMBER_OF_PLAYERS = 9;

    public void playPoker(String[] args) {
        printPokerHandResult(calculateHand(args));
    }

    public List<Hand> calculateHand(String[] args) {
        List<Hand> cardsHands = new ArrayList<>();
        if (!validateHand(args)) {
            return Collections.emptyList();
        }

        for (int i = 1; i <= args.length; i++) {
            cardsHands.add(new Hand(i, args[i - 1]));
        }

        Collections.sort(cardsHands);

        return cardsHands;
    }

    private void printPokerHandResult(List<Hand> cardsHands) {

        if (cardsHands == null || cardsHands.size() == 0) {
            Log.error("This hand is empty");
            return;
        }

        Log.info("Ranking:");

        int ranking = 1;

        for (int i = cardsHands.size(); i > 0; i--) {
            final Hand hand = cardsHands.get(i - 1);
            Log.info("        " + ranking + "     Player " + hand.getPlayerNumber() + "    " +
                    hand + "     " + hand.getRank());
            ranking++;
        }
        Log.info(" ");

        final List<Hand> tieWinners = getWinners(cardsHands);

        StringBuilder winnersNumbers = new StringBuilder();
        for (Hand hand : tieWinners) {
            winnersNumbers.append(hand.getPlayerNumber()).append(", ");
        }

        if (tieWinners.size() == 1) {
            Log.info("Player " + winnersNumbers.substring(0, winnersNumbers.length() - 2) + " wins.");
        }
        if (tieWinners.size() > 1) {
            Log.info("Players " + winnersNumbers.substring(0, winnersNumbers.length() - 2) + " win and will get the pod equally splitted");
        }
    }

    public List<Hand> getWinners(List<Hand> cardsHands) {
        final Hand winner = cardsHands.get(cardsHands.size() - 1);
        return cardsHands.stream()
                .filter(c -> c.getRank().getHandRankValue().getOrder() == winner.getRank().getHandRankValue().getOrder())
                .filter(c -> c.getRank().getHandValuableCardsString().equals(winner.getRank().getHandValuableCardsString()))
                .collect(Collectors.toList());
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

        Map<String, Integer> cardsRepetition = new HashMap<>();

        for (String arg : args) {

            final String[] cards = arg.split(CARD_HAND_SEPARATOR, CARD_HAND_CARD_NUMBER);

            if (cards.length < CARD_HAND_CARD_NUMBER ||
                    cards[CARD_HAND_CARD_NUMBER - 1].length() > 2) {
                Log.error("An hand must be made of exactly " + CARD_HAND_CARD_NUMBER + " cards: [" + arg + "]");
                return false;
            }

            final String[] handStringSplit = arg.split(CARD_HAND_SEPARATOR, CARD_HAND_CARD_NUMBER);

            if (!validateHandCards(handStringSplit) || !validateHandCardsRepetition(cardsRepetition, handStringSplit)) {
                return false;
            }
        }

        return true;
    }

    private boolean validateHandCardsRepetition(Map<String, Integer> cardsRepetition, String[] handStringSplit) {
        for (String cardString : handStringSplit) {
            if (cardsRepetition.putIfAbsent(cardString, 1) != null) {
                Log.error("The card is repeated in this hand: [" + cardString + "]");
                return false;
            }
        }
        return true;
    }

    private boolean validateHandCards(String[] handStringSplit) {

        for (String cardString : handStringSplit) {
            final Card card = Card.getCard(cardString);

            if (card.getCardRank() == null) {
                Log.error("Invalid card rank: [" + cardString + "], valid ranks are: " + Arrays.toString(CardRank.values()));
                return false;
            }

            if (card.getSuit() == null) {
                Log.error("Invalid card suit: [" + cardString + "], valid suits are: " + Arrays.toString(Suit.values()));
                return false;
            }
        }
        return true;
    }
}
