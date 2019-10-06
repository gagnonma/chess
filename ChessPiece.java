package p3;

public abstract class ChessPiece implements IChessPiece {

	private Player owner;

	protected ChessPiece(Player player) {
		this.owner = player;
	}

	public abstract String type();

	public Player player() {
		return owner;
	}

	public boolean isValidMove(Move move, IChessPiece[][] board){
		if (((move.fromRow == move.toRow) &&
				(move.fromColumn == move.toColumn))){
			//check if starting and ending position are the same
			return false;
		}else if(move.fromColumn < 0 || move.fromColumn > 7 ||
				move.fromRow < 0 || move.fromRow > 7){
			//check if starting position is off the board
			throw new IndexOutOfBoundsException();
		}else if(move.toColumn < 0 || move.toColumn > 7 ||
				move.toRow < 0 || move.toRow > 7){
			//check if ending position is off the board
			throw new IndexOutOfBoundsException();
		}else if(board[move.toRow][move.toColumn] != null){
			if (board[move.toRow][move.toColumn].player() ==
					this.player())
			//check if the ending position contains a piece of the
			// same character
			return false;
		}


		return true;
	}
}
