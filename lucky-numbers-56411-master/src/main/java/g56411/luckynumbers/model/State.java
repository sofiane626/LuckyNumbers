/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56411.luckynumbers.model;

/**
 *
 * @author Sofiane
 */
public enum State {
    /*
    NOT_STARTED is the state we are in when we did not start the game
       
    PICK_TILE   is the state where we we have to pick a tile 
      
    
    PLACE_TILE  is the state where the player choose a face up tile that he have
    to place on his board
    
    PLACE_OR_DROP_TILE is the state when the user pick a random face down tile 
    and decides wether or to put back or place the tile
       
    GAME_OVER   is the state where the game stop so basically when one of
    the players  complete his board
      
    TURN_END    is the state where we have to pass to the next player 
    */
    
    NOT_STARTED, PICK_TILE, PLACE_TILE,PLACE_OR_DROP_TILE, GAME_OVER, TURN_END;
    
    
}
