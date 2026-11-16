package client.util;

public class RotationRanker {
   public static int computeCount(int count, int count2) {
      int count3 = count % count2;
      if (count3 < 0) {
         count3 += count2;
      }

      return count3;
   }

   public static float computeFactor(float posX, float posY, float posZ) {
      return posX < posY ? posY : (posX > posZ ? posZ : posX);
   }
}
