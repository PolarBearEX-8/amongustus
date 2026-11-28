package net.augustus.utils.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import net.augustus.modules.ModuleMode;
import net.minecraft.util.Vec3;

public final class NumberFormatUtil {

   public static long clamp(long value, long min, long max) {
      return Math.max(value, Math.min(min, max));
   }

   public static double distance2D(double d, double d2, double d3, double d4) {
      return HypotMath.hypot(d - d3, d2 - d4);
   }

   public static float angleDistance(float f, float f2) {
      float f3 = Math.abs(f2 - f) % 360.0F;
      return f3 > 180.0F ? 360.0F - f3 : f3;
   }

   public static String formatIntCoords(double d, double d2, double d3) {
      return (int)d + "," + (int)d2 + "," + (int)d3;
   }

   public static double maxValue(List list) {
      double d = 0.0;

      for (Number number : (Iterable<Number>)(list)) {
         d = Math.max(d, number.doubleValue());
      }

      return d;
   }

   public static String formatVec3(Vec3 vec) {
      return formatCoords(vec.xCoord, vec.yCoord, vec.zCoord);
   }

   public static double absDiff(double d, double d2) {
      return Math.abs(d - d2);
   }

   public static String formatDecimal(double d, int n) {
      if (Double.isNaN(d)) {
         return "NaN";
      } else {
         return Double.isInfinite(d) ? "Infinite" : new BigDecimal(d).setScale(n, RoundingMode.HALF_UP).toPlainString();
      }
   }

   public static double clamp(double value, double min, double max) {
      return Math.max(value, Math.min(min, max));
   }

   public static double minValue(List list) {
      double d = 1000.0;

      for (Number number : (Iterable<Number>)(list)) {
         d = Math.min(d, number.doubleValue());
      }

      return d;
   }

   public static double remapRange(double d, double d2, double d3, double d4, double d5) {
      return (d - d2) / (d3 - d2) * (d5 - d4) + d4;
   }

   public static String formatCoords(double d, double d2, double d3) {
      return formatDecimal(d, 3) + ", " + formatDecimal(d2, 4) + ", " + formatDecimal(d3, 3);
   }

   public static float signedAngleDiff(float f, float f2, int n, byte b, int n2) {
      float f3 = f - f2 % 360.0F;
      if (f3 > 180.0F) {
         f3 = 360.0F - f3;
      }

      if (f3 < -180.0F) {
         f3 += 360.0F;
      }

      return f3;
   }

   public static int clamp(int value, int min, int max) {
      return Math.max(value, Math.min(min, max));
   }

   public static double distance3D(double d, double d2, double d3, double d4, double d5, double d6) {
      return length3D(d - d4, d2 - d5, d3 - d6);
   }

   public static double length3D(double d, double d2, double d3) {
      return Math.sqrt(d * d + d2 * d2 + d3 * d3);
   }

}
