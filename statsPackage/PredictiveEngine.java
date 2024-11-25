package statsPackage;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import triePackage.Trie;

public class PredictiveEngine {
    Hasher hasher;
    HashMap<String, Integer> vocabMap;
    HashMap<String, Double> probabilityMap;
    Trie vocabTrie;
    int total;

    public PredictiveEngine (
        Hasher hasher,
        Trie vocabTrie,
        int total
    ) {
        this.hasher = hasher;
        this.vocabMap = hasher.getVocabMap();
        this.probabilityMap = hasher.getProbabilityMap();
        this.vocabTrie = vocabTrie;
        this.total = total;
    }

    /**
     * 
     * @param letter    'guess()' function input char
     * @param charPos   'guess()' function input char index
     * @return          3 expected values corresponding to
     *                  the 3 best guesses.
     */
    public String[] exValKeys (char letter, int charPos) {
        String[] topExValKeys = new String[3];

        HashMap<String, Double> relevants = hasher.relevantHash(letter, charPos);
        PriorityQueue<Map.Entry<String, Double>> minHeap = new PriorityQueue<>(
            Comparator.comparingDouble(Map.Entry::getValue)
        );

        for (Map.Entry<String, Double> tempEntry : relevants.entrySet()) {
            minHeap.offer(tempEntry);
            if (minHeap.size() > 3) {
                minHeap.poll();
            }
        }

        for (int i = 0; i < topExValKeys.length; i++) {
            topExValKeys[i] = minHeap.poll().getKey();
        }

        return topExValKeys;
    }
}
