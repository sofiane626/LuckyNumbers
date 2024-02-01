package g56411.luckynumbers.model;

import java.util.ArrayList;
import java.util.List;

import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Sofiane
 */
public class Game implements Model {

    private State state;
    private int playerCount;
    private int currentPlayerNumber;
    private Board[] boards;
    private Tile pickedTile;
    private Deck deck;

    /**
     * create a game and initialize the state to NOT_STARTED
     */
    public Game() {
        state = State.NOT_STARTED;

    }

    @Override
    public void start(int playerCount) {
        if (this.state != State.NOT_STARTED && this.state != State.GAME_OVER) {
            throw new IllegalStateException("we should be in NOT_STARTED "
                    + "or in GAME_OVER ");
        }
        if (playerCount < 2 || playerCount > 4) {
            throw new IllegalArgumentException("Number of player is not "
                    + "between 2 and 4");
        }
        this.deck = new Deck(playerCount);
        this.currentPlayerNumber = 0;
        this.playerCount = playerCount;
        this.state = State.PICK_TILE;
        boards = new Board[playerCount];
        for (int i = 0; i < playerCount; i++) {
            boards[i] = new Board();
        }
        for (int i = 0; i < this.playerCount; i++) {
            putTileFirst(pickFourTiles(), i);
        }

    }

    Tile pickTile(int value) {
        if (this.state != State.PICK_TILE) {
            throw new IllegalArgumentException("we are in the wrong state "
                    + "we should be in the state PICK_TILE");
        }
        this.state = State.PLACE_TILE;
        return this.pickedTile = new Tile(value);
    }

    @Override
    public int getBoardSize() {
        return boards[0].getSize();
    }

