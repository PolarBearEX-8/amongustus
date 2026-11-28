package net.augustus.cosmetics;

public class CosmeticMathHelper {
   private static final float[] SIN_TABLE = new float[4096];

   public static int floorDouble(double d) {
      int n = (int)d;
      return d < (double)n ? n - 1 : n;
   }

   static {

      for (int i = 0; i < 4096; i++) {
         SIN_TABLE[i] = (float)Math.sin((double)i * Math.PI * 2.0 / 4096.0);
      }
   }

   public static int absInt(int n) {
      return n >= 0 ? n : -n;
   }

   public static int clampInt(int n, int n2, int n3) {
      return n < n2 ? n2 : (n > n3 ? n3 : n);
   }

   public static float clampFloat(float f, float f2, float f3) {
      return f < f2 ? f2 : (f > f3 ? f3 : f);
   }

   public static float sin(float f) {
      return SIN_TABLE[(int)(f * 651.8986F) & 4095];
   }

   public static float cos(float f) {
      return SIN_TABLE[(int)(f * 651.8986F + 1024.0F) & 4095];
   }



}
