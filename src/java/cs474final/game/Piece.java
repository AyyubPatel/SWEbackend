package cs474final.game;
/*
 * Game Piece class. Pieces are passive objects, in that they don't actually do anything to 
 * other piece objects. The game evaluates and controls interactions between pieces
*/

public class Piece{
    boolean king;      //initialized to false, this value will be set by the game when it recognizes
                       //that the required conditions are met
    int [] location;   //location will be an array of two values between 0 and 7
    private int team;  //team will be 0 or 1

    public Piece(int [] location, final int team){
        this.king = false;
        this.location = location;
        this.team = team;
    }

    public void kingMe(){
        this.king = true; //Pieces can only be kinged in one direction, don't need a reverse king method
    }

    public void setLocation(int [] newLocation){
        location[0] = newLocation[0]; //legal locations are handled by the game 
        location[1] = newLocation[1]; //itself, not the pieces here
    }
    
    public int [] getLocation(){
        return location;
    }

    public int getTeam(){
        return team; //returns information about the team
    } 
}
