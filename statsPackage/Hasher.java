package statsPackage;
import java.util.ArrayList;
import java.util.HashMap;

import triePackage.Trie;

public class Hasher {
    public HashMap<String, Integer> hashedVocab;
    public HashMap<String, Double> ratios;
    Trie vocabTrie;

    public Hasher () {
        this.hashedVocab = new HashMap<>();
        this.ratios = new HashMap<>();
        this.vocabTrie = new Trie();
    }

    // Accessors
    public HashMap<String, Integer> getHashedVocab () {
        return hashedVocab;
    }

    public HashMap<String, Double> getHashedOldMsgs () {
        return ratios;
    }

    public int getVocabSize () {
        return hashedVocab.size();
    }

    // Modifiers
    /**
     * @param vocab vocab word
     * @param lnnum line number from the input file
     */
    public void addVocab (String vocab, int lnnum) {
        hashedVocab.put(vocab, lnnum);
    }

    /**
     * @param word      individual word from the 'old messages' file
     * @param ratio     (occurrences):(total word count)
     */
    public void addOldMsg (String word, double ratio) {
        ratios.put(word, ratio);
    }

    public void addVocabTrie (Trie trie) {
        vocabTrie = trie;
    }

    // Predicates

    // Utilities
    public void hashOldMessages (ArrayList<String> words) {
        int totalWordCount = words.size();
        for (String temp : hashedVocab.keySet()) {
            int occurrences = 0;
            for (String tempWord : words) {
                if (tempWord.equals(temp)) {
                    occurrences++;
                }
            }
            ratios.put(temp, ((double) (occurrences / totalWordCount)));
        }
    }
}