
 
package g56411.luckynumbers.model;

/**
 * give us the position on the board with a row and a column
 * @author Sofiane
 */
public class Position {
    private int row;
    private int column;
    /**
     * represent the position on the board
     * @param row the row on the board
     * @param column the column on the board
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
    /**
     * 
     * @return the row on the board
     */
    public int getRow() {
        return row;
    }
    /**
     * 
     * @return the column on the board
     */
    public int getColumn() {
        return column;
    }
    
}
