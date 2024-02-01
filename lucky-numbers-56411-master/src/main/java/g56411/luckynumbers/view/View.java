/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56411.luckynumbers.view;

import g56411.luckynumbers.model.Position;

/**
 *
 * @author Sofiane
 */
public interface View {
    /**
     * this method will be use in the begining of the game 
     * and will basically display the name of the players,
     * the autor and the version of the game
     * 
     */
    public void displayWelcome();
    /**
     * this method display  the board of the current player and the tile 
     * that he have to place
     * and if the tile is a random face down tile we ask to the current player
     * if he wants to drop the tile or put it in his board
     */
    public void displayGame();
    /**
     * this method display the winner of the game and after ask if the 
     * players wants to play again or not
     */
    public void displayWinner();
    /**
     * ask the number of player that will join the game 
     * the number of player is between 2 and 4
     * @return  the number of player
     */
    public int  askPlayerCount();
    /**
     * ask to the current player the number of the row and the number of the column 
     * he want to put his tile
     * @return the position with the row and the column he choose and verify
     * that the position is between 1 and 4
     */
    public Position askPosition() ;
    /**
     * this method will be used when we have an error and print the message in parameter
     * @param message is the message we will display if we have an error in the game
     * 
     */
    public void  displayError(String message);
    /**
     * this method displays the number of tiles face down remaining on the deck
     */
    void displayFaceDownTiles();
    /**
     * this method displays all the tiles face up
     */
    void displayFaceUpTiles();
    /**
     * this method askp to the current player if he wants a face down or a face 
     * up tile 
     */
    void askPlayerFaceOfTile();
    /**
     * this method ask the player if he wants to put ou drop the picked tile
     */
    void askPlayerDropOrPut();
    
}
