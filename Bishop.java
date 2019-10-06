package p3;

public class Bishop extends ChessPiece {

	/**
	 * Constructor that uses the parent constructor
	 * @param player
	 */
	public Bishop(Player player) {
		super(player);
	}

	/**
	 * Returns the type of piece it is
	 * @return
	 */
	public String type() {
		return "Bishop";
	}

	/**
	 * function that determines if the given move is valid
	 * within the rules of a bishop
	 * @param move
	 * @param board
	 * @return boolean
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		boolean valid;
		//test against the general isValidMove of the parent
		try {
			valid = super.isValidMove(move,board);
		}catch (IndexOutOfBoundsException e){
			return false;
		}
		if(!valid){
			return valid;
		}

		//use the differences to determine what direction the move is
		int rowDiff = move.toRow - move.fromRow;
		int colDiff = move.toColumn - move.fromColumn;

		//check its not the same position
		if(colDiff != 0) {
			//Going down right or up left
			if ((double)rowDiff / colDiff == 1.0) {
				//going down right
				if (rowDiff > 0) {
					for (int i = 1; i < rowDiff; i++) {
						if (board[move.fromRow + i][move.fromColumn + i] != null) {
							//check if any pieces are in the way
							return false;
						}
					}
				//going up left
				} else if (rowDiff < 0) {
					for (int i = -1; i > rowDiff; i--) {
						if (board[move.fromRow + i][move.fromColumn + i] != null) {
							return false;
						}
					}
				}
				return true;
			}
			//going up right or down left
			if ((double)rowDiff / colDiff == -1.0) {
				//going up right
				if (colDiff > 0) {
					for (int i = 1; i < colDiff; i++) {
						if (board[move.fromRow - i][move.fromColumn + i] != null) {
							return false;
						}
					}
				//going down left
				} else if (colDiff < 0) {
					for (int i = -1; i > colDiff; i--) {
						if (board[move.fromRow - i][move.fromColumn + i] != null) {
							return false;
						}
					}
				}
				return true;
			}
		}
		return false;
	}
}
