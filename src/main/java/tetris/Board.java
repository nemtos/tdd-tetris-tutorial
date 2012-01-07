package tetris;

public class Board {

    private final int rows;
    private final int columns;

    private Block fallingBlock = null;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public String toString() {
        String s = "";
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (isFallingBlockInPosition(row, col)) {
                    s += fallingBlock.getBlockName();
                } else {
                    s += ".";
                }
            }
            s += "\n";
        }
        return s;
    }

    private boolean isFallingBlockInPosition(int row, int col) {
        return fallingBlock != null
                && fallingBlock.getRowPosition() == row
                && fallingBlock.getColumnPosition() == col;
    }

    public boolean hasFalling() {
        return this.fallingBlock != null;
    }

    public void drop(Block block) {
        if (this.fallingBlock == null) {
            this.fallingBlock = block;
        } else {
            throw new IllegalStateException("already falling");
        }
    }

    public void tick() {
        this.fallingBlock.setRowPosition(this.fallingBlock.getRowPosition() + 1);
    }
}
