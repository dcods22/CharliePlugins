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
public class ClientBotThread implements Runnable {
    //Fields
    protected Hand myHand;
    protected Card upCard;
    protected Courier courier;
    protected IPlayer player;
    protected Hid hid;
    protected Advisor advisor;

    /**
     * constructor to create a thread
     * @param myHand the hand of the player
     * @param upCard the dealers upcard
     * @param courier the courier
     * @param player a reference to the player
     */
    public ClientBotThread(Hand myHand, Card upCard, Courier courier, IPlayer player) {
        this.myHand = myHand;
        this.upCard = upCard;
        this.courier = courier;
        this.player = player;
        this.hid = myHand.getHid();
        advisor = new Advisor();
    }

    /**
     * method to run the thread
     */
    @Override
    public void run() {
        //try for the sleep 
        try {
            int sleep = (int) ((Math.random() * 1000) + 1500);

            Thread.sleep(sleep);
        } catch (InterruptedException e) {
        }

        if (!(myHand.isBlackjack() || myHand.isBroke() || myHand.isCharlie() || myHand.getValue() == 21)) {
            //creating an advisor
            Play advise = advisor.advise(myHand, upCard);

            //change advise if it is split
            if (advise == Play.SPLIT) {
                advise = Play.HIT;
                //advise = advisor.adviseTotalOnly(myHand, upCard);
            }
            else if ((advise == Play.DOUBLE_DOWN) && (myHand.size() > 2)) {
                advise = Play.HIT;
            }

            //make the play
            if (advise != Play.HIT) {
                if (advise == Play.STAY) {
                    courier.stay(hid);
                } else if (advise == Play.DOUBLE_DOWN) {
                    hid.dubble();
                    courier.dubble(hid);
                }
            } else {
                courier.hit(hid);
                player.play(hid);
            }
        }
    }

}
