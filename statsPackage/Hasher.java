package statsPackage;

import java.util.ArrayList;
import java.util.HashMap;

public class Hasher {
    HashMap<String, Integer> hashedVocab;
    ArrayList<String> vocab;

    public Hasher (ArrayList<String> vocab) {
        this.hashedVocab = hashVocab();
        this.vocab = vocab;
    }

    // Accessors

    // Modifiers
    public HashMap<String, Integer> hashVocab () {
        HashMap<String, Integer> inHashMap = new HashMap<>();
        for (int i = 0; i < vocab.size(); i++) {
            inHashMap.put(vocab.get(i), (i + 1));
        }
        return inHashMap;
    }

    // Predicates

    // Utilities
}