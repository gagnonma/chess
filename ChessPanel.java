package p3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChessPanel extends JPanel {

    private JButton[][] board;
    private JButton undo;
    private JButton AI;
    private ChessModel model;

    private ImageIcon wRook;
    private ImageIcon wBishop;
    private ImageIcon wQueen;
    private ImageIcon wKing;
    private ImageIcon wPawn;
    private ImageIcon wKnight;
    private ImageIcon bRook;
    private ImageIcon bBishop;
    private ImageIcon bQueen;
    private ImageIcon bKing;
    private ImageIcon bPawn;
    private ImageIcon bKnight;

    private boolean firstTurnFlag;
    private int fromRow;
    private int toRow;
    private int fromCol;
    private int toCol;
    // declare other intance variables as needed

    private listener listener;

    /**
     * Constructor that sets up the panel by creating all the buttons
     */
    public ChessPanel() {
        model = new ChessModel();
        board = new JButton[model.numRows()][model.numColumns()];
        listener = new listener();
        undo = new JButton("Undo");
        undo.addActionListener(listener);
        AI = new JButton("AI");
        AI.addActionListener(listener);
        add(undo);
        add(AI);
        createIcons();

        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        boardpanel.setLayout(new GridLayout(model.numRows(),
                model.numColumns(), 1, 1));


        //create the buttons for the board
        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                }else if(model.pieceAt(r, c).player()==Player.WHITE){
                    placeWhitePieces(r, c);
                }else if(model.pieceAt(r, c).player() == Player.BLACK){
                    placeBlackPieces(r,c);
                }

                setBackGroundColor(r, c);
                boardpanel.add(board[r][c]);
            }
        }
        add(boardpanel, BorderLayout.WEST);
        boardpanel.setPreferredSize(new Dimension(600, 600));
        add(buttonpanel);
        firstTurnFlag = true;
    }

    /**
     * Sets the background color for the given button
     * @param r
     * @param c
     */
    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);
        } else if ((c % 2 == 0 && r % 2 == 0) ||
                (c % 2 == 1 && r % 2 == 1)) {
            board[r][c].setBackground(Color.WHITE);
        }
    }

    /**
     * Sets the icons and creates the action listeners for the white
     * pieces
     * @param r
     * @param c
     */
    private void placeWhitePieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, wPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, wRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, wKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, wBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, wQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, wKing);
            board[r][c].addActionListener(listener);
        }
    }

    /**
     * Sets the icons and creates the action listeners for the black
     * pieces
     * @param r
     * @param c
     */
    private void placeBlackPieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, bPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, bRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, bKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, bBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, bQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, bKing);
            board[r][c].addActionListener(listener);
        }
    }

    /**
     * creates the icons for both white and black pieces
     */
    private void createIcons() {
        // Sets the Image for white player pieces
        wRook = new ImageIcon("wRook.png");
        wBishop = new ImageIcon("wBishop.png");
        wQueen = new ImageIcon("wQueen.png");
        wKing = new ImageIcon("wKing.png");
        wPawn = new ImageIcon("wPawn.png");
        wKnight = new ImageIcon("wKnight.png");

        // Sets the Image for black player pieces
        bRook = new ImageIcon("bRook.png");
        bBishop = new ImageIcon("bBishop.png");
        bQueen = new ImageIcon("bQueen.png");
        bKing = new ImageIcon("bKing.png");
        bPawn = new ImageIcon("bPawn.png");
        bKnight = new ImageIcon("bKnight.png");
    }

    /**
     * Method that updates the board
     */
    private void displayBoard() {
        //go through the entire board setting the icons for all the
        //pieces
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (model.pieceAt(r, c) == null)
                    board[r][c].setIcon(null);
                else if (model.pieceAt(r, c).player() == Player.WHITE){
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        if (r == 0) {
                            model.setPiece(r, c,
                                    new Queen(Player.WHITE));
                            board[r][c].setIcon(wQueen);
                        } else {
                            board[r][c].setIcon(wPawn);
                        }

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(wRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(wKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(wBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(wQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(wKing);

                } else if (model.pieceAt(r, c).player() ==
                        Player.BLACK) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        if (r == 7) {
                            model.setPiece(r, c,
                                    new Queen(Player.BLACK));
                            board[r][c].setIcon(bQueen);
                        } else {
                            board[r][c].setIcon(bPawn);
                        }

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(bRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(bKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(bBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(bQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(bKing);

                }
            }
        }
        repaint();
    }

    /**
     * inner class that represents action listener for buttons
     */
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            for (int r = 0; r < model.numRows(); r++) {
                for (int c = 0; c < model.numColumns(); c++) {
                    if (board[r][c] == event.getSource()) {
                        if (firstTurnFlag == true) {
                            fromRow = r;
                            fromCol = c;
                            firstTurnFlag = false;
                        } else {
                            toRow = r;
                            toCol = c;
                            firstTurnFlag = true;
                            Move m = new Move(fromRow, fromCol,
                                    toRow, toCol);
                            if ((model.isValidMove(m)) == true) {
                                model.move(m);
                                displayBoard();
                                System.out.
                                        println(model.currentPlayer());
                                if (model.isComplete()){
                                    JOptionPane.showMessageDialog(null,
                                            "GAME OVER");
                                }else if(model.inCheck(model.currentPlayer())){
                                    JOptionPane.showMessageDialog(null,
                                            "" + model.currentPlayer()+
                                                    " IN CHECK");
                                }
                            }
                        }
                    }
                }
            }
            if(event.getSource() == undo){
                model.undo();
                displayBoard();
                System.out.println(model.currentPlayer());
            }

            if (event.getSource() == AI){
                model.AI();
                displayBoard();
                if (model.isComplete()){
                    JOptionPane.showMessageDialog(null,
                            "GAME OVER");
                }else if(model.inCheck(model.currentPlayer())){
                    JOptionPane.showMessageDialog(null,
                            "" + model.currentPlayer()+
                                    " IN CHECK");
                }
                System.out.println(model.currentPlayer());
            }
        }
    }
}
