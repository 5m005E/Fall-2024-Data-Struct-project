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
import java.util.HashMap;

import statsPackage.Hasher;
import statsPackage.PredictiveEngine;
import triePackage.Trie;

public class SmartWord {
	private final boolean DEBUG = true;

	final int NUM_GUESSES = 3;
	String[] guesses = new String[NUM_GUESSES];
	Hasher hasher;
	String wordFile;
	Trie vocabTrie;
	int totalVocab;
	int inWordCount;
	ArrayList<String> oldMessages;

	public SmartWord (String wordFile) throws FileNotFoundException {
		this.hasher = new Hasher();
		this.wordFile = wordFile;
		this.vocabTrie = new Trie();
		this.totalVocab = 0;
		this.inWordCount = 0;
		this.oldMessages = new ArrayList<>();
	}

	/**
	 * 
	 * @param oldMessageFile
	 * @throws FileNotFoundException
	 */
	public void processOldMessages (String oldMessageFile) throws FileNotFoundException {
		if (DEBUG) {
			System.out.println("processOldMessages(" + oldMessageFile + ")");
		}

		try (BufferedReader tempBR = new BufferedReader(new FileReader(new File(oldMessageFile)))) {
			String line;
			
			ArrayList<HashMap<String, Integer>> occurrenceMapList = new ArrayList<>();
			HashMap<String, Integer> occurrenceMap = new HashMap<>();
			
			while ((line = tempBR.readLine()) != null) {

				line = line.toLowerCase();

				if (DEBUG) {
					System.out.println("----------\nPARSING:" + line + "\n----------");
				}

				String[] lineArr = line.split(" ");
				for (int i = 0; i < lineArr.length; i++) {
					String temp = lineArr[i];
					String cleanTemp = temp.replaceAll("[^a-zA-Z]+", "");

					oldMessages.add(cleanTemp);

					if (!hasher.getProbabilityMap().containsKey(cleanTemp)) {
						int newVocabHashValue = hasher.getVocabSize() + 1;
						hasher.addVocab(cleanTemp, newVocabHashValue);
					}

					// TODO: add correspondence map logic
				}
			}
			hasher.hashOldMessages(oldMessages);
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
				totalVocab++;
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

		if (DEBUG) {
			System.out.println("VocabHashMap:");
			for (String temp : hasher.getVocabMap().keySet()) {
				int tempValue = hasher.getVocabMap().get(temp);
				System.out.println("(" + tempValue + "):\t"+ temp);
			}

			System.out.println("\nProbabilityMap:");
			for (String temp : hasher.getProbabilityMap().keySet()) {
				double tempValue = hasher.getProbabilityMap().get(temp);
				System.out.println("STRING:" + temp + "\tRATIO:" + tempValue);
			}
		}

		return (
			new PredictiveEngine(
			hasher,
			vocabTrie
			).exValKeys(letter, letterPosition)
		);
	}

	public void feedback (
	boolean isCorrectGuess, 
	String correctWord
	) {

	}

}