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
 * Class to create the basic strategy
 * @author Brenden Bishop and Dan Cody
 */
public final class BasicStrategy {

    //Maps for the tables
    protected Map<Integer, Play[]> pairTable;
    protected Map<Integer, Play[]> aceTable;
    protected Map<Integer, Play[]> totalTable;
    
    public final static Play H = Play.HIT;
    public final static Play S = Play.STAY;
    public final static Play D = Play.DOUBLE_DOWN;
    public final static Play P = Play.SPLIT;

    /**
     * constructor to call the private constructor
     */
    public BasicStrategy() {
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

    /**
     * Create the total table
     */
    public void createTotalTable() {
        totalTable = new HashMap<>();
        //create pair table

        //fill with values
        Play[] twenty =     {Play.NONE, S, S, S, S, S, S, S, S, S, S, S};
        Play[] nineteen =   {Play.NONE, S, S, S, S, S, S, S, S, S, S, S};
        Play[] eightteen =  {Play.NONE, S, S, S, S, S, S, S, S, S, S, S};
        Play[] seventeen =  {Play.NONE, S, S, S, S, S, S, S, S, S, S, S};
        Play[] sixteen =    {Play.NONE, H, S, S, S, S, S, H, H, H, H, H};
        Play[] fifteen =    {Play.NONE, H, S, S, S, S, S, H, H, H, H, H};
        Play[] fourteen =   {Play.NONE, H, S, S, S, S, H, H, H, H, H, H};
        Play[] thirteen =   {Play.NONE, H, S, S, S, S, H, H, H, H, H, H};
        Play[] twelve =     {Play.NONE, H, H, H, S, S, S, H, H, H, H, H};
        Play[] eleven =     {Play.NONE, H, D, D, D, D, D, D, D, D, D, H};
        Play[] ten =        {Play.NONE, H, D, D, D, D, D, D, D, D, H, H};
        Play[] nine =       {Play.NONE, H, H, D, D, D, D, H, H, H, H, H};
        Play[] eight =      {Play.NONE, H, H, H, H, H, H, H, H, H, H, H};
        Play[] seven =      {Play.NONE, H, H, H, H, H, H, H, H, H, H, H};
        Play[] six =        {Play.NONE, H, H, H, H, H, H, H, H, H, H, H};
        Play[] five =       {Play.NONE, H, H, H, H, H, H, H, H, H, H, H};

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

    /**
     * Method to create the ace table
     */
    public void createAceTable() {
        //create ace table
        aceTable = new HashMap<>();

        //fill with values
        Play[] aTen =       {Play.NONE, S, S, S, S, S, S, S, S, S, S, S};
        Play[] aNine =      {Play.NONE, H, S, S, S, S, S, S, S, S, S, S};
        Play[] aEight =     {Play.NONE, H, S, S, S, S, S, S, S, S, S, S};
        Play[] aSeven =     {Play.NONE, H, S, D, D, D, D, S, S, H, H, H};
        Play[] aSix =       {Play.NONE, H, H, D, D, D, D, H, H, H, H, H};
        Play[] aFive =      {Play.NONE, H, H, H, D, D, D, H, H, H, H, H};
        Play[] aFour =      {Play.NONE, H, H, H, D, D, D, H, H, H, H, H};
        Play[] aThree =     {Play.NONE, H, H, H, H, D, D, H, H, H, H, H};
        Play[] aTwo =       {Play.NONE, H, H, H, H, D, D, H, H, H, H, H};

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

    /**
     * Method to create the pair table
     */
    public void createPairTable() {
        //create total Table
        pairTable = new HashMap<>();

        //fill with values            
        Play[] cAce =       {Play.NONE, P, P, P, P, P, P, P, P, P, P, P};
        Play[] cTen =       {Play.NONE, S, S, S, S, S, S, S, S, S, S, S};
        Play[] cNine =      {Play.NONE, S, P, P, P, P, P, S, P, P, S, S};
        Play[] cEight =     {Play.NONE, P, P, P, P, P, P, P, P, P, P, P};
        Play[] cSeven =     {Play.NONE, H, P, P, P, P, P, P, H, H, H, H};
        Play[] cSix =       {Play.NONE, H, P, P, P, P, P, H, H, H, H, H};
        Play[] cFive =      {Play.NONE, H, D, D, D, D, D, D, D, D, H, H};
        Play[] cFour =      {Play.NONE, H, H, H, H, P, P, H, H, H, H, H};
        Play[] cThree =     {Play.NONE, H, P, P, P, P, P, P, H, H, H, H};
        Play[] cTwo =       {Play.NONE, H, P, P, P, P, P, P, H, H, H, H};

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
    
    /**
     * Method to return advice on the play to make
     * @param myHand hand you currently have
     * @param upCard dealers up card
     * @return Party Enum of the play to make
     */
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
    
    /**
     * Method to return the play based solely on the hands total
     * @param myHand The players current hand
     * @param upCard The dealers up card
     * @return 
     */
    public Play adviseTotalOnly(Hand myHand, Card upCard){
        //dealer card value
        int secondValue = upCard.value();
        //array of the advices
        Play[] playArray;
        
        //total number hash table
        //key is total since its the amount
        Integer total = myHand.getValue();
        playArray = totalTable.get(total);
        
        //return the play based on the position in the array
        return playArray[secondValue];
        
    }
}
