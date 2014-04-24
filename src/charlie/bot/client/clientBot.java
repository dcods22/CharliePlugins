package charlie.bot.client;

import charlie.actor.Courier;
import charlie.card.Card;
import charlie.card.Hid;
import charlie.plugin.IGerty;
import charlie.view.AMoneyManager;
import java.awt.Graphics2D;
import java.util.List;

/**
 *
 * @author Dan Cody and Brenden Bishop
 */
public class clientBot implements IGerty{
    
    private final int MIN_BET = 5;
    protected int handCount = 0;
    protected int betAmount = MIN_BET;
    protected int runningCount = 0;
    protected int trueCount = 0;
    protected int blackjacks = 0;
    protected int charlies = 0;
    protected int wins = 0;
    protected int loses = 0;
    protected int busts = 0;
    protected int pushes = 0;
    
    
    @Override
    public void go() {
    
    }

    @Override
    public void setCourier(Courier courier) {
    
    }

    @Override
    public void setMoneyManager(AMoneyManager moneyManager) {
    
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

    @Override
    public void shuffling() {
        runningCount = 0;
        trueCount = 0;
    }

    @Override
    public void play(Hid hid) {
    
    }
    
}
