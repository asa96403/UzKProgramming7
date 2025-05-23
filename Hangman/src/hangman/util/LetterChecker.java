package hangman.util;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Utility class modified to check for same letters but not number of letters from earlier homework
 */
public class LetterChecker {

	/**
	 * Returns true if the two strings contain the same letters.
	 */
	public static boolean isSameLetters(String a, String b) {
		if (Arrays.equals(countLetters(a), countLetters(b))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Helper method to count how many times each letter (a-z) appears.
	 * ignores spaces
	 */
	public static int[] countLetters(String s) {
//		char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
//				'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		int[] counts = new int[26];
		Arrays.fill(counts, 0);
		for(int i=0; i<s.length(); i++) {
			if(0 <= (int) s.charAt(i)-97 && (int) s.charAt(i)-97<25) {
				if(counts[(int) s.charAt(i)-97]==0) {
					counts[(int) s.charAt(i)-97]++;					
				}
			}
		}
		return counts;
	}
}
