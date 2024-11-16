/*
 * Author: Caleb Brooks, Santi, Orion
 * Desc (for now). 'Trie' custom data structure for term project.
 */

package triePackage;

public class Trie {
    private TrieNode root;

    public Trie () {
        root = new TrieNode();
    }

    public void insert (final String word) {
        TrieNode current = root;
        for (char temp : word.toCharArray()) {
            if (!current.children.containsKey(temp)) {
                current.children.put(temp, new TrieNode());
            }
            current = current.children.get(temp);
        }
    }
}