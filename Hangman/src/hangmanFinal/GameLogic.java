package hangmanFinal;

public interface GameLogic {
	void handleGuess(char Guess);
	boolean hasWon();
	void endGame(boolean won);
}
