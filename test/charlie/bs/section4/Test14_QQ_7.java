/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package charlie.bs.section4;

import charlie.card.Card.Suit;
import charlie.card.Card;
import charlie.card.Hand;
import charlie.card.Hid;
import charlie.dealer.Seat;
import charlie.plugin.IAdvisor;
import charlie.advisor.Advisor;
import charlie.util.Play;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Brenden & Dan
 */
public class Test14_QQ_7 {
    
    public Test14_QQ_7() {
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
    public void testAdvice(){
        IAdvisor advisor = new Advisor();
        Hand myHand = new Hand(new Hid(Seat.YOU));
        Card cardOne = new Card(10, Suit.DIAMONDS);
        Card cardTwo = new Card(10, Suit.CLUBS);
        Card upCard = new Card(7, Suit.HEARTS);
        myHand.hit(cardOne);
        myHand.hit(cardTwo);
        Play advice = Play.STAY;
        Play testAdvice = advisor.advise(myHand, upCard);
        assertEquals(advice,testAdvice);
    }
}