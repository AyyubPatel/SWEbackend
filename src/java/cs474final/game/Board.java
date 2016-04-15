package cs474final.game;

import cs474final.game.Piece;
//import cs474final.game.Team;

/*
 * The Board is also a passive object - it does not drive the game, it simply represents the game
 * in its current state. Will act as a container for piece objects. If there is no piece in the
 * board location, the location will be null.
 */
public class Board{
    final int size = 8; // We're only handling one checker board, and it's size 8x8. 
    
    Piece [][] locations = new Piece[size][size]; //sizexsize array with nothing in it

    public Board(Piece [] team0, Piece [] team1){
        //Place pieces for team 0
        for (int i = 0; i < team0.length; i++){
            if (team0[i] != null)
                locations[team0[i].getLocation()[0]][team0[i].getLocation()[1]] = team0[i];
        }
        //Place pieces for team 1 
        for (int i = 0; i < team1.length; i++){
            if (team1[i] != null)
                locations[team1[i].getLocation()[0]][team1[i].getLocation()[1]] = team1[i];
        }
    }

    void updateBoard(Piece [] team0, Piece [] team1){
        // Clears out old board information to update with team piece information
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                locations[i][j] = null;
            }
        }

        //Place pieces for team 0
        for (int i = 0; i < team0.length; i++){
            if (team0[i] != null)
                locations[team0[i].getLocation()[0]][team0[i].getLocation()[1]] = team0[i];
        }
        //Place pieces for team 1 
        for (int i = 0; i < team1.length; i++){
            if (team1[i] != null)
                locations[team1[i].getLocation()[0]][team1[i].getLocation()[1]] = team1[i];
        }
    }
}
