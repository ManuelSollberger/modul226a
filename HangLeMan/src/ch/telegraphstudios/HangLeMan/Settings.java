package ch.telegraphstudios.HangLeMan;

import java.util.ArrayList;

public class Settings {

	/**
	 * This list contains all possible words.
	 */
	public static ArrayList<String> words = new ArrayList<String>();
	
	/**
	 * Removes every word from the list.
	 */
	public static void clearWords() {
		words = new ArrayList<String>();
	}
	
	/**
	 * Adds the given words to the word list.
	 * @param wordsToAdd
	 */
	public static void addWords(ArrayList<String> wordsToAdd) {
		for (String newWord : wordsToAdd) {
			words.add(newWord);
		}
	}
	
	/**
	 * Adds the given words to the word list. (As string array)
	 * @param wordsToAdd
	 */
	public static void addWords(String[] wordsToAdd) {
		for (String newWord : wordsToAdd) {
			words.add(newWord);
		}
	}
	
}
