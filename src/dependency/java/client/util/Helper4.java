package client.util;

public class Helper4 {
   public static float computeFactor(float posX, float posY, float posZ) {
      return posX < posY ? posY : (posX > posZ ? posZ : posX);
   }

   public static int computeCount(int count, int count2, int count3) {
      return count < count2 ? count2 : (count > count3 ? count3 : count);
   }

   public static double computeValue(double posX, double posY, double posZ) {
      return posX < posY ? posY : (posX > posZ ? posZ : posX);
   }
}
