package assignment2;

import java.util.Scanner;
/**
 * This class contains the Main method to start the program.
 * It calls Game for the game to actually begin.
 */
public class Driver {
	/**
	 * The main method, which introduces the game and is run from commmand line.
	 * @param args Command line args. Use a 1 to set Developer Mode, which shows the secret Code.
	 */
	public static void main(String[] args){
		boolean devMode = args.length == 1 && args[0].equals("1");

        System.out.print(Strings.introString);
        Scanner scan=new Scanner(System.in);
        String playing=scan.nextLine();
        Game currentGame;

        while(!playing.equals("N")){
            if(playing.equalsIgnoreCase("Y")){
                currentGame=new Game(devMode);
                currentGame.runGame(scan);
            }
            System.out.print(Strings.anotherGame);
            playing=scan.nextLine();
	    }

        scan.close();
    }
}
