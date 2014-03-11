/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package charlie.bot.server;
import charlie.card.Card;
import charlie.card.Hand;
import charlie.card.Hid;
import charlie.dealer.Dealer;
import charlie.dealer.Seat;
import charlie.plugin.IBot;
import java.util.List;

/**
 * Class to make the B9 bot
 * @author Dan Cody and Brenden Bishop
 */
public class B9 implements IBot{
    //Hand of the bot
    Hand myHand;
    //dealer of the bot
    Dealer dealer;
    //upcard of the dealer
    Card upCard;
    //hid of the bot
    Hid hid;
    //seat of the bot
    Seat seat;
    //current running count
    int runningCount;
    //current true count
    int trueCount;
    //current bet size
    double betsize;
    
    public B9(){ 
        runningCount = 0;
        trueCount = 0;
    }
    
    /**
     * Method to get the hand of the bot
     * @return Hand of the bot
     */
    @Override
    public Hand getHand() {
        hid = new Hid(seat);
        return (myHand = new Hand(hid));  
    }

    /**
     * Method to get the dealer
     * @param dealer the dealer of the game
     */
    @Override
    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    /**
     * Method to set the seat of the bot
     * @param seat the seat of which the bot is in
     */
    @Override
    public void sit(Seat seat) {
        this.seat = seat;
    }

    /**
     * Method to start the game
     * @param hids HID of each player
     * @param shoeSize shoe size on the table
     */
    @Override
    public void startGame(List<Hid> hids, int shoeSize) {
        betsize = 5.0;
        hid.setAmt(betsize);
    }
    
    /**
     * Method to say the game has ended
     * @param shoeSize the amount of decks left in the shoe
     */
    @Override
    public void endGame(int shoeSize) {
        trueCount = (runningCount / (shoeSize / 52));
    }

    /**
     * Method to say a card has been delt
     * @param hid the hid to which the card has been delt
     * @param card the card of which is delt
     * @param values the value of the card
     */
    @Override
    public void deal(Hid hid, Card card, int[] values) {
        //if its delt to the dealer then it is the upcard
        if(hid.getSeat() == Seat.DEALER)
            upCard = card;
       
        //if I have been delt enter responder thread
        if(this.hid.getSeat() == hid.getSeat()){
            if(myHand.size() > 2){
                Responder responder = new Responder(myHand, upCard, dealer, this);
                new Thread(responder).start();
            }
           //myHand.hit(card);
        }
        
        int cardValue = values[0];
        
        if(cardValue > 10)
            runningCount--;
        else if(cardValue < 7)
            runningCount++;
    }

    /**
     * method to get insurance
     */
    @Override
    public void insure() {
    }

    /**
     * Method to say the player has busted
     * @param hid hid of the player
     */
    @Override
    public void bust(Hid hid) {
        if(this.hid.getSeat() == hid.getSeat())
            this.lose(hid);
    }

    /**
     * Method to say that the player has got blackjack
     * @param hid hid of the player
     */
    @Override
    public void blackjack(Hid hid) {
       if(this.hid.getSeat() == hid.getSeat())
           this.win(hid);
    }
    
    /**
     * Method to say that the player has got a charlie
     * @param hid hid of the player
     */
    @Override
    public void charlie(Hid hid) {
        if(this.hid.getSeat() == hid.getSeat())
            this.win(hid);
    }

    /**
     * Method to say that the player has won
     * @param hid hid of the player
     */
    @Override
    public void win(Hid hid) {
        //if(this.hid.getSeat() == hid.getSeat())
            
    }
    
    /**
     * Method to say that the player lost
     * @param hid hid of the player
     */
    @Override
    public void lose(Hid hid) {
        //if(this.hid.getSeat() == hid.getSeat())
    }

    /**
     * Method to say that the player has pushed
     * @param hid hid of the player
     */
    @Override
    public void push(Hid hid) {
        //if(this.hid.getSeat() == hid.getSeat())
    }

    /**
     * Method to state the dealer is shuffling
     */
    @Override
    public void shuffling() {
        runningCount = 0;
        trueCount = 0;
    }

    /**
     * Method to say that a play has been requested
     * @param hid hid of the player to which needs to play
     */
    @Override
    public void play(Hid hid) {  
        //if its my play enter responder thread
        if(this.hid.getSeat() == hid.getSeat()){
            Responder responder = new Responder(myHand, upCard, dealer, this);
            new Thread(responder).start();
        }
    }
    
}
