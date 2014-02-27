/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.advisor;

import charlie.util.Play;
import java.util.HashMap;
import java.util.Map;

import charlie.card.Card;
import charlie.card.Hand;

/**
 *
 * @author Brenden
 */
public class BasicStratergy {

    protected Map<Integer, Play[]> pairTable;
    protected Map<Integer, Play[]> aceTable;
    protected Map<Integer, Play[]> totalTable;

    public BasicStratergy() {
        BasicStratergyPrivate();
    }

    private void BasicStratergyPrivate() {
        if (pairTable == null) {
            createPairTable();
        }
        if (aceTable == null) {
            createAceTable();
        }
        if (totalTable == null) {
            createTotalTable();
        }

    }

    public void createTotalTable() {
        totalTable = new HashMap<>();
        //create pair table

        //fill with values
        Play[] twenty = {Play.NONE, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY};
        Play[] nineteen = {Play.NONE, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY};
        Play[] eightteen = {Play.NONE, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY};
        Play[] seventeen = {Play.NONE, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY};
        Play[] sixteen = {Play.NONE, Play.HIT, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        Play[] fifteen = {Play.NONE, Play.HIT, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        Play[] fourteen = {Play.NONE, Play.HIT, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        Play[] thirteen = {Play.NONE, Play.HIT, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        Play[] twelve = {Play.NONE, Play.HIT, Play.HIT, Play.HIT, Play.STAY, Play.STAY, Play.STAY, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        Play[] eleven = {Play.NONE, Play.HIT, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.HIT};
        Play[] ten = {Play.NONE, Play.HIT, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.HIT, Play.HIT};
        Play[] nine = {Play.NONE, Play.HIT, Play.HIT, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        Play[] eight = {Play.NONE, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        Play[] seven = {Play.NONE, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        Play[] six = {Play.NONE, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        Play[] five = {Play.NONE, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};

        totalTable.put(5, five);
        totalTable.put(6, six);
        totalTable.put(7, seven);
        totalTable.put(8, eight);
        totalTable.put(9, nine);
        totalTable.put(10, ten);
        totalTable.put(11, eleven);
        totalTable.put(12, twelve);
        totalTable.put(13, thirteen);
        totalTable.put(14, fourteen);
        totalTable.put(15, fifteen);
        totalTable.put(16, sixteen);
        totalTable.put(17, seventeen);
        totalTable.put(18, eightteen);
        totalTable.put(19, nineteen);
        totalTable.put(20, twenty);
    }

    public void createAceTable() {
        //create ace table
        aceTable = new HashMap<>();

        //fill with values
        Play[] aTen = {Play.NONE, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY};
        Play[] aNine = {Play.NONE, Play.HIT, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY};
        Play[] aEight = {Play.NONE, Play.HIT, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY};
        Play[] aSeven = {Play.NONE, Play.HIT, Play.STAY, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.STAY, Play.STAY, Play.HIT, Play.HIT, Play.HIT};
        Play[] aSix = {Play.NONE, Play.HIT, Play.HIT, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        Play[] aFive = {Play.NONE, Play.HIT, Play.HIT, Play.HIT, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        Play[] aFour = {Play.NONE, Play.HIT, Play.HIT, Play.HIT, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        Play[] aThree = {Play.NONE, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        Play[] aTwo = {Play.NONE, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};

        aceTable.put(2, aTwo);
        aceTable.put(3, aThree);
        aceTable.put(4, aFour);
        aceTable.put(5, aFive);
        aceTable.put(6, aSix);
        aceTable.put(7, aSeven);
        aceTable.put(8, aEight);
        aceTable.put(9, aNine);
        aceTable.put(10, aTen);
    }

    public void createPairTable() {
        //create total Table
        pairTable = new HashMap<>();

        //fill with values            
        Play[] cAce = {Play.NONE, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT};
        Play[] cTen = {Play.NONE, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY, Play.STAY};
        Play[] cNine = {Play.NONE, Play.STAY, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.STAY, Play.SPLIT, Play.SPLIT, Play.STAY, Play.STAY};
        Play[] cEight = {Play.NONE, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT};
        Play[] cSeven = {Play.NONE, Play.HIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        Play[] cSix = {Play.NONE, Play.HIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        Play[] cFive = {Play.NONE, Play.HIT, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.DOUBLE_DOWN, Play.HIT, Play.HIT};
        Play[] cFour = {Play.NONE, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.SPLIT, Play.SPLIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        Play[] cThree = {Play.NONE, Play.HIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};
        Play[] cTwo = {Play.NONE, Play.HIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.SPLIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT};

        pairTable.put(1, cAce);
        pairTable.put(2, cTwo);
        pairTable.put(3, cThree);
        pairTable.put(4, cFour);
        pairTable.put(5, cFive);
        pairTable.put(6, cSix);
        pairTable.put(7, cSeven);
        pairTable.put(8, cEight);
        pairTable.put(9, cNine);
        pairTable.put(10, cTen);
        pairTable.put(11, cAce);
    }
    
    public Play advise(Hand myHand, Card upCard){
        //initial values of card sin the hand
        Card cardOne = myHand.getCard(0);
        Card cardTwo = myHand.getCard(1);
        //dealer card value
        int secondValue = upCard.value();
        //array of the advices
        Play[] playArray;
        //the actual advice
        Play advice;
        
        if(myHand.size() > 2)
        {
            Integer total = myHand.getValue();
            playArray = totalTable.get(total);
            advice = playArray[secondValue];
        }else{
            if (myHand.isPair()) {
                //lookup is value since both cards are the same
                //pair hash table
                Integer value = cardOne.value();
                playArray = pairTable.get(value);
                advice = playArray[secondValue];
            } else if (cardOne.isAce()) {
                //ace hash table
                //key is value because its other card besides ace
                Integer value = cardTwo.value();
                playArray = aceTable.get(value);
                advice = playArray[secondValue];
            } else if (cardTwo.isAce()) {
                //ace hash table
                //key is value because its other card besides ace
                Integer value = cardOne.value();
                playArray = aceTable.get(value);
                advice = playArray[secondValue];
            } else {
                //total number hash table
                //key is total since its the amount
                Integer total = myHand.getValue();
                playArray = totalTable.get(total);
                advice = playArray[secondValue];
            }
            
        }
        return advice;
    }
}
