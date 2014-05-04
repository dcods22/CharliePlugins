package charlie.bot.client;

import charlie.actor.Courier;
import charlie.advisor.*;
import charlie.card.Card;
import charlie.card.Hand;
import charlie.card.Hid;
import charlie.dealer.Seat;
import charlie.plugin.IGerty;
import charlie.view.AMoneyManager;
import java.awt.Color;
import java.awt.Font;
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
    protected double trueCount = 0;
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
    private final String betSystem = "Omega 2";
    //Old bet
    protected int oldBet = 0;
    //Decks in shoe
    protected double shoeDecks = 0.0;
    
    //which count
    protected boolean zen = false;
    protected boolean omega = true;
    
    
    //Renderings
    //Font 
    protected Font font = new Font("Arial", Font.BOLD, 12);
    //Position to render
    public final static int X = 20;
    public final static int Y = 200;
    
    /**
     * Method to start the client bot
     */
    @Override
    public void go() {
       //create an advisor
        advisor = new Advisor();
        
        //determining the bet amount based on true count
        betAmount = getBetAmount();
        
        if(betAmount > manager.getBankroll())
            betAmount = (int) manager.getBankroll();
        
        //Keep track of the max bet
        if(betAmount > maxBet)
            maxBet = betAmount;
        
        
        //track the total bet and mean bet
        totalBet = totalBet + betAmount;
        meanBet =  (double) totalBet / ((double) handCount + 1.0);
        
        
        makeBet(betAmount);
    }

    /**
     * method to set the courier
     * @param courier the courier to set
     */
    @Override
    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    /**
     * method to set the money manager
     * @param moneyManager the money manager to set
     */
    @Override
    public void setMoneyManager(AMoneyManager moneyManager) {
        manager = moneyManager;
        manager.setBankroll(1000.00);  
    }

    /**
     * Method to update the view
     */
    @Override
    public void update() {
    
    }

    /**
     * Method to render the view
     */
    @Override
    public void render(Graphics2D g) {
        //Strings to render
        String hand         = "Hand Count: " + handCount;
        String charlie      = "Charlies: " + charlies;
        String win          = "Wins: " + wins;
        String lose         = "Losses: " + loses;
        String push         = "Pushes: " + pushes;
        String bust         = "Busts: " + busts;
        String blackjack    = "Blackjacks: " + blackjacks;
        String runCount     = "Running Count: " + runningCount;
        String truCount     = "TrueCount: " + ((Math.round(trueCount * 100.0)) / 100.0);
        String maxBetStr    = "Max Bet: " + maxBet;
        String meanBetStr   = "Mean Bet: " + ((Math.round(meanBet * 100.0)) / 100.0);
        String shoeDeck     = "Shoe Decks: " + ((Math.round(shoeDecks * 100.0)) / 100.0);
        
        //Draw the Side Bets and Amounts
        g.setFont(font);
        g.setColor(Color.black);
        
        //Draw Strings
        g.drawString(betSystem, X,Y);
        g.drawString(shoeDeck, X,Y +15);
        g.drawString(maxBetStr, X, Y + 30);
        g.drawString(meanBetStr, X, Y + 45);
        g.drawString(hand, X, Y + 60);
        g.drawString(win, X, Y+75);
        g.drawString(lose, X, Y+90);
        g.drawString(bust, X, Y+105);
        g.drawString(push, X, Y+120);
        g.drawString(blackjack, X, Y+135);
        g.drawString(charlie, X, Y+150);
        g.drawString(runCount, X,Y+165);
        
        if(zen)
            g.drawString(truCount, X, Y+180);
        
    }

    /**
     * Method to say a new game has started
     * @param hids the HID's in use
     * @param shoeSize the shoe size
     */
    @Override
    public void startGame(List<Hid> hids, int shoeSize) {
        //setting the true count
        shoeDecks = ((double) shoeSize) / 52.0;
        
        upCard = null;
        myHand = null;
    }

    /**
     * method to say the game has ended
     * @param shoeSize the shoe size
     */
    @Override
    public void endGame(int shoeSize) {
        //setting the true count
        shoeDecks = ((double) shoeSize) / 52.0;
        
        //figuring out the true count
        trueCount = ((double) runningCount) / shoeDecks;
        handCount++;
    }

    /**
     * Method to say that a card is dealt
     * @param hid the hid the card is dealt to
     * @param card the card object dealt
     * @param values the values of the cards
     */
    @Override
    public void deal(Hid hid, Card card, int[] values) {
        
        //create a hand
        if(hid.getSeat() == Seat.YOU){
            if(myHand == null){
                myHand = new Hand(hid);
            }
        }
        
        //freeze at 100 games
        if(handCount == 100){
            try{
                Thread.sleep(10000000);
            }catch(InterruptedException e){
            }
        }
        
        //zenCount(card);
        omegaTwo(card);
        
        
        //getting the dealers upcard
        if((hid.getSeat() == Seat.DEALER) && (upCard == null))
        {
            upCard = card;
        }
        
        //increasing your hand
        if(hid.getSeat() == Seat.YOU){
            myHand.hit(card);
        }
    }

    /**
     * Method to say shuffling has happened
     */
    @Override
    public void shuffling() {
        //reseting the counts
        runningCount = 0;
        trueCount = 0;
        betAmount = MIN_BET;
    }

    /**
     * Method to make a play
     * @param hid the hid which needs to make a play
     */
    @Override
    public void play(Hid hid) {
        //create and start the thread 
       if(hid.getSeat() == Seat.YOU){
            ClientBotThread clientThread = new ClientBotThread(myHand, upCard, courier, this);
            new Thread(clientThread).start();
        }
    }   
    
    /**
     * Method to insure the bet
     */
    @Override
    public void insure() {
    
    }

    /**
     * method to say an HID has busted
     * @param hid the hid which busted
     */
    @Override
    public void bust(Hid hid) {
        if(hid.getSeat() == Seat.YOU)
            busts++;
    }

    /**
     * method to say an HID has won
     * @param hid the hid which won
     */
    @Override
    public void win(Hid hid) {
        if(hid.getSeat() == Seat.YOU)
            wins++;
    }

    /**
     * method to say an HID has gotten blackjack
     * @param hid the hid which has gotten blackjack
     */
    @Override
    public void blackjack(Hid hid) {
        if(hid.getSeat() == Seat.YOU)
            blackjacks++;
    }

    /**
     * method to say an HID has gotten a charlie
     * @param hid the hid which has gotten a charlie
     */
    @Override
    public void charlie(Hid hid) {
        if(hid.getSeat() == Seat.YOU)
            charlies++;
    }

    /**
     * method to say an HID has lost
     * @param hid the hid which lost
     */
    @Override
    public void lose(Hid hid) {
        if(hid.getSeat() == Seat.YOU)
            loses++;
    }

    /**
     * method to say an HID has pushed
     * @param hid the hid which pushed
     */
    @Override
    public void push(Hid hid) {
        if(hid.getSeat() == Seat.YOU)
            pushes++;
    }
    
    /**
     * method to adjust zen count
     */
    private void zenCount(Card card){
        //Zen method for counting cards
        if(card.isAce())
            runningCount--;
        else if(card.value() < 8)
            runningCount++;
        else if(card.isFace() || card.value() == 10)
            runningCount = runningCount - 2;
        else if(card.value() == 8 || card.value() == 9)
            runningCount--;
        
    }
    
    /**
     * method to adjust Omega 2 Count
     */
    private void omegaTwo(Card card){
        //Omega two method for counting cards
        if(card.isAce())
            runningCount = runningCount + 0;
        else if(card.value() < 4)
            runningCount++;
        else if(card.value() > 3 && card.value() < 7)
            runningCount = runningCount + 2;
        else if(card.value() == 7)
            runningCount++;
        else if(card.value() == 8)
            runningCount = runningCount + 0;
        else if(card.value() == 9)
            runningCount--;
        else if(card.isFace() || card.value() == 10)
            runningCount = runningCount - 2;
        
    }
    
    private int getBetAmount(){
        if(zen){
            if(trueCount > 0)
                return (int) (25 * trueCount);
            else if(trueCount == -1 || trueCount == 0)
                return 10;
            else 
                return MIN_BET;
        }else if(omega){
            if(runningCount > 0)
                return (25 * runningCount);
            else if(runningCount == -1 || runningCount == 0)
                return 10;
            else 
                return MIN_BET;
        }
        return MIN_BET;
    }
    
    private void makeBet(int betAmount){
        
        //Check if bet changed
        if(oldBet != betAmount){
            //make the bets
            manager.clearBet();
            
            //While loop to bet the right amount of chips
            while(betAmount > 0){
                if(betAmount > 100){
                    manager.upBet(100);
                    betAmount = betAmount - 100;
                }
                else if(betAmount > 25){
                    manager.upBet(25);
                    betAmount = betAmount - 25;
                }
                else{
                    manager.upBet(5);
                    betAmount = betAmount - 5;
                }
            }
        }
        
        //place the actual bet
        courier.bet(betAmount, 0);
        oldBet = betAmount;
    }
}
