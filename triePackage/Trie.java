/*
 * Authors: Caleb, Santi, Orion
 * Desc (for now). 'Trie' custom data structure for term project.
 */

package triePackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    /**
     * 
     * @param givenChar
     * @param givenIndex
     * @return
     */
    public List<String> find (char givenChar, int givenIndex) {
        List<String> resultantList = new ArrayList<>();
        collectGivenIndex(root, new StringBuilder(), givenChar, givenIndex, 0, resultantList);
        return resultantList;
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
    /**
     * @param word  word to search
     * @return      whether or not the word was found
     */
    public boolean search (String word) {
        TrieNode current = root;
        char[] wordCharArray = word.toCharArray();
        for (char temp : wordCharArray) {
            current = current.children.get(temp);
            if (current == null) {
                return false;
            }
        }
        return current.leafPredicate();
    }

    /**
     * 
     * @param node
     * @param currentString
     * @param resultantList
     */
    private void collect (TrieNode node, StringBuilder currentString, List<String> resultantList) {
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            char nextChar = entry.getKey();
            TrieNode childNode = entry.getValue();

            currentString.append(nextChar); 

            collect(childNode, currentString, resultantList);
            currentString.deleteCharAt(currentString.length() - 1); 
        }
    }

    /**
     * 
     * @param node
     * @param currentString
     * @param givenChar
     * @param targetIndex
     * @param currentIndex
     * @param resultantList
     */
    private void collectGivenIndex (
        TrieNode node,
        StringBuilder currentString,
        char givenChar,
        int targetIndex,
        int currentIndex,
        List<String> resultantList
    ) {
        if (node == null) {
            return;
        }

        if (currentIndex == targetIndex) {
            if (!node.children.containsKey(givenChar)) {
                return;
            }

            TrieNode nextNode = node.children.get(givenChar);
            currentString.append(givenChar);

            collect(nextNode, currentString, resultantList);

            currentString.deleteCharAt(currentString.length() - 1); 
            return;
        }

        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            currentString.append(entry.getKey()); 
            collectGivenIndex(entry.getValue(), currentString, givenChar, targetIndex, currentIndex + 1, resultantList);
            currentString.deleteCharAt(currentString.length() - 1); 
        }
    }

    // Predicates
    public boolean isEmpty () {
        return root.leafPredicate();
    }


}