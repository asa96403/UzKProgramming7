package hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Game extends JFrame {
	// GUI components
	protected JLabel wordLabel, attemptsLabel, timeLabel;
	protected JTextField inputField;
	// The word the player has to guess (fixed for now)
	protected String currentWord = "example ";
	// Stores the letters guessed by the player
	protected ArrayList<Character> guessedLetters = new ArrayList<>();
	// Number of tries left
	protected int attemptsLeft = 6;
	// Time left (not yet functional – stays at 60)
	protected int timeLeft = 60;

	// Sets up the window and its components
	public Game() {
		setTitle(" Hangman Game ");
		setSize(400, 250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(6, 1));
		// Button to start a new game
		JButton startButton = new JButton(" Start Game ");
		add(startButton);
		// Label showing the guessed word with _ for unknown letters
		wordLabel = new JLabel("Word: ", SwingConstants.CENTER);
		add(wordLabel);
		// Label showing how many tries are left
		attemptsLabel = new JLabel(" Attempts left: " + attemptsLeft, SwingConstants.CENTER);
		add(attemptsLabel);
		// Label showing the time left (not yet changing)
		timeLabel = new JLabel("Time left: " + timeLeft, SwingConstants.CENTER);
		add(timeLabel);
		// Input field where the player types their guess
		inputField = new JTextField();
		inputField.setHorizontalAlignment(JTextField.CENTER);
		inputField.setEnabled(false); // initially inactive
		add(inputField);
		// Button to start a new game
				JButton sendButton = new JButton(" Enter ");
				add(sendButton);
		// Starts the game when button is clicked and enables restart
		startButton.addActionListener(e -> {
			startGame();
			startButton.setEnabled(false);
		});
		// processes the input when pressing the "Enter" button
				sendButton.addActionListener(e -> {
					processInput();
				});
		
		setVisible(true);
	}

	// Resets game state when starting a new game
	protected void startGame() {
		currentWord=currentWord.trim();
		guessedLetters.clear();
		attemptsLeft = 6;
		timeLeft = 60;
		updateDisplay();
		inputField.setEnabled(true);
		inputField.requestFocus();
	}

	// Updates the word , attempts , and time on the screen
	protected void updateDisplay() {
		StringBuilder display = new StringBuilder();
		for (char c : currentWord.toCharArray()) {
			if (guessedLetters.contains(c)) {
				display.append(c).append(" ");
			} else {
				display.append("_ ");
			}
		}
		wordLabel.setText("Word: " + display.toString());
		attemptsLabel.setText(" Attempts left: " + attemptsLeft);
		timeLabel.setText("Time left: " + timeLeft);
	}

	/**
	 * Retrieves the text from the input field inputField and converts it to
	 * lowercase. Clears the input field and checks whether it is one character. If
	 * valid handles guess, if not outputs message and returns.
	 * @author aabert
	 */
	public void processInput() {
		String input = inputField.getText();
		input = input.toLowerCase();
		input.trim();
		inputField.setText("");
		if (input.length() == 1 && Character.isAlphabetic(input.charAt(0))) {
			handleGuess(input.charAt(0));
		} else {
			System.out.println("Please enter exactly one letter!");
			return;
		}
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
	protected void handleGuess(char guess) {
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
	 * Returns true if every letter of the String currentWord has already been
	 * guessed, else return false.
	 * 
	 * @return true, if the entire word has been guessed
	 * @author aabert
	 */
	private boolean hasWon() {
		StringBuffer guessed = new StringBuffer();
		for (char character : guessedLetters) {
			guessed.append(character);
		}
		return hangman.util.LetterChecker.isSameLetters(currentWord, guessed.toString());
	}

	/**
	 * Checks whether won is true or false and prints an appropriate message to the
	 * console that also includes the target word. Disables further user input and
	 * ends the whole program.
	 * 
	 * @param won indicates whether the game has been won (the word guessed)
	 * @author aabert
	 */
	protected void endGame(boolean won) {
		if (won) {
			System.out.println("You won! :) The word was: " + currentWord);
		} else {
			System.out.println("You lost! :( The word was: " + currentWord);
		}
		inputField.setEnabled(false);
		System.exit(0);
	}
}
