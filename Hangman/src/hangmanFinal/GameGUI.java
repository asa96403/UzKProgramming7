package hangmanFinal;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public abstract class GameGUI extends JFrame {
	protected JLabel wordLabel, attemptsLabel, timeLabel;
	protected JTextField inputField;
	protected String currentWord = "example ";
	protected Set<Character> guessedLetters = new HashSet<>();
	protected int attemptsLeft = 6;
	protected int timeLeft = 60;

	public GameGUI() {
		setTitle(" Hangman Game ");
		setSize(400, 250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(6, 1));
		JButton startButton = new JButton(" Start Game ");
		add(startButton);
		wordLabel = new JLabel("Word: ", SwingConstants.CENTER);
		add(wordLabel);
		attemptsLabel = new JLabel(" Attempts left: " + attemptsLeft, SwingConstants.CENTER);
		add(attemptsLabel);
		timeLabel = new JLabel("Time left: " + timeLeft, SwingConstants.CENTER);
		add(timeLabel);
		inputField = new JTextField();
		inputField.setHorizontalAlignment(JTextField.CENTER);
		inputField.setEnabled(false);
		add(inputField);
		startButton.addActionListener(e -> startGame());
		setupInput();
		setVisible(true);
	}

	protected void setupInput() {
		inputField.addActionListener(e -> {
			String input = inputField.getText().toLowerCase();
			inputField.setText("");
			if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
				System.out.println(" Please enter exactly one letter .");
				return;
			}
			handleGuess(input.charAt(0));
		});
	}

	protected void startGame() {
		guessedLetters.clear();
		attemptsLeft = 6;
		timeLeft = 60;
		updateDisplay();
		inputField.setEnabled(true);
		inputField.requestFocus();
	}

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

	public void processInput() {
// implement again
	}

	protected abstract void handleGuess(char guess);

	protected abstract boolean hasWon();

	protected abstract void endGame(boolean won);
}
