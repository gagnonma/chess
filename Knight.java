package p3;

public class Knight extends ChessPiece {

	public Knight(Player player) {
		super(player);
	}

	public String type() {
		return "Knight";
	}

	/**
	 * Determines if the given move is a valid move for a knight
	 * @param move
	 * @param board
	 * @return boolean
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board){

		boolean valid;
		//test against general rules of a move
		try {
			valid = super.isValidMove(move,board);
		}catch (IndexOutOfBoundsException e){
			return false;
		}
		if(!valid){
			return false;
		}

		//check for (r+1,c+2)(r+1,c-2)(r-1,c+2),(r-1,c-2)
		if(move.toRow == move.fromRow +1 ||
				move.toRow == move.fromRow-1){
			if(move.toColumn == move.fromColumn+2 ||
					move.toColumn == move.fromColumn-2){
				return true;
			}
		}
		//check for (r+2,c+1)(r+2,c-1)(r-2,c+1),(r-2,c-1)
		if(move.toRow == move.fromRow +2 ||
				move.toRow == move.fromRow-2){
			if(move.toColumn == move.fromColumn+1 ||
					move.toColumn == move.fromColumn-1){
				return true;
			}
		}
		return false;
		
	}

}
