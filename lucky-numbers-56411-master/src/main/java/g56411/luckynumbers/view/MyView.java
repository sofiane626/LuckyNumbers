/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56411.luckynumbers.view;

import g56411.luckynumbers.model.Model;
import g56411.luckynumbers.model.Position;
import g56411.luckynumbers.model.State;
import g56411.luckynumbers.model.Tile;
import java.util.Scanner;

/**
 *
 * @author Sofiane
 */
public class MyView implements View {

    private Model model;

    public MyView(Model model) {
        this.model = model;
    }

    @Override
    public void displayWelcome() {
        System.out.println("   -----Lucky Numbers-----            ");
        System.out.println("    Version of the game:v02.1   ");
        System.out.println("The autor of the game is Sofiane ");
        System.out.println("================================");
    }

    @Override
    public void displayGame() {
        if (model.getState() == State.PICK_TILE) {
            displayBoard();
        } else if (model.getState() == State.TURN_END) {
            displayBoard();
            System.out.println("******************");
            System.out.println("Next Player Turn");
            System.out.println("******************");
        }

    }

    @Override
    public void displayWinner() {
        Scanner kdb = new Scanner(System.in);
        if (model.getWinners().size() != 1) {
            System.out.println("!!!!!!!!!!!!!These are the Winners !!!!!!!!!!");
            for (int i = 0; i < model.getWinners().size(); i++) {
                int winner = model.getWinners().get(i);
                winner++;
                System.out.println("winner number " + (i + 1) + " is the player number"
                        + " :" + winner);

            }
        } else {
            int win = model.getWinners().get(0);
            win++;
            System.out.println("The Winner is the player number " + win);
        }
        System.out.println("Do you want a new round with your friends?");
        System.out.println("Type Y if you want a new round or type something else "
                + " if you want to stop the game ");
        if (kdb.nextLine().equals("Y")) {
            int playerCount = askPlayerCount();
            model.start(playerCount);
        } else {
            System.out.println("GoodBye");
            System.exit(0);
        }
    }

    @Override
    public int askPlayerCount() {
        int numberPlayer = LectureRobuste("How many player will participate "
                + " to this game");
        while (numberPlayer < 2 || numberPlayer > 4) {
            System.out.println("The value that you just enter is not "
                    + " between 2 and 4");
            numberPlayer = LectureRobuste("How many player will participate "
                    + "to this game");
        }
        return numberPlayer;

    }

    @Override
    public Position askPosition() {
        int row, column;
        System.out.println("Enter the row and the column "
                + " where you want to put your tile");
        row = LectureRobuste("the row :");
        while (row < 1 || row > 4) {
            row = LectureRobuste("enter a correct position for "
                    + " the row(between 1 and 4):");
        }
        column = LectureRobuste("the column:");
        while (column < 1 || column > 4) {
            column = LectureRobuste("enter a correct position "
                    + "for the column(between 1 and 4):");
        }

        Position pos = new Position(row - 1, column - 1);
        return pos;
    }

    @Override
    public void displayError(String message) {
        System.out.println(message);
    }

    /**
     * this method display the board of the currentPlayer and the number of
     * current player too
     *
     */
    private void displayBoard() {
        int player = model.getCurrentPlayerNumber() + 1;
        System.out.println("Board of the player " + player);
        System.out.println("  | 1 | 2 | 3 | 4 |");
        System.out.println("====================");
        int val = 1;

        for (int i = 0; i < model.getBoardSize(); i++) {

            System.out.print(val + " |");
            for (int j = 0; j < model.getBoardSize(); j++) {
                Tile currentTile = model.getTile(model.getCurrentPlayerNumber(),
                        new Position(i, j));
                if (currentTile == null) {
                    System.out.print(" . |");

                } else {
                    System.out.print(String.format("%3d",
                            currentTile.getValue()) + "|");

                }

            }
            val++;
            if (val <= 4) {
                System.out.println("");
                System.out.println("--------------------");
            } else {
                System.out.println("");
            }

        }
        System.out.println("====================");

    }

    /**
     * this method will just make sure that the user enter a integer value
     *
     * @param message we will display to ask a value to the user
     * @return a correct integer value that the user entered
     */
    private static int LectureRobuste(String message) {
        Scanner clavier = new Scanner(System.in);
        System.out.println(message);

        while (!clavier.hasNextInt()) {
            clavier.next();
            System.out.println("you have to enter a positive integer value ");
            System.out.println(message);
        }
        return clavier.nextInt();
    }

    /**
     * this method display the remaining numbers of face down tiles on the deck
     *
     */
    @Override
    public void displayFaceDownTiles() {
        System.out.println("there is " + model.faceDownTileCount()
                + " remaining face down tiles");

    }

    /**
     * this method display the remaining face up tiles on the deck only if we
     * have minimum of one tile face up
     */
    @Override
    public void displayFaceUpTiles() {

        if (model.faceUpTileCount() != 0) {
            System.out.println("Face Up Tiles");
            System.out.println("---------------");

            for (int i = 0; i < model.getAllfaceUpTiles().size(); i++) {
                System.out.print(model.getAllfaceUpTiles().get(i).getValue() + " ");

            }
            System.out.println("");
        } else {
            System.out.println("There is no face up tile in the deck");
        }
        System.out.println("**********************************************");
    }

    /**
     * this method ask to the player if he wants a face up tiles or a random
     * face down tiles and then pick it
     */
    @Override
    public void askPlayerFaceOfTile() {
        if (model.getAllfaceUpTiles().isEmpty()) {
            model.pickFaceDownTile();

        } else {

            String message = ("type 1 if you want a face up tile or 2 if you want"
                    + " a random face down tile");
            int answer = LectureRobuste(message);
            while (answer != 1 && answer != 2) {
                System.out.println("you have to type 1 or 2 to answer");
                answer = LectureRobuste(message);
            }
            if (answer == 1) {
                playerPickFaceUp();
            } else {
                model.pickFaceDownTile();
            }

        }
    }

    /**
     * this method askp the player if he wants to drop or put the random face
     * down tile that he got
     */
    @Override
    public void askPlayerDropOrPut() {
        if (model.getState() == State.PLACE_OR_DROP_TILE) {
            System.out.println("This is your picked tile : "
                    +model.getPickedTile().getValue());
            String message = ("Type 1 if you want to drop your tile or"
                    + " something else if you want to put it");
            int answer = LectureRobuste(message);
            if (answer == 1) {
                model.dropTile();
            }
        }
    }

    /**
     * this method is called when the player choose to pick a face Up tile
     */
    private void playerPickFaceUp() {
        Scanner kdb = new Scanner(System.in);
        System.out.println("Choose among these face up tile.s");

        int tileChoosen = kdb.nextInt();
        Tile tile = new Tile(1);
        boolean find = false;
        while (!find) {
            for (int i = 0; i < model.getAllfaceUpTiles().size(); i++) {
                if (model.getAllfaceUpTiles().get(i).getValue() == tileChoosen) {
                    tile = model.getAllfaceUpTiles().get(i);
                    find = true;
                }

            }
            if (!find) {
                System.out.println("mmmmh this tile is not face up "
                        + "choose a correct face up tiles");
                tileChoosen = LectureRobuste("You can choose among these face up tile.s");
            }
        }
        model.pickFaceUpTile(tile);
    }
}
