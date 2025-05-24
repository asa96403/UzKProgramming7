package hangmanFinal.util;

import java.util.Arrays;

/**
 * Utility class modified to check for same letters but not number of letters from earlier homework
 */
public class LetterChecker {

	/**
	 * Returns true if the two strings contain the same letters.
	 */
	public static boolean hasAllLetters(String a, String b, String word) {
		if (Arrays.equals(countLetters(a, word), countLetters(b, word))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Helper method to count how many times each letter (a-z) appears.
	 * ignores spaces
	 */
	public static int[] countLetters(String s, String word) {
		int[] counts = new int[26];
		Arrays.fill(counts, 0);
		for(int i=0; i<s.length(); i++) {
			if(0 <= (int) s.charAt(i)-97 && (int) s.charAt(i)-97<25) {
				if(counts[(int) s.charAt(i)-97]==0 && word.indexOf(s.charAt(i))!=-1) {
					counts[(int) s.charAt(i)-97]++;					
				}
			}
		}
		return counts;
	}
}
