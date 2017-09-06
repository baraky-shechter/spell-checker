package edu.frostburg.cosc310;

import java.util.Scanner;
import java.io.*;

/**
 * Driver class for the SpellChecker class
 * 
 * @author Barak Shechter
 * @version 2015.11.9
 */
public class Main {

	private static String filename = "dictionary.txt";
	private static SpellChecker checker;
	private static Scanner scan;
	
	public static void main(String[] args) {
		
			checker = new SpellChecker(filename);
			if (checker.getFileFound()) {
				scan = new Scanner(System.in);
				//checker.printDictionary();
				System.out.println("Enter a word: ");
				String word = scan.next();
				System.out.println(checker.find(word));
			}
		
	}
	
	

}
