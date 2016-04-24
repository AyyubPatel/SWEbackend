package cs474final.game;

import cs474final.game.*;
import java.util.Scanner;

/*
 * This class is where the real action happens, essentially functions as the main.
 * Uses functionality from the game board and piece classes to simulate
 * the checkers game process. This provides a 'master view' of the game;
 * compared with the mobile checkers game, which would only allow the player
 * to access their own branch.
 */
public class PlayCheckersTerminal{
    public static void main(String [] args){
        /* Some initialization of the game */
        boolean game = true;
        Scanner sc = new Scanner(System.in);

        /* Player 0 */
        Board b0 = new Board(0);
        boolean turn0 = true;

        /* Player 1 */
        Board b1 = new Board(1);
        boolean turn1 = false;

        /* Give the players some information */
        System.out.println("***************************");
        System.out.println("*** Welcome to Checkers ***");
        System.out.println("** Player 0 use x pieces **");
        System.out.println("** Player 1 use o pieces **");
        System.out.println("***************************");
        b0.printBoard();

        while (game){
            if (turn0){
                /* Player 0's turn */

                /* Update board with Player 1's last move */
                b0.updateBoard(b1.locations);

                System.out.println("Your move, Player 0!");

                /* Make a move */
                int x1, x2, y1, y2;
                boolean move;
                do {
                    System.out.println("Select piece by entering its location as <x> <y>");
                    x1 = sc.nextInt();
                    y1 = sc.nextInt();
                    System.out.println("Make move by entering endpoint as <x> <y>");
                    x2 = sc.nextInt();
                    y2 = sc.nextInt();
                    move = b0.makeMove(x1, y1, x2, y2);
                    if (!move){
                        System.out.println("Not a valid move, try another!");
                    }
                } while (!move); //Repeat if move is invalid

                /* After the move */
                b0.printBoard();
                b1.updateBoard(b0.locations);

                turn0 = false;
                turn1 = true;
            }
            else{
                /* Player 1's turn */

                /* Update board with Player 0's last move */
                b1.updateBoard(b0.locations);

                System.out.println("Your move, Player 1!");

                /* Make a move */
                int x1, x2, y1, y2;
                boolean move;
                do {
                    System.out.println("Select piece by entering its location as <x> <y>");
                    x1 = sc.nextInt();
                    y1 = sc.nextInt();
                    System.out.println("Make move by entering endpoint as <x> <y>");
                    x2 = sc.nextInt();
                    y2 = sc.nextInt();
                    move = b1.makeMove(x1, y1, x2, y2);
                    if (!move){
                        System.out.println("Not a valid move, try another!");
                    }
                } while (!move); //Repeat if move is invalid

                /* After the move */
                b1.printBoard();
                b0.updateBoard(b1.locations);
                turn1 = false;
                turn0 = true;
            }

            /*
             * After a move, inspect Player 0's board to check if the game is
             * over. This should change in the mobile app version of the game.
             */
            int [] piecesRemaining = b0.teamCount();
            if (piecesRemaining[0] == 0){
                game = false;
                System.out.println("Player 1 wins!");
            }
            if (piecesRemaining[1] == 0){
                game = false;
                System.out.println("Player 0 wins!");
            }
        }
    }
}
