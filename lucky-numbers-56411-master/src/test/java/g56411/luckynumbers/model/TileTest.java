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
public class TileTest {
    
    public TileTest() {
    }

    @Test
    public void test_flipFcaeUp() {
        Tile tile=new Tile(15);
        tile.flipFaceUp();
        assertEquals(true,tile.isFaceUp() );
    }
    @Test
    public void test_faceUp_is_False_first() {
        Tile tile=new Tile(15);
        assertEquals(false,tile.isFaceUp() );
        
        
    }
        @Test
    public void test_Stay_True_When_faceUp_already_True() {
        Tile tile=new Tile(15);
        tile.flipFaceUp();
        tile.flipFaceUp();
        assertEquals(true,tile.isFaceUp() );
        
        
        
    }
    
    
}
