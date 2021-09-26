package com.exasol.challenge.model;

import com.exasol.challenge.pokerHands.model.CardHand;
import com.exasol.challenge.pokerHands.model.CardRank;
import com.exasol.challenge.pokerHands.model.HandRank;
import com.exasol.challenge.pokerHands.model.Suit;
import org.junit.jupiter.api.Test;

import static com.exasol.challenge.pokerHands.model.CardRank.ACE;
import static com.exasol.challenge.pokerHands.model.CardRank.SIX;
import static com.exasol.challenge.pokerHands.model.CardRank.TWO;
import static com.exasol.challenge.pokerHands.model.HandRank.HandRankValue.FOUR_OF_A_KIND;
import static com.exasol.challenge.pokerHands.model.HandRank.HandRankValue.ROYAL_FLUSH;
import static com.exasol.challenge.pokerHands.model.HandRank.HandRankValue.STRAIGHT;
import static com.exasol.challenge.pokerHands.model.HandRank.HandRankValue.STRAIGHT_FLUSH;
import static com.exasol.challenge.pokerHands.model.Suit.DIAMONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardHandTest {

    @Test
    public void shouldClassifyAsFourOfAKind() {
        classifyByRankAndCardRank("2S 2H 2D 2C 7D", FOUR_OF_A_KIND, TWO);
        classifyByRankAndCardRank("7D 2S 2H 2D 2C", FOUR_OF_A_KIND, TWO);
        classifyByRankAndCardRank("AD AS 2S AH AC", FOUR_OF_A_KIND, ACE);
    }

    @Test
    public void shouldClassifyAsStraightFlush() {
        classifyByRankAndCardRank("2D 3D 4D 5D 6D", STRAIGHT_FLUSH, SIX);
    }

    @Test
    public void shouldClassifyAsRoyalFlush() {
        classifyByRankAndCardSuit("JD AD TD KD QD", ROYAL_FLUSH, DIAMONDS);
    }

    @Test
    public void shouldNotClassifyAsRoyalFlush() {
        classifyByRankAndCardRank("JS AD TD KD QD", STRAIGHT, ACE);
    }

    private void classifyByRankAndCardRank(String handString, HandRank.HandRankValue expectedHandRankValue,
                                           CardRank expectedCardRank) {
        CardHand cardHand = new CardHand(1, handString);
        HandRank handRank = cardHand.getRank();

        assertEquals(expectedHandRankValue, handRank.getHandRankValue());
        assertEquals(expectedCardRank, handRank.getHandValuableCards()[0].getCardRank());
    }

    private void classifyByRankAndCardSuit(String handString, HandRank.HandRankValue expectedHandRankValue, Suit expectedSuit) {
        CardHand cardHand = new CardHand(1, handString);
        HandRank handRank = cardHand.getRank();

        assertEquals(expectedHandRankValue, handRank.getHandRankValue());
        assertEquals(expectedSuit, handRank.getHandValuableCards()[0].getSuit());
    }

}
