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
	private final Random numberGenerator;

	public BoggleBoard() {
		dice = new ArrayList<>();
		for (String faces : DIE_FACES) {
			dice.add(new Die(faces));
		}
		board = new char[4][4];
		numberGenerator = new Random();
		generateNewBoard();
	}

	public void generateNewBoard() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				board[i][j] = dice.get(4*i + j).roll();
			}
		}
	}

	public char[][] getBoardAs2DArray() {
		return board;
	}

	public void printBoard() {
		System.out.println("\n---------");
		for (int i = 0; i < 4; i++) {
			System.out.print("|");
			for (int j = 0; j < 4; j++) {
				System.out.print(board[i][j]+"|");
			}
			System.out.println("\n---------");
		}
	}

	public static void main(String[] args) {
		BoggleBoard board = new BoggleBoard();
		board.printBoard();
	}
	

}

