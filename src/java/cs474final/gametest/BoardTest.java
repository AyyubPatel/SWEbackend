package cs474final.gametest;

import cs474final.game.Board;
import cs474final.game.Piece;
import org.junit.*;
import static org.junit.Assert.*;

public class BoardTest{
    Board b0, b1, b2; // Corresponds to different teams' board views
    Piece [][] newLocations;

    @Before
    public void setUp(){
        b0 = new Board(0); //Team0 board
        b1 = new Board(1); //Team1 board
        b2 = new Board(1); //Team1 board

        //Set up sparser board for test cases. Assume updateBoard() method
        //works - we will also test this in a separate method.

        newLocations = new Piece[8][8];
        // Clear out board array.
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                b0.locations[i][j] = null;
                b1.locations[i][j] = null;
                newLocations[i][j] = null;
            }
        }

        //Populate the new locations array with pieces
        /* TODO good coverage will be:
         * 4 simple moves for team0 and team1, 2 by regular pieces, 2 by kings
         * 4 jump moves for team0 and team1, 2 by regular pieces, 2 by kings
         * Come up with a creative way to make this flow naturally.
         */
         //For updateBoard Test
         newLocations[3][3] = new Piece(0, 5);

         b0.locations[4][6] = new Piece(0, 0);
         b1.locations[4][6] = new Piece(0, 0);

         b0.locations[3][5] = new Piece(1, 0);
         b1.locations[3][5] = new Piece(1, 0);

         b0.locations[2][2] = new Piece(0, 1);
         b1.locations[2][2] = new Piece(0, 1);

         b0.locations[3][3] = new Piece(1, 1);
         b1.locations[3][3] = new Piece(1, 1);

         b0.locations[3][1] = new Piece(1, 2);
         b1.locations[3][1] = new Piece(1, 2);

         b0.locations[4][2] = new Piece(0, 2);
         b1.locations[4][2] = new Piece(0, 2);

         b0.locations[5][5] = new Piece(1, 3);
         b1.locations[5][5] = new Piece(1, 3);
    }

    @After
    public void tearDown(){
        b0 = null;
        b1 = null;
        b2 = null;
        newLocations = null;
    }

    //Test Setup
    @Test
    public void defaultGameTest(){
        //TODO write test that makes sure locations array is correct on setup
        //We are only testing a handful of locations, because it is clearly
        //set up correctly from visual inspection of the terminal representation
    }

    @Test
    public void testUpdate(){
        //TODO test to make sure b2 now has the same pieces as newLocations
        b2.updateBoard(newLocations);
        assertTrue(b2.locations[3][3].equals(new Piece(0, 5)));
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (i != 3 && j != 3){
                     assertTrue(b2.locations[i][j] == null);
                }
            }
        }
    }

    @Test
    public void testIllegalMoves(){
        //TODO write tests for a series of moves
    }

    @Test
    public void testComplexLegalMoves(){
        /*________________
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|x|_|_|_|
          |_|_|_|o|_|o|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|_|o|_|_|_|_|
          |_|_|x|_|x|_|_|_|
          |_|x|_|o|_|_|_|_|
          |_|_|_|_|_|_|_|_|
        */

        //Simple Top Left Normal Team0
        assertFalse(b0.locations[4][6].isKing());
        boolean move0 = b0.makeMove(4, 6, 3, 7);
        assertTrue(move0);
        assertTrue(b0.locations[4][6] == null); //Start
        Piece tmp1 = new Piece(0, 0);
        tmp1.kingMe();
        assertTrue(b0.locations[3][7].equals(tmp1)); //End
        assertTrue(b0.locations[3][7].isKing());

        b1.updateBoard(b0.locations); //Update other Board
        /*________________
          |_|_|_|x|_|_|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|_|o|_|o|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|_|o|_|_|_|_|
          |_|_|x|_|x|_|_|_|
          |_|_|_|o|_|_|_|_|
          |_|_|_|_|_|_|_|_|
        */
        //Simple Bottom Right Kinged Team0
        boolean move1 = b0.makeMove(3, 7, 4, 6);
        assertTrue(move1);
        assertTrue(b0.locations[3][7] == null); //Start
        assertTrue(b0.locations[4][6].equals(tmp1)); //End

        b1.updateBoard(b0.locations); //Update other board

        /*________________
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|x|_|_|_|
          |_|_|_|o|_|o|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|_|o|_|_|_|_|
          |_|_|x|_|x|_|_|_|
          |_|_|_|o|_|_|_|_|
          |_|_|_|_|_|_|_|_|
        */

        //Jump Bottom Left Kinged Team0
        boolean move3 = b0.makeMove(4, 6, 2, 4);
        assertTrue(move3);
        assertTrue(b0.locations[4][6] == null); //Start
        assertTrue(b0.locations[3][5] == null); //Taken
        assertTrue(b0.locations[2][4].equals(tmp1)); //End

        b1.updateBoard(b0.locations);

        /*________________
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|_|o|_|_|
          |_|_|x|_|_|_|_|_|
          |_|_|_|o|_|_|_|_|
          |_|_|x|_|x|_|_|_|
          |_|_|_|o|_|_|_|_|
          |_|_|_|_|_|_|_|_|
        */

        //Jump Top Right Normal Team0
        boolean move4 = b0.makeMove(2, 2, 4, 4);
        assertTrue(move4);
        assertTrue(b0.locations[2][2] == null); //Start
        assertTrue(b0.locations[3][3] == null); //Taken
        assertTrue(b0.locations[4][4].equals(new Piece(0, 1))); //End

        b1.updateBoard(b0.locations); //Update other board

        /*________________
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|_|o|_|_|
          |_|_|x|_|x|_|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|x|_|_|_|
          |_|_|_|o|_|_|_|_|
          |_|_|_|_|_|_|_|_|
        */

        // Now we'll do Team1 moves
        //Simple Bottom Right Normal Team1
        boolean move5 = b1.makeMove(3, 1, 4, 0);
        assertTrue(move5);
        assertTrue(b1.locations[3][1] == null); //Start
        Piece tmp2 = new Piece(1, 2);
        tmp2.kingMe();
        assertTrue(b1.locations[4][0].equals(tmp2)); //End

        b0.updateBoard(b1.locations);

        /*________________
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|_|o|_|_|
          |_|_|x|_|x|_|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|x|_|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|o|_|_|_|
        */

        //Simple Top Left Kinged Team1
        boolean move6 = b1.makeMove(4, 0, 3, 1);
        assertTrue(move6);
        assertTrue(b1.locations[4][0] == null); //Start
        assertTrue(b1.locations[3][1].equals(tmp2)); //End

        b0.updateBoard(b1.locations);
        /*________________
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|_|o|_|_|
          |_|_|X|_|x|_|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|x|_|_|_|
          |_|_|_|O|_|_|_|_|
          |_|_|_|_|_|_|_|_|
        */

        //Jump Top Right Kinged Team1
        boolean move7 = b1.makeMove(3, 1, 5, 3);
        assertTrue(move7);
        assertTrue(b1.locations[3][1] == null); //Start
        assertTrue(b1.locations[4][2] == null); //Taken
        assertTrue(b1.locations[5][3].equals(tmp2)); //End

        b0.updateBoard(b1.locations); //Update other board
        /*________________
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|_|o|_|_|
          |_|_|X|_|x|_|_|_|
          |_|_|_|_|_|O|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|_|_|_|_|
        */

        //Jump Bottom Left Normal Team1
        boolean move8 = b1.makeMove(5, 5, 3, 3);
        assertTrue(move8);
        assertTrue(b1.locations[5][5] == null); //Start
        assertTrue(b1.locations[4][4] == null); //Taken
        assertTrue(b1.locations[3][3].equals(new Piece(1, 3))); //End

        b0.updateBoard(b1.locations); //Update other board
        /*________________
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|X|_|_|_|_|_|
          |_|_|_|o|_|O|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|_|_|_|_|
          |_|_|_|_|_|_|_|_|
        */

    }



}
