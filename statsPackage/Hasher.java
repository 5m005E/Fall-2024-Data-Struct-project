package statsPackage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import triePackage.Trie;

public class Hasher {
    public HashMap<String, Integer> vocabMap;
    public HashMap<String, Double> probabilityMap;
    public HashMap<String, Integer> correspondenceMap;
    public HashMap<String, HashMap<String, Integer>> correspondenceMatrix;
    Trie vocabTrie;

    public Hasher () {
        this.vocabMap = new HashMap<>();
        this.probabilityMap = new HashMap<>();
        this.correspondenceMap = new HashMap<>();
        this.vocabTrie = new Trie();
    }

    // Accessors
    public HashMap<String, Integer> getVocabMap () {
        return vocabMap;
    }

    public HashMap<String, Double> getProbabilityMap () {
        return probabilityMap;
    }

    /**
     * 
     * @param letter    input letter from 'guess()'
     * @param charPos   input char position from 'guess()'
     * @return          array of most likely complete words
     */
    public HashMap<String, Double> relevantHash (char letter, int charPos) {

        HashMap<String, Double> hashed = new HashMap<>();
        List<String> relevants = new ArrayList<>(vocabTrie.find(letter, charPos));

        for (String temp : relevants) {
            hashed.put(temp, probabilityMap.get(temp));
        }
        return hashed;
    }

    public int getVocabSize () {
        return vocabMap.size();
    }

    // Modifiers
    /**
     * @param vocab vocab word
     * @param lnnum line number from the input file
     */
    public void addVocab (String vocab, int lnnum) {
        vocabMap.put(vocab, lnnum);
    }

    /**
     * @param word      individual word from the 'old messages' file
     * @param ratio     (occurrences):(total word count)
     */
    public void addProbability (String word, double ratio) {
        probabilityMap.put(word, ratio);
    }

    public void addVocabTrie (Trie trie) {
        vocabTrie = trie;
    }

    public void addCorrespondence (String a, int pos) {
        correspondenceMap.put(a, pos);
    }

    // Utilities
    public void hashOldMessages (ArrayList<String> words) {
        int totalWordCount = words.size();
        for (String temp : vocabMap.keySet()) {
            int occurrences = 0;
            for (String tempWord : words) {
                if (tempWord.equals(temp)) {
                    occurrences++;
                }
            }

            double occurrencesDouble = occurrences;
            double wordCountDouble = totalWordCount;
            double ratio = 0;
            if (occurrences > 0) {
                ratio = (occurrencesDouble / wordCountDouble) * 100;
            }

            addProbability(temp, ratio);
        }
    }


}