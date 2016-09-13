package assignment2;
public class Code{
	private String code;
	private int blackPegs;
	private int whitePegs;
	private boolean valid;

	private Code(){ }

	public static Code convertToCode(String toCode, Code secretCode){
		Code newCode=new Code();
		newCode.code=toCode;
		newCode.valid=isValid(toCode);
		if(newCode.valid){
			newCode.check(secretCode);
		}

		return newCode;
	}

	public static Code randomGenerateSecretCode(){
		String secretCodeString=SecretCodeGenerator.getInstance().getNewSecretCode();
		Code secretCode=new Code();

		secretCode.code=secretCodeString;
		secretCode.blackPegs=4;
		secretCode.whitePegs=0;
		
		return secretCode;
	}

	public String getCode(){
		return code;
	}

	public int getBlackPegs(){
		return blackPegs;
	}

	public int getWhitePegs(){
		return whitePegs;
	}

	public boolean isValidCode(){
		return valid;
	}

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
