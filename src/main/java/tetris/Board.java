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
                if (this.isFallingBlockInPosition(row, col)) {
                    s += this.getFallingBlock().getBlockName();
                } else {
                    Block fallenBlock = this.getFallenBlockInPosition(row, col);
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
        for (Block block : this.getBlocksFallenOnBoard()) {
            if (block.getRowPosition() == row && block.getColumnPosition() == col) {
                return block;
            }
        }
        return null;
    }

    public boolean hasFalling() {
        return this.getFallingBlock() != null;
    }

    private boolean isFallingBlockInPosition(int row, int col) {
        return this.getFallingBlock() != null
                && this.getFallingBlock().getRowPosition() == row
                && this.getFallingBlock().getColumnPosition() == col;
    }

    private boolean canBlockStillFalling() {
        for (Block block : this.getBlocksFallenOnBoard()) {
            if (this.getFallingBlock().getColumnPosition() == block.getColumnPosition()
                    && this.getFallingBlock().getRowPosition() + 1 == block.getRowPosition()) {
                return false;
            }
        }
        return (this.getFallingBlock().getRowPosition() < this.rows - 1);
    }

    public void drop(Block block) {
        if (this.getFallingBlock() == null) {
            this.setFallingBlock(block);
        } else {
            throw new IllegalStateException("already falling");
        }
    }

    public void tick() {
        if (this.canBlockStillFalling()) {
            this.getFallingBlock().setRowPosition(this.getFallingBlock().getRowPosition() + 1);
        } else if (this.hasFalling()) {
            this.getBlocksFallenOnBoard().add(this.getFallingBlock());
            this.setFallingBlock(null);
        }
    }
}
