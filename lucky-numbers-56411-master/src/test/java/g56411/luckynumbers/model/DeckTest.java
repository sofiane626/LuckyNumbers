/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56411.luckynumbers.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Sofiane
 */
public class DeckTest {

    public DeckTest() {
    }

    @Test
    public void test_the_deck() {
        Deck deck = new Deck(2);
        assertEquals(40, deck.getAllFaceDown().size());

    }

    @Test
    public void return_the_Tile_Face_Up_empty_when_deck_initialized() {
        Deck deck = new Deck(2);
        assertEquals(true, deck.getAllFaceUp().isEmpty());
    }

    @Test
    public void the_number_of_tile_FaceDown_decrease_by_one_when_pickFaceDown() {
        Deck deck = new Deck(2);
        deck.pickFaceDown();
        assertEquals(39, deck.faceDownCount());
    }

    @Test
    public void return_the_number_of_Tile_Face_Down() {
        Deck deck = new Deck(2);
        assertEquals(40, deck.faceDownCount());
    }

    @Test
    public void return_the_number_of_Tile_Face_Up() {
        Deck deck = new Deck(2);
        Tile tile = deck.pickFaceDown();
        assertEquals(0, deck.faceUpCount());
        deck.putBack(tile);
        assertEquals(1, deck.faceUpCount());
    }

    @Test
    public void remove_the_tile_faceUp_choosen() {
        Deck deck = new Deck(2);
        Tile tile = deck.pickFaceDown();
        deck.putBack(tile);
        assertEquals(1, deck.faceUpCount());
        deck.pickFaceUp(tile);
        assertEquals(0, deck.faceUpCount());

    }
    @Test
    public void test_on_the_face_of_the_tiles_faceDown() {
        Deck deck = new Deck(2);
        assertEquals(false, deck.hasFaceUp(new Tile(15)));

    }

    @Test
    public void test_on_the_face_of_the_tiles_faceUp() {
        Deck deck = new Deck(2);
        assertEquals(false, deck.hasFaceUp(new Tile(15)));
        Tile tile = deck.pickFaceDown();
        deck.putBack(tile);
        assertEquals(true, deck.hasFaceUp(tile));

    }
}
