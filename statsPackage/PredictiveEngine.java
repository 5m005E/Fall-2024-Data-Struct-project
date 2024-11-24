package statsPackage;

import java.util.HashMap;

import triePackage.Trie;

public class PredictiveEngine {
    Hasher hasher;
    HashMap<String, Integer> vocab;
    HashMap<String, Double> ratios;
    Trie vocabTrie;
    int total;

    public PredictiveEngine (
        Hasher hasher,
        Trie vocabTrie,
        int total
    ) {
        this.hasher = hasher;
        this.vocab = hasher.getHashedVocab();
        this.ratios = hasher.getHashedOldMsgs();
        this.vocabTrie = vocabTrie;
        this.total = total;
    }

    public int expectedValue (char letter, int charPos) {
        
    }

    public double variance () {
        return 0;
    }

    public int predict () {
        return 0;
    }
}
