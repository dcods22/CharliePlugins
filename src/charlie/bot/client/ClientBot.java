package charlie.bot.client;

import charlie.actor.Courier;
import charlie.advisor.*;
import charlie.card.Card;
import charlie.card.Hand;
import charlie.card.Hid;
import charlie.dealer.Seat;
import charlie.plugin.IGerty;
import charlie.view.AMoneyManager;
import java.awt.Graphics2D;
import java.util.List;

/**
 * Class to create a bot that will play with the dealer.  
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
    //total bet 
    protected int totalBet = 0;
    //mean bet amount
    protected double meanBet = 0.0;
    //max bet
    protected int maxBet = 0;
    //dealer upcard
    protected Card upCard;
    //bots hand
    protected Hand myHand;
    //advisor
    private Advisor advisor;
    //Counting system
    private final String betSystem = "Zen Count";
    //Old bet
    protected int oldBet = 0;
    
    @Override
    public void go() {
       
        advisor = new Advisor();
        
        //determining the bet amount
        if(trueCount < 1)
            betAmount = MIN_BET;
        else if(trueCount < 3)
            betAmount = 10;
        else if(trueCount < 6)
            betAmount = 15;
        else if(trueCount < 8)
            betAmount = 25;
        else
            betAmount = MIN_BET;
        
        
        //Keep track of the max bet
        if(betAmount > maxBet)
            maxBet = betAmount;
        
        if(handCount != 0){
            //track the total bet and mean bet
            totalBet = totalBet + betAmount;
            meanBet = totalBet / handCount;
        }
        
        if(oldBet != betAmount){
            //make the bets
            manager.clearBet();
            manager.upBet(betAmount);
        }
        
        //place the actual bet
        courier.bet(betAmount, 0);
        oldBet = betAmount;
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
        upCard = null;
    }

    @Override
    public void endGame(int shoeSize) {
        //setting the true count
        trueCount = runningCount / shoeSize;
        handCount++;
    }

    @Override
    public void deal(Hid hid, Card card, int[] values) {
        
        if(myHand == null){
            myHand = new Hand(hid);
        }
        
        //freeze at 100 games
        if(handCount == 100){
            try{
                Thread.sleep(10000000);
            }catch(InterruptedException e){
            }
        }
        
        //Zen method for counting cards
        if(card.isAce())
            runningCount--;
        else if(card.value() < 8)
            runningCount++;
        else if(card.isFace() || card.value() == 10)
            runningCount = runningCount - 2;
        else if(card.value() == 8 || card.value() == 9)
            runningCount--;
        
        //getting the dealers upcard
        if((hid.getSeat() == Seat.DEALER) && (upCard == null))
        {
            upCard = card;
        }
        
        //increasing your hand
        if(hid.getSeat() == Seat.YOU){
            myHand.hit(card);
        }
     
        //playing the hand
        if(myHand.size() >= 2){
            this.play(hid);
        }
    }

    @Override
    public void shuffling() {
        //reseting the counts
        runningCount = 0;
        trueCount = 0;
    }

    @Override
    public void play(Hid hid) {
        if(hid.getSeat() == Seat.YOU){
            ClientBotThread clientThread = new ClientBotThread(myHand, upCard, courier, this);
            new Thread(clientThread).start();
        }
    }   
    
    @Override
    public void insure() {
    
    }

    @Override
    public void bust(Hid hid) {
        if(hid.getSeat() == Seat.YOU)
            busts++;
    }

    @Override
    public void win(Hid hid) {
        if(hid.getSeat() == Seat.YOU)
            wins++;
    }

    @Override
    public void blackjack(Hid hid) {
        if(hid.getSeat() == Seat.YOU)
            blackjacks++;
    }

    @Override
    public void charlie(Hid hid) {
        if(hid.getSeat() == Seat.YOU)
            charlies++;
    }

    @Override
    public void lose(Hid hid) {
        if(hid.getSeat() == Seat.YOU)
            loses++;
    }

    @Override
    public void push(Hid hid) {
        if(hid.getSeat() == Seat.YOU)
            pushes++;
    }
}
