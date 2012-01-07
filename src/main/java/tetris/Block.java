package tetris;

public class Block {
    private char blockName = '.';

    private int rowPosition;
    private int columnPosition;

    public Block(char blockName) {
        this.setBlockName(blockName);

        // starting in the top middle line
        this.setRowPosition(0);
        this.setColumnPosition(1);
    }

    public char getBlockName() {
        return blockName;
    }

    public void setBlockName(char blockName) {
        this.blockName = blockName;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    public int getColumnPosition() {
        return columnPosition;
    }

    public void setColumnPosition(int columnPosition) {
        this.columnPosition = columnPosition;
    }
}
