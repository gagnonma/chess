package p3;

public class King extends ChessPiece {
	public int numMove = 0;
	private boolean castledRight = false;
	private boolean castledLeft = false;

	//Getters and setters for instance variables

	public boolean hasCastledRight() {
		return castledRight;
	}

	public void setCastledRight(boolean castledRight) {
		this.castledRight = castledRight;
	}

	public boolean hasCastledLeft() {
		return castledLeft;
	}

	public void setCastledLeft(boolean castledLeft) {
		this.castledLeft = castledLeft;
	}

	public int getNumMove() {
		return numMove;
	}

	public void undoMove(){
		numMove--;
	}

	/**
	 * Constructor that uses the parent constructor
	 * @param player
	 */
	public King(Player player) {
		super(player);
	}

	public String type() {
		return "King";
	}


	/**
	 * Determines if the given move is a valid move for a king
	 * @param move
	 * @param board
	 * @return boolean
	 */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
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
		//king valid moves are the eight surrounding pieces
		for(int r = -1; r < 2; r++){
			for (int c  = -1; c < 2; c++){
				if(move.toRow == move.fromRow+r &&
						move.toColumn == move.fromColumn+c){
					numMove++;
					return true;
				}
			}
		}
		return false;
	}
}
