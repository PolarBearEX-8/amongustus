package client.util;

public class InternalFloatArrayEntry {
   public static final float[] factorArray = new float[4096];

   public static int computeCount(int count) {
      return count >= 0 ? count : -count;
   }

   public static float computeFactor(float factor) {
      return factorArray[(int)(factor * 651.8986F + 1024.0F) & 4095];
   }

   public static float computeFactor(float posX, float posY, float posZ) {
      return posX < posY ? posY : (posX > posZ ? posZ : posX);
   }

   public static int computeCount(double offset) {
      int count = (int)offset;
      return offset < (double)count ? count - 1 : count;
   }

   public static float computeFactorByFloat(float factor) {
      return factorArray[(int)(factor * 651.8986F) & 4095];
   }

   public static int computeCount(int count, int count2, int count3) {
      return count < count2 ? count2 : (count > count3 ? count3 : count);
   }

   static {
      for (int index = 0; index < 4096; index++) {
         factorArray[index] = (float)Math.sin((double)index * Math.PI * 2.0 / 4096.0);
      }
   }
}
