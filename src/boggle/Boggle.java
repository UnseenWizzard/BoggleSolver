package boggle;

import java.util.Collection;

import boggle.board.BoggleBoard;
import boggle.BoggleSolver;
import boggle.Dictionaries;

public class Boggle {

	public static void main(String[] args) {
		int size = 4;
		if (args.length > 0) {
			try {
				size = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.err.println("\""+args[0]+"\" is not a number! If you want to start with a larger board, please supply a number as the first argument!");
				return;
			}
		}

		BoggleBoard board = new BoggleBoard(size);
		BoggleSolver solver = new BoggleSolver(Dictionaries.OWL2_SCRABBLE_THREES);

		if (size <= 25) {
			board.printBoard();
		} else {
			System.out.println("Not printing board to cmd-line because it's larger than 25x25");
		}
		System.out.println("Finding words on board ... ");
		long start = System.nanoTime();
		Collection<String> foundWords = solver.findAllWordsOnBoard(board.getBoardAs2DArray());
		long end = System.nanoTime();
		System.out.println(String.format("Found %d words in %d ms!",foundWords.size(), (end-start)/1000000));
		int wordsToPrint = (foundWords.size() > 30) ? 30 : foundWords.size();
		System.out.print("Words: {");
		String[] wordsArray = foundWords.toArray(new String[foundWords.size()]);
		int i = 0;
		for (; i < wordsToPrint-1; i++) {
			System.out.print(wordsArray[i] + ", ");
		}
		String endString = (foundWords.size() > 30) ? ", ... }" : "}";
		System.out.print(wordsArray[i] + endString + "\n");
		
	}

}