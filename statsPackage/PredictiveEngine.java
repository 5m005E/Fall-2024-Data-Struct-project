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
        this.vocab = new HashMap<>();
        this.ratios = new HashMap<>();
        this.vocabTrie = vocabTrie;
        this.total = total;
    }

    
}
