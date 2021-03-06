package boggle.board;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class BoggleBoard {

	private class Die {
		private final int sides;
		private final Map<Integer, Character> values;
		private final Random numberGenerator;

		Die(String values) {
			this.sides = values.length();
			this.values = new HashMap<>();
			this.numberGenerator = new Random();

			int i = 0;
			for (char value : values.toCharArray()) {
				this.values.put(i, value);
				i++;
			}
		}

		Character roll() {
			return values.get(numberGenerator.nextInt(sides));
		}
	}

	private static final String[] DIE_FACES = {
					"ARELSC","TABIYL","EDNSWO","BIOFXR",
                    "MCDPAE","IHFYEE","KTDNUO","MOQAJB",
                    "ESLUPT","INVTGE","ZNDVAE","UKGELY",
                    "OCATAI","ULGWIR","SPHEIN","MSHARO"};
	
	private final List<Die> dice; 
	private char[][] board;
	private final int boardSize;
	private final Random numberGenerator;

	public BoggleBoard() {
		this(4);
	}

	public BoggleBoard(int boardSize) {
		dice = new ArrayList<>();
		for (String faces : DIE_FACES) {
			dice.add(new Die(faces));
		}
		this.boardSize = boardSize;
		board = new char[boardSize][boardSize];
		numberGenerator = new Random();
		System.out.println(String.format("Constructing %d x %d BoggleBoard", boardSize, boardSize));
		generateNewBoard();
	}

	public void generateNewBoard() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				board[i][j] = dice.get( (4*i + j) % 16).roll();
			}
		}
	}

	public char[][] getBoardAs2DArray() {
		return board;
	}

	public void printBoard() {
		String rowSeperator = "\n-";
		for (int i = 0; i < boardSize; i++) {
			rowSeperator += "-";
		}
		System.out.println(rowSeperator);
		for (int i = 0; i < boardSize; i++) {
			System.out.print("|");
			for (int j = 0; j < boardSize; j++) {
				System.out.print(board[i][j]+"|");
			}
			System.out.println(rowSeperator);
		}
	}

	public static void main(String[] args) {
		BoggleBoard board = new BoggleBoard();
		board.printBoard();
	}
	

}

