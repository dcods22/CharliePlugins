/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package charlie.bot.client;

import charlie.actor.Courier;
import charlie.card.Card;
import charlie.card.Hand;
import charlie.util.Play;
import charlie.advisor.*;
import charlie.card.Hid;
import charlie.plugin.IPlayer;

/**
 *
 * @author Dan
 */
public class ClientBotThread implements Runnable{
    protected Hand myHand;
    protected Card upCard;
    protected Courier courier;
    protected IPlayer player;
    protected Hid hid;
    protected Advisor advisor;
    
    
    public ClientBotThread(Hand myHand, Card upCard, Courier courier, IPlayer player){
        this.myHand = myHand;
        this.upCard = upCard;
        this.courier = courier;
        this.player = player;
        this.hid = myHand.getHid();
        advisor = new Advisor();
    }
    
    @Override
    public void run() {
        //creating an advisor
        Play advise = advisor.advise(myHand, upCard);
        
        //try for the sleep 
        try{
            int sleep = (int) ((Math.random() * 1000) + 1500);

            Thread.sleep(sleep);

            //make the play
            if(advise != Play.HIT){
                if(advise == Play.STAY)
                    courier.stay(hid);
                else if(advise == Play.DOUBLE_DOWN){
                    hid.dubble();
                    courier.dubble(hid);
                }
            }    
            else{ 
                courier.hit(hid);
            }
         }catch(InterruptedException e){
         }
    }
    
}
