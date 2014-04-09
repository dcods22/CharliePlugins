/*
 Copyright (c) 2014 Ron Coleman

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package charlie.sidebet.rule;

import charlie.card.Card;
import charlie.card.Hand;
import charlie.plugin.ISideBetRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements the side bet rule for Super 7.
 * @author Ron Coleman
 */
public class SideBetRule implements ISideBetRule {
    private final Logger LOG = LoggerFactory.getLogger(SideBetRule.class);
    //setting the pay the user should get
    private Double PAYOFF = 0.0;    
    
    /**
     * Apply rule to the hand and return the payout if the rule matches
     * and the negative bet if the rule does not match.
     * @param hand Hand to analyze.
     * @return 
     */
    @Override
    public double apply(Hand hand) {

        //setting the bet
        Double bet = hand.getHid().getSideAmt();
        LOG.info("side bet amount = "+bet);
        
        //making sure bet is made
        if(bet == 0)
            return 0.0;
        
        LOG.info("side bet rule applying hand = "+hand);
        
        //Getting both cards in the hand
        Card card = hand.getCard(0);
        Card card2 = hand.getCard(1);
 
        //Super 7
        if(card.getRank() == 7) {
            LOG.info("side bet SUPER 7 matches");
            if(PAYOFF < 3.0)
                PAYOFF = 3.0;
        }
        
        //Royal Match
        if(card.getSuit() == card2.getSuit()){
            if((card.getRank() == Card.QUEEN && card2.getRank() == Card.KING) || (card2.getRank() == Card.QUEEN || card.getRank() == Card.KING)){
                LOG.info("side bet ROYAL MATCH matches");
                if(PAYOFF < 25.0)
                    PAYOFF = 25.0;
            }
        }
        
        //Exactly 13
        if(hand.getValue() == 13){
            LOG.info("side bet EXACTLY13 matches");
            if(PAYOFF < 10.0)
                PAYOFF = 10.0;
        }
        
        if(PAYOFF != 0.0)
            return bet * PAYOFF;
        else{
            LOG.info("side bet rule no match");
        
            return -bet;
        }
    }
}
