package charlie.bot.test;

import java.util.Date;
import java.util.Random;

/**
 * A class for a shoe of random cards
 * @author Daniel Cody and Brenden Bishop
 */
public class Shoe01 extends Shoe {   
    @Override
    public void init() {
        
        super.ran = new Random(new Date().getTime());
        
        super.numDecks = 1;
        
        super.load();
        
        super.shuffle();
    }
}
