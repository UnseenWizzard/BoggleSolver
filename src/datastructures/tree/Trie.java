//Trie is usefull for dictionaries, autocomplete and spellcheck
//Search is performed in O(m) where m is the lenght of the search string
//Insert also O(m)
package datastructures.tree;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

public class Trie {
	private class Node {
		char value;
		Map<Character, Node> children = new HashMap<Character, Node>();
		boolean completesWord; //for complete words only search

		Node(char value) {
			this.value = value;
		}
	}

	Node root = new Node('*');

	public Set<Character> getBeginningCharacters() {
		return root.children.keySet();
	}

	public void print() {
			preOrderTreeWalk(root, 0);
		}

	public void preOrderTreeWalk(Node node, int level) {
			if (node != null) {
				System.out.println(level+": "+node.value);
				for (Node child : node.children.values()) {
					preOrderTreeWalk(child, level+1);
				}
			}
		}

	public boolean containsWord(String word) {
		return find(word).isPresent();
	}

	public boolean containsPrefix(String word) {
		return find(root, word, false).isPresent();
	}

	public Optional<Character> find(String value) {
		return find(root, value, true);
	}

	public Optional<Character> find(Node node, String value, boolean completeWordsOnly) {
		for (char character : value.toCharArray()) {
			Node childContainingChar = node.children.get(character);
			if (childContainingChar  != null) {
				node = childContainingChar ;
			} else {
				return Optional.empty();
			}
		}
		if (completeWordsOnly && !node.completesWord) { //for complete words only search
			return Optional.empty();
		}
		return Optional.of(node.value);
	}

	public void insert(String value) {
		Node node = root;
		for (char character :  value.toCharArray()) {
			Node childContainingChar = node.children.get(character);
			if (childContainingChar != null) {
				node = childContainingChar;
			} else {
				Node newEntry = new Node(character);
				node.children.put(character, newEntry);
				node  = newEntry;
			}
		}
		node.completesWord = true; //for complete words only search
	}

	public static void main(String[] args) {
		String[] inputs = {"hello", "hell", "go", "google", "goal", "help"};
		
		Trie trie = new Trie(); 
		for (String input : inputs) {
			trie.insert(input);
		}

		System.out.println("Contains goal: "+trie.containsWord("goal"));
		System.out.println("Contains god: "+trie.containsWord("god"));
		System.out.println("Contains he: "+trie.containsWord("he")); //if it needs to strictly check for words, add a "completeWord" boolean and only return success if the curernt node completes a word
		System.out.println("Contains he as Prefix: "+trie.containsPrefix("he"));
		trie.print();
	}

}
