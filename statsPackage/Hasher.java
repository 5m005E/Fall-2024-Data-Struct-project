package statsPackage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import triePackage.Trie;

public class Hasher {
    public HashMap<String, Integer> vocabMap;
    public HashMap<String, Double> probabilityMap;
    Trie vocabTrie;

    public Hasher () {
        this.vocabMap = new HashMap<>();
        this.probabilityMap = new HashMap<>();
        this.vocabTrie = new Trie();
    }

    // Accessors
    public HashMap<String, Integer> getVocabMap () {
        return vocabMap;
    }

    public HashMap<String, Double> getProbabilityMap () {
        return probabilityMap;
    }

    public HashMap<String, Integer> relevantHash (char letter, int charPos) {
        HashMap<String, Integer> hashed = new HashMap<>();
        List<String> relevants = vocabTrie.find(letter, charPos);

        for (String temp : relevants) {
            hashed.put(temp, vocabMap.get(temp));
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
    public void addOldMsg (String word, double ratio) {
        probabilityMap.put(word, ratio);
    }

    public void addVocabTrie (Trie trie) {
        vocabTrie = trie;
    }

    // Predicates

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
            probabilityMap.put(temp, ((double) (occurrences / totalWordCount)));
        }
    }
}