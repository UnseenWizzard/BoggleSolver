package boggle.board;

import java.util.Objects;

public class BoardEntry {
	BoardIndex index;
	char value;

	public BoardEntry(int row, int col, char value) {
		this.index = new BoardIndex(row, col);	
		this.value = value; 
	}

	public int getRow() {
		return index.row;
	}

	public int getCol() {
		return index.col;
	}

	public char getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "(["+index.row + "," + index.col + "] : "+ value+")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(index.row, index.col, value);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof BoardEntry) {
			BoardEntry other = (BoardEntry)o;
			return index.row==other.index.row && index.col==other.index.col && value==other.value;
		}
		return false;
	}
}