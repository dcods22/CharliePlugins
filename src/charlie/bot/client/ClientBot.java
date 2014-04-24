package charlie.bot.client;

import charlie.actor.Courier;
import charlie.advisor.*;
import charlie.card.Card;
import charlie.card.Hand;
import charlie.card.Hid;
import charlie.dealer.Seat;
import charlie.plugin.IGerty;
import charlie.util.Play;
import charlie.view.AMoneyManager;
import java.awt.Graphics2D;
import java.util.List;

/**
 *
 * @author Dan Cody and Brenden Bishop
 */
public class ClientBot implements IGerty{
    //money manager
    protected AMoneyManager manager;
    //courier
    protected Courier courier;
    //min bet
    private final int MIN_BET = 5;
    //number of hands
    protected int handCount = 0;
    //actual bet amount
    protected int betAmount = MIN_BET;
    //running count
    protected int runningCount = 0;
    //true count
    protected int trueCount = 0;
    //number of blackjacks
    protected int blackjacks = 0;
    //number of charlies
    protected int charlies = 0;
    //number of wins
    protected int wins = 0;
    //number of loses
    protected int loses = 0;
    //number of busts
    protected int busts = 0;
    //number of pushes
    protected int pushes = 0;
    //dealer upcard
    protected Card upCard;
    //bots hand
    protected Hand myHand;
    //advisor
    private Advisor advisor;
    
    @Override
    public void go() {
       
        if(trueCount < 3){
            betAmount = 10;
        }else if(trueCount < 6){
            betAmount = 15;
        }else if(trueCount < 8){
            betAmount = 25;
        }else{
            betAmount = MIN_BET;
        }
            
        courier.bet(betAmount, 0);
        manager.upBet(betAmount);
        
    }

    @Override
    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    @Override
    public void setMoneyManager(AMoneyManager moneyManager) {
        manager = moneyManager;
        manager.setBankroll(1000.00);  
    }

    @Override
    public void update() {
    
    }

    @Override
    public void render(Graphics2D g) {
        
    }

    @Override
    public void startGame(List<Hid> hids, int shoeSize) {
    
    }

    @Override
    public void endGame(int shoeSize) {
        trueCount = runningCount / shoeSize;
        handCount++;
    }

    @Override
    public void deal(Hid hid, Card card, int[] values) {
        
        if(handCount == 100){
            try{
                Thread.sleep(10000000);
            }catch(InterruptedException e){
            }
        }
        
        if(card.isAce())
            runningCount--;
        else if(card.value() < 8)
            runningCount++;
        else if(card.isFace() || card.value() == 10)
            runningCount = runningCount - 2;
        else if(card.value() == 8 || card.value() == 9)
            runningCount--;
        
        if(hid.getSeat() == Seat.DEALER)
        {
            this.upCard = card;
        }
        
        if(hid.getSeat() == Seat.YOU){
            myHand.hit(card);
        }
     
        if(myHand.size() == 2){
            play(hid);
        }
    }

    @Override
    public void shuffling() {
        runningCount = 0;
        trueCount = 0;
    }

    @Override
    public void play(Hid hid) {
        advisor = new Advisor();
        Play advise = advisor.advise(myHand, upCard);
        
        try{
            int sleep = (int) ((Math.random() * 1000) + 1500);

            Thread.sleep(sleep);

            if(advise != Play.HIT)
                if(advise == Play.STAY)
                    courier.hit(hid);
                else if(advise == Play.DOUBLE_DOWN){
                    hid.dubble();
                    courier.dubble(hid);
                }
            else courier.hit(hid);
         }catch(InterruptedException e){
         }
    }   
    
    @Override
    public void insure() {
    
    }

    @Override
    public void bust(Hid hid) {
        busts++;
    }

    @Override
    public void win(Hid hid) {
        wins++;
    }

    @Override
    public void blackjack(Hid hid) {
        blackjacks++;
    }

    @Override
    public void charlie(Hid hid) {
        charlies++;
    }

    @Override
    public void lose(Hid hid) {
        loses++;
    }

    @Override
    public void push(Hid hid) {
        pushes++;
    }
}
