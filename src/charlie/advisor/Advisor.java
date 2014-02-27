/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package charlie.advisor;
import charlie.card.Card;
import charlie.card.Hand;
import charlie.plugin.IAdvisor;
import charlie.util.Play;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to give advice on what Black Jack move to make
 * @author Dan Cody
 */
public class Advisor implements IAdvisor{
    
    //three maps for the advice
    Map<Integer, Play[]> pairTable;
    Map<Integer, Play[]> aceTable;
    Map<Integer, Play[]> totalTable;
    
    /**
     * empty constructor to create an instance of the Advisor
     */
    public Advisor(){
        
    }
   
    /**
     * Method to return the advice on which Black Jack move to make
     * @param myHand hand you currently have
     * @param upCard the dealers upcard
     * @return Play enum of which move to make
     */
    @Override
    public Play advise(Hand myHand, Card upCard){
        
        BasicStratergy advisor = new BasicStratergy();
        
        //return the advice
        return advisor.advise(myHand, upCard);
    };
    
}
