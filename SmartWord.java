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
import statsPackage.PredictiveEngine;
import triePackage.Trie;

public class SmartWord {
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
		try (BufferedReader tempBR = new BufferedReader(new FileReader(new File(oldMessageFile)))) {
			String line;
			while ((line = tempBR.readLine().toLowerCase()) != null) {
				String[] lineArr = line.split(" ");
				for (String temp : lineArr) {
					String cleanTemp = temp.replaceAll("[^a-zA-Z]+", "");
					oldMessages.add(cleanTemp);
					if (!hasher.getProbabilityMap().containsKey(cleanTemp)) {
						int newVocabHashValue = hasher.getVocabSize() + 1;
						hasher.addVocab(cleanTemp, newVocabHashValue);
					}
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