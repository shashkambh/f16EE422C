package assignment2;

/**
 * This class manages the guesses that the user makes.
 * It compares a given guess with a generated secret Code.
 */
public class Code{
	private String code;
	private int blackPegs;
	private int whitePegs;
	private boolean valid;

	/**  
	 * Sole constructor. Prevent external classes from constructing Codes.
	 */
	private Code(){ }

	/**
	 * Converts a given input string to a Code. Also sets the number of black
	 * and white pegs based on the secret Code given.
	 * @param toCode The input string to be converted to the code.
	 * @param secretCode The secret Code that the new Code will be checked against.
	 * @return A new Code with validity checked and number of pegs set.
	 */
	public static Code convertToCode(String toCode, Code secretCode){
		Code newCode=new Code();
		newCode.code=toCode;
		newCode.valid=isValid(toCode);
		if(newCode.valid){
			newCode.check(secretCode);
		}

		return newCode;
	}

	/**
	 * Generates a secret Code based on a random number generator.
	 * @return A randomly generated secret Code for the player to guess.
	 */
	public static Code randomGenerateSecretCode(){
		String secretCodeString=SecretCodeGenerator.getInstance().getNewSecretCode();
		Code secretCode=new Code();

		secretCode.code=secretCodeString;
		secretCode.blackPegs=4;
		secretCode.whitePegs=0;
		
		return secretCode;
	}

	/**
	 * Returns the String representing the Code.
	 * @return the String that represents the guess
	 */
	public String getCode(){
		return code;
	}

	/**
	 * Returns the number of black pegs that the Code gets
	 * @return the number of letters that matched exactly with the secret Code
	 */
	public int getBlackPegs(){
		return blackPegs;
	}

	/**
	 * Returns the number of white pegs that the Code gets
	 * @return the number of letters that matched at different locations with the secret Code
	 */
	public int getWhitePegs(){
		return whitePegs;
	}

	/**
	 * Returns if the String given to the Code construction was a valid guess
	 * @return true if the guess was valid, false otherwise
	 */
	public boolean isValidCode(){
		return valid;
	}

	/**
	 * Produces a String for the user to view about the number of pegs
	 * @return A formatted output String that shows the number of pegs to the user.
	 */
	public String getPegString(){
		String pegString="Result: ";
		if(!valid){
			pegString="INVALID GUESS";
		} else if(whitePegs == 0 && blackPegs == 0){
			pegString+="No pegs";
		} else {
			String blackPegString = blackPegs == 1 ? "1 black peg" : blackPegs + " black pegs";
			String whitePegString = whitePegs == 1 ? "1 white peg" : whitePegs + " white pegs";
			if(blackPegs != 0){
				pegString += blackPegString;
				if(whitePegs != 0){
					pegString += " and ";
				}
			}

			if(whitePegs!=0){
				pegString += whitePegString;
			}
		}

		if(pegString.contains("4 black pegs")){
			pegString+=" - You win!!";
		}
		return pegString;
	}
	/**
	 * Checks if a String is a valid Code guess.
	 * @param possibleCode The string to be checked.
	 * @return True if the String is a valid Code, false otherwise
	 */
	private static boolean isValid(String possibleCode){
		boolean correctLength = (possibleCode.length() == GameConfiguration.pegNumber);
		boolean validChars = true;
		for(int i=0; i<possibleCode.length() && validChars; i++){
			String charToCheck=possibleCode.substring(i,i+1);
			boolean found=false;
			for(int j=0; j<GameConfiguration.colors.length && !found; j++){
				if(GameConfiguration.colors[j].equals(charToCheck)){
					found=true;
				}
			}
			validChars=found;
		}
		return validChars && correctLength;
	}
	/**
	 * Checks how many pegs should be set for the Code based on the secret Code.
	 * @param secretCode the secret code to be checked against.
	 */
	private void check(Code secretCode){
		this.blackPegs = 0;
		this.whitePegs = 0;
		String compareCode = code;
        String secretCodeString = secretCode.code;
		for(int i = compareCode.length() - 1; i >= 0; i--){
			if(compareCode.charAt(i) == secretCodeString.charAt(i)){
				compareCode = compareCode.substring(0,i) + compareCode.substring(i+1);
				secretCodeString = secretCodeString.substring(0,i) + secretCodeString.substring(i+1);
				this.blackPegs++;
			}
		}

		for(int i=compareCode.length() - 1; i >= 0; i--){
			boolean found=false;
			for(int j = 0; j < secretCodeString.length() && !found; j++){
				if(compareCode.charAt(i) == secretCodeString.charAt(j)){
					compareCode = compareCode.substring(0,i)+compareCode.substring(i+1);
					secretCodeString = secretCodeString.substring(0,j) + secretCodeString.substring(j+1);
					this.whitePegs++;
					found=true;
				}
			}
		}
	}

}
