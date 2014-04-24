/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clientBot;

import charlie.actor.Courier;
import charlie.card.Card;
import charlie.card.Hid;
import charlie.plugin.IGerty;
import charlie.view.AMoneyManager;
import java.awt.Graphics2D;
import java.util.List;

/**
 *
 * @author Dan
 */
public class clientBot implements IGerty{
    
    private final int MIN_BET = 5;
    protected int handCount;
    protected int betAmount;
    protected int runningCount;
    protected int trueCount;

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
    
    }

    @Override
    public void deal(Hid hid, Card card, int[] values) {
     
    }

    @Override
    public void insure() {
    
    }

    @Override
    public void bust(Hid hid) {
    
    }

    @Override
    public void win(Hid hid) {
    
    }

    @Override
    public void blackjack(Hid hid) {
    
    }

    @Override
    public void charlie(Hid hid) {
    
    }

    @Override
    public void lose(Hid hid) {
    
    }

    @Override
    public void push(Hid hid) {
    
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
