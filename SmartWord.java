/*
  Authors (group members): Caleb, Santi, Ashley
  Email addresses of group members: cbrooks2022@my.fit.edu | Santi's eMail | Ashley's eMail
  Group name: "ClassNotFoundException"
  Course:   Algorithms & Data Struct
  Section:  04
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import statsPackage.Hasher;
import triePackage.Trie;

public class SmartWord {
	final int NUM_GUESSES = 3;
	String[] guesses = new String[NUM_GUESSES];
	Hasher hasher;
	String wordFile;
	Trie vocabTrie;

	public SmartWord (String wordFile) throws FileNotFoundException {
		this.hasher = new Hasher();
		this.wordFile = wordFile;
		this.vocabTrie = new Trie();
	}

	public void processOldMessages (String oldMessageFile) throws FileNotFoundException {
		try (BufferedReader tempBR = new BufferedReader(new FileReader(new File(oldMessageFile)))) {
			ArrayList<ArrayList<String>> sentences = new ArrayList<>();
			String line;
			while ((line = tempBR.readLine()) != null) {
				// TODO: add logic for parsing individual sentences
				// String[] lineArray = line.split(" ");
				// for (int i = 0; i < lineArray.length; i++) {
				// ...
				// }
			}

		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	public void processVocab () throws FileNotFoundException {
		try (BufferedReader tempBR = new BufferedReader(new FileReader(new File(wordFile)))) {
			int lnnum = 1;
			String line;
			while ((line = tempBR.readLine()) != null) {
				hasher.addVocab(line, lnnum);
				lnnum++;
				vocabTrie.insert(line.toCharArray());
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String[] guess (
	char letter,
	int letterPosition,
	int wordPosition
	) {

		return guesses;
	}

	public void feedback (
	boolean isCorrectGuess, 
	String correctWord
	) {

	}

}