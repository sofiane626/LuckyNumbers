package g56411.luckynumbers.model;

/**
 * a tile is a card with a value that we'll place in a board 16x16
 *
 * @author Sofiane
 */
public class Tile implements Comparable<Tile>{

    private final int value;
    private boolean faceUp;
    

    /**
     * build a new tile with a value between 1 and 20 and initialize it 
     * faceDown
     *
     * @param value given to the tile
     *       
     */
    public Tile(int value) {
        this.value = value;
        this.faceUp = false;
    }

    /**
     *
     * @return the value of the tile
     */
    public int getValue() {
        return value;
    }
    /**
     * 
     * @return true if the Tile is face up false if it's hidden to the players
     */
    public boolean isFaceUp() {
        return faceUp;
    }
    /**
     * this method change the state of the tile to true if the Tile is not Face
     * Up when we use it
     */
    void flipFaceUp() {
        if(!faceUp){
            faceUp=true;
        }
    }

    @Override
    public int compareTo(Tile o) {
      return this.value-o.value;
    }

}
