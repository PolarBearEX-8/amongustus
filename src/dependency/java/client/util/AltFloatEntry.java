package client.util;

import net.minecraft.util.MathHelper;

public class AltFloatEntry {
   public static final float factor3 = (float) Math.PI;
   public static final float factor2 = (float) (Math.PI * 2);
   public static final float factor = (float) (Math.PI / 2);
   public static final float[] factorArray = new float[65536];

   public static boolean abs(float posX, float posY, float posZ) {
      return Math.abs(posX - posY) <= posZ;
   }

   public static float computeFactor2(float factor4) {
      return (float) (Math.PI / 2) - factorArray[(int)((double)(factor4 + 1.0F) * 32767.5) & 65535];
   }

   public static int computeCount(int[] countArray) {
      return countArray.length <= 0 ? 0 : computeCountByIndexArray(countArray) / countArray.length;
   }

   public static float computeFactor(float factor4) {
      return factor4 * 180.0F / (float) Math.PI;
   }

   public static int computeCount(int count) {
      int count2 = MathHelper.processAction(count);
      return count == count2 ? count2 : count2 / 2;
   }

   public static int computeCountByIndexArray(int[] countArray) {
      if (countArray.length <= 0) {
         return 0;
      } else {
         int count = 0;

         for (int index = 0; index < countArray.length; index++) {
            int count2 = countArray[index];
            count += count2;
         }

         return count;
      }
   }

   public static float round(double offset) {
      return (float)((double)Math.round(offset * 1.0E8) / 1.0E8);
   }

   public static float computeFactor3(float factor4) {
      return factor4 / 180.0F * (float) Math.PI;
   }

   public static float computeFactorByFloat(float factor4) {
      return factorArray[(int)((double)(factor4 + 1.0F) * 32767.5) & 65535];
   }

   static {
      for (int index = 0; index < 65536; index++) {
         factorArray[index] = (float)Math.asin((double)index / 32767.5 - 1.0);
      }

      for (int index2 = -1; index2 < 2; index2++) {
         factorArray[(int)(((double)index2 + 1.0) * 32767.5) & 65535] = (float)Math.asin((double)index2);
      }
   }
}
