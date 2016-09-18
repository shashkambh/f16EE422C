package assignment2;

import java.util.Scanner;

/**
 * This class uses the termal to interface with the user.
 */
public class Game extends Mastermind{
    private static final String CODE_GEN="Generating secret code....";
    private static final String PROMPT="What is your next guess?\n"+ 
"Type in the characters for your guess and press enter.\nEnter guess:";
    private static final String LOSING_MESSAGE="(Sorry, you are out of guesses. You lose, boo-hoo.)";

    private boolean debugMode;

    /**
     * Sole constructor.
     * @param startDebugMode Set to true if the user wants to enter Developer Mode, 
     * where the secret code is output to the screen.
     */
    public Game(boolean startDebugMode){
        debugMode=startDebugMode;
    }
    /**
     * Runs the given Game to completion.
     * @param input The scanner to read from for user input.
     */
    public void runGame(Scanner input){
        boolean won=false;
        String codeGenOut = CODE_GEN;
        if(debugMode){
            codeGenOut+="(for this example the secret code is " + "\n" + getSecretCodeString() + ")";
        }
        System.out.println(codeGenOut);
		int guessesLeft=getGuessesLeft();
        while(!won && guessesLeft > 0){
            System.out.println();
            System.out.println("You have " + guessesLeft + (guessesLeft != 1? " guesses" : " guess") + " left.");
            System.out.print(PROMPT);
            String userGuess=input.nextLine();
            String result=parseInput(userGuess);
            won=result.contains("You win");
            System.out.println(result);
        }
        if(!won){
            System.out.println(LOSING_MESSAGE);
        }
        System.out.println();
    }
}
