package boggle;

import java.util.Collection;

import boggle.board.BoggleBoard;
import boggle.BoggleSolver;
import boggle.Dictionaries;

public class Boggle {

	public static void main(String[] args) {
		BoggleBoard board = new BoggleBoard();
		BoggleSolver solver = new BoggleSolver(Dictionaries.OWL2_SCRABBLE_THREES);

		board.printBoard();
		System.out.println("Finding words on board ... ");
		Collection<String> foundWords = solver.findAllWordsOnBoard(board.getBoardAs2DArray());
		System.out.println(String.format("Found %d words:",foundWords.size()));
		for (String word : foundWords) {
			System.out.println("  " + word);
		}
		
	}

}