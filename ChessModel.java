package p3;

import java.util.Random;
import java.util.Stack;

public class ChessModel implements IChessModel {
    private IChessPiece[][] board;
    private Player player;
    public Stack<Move> moveStack = new Stack<>();
    public Stack<IChessPiece> pieceStack = new Stack<>();


    /***
     * Constructor that creates a 2d array of chess pieces and sets
     * the initial positions and player
     */
    public ChessModel() {
        board = new IChessPiece[8][8];
        player = Player.WHITE;

        //make white pieces
        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight (Player.WHITE);
        board[7][7] = new Rook(Player.WHITE);

        //make black pieces
        board[0][0] = new Rook(Player.BLACK);
        board[0][1] = new Knight(Player.BLACK);
        board[0][2] = new Bishop(Player.BLACK);
        board[0][3] = new Queen(Player.BLACK);
        board[0][4] = new King(Player.BLACK);
        board[0][5] = new Bishop(Player.BLACK);
        board[0][6] = new Knight (Player.BLACK);
        board[0][7] = new Rook(Player.BLACK);

        //make the pawns
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(Player.WHITE);
            board[1][i] = new Pawn(Player.BLACK);
        }
    }

    /**
     * Function to determine if the current player is in checkmate
     * @return boolean
     */
    public boolean isComplete() {
        Player p = currentPlayer();
        //determine if player is in check
        if(!inCheck(p)){
            return false;
        }
        //find king
        int kingR = -1;
        int kingC = -1;
        for (int r = 0; r < numRows(); r++) {
            for (int c = 0; c < numColumns(); c++) {
                if(board[r][c] != null){
                    if(board[r][c].player() == p &&
                            board[r][c] instanceof King){
                        kingR = r;
                        kingC = c;
                    }
                }
            }
        }
        //check the king's moves
        for(int row  = -1; row < 2; row++){
            for(int col = -1; col < 2;col++){
                Move kingMove = new Move(kingR,kingC,
                        kingR+row,kingC+col);
                if (isValidMove(kingMove)){
                    return false;
                }
            }
        }
        //go through the board looking for pieces of the player
        for (int r = 0; r < numRows(); r++) {
            for (int c = 0; c < numColumns(); c++) {
                if (board[r][c] != null) {
                    if (board[r][c].player() == p) {
                        if (board[r][c].type().equals("Rook")) {
                            //if piece found is a rook
                            Move rMove = rookCanMove(r, c);
                            if(rMove != null){
                                move(rMove);
                                return false;
                            }
                        }else if (board[r][c].type().equals("Bishop")){
                            //if piece found is a bishop
                            Move bMove = bishopCanMove(r, c);
                            if(bMove != null){
                                move(bMove);
                                return false;
                            }
                        }else if (board[r][c].type().equals("Queen")){
                            //if piece found is a queen
                            Move rMove = rookCanMove(r, c);
                            Move bMove = bishopCanMove(r,c);
                            if(rMove != null){
                                return false;
                            }else if(bMove != null){
                                move(bMove);
                                return false;
                            }
                        }else if (board[r][c].type().equals("Knight")){
                            //if piece found is a knight
                            Move kMove = knightCanMove(r, c);
                            if(kMove != null){
                                return false;
                            }
                        }else if (board[r][c].type().equals("Pawn")){
                            //if piece found is a pawn
                            Move pMove = pawnCanMove(r,c,p);
                            if(pMove != null){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }


    /**
     * Function that determines if a given move is valid
     * @param move Move
     * @return boolean
     */
    public boolean isValidMove(Move move) {
        //check there is a piece at the from coordinates
        if (board[move.fromRow][move.fromColumn] != null) {
            //check piece is on the current player's team
            if(board[move.fromRow][move.fromColumn].player() ==
                    currentPlayer()) {
                //check if it is a valid move using the piece's
                // specific tests
                if (board[move.fromRow]
                        [move.fromColumn].isValidMove(move, board)) {
                    //if the player is in check check if this move
                    // will take it out of check, if not then not valid
                    if(inCheck(player)) {
                        move(move);
                        if (inCheck(player.next())) {
                            undo();
                            return false;
                        } else {
                            undo();
                            return true;
                        }
                    }else{
                        return true;
                    }
                    //check specifically for castling
                }else if (isCastling(move)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCastling(Move move){
        //check specifically for castling
        if (board[move.fromRow][move.fromColumn] instanceof King){
            King king = (King) board[move.fromRow][move.fromColumn];
            Player p = king.player();
            //check if king hasn't moved
            if(king.getNumMove() == 0) {
                //check that we aren't in check
                if (!inCheck(p)) {
                    //check there is rook in the right corner
                    //and that our move is to the right
                    if (board[move.fromRow][move.fromColumn + 3]
                            instanceof Rook && move.fromRow ==
                            move.toRow && move.toColumn ==
                            move.fromColumn + 2) {
                        Rook rook = (Rook) board[move.fromRow]
                                [move.fromColumn + 3];
                        //check if the rook hasn't moved
                        if (rook.getNumMove() == 0) {
                            //make sure every space between
                            //king and rook is empty and that
                            //king does not move thru or into
                            //check
                            for (int c = 1; c < 3; c++) {
                                if (board[move.fromRow]
                                        [move.fromColumn + c]
                                        == null) {
                                    Move kMove = new Move(
                                            move.fromRow,
                                            move.fromColumn,
                                            move.fromRow,
                                            move.fromColumn + c);
                                    move(kMove);
                                    if (inCheck(p)) {
                                        undo();
                                        return false;
                                    } else {
                                        undo();
                                    }
                                } else {
                                    return false;
                                }
                            }
                            king.setCastledRight(true);
                            king.numMove++;
                            board[move.fromRow]
                                    [move.fromColumn] = king;

                            return true;
                        }
                        //check for rook in the left corner
                        //and that our move is to the left
                    } else if (board[move.fromRow]
                            [move.fromColumn - 4] instanceof Rook
                            && move.fromRow == move.toRow &&
                            move.toColumn == move.fromColumn - 2) {
                        Rook rook = (Rook) board[move.fromRow]
                                [move.fromColumn - 4];
                        //check if the rook hasn't moved
                        if (rook.getNumMove() == 0) {
                            //make sure every space between
                            //king and rook is empty and that
                            //king does not move thru or into
                            //check
                            for (int c = -1; c > -3; c--) {
                                if (board[move.fromRow]
                                        [move.fromColumn + c] == null){
                                    Move kMove = new Move(
                                            move.fromRow,
                                            move.fromColumn + c + 1,
                                            move.fromRow,
                                            move.fromColumn + c);
                                    move(kMove);
                                    if (inCheck(p)) {
                                        undo();
                                        return false;
                                    } else {
                                        undo();
                                    }
                                } else {
                                    return false;
                                }
                            }
                            if (board[move.fromRow][move.fromColumn-3]
                                    != null) {
                                return false;
                            }
                            king.setCastledLeft(true);
                            king.numMove++;
                            board[move.fromRow][move.fromColumn]=king;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Function that moves a piece based on the given move
     * @param move Move
     */
    public void move(Move move) {
        //check for castling
        if(board[move.fromRow][move.fromColumn] instanceof King){
            King king = (King)board[move.fromRow][move.fromColumn];
            if(king.hasCastledLeft()){
                pieceStack.push(board[move.fromRow]
                        [move.fromColumn-4]);
                pieceStack.push(king);
                //move king
                board[move.toRow][move.toColumn] =
                        board[move.fromRow][move.fromColumn];
                board[move.fromRow][move.fromColumn] = null;
                //move rook
                board[move.fromRow][move.fromColumn-1] =
                        board[move.fromRow][move.fromColumn-4];
                board[move.fromRow][move.fromColumn-4] = null;
                moveStack.push(move);
                player = player.next();
                return;
            }else if(king.hasCastledRight()){
                pieceStack.push(board[move.fromRow]
                        [move.fromColumn+3]);
                pieceStack.push(king);
                //move king
                board[move.toRow][move.toColumn] =
                        board[move.fromRow][move.fromColumn];
                board[move.fromRow][move.fromColumn] = null;
                //move rook
                board[move.fromRow][move.fromColumn+1] =
                        board[move.fromRow][move.fromColumn+3];
                board[move.fromRow][move.fromColumn+3] = null;
                moveStack.push(move);
                player = player.next();
                return;
            }
        }
        //check for en Passanting
        if(isEnPassant(move) == 1){
            //push the pawn that got killed
            Pawn temp = (Pawn)board[move.fromRow][move.fromColumn+1];
            temp.gotEnPassanted = true;
            pieceStack.push(temp);
            //move the piece and empty the other cells
            board[move.toRow][move.toColumn] =  board[move.fromRow]
                    [move.fromColumn];
            board[move.fromRow][move.fromColumn] = null;
            board[move.fromRow][move.fromColumn+1] = null;
            moveStack.push(move);
            player = player.next();
            return;

        }else if(isEnPassant(move) == -1){
            //push the pawn that got killed
            Pawn temp = (Pawn)board[move.fromRow][move.fromColumn-1];
            temp.gotEnPassanted = true;
            pieceStack.push(temp);
            //move the piece and empty the other cells
            board[move.toRow][move.toColumn] =  board[move.fromRow]
                    [move.fromColumn];
            board[move.fromRow][move.fromColumn] = null;
            board[move.fromRow][move.fromColumn-1] = null;
            moveStack.push(move);
            player = player.next();
            return;
        }
        //move the piece
        pieceStack.push(board[move.toRow][move.toColumn]);
        board[move.toRow][move.toColumn] =  board[move.fromRow]
                [move.fromColumn];
        board[move.fromRow][move.fromColumn] = null;
        moveStack.push(move);
        player = player.next();
    }

    /**
     * Helper method to determine if the move is an en Passant
     * @param move
     * @return 0 if not en Passant
     * return 1 if en Passanting to the right
     * return -1 if en Passanting to the left
     */
    public int isEnPassant(Move move){
        if(board[move.fromRow][move.fromColumn] instanceof Pawn){
            Pawn from = (Pawn) board[move.fromRow][move.fromColumn];
            int pM = 0;
            if(from.player() == Player.WHITE){
                pM = -1;
            }else if(from.player() == Player.BLACK){
                pM = 1;
            }
            //if en Passsanting to the right
            if (move.toColumn == 1 + move.fromColumn && move.toRow ==
                    move.fromRow + 1*pM) {
                if (board[move.fromRow][move.fromColumn + 1] instanceof
                        Pawn && move.toColumn == 1 + move.fromColumn) {

                    Pawn temp = (Pawn) board[move.fromRow]
                            [move.fromColumn + 1];
                    if (temp.player().equals(from.player().next())) {
                        if(temp.getNumMove()==1&&temp.didDoubleJump){
                            return 1;
                        }
                    }
                }
            //if en passanting to the right
            }else if (move.toColumn ==  move.fromColumn - 1 &&
                    move.toRow == move.fromRow + 1*pM) {
                if (board[move.fromRow][move.fromColumn - 1] instanceof
                        Pawn && move.toColumn == move.fromColumn - 1) {
                    Pawn temp = (Pawn) board[move.fromRow]
                            [move.fromColumn - 1];
                    if (temp.player().equals(from.player().next())) {
                        if (temp.getNumMove()==1&&temp.didDoubleJump){
                            return -1;
                        }
                    }
                }
            }
        }
        return 0;
    }


    /**
     * Function that determines if the given player is in check
     * @param  p Player
     * @return boolean
     */
    public boolean inCheck(Player p) {
        //find the king
        int kingR = -1;
        int kingC = -1;
        for (int r = 0; r < numRows(); r++) {
            for (int c = 0; c < numColumns(); c++) {
                if(board[r][c] != null){
                    if(board[r][c].player() == p &&
                            board[r][c] instanceof King){
                        kingR = r;
                        kingC = c;
                    }
                }
            }
        }
        //Goes through the board looking for pieces of opposite team
        for (int r = 0; r < numRows(); r++) {
            for (int c = 0; c < numColumns(); c++) {
                if(board[r][c] != null) {
                    if (board[r][c].player() != p) {
                        //checks if attacking the king is a valid move
                        Move m = new Move(r,c,kingR,kingC);
                        if(isValidCheckMove(m)){
                            return true;
                        }
                    }
                }
            }
        }
        //if it doesn't find a piece that can attack the king then
        //its not in check
        return false;
    }

    /**
     * Function that determines if a move is a valid move while
     * ignoring who the current player is if the move takes
     * the player out of check. Helper method for inCheck function
     * @param move
     * @return boolean
     */
    public boolean isValidCheckMove(Move move) {
        if (board[move.fromRow][move.fromColumn] != null) {
            if (board[move.fromRow][move.fromColumn].isValidMove(move, board)) {
                return true;
            }
        }
        return false;
    }

    /**
     * returns the currentPlayer
     * @return Player
     */
    public Player currentPlayer() {
        return player;
    }

    /**
     * returns the number of rows in the board
     * @return int
     */
    public int numRows() {
        return 8;
    }

    /**
     * returns the number of columns in the board
     * @return int
     */
    public int numColumns() {
        return 8;
    }

    /**
     * Returns the piece at the given coordinate
     * @param row
     * @param column
     * @return IChessPiece
     */
    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }


    /**
     * Function that undoes the last move played
     */
    public void undo(){
        //gets the last piece and move used from the stack
        if (moveStack.isEmpty()){
            return;
        }
        Move move = moveStack.pop();
        IChessPiece piece = pieceStack.pop();

        //checks for undoing a castle
        if(piece instanceof King){
            King king = (King) piece;
            if(king.hasCastledRight()){
                Rook rook = (Rook) pieceStack.pop();
                king.numMove = 0;
                king.setCastledRight(false);
                //return the king to its original spot
                board[move.fromRow][4] = king;
                board[move.toRow][move.toColumn] = null;
                //return the rook
                board[move.fromRow][7] = rook;
                board[move.fromRow][move.fromColumn+1] = null;
                player = player.next();
                return;
            }else if(king.hasCastledLeft()){
                Rook rook = (Rook) pieceStack.pop();
                king.numMove = 0;
                king.setCastledLeft(false);
                //return the king to its original spot
                board[move.fromRow][4] = king;
                board[move.toRow][move.toColumn] = null;
                //return the rook
                board[move.fromRow][0] = rook;
                board[move.fromRow][move.fromColumn-1] = null;
                player = player.next();
                return;
            }
        }

        //check if the last piece to get killed was en passanted
        if(piece instanceof Pawn){
            Pawn temp = (Pawn)piece;
            int pM = 0;
            if(temp.player() == Player.WHITE){
                pM = -1;
            }else if (temp.player() == Player.BLACK){
                pM = 1;
            }
            if(temp.gotEnPassanted){
                temp.gotEnPassanted = false;
                piece =temp;
                //subtract pawn move
                Pawn from = (Pawn)board[move.toRow][move.toColumn];
                from.undoMove();
                board[move.toRow][move.toColumn] = from;
                //undo the move
                board[move.fromRow][move.fromColumn] =
                        board[move.toRow][move.toColumn];
                board[move.toRow+pM][move.toColumn] = piece;
                board[move.toRow][move.toColumn] = null;
                player = player.next();
                return;
            }
        }
        //if the last piece used was a pawn subtract 1 from
        // its move count
        if(board[move.toRow][move.toColumn] instanceof Pawn){
            Pawn temp = (Pawn)board[move.toRow][move.toColumn];
            temp.undoMove();
            board[move.toRow][move.toColumn] = temp;
        }
        //if the last piece used was a rook subtract 1 from
        // its move count
        if(board[move.toRow][move.toColumn] instanceof Rook){
            Rook temp = (Rook) board[move.toRow][move.toColumn];
            temp.undoMove();
            board[move.toRow][move.toColumn] = temp;
        }
        //if the last piece used was a king subtract 1 from
        // its move count
        if(board[move.toRow][move.toColumn] instanceof  King){
            King temp = (King) board[move.toRow][move.toColumn];
            temp.undoMove();
            board[move.toRow][move.toColumn] = temp;
        }
        board[move.fromRow][move.fromColumn] =
                board[move.toRow][move.toColumn];
        board[move.toRow][move.toColumn] = piece;
        player = player.next();

    }


    /**
     * Function that changes the piece at (r,c) to the given piece
     * @param row
     * @param column
     * @param piece
     */
    public void setPiece(int row, int column, IChessPiece piece) {
        board[row][column] = piece;
    }

    /**
     * Function to run the AI, its priorities are as follows
     * 1)Get out of check
     * 2)Attack opponent
     * 3)Get out of danger
     * 4)randomly move a piece
     */
    public void AI() {
        Player p = currentPlayer();
        //a. Check to see if you are in check.
        // 	i. If so, get out of check by moving the king or placing a
        // 	piece to block the check
        if(inCheck(currentPlayer())){
            //find king
            int kingR = -1;
            int kingC = -1;
            for (int r = 0; r < numRows(); r++) {
                for (int c = 0; c < numColumns(); c++) {
                    if(board[r][c] != null){
                        if(board[r][c].player() == p &&
                                board[r][c] instanceof King){
                            kingR = r;
                            kingC = c;
                        }
                    }
                }
            }
            //check the king's moves
            for(int row  = -1; row < 2; row++){
                for(int col = -1; col < 2;col++){
                    Move kingMove = new Move(kingR,kingC,
                            kingR+row,kingC+col);
                    if (isValidMove(kingMove)){
                        move(kingMove);
                        return;
                    }
                }
            }
            //go through the board looking for pieces of the player
            for (int r = 0; r < numRows(); r++) {
                for (int c = 0; c < numColumns(); c++) {
                    if (board[r][c] != null) {
                        if (board[r][c].player() == p) {
                            if (board[r][c].type().equals("Rook")) {
                                //if piece found is a rook
                                Move rMove = rookCanMove(r, c);
                                if(rMove != null){
                                    move(rMove);
                                    return;
                                }
                            } else if (board[r]
                                    [c].type().equals("Bishop")){
                                //if piece found is a bishop
                                Move bMove = bishopCanMove(r, c);
                                if(bMove != null){
                                    move(bMove);
                                    return;
                                }
                            } else if (board[r]
                                    [c].type().equals("Queen")) {
                                //if piece found is a queen
                                Move rMove = rookCanMove(r, c);
                                Move bMove = bishopCanMove(r,c);
                                if(rMove != null){
                                    move(rMove);
                                    return;
                                }else if(bMove != null){
                                    move(bMove);
                                    return;
                                }
                            } else if (board[r]
                                    [c].type().equals("Knight")) {
                                //if piece found is a knight
                                Move kMove = knightCanMove(r, c);
                                if(kMove != null){
                                    move(kMove);
                                    return;
                                }
                            } else if (board[r]
                                    [c].type().equals("Pawn")) {
                                //if piece found is a pawn
                                Move pMove = pawnCanMove(r,c,p);
                                if(pMove != null){
                                    move(pMove);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            return;
        }
		/*
		Part B Attempt to attack enemy pieces
		 */
        for (int r = 0; r < numRows(); r++) {
            for (int c = 0; c < numColumns(); c++) {
                if (board[r][c] != null) {
                    if (board[r][c].player() == p) {
                        if (board[r][c].type().equals("Rook")) {
                            //if piece found is a rook
                            Move rMove = rookCanAttack(r,c,p);
                            if(rMove != null){
                                move(rMove);
                                return;
                            }
                        }else if (board[r][c].type().equals("Bishop")){
                            //if piece found is a bishop
                            Move bMove = bishopCanAttack(r,c,p);
                            if(bMove != null){
                                move(bMove);
                                return;
                            }
                        }else if (board[r][c].type().equals("Queen")){
                            //if piece found is a queen
                            Move rMove = rookCanAttack(r,c,p);
                            Move bMove = bishopCanAttack(r,c,p);
                            if(rMove != null){
                                move(rMove);
                                return;
                            }else if(bMove != null){
                                move(bMove);
                                return;
                            }
                        }else if (board[r][c].type().equals("Knight")){
                            //if piece found is a knight
                            Move kMove = knightCanAttack(r,c,p);
                            if(kMove != null){
                                move(kMove);
                                return;
                            }
                        }else if (board[r][c].type().equals("Pawn")){
                            //if piece found is a pawn
                            Move pMove = pawnCanAttack(r,c,p);
                            if(pMove != null){
                                move(pMove);
                                return;
                            }
                        }
                    }
                }
            }
        }
		/*
		Part C Attempt to get piece out of danger
		 */
        for (int r = 0; r < numRows(); r++) {
            for (int c = 0; c < numColumns(); c++) {
                if (board[r][c] != null) {
                    if (board[r][c].player() == p) {
                        if (board[r][c].type().equals("Rook")) {
                            //if piece found is a rook
                            if(inDanger(r,c,p)){
                                Move rMove = rookCanEscape(r,c,p);
                                if(rMove != null){
                                    move(rMove);
                                    return;
                                }
                            }
                        }else if (board[r][c].type().equals("Bishop")){
                            //if piece found is a bishop
                            if(inDanger(r,c,p)){
                                Move bMove = bishopCanEscape(r,c,p);
                                if(bMove != null){
                                    move(bMove);
                                    return;
                                }
                            }
                        }else if (board[r][c].type().equals("Queen")){
                            //if piece found is a queen
                            if(inDanger(r,c,p)){
                                Move rMove = rookCanEscape(r,c,p);
                                Move bMove = bishopCanEscape(r,c,p);
                                if(rMove != null){
                                    move(rMove);
                                    return;
                                }else if(bMove != null){
                                    move(bMove);
                                    return;
                                }
                            }
                        }else if (board[r][c].type().equals("Knight")){
                            //if piece found is a knight
                            if(inDanger(r,c,p)){
                                Move kMove = knightCanEscape(r,c,p);
                                if(kMove != null){
                                    move(kMove);
                                    return;
                                }
                            }
                        }else if (board[r][c].type().equals("Pawn")){
                            //if piece found is a pawn
                            if(inDanger(r,c,p)){
                                Move pMove = pawnCanEscape(r,c,p);
                                if(pMove != null){
                                    move(pMove);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
		/*
		Part D
		Move random piece
		 */
        Random rand = new Random();
        int randR = rand.nextInt(8);
        int randC = rand.nextInt(8);
        boolean done = false;
        while(!done){
            if(board[randR][randC] != null){
                if(board[randR][randC].player().equals(p)){
                    if (board[randR][randC].type().equals("Rook")) {
                        //if piece found is a rook
                        Move rMove = rookCanEscape(randR,randC,p);
                        if(rMove != null){
                            move(rMove);
                            done = true;
                        }
                    } else if (board[randR]
                            [randC].type().equals("Bishop")) {
                        //if piece found is a bishop
                        Move bMove = bishopCanEscape(randR,randC,p);
                        if(bMove != null){
                            move(bMove);
                            done = true;
                        }
                    } else if (board[randR]
                            [randC].type().equals("Queen")) {
                        //if piece found is a queen
                        Move rMove = rookCanEscape(randR,randC,p);
                        Move bMove = bishopCanEscape(randR,randC,p);
                        if(rMove != null){
                            move(rMove);
                            done = true;
                        }else if(bMove != null){
                            move(bMove);
                            done = true;
                        }
                    } else if (board[randR]
                            [randC].type().equals("Knight")) {
                        //if piece found is a knight
                        Move kMove = knightCanEscape(randR,randC,p);
                        if(kMove != null){
                            move(kMove);
                            done = true;
                        }
                    } else if (board[randR]
                            [randC].type().equals("Pawn")) {
                        //if piece found is a pawn
                        Move pMove = pawnCanEscape(randR,randC,p);
                        if(pMove != null){
                            move(pMove);
                            done = true;
                        }
                    }
                }
            }
            randR = rand.nextInt(8);
            randC = rand.nextInt(8);
        }
    }

    /**
     * Function that determines if a pawn at (r,c) can move to
     * stop a checkmate
     * @param r int
     * @param c int
     * @param p Player
     * @return boolean
     */
    public Move pawnCanMove(int r, int c, Player p){
        int pM = 0;
        if (p == Player.WHITE){
            pM = -1;
        }else if (p == Player.BLACK){
            pM = 1;
        }

        //check pawn jumping 1 space
        Move pMove = new Move(r, c, r + pM, c);
        if(isValidMove(pMove)) {
            return pMove;
        }
        //check pawn jumping 2 spaces
        pMove = new Move(r, c, r + 2*pM, c);
        if(isValidMove(pMove)) {
            return pMove;
        }
        //check pawn attacks right
        pMove = new Move(r, c, r + pM, c+1);
        if(isValidMove(pMove)) {
            return pMove;
        }
        //check pawn attacks left
        pMove = new Move(r, c, r + pM, c-1);
        if(isValidMove(pMove)) {
            return pMove;
        }
        return null;
    }

    /**
     * Function that determines if a rook at (r,c) can move to
     * stop a checkmate
     * @param r int
     * @param c int
     * @return boolean
     */
    public Move rookCanMove(int r, int c){
        //check moves to the right
        for(int col = 1; col < 8;col++){
            Move rMove = new Move(r,c,r,c+col);
            if(isValidMove(rMove)){
                return rMove;
            }
        }
        //check moves to the left
        for(int col = -1; col > -8;col--){
            Move rMove = new Move(r,c,r,c+col);
            if(isValidMove(rMove)){
                return rMove;
            }
        }
        //check moves upward
        for(int row = -1; row > -8;row--){
            Move rMove = new Move(r,c,r+row,c);
            if(isValidMove(rMove)){
                return rMove;
            }
        }
        //check moves downward
        for(int row = 1; row < 8;row++){
            Move rMove = new Move(r,c,r+row,c);
            if(isValidMove(rMove)){
                return rMove;
            }
        }


        return null;
    }

    /**
     * Function that determines if a bishop at (r,c) can move to
     * stop a checkmate
     * @param r int
     * @param c int
     * @return boolean
     */
    public Move bishopCanMove(int r, int c){
        //check moves to the down right
        for(int i = 1; i < 8;i++){
            Move bMove = new Move(r,c,r+i,c+i);
            if(isValidMove(bMove)){
                return bMove;
            }
        }
        //check moves to the up right
        for(int i = 1; i < 8;i++){
            Move bMove = new Move(r,c,r-i,c+i);
            if(isValidMove(bMove)){
                return bMove;
            }
        }
        //check moves to the down left
        for(int i = 1; i < 8;i++){
            Move bMove = new Move(r,c,r+i,c-i);
            if(isValidMove(bMove)){
                return bMove;
            }
        }

        //check moves to the up left
        for(int i = 1; i < 8;i++){
            Move bMove = new Move(r,c,r-i,c-i);
            if(isValidMove(bMove)){
                return bMove;
            }
        }

        return null;
    }

    /**
     * Function that determines if a knight at (r,c) can move to
     * stop a checkmate
     * @param r int
     * @param c int
     * @return boolean
     */
    public Move knightCanMove(int r,int c){
        for(int row = 1; row < 3; row++){
            for(int rM = 1; rM > -2; rM -=2){
                if(row == 1){
                    for(int cM = 1; cM > -2; cM -= 2){
                        //checks moves:(r+1,c+2),(r+1,c-2),
                        // (r-1,c+2),(r-1,c-2)
                        Move kMove = new Move(r,c,r+(row*rM),c+(2*cM));
                        if(isValidMove(kMove)){
                            return kMove;
                        }
                    }
                }else if(row == 2){
                    for(int cM = 1; cM > -2; cM -= 2){
                        //checks moves: (r+2,c+1),(r+2,c-1),
                        // (r-2,c+1),(r-2,c-1)
                        Move kMove = new Move(r,c,r+(row*rM),c+(1*cM));
                        if(isValidMove(kMove)){
                            return kMove;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Function that determines if a pawn at (r,c) can move to
     * kill an enemy
     * @param r int
     * @param c int
     * @param p Player
     * @return boolean
     */
    public Move pawnCanAttack(int r, int c, Player p){
        int pM = 0;
        if (p == Player.BLACK){
            pM = 1;
        }else if (p == Player.WHITE){
            pM = -1;
        }
        //check pawn jumps down right
        Move pMove = new Move(r, c, r + pM, c+1);
        if(isValidMove(pMove)) {
            if(board[pMove.toRow][pMove.toColumn] != null){
                if(!board[pMove.toRow]
                        [pMove.toColumn].type().equals(p)){
                    return pMove;
                }
            }
        }
        //check pawn jumps down left
        pMove = new Move(r, c, r + pM, c-1);
        if(isValidMove(pMove)) {
            if(board[pMove.toRow][pMove.toColumn] != null){
                if(!board[pMove.toRow]
                        [pMove.toColumn].type().equals(p)){
                    return pMove;
                }
            }
        }


        return null;
    }

    /**
     * Function that determines if a rook at (r,c) can move to
     * kill an enemy
     * @param r int
     * @param c int
     * @param p Player
     * @return boolean
     */
    public Move rookCanAttack(int r, int c,Player p){
        //check moves to the right
        for(int col = 1; col < 8;col++){
            Move rMove = new Move(r,c,r,c+col);
            if(isValidMove(rMove)){
                if(board[rMove.toRow][rMove.toColumn] != null){
                    if(!board[rMove.toRow]
                            [rMove.toColumn].type().equals(p)){
                        return rMove;
                    }
                }
            }else{
                //break out of loop
                col = 9;
            }
        }
        //check moves to the left
        for(int col = -1; col > -8;col--){
            Move rMove = new Move(r,c,r,c+col);
            if(isValidMove(rMove)){
                if(board[rMove.toRow][rMove.toColumn] != null){
                    if(!board[rMove.toRow]
                            [rMove.toColumn].type().equals(p)){
                        return rMove;
                    }
                }
            }else{
                //break out of loop
                col = -9;
            }
        }
        //check moves upward
        for(int row = -1; row > -8;row--){
            Move rMove = new Move(r,c,r+row,c);
            if(isValidMove(rMove)){
                if(board[rMove.toRow][rMove.toColumn] != null){
                    if(!board[rMove.toRow]
                            [rMove.toColumn].type().equals(p)){
                        return rMove;
                    }
                }
            }else{
                //break out of loop
                row = -9;
            }
        }
        //check moves downward
        for(int row = 1; row < 8;row++){
            Move rMove = new Move(r,c,r+row,c);
            if(isValidMove(rMove)){
                if(board[rMove.toRow][rMove.toColumn] != null){
                    if(!board[rMove.toRow]
                            [rMove.toColumn].type().equals(p)){
                        return rMove;
                    }
                }
            }else{
                //break out of loop
                row = 9;
            }
        }


        return null;
    }

    /**
     * Function that determines if a bishop at (r,c) can move to
     * kill an enemy
     * @param r int
     * @param c int
     * @param p Player
     * @return boolean
     */
    public Move bishopCanAttack(int r, int c,Player p){
        //check moves to the down right
        for(int i = 1; i < 8;i++){
            Move bMove = new Move(r,c,r+i,c+i);
            if(isValidMove(bMove)){
                if(board[bMove.toRow][bMove.toColumn] != null){
                    if(!board[bMove.toRow]
                            [bMove.toColumn].type().equals(p)){
                        return bMove;
                    }
                }
            }else{
                //break out of loop
                i = 9;
            }
        }
        //check moves to the up right
        for(int i = 1; i < 8;i++){
            Move bMove = new Move(r,c,r-i,c+i);
            if(isValidMove(bMove)){
                if(board[bMove.toRow][bMove.toColumn] != null){
                    if(!board[bMove.toRow]
                            [bMove.toColumn].type().equals(p)){
                        return bMove;
                    }
                }
            }else{
                //break out of loop
                i = 9;
            }
        }
        //check moves to the down left
        for(int i = 1; i < 8;i++){
            Move bMove = new Move(r,c,r+i,c-i);
            if(isValidMove(bMove)){
                if(board[bMove.toRow][bMove.toColumn] != null){
                    if(!board[bMove.toRow]
                            [bMove.toColumn].type().equals(p)){
                        return bMove;
                    }
                }
            }else{
                //break out of loop
                i = 9;
            }
        }

        //check moves to the up left
        for(int i = 1; i < 8;i++){
            Move bMove = new Move(r,c,r-i,c-i);
            if(isValidMove(bMove)){
                if(board[bMove.toRow][bMove.toColumn] != null){
                    if(!board[bMove.toRow]
                            [bMove.toColumn].type().equals(p)){
                        return bMove;
                    }
                }
            }else{
                //break out of loop
                i = 9;
            }
        }

        return null;
    }

    /**
     * Function that determines if a knight at (r,c) can move to
     * kill an enemy
     * @param r int
     * @param c int
     * @param p Player
     * @return boolean
     */
    public Move knightCanAttack(int r,int c,Player p){
        for(int row = 1; row < 3; row++){
            for(int rM = 1; rM > -2; rM -=2){
                if(row == 1){
                    for(int cM = 1; cM > -2; cM -= 2){
                        //checks moves:(r+1,c+2),(r+1,c-2),(r-1,c+2),
                        // (r-1,c-2)
                        Move kMove = new Move(r,c,r+(row*rM),c+(2*cM));
                        if(isValidMove(kMove)){
                            if(board[kMove.toRow]
                                    [kMove.toColumn] != null){
                                if(!(board[kMove.toRow]
                                        [kMove.toColumn].player()==p)){
                                    return kMove;
                                }
                            }
                        }
                    }
                }else if(row == 2){
                    for(int cM = 1; cM > -2; cM -= 2){
                        //checks moves: (r+2,c+1),(r+2,c-1),
                        // (r-2,c+1),(r-2,c-1)
                        Move kMove = new Move(r,c,r+(row*rM),c+(1*cM));
                        if(isValidMove(kMove)){
                            if(board[kMove.toRow]
                                    [kMove.toColumn] != null){
                                if(!(board[kMove.toRow]
                                        [kMove.toColumn].player()==p)){
                                    return kMove;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Function that determines if the given position can be attacked
     * by the other team
     * @param  p Player
     * @return boolean
     */
    public boolean inDanger(int row, int col, Player p) {
        //Goes through the board looking for pieces of opposite team
        for (int r = 0; r < numRows(); r++) {
            for (int c = 0; c < numColumns(); c++) {
                if(board[r][c] != null) {
                    if (board[r][c].player() != p) {
                        //checks if attacking the the piece is a valid move
                        Move m = new Move(r,c,row,col);
                        if(isValidCheckMove(m)){
                            return true;
                        }
                    }
                }
            }
        }
        //if it doesn't find a piece that can attack the position then
        //its not in check
        return false;
    }

    /**
     * Function that determines if a pawn at (r,c) can move
     * @param r int
     * @param c int
     * @param p Player
     * @return boolean
     */
    public Move pawnCanEscape(int r, int c, Player p){
        int pM = 0;
        if (p == Player.BLACK){
            pM = 1;
        }else if (p == Player.WHITE){
            pM = -1;
        }
        //check pawn jumping 2 spaces
        Move pMove = new Move(r, c, r + 2*pM, c);
        if(isValidMove(pMove)) {
            if(!inDanger(r + 2*pM,c,p)){
                return pMove;
            }
        }

        //check pawn jumping 1 space
        pMove = new Move(r, c, r + pM, c);
        if(isValidMove(pMove)) {
            if(!inDanger(r + pM,c,p)){
                return pMove;
            }
        }

        //check pawn jumps down right
        pMove = new Move(r, c, r + pM, c+1);
        if(isValidMove(pMove)) {
            if(!inDanger(r + pM,c+1,p)){
                return pMove;
            }
        }
        //check pawn jumps down left
        pMove = new Move(r, c, r + pM, c-1);
        if(isValidMove(pMove)) {
            if(!inDanger(r + 2*pM,c-1,p)){
                return pMove;
            }
        }
        return null;
    }

    /**
     * Function that determines if a rook at (r,c) can move
     * @param r int
     * @param c int
     * @param p Player
     * @return boolean
     */
    public Move rookCanEscape(int r, int c,Player p){
        //check moves to the right
        for(int col = 1; col < 8;col++){
            Move rMove = new Move(r,c,r,c+col);
            if(isValidCheckMove(rMove)){
                if(!inDanger(r,c+col,p)){
                    return rMove;
                }
            }else{
                //break out of loop
                col = 9;
            }
        }
        //check moves to the left
        for(int col = -1; col > -8;col--){
            Move rMove = new Move(r,c,r,c+col);
            if(isValidMove(rMove)){
                if(!inDanger(r,c+col,p)){
                    return rMove;
                }
            }else{
                //break out of loop
                col = -9;
            }
        }
        //check moves upward
        for(int row = -1; row > -8;row--){
            Move rMove = new Move(r,c,r+row,c);
            if(isValidMove(rMove)){
                if(!inDanger(r+row,c,p)){
                    return rMove;
                }
            }else{
                //break out of loop
                row = -9;
            }
        }
        //check moves downward
        for(int row = 1; row < 8;row++){
            Move rMove = new Move(r,c,r+row,c);
            if(isValidMove(rMove)){
                if(!inDanger(r+row,c,p)){
                    return rMove;
                }
            }else{
                //break out of loop
                row = 9;
            }
        }


        return null;
    }

    /**
     * Function that determines if a bishop at (r,c) can move
     * @param r int
     * @param c int
     * @param p Player
     * @return boolean
     */
    public Move bishopCanEscape(int r, int c,Player p){
        //check moves to the down right
        for(int i = 1; i < 8;i++){
            Move bMove = new Move(r,c,r+i,c+i);
            if(isValidMove(bMove)){
                if(!inDanger(r+i,c+i,p)){
                    return bMove;
                }
            }else{
                //break out of loop
                i = 9;
            }
        }
        //check moves to the up right
        for(int i = 1; i < 8;i++){
            Move bMove = new Move(r,c,r-i,c+i);
            if(isValidMove(bMove)){
                if(!inDanger(r-i,c+i,p)){
                    return bMove;
                }
            }else{
                //break out of loop
                i = 9;
            }
        }
        //check moves to the down left
        for(int i = 1; i < 8;i++){
            Move bMove = new Move(r,c,r+i,c-i);
            if(isValidMove(bMove)){
                if(!inDanger(r+i,c-i,p)){
                    return bMove;
                }
            }else{
                //break out of loop
                i = 9;
            }
        }

        //check moves to the up left
        for(int i = 1; i < 8;i++){
            Move bMove = new Move(r,c,r-i,c-i);
            if(isValidMove(bMove)){
                if(!inDanger(r-i,c-i,p)){
                    return bMove;
                }
            }else{
                //break out of loop
                i = 9;
            }
        }

        return null;
    }

    /**
     * Function that determines if a knight at (r,c)
     * @param r int
     * @param c int
     * @param p Player
     * @return boolean
     */
    public Move knightCanEscape(int r,int c,Player p){
        for(int row = 1; row < 3; row++){
            for(int rM = 1; rM > -2; rM -=2){
                if(row == 1){
                    for(int cM = 1; cM > -2; cM -= 2){
                        //checks moves:(r+1,c+2),(r+1,c-2),
                        // (r-1,c+2),(r-1,c-2)
                        Move kMove = new Move(r,c,r+(row*rM),c+(2*cM));
                        if(isValidMove(kMove)){
                            if(!inDanger(r+(row*rM),c+(2*cM),p)){
                                return kMove;
                            }
                        }
                    }
                }else if(row == 2){
                    for(int cM = 1; cM > -2; cM -= 2){
                        //checks moves: (r+2,c+1),(r+2,c-1),
                        // (r-2,c+1),(r-2,c-1)
                        Move kMove = new Move(r,c,r+(row*rM),c+(cM));
                        if(isValidMove(kMove)){
                            if(!inDanger(r+(row*rM),c+(cM),p)){
                                return kMove;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

}
