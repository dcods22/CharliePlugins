/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package charlie.sb.section1;

import charlie.card.Card;
import charlie.card.Hand;
import charlie.card.Hid;
import charlie.dealer.Seat;
import charlie.sidebet.rule.SideBetRule;
import charlie.sidebet.test.Shoe;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Brenden
 */
public class Test_10 {
    
    public Test_10() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testSideBets() {
        Shoe shoe = new Shoe();
        shoe.init();
        Hand myHand = new Hand(new Hid(Seat.YOU));
        Card cardOne = new Card(shoe.next());
        Card cardTwo = new Card(shoe.next());
        myHand.hit(cardOne);
        myHand.hit(cardTwo);
        myHand.getHid().setSideAmt(10);
        myHand.getHid().setAmt(25);
        SideBetRule test = new SideBetRule();
        double startAmt = 1000;
        double totalAmt = 1055;
        double betAmt = myHand.getHid().getAmt();
        double testPayout = test.apply(myHand);
        double payout = startAmt + betAmt + testPayout;
        assertEquals(payout, totalAmt, 0.01);     
    }
}
