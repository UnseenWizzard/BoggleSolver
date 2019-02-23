package boggle;

import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import java.util.ArrayList;
import datastructures.tree.Trie;
import boggle.board.BoardIndex;
import boggle.board.BoardEntry;

public class BoggleSolver {

	private final BoardIndex[] neighbourOffsets = { 
		new BoardIndex(-1,-1), new BoardIndex(-1,0)	, new BoardIndex(-1,+1), 
		new BoardIndex(0,-1) 						, new BoardIndex(0,+1), 
		new BoardIndex(+1,-1), new BoardIndex(+1,0)	, new BoardIndex(+1,+1) 											
	};
	
	private final Trie dictionary;
	private char[][] board;

	public BoggleSolver(String[] dictionary) {
		this.dictionary = new Trie();
		for (String word : dictionary) {
			this.dictionary.insert(word);
		}
	}

	public Set<String> findAllWordsOnBoard(char[][] board) {
		this.board = board;
		Set<String> found = new HashSet<>();

		Set<Character> charactersStartingAWord = dictionary.getBeginningCharacters();
		Collection<BoardEntry> startEntries = new ArrayList<>();
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				if (charactersStartingAWord.contains(board[r][c])) {
					startEntries.add(new BoardEntry(r, c, board[r][c]));
				}
			}
		}

		for (BoardEntry entry : startEntries) {
			found.addAll(searchForWords(entry)); //this could happen in it's own thread for each
		}


		return found;
	}

	private Set<String> searchForWords(BoardEntry from) {
		return searchForWords(from, new HashSet<>(), ""+from.getValue());
	}

	private Set<String> searchForWords(BoardEntry from, Set<BoardEntry> visited, String wordSoFar) {
		Set<String> found = new HashSet<>();

		visited.add(from);
		Set<BoardEntry> neighbours = getNeighboursOf(from);
		neighbours.removeAll(visited);
		
		for (BoardEntry neighbour : neighbours) {
			String currentWord = wordSoFar + neighbour.getValue();
			if (dictionary.containsWord(currentWord)) {
				found.add(currentWord);
			}
			if (dictionary.containsPrefix(currentWord)) {
				found.addAll(searchForWords(neighbour, visited, currentWord));
			}
		}
		return found;
	}

	private Set<BoardEntry> getNeighboursOf(BoardEntry entry) {
		Set<BoardEntry> neighbours = new HashSet<>();
		for (BoardIndex offset : neighbourOffsets) {
			int neighbourRow = entry.getRow() + offset.row;
			int neighbourCol = entry.getCol() + offset.col;
			if (onBoard(neighbourRow, neighbourCol)) {
				char neighbourValue = board[neighbourRow][neighbourCol];
				neighbours.add(new BoardEntry(neighbourRow, neighbourCol, neighbourValue));
			}
		}
		return neighbours;
	}

	private boolean onBoard(int row, int col) {
		return row >= 0 && row < board.length && col >= 0 && col < board[row].length;
	}

	public static void main (String[] args) {
		char[][] board = { {'a', 'b', 's'}, {'b', 'b', 'm'}, {'x', 'a', 'r'} };

		String[] expectedWords = {"arm", "arms", "bar", "abba", "abs"};
		String[] otherWords = {"array", "salt", "sack", "bear", "bore"};
		String[] dictionary = new String[expectedWords.length + otherWords.length];
		int i = 0;
		for (String word : expectedWords) {
			dictionary[i] = word;
			i++;
		}
		for (String word : otherWords) {
			dictionary[i] = word;
			i++;
		}

		BoggleSolver solver = new BoggleSolver(dictionary);

		for (String foundWord : solver.findAllWordsOnBoard(board)) {
			System.out.println(foundWord);
		}
		
	}
}