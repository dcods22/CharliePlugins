/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.bs.section1;

import charlie.card.Card.Suit;
import charlie.card.Card;
import charlie.card.Hand;
import charlie.card.Hid;
import charlie.dealer.Seat;
import charlie.plugin.IAdvisor;
import charlie.util.Play;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Dan
 */
public class Test00_1217_26 {
    
// TODO add test methods here
    @Test 
    public void testAdvice(){
        Hand myHand = new Hand(new Hid(Seat.YOU));
        Card cardOne = new Card(10, Suit.DIAMONDS);
        Card cardTwo = new Card(7, Suit.CLUBS);
        Card upCard = new Card(9, Suit.HEARTS);
        myHand.hit(cardOne);
        myHand.hit(cardTwo);
        Play advice = Play.HIT;
        Play testAdvice = advise(myHand, upCard);
        assertEquals(advice,testAdvice);
    }
    
}
