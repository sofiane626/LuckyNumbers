/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56411.luckynumbers.LuckyNumbers;

import g56411.luckynumbers.controller.Controller;
import g56411.luckynumbers.model.Game;
import g56411.luckynumbers.model.Model;
import g56411.luckynumbers.view.MyView;

/**
 *
 * @author Sofiane
 */
public class LuckyNumbers {

    public static void main(String[] args) {
        Model game = new Game();
        Controller controller = new Controller(game, new MyView(game));
        controller.play();
    }

}
