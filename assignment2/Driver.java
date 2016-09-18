/** EE422C Project 2 (Mastermind) submission by
 *  Shashank Kambhampati
 *  Slip days used: 0
 *  Fall 2016
 */

package assignment2;

import java.util.Scanner;
/**
 * This class contains the Main method to start the program.
 * It calls Game for the game to actually begin.
 */
public class Driver {

    public static final String ANOTHER_GAME="Do you want to play again? (Y/N):";
    public static final String PLAY="Do you want to play? (Y/N):";
    public static final String INTRO="Welcome to Mastermind. Here are the rules.\n\n" +
        "This is a text version of the classic board game Mastermind.\n" +
        "The computer will think of a secret code. The code consists of 4\ncolored pegs.\n" +
        "The pegs MUST be one of six colors: blue, green, orange, purple,\nred, or yellow. A color may appear more than once in the code." +
		"You\ntry to guess what colored pegs are in the code and what order they\nare in." +
        "After you make a valid guess the result (feedback) will be\ndisplayed.\n" +
        "The result consists of a black peg for each peg you have guessed\nexactly correct (color and position) in your guess." + 
        "For each peg in\nthe guess that is the correct color, but is out of position, you get\na white peg." +
		"For each peg that is fully incorrect, you get no\nfeedback.\n" +
        "Only the first letter of the color is displayed. B for Blue, R for\nRed, and so forth.\n" +
        "When entering guesses you only need to enter the first character of\neach color as a capital letter.\n\n" +
        "You have 12 guesses to figure out the secret code or you lose the\ngame. Are you ready to play? (Y/N):";


    
    /**
     * The main method, which introduces the game and is run from commmand line.
     * @param args Command line args. Use a 1 to set Developer Mode, which shows the secret Code.
     */
    public static void main(String[] args){
        boolean devMode = (args.length == 1) && args[0].equals("1");
        boolean played = false;

        System.out.print(INTRO);
        Scanner scan = new Scanner(System.in);
        String playing = scan.nextLine();
        Game currentGame;

        while(!playing.equalsIgnoreCase("N")){
            if(playing.equalsIgnoreCase("Y")){
                played = true;
                currentGame = new Game(devMode);
                currentGame.runGame(scan);
            }
            if(played){
                System.out.print(ANOTHER_GAME);
            } else {
                System.out.print(PLAY);
            }
            playing=scan.nextLine();
        }

        scan.close();
    }
}
