package cs474final.gametest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
   public static void main(String[] args) {
      System.out.println("Piece Unit Tests");
      Result result = JUnitCore.runClasses(cs474final.gametest.PieceTest.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println("Piece tests passed: "+result.wasSuccessful());

      System.out.println("Board Unit Tests");
      result = JUnitCore.runClasses(cs474final.gametest.BoardTest.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println("Board tests passed: "+result.wasSuccessful());
   }
}
