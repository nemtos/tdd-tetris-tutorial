package tetris;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Board {

    private final int rows;
    private final int columns;

    private List<Block> fallingBlocks = null;
    private List<Block> blocksFallenOnBoard = new LinkedList<Block>();

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public List<Block> getFallingBlocks() {
        return fallingBlocks;
    }

    public void setFallingBlocks(List<Block> fallingBlocks) {
        this.fallingBlocks = fallingBlocks;
    }

    public void setFallingBlocks(Piece piece) {
        this.fallingBlocks = piece.getPieceStructure();
    }

    public List<Block> getBlocksFallenOnBoard() {
        return blocksFallenOnBoard;
    }

    public String toString() {
        String s = "";
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Block fallingBlockInPosition = getFallingBlockInPosition(row, col);
                if (fallingBlockInPosition != null && fallingBlockInPosition.getBlockName() != '\n' && fallingBlockInPosition.getBlockName() != '.') {
                    s += fallingBlockInPosition.getBlockName();
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
        return this.getFallingBlocks() != null;
    }

    private Block getFallingBlockInPosition(int row, int col) {
        if (fallingBlocks != null) {
            for (Block fallingBlock : fallingBlocks) {
                if (fallingBlock.getRowPosition() == row
                    && fallingBlock.getColumnPosition() == col) {
                    return fallingBlock;
                }
            }
        }
        return null;
    }

    private boolean canBlockStillFalling() {
        int mostLowerBlockRow = 0;
        for (Block fallingBlock : this.getFallingBlocks()) {
            for (Block block : this.getBlocksFallenOnBoard()) {
                if (fallingBlock.getColumnPosition() == block.getColumnPosition()
                    && fallingBlock.getRowPosition() + 1 == block.getRowPosition()
                    && fallingBlock.getBlockName() != '.' && fallingBlock.getBlockName() != '\n') {
                    return false;
                }
            }
            if (fallingBlock.getRowPosition() > mostLowerBlockRow && fallingBlock.getBlockName() != '.' && fallingBlock.getBlockName() != '\n') {
                mostLowerBlockRow = fallingBlock.getRowPosition();
            }
        }
        return (mostLowerBlockRow < this.rows - 1);
    }

    public void drop(Block block) {
        if (this.getFallingBlocks() == null) {
            List<Block> fallingBlocks = new ArrayList<Block>();
            fallingBlocks.add(block);
            this.setFallingBlocks(fallingBlocks);
        } else {
            throw new IllegalStateException("already falling");
        }
    }

    public void drop(Tetromino shape) {
        if (this.getFallingBlocks() == null) {
            Piece shapePiece = shape.getShapePiece();
            for (Block block : shapePiece.getPieceStructure()) {
                // dropping in the middle
                block.setColumnPosition(block.getColumnPosition() + columns / 2 - 1);
            }
            this.setFallingBlocks(shapePiece);
        } else {
            throw new IllegalStateException("already falling");
        }
    }

    public void tick() {
        if (this.canBlockStillFalling()) {
            for (Block fallingBlock : this.getFallingBlocks()) {
                fallingBlock.setRowPosition(fallingBlock.getRowPosition() + 1);
            }
        } else if (this.hasFalling()) {
            for (Block fallingBlock : this.getFallingBlocks()) {
                if (fallingBlock.getBlockName() != '.' && fallingBlock.getBlockName() != '\n') {
                    this.getBlocksFallenOnBoard().add(fallingBlock);
                }
            }
            this.setFallingBlocks((List<Block>) null);
        }
    }
}
