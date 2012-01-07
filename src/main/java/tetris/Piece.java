package tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Date: 07/01/12
 * Time: 15:48
 */
public class Piece {
    private List<Block> pieceStructure = new ArrayList<Block>();
    private int pieceLength = 3;

    public Piece(String pieceStructure) {
        this.pieceStructure = getPieceStructure(pieceStructure);
    }

    private List<Block> getPieceStructure(String pieceStructure) {
        this.pieceLength = pieceStructure.substring(0, pieceStructure.indexOf('\n')).length();
        List<Block> structure = new ArrayList<Block>(this.pieceLength * (this.pieceLength + 1));
        int rowPosition = 0;
        int colPosition = 0;
        for (int i = 0; i < pieceStructure.toCharArray().length; i++) {
            char blockName = pieceStructure.toCharArray()[i];
            Block block = new Block(blockName);
            block.setRowPosition(rowPosition);
            block.setColumnPosition(colPosition);

            colPosition++;
            if (colPosition > this.pieceLength) {
                colPosition = 0;
                rowPosition++;
            }

            structure.add(block);
        }

        return structure;
    }

    public List<Block> getPieceStructure() {
        return pieceStructure;
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
        Block[] orderedBlocks = new Block[this.pieceLength * (this.pieceLength + 1)];
        int pivot = (this.pieceLength - 1) / 2;
        for (Block block : pieceStructure) {
            if (block.getBlockName() != '\n') {
                int newRow = block.getColumnPosition();
                int newCol = pivot + (pivot - block.getRowPosition());

                block.setRowPosition(newRow);
                block.setColumnPosition(newCol);
            }
            orderedBlocks[block.getColumnPosition() + (this.pieceLength + 1) * block.getRowPosition()] = block;
        }
        pieceStructure = Arrays.asList(orderedBlocks);
        return this;
    }

    public Piece rotateLeft() {
        Block[] orderedBlocks = new Block[this.pieceLength * (this.pieceLength + 1)];
        int pivot = (this.pieceLength - 1) / 2;
        for (Block block : pieceStructure) {
            if (block.getBlockName() != '\n') {
                int newRow = pivot + (pivot - block.getColumnPosition());
                int newCol = block.getRowPosition();

                block.setRowPosition(newRow);
                block.setColumnPosition(newCol);
            }
            orderedBlocks[block.getColumnPosition() + (this.pieceLength + 1) * block.getRowPosition()] = block;
        }
        pieceStructure = Arrays.asList(orderedBlocks);
        return this;
    }
}
