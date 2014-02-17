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

/**
 * Class to give advice on what Black Jack move to make
 * @author Dan
 */

public class Advisor implements IAdvisor{
    
    @Override
    public Play advise(Hand myHand, Card upCard){
        Card cardOne = myHand.getCard(0);
        Card cardTwo = myHand.getCard(1);
        Play advice;
        
        if(myHand.isPair()){
           //pair hash table
            advice = Play.NONE;
        }
        else if(cardOne.isAce() || cardTwo.isAce()){
            //ace hash table
            advice = Play.NONE;
        }else{
            //total number hash table
            advice = Play.NONE;
        }
            
        return advice;
    };
    
}
