/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56411.luckynumbers.controller;

import g56411.luckynumbers.model.Model;
import g56411.luckynumbers.model.Position;
import static g56411.luckynumbers.model.State.*;
import g56411.luckynumbers.view.View;


/**
 *
 * @author Sofiane
 */
public class Controller {

    private Model game;
    private View view;

    /**
     * this is the controller that control the game
     *
     * @param game
     * @param view
     */
    public Controller(Model game, View view) {
        this.game = game;
        this.view = view;
    }

    /**
     * this method display a message to the players and lauch the private
     * methode forPlay
     */
    public void play() {
        view.displayWelcome();
        while (true) {
            forPlay();
        }
    }

    /**
     * this method lauch a method from the model package based on what state we
     * are located and also based on what the game have to do
     */
    private void forPlay() {
        switch (game.getState()) {
            case NOT_STARTED:
                int playerCount = view.askPlayerCount();
                game.start(playerCount);
                break;

            case PICK_TILE:
                view.displayGame();
                view.displayFaceDownTiles();
                view.displayFaceUpTiles();
                view.askPlayerFaceOfTile();

                break;

            case PLACE_TILE:
                
                while (game.getState() == PLACE_TILE) {
                    try {
                      Position pos=view.askPosition();
                      game.putTile(pos);
                      view.displayGame();

                    } catch (IllegalArgumentException e) {
                        view.displayError("you can't put this"
                                + " tile in this position");
                    }
                }
                break;
            case PLACE_OR_DROP_TILE:
                view.askPlayerDropOrPut();
                if(game.getState() == PLACE_OR_DROP_TILE) {
                    try {
                        Position pos=view.askPosition();
                        game.putTile(pos);
                        view.displayGame();
                    } catch (IllegalArgumentException e) {
                        view.displayError("you can't put"
                                + " this tile in this position");
                    }
                }
                break;

            case GAME_OVER:
                
                view.displayWinner();
                
                break;

            case TURN_END:
                game.nextPlayer();

                break;
        }
    }
}
