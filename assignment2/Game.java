package assignment2;

import java.util.Scanner;

public class Game extends Mastermind{
    private boolean debugMode;
    
    public Game(boolean startDebugMode){
        debugMode=startDebugMode;
    }

    public void runGame(Scanner input){
        boolean won=false;
        String codeGenOut=Strings.codeGenString;
        if(debugMode){
            codeGenOut+="(for this example the secret code is " + "\n" + getSecretCodeString() + ")";
        }
        System.out.println(codeGenOut);
        System.out.println();
        while(!won && getGuessesLeft() > 0){
            System.out.println("You have " + getGuessesLeft() + (getGuessesLeft() != 1? " guesses" : " guess") + " left.");
            System.out.print(Strings.promptString);
            String userGuess=input.nextLine();
            String result=parseInput(userGuess);
            won=result.contains("You win");
            System.out.println(result);
            System.out.println();
        }
        if(!won){
            System.out.println(Strings.losingMessage);
        }
    }
}
