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
	 * Parse input from the old message file.
	 * @param oldMessageFile			name of the old message file.
	 * @throws FileNotFoundException	throw this error if oldMessageFile DNE.
	 */
	public void processOldMessages (String oldMessageFile) throws FileNotFoundException {

		processVocab();

		try (final BufferedReader tempBR = new BufferedReader(new FileReader(new File(oldMessageFile)))) {
			String line;
			while ((line = tempBR.readLine()) != null) {

				line = line.toLowerCase();

				final String[] lineArr = line.split(" ");
				final int lineLength = lineArr.length - 1;

				for (int i = 0; i < lineArr.length; i++) {
					String temp = lineArr[i];
					String cleanTemp = temp.replaceAll("[^a-zA-Z]+", "");

					oldMessages.add(cleanTemp);

					hasher.addCorrespondence(cleanTemp, i, lineLength);

					if (!hasher.getVocabMap().containsKey(cleanTemp)) {
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
		try (final BufferedReader tempBR = new BufferedReader(new FileReader(new File(wordFile)))) {
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

	/**
	 * Use predictive engine to process the best guesses.
	 * @param letter			input letter
	 * @param letterPosition	input letter's position in a word
	 * @param wordPosition		relative position on a line
	 * @return					3 best guesses as a string array list
	 */
	public String[] guess (
	char letter,
	int letterPosition,
	int wordPosition
	) {
		return (
			new PredictiveEngine(
			hasher,
			vocabTrie
			).exValKeys(letter, letterPosition, wordPosition)
		);
	}

	public void feedback (
	boolean isCorrectGuess, 
	String correctWord
	) {
		if (!isCorrectGuess) {
			return;
		}

		final double feedbackWeight = 3;	// tunable weight parameter for feedback probability

		if (hasher.probabilityMap.containsKey(correctWord)) {
			double adjustProb = hasher.probabilityMap.get(correctWord) * feedbackWeight;
			hasher.probabilityMap.replace(correctWord, adjustProb);
		}
	}
}
