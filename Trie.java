import java.util.HashMap;

class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean isLeafNode;

    TrieNode() {
        children = new HashMap<>();
        this.isLeafNode = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // method to insert into the trie
    // pass in the whole word from words.txt
    public void insert(String word) {
        TrieNode currentNode = root; // start adding at the root node
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            // if it trie doesn't already contain the character then make a new branch
            if (!currentNode.children.containsKey(c)); {
                currentNode.children.put(c, new TrieNode());
            }

            // move to the next node/character 
            currentNode = currentNode.children.get(c);
            
        }

        // after the loop set the last node/character as a leaf node to know its a full word
        currentNode.isLeafNode = true;
    }

    // method to get guesses/possible words 

}

