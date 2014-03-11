/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package charlie.bot.server;

import charlie.advisor.BasicStrategy;
import charlie.card.Card;
import charlie.card.Hand;
import charlie.card.Hid;
import charlie.dealer.Dealer;
import charlie.plugin.IBot;
import charlie.util.Play;

/**
 *
 * @author Dan
 */
public class Responder implements Runnable{
    IBot player;
    private BasicStrategy advisor;
    Hand myHand;
    Card upCard;
    Dealer dealer;
    Play advise;
    Hid hid;
    
    public Responder(Hand myHand, Card upCard, Dealer dealer, IBot player){
        this.myHand = myHand;
        this.upCard = upCard;
        this.dealer = dealer;
        this.player = player;
        this.hid = myHand.getHid();
    }
    
    @Override
    public void run(){
        advisor = new BasicStrategy();
        advise = advisor.advise(myHand, upCard);
        if(advise == Play.SPLIT)
            advise = advisor.adviseTotalOnly(myHand, upCard);
        
        try{
            int sleep = (int) ((Math.random() * 1000) + 1500);

            Thread.sleep(sleep);

            if(advise == Play.HIT)
                dealer.hit(player, hid);
            else if(advise == Play.STAY)
                dealer.stay(player, hid);
            else if(advise == Play.DOUBLE_DOWN){
                hid.dubble();
                dealer.doubleDown(player, hid);
            }
         }catch(InterruptedException e){
        }
    }
}
