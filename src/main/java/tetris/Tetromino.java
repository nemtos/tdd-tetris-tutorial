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

    public Tetromino(Piece pieceShape) {
        this.shapePiece = pieceShape;
    }


    public Tetromino rotateRight() {
        return new Tetromino(new Piece(shapePiece.toString()).rotateRight());
    }

    public Tetromino rotateLeft() {
        return new Tetromino(new Piece(shapePiece.toString()).rotateLeft());
    }

    @Override
    public String toString() {
        return shapePiece.toString();
    }
}
