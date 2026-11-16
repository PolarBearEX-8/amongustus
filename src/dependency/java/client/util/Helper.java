package client.util;

public class Helper {
   public static final float[] factorArray = new float[65536];

   public static float computeFactor(float factor) {
      return factorArray[(int)(factor * 10430.378F + 16384.0F) & 65535];
   }

   static {
      for (int index = 0; index < factorArray.length; index++) {
         factorArray[index] = (float)StrictMath.sin((double)index * Math.PI * 2.0 / 65536.0);
      }
   }

   public static float computeFactorByFloat(float factor) {
      return factorArray[(int)(factor * 10430.378F) & 65535];
   }
}
