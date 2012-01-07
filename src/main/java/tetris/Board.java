package tetris;

import java.util.LinkedList;
import java.util.List;

public class Board {

    private final int rows;
    private final int columns;

    private Block fallingBlock = null;
    private List<Block> blocksFallenOnBoard = new LinkedList<Block>();

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public Block getFallingBlock() {
        return fallingBlock;
    }

    public void setFallingBlock(Block fallingBlock) {
        this.fallingBlock = fallingBlock;
    }

    public List<Block> getBlocksFallenOnBoard() {
        return blocksFallenOnBoard;
    }

    public String toString() {
        String s = "";
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (isFallingBlockInPosition(row, col)) {
                    s += getFallingBlock().getBlockName();
                } else {
                    Block fallenBlock = getFallenBlockInPosition(row, col);
                    if (fallenBlock != null) {
                        s += fallenBlock.getBlockName();
                    } else {
                        s += ".";
                    }
                }
            }
            s += "\n";
        }
        return s;
    }

    private Block getFallenBlockInPosition(int row, int col) {
        for (Block block : getBlocksFallenOnBoard()) {
            if (block.getRowPosition() == row && block.getColumnPosition() == col) {
                return block;
            }
        }
        return null;
    }

    private boolean isFallingBlockInPosition(int row, int col) {
        return getFallingBlock() != null
                && getFallingBlock().getRowPosition() == row
                && getFallingBlock().getColumnPosition() == col;
    }

    public boolean hasFalling() {
        return this.getFallingBlock() != null;
    }

    public void drop(Block block) {
        if (this.getFallingBlock() == null) {
            this.setFallingBlock(block);
        } else {
            throw new IllegalStateException("already falling");
        }
    }

    public void tick() {
        if (this.getFallingBlock().getRowPosition() < rows - 1) {
            this.getFallingBlock().setRowPosition(this.getFallingBlock().getRowPosition() + 1);
        } else if (this.hasFalling()) {
            getBlocksFallenOnBoard().add(this.getFallingBlock());
            this.setFallingBlock(null);
        }
    }
}
