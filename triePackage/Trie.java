/*
 * Authors: Caleb, Santi, Orion
 * Desc (for now). 'Trie' custom data structure for term project.
 */

package triePackage;

public class Trie {
    private final TrieNode root;

    // Constructor
    public Trie () {
        root = new TrieNode();
    }

    // Accessors
    public TrieNode getRoot () {
        return root;
    }

    // Modifiers
    public void insert (final char[] wordCharArray) {
        TrieNode current = root;
        for (char temp : wordCharArray) {
            current.children.putIfAbsent(temp, new TrieNode());
            current = current.children.get(temp);
        }
        current.pathEnd = true;
    }

    // Utilities
    public boolean search () {
        return false;
    }

    // Predicates
    public boolean isEmpty () {
        return root.leafPredicate();
    }
}