package p3;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChessModelTest {

    /**
     * King is only white piece
     * Not in check
     */
    @Test
    public void isComplete0() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(3,3,new King(Player.WHITE));

        assertFalse(board.isComplete());

    }

    /**
     * King is only white piece
     * King has no available moves
     */
    @Test
    public void isComplete1() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(3,3,new King(Player.WHITE));
        board.setPiece(2,0,new Rook(Player.BLACK));
        board.setPiece(3,0,new Rook(Player.BLACK));
        board.setPiece(4,0,new Rook(Player.BLACK));

        assertTrue(board.isComplete());

    }

    /**
     * King is only white piece
     * King has s valid move
     */
    @Test
    public void isComplete2() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(3,3,new King(Player.WHITE));
        board.setPiece(2,0,new Rook(Player.BLACK));
        board.setPiece(3,0,new Rook(Player.BLACK));

        assertFalse(board.isComplete());

    }

    /**
     * White:King(0,0) Rook(0,1) Rook(1,1)
     * Black: Rook(5,0)
     * Rook moves left saves king
     */
    @Test
    public void isComplete3() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(0,0,new King(Player.WHITE));
        board.setPiece(0,1,new Rook(Player.WHITE));
        board.setPiece(1,1,new Rook(Player.WHITE));
        board.setPiece(5,0,new Rook(Player.BLACK));

        assertFalse(board.isComplete());

    }
    /**
     * White:King(0,0) Bishop(0,1) Bishop(1,0) Bishop (2,0)
     * Black: Bishop(2,2)
     * Bishop moves upRight saves king
     */
    @Test
    public void isComplete4() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(0,0,new King(Player.WHITE));
        board.setPiece(0,1,new Bishop(Player.WHITE));
        board.setPiece(1,0,new Bishop(Player.WHITE));
        board.setPiece(2,0,new Bishop(Player.WHITE));
        board.setPiece(2,2,new Bishop(Player.BLACK));

        assertFalse(board.isComplete());

    }

    /**
     * White:King(0,0) Bishop(0,1) Bishop(1,0) Knight(0,3)
     * Black: Bishop(2,2)
     * Knight r+1, col-2 save king
     */
    @Test
    public void isComplete5() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(0,0,new King(Player.WHITE));
        board.setPiece(0,1,new Bishop(Player.WHITE));
        board.setPiece(1,0,new Bishop(Player.WHITE));
        board.setPiece(0,3,new Knight(Player.WHITE));
        board.setPiece(2,2,new Bishop(Player.BLACK));

        assertFalse(board.isComplete());

    }

    /**
     * White:King(7,0) Bishop(6,0) Pawn(7,1)
     * Black: Bishop(5,2)
     * Pawn Moves up 1 save king
     */
    @Test
    public void isComplete6() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(7,0,new King(Player.WHITE));
        board.setPiece(6,0,new Bishop(Player.WHITE));
        board.setPiece(7,1,new Pawn(Player.WHITE));
        board.setPiece(5,2,new Bishop(Player.BLACK));

        assertFalse(board.isComplete());

    }

    /**
     * White:King(0,0) Rook(0,1) Queen(1,1)
     * Black: Rook(5,0)
     * Queen moves left saves king
     */
    @Test
    public void isComplete7() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(0,0,new King(Player.WHITE));
        board.setPiece(0,1,new Rook(Player.WHITE));
        board.setPiece(1,1,new Queen(Player.WHITE));
        board.setPiece(5,0,new Rook(Player.BLACK));

        assertFalse(board.isComplete());

    }
    /**
     * White:King(0,0) Bishop(0,1) Bishop(1,0) Queen (2,0)
     * Black: Bishop(2,2)
     * Queen moves upRight saves king
     */
    @Test
    public void isComplete8() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(0,0,new King(Player.WHITE));
        board.setPiece(0,1,new Bishop(Player.WHITE));
        board.setPiece(1,0,new Bishop(Player.WHITE));
        board.setPiece(4,0,new Queen(Player.WHITE));
        board.setPiece(2,2,new Bishop(Player.BLACK));

        assertFalse(board.isComplete());

    }

    /**
     * Move is valid, not in check to start
     */
    @Test
    public void isValid0() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(3,3,new Pawn(Player.WHITE));
        Move move = new Move(3,3,2,3);

        assertTrue(board.isValidMove(move));

    }



    /**
     * Testing castling right
     * Should work
     */
    @Test
    public void isValidCastlingRight1() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(7,4,new King(Player.WHITE));
        board.setPiece(7,7,new Rook(Player.WHITE));

        Move kMove = new Move(7,4,7,6);

        assertTrue(board.isValidMove(kMove));

    }

    /**
     * Testing castling right
     * Moves into check return false
     */
    @Test
    public void isValidCastlingRight2() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(7,4,new King(Player.WHITE));
        board.setPiece(7,7,new Rook(Player.WHITE));
        board.setPiece(4,5,new Rook(Player.BLACK));

        Move kMove = new Move(7,4,7,6);

        assertFalse(board.isValidMove(kMove));

    }

    /**
     * Testing castling right
     * There is a piece in the way
     * return false
     */
    @Test
    public void isValidCastlingRight3() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(7,4,new King(Player.WHITE));
        board.setPiece(7,7,new Rook(Player.WHITE));
        board.setPiece(7,5,new Rook(Player.WHITE));

        Move kMove = new Move(7,4,7,6);

        assertFalse(board.isValidMove(kMove));

    }

    /**
     * Testing castling left
     * Should work
     */
    @Test
    public void isValidCastlingLeft1() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(7,4,new King(Player.WHITE));
        board.setPiece(7,0,new Rook(Player.WHITE));

        Move kMove = new Move(7,4,7,2);

        assertTrue(board.isValidMove(kMove));

    }

    /**
     * Testing castling left
     * Moves into check return false
     */
    @Test
    public void isValidCastlingLeft2() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(7,4,new King(Player.WHITE));
        board.setPiece(7,0,new Rook(Player.WHITE));
        board.setPiece(4,3,new Rook(Player.BLACK));

        Move kMove = new Move(7,4,7,2);

        assertFalse(board.isValidMove(kMove));

    }

    /**
     * Testing castling left
     * There is a piece in the way
     * return false
     */
    @Test
    public void isValidCastlingLeft3() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(7,4,new King(Player.WHITE));
        board.setPiece(7,0,new Rook(Player.WHITE));
        board.setPiece(7,2,new Rook(Player.WHITE));

        Move kMove = new Move(7,4,7,2);

        assertFalse(board.isValidMove(kMove));

    }

    /**
     * Testing castling left
     * There is a piece to the right of the rook
     * return false
     */
    @Test
    public void isValidCastlingLeft4() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(7,4,new King(Player.WHITE));
        board.setPiece(7,0,new Rook(Player.WHITE));
        board.setPiece(7,1,new Rook(Player.WHITE));

        Move kMove = new Move(7,4,7,2);

        assertFalse(board.isValidMove(kMove));

    }

    /**
     * Testing castling left in move function
     */
    @Test
    public void MoveCastlingLeft1() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(7,4,new King(Player.WHITE));
        board.setPiece(7,0,new Rook(Player.WHITE));

        Move kMove = new Move(7,4,7,2);
        board.isValidMove(kMove);
        board.move(kMove);


        assertTrue(board.pieceAt(7,2) instanceof  King);
        assertTrue(board.pieceAt(7,3) instanceof  Rook);

    }

    /**
     * Testing castling right in move function
     */
    @Test
    public void MoveCastlingRight1() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(7,4,new King(Player.WHITE));
        board.setPiece(7,7,new Rook(Player.WHITE));

        Move kMove = new Move(7,4,7,6);
        board.isValidMove(kMove);
        board.move(kMove);


        assertTrue(board.pieceAt(7,6) instanceof  King);
        assertTrue(board.pieceAt(7,5) instanceof  Rook);

    }

    /**
     * Testing En Passanting right in move function
     */
    @Test
    public void MoveEnPassantRight() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(4,6,new Pawn(Player.WHITE));
        board.setPiece(1,7,new Pawn(Player.BLACK));

        //move white pawn up 1
        Move pMove = new Move(4,6,3,6);
        board.isValidMove(pMove);
        board.move(pMove);
        //move black pawn down 2
        pMove = new Move(1,7,3,7);
        board.isValidMove(pMove);
        board.move(pMove);
        //En Passant to the right
        pMove = new Move(3,6,2,7);
        board.isValidMove(pMove);
        board.move(pMove);


        assertTrue(board.pieceAt(2,7) instanceof  Pawn);
        assertTrue(board.pieceAt(3,7) == null);

    }

    /**
     * Testing En Passanting left in move function
     */
    @Test
    public void MoveEnPassantLeft() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(4,6,new Pawn(Player.BLACK));
        board.setPiece(6,5,new Pawn(Player.WHITE));

        //move white pawn up 2
        Move pMove = new Move(6,5,4,5);
        board.isValidMove(pMove);
        board.move(pMove);
        //En Passant to the left
        pMove = new Move(4,6,5,5);
        board.isValidMove(pMove);
        board.move(pMove);


        assertTrue(board.pieceAt(5,5) instanceof  Pawn);
        assertTrue(board.pieceAt(4,5) == null);

    }

    /**
     * Testing undoing castling right
     */
    @Test
    public void UndoCastlingRight1() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(7,4,new King(Player.WHITE));
        board.setPiece(7,7,new Rook(Player.WHITE));

        Move kMove = new Move(7,4,7,6);
        board.isValidMove(kMove);
        board.move(kMove);

        board.undo();


        assertTrue(board.pieceAt(7,4) instanceof  King);
        assertTrue(board.pieceAt(7,7) instanceof  Rook);

    }

    /**
     * Testing undoing castling left
     */
    @Test
    public void UndoCastlingLeft1() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(7,4,new King(Player.WHITE));
        board.setPiece(7,0,new Rook(Player.WHITE));

        Move kMove = new Move(7,4,7,2);
        board.isValidMove(kMove);
        board.move(kMove);

        board.undo();

        assertTrue(board.pieceAt(7,4) instanceof  King);
        assertTrue(board.pieceAt(7,0) instanceof  Rook);

    }

    /**
     * Testing castling left in move function
     */
    @Test
    public void UndoNothing() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(7,4,new King(Player.WHITE));
        board.setPiece(7,0,new Rook(Player.WHITE));

        board.undo();

        assertTrue(board.pieceAt(7,4) instanceof  King);


    }

    /**
     * Testing undoing En Passanting left
     */
    @Test
    public void UndoEnPassantLeft() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(4,6,new Pawn(Player.BLACK));
        board.setPiece(6,5,new Pawn(Player.WHITE));

        //move white pawn up 2
        Move pMove = new Move(6,5,4,5);
        board.isValidMove(pMove);
        board.move(pMove);
        //En Passant to the left
        pMove = new Move(4,6,5,5);
        board.isValidMove(pMove);
        board.move(pMove);

        board.undo();


        assertTrue(board.pieceAt(4,6) instanceof  Pawn);
        assertTrue(board.pieceAt(4,5) instanceof  Pawn);

    }

    /**
     * Testing undoing En Passanting right in undo function
     */
    @Test
    public void UndoEnPassantRight() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(4,6,new Pawn(Player.WHITE));
        board.setPiece(1,7,new Pawn(Player.BLACK));

        //move white pawn up 1
        Move pMove = new Move(4,6,3,6);
        board.isValidMove(pMove);
        board.move(pMove);
        //move black pawn down 2
        pMove = new Move(1,7,3,7);
        board.isValidMove(pMove);
        board.move(pMove);
        //En Passant to the right
        pMove = new Move(3,6,2,7);
        board.isValidMove(pMove);
        board.move(pMove);

        board.undo();

        assertTrue(board.pieceAt(3,6) instanceof  Pawn);
        assertTrue(board.pieceAt(3,7) instanceof  Pawn);

    }




    /**
     * King is only white piece
     * King has no available moves
     */
    @Test
    public void testAI0() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(3,3,new King(Player.WHITE));
        board.setPiece(2,0,new Rook(Player.BLACK));
        board.setPiece(3,0,new Rook(Player.BLACK));
        board.setPiece(4,0,new Rook(Player.BLACK));

        board.AI();

        assertTrue(board.isComplete());

    }

    /**
     * King is in check
     * Tests to see if AI will move king out of check
     */
    @Test
    public void testAI1(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(0,0, new King(Player.WHITE));
        board.setPiece(2,1, new Rook(Player.BLACK));
        board.setPiece(0,2, new Rook(Player.BLACK));

        board.AI();

        assertTrue(board.pieceAt(1,0) instanceof King);

    }

    /**
     * White: King(0,0), Rook(0,1), Rook(1,1)
     * Black: Rook(5,0)
     *
     * White king is in check, AI will move the rook to save it
     */
    @Test
    public void testAI2(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(0,0,new King(Player.WHITE));
        board.setPiece(0,1,new Rook(Player.WHITE));
        board.setPiece(1,1,new Rook(Player.WHITE));
        board.setPiece(5,0,new Rook(Player.BLACK));

        board.AI();

        assertTrue(board.pieceAt(1,0) instanceof Rook);

    }

    /**
     * White:King(0,0) Bishop(0,1) Bishop(1,0) Bishop (2,0)
     * Black: Bishop(2,2)
     * White king is in check, AI will move bishop to save king
     */
    @Test
    public void testAI3() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(0,0,new King(Player.WHITE));
        board.setPiece(0,1,new Bishop(Player.WHITE));
        board.setPiece(1,0,new Bishop(Player.WHITE));
        board.setPiece(2,0,new Bishop(Player.WHITE));
        board.setPiece(2,2,new Bishop(Player.BLACK));

        board.AI();

        assertTrue(board.pieceAt(1,1) instanceof Bishop);

    }

    /**
     * White: King(0,0), Rook(0,1), Queen(1,1)
     * Black: Rook(5,0)
     * White king is in check, AI will move queen to save it
     */
    @Test
    public void testAI4(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(0,0,new King(Player.WHITE));
        board.setPiece(0,1,new Rook(Player.WHITE));
        board.setPiece(1,1,new Queen(Player.WHITE));
        board.setPiece(5,0,new Rook(Player.BLACK));

        board.AI();

        assertTrue(board.pieceAt(1,0) instanceof Queen);

    }

    /**
     * White:King(0,0) Bishop(0,1) Bishop(1,0) Queen (2,0)
     * Black: Bishop(2,2)
     * White king is in check, AI will move the queen to save king
     */
    @Test
    public void testAI5() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(0,0,new King(Player.WHITE));
        board.setPiece(0,1,new Bishop(Player.WHITE));
        board.setPiece(1,0,new Bishop(Player.WHITE));
        board.setPiece(4,0,new Queen(Player.WHITE));
        board.setPiece(2,2,new Bishop(Player.BLACK));

        board.AI();

        assertTrue(board.pieceAt(2,2) instanceof Queen);

    }

    /**
     * White:King(0,0) Bishop(0,1) Bishop(1,0) Knight(0,3)
     * Black: Bishop(2,2)
     * Knight r+1, col-2 save king
     */
    @Test
    public void testAI6() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(0,0,new King(Player.WHITE));
        board.setPiece(0,1,new Bishop(Player.WHITE));
        board.setPiece(1,0,new Bishop(Player.WHITE));
        board.setPiece(0,3,new Knight(Player.WHITE));
        board.setPiece(2,2,new Bishop(Player.BLACK));

        board.AI();

        assertTrue(board.pieceAt(1,1) instanceof Knight);

    }

    /**
     * White:King(7,0) Bishop(6,0) Pawn(7,1)
     * Black: Bishop(5,2)
     * Pawn Moves up 1 save king
     */
    @Test
    public void testAI7() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }
        board.setPiece(7,0,new King(Player.WHITE));
        board.setPiece(6,0,new Bishop(Player.WHITE));
        board.setPiece(7,1,new Pawn(Player.WHITE));
        board.setPiece(5,2,new Bishop(Player.BLACK));

        board.AI();

        assertTrue(board.pieceAt(6,1) instanceof Pawn);

    }

    /**
     * White: King(7,0), Bishop(6,0), Bishop(7,1), Pawn(7,2)
     * Black: Bishop(4,3)
     * Pawn Moves up 2 save king
     */
    @Test
    public void testAI8() {
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(7,0, new King(Player.WHITE));
        board.setPiece(6,0, new Bishop(Player.WHITE));
        board.setPiece(7,1, new Bishop(Player.WHITE));
        board.setPiece(7,2, new Pawn(Player.WHITE));
        board.setPiece(4,3, new Bishop(Player.BLACK));

        board.AI();

        assertTrue(board.pieceAt(5,2) instanceof Pawn);
    }

    /**
     * White: Rook(0,0)
     * Black: Pawn(3,0)
     *
     * We are testing part B of the AI requirement
     * to see if it will move the rook to attack
     *
     */
    @Test
    public void testAI9(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(0,0, new Rook(Player.WHITE));
        board.setPiece(3,0, new Pawn(Player.BLACK));

        board.AI();

        assertTrue(board.pieceAt(3,0) instanceof Rook);
    }

    /**
     * White: Bishop(0,0)
     * Black: Pawn(2,2)
     *
     * We are testing part B of the AI requirements
     * to see if the AI will move the Bishop to attack
     *
     */
    @Test
    public void testAI10(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(0,0, new Bishop(Player.WHITE));
        board.setPiece(2,2, new Pawn(Player.BLACK));

        board.AI();

        assertTrue(board.pieceAt(2,2) instanceof Bishop);
    }

    /**
     * White: Queen(0,0)
     * Black: Pawn(0,2)
     *
     * We are testing part B of the AI requirements
     * to see if the AI will move the queen using the rook
     * logic to attack the pawn
     *
     */
    @Test
    public void testAI11(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(0,0, new Queen(Player.WHITE));
        board.setPiece(0,2, new Pawn(Player.BLACK));

        board.AI();

        assertTrue(board.pieceAt(0,2) instanceof Queen);
    }

    /**
     * White: Queen(0,0)
     * Black: Pawn(2,2)
     *
     * We are testing part B of the AI requirements
     * to see if the AI will move the queen using the bishop
     * logic to attack the pawn
     *
     */
    @Test
    public void testAI12(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(0,0, new Queen(Player.WHITE));
        board.setPiece(2,2, new Pawn(Player.BLACK));

        board.AI();

        assertTrue(board.pieceAt(2,2) instanceof Queen);
    }

    /**
     * White: Knight(0,0)
     * Black: Pawn(1,2)
     *
     * We are testing part B of the AI requirements
     * to see if the AI will move the knight to capture
     * the pawn
     */
    @Test
    public void testAI13(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(0,0, new Knight(Player.WHITE));
        board.setPiece(1,2, new Pawn(Player.BLACK));

        board.AI();

        assertTrue(board.pieceAt(1,2) instanceof Knight);
    }

    /**
     * White: Knight(0,0)
     * Black: Pawn(2,1)
     *
     * We are testing part B of the AI requirements
     * to see if the AI will move the knight to capture the pawn
     *
     */
    @Test
    public void testAI14(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(0,0, new Knight(Player.WHITE));
        board.setPiece(2,1, new Pawn(Player.BLACK));

        board.AI();

        assertTrue(board.pieceAt(2,1) instanceof Knight);
    }

    /**
     * White: Pawn(6,0)
     * Black: Pawn(5,1)
     *
     * We are testing part B of the AI requirements
     * to see if the AI will move the pawn to attack
     * the other pawn
     *
     */
    @Test
    public void testAI15(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(6,0, new Pawn(Player.WHITE));
        board.setPiece(5,1, new Pawn(Player.BLACK));

        board.AI();

        assertTrue(board.pieceAt(5,1) instanceof Pawn);
    }

    /** White: Rook(0,0)
     *  Black: Rook(2,2)
     *
     *  The AI rook is in danger so it will move it right
     */
    @Test
    public void testAI16(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(0,0, new Rook(Player.WHITE));
        board.setPiece(2,2, new Bishop(Player.BLACK));


        board.AI();

        assertTrue(board.pieceAt(0,1) instanceof Rook);
    }

    /** White: Bishop(0,0)
     *  Black: Rook(0,2)
     *
     *  The AI Bishop is in danger so it will move it down right
     */
    @Test
    public void testAI17(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(0,0, new Bishop(Player.WHITE));
        board.setPiece(0,2, new Rook(Player.BLACK));


        board.AI();

        assertTrue(board.pieceAt(1,1) instanceof Bishop);
    }

    /** White: Pawn(6,1)
     *  Black: Bishop(5,2)
     *
     *  The AI pawn is in danger so it will move it up
     */
    @Test
    public void testAI18(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(6,1, new Pawn(Player.WHITE));
        board.setPiece(4,3, new Bishop(Player.BLACK));


        board.AI();

        assertTrue(board.pieceAt(4,1) instanceof Pawn);
    }

    /** White: Queen(0,0), Rook(1,0)
     *  Black: Knight(2,1)
     *
     *  The AI Queen is in danger so it will move it right
     */
    @Test
    public void testAI19(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(0,0, new Queen(Player.WHITE));
        board.setPiece(1,0, new Rook(Player.WHITE));
        board.setPiece(2,1, new Knight(Player.BLACK));


        board.AI();

        assertTrue(board.pieceAt(0,1) instanceof Queen);
    }

    /** White: Knight(0,0)
     *  Black: Bishop(2,2)
     *
     *  The AI Knight is in danger so it will move it to 1,2
     */
    @Test
    public void testAI20(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(0,0, new Knight(Player.WHITE));
        board.setPiece(2,2, new Bishop(Player.BLACK));


        board.AI();

        assertTrue(board.pieceAt(1,2) instanceof Knight);
    }

    /** White: Rook(0,0)
     *  Black:
     *
     *  Test the AI to move the rook randomly
     */
    @Test
    public void testAI21(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(0,0, new Rook(Player.WHITE));

        board.AI();

        assertTrue(board.pieceAt(0,1) instanceof Rook);
    }

    /** White: Bishop(0,0)
     *  Black:
     *
     *  Test the AI to see if it will move the Bishop randomly
     */
    @Test
    public void testAI22(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(0,0, new Bishop(Player.WHITE));

        board.AI();

        assertTrue(board.pieceAt(1,1) instanceof Bishop);
    }

    /** White: Bishop(0,0)
     *  Black:
     *
     *  The AI Queen is in danger so it will move it to the right to 0,1
     */
    @Test
    public void testAI23(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(0,0, new Queen(Player.WHITE));

        board.AI();

        assertTrue(board.pieceAt(0,1) instanceof Queen);
    }

    /** White: Knight(0,0)
     *  Black:
     *
     *  Test the AI to see if it will move the Knight randomly
     */
    @Test
    public void testAI24(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(0,0, new Knight(Player.WHITE));

        board.AI();

        assertTrue(board.pieceAt(1,2) instanceof Knight);
    }

    /** White: Pawn(6,0)
     *  Black:
     *
     *  Test the AI to see if it will Randomly move the Pawn
     */
    @Test
    public void testAI25(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(6,0, new Pawn(Player.WHITE));

        board.AI();

        assertTrue(board.pieceAt(4,0) instanceof Pawn);
    }

    /**
     * White: Queen(0,0), Rook(1,0), Pawn(0,1)
     * Black: Knight(2,1)
     *
     * Test to see if the AI will move the Queen out of danger using the
     * Bishop move
     */
    @Test
    public void testAI26(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(0,0, new Queen(Player.WHITE));
        board.setPiece(1,0, new Rook(Player.WHITE));
        board.setPiece(0,1, new Pawn(Player.WHITE));
        board.setPiece(2,1, new Knight(Player.BLACK));

        board.AI();

        assertTrue(board.pieceAt(1,1) instanceof Queen);
    }

    /**
     * White: Queen(0,0), Pawn(1,0), Pawn(0,1)
     * Black:
     *
     * Test the AI to see if it will randomly move the Queen using
     * a Bishop move
     */
    @Test
    public void testAI27(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(0,0, new Queen(Player.WHITE));
        board.setPiece(1,0, new Pawn(Player.WHITE));
        board.setPiece(0,1, new Pawn(Player.WHITE));

        board.AI();

        assertTrue(board.pieceAt(1,1) instanceof Queen);
    }

    /**
     * White: Pawn(2,2), Pawn(5,3)
     * Black: Pawn(1,1)
     *
     * Testing the pawn can move function with the AI but for
     * Black pawns
     */
    @Test
    public void testPawnCanAttack(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(1,1, new Pawn(Player.BLACK));
        board.setPiece(2,2, new Pawn(Player.WHITE));
        board.setPiece(5,3, new Pawn(Player.WHITE));

        Move move = new Move(5,3,4,3);
        board.move(move);
        board.AI();

        assertTrue(board.pieceAt(2,2) instanceof Pawn);
    }

    /**
     * White: Rook(2,0), Pawn(5,3)
     * Black: Pawn(1,1)
     *
     * Testing the pawn can move function with the AI but for
     * Black pawns
     */
    @Test
    public void testPawnCanAttack2(){
        ChessModel board = new ChessModel();
        for(int r = 0; r < 8; r ++){
            for (int c =0 ; c < 8; c++){
                board.setPiece(r,c,null);
            }
        }

        board.setPiece(1,1, new Pawn(Player.BLACK));
        board.setPiece(2,0, new Rook(Player.WHITE));
        board.setPiece(5,3, new Pawn(Player.WHITE));

        Move move = new Move(5,3,4,3);
        board.move(move);
        board.AI();

        assertTrue(board.pieceAt(2,0) instanceof Pawn);
    }

    /**
     * White: Bishop(3,3)
     * Black: King(0,0), Pawn(1,3)
     *
     * Test the Pawn can protect method by seeing if the AI will move the
     * Black pawn to capture the White Bishop who is putting the Black King
     * in check
     */
    @Test
    public void testPawnCanProtect() {
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(0, 0, new King(Player.BLACK));
        board.setPiece(1, 3, new Pawn(Player.BLACK));
        board.setPiece(3, 3, new Bishop(Player.WHITE));

        Move move = new Move(3, 3, 2, 2);
        board.move(move);
        board.move(board.pawnCanMove(1,3, Player.BLACK));

        assertTrue(board.pieceAt(2,2) instanceof Pawn);
    }

    /**
     * White: Bishop(3,3)
     * Black: Pawn(1,1)
     *
     * Test the Pawn can protect method
     */
    @Test
    public void testPawnCanProtect2() {
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(0,7, new King(Player.BLACK));
        board.setPiece(1, 4, new Pawn(Player.BLACK));
        board.setPiece(3, 4, new Bishop(Player.WHITE));

        Move move = new Move(3, 4, 2, 5);
        board.move(move);
        board.move(board.pawnCanMove(1,4, Player.BLACK));

        assertTrue(board.pieceAt(2,5) instanceof Pawn);
    }

    /**
     * White: Pawn(0,0), Pawn(0,1) Pawn(1,0)
     * Black:
     *
     * Test the Pawn can protect method will return null
     */
    @Test
    public void testPawnCanProtect3() {
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(0,0, new Pawn(Player.WHITE));
        board.setPiece(1,0, new Pawn(Player.WHITE));
        board.setPiece(0,1, new Pawn(Player.WHITE));


        assertTrue(board.pawnCanMove(0,0, Player.WHITE) == null);


    }

    /**
     * White: King(0,0), Rook(2,0)
     * Black: Bishop(3,3)
     *
     * Testing the rook can protect method to see if it will move to the right
     *
     */
    @Test
    public void testRookCanProtect(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(0,0, new King(Player.WHITE));
        board.setPiece(2,0, new Rook(Player.WHITE));
        board.setPiece(3,3, new Bishop(Player.BLACK));


        board.move(board.rookCanMove(2,0));


        assertTrue(board.pieceAt(2,2) instanceof Rook);
    }

    /**
     * White: King(0,0), Rook(2,0)
     * Black: Bishop(5,3)
     *
     * Testing the rook can protect method to see if it will move up
     *
     */
    @Test
    public void testRookCanProtect2(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(0,0, new King(Player.WHITE));
        board.setPiece(5,3, new Rook(Player.WHITE));
        board.setPiece(3,3, new Bishop(Player.BLACK));


        board.move(board.rookCanMove(5,3));


        assertTrue(board.pieceAt(3,3) instanceof Rook);
    }

    /**
     * White: King(0,0), Rook(0,3)
     * Black: Bishop(3,3)
     *
     * Testing the rook can protect method to see if it will move down
     *
     */
    @Test
    public void testRookCanProtect3(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(0,0, new King(Player.WHITE));
        board.setPiece(0,3, new Rook(Player.WHITE));
        board.setPiece(3,3, new Bishop(Player.BLACK));


        board.move(board.rookCanMove(0,3));


        assertTrue(board.pieceAt(3,3) instanceof Rook);
    }

    /**
     * White: Pawn(1,0), Rook(0,0), Pawn(0,1)
     * Black:
     *
     * Testing the rook can protect method to see if it will return null
     *
     */
    @Test
    public void testRookCanProtect4(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(0,1, new Pawn(Player.WHITE));
        board.setPiece(0,0, new Rook(Player.WHITE));
        board.setPiece(1,0, new Pawn(Player.WHITE));

        assertTrue(board.rookCanMove(0,0) == null);
    }

    /**
     * White: King(0,0), Bishop(1,0)
     * Black: Knight(2,1)
     *
     * Test the Bishop Can Protect method for down right
     */
    @Test
    public void testBishopCanProtect(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(0,0, new King(Player.WHITE));
        board.setPiece(1,0, new Bishop(Player.WHITE));
        board.setPiece(2,1, new Knight(Player.BLACK));

        board.move(board.bishopCanMove(1,0));
        assertTrue(board.pieceAt(2,1) instanceof Bishop);
    }

    /**
     * White: King(0,0), Bishop(3,2)
     * Black: Knight(2,1)
     *
     * Test the Bishop Can Protect method for up left
     */
    @Test
    public void testBishopCanProtect2(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(0,0, new King(Player.WHITE));
        board.setPiece(3,2, new Bishop(Player.WHITE));
        board.setPiece(2,1, new Knight(Player.BLACK));

        board.move(board.bishopCanMove(3,2));
        assertTrue(board.pieceAt(2,1) instanceof Bishop);
    }

    /**
     * White: King(0,0), Knight(0,3)
     * Black: Bishop(2,2)
     *
     * Test the Knight Can Protect method for moving 2 rows
     */
    @Test
    public void testKinghtCanProtect(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(0,0, new King(Player.WHITE));
        board.setPiece(0,1, new Knight(Player.WHITE));
        board.setPiece(2,0, new Rook(Player.BLACK));

        board.move(board.knightCanMove(0,1));
        assertTrue(board.pieceAt(2,0) instanceof Knight);
    }

    /**
     * White: Pawn(2,1), Knight(0,3), Pawn(1,2)
     * Black:
     *
     * Test the Knight Can Protect method will return null
     */
    @Test
    public void testKinghtCanProtect2(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(1,2, new Pawn(Player.WHITE));
        board.setPiece(0,0, new Knight(Player.WHITE));
        board.setPiece(2,1, new Pawn(Player.WHITE));


        assertTrue(board.knightCanMove(0,0) == null);
    }

    /**
     * White: Rook(0,2)
     * Black: Pawn(0,0)
     *
     * Testing the Rook Can Attack method to attack to the left
     *
     */
    @Test
    public void testRookCanAttack(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(0,2, new Rook(Player.WHITE));
        board.setPiece(0,0, new Pawn(Player.BLACK));

        board.move(board.rookCanAttack(0,2, Player.WHITE));
        assertTrue(board.pieceAt(0,0) instanceof Rook);
    }

    /**
     * White: Rook(3,0)
     * Black: Pawn(0,0)
     *
     * Testing the Rook Can Attack method to attack upward
     *
     */
    @Test
    public void testRookCanAttack2(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(3,0, new Rook(Player.WHITE));
        board.setPiece(0,0, new Pawn(Player.BLACK));

        board.move(board.rookCanAttack(3,0, Player.WHITE));
        assertTrue(board.pieceAt(0,0) instanceof Rook);
    }

    /**
     * White: Bishop(7,0)
     * Black: Pawn(6,1)
     *
     * Testing the Bishop Can Attack method to the up right
     *
     */
    @Test
    public void testBishopCanAttack(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(7,0, new Bishop(Player.WHITE));
        board.setPiece(6,1, new Pawn(Player.BLACK));

        board.move(board.bishopCanAttack(7,0, Player.WHITE));
        assertTrue(board.pieceAt(6,1) instanceof Bishop);
    }

    /**
     * White: Bishop(5,2)
     * Black: Pawn(6,1)
     *
     * Testing the Bishop Can Attack method to the down left
     *
     */
    @Test
    public void testBishopCanAttack2(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(5,2, new Bishop(Player.WHITE));
        board.setPiece(6,1, new Pawn(Player.BLACK));

        board.move(board.bishopCanAttack(5,2, Player.WHITE));
        assertTrue(board.pieceAt(6,1) instanceof Bishop);
    }

    /**
     * White: Bishop(7,2)
     * Black: Pawn(6,1)
     *
     * Testing the Bishop Can Attack method to the up right
     *
     */
    @Test
    public void testBishopCanAttack3(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(7,2, new Bishop(Player.WHITE));
        board.setPiece(6,1, new Pawn(Player.BLACK));

        board.move(board.bishopCanAttack(7,2, Player.WHITE));
        assertTrue(board.pieceAt(6,1) instanceof Bishop);
    }






    /**
     * White: Knight(7,0), Rook(6,2)
     *
     * Testing the knight can move up 2 right 1
     *
     */
    @Test
    public void testKnightCanEscape1(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(7,0, new Knight(Player.WHITE));
        board.setPiece(6,2, new Rook(Player.WHITE));


        board.move(board.knightCanEscape(7,0,Player.WHITE));


        assertTrue(board.pieceAt(5,1) instanceof Knight);
    }

    /**
     * White: Knight(7,0), Rook(6,2),Rook(5,2)
     *
     * Testing the knight has no moves
     *
     */
    @Test
    public void testKnightCanEscape2(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(7,0, new Knight(Player.WHITE));
        board.setPiece(6,2, new Rook(Player.WHITE));
        board.setPiece(5,1, new Rook(Player.WHITE));




        assertTrue(board.knightCanEscape(7,0,Player.WHITE) == null);
    }

    /**
     * White: Bishop(7,0)
     *
     * Testing the bishop can go up right
     *
     */
    @Test
    public void testBishopCanEscape1(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(7,0, new Bishop(Player.WHITE));

        board.move(board.bishopCanEscape(7,0,Player.WHITE));


        assertTrue(board.pieceAt(6,1) instanceof Bishop);
    }


    /**
     * White: Bishop(0,7)
     *
     * Testing the bishop can go down left
     *
     */
    @Test
    public void testBishopCanEscape2(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(0,7, new Bishop(Player.WHITE));

        board.move(board.bishopCanEscape(0,7,Player.WHITE));

        assertTrue(board.pieceAt(1,6) instanceof Bishop);
    }

    /**
     * White: Bishop(7,7)
     *
     * Testing the bishop can go up left
     *
     */
    @Test
    public void testBishopCanEscape3(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(7,7, new Bishop(Player.WHITE));

        board.move(board.bishopCanEscape(7,7,Player.WHITE));

        assertTrue(board.pieceAt(6,6) instanceof Bishop);
    }

    /**
     * White: Bishop(0,7), Pawn (1,6)
     *
     * Testing the bishop has no moves
     *
     */
    @Test
    public void testBishopCanEscape4(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }


        board.setPiece(0,7, new Bishop(Player.WHITE));
        board.setPiece(1,6, new Pawn(Player.WHITE));


        assertTrue(board.bishopCanEscape(0,7,Player.WHITE) == null);
    }

    /**
     * White: Rook(0,7), Pawn(1,7)
     *
     * Testing the rook moves left
     *
     */
    @Test
    public void testRookCanEscape1(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(0,7, new Rook(Player.WHITE));
        board.setPiece(1,7, new Pawn(Player.WHITE));

        board.move(board.rookCanEscape(0,7,Player.WHITE));

        assertTrue(board.pieceAt(0,6) instanceof Rook);
    }

    /**
     * White: Rook(7,7), Pawn(7,6)
     *
     * Testing the rook moves up
     *
     */
    @Test
    public void testRookCanEscape2(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(7,7, new Rook(Player.WHITE));
        board.setPiece(7,6, new Pawn(Player.WHITE));

        board.move(board.rookCanEscape(7,7,Player.WHITE));

        assertTrue(board.pieceAt(6,7) instanceof Rook);
    }

    /**
     * White: Rook(0,7), Pawn(0,6)
     *
     * Testing the rook moves down
     *
     */
    @Test
    public void testRookCanEscape3(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(0,7, new Rook(Player.WHITE));
        board.setPiece(0,6, new Pawn(Player.WHITE));

        board.move(board.rookCanEscape(0,7,Player.WHITE));

        assertTrue(board.pieceAt(1,7) instanceof Rook);
    }

    /**
     * White: Pawn(7,0), Rook(5,0)
     *
     * Testing white pawn jumps 1
     *
     */
    @Test
    public void testPawnCanEscape1(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(5,0, new Rook(Player.WHITE));
        board.setPiece(7,0, new Pawn(Player.WHITE));

        board.move(board.pawnCanEscape(7,0,Player.WHITE));

        assertTrue(board.pieceAt(6,0) instanceof Pawn);
    }

    /**
     * White: Pawn(7,0),Rook(6,0)
     * Black: Rook(6,1)
     *
     * Testing white pawn kills to the right
     *
     */
    @Test
    public void testPawnCanEscape2(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(6,1, new Rook(Player.BLACK));
        board.setPiece(6,0, new Rook(Player.WHITE));
        board.setPiece(7,0, new Pawn(Player.WHITE));

        board.move(board.pawnCanEscape(7,0,Player.WHITE));

        assertTrue(board.pieceAt(6,1) instanceof Pawn);
    }

    /**
     * White: Rook(2,0)(moves to (1,0))
     * Black: Pawn(0,1),Rook(1,1)
     *
     * Testing black pawn kills to the left
     *
     */
    @Test
    public void testPawnCanEscape3(){
        ChessModel board = new ChessModel();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(1,1, new Rook(Player.BLACK));
        board.setPiece(0,1, new Pawn(Player.BLACK));
        board.setPiece(2,0, new Pawn(Player.WHITE));

        Move move = new Move(2,0,1,0);
        board.move(move);

        board.move(board.pawnCanEscape(0,1,Player.BLACK));

        assertTrue(board.pieceAt(1,0) instanceof Pawn);
    }




}