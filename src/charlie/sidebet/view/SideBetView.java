/*
 Copyright (c) 2014 Ron Coleman

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package charlie.sidebet.view;

import charlie.audio.Effect;
import charlie.card.Hid;
import charlie.plugin.ISideBetView;
import charlie.view.AMoneyManager;
import charlie.audio.SoundFactory;
import charlie.view.AHand.Outcome;
import charlie.view.sprite.Chip;
import charlie.view.sprite.AtStakeSprite;
import charlie.view.sprite.ChipButton;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements the side bet view
 * @author Ron Coleman, Ph.D.
 */
public class SideBetView implements ISideBetView {
    private final Logger LOG = LoggerFactory.getLogger(SideBetView.class);
    
    public final static int X = 400;
    public final static int Y = 200;
    public final static int DIAMETER = 50;
    
    protected List<Chip> chips = new ArrayList<>();
    // Corresponding chips equal to the stake
    public final static int PLACE_HOME_X = X + AtStakeSprite.DIAMETER + 10;
    public final static int PLACE_HOME_Y = Y + AtStakeSprite.DIAMETER / 4;
    protected Random ran = new Random();
    protected Integer[] amounts = { 100, 25, 5 };
    
    protected Font font = new Font("Arial", Font.BOLD, 18);
    protected BasicStroke stroke = new BasicStroke(3);
    
    // See http://docs.oracle.com/javase/tutorial/2d/geometry/strokeandfill.html
    protected float dash1[] = {10.0f};
    protected BasicStroke dashed
            = new BasicStroke(3.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    10.0f, dash1, 0.0f);   

    protected List<ChipButton> buttons;
    protected int amt = 0;
    protected AMoneyManager moneyManager;

    protected Outcome outcome = Outcome.None;
    protected Color looseColorBg = new Color(250,58,5);
    protected Color looseColorFg = Color.WHITE;
    protected Color winColorFg = Color.BLACK;
    protected Color winColorBg = new Color(116,255,4);
    protected Font outcomeFont = new Font("Arial", Font.BOLD, 18);
    
    public SideBetView() {
        LOG.info("side bet view constructed");
    }
    
    /**
     * Sets the money manager.
     * @param moneyManager 
     */
    @Override
    public void setMoneyManager(AMoneyManager moneyManager) {
        this.moneyManager = moneyManager;
        this.buttons = moneyManager.getButtons();
    }
    
    /**
     * Registers a click for the side bet.
     * @param x X coordinate
     * @param y Y coordinate
     */
    @Override
    public void click(int x, int y) {
        int oldAmt = amt;
        
        // Test if any chip button has been pressed.
        for(ChipButton button: buttons) {
            if(button.isPressed(x, y)) {
                //update amount
                amt += button.getAmt();
                //log the infor
                LOG.info("A. side bet amount "+button.getAmt()+" updated new amt = "+amt);
                //see how many chips are present
                int n = chips.size();
                
                //find place to put the chip
                int placeX = PLACE_HOME_X + n * 8 + ran.nextInt(10)-10;
                
                int placeY = PLACE_HOME_Y + ran.nextInt(5)-5;
                
                //create a new one
                Chip chip = new Chip(button.getImage(),placeX,placeY,button.getAmt());
                
                //add chip and play sound
                chips.add(chip);
                SoundFactory.play(Effect.CHIPS_IN);
            } 
        }
        
        //check to see if amount has been clicked to clear it out
        if(((x >= (X - DIAMETER)) && (y >= (Y - DIAMETER))) && (x <= (X + DIAMETER)) && (y <=(Y + DIAMETER))){
            amt = 0;
            chips.clear();
            LOG.info("B. side bet amount cleared");
            SoundFactory.play(Effect.CHIPS_OUT);
        }
    }

