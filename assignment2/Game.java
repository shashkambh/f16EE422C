package assignment2;

import java.util.Scanner;

public class Game extends Mastermind{
    private boolean debugMode;
    
    public Game(boolean startDebugMode){
        debugMode=startDebugMode;
    }

    public void runGame(Scanner input){
        boolean won=false;
        System.out.println(Strings.codeGenString);
        System.out.println();
        while(!won && guessesLeft > 0){
            System.out.println("You have " + guessesLeft + (guessesLeft != 1? " guesses" : " guess") + " left.");
            if(debugMode){
                System.out.println(secretCode.getCode());
            }
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
