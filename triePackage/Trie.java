/*
 * Author: Caleb Brooks, Santi, Orion
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

    // Modifiers
    public void insert (final String word) {
        TrieNode current = root;
        for (char temp : word.toCharArray()) {
            current.children.putIfAbsent(temp, new TrieNode());
            current = current.children.get(temp);
        }
        current.pathEnd = true;
    }

    // Utilities

    // Predicates
    public boolean isEmpty () {
        return root.leafPredicate();
    }
}