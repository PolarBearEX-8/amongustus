package client.util;

public class InterpolationRanker {
   public static double computeValue(double offset) {
      return offset * 20.0;
   }

   public static double computeValueByDouble(double offset) {
      return offset / 20.0;
   }
}
