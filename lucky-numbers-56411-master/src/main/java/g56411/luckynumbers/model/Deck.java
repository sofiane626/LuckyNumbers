
package g56411.luckynumbers.model;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;


/**
 *
 * @author Sofiane
 */
public class Deck {

    private List<Tile> faceUpTiles;
    private List<Tile> faceDownTiles;
    private List<Tile> firstTiles;

    /**
     * this methode creates a deck of Tiles with a value from 1 to 20 the times
     * nomber of player so 2 player would have 40 Tiles in the deck
     *
     * @param playerCount number of players in the game
     */
    public Deck(int playerCount) {
        faceDownTiles = new ArrayList<>();
        faceUpTiles = new ArrayList<>();

         for (int i = 0; i < playerCount; i++) {
            for (int j = 1; j < 21; j++) {
                faceDownTiles.add(new Tile(j));
            }

        }
    }

    /**
     *
     * @return the number of tile remaining in the list of the face Down Tiles
     */
    public int faceDownCount() {
        return this.faceDownTiles.size();
    }

    /**
     * this method choose a random tile in the list of the tiles faceDown
     *
     * @return the random tile choosen
     */
    public Tile pickFaceDown() {
        int index = (int) (Math.random() * faceDownCount());
        Tile tile = this.faceDownTiles.remove(index);
        tile.flipFaceUp();
        return tile;

    }
    
    /**
     *
     * @return the number of tile face Up in the deck
     */
    public int faceUpCount() {
        return this.faceUpTiles.size();
    }

    /**
     * @return the list that contains all the tiles face Up in the deck
     */
    public List<Tile> getAllFaceUp() {
        return Collections.unmodifiableList(faceUpTiles);
    }

    List<Tile> getAllFaceDown() {
        return this.faceDownTiles;
    }

    /**
     *
     * @param tile is the Tile that we want to check if it's face up or down
     * @return true if it's face up and false if it's face Down
     */
    public boolean hasFaceUp(Tile tile) {
        return  tile.isFaceUp();
    }

    /**
     *
     * @param tile is the Tile that we want to put back this method put back the
     * tile in parameter in the list of the tiles faceUp
     */
    public void putBack(Tile tile) {
        this.faceUpTiles.add(tile);
    }

    /**
     * this method remove the tile in parameter from the deck
     *
     * @param tile is the tile in the list of the tiles face up that we want to
     * remove
     */
    public void pickFaceUp(Tile tile) {
        
        this.faceUpTiles.remove(tile);

    }

}
