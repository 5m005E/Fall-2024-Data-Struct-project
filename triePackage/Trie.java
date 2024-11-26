/*
 * Authors: Caleb, Santi, Orion
 * Desc (for now). 'Trie' custom data structure for term project.
 */

package triePackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trie {

    private final boolean DEBUG = true;

    private final TrieNode root;
    public ArrayList<String> resultant;

    // Constructor
    public Trie () {
        this.root = new TrieNode();
        this.resultant = new ArrayList<>();
    }

    // Accessors
    public TrieNode getRoot () {
        return root;
    }

    public List<String> find (char targetChar, int targetPos) {

        this.resultant = new ArrayList<>();

        StringBuilder currentString = new StringBuilder();
        TrieNode currentNode = root;
        int currentIndex = 0;

        if (DEBUG) {
            System.out.println("Debug Trie.find():");
            System.out.printf("Params[targetChar:%c, targetPos:%d]", targetChar, targetPos);
            System.out.println();
        }

        collect(currentString, currentNode, currentIndex, targetChar, targetPos);
    
        if (DEBUG) {
            System.out.println("Resultant list:");
            if (!resultant.isEmpty()) {
                for (String temp : resultant) {
                    System.out.println(temp);
                }
            } else {
                System.out.println("Resultant list is empty.");
            }
        }
    
        return resultant;
    }

    public void collect (
        StringBuilder currentString,
        TrieNode currentNode,
        int currentIndex,
        char targetChar,
        int targetPos
    ) {
        if (DEBUG) {
            System.out.println("Debug Trie.collect():");
            System.out.printf(
                "Params[currentString:%s, currentNode:%c, currentIndex:%d, targetChar:%c, targetPos:%d]",
                
            );
        }

        if (currentNode == null) {
            return;
        }

        if (currentIndex == targetPos) {
            if (currentNode.getChildren().containsKey(targetChar)) {
                currentString.append(targetChar);
                collectAll(currentNode, currentString);

                int deleteIndex = currentString.length() - 1;
                currentString.deleteCharAt(deleteIndex);
                return;
            } else {
                return;
            }
        }

        for (Map.Entry<Character, TrieNode> tempEntry : currentNode.children.entrySet()) {
            currentString.append(tempEntry.getKey());
            currentNode = tempEntry.getValue();
            currentIndex++;
            collect(currentString, currentNode, currentIndex, targetChar, targetPos);

            int deleteIndex = currentString.length() - 1;
            currentString.deleteCharAt(deleteIndex);
        }
        
    }

    public void collectAll (
        TrieNode currentNode,
        StringBuilder currentString
    ) {
        if (currentNode == null) {
            return;
        }

        if (currentNode.leafPredicate()) {
            if (DEBUG) {
                System.out.println("Adding: " + currentString.toString());
            }
            resultant.add(currentString.toString());
        }

        for (Map.Entry<Character, TrieNode> tempEntry : currentNode.getChildren().entrySet()) {
            TrieNode child = tempEntry.getValue();

            currentString.append(tempEntry.getKey());
            collectAll(child, currentString);

            int deleteIndex = currentString.length() - 1;
            currentString.deleteCharAt(deleteIndex);
        }
    }


    // Modifiers
    public void insert (final char[] wordCharArray) {
        if (wordCharArray == null || wordCharArray.length == 0) {
            return;
        }

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
    public boolean contains (String word) {
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

    // Predicates
    public boolean isEmpty () {
        return root.leafPredicate();
    }


}