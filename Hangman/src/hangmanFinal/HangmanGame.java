package hangmanFinal;


public class HangmanGame extends GameGUI implements GameLogic, TimerLogic{

	/**
	 * starts a new timer thread
	 * @author aabert
	 */
	@Override
	public void startTimer() {
		TimerRunnable timer = new TimerRunnable(this);
		timer.start();
	}

	//Overridden to add the startTimer call
	@Override
	public void startGame() {
		super.startGame();
		startTimer();
	}
	
	/**
	 * Check if the letter has already been guessed (using guessedLetters). If so,
	 * it prints a hint for the user and exits early. Otherwise, it adds the letter
	 * to the set of guessed letters. If the letter is not part of the target word
	 * currentWord, it reduces the number of remaining attempts attemptsLeft. Ends
	 * the game if the player has either guessed the word completely or used up all
	 * attempts.
	 * 
	 * @param guess the Character that has been guessed
	 * @author aabert
	 */
	@Override
	public void handleGuess(char guess) {
		for (char letter : guessedLetters) {
			if (guess == letter) {
				System.out.println("You already tried the letter: " + guess);
				return;
			}
		}
		guessedLetters.add(guess);
		if (currentWord.indexOf(guess) == -1)
			attemptsLeft--;
		updateDisplay();
		if (hasWon() || attemptsLeft == 0) {
			endGame(hasWon());
		}
	}

	/**
	 * Checks whether won is true or false and prints an appropriate message to the
	 * console that also includes the target word. Disables further user input and
	 * ends the whole program.
	 * 
	 * @param won indicates whether the game has been won (the word guessed)
	 * @author aabert
	 */
	@Override
	public void endGame(boolean won) {
		if (won) {
			System.out.println("You won! :) The word was: " + currentWord);
		} else {
			System.out.println("You lost! :( The word was: " + currentWord);
		}
		inputField.setEnabled(false);
		System.exit(0);
	}

	/**
	 * Returns true if every letter of the String currentWord has already been
	 * guessed, else return false.
	 * 
	 * @return true, if the entire word has been guessed
	 * @author aabert
	 */
	@Override
	public boolean hasWon() {
		StringBuffer guessed = new StringBuffer();
		for (char character : guessedLetters) {
			guessed.append(character);
		}
		return hangman.util.LetterChecker.hasAllLetters(currentWord, guessed.toString(), currentWord);
	}

}
