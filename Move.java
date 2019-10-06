package p3;

public class Move {
	public int fromRow, fromColumn, toRow, toColumn;

	/**
	 * Constructor that sets nothing
	 */
	public Move() {
	}

	/**
	 * Constructor that sets the instance variables to the given values
	 * @param fromRow
	 * @param fromColumn
	 * @param toRow
	 * @param toColumn
	 */
	public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
		this.fromRow = fromRow;
		this.fromColumn = fromColumn;
		this.toRow = toRow;
		this.toColumn = toColumn;
	}

	@Override
	public String toString() {
		return "Move [fromRow=" + fromRow + ", fromColumn=" +
				fromColumn + ", toRow=" + toRow + ", toColumn=" +
				toColumn + "]";
	}
	
	
}
