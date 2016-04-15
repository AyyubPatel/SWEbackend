package cs471final.gametest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(CustomerTest.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println("Customer tests passed: "+result.wasSuccessful());
      
      result = JUnitCore.runClasses(ItemTest.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println("Item tests passed: "+result.wasSuccessful());
      
      result = JUnitCore.runClasses(ProductsTest.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println("Products tests passed: "+result.wasSuccessful());
      
      result = JUnitCore.runClasses(ShoppingCartTest.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println("ShoppingCart tests passed: "+result.wasSuccessful());
      
   }
}
