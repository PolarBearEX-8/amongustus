package client.util;

public class Helper3 {
   public static double computeValue(double offset, double offset2) {
      double offset3 = offset - offset2;
      if (!(offset3 > 180.0) && !(offset3 < -180.0)) {
         return offset2;
      } else {
         double offset4 = Math.copySign(360.0 - Math.abs(offset3), offset3);
         return offset + offset4;
      }
   }

   public static double computeValue(double posX, double posY, double posZ) {
      return posX + (posY - posX) * posZ;
   }

   public static float computeFactor(float posX, float posY, float posZ) {
      return posX + (posY - posX) * posZ;
   }

   public static double computeValueByDouble(double posX, double posY, double posZ) {
      double offset = HelperHandler.computeValue(posX);
      double offset2 = HelperHandler.computeValue(posY);
      return computeValue(offset, computeValue(offset, offset2), posZ);
   }
}
