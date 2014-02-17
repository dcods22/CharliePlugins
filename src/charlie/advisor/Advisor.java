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
import java.util.Hashtable;

/**
 * Class to give advice on what Black Jack move to make
 * @author Dan
 */


public class Advisor implements IAdvisor{
    Hashtable<Integer, Play[]> pairTable;
    Hashtable<Integer, Play[]> aceTable;
    Hashtable<Integer, Play[]> totalTable;
    /*
    *   hashtable of arrayLists where arrayList 
    *   index is based on dealers upCard
    */
    
    public void createTables(){ 
        if(pairTable == null){
            //create pair table
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
            
            pairTable.put(5,five);
            pairTable.put(6,six);
            pairTable.put(7,seven);
            pairTable.put(8,eight);
            pairTable.put(9,nine);
            pairTable.put(10,ten);
            pairTable.put(11,eleven);
            pairTable.put(12,twelve);
            pairTable.put(13,thirteen);
            pairTable.put(14,fourteen);
            pairTable.put(15,fifteen);
            pairTable.put(16,sixteen);
            pairTable.put(17,seventeen);
            pairTable.put(18,eightteen);
            pairTable.put(19,nineteen);
            pairTable.put(20,twenty);
        }
        
        if(aceTable == null){
            //create ace table
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
        
        if(totalTable == null){
            //create total Table
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
        
            aceTable.put(1,cAce);
            aceTable.put(2,cTwo);
            aceTable.put(3,cThree);
            aceTable.put(4,cFour);    
            aceTable.put(5,cFive);
            aceTable.put(6,cSix);
            aceTable.put(7,cSeven);
            aceTable.put(8,cEight);
            aceTable.put(9,cNine);
            aceTable.put(10,cTen);
            aceTable.put(11,cAce);
        }        
    }
   
    @Override
    public Play advise(Hand myHand, Card upCard){
        createTables();
        Card cardOne = myHand.getCard(0);
        Card cardTwo = myHand.getCard(1);
        int secondValue = upCard.value();
        Play[] playArray;
        Play advice;
        
        if(myHand.isPair()){
            //lookup is value since both cards are the same
            //pair hash table
            
            int value = cardOne.value();
            playArray = pairTable.get(value);
            advice = playArray[secondValue];
        }
        else if(cardOne.isAce()){
            //ace hash table
            //key is value because its other card besides ace
            int value = cardTwo.value();
            playArray = aceTable.get(value);
            advice = playArray[secondValue];        
        }
        else if(cardTwo.isAce()){
            //ace hash table
            //key is value because its other card besides ace
            int value = cardOne.value();
            playArray = aceTable.get(value);
            advice = playArray[secondValue];         
        }else{
            //total number hash table
            //key is total since its the amount
            int total = cardOne.value() + cardTwo.value();
            playArray = totalTable.get(total);
            advice = playArray[secondValue];
        }
            
        return advice;
    };
    
}
