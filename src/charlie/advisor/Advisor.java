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
            int value = cardOne.value();
            //lookup is value since both cards are the same
            //pair hash table
            advice = Play.NONE;
        }
        else if(cardOne.isAce()){
            int value = cardTwo.value();
            //ace hash table
            //key is value because its other card besides ace
            advice = Play.NONE;        
        }
        else if(cardTwo.isAce()){
            int value = cardOne.value();
            //ace hash table
            //key is value because its other card besides ace
            advice = Play.NONE;          
        }else{
            int total = cardOne.value() + cardTwo.value();
            //total number hash table
            //key is total since its the amount
            advice = Play.NONE;
        }
            
        return advice;
    };
    
}
