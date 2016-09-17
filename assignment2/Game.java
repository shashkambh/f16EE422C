package assignment2;

import java.util.Scanner;

/**
 * This class uses the termal to interface with the user.
 */
public class Game extends Mastermind{
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
        String codeGenOut=Strings.codeGenString;
        if(debugMode){
            codeGenOut+="(for this example the secret code is " + "\n" + getSecretCodeString() + ")";
        }
        System.out.println(codeGenOut);
        while(!won && getGuessesLeft() > 0){
            System.out.println();
            System.out.println("You have " + getGuessesLeft() + (getGuessesLeft() != 1? " guesses" : " guess") + " left.");
            System.out.print(Strings.promptString);
            String userGuess=input.nextLine();
            String result=parseInput(userGuess);
            won=result.contains("You win");
            System.out.println(result);
        }
        if(!won){
            System.out.println(Strings.losingMessage);
        }
        System.out.println();
    }
}
