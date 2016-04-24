package cs474final.gametest;

import cs474final.game.Piece;
import org.junit.*;
import static org.junit.Assert.*;
public class PieceTest{
    Piece p1, p2, p3, p4, p5;

    @Before
    void setUp(){
        p1 = new Piece(0, 3);
        p2 = new Piece(0, 3);
        p3 = new Piece(0, 3);
        p3.kingMe();
        p4 = new Piece(0, 4);
        p5 = new Piece(1, 3);
    }

    @After
    void tearDown(){
        p1 = null;
        p2 = null;
        p3 = null;
        p4 = null;
    }

    @Test
    void testKing(){
        assertTrue(p1.isKing() == false);
        assertTrue(p3.isKing() == true);
    }

    @Test
    void testGetters(){
        assertTrue(p1.getTeam() == 0);
        assertTrue(p4.getPieceID() == 4);
    }

    @Test
    void testEquals(){
        assertTrue(p1.equals(p2));  //functionally identical
        assertFalse(p1.equals(p3)); //differ by king status
        assertFalse(p1.equals(p4)); //differ by pieceID
        assertFalse(p2.equals(p5)); //differ by team
        assertFalse(p4.equals(p5)); //differ by pieceID and team
    }


}
