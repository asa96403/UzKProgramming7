package hangmanFinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordFinder extends HangmanGame {
	/**
	 * Reads the words.txt file and returns a random word chosen from it that has the specified length. 
	 * Deletes the chosen word from the file by rewriting it with only the left-over words.
	 * @param length the length of the word to choose, must be <=10 or no word will be found
	 * @return the chosen word from the words file with length length
	 * @author aabert
	 */
	public String chooseRandomWordFromFile(int length) {
		File file = new File(".//src//hangmanFinal/wordFiles/words.txt");
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<String> allWords = new ArrayList<String>();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				allWords.add(line.trim());
				if (line.length() == length) {
					words.add(line.trim());
				}
			}
			if (words.isEmpty())
				return null;
		} catch (FileNotFoundException e) {
			System.err.println("The word file was not found");
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("An IO error ocurred while reading a line from the file.");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		Random random = new Random();
		int index = random.nextInt(words.size());
		String chosen = words.get(index);
		allWords.remove(allWords.indexOf(chosen));
		// Überschreiben: bei Buffered Reader append:false: leert file, neu rein schreiben
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
			for (String line : allWords) {
				writer.write(line);
				writer.newLine();
			}
		} catch (IOException e) {
			System.err.println("During File Writing the File could not be accessed!");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return chosen;
	}

	@Override
	protected void startGame() {
		int length = 4;
			currentWord = chooseRandomWordFromFile(length);

			if (currentWord == null) {
				System.out.println("No word found.");
				System.exit(0);
				return;
			}

			currentWord = currentWord.toLowerCase();
			guessedLetters.clear();
			attemptsLeft = 6;

			timeLeft = 60;
			updateDisplay();
			inputField.setEnabled(true);
			inputField.requestFocus();

			startTimer();
	}
}
