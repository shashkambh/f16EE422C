package assignment2;
import java.util.ArrayList;

public class Mastermind{

	private ArrayList<Code> previousGuesses;
	private Code secretCode;
	private int guessesLeft;

	public Mastermind(){
		previousGuesses=new ArrayList<>();
	    guessesLeft=GameConfiguration.guessNumber;
        secretCode=Code.randomGenerateSecretCode();
	}
	
    public String getSecretCodeString(){
        return secretCode.getCode();
    }

    public int getGuessesLeft(){
        return guessesLeft;
    }

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

	public String history(){
		String listOfGuesses="";

		for(Code e : previousGuesses){
			listOfGuesses+=e.getCode() + "\t\t" + e.getBlackPegs() + "B_" + e.getWhitePegs() + "w" + "\n";
		}
		return listOfGuesses;
	}

}
