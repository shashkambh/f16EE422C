package assignment2;

import java.util.Scanner;

public class Driver {
	public static void main(String[] args){
		boolean devMode=args != null && args.length>0 && args[0].equals("1");

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
    }
}
