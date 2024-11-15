import java.util.HashMap;

class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap<>();
    boolean isLeadNode;

    TrieNode() {
        this.isLeadNode = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // method to insert into the trie
    public void insert() {
        
    }

    // method to get guesses/possible words 

}

