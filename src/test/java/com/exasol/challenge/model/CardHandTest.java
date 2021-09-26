package com.exasol.challenge.model;

import com.exasol.challenge.pokerHands.model.Card;
import com.exasol.challenge.pokerHands.model.CardHand;
import com.exasol.challenge.pokerHands.model.CardRank;
import com.exasol.challenge.pokerHands.model.HandRank;
import com.exasol.challenge.pokerHands.model.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardHandTest{

    @Test
    public void shouldClassifyAsFourOfAKind(){
        classifyByRankAndCardRank("2S 2H 2D 2C 7D", HandRank.HandRankValue.FOUR_OF_A_KIND, new Card(CardRank.TWO.getValue()));
        classifyByRankAndCardRank("7D 2S 2H 2D 2C", HandRank.HandRankValue.FOUR_OF_A_KIND, new Card(CardRank.TWO.getValue()));
        classifyByRankAndCardRank("AD AS 2S AH AC", HandRank.HandRankValue.FOUR_OF_A_KIND, new Card(CardRank.ACE.getValue()));
    }

    @Test
    public void shouldClassifyAsStraightFlush(){
        classifyByRankAndCardRank("2D 3D 4D 5D 6D", HandRank.HandRankValue.STRAIGHT_FLUSH, new Card(CardRank.SIX.getValue()));
    }

    @Test
    public void shouldClassifyAsRoyalFlush(){
        classifyByRankAndCardSuit("JD AD TD KD QD", HandRank.HandRankValue.ROYAL_FLUSH, Suit.DIAMONDS);
    }

    @Test
    public void shouldNotClassifyAsRoyalFlush(){
        classifyByRankAndCardRank("JS AD TD KD QD", HandRank.HandRankValue.STRAIGHT, new Card(CardRank.ACE.getValue()));
    }

    private void classifyByRankAndCardRank(String handString, HandRank.HandRankValue expectedHandRankValue, Card expectedType){
        CardHand cardHand = new CardHand(1, handString);
        HandRank handRank = cardHand.getRank();

        Assertions.assertEquals(expectedHandRankValue, handRank.getHandRankValue());
        Assertions.assertEquals(expectedType.getCardRank(), handRank.getHandValuableCards()[0].getCardRank());
    }

    private void classifyByRankAndCardSuit(String handString, HandRank.HandRankValue expectedHandRankValue, Suit expectedSuit){
        CardHand cardHand = new CardHand(1, handString);
        HandRank handRank = cardHand.getRank();

        Assertions.assertEquals(expectedHandRankValue, handRank.getHandRankValue());
        Assertions.assertEquals(expectedSuit, handRank.getHandValuableCards()[0].getSuit());
    }

}
