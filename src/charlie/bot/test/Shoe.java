/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package charlie.bot.test;

import java.util.Random;

/**
 * Class to override shoe
 * @author Dan Cody and Brenden Bishop
 */
public class Shoe extends charlie.card.Shoe{
    
    //Method to overrite init
    @Override
    public void init(){
        ran = new Random(1);
        load();
        shuffle();
    }
    
}