    /**
     * Informs view the game is over and it's time to update the bankroll for the hand.
     * @param hid Hand id
     */
    @Override
    public void ending(Hid hid) {
        double bet = hid.getSideAmt();
        
        if(bet == 0)
            return;

        LOG.info("side bet outcome = "+bet);
        
        // Update the bankroll
        moneyManager.increase(bet);
        
        //change the outcome field based on outcome
        if(bet > 0){
            outcome = Outcome.Win;
        }else if(bet < 0){
            outcome = Outcome.Lose;
        }
        
        LOG.info("new bankroll = "+moneyManager.getBankroll());
    }

    /**
     * Informs view the game is starting
     */
    @Override
    public void starting() {
        //set the output to none
        outcome = Outcome.None;
    }

    /**
     * Gets the side bet amount.
     * @return Bet amount
     */
    @Override
    public Integer getAmt() {
        return amt;
    }

    /**
     * Updates the view
     */
    @Override
    public void update() {
    }

    /**
     * Renders the view
     * @param g Graphics context
     */
    @Override
    public void render(Graphics2D g) {
        
        //Strings to render on screen
        String super7 = "SUPER 7 Pays 3:1";
        String royalMatch = "ROYAL MATCH Pays 25:1";
        String exactly13 = "EXACTLY 13 Pays 1:1";
        
        // Draw the at-stake place on the table
        g.setColor(Color.RED); 
        g.setStroke(dashed);
        g.drawOval(X-DIAMETER/2, Y-DIAMETER/2, DIAMETER, DIAMETER);
        
        // Draw the at-stake amount
        g.setFont(font);
        g.setColor(Color.WHITE);
        
        //make sure the output is centered
        if (amt < 10) {
            g.drawString("" + amt, X - 5, Y + 5);
        } else {
            if (amt < 100) {
                g.drawString("" + amt, X - 10, Y + 5);
            } else {
                if (amt > 100) {
                    g.drawString("" + amt, X - 15, Y + 5);
                } else {
                    if(amt > 1000){
                    g.drawString("" + amt, X - 20, Y + 5);
                    }
                }
            }
        }   
        
        //Draw the Side Bets and Amounts
        g.setFont(font);
        g.setColor(Color.black);
        //Draw the strings
        g.drawString(super7, X+40, Y-60);
        g.drawString(royalMatch, X+40, Y-40);
        g.drawString(exactly13, X+40, Y-20);
        
        //render all the chips
        for(int i=0; i < chips.size(); i++) {
            Chip chip = chips.get(i);
            chip.render(g);
        }
        
        //render the 
        renderOutcome(g);
    }
    
    protected void renderOutcome(Graphics2D g) {
        //position to draw outcome text
        int drawX = X + 25;
        int drawY = Y + 2;
        
        String outcomeString = "";
        
        //setting the outcome string
        if(outcome == Outcome.None){
        }else if(outcome == Outcome.Win){
            outcomeString = " WIN ! ";
        }else if(outcome == Outcome.Lose){
            outcomeString = " LOSE ! ";
        }
        
        //figuring the distance to draw the output
        FontMetrics fm = g.getFontMetrics(outcomeFont);
            int w = fm.charsWidth(outcomeString.toCharArray(), 0, outcomeString.length());
            int h = fm.getHeight();
            
        //draw the outcome
        if(outcome == Outcome.None) {
        } else if(outcome == Outcome.Lose){
            //render loss
            g.setColor(looseColorBg);
            g.fillRoundRect(drawX, drawY-h+5, w, h, 5, 5);
            g.setColor(looseColorFg);
            g.setFont(outcomeFont);
            g.drawString(outcomeString, drawX, drawY);
        }else if(outcome == Outcome.Win){
            //render win
            g.setColor(winColorBg);
            g.fillRoundRect(drawX, drawY-h+5, w, h, 5, 5);
            g.setColor(winColorFg);
            g.setFont(outcomeFont);
            g.drawString(outcomeString, drawX, drawY);
        }
        
    }
}
