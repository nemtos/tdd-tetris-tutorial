package tetris;

/**
 * Date: 07/01/12
 * Time: 17:44
 */
public class Tetromino {
    public static final Tetromino T_SHAPE = new Tetromino(".T.\n" +
                                                          "TTT\n" +
                                                          "...\n");

    private Piece shapePiece;

    public Tetromino(String pieceShape) {
        this.shapePiece = new Piece(pieceShape);
    }


    public void rotateRight() {
        new Piece(shapePiece.toString()).rotateRight();
    }

    public void rotateLeft() {
        new Piece(shapePiece.toString()).rotateLeft();
    }
}
