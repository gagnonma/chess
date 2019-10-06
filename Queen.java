package p3;

public class Queen extends ChessPiece {

	public Queen(Player player) {
		super(player);

	}

	public String type() {
		return "Queen";
		
	}

	/**
	 * Method that determines if the given move can be performed by a
	 * queen
	 * @param move
	 * @param board
	 * @return
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		boolean valid;
		//test general rules for a move
		try {
			valid = super.isValidMove(move,board);
		}catch (IndexOutOfBoundsException e){
			return false;
		}
		if(!valid){
			return valid;
		}

		//test if it can make a rook or a bishop move
		Bishop move1 = new Bishop(board[move.fromRow]
				[move.fromColumn].player());
		Rook move2 = new Rook(board[move.fromRow]
				[move.fromColumn].player());
		return (move1.isValidMove(move, board) ||
				move2.isValidMove(move, board));
	}
}
