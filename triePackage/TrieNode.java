package triePackage;
import java.util.HashMap;

public class TrieNode {
    HashMap<Character, TrieNode> children;

    public TrieNode () {
        this.children = new HashMap<>();
    }

    /**
     * @return  true if the node has children.
     */
    public boolean leafPredicate () {
        return (children.isEmpty());
    }
}
