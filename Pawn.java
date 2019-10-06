package p3;

public class Pawn extends ChessPiece {
	private int numMove = 0;
	public boolean didDoubleJump = false;
	public boolean gotEnPassanted = false;

	public Pawn(Player player) {
		super(player);
	}

	public String type() {
		return "Pawn";
	}

	public void undoMove(){
		numMove--;
	}

	public int getNumMove() {
		return numMove;
	}


	// determines if the move is valid for a pawn piece
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid;
		try {
			valid = super.isValidMove(move,board);
		}catch (IndexOutOfBoundsException e){
			return false;
		}
		if(!valid){
			return false;
		}
		//multiplier based on player so that they move the right way
		int pM = 0;
		if(this.player() == Player.WHITE){
			pM = -1;
		}else if(this.player() == Player.BLACK){
			pM = 1;
		}
		// Checking valid movement for player

		if ((move.toColumn == move.fromColumn) &&
				(move.toRow == move.fromRow + 2*pM) &&
				board[move.fromRow+1*pM][move.fromColumn]==null &&
				board[move.toRow][move.toColumn] == null &&
				numMove == 0){
			//check for pawn jumping 2 spaces on first move
			this.numMove++;
			didDoubleJump =  true;
			return true;
		} else if ((move.toColumn == move.fromColumn) &&
				(move.toRow == move.fromRow + 1*pM) &&
				board[move.toRow][move.toColumn] == null) {
			//check for pawn jumping 1 space
			this.numMove++;
			return true;
		} else if(board[move.toRow][move.toColumn] != null){
			if(move.toColumn == 1 + move.fromColumn ||
				move.toColumn == move.fromColumn - 1){
				if (move.toRow == move.fromRow + 1*pM) {
					if (board[move.toRow][move.toColumn].player() !=
							this.player()) {
						//check for pawn attacking normally
						this.numMove++;
						return true;
					}
				}
			}
		}else if(board[move.toRow][move.toColumn] == null) {
			if (move.toColumn == 1 + move.fromColumn ||
					move.toColumn == move.fromColumn - 1) {
				if (move.toRow == move.fromRow + 1 * pM) {
					if (move.fromColumn + 1 < 8) {
						if (board[move.fromRow][move.fromColumn + 1]
								instanceof Pawn && move.toColumn == 1
								+ move.fromColumn) {
							Pawn temp = (Pawn) board[move.fromRow]
									[move.fromColumn + 1];
							if (temp.player().equals(player().next())){
								if (temp.getNumMove() == 1 &&
										temp.didDoubleJump) {
									//check for En Passant to the right
									this.numMove++;
									return true;
								}
							}
						}
					}
					if (move.fromColumn - 1 > -1) {
						if (board[move.fromRow][move.fromColumn - 1]
								instanceof Pawn && move.toColumn ==
								move.fromColumn - 1) {
							Pawn temp = (Pawn) board[move.fromRow]
									[move.fromColumn - 1];
							if (temp.player().equals(player().next())){
								if (temp.getNumMove() == 1 &&
										temp.didDoubleJump) {
									//check for En Passant to the left
									this.numMove++;
									return true;
								}
							}
						}
					}
				}
			}
		}else {
			return false;
		}
	return false;
	}
}
