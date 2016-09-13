package assignment2;
public class Strings{
    public static final String anotherGame="Do you want to play again? (Y/N):";
	public static final String codeGenString="Generating secret code...";
	public static final String losingMessage="Sorry, you are out of guesses. You lose, boo-hoo.";
	public static final String promptString="What is your next guess?\n"+ 
"Type in the characters for your guess and press enter.\nEnter guess:";
	public static final String introString="Welcome to Mastermind. Here are the rules.\n\n"+
"This is a text version of the classic board game Mastermind.\n"+
"The computer will think of a secret code. The code consists of 4\ncolored pegs.\n"+
"The pegs MUST be one of six colors: blue, green, orange, purple,\nred, or yellow. A color may appear more than once in the code. You\ntry to guess what colored pegs are in the code and what order they\nare in."+
"After you make a valid guess the result (feedback) will be\ndisplayed.\n"+
"The result consists of a black peg for each peg you have guessed\nexactly correct (color and position) in your guess. For each peg in\nthe guess that is the correct color, but is out of position, you get\na white peg. For each peg that is fully incorrect, you get no\nfeedback.\n"+
"Only the first letter of the color is displayed. B for Blue, R for\nRed, and so forth.\n"+
"When entering guesses you only need to enter the first character of\neach color as a capital letter.\n\n"+
"You have 12 guesses to figure out the secret code or you lose the\ngame. Are you ready to play? (Y/N):";
}
