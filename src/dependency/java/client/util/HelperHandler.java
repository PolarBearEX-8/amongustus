package client.util;

public class HelperHandler {
   public static float computeFactor(double offset) {
      return 0.0F;
   }

   public static double computeValue(double offset) {
      double offset2 = offset % 360.0;
      if (offset2 >= 180.0) {
         offset2 -= 360.0;
      }

      if (offset2 < -180.0) {
         offset2 += 360.0;
      }

      return offset2;
   }
}
