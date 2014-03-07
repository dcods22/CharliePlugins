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
 *
 * @author Dan
 */
public class N6 implements IBot{
    Responder responder;
    Hand myHand;
    Dealer dealer;
    Card upCard;
    Hid hid;
    Seat seat;
    int runningCount;
    int trueCount;
    double betsize;
    
    public N6(){ 
        runningCount = 0;
        trueCount = 0;
    }
    
    @Override
    public Hand getHand() {
        hid = new Hid(seat);
        return (myHand = new Hand(hid));  
    }
    
    @Override
    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    @Override
    public void sit(Seat seat) {
        this.seat = seat;
    }

    @Override
    public void startGame(List<Hid> hids, int shoeSize) {
        betsize = 5.0;
        hid.setAmt(betsize);
    }

    @Override
    public void endGame(int shoeSize) {
        trueCount = (runningCount / shoeSize);
    }

    @Override
    public void deal(Hid hid, Card card, int[] values) {
        if(hid.getSeat() == Seat.DEALER)
            upCard = card;
       // else if(this.hid.getSeat() == hid.getSeat())
         //   myHand.hit(card);
        
        int cardValue = values[0];
        
        if(cardValue > 10)
            runningCount--;
        else if(cardValue < 7)
            runningCount++;
    }

    @Override
    public void insure() {
    }

    @Override
    public void bust(Hid hid) {
        if(this.hid.getSeat() == hid.getSeat())
            this.lose(hid);
    }

    @Override
    public void blackjack(Hid hid) {
       if(this.hid.getSeat() == hid.getSeat())
           this.win(hid);
    }

    @Override
    public void charlie(Hid hid) {
        if(this.hid.getSeat() == hid.getSeat())
            this.win(hid);
    }

    @Override
    public void win(Hid hid) {
        //if(this.hid.getSeat() == hid.getSeat())
            
    }
    
    @Override
    public void lose(Hid hid) {
        //if(this.hid.getSeat() == hid.getSeat())
    }

    @Override
    public void push(Hid hid) {
        //if(this.hid.getSeat() == hid.getSeat())
    }

    @Override
    public void shuffling() {
        runningCount = 0;
        trueCount = 0;
    }

    @Override
    public void play(Hid hid) {  
            if(this.hid.getSeat() == hid.getSeat()){
                responder = new Responder(myHand, upCard, dealer, this);
                new Thread(responder).start();
            }
    }    
}
