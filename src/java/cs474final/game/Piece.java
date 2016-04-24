package cs474final.game;

import java.util.Objects;
//import cs474final.game.Move;

/*
 * Game Piece class. Pieces are passive objects, in that they don't actually do anything to
 * other piece objects. The game evaluates and controls interactions between pieces
 */

public class Piece{
    private boolean king; //initialized to false, this value will be set by the
                          //game when it recognizes that the required conditions are met
    private int team;     //team will be 0 or 1, corresponds to dark or light respectively
    final int pieceID;
//    private Move moves;

    public Piece(final int team, final int pieceID){
        this.king = false;
        this.team = team;
        this.pieceID = pieceID;
    }

    public void kingMe(){
        this.king = true; //Pieces can only be kinged in one direction, don't need a reverse king method
    }
    public boolean isKing(){
        return king;
    }

    public int getTeam(){
        return team; //returns information about the team
    }

    public int getPieceID(){
        return pieceID;
    }
    /*
     * Piece equality is contingent on team and ID equality,
     * and king status (we will use this information in
     * updating the pieces from board state to board state)
     */
    public boolean equals(Object o){
        return (o != null) &&
               (o instanceof Piece) &&
               (this.getTeam() == ((Piece) o).getTeam()) &&
               (this.getPieceID() == ((Piece) o).getPieceID()) &&
               (this.isKing() == ((Piece) o).isKing());
    }

    public int hashCode() {
        return Objects.hash(this.getPieceID(),
                            this.getTeam(),
                            this.isKing());
    }
}
