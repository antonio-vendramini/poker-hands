package com.exasol.challenge;

import com.exasol.challenge.pokerHands.CardHand;
import com.exasol.challenge.pokerHands.CardRank;
import com.exasol.challenge.pokerHands.HandRank;
import com.exasol.challenge.pokerHands.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardHandTest{

    @Test
    public void shouldClassifyAsFourOfAKind(){
        classifyByRankAndType("2S 2H 2D 2C 7D", HandRank.HandRankValue.FOUR_OF_A_KIND, CardRank.TWO.getValue());
        classifyByRankAndType("7D 2S 2H 2D 2C", HandRank.HandRankValue.FOUR_OF_A_KIND, CardRank.TWO.getValue());
        classifyByRankAndType("AD AS 2S AH AC", HandRank.HandRankValue.FOUR_OF_A_KIND, CardRank.ACE.getValue());
    }

    @Test
    public void shouldClassifyAsStraightFlush(){
        classifyByRankAndType("2D 3D 4D 5D 6D", HandRank.HandRankValue.STRAIGHT_FLUSH, CardRank.SIX.getValue());
//        classifyByRankAndType("2D 3D 4S 5D 6D", HandRank.HandRankValue.FOUR_OF_A_KIND, CardRank.TWO.getValue());
    }

    @Test
    public void shouldClassifyAsRoyalFlush(){
        classifyByRankAndType("JD AD TD KD QD", HandRank.HandRankValue.ROYAL_FLUSH, Suit.DIAMONDS.getValue());
    }

    @Test
    public void shouldNotClassifyAsRoyalFlush(){
        classifyByRankAndType("JS AD TD KD QD", HandRank.HandRankValue.HIGH_CARD, CardRank.ACE.getValue());
    }

    private void classifyByRankAndType(String handString, HandRank.HandRankValue expectedHandRankValue, char expectedType){
        CardHand cardHand = new CardHand(1, handString);
        HandRank handRank = cardHand.getRank();

        Assertions.assertEquals(expectedHandRankValue, handRank.getHandRankValue());
        Assertions.assertEquals(expectedType, handRank.getHandValuableCards()[0]);
    }

}
