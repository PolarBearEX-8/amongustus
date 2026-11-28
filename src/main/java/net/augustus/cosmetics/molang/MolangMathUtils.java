package net.augustus.cosmetics.molang;

public class MolangMathUtils {
   public static float wrapDegreesFloat(double d) {
      return 0.0F;
   }

   public static double wrapDegrees(double d) {
      d %= 360.0;
      if (d >= 180.0) {
         d -= 360.0;
      }

      if (d < -180.0) {
         d += 360.0;
      }

      return d;
   }
}
