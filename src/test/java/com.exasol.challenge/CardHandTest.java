package com.exasol.challenge;

import com.exasol.challenge.pokerHands.CardHand;
import com.exasol.challenge.pokerHands.HandRank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.assertj.core.api.Assertions;

public class CardHandTest{


    @Test
    public void shouldCompareFourOfAKindTest(){
        CardHand cardHand = new CardHand(1, "2S 2S 2S 2S 7D");
        HandRank handRank = cardHand.getRank();

        Assertions.assertEquals(HandRank.HandRankValue.FOUR_OF_A_KIND, handRank.getHandRankValue());
    }

}
