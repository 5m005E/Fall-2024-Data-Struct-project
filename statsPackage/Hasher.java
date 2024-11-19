package statsPackage;
import java.util.HashMap;

public class Hasher {
    HashMap<String, Integer> hashedVocab;
    HashMap<String, Double> hashedOldMsgs;

    public Hasher () {
        this.hashedVocab = new HashMap<>();
        this.hashedOldMsgs = new HashMap<>();
    }

    // Accessors
    public HashMap<String, Integer> getHashedVocab () {
        return hashedVocab;
    }

    public HashMap<String, Double> getHashedOldMsgs () {
        return hashedOldMsgs;
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
    public void addOldMsgs (String word, double ratio) {
        hashedOldMsgs.put(word, ratio);
    }

    // Predicates

    // Utilities
}