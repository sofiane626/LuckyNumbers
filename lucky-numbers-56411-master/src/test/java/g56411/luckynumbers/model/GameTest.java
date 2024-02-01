/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56411.luckynumbers.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Sofiane
 */
public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    /* =====================
         Tests for start()
       ===================== */

 /* --- test related to the state --- */
    @Test
    public void start_when_game_not_started_ok() {
        game.start(4);
    }

    @Test
    public void start_when_game_over_ok() {
        fullPlay();
        game.start(2);
    }

    /* Play a game till the end */
    private void fullPlay() {
        game.start(2);
        int value = 1;
        int pos = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < game.getPlayerCount(); j++) {
                game.pickTile(value);
                game.putTile(new Position(pos, pos));
                game.nextPlayer();
            }
            pos++;
            value = value + 5;
        }
        int line = 0;
        int col = 1;
        value = 2;
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < game.getPlayerCount(); j++) {
                game.pickTile(value);
                game.putTile(new Position(line, col));
                game.nextPlayer();
            }
            value++;
            col++;
            if (col == game.getBoardSize()) {
                col = 0;
                line++;
            }

        }
        game.pickTile(15);
        game.putTile(new Position(3, 2));

    }

    @Test
    public void start_when_game_in_progress_ISE() {
        game.start(4);
        assertThrows(IllegalStateException.class,
                () -> game.start(1));
    }

    @Test
    public void start_state_changed_to_PICK_TILE() {
        game.start(3);
        assertEquals(State.PICK_TILE, game.getState());
    }

    /* --- tests related to the parameter --- */
    @Test
    public void start_playerCount_too_small_Exception() {

        assertThrows(IllegalArgumentException.class,
                () -> game.start(1));
    }

    @Test
    public void start_playerCount_minimum_accepted() {
        game.start(2);
    }

    @Test
    public void start_playerCount_maximum_accepted() {
        game.start(4);
    }

    @Test
    public void start_playerCount_too_big_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> game.start(5));
    }

    /* -- tests related to fields initialization --- */
    @Test
    public void start_playerCount_initialized() {
        game.start(4);
        assertEquals(4, game.getPlayerCount());
    }

    @Test
    public void start_current_player_is_player_0() {
        game.start(4);
        assertEquals(0, game.getCurrentPlayerNumber());
    }

    @Test
    public void getBoardSize_size_is_4() {
        game.start(3);
        assertEquals(4, game.getBoardSize());
    }

    @Test
    public void pickTile_is_correct() {
        game.start(2);
        int value = 1;

        for (int turn = 1; turn < game.getBoardSize() * game.getBoardSize(); turn++) {
            for (int player = 0; player < game.getPlayerCount(); player++) {
                game.pickTile(value);
                break;
            }
            break;

        }
        assertEquals(value, game.getPickedTile().getValue());

    }

    @Test
    public void Position_false_when_row_above4() {
        game.start(2);
        game.pickTile(10);
        game.isInside(new Position(5, 0));
        assertEquals(false, game.isInside(new Position(5, 0)));

    }

    @Test
    public void Position_false_when_Column_above4() {
        game.start(2);
        game.pickTile(10);
        game.isInside(new Position(0, 5));
        assertEquals(false, game.isInside(new Position(0, 5)));

    }

    @Test
    public void nextPlayer_return_to_0_after_the_lastPlayer() {
        game.start(2);
        game.pickTile(10);
        game.putTile(new Position(1, 1));
        game.nextPlayer();
        game.pickTile(10);
        game.putTile(new Position(1, 1));
        game.nextPlayer();
        assertEquals(0, game.getCurrentPlayerNumber());

    }

    @Test
    public void change_to_next_player() {
        game.start(2);
        game.pickTile(10);
        game.putTile(new Position(1, 1));
        game.nextPlayer();

        assertEquals(1, game.getCurrentPlayerNumber());

    }

    @Test
    public void display_the_right_state() {
        fullPlay();
        assertEquals(State.GAME_OVER, game.getState());
    }

    @Test
    public void pickFaceDown_state_changed_to_PLACE_OR_DROP_TILE() {
        game.start(3);
        game.pickFaceDownTile();
        assertEquals(State.PLACE_OR_DROP_TILE, game.getState());
    }

    @Test
    public void Exception_if_pickFaceDown_when_state_is_not_PICK_TILE() {
        assertThrows(IllegalArgumentException.class,
                () -> game.pickFaceDownTile());
    }

    @Test
    public void pickFaceUp_state_changed_to_PLACE_TILE() {
        game.start(2);
        //player 1 put a tile and drop it face Up
        Tile tile = game.pickFaceDownTile();
        game.dropTile();
        game.nextPlayer();
        //player 2 put the tile face Up
        game.pickFaceUpTile(tile);
        assertEquals(State.PLACE_TILE, game.getState());
    }

    @Test
    public void Exception_if_pickFaceUp_when_state_is_not_PICK_TILE() {
        assertThrows(IllegalStateException.class,
                () -> game.pickFaceUpTile(new Tile(12)));
    }

    @Test
    public void pickedTile_is_the_correct_Tile_choosen_FaceUp() {
        game.start(2);
        //player1 put a tile and drop it
        Tile tile = game.pickFaceDownTile();
        game.dropTile();
        assertEquals(1, game.faceUpTileCount());
        game.nextPlayer();
        //player 2 choose the tile face Up
        game.pickFaceUpTile(tile);

        assertEquals(game.getPickedTile(), tile);
    }

    @Test
    public void dropTile_when_state_isNot_PLACE_OR_DROP_TILE() {
        assertThrows(IllegalStateException.class,
                () -> game.dropTile());
    }

    @Test
    public void dropTile_putBack_correctly_on_the_deck() {
        game.start(2);
        game.pickFaceDownTile();
        game.dropTile();
        assertEquals(1, game.faceUpTileCount());

    }

    @Test
    public void state_becomes_TURN_END_when_dropTile() {
        game.start(2);
        game.pickFaceDownTile();
        game.dropTile();
        assertEquals(State.TURN_END, game.getState());
    }

    @Test
    public void putTile_state_changed_to_TURN_END() {
        game.start(3);
        game.pickFaceDownTile();
        System.out.println(game.getState());
        game.putTile(new Position(1, 1));
        System.out.println(game.getState());
        assertEquals(State.TURN_END, game.getState());
    }

    @Test
    public void exception_if_nextPlayer_launch_when_GAME_OVER() {
        fullPlay();
        assertThrows(IllegalStateException.class,
                () -> game.nextPlayer());
    }

    @Test
    public void exception_if_getPlayerCount_launch_before_start() {
        assertThrows(IllegalStateException.class,
                () -> game.getPlayerCount());
    }

    @Test
    public void game_initialize_the_state_to_NOT_STARTED() {
        assertEquals(State.NOT_STARTED, game.getState());
    }

    @Test
    public void exception_when_getCurrentPlayerNumber_launch_when_state_is_NOT_STARTED() {
        assertThrows(IllegalStateException.class,
                () -> game.getCurrentPlayerNumber());
    }

    @Test
    public void exception_when_getCurrentPlayerNumber_launch_when_state_is_GAME_OVER() {
        fullPlay();
        assertThrows(IllegalStateException.class,
                () -> game.getCurrentPlayerNumber());
    }

    @Test
    public void exception_when_ask_getWinners_in_the_middle_of_the_game() {
        game.start(3);
        assertThrows(IllegalStateException.class,
                () -> game.getWinners());
    }

    @Test
    public void exception_when_getTile_launch_when_NOT_STARTED() {
        assertThrows(IllegalStateException.class,
                () -> game.getTile(0, new Position(1, 1)));
    }

    @Test
    public void exception_when_canTileBePut_launch_when_PLACE_TILE() {
        assertThrows(IllegalStateException.class,
                () -> game.canTileBePut(new Position(0, 0)));
    }

    @Test
    public void True_when_position_inside() {
        game.start(2);
        game.pickTile(10);
        assertEquals(true, game.isInside(new Position(0, 0)));
    }

    @Test
    public void State_GAME_OVER_when_gameIsFinished() {
        fullPlay();
        assertEquals(State.GAME_OVER, game.getState());
    }


    /* === À vous de compléter... === */
}
