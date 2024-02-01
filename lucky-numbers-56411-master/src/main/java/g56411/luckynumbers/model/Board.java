package g56411.luckynumbers.model;

/**
 *
 * @author Sofiane
 */
public class Board {

    private Tile[][] tiles;

    /**
     * create a board with a size of 4x4
     */
    public Board() {
        this.tiles = new Tile[4][4];
    }

    /**
     *
     * @return the size of the board (same number of row and column so we can
     * use boards.lenght)
     */
    public int getSize() {
        return tiles.length;
    }

    /**
     * this method checks that the tile on the position pos is on the board
     *
     * @param pos is the position of the tile on the board
     * @return true if the tile in the position pos is on the board
     */
    public boolean isInside(Position pos) {
        return pos.getRow() <= getSize() - 1 && pos.getRow() >= 0
                && pos.getColumn() <= getSize() - 1 && pos.getColumn() >= 0;
    }

    /**
     * this method checks the Tile on the position and return it
     *
     * @param pos is the posiion of the tile on the board
     * @return return the Tile on the position pos
     */
    public Tile getTile(Position pos) {
        return tiles[pos.getRow()][pos.getColumn()];
    }

    /**
     *
     * @param tile represents the value of the tile
     * @param pos is the position of the tile on the board
     * @return true if the player can put the tile on the board with the
     * position given in parameter false if we can't put it
     */
    public boolean canBePut(Tile tile, Position pos) {
        Tile currentTile2 = tiles[pos.getRow()][pos.getColumn()];
        if (currentTile2 != null && currentTile2.getValue() == tile.getValue()) {
            return true;
        }
        for (int i = 0; i < getSize(); i++) {
            Tile currentTile = tiles[i][pos.getColumn()];
            if (i == pos.getRow()) {
                continue;
            }
            if (i < pos.getRow()) {
                if (currentTile != null && currentTile.getValue() >= tile.getValue()) {
                    return false;
                }
            } else {
                if (currentTile != null && currentTile.getValue() <= tile.getValue()) {
                    return false;
                }
            }
        }
        for (int j = 0; j < getSize(); j++) {
            Tile currentTile = tiles[pos.getRow()][j];
            if (j == pos.getColumn()) {
                continue;
            }
            if (j < pos.getColumn()) {
                if (currentTile != null && currentTile.getValue() >= tile.getValue()) {
                    return false;
                }
            } else {
                if (currentTile != null && currentTile.getValue() <= tile.getValue()) {
                    return false;
                }
            }
        }

        return true;

    }

    /**
     * this method put the tile on the position given on the board
     *
     * @param tile represents the value of the tile
     * @param pos is postion on the board
     */
    public void put(Tile tile, Position pos) {
        tiles[pos.getRow()][pos.getColumn()] = tile;
    }

    /**
     * this method checks if there is any null value on the board to verifies if
     * it's full or not
     *
     * @return true if the board is full false if its not
     */
    public boolean isFull() {
        for (int lg = 0; lg < getSize(); lg++) {
            for (int col = 0; col < getSize(); col++) {
                if (tiles[lg][col] == null) {
                    return false;
                }
            }
        }
        return true;

    }

}
