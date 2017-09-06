package edu.frostburg.cosc310;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

/**
 * SpellChecker class to create a spell checker object
 * 
 * @author Barak Shechter
 * @version 2015.11.9
 */
public class SpellChecker {

	private static Scanner scan;
	private boolean fileFound = true;
	private HashSet<String> wordlist;
	private HashSet<String> suggestions;
	private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	private static char[] alphaArray = alphabet.toCharArray();
	
	public SpellChecker(String filename) {
		try {
			System.out.println("Looking for dictionary file...");
			File f = new File(filename);
			scan = new Scanner(f);
			System.out.println("File found. Creating spell checker...");
			wordlist = new HashSet<String>();
			suggestions = new HashSet<String>();
			while(scan.hasNext()){
				wordlist.add(scan.nextLine());
			}
		}
		catch(FileNotFoundException err) {
			System.out.println("File not found. Spell Checker not initialized.");
			fileFound = false;
		}
		

	}
	
	/**
	 * Finds relevant suggestions for spelling a word.
	 * 
	 * @param s the input word to be spell checked
	 * @return a HashSet of all the suggestions to spell the word correctly.
	 */
	public HashSet<String> find(String s) {
		suggestions.add(s);
		suggestions.retainAll(wordlist);
		if (!suggestions.isEmpty()) {
			return suggestions;
		}
		swapLetters(s);
		wrongLetters(s);
		missingChar(s);
		phoneticSub(s);
		suggestions.retainAll(wordlist);
		if(!suggestions.isEmpty()) {
			System.out.println("The word is misspelled. Did you mean: ");
			return suggestions;
		}
		else {
			suggestions.add("The word is misspelled. No suggestions found.");
			return suggestions;
		}
		
	}
	
	
	/**
	 * Swaps the letters within a string and adds it to the suggestions HashSet
	 * 
	 * @param s String to be manipulated
	 */
	public void swapLetters(String s) {
		
		for (int i = 0; i<s.length();++i)
		{
			for (int j = 0; j <s.length(); ++j)
			{
				char[] charArray = s.toCharArray();
				char temp =charArray[i]; 
				charArray[i] = charArray[j];
				charArray[j] = temp;
				String tempString = new String(charArray);
				suggestions.add(tempString);
			}
		}
	}
	
	/**
	 * Changes the letters of a string and adds it to the suggestions HashSet 
	 * 
	 * @param s String to be manipulated
	 */
	public void wrongLetters(String s) {
		for (int i = 0; i<s.length();++i) {
			for(int j = 0; j < alphabet.length(); ++j) {
				char[] charArray = s.toCharArray();
				charArray[i] = alphaArray[j];
				String tempString = new String(charArray);
				suggestions.add(tempString);
			}
		}
	}
	
	/**
	 * Inserts all alphabetical letter into every index of the string
	 * (finds suggestions for wrong characters that were inserted 
	 * incorrectly, or 1 missing letter)
	 * 
	 * @param s String to be manipulated
	 */
	public void missingChar(String s) {
		ArrayList<Character> list = new ArrayList<>();
		for(int i=0; i<s.length()+1;++i) {
			for(int j=0;j<alphaArray.length; ++j) {
				list = arrayToList(s.toCharArray());
				list.add(i, alphaArray[j]);
				char[] charArray = listToArray(list);
				String tempString = new String(charArray);
				suggestions.add(tempString);
			}
		}
	}
	
	/**
	 * Helper method for missingChar(). Converts a char[] array and returns an 
	 * equivalent ArrayList<Character>
	 * 
	 * @param array Array to be converted
	 * @return ArrayList with the same elements as the array
	 */
	private ArrayList<Character> arrayToList(char[] array) {
		ArrayList<Character> list = new ArrayList<>();
		for(int i =0; i<array.length;++i) {
			list.add(array[i]);
		}
		return list;
	}
	
	/**
	 * Helper method of missingChar(). Converts an ArrayList<Character> and returns an
	 * equivalent char[] array.
	 * 
	 * @param list ArrayList to be converted
	 * @return char[] array
	 */
	private char[] listToArray(ArrayList<Character> list) {
		list.trimToSize();
		char[] array = new char[list.size()];
		for(int i=0; i<list.size();++i) {
			array[i] = list.get(i);
		}
		return array;
	}
	
	
	/**
	 * Adds to the suggestions HashSet some of the most common phonetic substitutions
	 * 
	 * @param s String to be manipulated
	 */
	public void phoneticSub(String s) {
		for (int i=0;i<s.length();++i) {
				char[] charArray = s.toCharArray();
				switch(charArray[i]) {
					case 'a':
						charArray[i] = 'e';
						break;
					case 'c':
						charArray[i] = 'k';
						break;
					case 'e':
						charArray[i] = 'i';
						break;
					case 's':
						charArray[i] = 'c';
						break;
					case 'p':
						charArray[i] = 'f';
						break;
					case 'f':
						charArray[i] = 'p';
						break;
				}
				String tempString = new String(charArray);
				suggestions.add(tempString);
		}
		
	}
	
	/**
	 * Prints the entire dictionary of the spell checker
	 */
	public void printDictionary() {
		for (String s: wordlist) {
			System.out.println(s);
		}
	}
	
	/**
	 * Returns if a file was found
	 * 
	 * @return true if a dictionary file was found.
	 */
	public boolean getFileFound() {
		return fileFound;
	}
	
}
