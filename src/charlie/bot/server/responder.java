/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package charlie.bot.server;

import charlie.card.Card;
import charlie.card.Hand;
import charlie.dealer.Dealer;

/**
 *
 * @author Dan
 */
public class responder {
    
    Hand myHand;
    Card upCard;
    Dealer dealer;
    
    public responder(Hand myHand, Card upCard, Dealer dealer){
        this.myHand = myHand;
        this.upCard = upCard;
        this.dealer = dealer;
    }
}
