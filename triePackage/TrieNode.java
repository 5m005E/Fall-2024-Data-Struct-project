package triePackage;
import java.util.HashMap;

public class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean pathEnd;

    public TrieNode () {
        this.children = new HashMap<>();
        this.pathEnd = false;
    }
    
    // Accessors
    /**
     * @return  amt of child nodes
     */
    public int childCount () {
        return children.size();
    }

    // Modifiers


    // Utilities
    

    // Precicates
    /**
     * @return  true if the node has children.
     */
    public boolean leafPredicate () {
        return (children.isEmpty());
    }
}
