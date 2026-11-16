package client.util;

public class BezierSelector {
   public static int computeCount(int count) {
      return (int)(500.0 * Math.pow(1.15, (double)(Math.max(1, count / 5) - 1)));
   }
}
