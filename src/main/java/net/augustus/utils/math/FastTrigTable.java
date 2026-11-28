package net.augustus.utils.math;

public class FastTrigTable {
   private static final float[] SIN_TABLE = new float[65536];

   public static float sin(float f) {
      return SIN_TABLE[(int)(f * 10430.378F) & 65535];
   }

   static {
      for (int i = 0; i < SIN_TABLE.length; i++) {
         SIN_TABLE[i] = (float)StrictMath.sin((double)i * Math.PI * 2.0 / 65536.0);
      }
   }

   public static float cos(float f) {
      return SIN_TABLE[(int)(f * 10430.378F + 16384.0F) & 65535];
   }
}