    /**
     *
     * @return a list of four random sorted tile
     */
    private List<Tile> pickFourTiles() {
        List<Tile> firstFour = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            firstFour.add(deck.pickFaceDown());

        }
        Collections.sort(firstFour);
        return firstFour;
    }

    /**
     *
     * @param list is a list that contains the four random tile that we will put
     * in the board of the player
     * @param numberPlayer we will put these 4 tiles in the board of the that
     * player in parameter
     */
    private void putTileFirst(List<Tile> list, int numberPlayer) {
        int pos = 0;

        for (int i = 0; i < 4; i++) {
            boards[numberPlayer].put(list.get(i), new Position(pos, pos));
            pos++;
        }

    }

    @Override
    public void putTile(Position pos) {
        if (state != State.PLACE_TILE && state != State.PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("we are in the wrong state "
                    + "we should be in PLACE_TILE ");
        }
        if (!isInside(pos)) {
            throw new IllegalArgumentException("position outside of the board");
        }
        if (canTileBePut(pos)) {
            if (boards[currentPlayerNumber].getTile(pos) != null) {
                deck.putBack(boards[currentPlayerNumber].getTile(pos));
            }
            boards[currentPlayerNumber].put(pickedTile, pos);

        } else {
            throw new IllegalArgumentException("you cannot put the picked tile"
                    + "on this position");
        }
        if (boards[this.currentPlayerNumber].isFull() || faceDownTileCount() == 0) {
            state = State.GAME_OVER;
        } else {
            state = State.TURN_END;

        }

    }

    @Override
    public void nextPlayer() {
        if (state != State.TURN_END) {
            throw new IllegalStateException("We are in the wrong state "
                    + "we should be in TURN_END ");

        }
        this.currentPlayerNumber = (currentPlayerNumber + 1) % playerCount;
        this.state = State.PICK_TILE;

    }

    @Override
    public int getPlayerCount() {
        if (state == State.NOT_STARTED) {
            throw new IllegalStateException("We are in the wrong state "
                    + "we should not be in NOT_STARTED");

        }
        return playerCount;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public int getCurrentPlayerNumber() {
        if (state == State.NOT_STARTED || state == State.GAME_OVER) {
            throw new IllegalStateException("We are in the wrong state"
                    + " we should not be in NOT_STARTED or in GAME_OVER");

        }
        return this.currentPlayerNumber;
    }

    @Override
    public Tile getPickedTile() {
        if (state != State.PLACE_TILE && state != State.PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("We are in the wrong state "
                    + "we should be in PLACE_TILE");
        }
        return this.pickedTile;
    }

    @Override
    public boolean isInside(Position pos) {
        return boards[currentPlayerNumber].isInside(pos);
    }

    @Override
    public boolean canTileBePut(Position pos) {
        if (state != State.PLACE_TILE && state != State.PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("We are in the wrong state"
                    + " we should be in PLACE_TILE");
        }
        if (!isInside(pos)) {
            throw new IllegalArgumentException("we are outside the board");
        }
        return boards[currentPlayerNumber].canBePut(pickedTile, pos);

    }

    @Override
    public Tile getTile(int playerNumber, Position pos) {
        if (state == State.NOT_STARTED) {
            throw new IllegalStateException("we should not be in the"
                    + " NOT_STARTED state");
        }
        if (!isInside(pos)) {
            throw new IllegalArgumentException("we are outside the board");
        }
        if (playerNumber >= playerCount || playerNumber < 0) {
            throw new IllegalArgumentException("player number is out of range");
        }
        return boards[playerNumber].getTile(pos);
    }

    /**
     *
     * @param playerNumber is the number of the player we want to check empty
     * space in his boards
     * @return the remaining tile on the board of the player in parameter
     */
    private int remainingTile(int playerNumber) {
        int remainingTile = 0;
        for (int i = 0; i < boards[playerNumber].getSize(); i++) {
            for (int j = 0; j < boards[playerNumber].getSize(); j++) {
                Position pos = new Position(i, j);
                if (boards[playerNumber].getTile(pos) == null) {
                    remainingTile++;
                }

            }

        }
        return remainingTile;
    }

    @Override
    public List<Integer> getWinners() {
        if (state != State.GAME_OVER) {
            throw new IllegalStateException("We should be in"
                    + " the state GAME_OVER");
        }
        List<Integer> winners = new ArrayList<>();
        if (boards[this.currentPlayerNumber].isFull()) {
            winners.add(this.currentPlayerNumber);
        } else {
            int min = remainingTile(0);
            winners.add(0);
            for (int i = 1; i < getPlayerCount(); i++) {
                if (remainingTile(i) < min) {
                    winners.clear();
                    winners.add(i);
                    min = remainingTile(i);
                } else if (remainingTile(i) == min) {
                    winners.add(i);
                }
            }
        }
        return winners;

    }

    @Override
    public Tile pickFaceDownTile() {
        if (this.state != State.PICK_TILE) {
            throw new IllegalArgumentException("we are in the wrong state "
                    + "we should be in the state PICK_TILE");
        }
        this.pickedTile = deck.pickFaceDown();
        this.state = State.PLACE_OR_DROP_TILE;
        return this.pickedTile;
    }

    @Override
    public void pickFaceUpTile(Tile tile) {
        if (this.state != State.PICK_TILE) {
            throw new IllegalStateException("we are in the wrong state "
                    + "we should be in the state PICK_TILE");
        }
        if (!getAllfaceUpTiles().contains(tile)) {
            throw new IllegalArgumentException("the tile u choose is not "
                    + " in the deck of the face up tiles");
        }

        this.pickedTile = tile;
        deck.pickFaceUp(tile);
        this.state = State.PLACE_TILE;
    }

    @Override
    public void dropTile() {
        if (this.state != State.PLACE_OR_DROP_TILE) {
            throw new IllegalStateException("we are in the wrong state "
                    + "we should be in the state PLACE_OR_DROP_TILE");
        }
        deck.putBack(this.pickedTile);
        if (faceDownTileCount() == 0) {
            this.state = State.GAME_OVER;
        } else {
            this.state = State.TURN_END;
        }
    }

    @Override
    public int faceDownTileCount() {
        return deck.faceDownCount();
    }

    @Override
    public int faceUpTileCount() {
        return deck.faceUpCount();
    }

    @Override
    public List<Tile> getAllfaceUpTiles() {
        return deck.getAllFaceUp();
    }

}
