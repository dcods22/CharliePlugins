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
    IBot player;
    //the advisor
    private Advisor advisor;
    //the players hand
    Hand myHand;
    //the dealer upcard
    Card upCard;
    //the dealer
    Dealer dealer;
    //the play he should make
    Play advise;
    //the players HID
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
        //create a new advisor
        advisor = new Advisor();
        advise = advisor.advise(myHand, upCard);
        if(advise == Play.SPLIT)
            advise = advisor.adviseTotalOnly(myHand, upCard);
        if((advise == Play.DOUBLE_DOWN) && (myHand.size() > 2))
            advise = Play.HIT;
        
        try{
            int sleep = (int) ((Math.random() * 1000) + 1500);

            Thread.sleep(sleep);

            if(advise != Play.HIT)
                if(advise == Play.STAY)
                    dealer.stay(player, hid);
                else if(advise == Play.DOUBLE_DOWN){
                    hid.dubble();
                    dealer.doubleDown(player, hid);
                }
            else dealer.hit(player, hid);
         }catch(InterruptedException e){
        }
    }
}
