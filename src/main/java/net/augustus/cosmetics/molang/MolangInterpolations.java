package net.augustus.cosmetics.molang;

public class MolangInterpolations {

   public static double lerp(double start, double end, double progress) {
      return start + (end - start) * progress;
   }

   public static double lerpYaw(double d, double d2, double d3) {
      d = MolangMathUtils.wrapDegrees(d);
      d2 = MolangMathUtils.wrapDegrees(d2);
      return lerp(d, normalizeYaw(d, d2), d3);
   }

   public static double normalizeYaw(double d, double d2) {
      double d3 = d - d2;
      if (!(d3 > 180.0) && !(d3 < -180.0)) {
         return d2;
      } else {
         d3 = Math.copySign(360.0 - Math.abs(d3), d3);
         return d + d3;
      }
   }

   public static float lerp(float start, float end, float progress) {
      return start + (end - start) * progress;
   }



}
