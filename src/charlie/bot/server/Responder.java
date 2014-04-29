package charlie.bot.server;

import charlie.advisor.Advisor;
import charlie.advisor.BasicStrategy;
import charlie.card.Card;
import charlie.card.Hand;
import charlie.card.Hid;
import charlie.dealer.Dealer;
import charlie.plugin.IBot;
import charlie.util.Play;

/**
 *
 * @author Daniel Cody and Brenden Bishop
 */
public class Responder implements Runnable{
    //The player
    private IBot player;
    //the advisor
    private Advisor advisor;
    //the players hand
    private Hand myHand;
    //the dealer upcard
    private Card upCard;
    //the dealer
    private Dealer dealer;
    //the play he should make
    private Play advise;
    //the players HID
    private Hid hid;
    
    public Responder(Hand myHand, Card upCard, Dealer dealer, IBot player){
        this.myHand = myHand;
        this.upCard = upCard;
        this.dealer = dealer;
        this.player = player;
        this.hid = myHand.getHid();
    }
    
    @Override
    public void run(){        
        try{
            int sleep = (int) ((Math.random() * 1000) + 1500);

            Thread.sleep(sleep);
        }catch(InterruptedException e){
        }
        
        if(!(myHand.isBlackjack() || myHand.isBroke() || myHand.isCharlie() || myHand.getValue() == 21)){
        //create a new advisor
        advisor = new Advisor();
        advise = advisor.advise(myHand, upCard);
        if(advise == Play.SPLIT)
            advise = advisor.adviseTotalOnly(myHand, upCard);
        if((advise == Play.DOUBLE_DOWN) && (myHand.size() > 2))
            advise = Play.HIT;
        
            if(advise != Play.HIT)
                if(advise == Play.STAY)
                    dealer.stay(player, hid);
                else if(advise == Play.DOUBLE_DOWN){
                    hid.dubble();
                    dealer.doubleDown(player, hid);
                }
            else dealer.hit(player, hid);
        }
    }
}
