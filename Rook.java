package p3;

public class Rook extends ChessPiece {
	private int numMove = 0;

	public void setNumMove(int numMove) {
		this.numMove = numMove;
	}


	public Rook(Player player) {
		
		super(player);
		
	}

	public String type() {
		
		return "Rook";
		
	}

	public int getNumMove() {
		return numMove;
	}

	public void undoMove(){
		numMove--;
	}

	// determines if the move is valid for a rook piece
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
		//if going horizontal check for any pieces blocking the way
		if (move.toRow == move.fromRow){
			int colDiff = move.toColumn - move.fromColumn;
			if (colDiff < 0) {
				for (int i = -1; i > colDiff;i--){
					if(board[move.fromRow][move.fromColumn+i] != null){
						return false;
					}
				}
			}else if (colDiff > 0) {
				for (int i = 1; i < colDiff;i++){
					if(board[move.fromRow][move.fromColumn+i] != null){
						return false;
					}
				}
			}
			numMove++;
			return true;
		}
		//if going vertical check for any pieces blocking the way
		if(move.toColumn == move.fromColumn){
			int rowDiff = move.toRow - move.fromRow;
			if (rowDiff < 0) {
				for (int i = -1; i > rowDiff;i--){
					if(board[move.fromRow+i][move.fromColumn] != null){
						return false;
					}
				}
			}else if (rowDiff > 0) {
				for (int i = 1; i < rowDiff;i++){
					if(board[move.fromRow+i][move.fromColumn] != null){
						return false;
					}
				}
			}
			numMove++;
			return true;
		}
		//if it doesn't go vertical or horizontal return false
        return false;
		
	}
	
}
