package cs474final.game;

import cs474final.game.Piece;

/*
 * The Board represents the game in its current state. Will act as a container
 * for piece objects. If there is no piece in the board location, the location
 * will be null. Handles move functionality.
 */
public class Board{
    final int size = 8; // We're only handling one checker board, and it's size 8x8.

    public Piece [][] locations = new Piece[size][size]; // sizexsize array with nothing in it
                                                  // Indexed [x, y] like cartesian coordinate system
    final int userTeam; //Will be 0 or 1
    //Board is created at the beginning of the game with default configuration
    public Board(int userTeam){
        this.userTeam = userTeam;
        // Initialize empty board.
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                locations[i][j] = null;
            }
        }
        //Place pieces for team 0 (dark team)
        locations[0][0] = new Piece(0, 0);
        locations[2][0] = new Piece(0, 1);
        locations[4][0] = new Piece(0, 2);
        locations[6][0] = new Piece(0, 3);
        locations[1][1] = new Piece(0, 4);
        locations[3][1] = new Piece(0, 5);
        locations[5][1] = new Piece(0, 6);
        locations[7][1] = new Piece(0, 7);
        locations[0][2] = new Piece(0, 8);
        locations[2][2] = new Piece(0, 9);
        locations[4][2] = new Piece(0, 10);
        locations[6][2] = new Piece(0, 11);

        //Place pieces for team 1 (light team)
        locations[7][7] = new Piece(1, 0);
        locations[5][7] = new Piece(1, 1);
        locations[3][7] = new Piece(1, 2);
        locations[1][7] = new Piece(1, 3);
        locations[6][6] = new Piece(1, 4);
        locations[4][6] = new Piece(1, 5);
        locations[2][6] = new Piece(1, 6);
        locations[0][6] = new Piece(1, 7);
        locations[7][5] = new Piece(1, 8);
        locations[5][5] = new Piece(1, 9);
        locations[3][5] = new Piece(1, 10);
        locations[1][5] = new Piece(1, 11);
    }

    /*
     * Method that runs at the beginning of a player's turn to update the
     * board state with the board state received from the other player.
     */

    public void updateBoard(Piece [][] newLocations){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (locations[i][j] == null){
                    if (newLocations[i][j] != null){
                        locations[i][j] = newLocations[i][j];
                    }
                }
                else{
                    if (!(locations[i][j].equals(newLocations[i][j]))){
                        locations[i][j] = newLocations[i][j];
                    }
                }
            }
        }
    }

    /*
     * This method and the ones below it cover move functionality. If the
     * move is successful, return true.
     */
    public boolean makeMove(int x1, int y1, int x2, int y2){
        if (isValidMove(x1, y1, x2, y2)){

            //See if we need to remove any pieces
            if (isJump(x1, y1, x2, y2)){
                takePiece(x1, y1, x2, y2);
            }
            //Update location of moved piece
            locations[x2][y2] = new Piece(locations[x1][y1].getTeam(),
                                          locations[x1][y1].getPieceID(),
                                          locations[x1][y1].isKing());
            locations[x1][y1] = null;

            //See if our noble piece should be crowned king
            if (locations[x2][y2].getTeam() == 0 && y2 == 7){
                locations[x2][y2].kingMe();
            }
            if (locations[x2][y2].getTeam() == 1 && y2 == 0){
                locations[x2][y2].kingMe();
            }
            return true;
        }
        else{
            return false;
        }
    }

    /*
     * Helper method for makeMove to remove a taken piece in a jump move
     */
     private void takePiece(int x1, int y1, int x2, int y2){
         //Up left
         if (x2 + 2 == x1 && y2 - 2 == y1){
             locations[x2+1][y2-1] = null;
         }
         //Up right
         else if (x2 - 2 == x1 && y2 - 2 == y1){
             locations[x2-1][y2-1] = null;
         }
         //Down left
         else if (x2 + 2 == x1 && y2 + 2 == y1){
              locations[x2+1][y2+1] = null;
         }
         //Down right
         else if (x2 - 2 == x1 && y2 + 2 == y1){
             locations[x2-1][y2+1] = null;
         }
     }

    /*
     * Logical method to determine if an attempted move is valid. In sequential
     * order, the method checks that the move would be within the game board,
     * would move the player's own piece, have an unoccupied the final location,
     * is in the proper direction, and follows the rules of a simple or jump move.
     */
    private boolean isValidMove(int x1, int y1, int x2, int y2){
        if (x1 < 0 || x1 >= size || y1 < 0 || y1 >= size ||
            x2 < 0 || x2 >= size || y2 < 0 || y2 >= size){
                return false; // At least one move location is out of bounds
        }
        else{
            // Check initial location
            if (locations[x1][y1] == null) {return false;}
            else if(locations[x1][y1].getTeam() != userTeam) {return false;}
            else{
                //Check final location
                if (locations[x2][y2] != null) {return false;}
                if (isSimple(x1, y1, x2, y2)) {return true;}
                else if (isJump(x1, y1, x2, y2)) {return true;}
                else {return false;}
            }
        }
    }
    /*
     * Helper method for the validateMove method
     */
    private boolean isSimple(int x1, int y1, int x2, int y2){
        if (Math.abs(x2-x1) == 1 && Math.abs(y2-y1) == 1){
            if (locations[x1][y1].isKing()) {return true;}
            else{
                if (locations[x1][y1].getTeam() == 0){
                    // can only move in increasing y direction
                    if (y1 < y2) {return true;}
                    else {return false;}
                }
                else{ // must be team 1
                    // can only move in decreasing y direction
                    if (y2 < y1) {return true;}
                    else {return false;}
                }
            }
        }
        else {return false;}
    } //end isSimple

    /*
     * Helper method for the validateMove method
     */
    private boolean isJump(int x1, int y1, int x2, int y2){
        if (Math.abs(x2-x1) == 2 && Math.abs(y2-y1) == 2){
            //Four possible jump locations, we want to make sure that
            //this SPECIFIC jump move is acceptable.
            //Up left
            if (x2 + 2 == x1 && y2 - 2 == y1 &&
                locations[x2+1][y2-1] != null &&
                locations[x2+1][y2-1].getTeam() != userTeam){
                    if (locations[x1][y1].isKing()){ return true;}
                    else if (locations[x1][y1].getTeam() == 0) {return true;}
                    else {return false;}
            }
            //Up right
            else if (x2 - 2 == x1 && y2 - 2 == y1 &&
                     locations[x2-1][y2-1] != null &&
                     locations[x2-1][y2-1].getTeam() != userTeam){
                    if (locations[x1][y1].isKing()){ return true;}
                    else if (locations[x1][y1].getTeam() == 0) {return true;}
                    else {return false;}
            }
            //Down left
            else if (x2 + 2 == x1 && y2 + 2 == y1 &&
                     locations[x2+1][y2+1] != null &&
                     locations[x2+1][y2+1].getTeam() != userTeam){
                    if (locations[x1][y1].isKing()){ return true;}
                    else if (locations[x1][y1].getTeam() == 1) {return true;}
                    else {return false;}
            }
            //Down right
            else if (x2 - 2 == x1 && y2 + 2 == y1 &&
                     locations[x2-1][y2+1] != null &&
                     locations[x2-1][y2+1].getTeam() != userTeam){
                    if (locations[x1][y1].isKing()){ return true;}
                    else if (locations[x1][y1].getTeam() == 1) {return true;}
                    else {return false;}
            }
            else {
                return false;
            }
        }
        else {
          return false;
        }
    }
    // End Move Functionality Methods

    // Method to count the number of remaining pieces on each team
    public int [] teamCount(){
        int count0 = 0;
        int count1 = 0;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                Piece tmp = locations[i][j];
                if (tmp != null){
                    if (tmp.getTeam() == 0) {count0++;}
                    if (tmp.getTeam() == 1) {count1++;}
                }
            }
        }
        return new int[]{count0, count1};
    }

    /*
     * Method used in the terminal representation of the checker's game
     * For testing purposes, may make its way into a test class.
     */
    public void printBoard(){
        System.out.println(" ________________");
        for (int j = size-1; j >= 0; j--){ // Print from top to bottom
            System.out.print(j+"|");
            for (int i = 0; i < size; i++){
                //Empty Tile
                if (locations[i][j] == null) System.out.print("_|");
                //Dark Piece
                else if (locations[i][j].getTeam() == 0){
                   if (!locations[i][j].isKing()) {System.out.print("x|");}
                   else {System.out.print("X|");}
                }
                //Light Piece
                else if (locations[i][j].getTeam() == 1){
                  if (!locations[i][j].isKing()) {System.out.print("o|");}
                  else {System.out.print("O|");}
                }
            }
            System.out.print("\n");
        }
        System.out.println("  0 1 2 3 4 5 6 7 ");
    }
    /*
    public String locationsToJSON(){
        // TODO: Convert board array representation to JSON string

    }
    */

    /*
    public Piece [][] locationsFromJSON(String jstring){
        // TODO: Convert JSON string to Board array representation
    }
    */

    public static void main(String [] args){
        Board b = new Board(0);
        b.printBoard();
    }
}
