/** EE422C Project 2 (Mastermind) submission by
 *  Shashank Kambhampati
 *  Slip days used: 0
 *  Fall 2016
 */

package assignment2;

import java.util.ArrayList;

/**
 * This class manages the list of Codes entered so far and
 * formats output for the Game class.
 */
public class Mastermind{

    private ArrayList<Code> previousGuesses;
    private Code secretCode;
    private int guessesLeft;

    /**
     * Sole constructor. Sets number of guesses and secret Code for a given game.
     */
    public Mastermind(){
        previousGuesses=new ArrayList<>();
        guessesLeft=GameConfiguration.guessNumber;
        secretCode=Code.randomGenerateSecretCode();
    }

    /**
     * Returns the secret Code string to be output if in developer mode.
     * @return The Sttring representation of the secret Code.
     */
    public String getSecretCodeString(){
        return secretCode.getCode();
    }

    /**
     * Returns the number of guesses left before the user loses.
     * @return The number of guesses left.
     */
    public int getGuessesLeft(){
        return guessesLeft;
    }

    /**
     * Takes input from the user and decides what to do with it.
     * @param in The string recieved from the user.
     * @return The response to the user's guess.
     */
    public String parseInput(String in){
        String toPrint;
        if(in.equalsIgnoreCase("history")){
            toPrint=history();
        } else {
            Code codeToTest=Code.convertToCode(in, secretCode);
            toPrint=in;
            while(toPrint.length() < 4){
                toPrint += " ";
            }
            toPrint+=" -> ";
            toPrint+=codeToTest.getPegString();
            if(codeToTest.isValidCode()){
                previousGuesses.add(codeToTest);
                guessesLeft--;
            }
        }
        return toPrint;
    }

    /**
     * Creates a list of guesses that the user has made so far.
     * @return The guesses the user has made, line by line.
     */
    public String history(){
        String listOfGuesses="";
        for(Code e : previousGuesses){
            listOfGuesses+=e.getCode() + "\t\t" + e.getBlackPegs() + "B_" + e.getWhitePegs() + "W" + "\n";
        }
        if(!listOfGuesses.equals("")){
            listOfGuesses=listOfGuesses.substring(0, listOfGuesses.length()-1);
        }
        return listOfGuesses;
    }

}
