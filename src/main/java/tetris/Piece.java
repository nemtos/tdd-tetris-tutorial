package tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Date: 07/01/12
 * Time: 15:48
 */
public class Piece {
    List<Block> pieceStructure = new ArrayList<Block>(12);

    public Piece(String pieceStructure) {
        this.pieceStructure = getPieceStructure(pieceStructure);
    }

    private List<Block> getPieceStructure(String pieceStructure) {
        List<Block> structure = new ArrayList<Block>(12);
        int rowPosition = 0;
        int colPosition = 0;
        for (int i = 0; i < pieceStructure.toCharArray().length; i++) {
            char blockName = pieceStructure.toCharArray()[i];
            Block block = new Block(blockName);
            block.setRowPosition(rowPosition);
            block.setColumnPosition(colPosition);

            colPosition++;
            if (colPosition > 3) {
                colPosition = 0;
                rowPosition++;
            }

            structure.add(block);
        }

        return structure;
    }

    @Override
    public String toString() {
        String piece = "";
        for (Block block : pieceStructure) {
            piece += block.getBlockName();
        }
        return piece;
    }

    public Piece rotateRight() {
        Block[] orderedBlocks = new Block[12];
        for (Block block : pieceStructure) {
            if (block.getBlockName() != '\n') {
                if (block.getRowPosition() == 0) {
                    if (block.getColumnPosition() == 0) {
                        block.setColumnPosition(2);
                    } else if (block.getColumnPosition() == 1) {
                        block.setRowPosition(1);
                        block.setColumnPosition(2);
                    } else {
                        block.setRowPosition(2);
                    }
                } else if (block.getRowPosition() == 1) {
                    if (block.getColumnPosition() == 0) {
                        block.setRowPosition(0);
                        block.setColumnPosition(1);
                    } else if (block.getColumnPosition() == 2) {
                        block.setRowPosition(2);
                        block.setColumnPosition(1);
                    }
                } else if (block.getRowPosition() == 2) {
                    if (block.getColumnPosition() == 0) {
                        block.setRowPosition(0);
                    } else if (block.getColumnPosition() == 1) {
                        block.setRowPosition(1);
                        block.setColumnPosition(0);
                    } else {
                        block.setColumnPosition(0);
                    }
                }
            }
            orderedBlocks[block.getColumnPosition() + 4 * block.getRowPosition()] = block;
        }
        pieceStructure = Arrays.asList(orderedBlocks);
        return this;
    }

    public Piece rotateLeft() {
        Block[] orderedBlocks = new Block[12];
        for (Block block : pieceStructure) {
            if (block.getBlockName() != '\n') {
                if (block.getRowPosition() == 0) {
                    if (block.getColumnPosition() == 0) {
                        block.setRowPosition(2);
                    } else if (block.getColumnPosition() == 1) {
                        block.setRowPosition(1);
                        block.setColumnPosition(0);
                    } else {
                        block.setColumnPosition(0);
                    }
                } else if (block.getRowPosition() == 1) {
                    if (block.getColumnPosition() == 0) {
                        block.setRowPosition(2);
                        block.setColumnPosition(1);
                    } else if (block.getColumnPosition() == 2) {
                        block.setRowPosition(0);
                        block.setColumnPosition(1);
                    }
                } else if (block.getRowPosition() == 2) {
                    if (block.getColumnPosition() == 0) {
                        block.setColumnPosition(2);
                    } else if (block.getColumnPosition() == 1) {
                        block.setRowPosition(1);
                        block.setColumnPosition(2);
                    } else {
                        block.setRowPosition(0);
                    }
                }
            }
            orderedBlocks[block.getColumnPosition() + 4 * block.getRowPosition()] = block;
        }
        pieceStructure = Arrays.asList(orderedBlocks);
        return this;
    }
}
