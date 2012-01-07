package tetris;

/**
 * Date: 07/01/12
 * Time: 17:44
 */
public class Tetromino {
    public static final Tetromino T_SHAPE = new Tetromino(".T.\n" +
                                                          "TTT\n" +
                                                          "...\n");

    public static final Tetromino I_SHAPE = new Tetromino(".....\n" +
                                                          ".....\n" +
                                                          "IIII.\n" +
                                                          ".....\n" +
                                                          ".....\n");
    public static final Tetromino O_SHAPE = new Tetromino(".OO\n" +
                                                          ".OO\n" +
                                                          "...\n");
    private Piece shapePiece;

    public Tetromino(String pieceShape) {
        this.shapePiece = new Piece(pieceShape);
    }

    public Tetromino(Piece pieceShape) {
        this.shapePiece = pieceShape;
    }

    public Piece getShapePiece() {
        return new Piece(shapePiece.toString());
    }

    public Tetromino rotateRight() {
        if (this.equals(Tetromino.I_SHAPE)) {
            return new Tetromino(new Piece(shapePiece.toString()).rotateLeft());
        } else if (this.equals(Tetromino.O_SHAPE)) {
            return this;
        } else {
            return new Tetromino(new Piece(shapePiece.toString()).rotateRight());
        }
    }

    public Tetromino rotateLeft() {
        if (this.shapePiece.toString().equals(new Piece(Tetromino.I_SHAPE.shapePiece.toString()).rotateLeft().toString())) {
            return new Tetromino(new Piece(shapePiece.toString()).rotateRight());
        }
        if (this.equals(Tetromino.O_SHAPE)) {
            return this;
        } else {
            return new Tetromino(new Piece(shapePiece.toString()).rotateLeft());
        }
    }

    @Override
    public String toString() {
        return shapePiece.toString();
    }
}
