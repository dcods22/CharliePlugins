/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package charlie.advisor;
import charlie.card.Card;
import charlie.card.Hand;
import charlie.plugin.IAdvisor;
import charlie.util.Play;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to give advice on what Black Jack move to make
 * @author Dan Cody
 */
public class Advisor implements IAdvisor{
    
    //three maps for the advice
    Map<Integer, Play[]> pairTable;
    Map<Integer, Play[]> aceTable;
    Map<Integer, Play[]> totalTable;
    
    /**
     * empty constructor to create an instance of the Advisor
     */
    public Advisor(){
        
    }
    
    /**
     * Method to create the tables of advice for Black Jack
     * Done with a singleton design pattern to check if the tables already exist
     * so only one copy is made of each
     */
    public void createTables(){ 
        //check to make sure total table does not already exists
        if(totalTable == null){
            //create pair table
            totalTable = new HashMap<>();
            
            //fill with values
            Play[] twenty = {Play.NONE,Play.STAY,  Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY};
            Play[] nineteen= {Play.NONE,Play.STAY,  Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY};
            Play[] eightteen = {Play.NONE,Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY};
            Play[] seventeen = {Play.NONE,Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY};
            Play[] sixteen = {Play.NONE,Play.HIT,  Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
            Play[] fifteen = {Play.NONE,Play.HIT,  Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
            Play[] fourteen = {Play.NONE,Play.HIT, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
            Play[] thirteen = {Play.NONE,Play.HIT,  Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
            Play[] twelve = {Play.NONE,Play.HIT,  Play.HIT, Play.HIT, Play.STAY, Play.STAY, Play.STAY, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
            Play[] eleven = {Play.NONE,Play.HIT, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.HIT};
            Play[] ten = {Play.NONE,Play.HIT, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.HIT, Play.HIT};
            Play[] nine = {Play.NONE,Play.HIT, Play.HIT, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
            Play[] eight = {Play.NONE,Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
            Play[] seven = {Play.NONE,Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
            Play[] six = {Play.NONE,Play.HIT,  Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
            Play[] five = {Play.NONE,Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
            
            totalTable.put(5,five);
            totalTable.put(6,six);
            totalTable.put(7,seven);
            totalTable.put(8,eight);
            totalTable.put(9,nine);
            totalTable.put(10,ten);
            totalTable.put(11,eleven);
            totalTable.put(12,twelve);
            totalTable.put(13,thirteen);
            totalTable.put(14,fourteen);
            totalTable.put(15,fifteen);
            totalTable.put(16,sixteen);
            totalTable.put(17,seventeen);
            totalTable.put(18,eightteen);
            totalTable.put(19,nineteen);
            totalTable.put(20,twenty);
        }
        
        //check to make sure ace table does not already exists
        if(aceTable == null){
            //create ace table
            aceTable = new HashMap<>();
            
            //fill with values
            Play[] aTen = {Play.NONE, Play.STAY,  Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY};
            Play[] aNine = {Play.NONE,Play.HIT,  Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY};
            Play[] aEight = {Play.NONE,Play.HIT,  Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY};
            Play[] aSeven = {Play.NONE,Play.HIT, Play.STAY, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.STAY, Play.STAY, Play.HIT, Play.HIT, Play.HIT};
            Play[] aSix = {Play.NONE,Play.HIT, Play.HIT, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
            Play[] aFive = {Play.NONE,Play.HIT,  Play.HIT, Play.HIT, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
            Play[] aFour = {Play.NONE,Play.HIT, Play.HIT, Play.HIT, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
            Play[] aThree = {Play.NONE,Play.HIT,  Play.HIT, Play.HIT, Play.HIT, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
            Play[] aTwo = {Play.NONE,Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        
            aceTable.put(2,aTwo);
            aceTable.put(3,aThree);
            aceTable.put(4,aFour);    
            aceTable.put(5,aFive);
            aceTable.put(6,aSix);
            aceTable.put(7,aSeven);
            aceTable.put(8,aEight);
            aceTable.put(9,aNine);
            aceTable.put(10,aTen);
        }
        
        //check to make sure pair table does not already exist
        if(pairTable == null){
            //create total Table
            pairTable = new HashMap<>();
            
            //fill with values            
            Play[] cAce = {Play.NONE, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT};
            Play[] cTen = {Play.NONE, Play.STAY,  Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY};
            Play[] cNine = {Play.NONE, Play.STAY, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.STAY, Play.SPLIT, Play.SPLIT, Play.STAY, Play.STAY};
            Play[] cEight = {Play.NONE, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT};
            Play[] cSeven = {Play.NONE, Play.HIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
            Play[] cSix = {Play.NONE, Play.HIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
            Play[] cFive = {Play.NONE, Play.HIT, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.HIT, Play.HIT};
            Play[] cFour = {Play.NONE, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.SPLIT, Play.SPLIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
            Play[] cThree = {Play.NONE, Play.HIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
            Play[] cTwo = {Play.NONE, Play.HIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        
            pairTable.put(1,cAce);
            pairTable.put(2,cTwo);
            pairTable.put(3,cThree);
            pairTable.put(4,cFour);    
            pairTable.put(5,cFive);
            pairTable.put(6,cSix);
            pairTable.put(7,cSeven);
            pairTable.put(8,cEight);
            pairTable.put(9,cNine);
            pairTable.put(10,cTen);
            pairTable.put(11,cAce);
        }        
    }
   
    /**
     * Method to return the advice on which Black Jack move to make
     * @param myHand hand you currently have
     * @param upCard the dealers upcard
     * @return Play enum of which move to make
     */
    @Override
    public Play advise(Hand myHand, Card upCard){
        //check to make sure tables are created
        createTables();
        //initial values of card sin the hand
        Card cardOne = myHand.getCard(0);
        Card cardTwo = myHand.getCard(1);
        //dealer card value
        int secondValue = upCard.value();
        //array of the advices
        Play[] playArray;
        //the actual advice
        Play advice;
        
        if(myHand.size() == 2){
            if(myHand.isPair()){
                //lookup is value since both cards are the same
                //pair hash table
                Integer value = cardOne.value();
                playArray = pairTable.get(value);
                advice = playArray[secondValue];
            }
            else if(cardOne.isAce()){
                //ace hash table
                //key is value because its other card besides ace
                Integer value = cardTwo.value();
                playArray = aceTable.get(value);
                advice = playArray[secondValue];        
            }
            else if(cardTwo.isAce()){
                //ace hash table
                //key is value because its other card besides ace
                Integer value = cardOne.value();
                playArray = aceTable.get(value);
                advice = playArray[secondValue];         
            }else{
                //total number hash table
                //key is total since its the amount
                Integer total = cardOne.value() + cardTwo.value();
                playArray = totalTable.get(total);
                advice = playArray[secondValue];
            }   
        }else if(myHand.size() == 3){
            //get next card value
            Card cardThree = myHand.getCard(2);
            //find advice based on total
            Integer total = cardOne.value() + cardTwo.value() + cardThree.value();
            playArray = totalTable.get(total);
            advice = playArray[secondValue];
        }else if(myHand.size() == 4){
            //get next two card values
            Card cardThree = myHand.getCard(2);
            Card cardFour = myHand.getCard(3);
            //find advice based on total
            Integer total = cardOne.value() + cardTwo.value() + cardThree.value() + cardFour.value();
            playArray = totalTable.get(total);
            advice = playArray[secondValue];
        }else if(myHand.size() == 5){
            //get next three card values
            Card cardThree = myHand.getCard(2);
            Card cardFour = myHand.getCard(3);
            Card cardFive = myHand.getCard(4);
            //find advice based on total
            Integer total = cardOne.value() + cardTwo.value() + cardThree.value() + cardFour.value()  + cardFive.value();
            playArray = totalTable.get(total);
            advice = playArray[secondValue];
        }else{
            //extra case just incase of 6 card charlie and advice
            Card cardThree = myHand.getCard(2);
            Card cardFour = myHand.getCard(3);
            Card cardFive = myHand.getCard(4);
            Card cardSix= myHand.getCard(5);
            //get advice based on totals
            Integer total = cardOne.value() + cardTwo.value() + cardThree.value() + cardFour.value()  + cardFive.value() + cardSix.value();
            playArray = totalTable.get(total);
            advice = playArray[secondValue];
        }
           
        //return the advice
        return advice;
    };
    
}
