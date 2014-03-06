/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package charlie.bot.server;

import charlie.advisor.BasicStrategy;
import charlie.card.Card;
import charlie.card.Hand;
import charlie.dealer.Dealer;
import charlie.util.Play;

/**
 *
 * @author Dan
 */
public class Responder implements Runnable{
    private BasicStrategy advisor;
    Hand myHand;
    Card upCard;
    Dealer dealer;
    Play advise;
    
    public Responder(Hand myHand, Card upCard, Dealer dealer){
        this.myHand = myHand;
        this.upCard = upCard;
        this.dealer = dealer;
    }
    
    @Override
    public void run(){
        advisor = new BasicStrategy();
        advise = advisor.advise(myHand, upCard);
        if(advise == Play.STAY)
            advise = advisor.adviseTotalOnly(myHand, upCard);
        
        
    }
}
