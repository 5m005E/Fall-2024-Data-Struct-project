package statsPackage;
import java.util.HashMap;

import triePackage.Trie;

public class PredictiveEngine {
    Hasher hasher;
    HashMap<String, Integer> vocab;
    HashMap<String, Double> probabilityMap;
    Trie vocabTrie;
    int total;

    public PredictiveEngine (
        Hasher hasher,
        Trie vocabTrie,
        int total
    ) {
        this.hasher = hasher;
        this.vocab = hasher.getVocabMap();
        this.probabilityMap = hasher.getProbabilityMap();
        this.vocabTrie = vocabTrie;
        this.total = total;
    }

    public int exVal (char letter, int charPos) {
        double maxExVal = Double.MIN_VALUE;

        HashMap<String, Integer> relevants = hasher.relevantHash(letter, charPos);

        return 0;
    }

    public int predict () {
        return 0;
    }
}
