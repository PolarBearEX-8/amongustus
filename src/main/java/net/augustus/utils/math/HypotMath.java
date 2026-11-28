package net.augustus.utils.math;

import net.augustus.modules.ModuleMode;

public final class HypotMath {
   private static final double OVERFLOW_THRESHOLD = Double.longBitsToDouble(6633802251116740608L);
   private static final double SCALE_DOWN = Double.longBitsToDouble(1229482698272145408L);
   private static final double UNDERFLOW_THRESHOLD = Double.longBitsToDouble(2580562586483294208L);
   private static final double SCALE_UP = Double.longBitsToDouble(7984882139327889408L);

   public static double hypot(double d, double d2) {
      d = Math.abs(d);
      d2 = Math.abs(d2);
      if (d2 < d) {
         double d3 = d;
         d = d2;
         d2 = d3;
      } else if (!(d2 >= d)) {
         if (d == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
         }

         return Double.NaN;
      }

      if (d2 - d == d2) {
         return d2;
      } else {
         double d4;
         if (d > OVERFLOW_THRESHOLD) {
            d *= SCALE_DOWN;
            d2 *= SCALE_DOWN;
            d4 = SCALE_UP;
         } else if (d2 < UNDERFLOW_THRESHOLD) {
            d *= SCALE_UP;
            d2 *= SCALE_UP;
            d4 = SCALE_DOWN;
         } else {
            d4 = 1.0;
         }

         return d4 * Math.sqrt(d * d + d2 * d2);
      }
   }

   public static double hypotExact(double d, double d2) {
      if (Double.isInfinite(d) || Double.isInfinite(d2)) {
         return Double.POSITIVE_INFINITY;
      } else if (!Double.isNaN(d) && !Double.isNaN(d2)) {
         d = Math.abs(d);
         d2 = Math.abs(d2);
         if (d < d2) {
            double d3 = d;
            d = d2;
            d2 = d3;
         }

         int exponent = Math.getExponent(d);
         if (exponent > Math.getExponent(d2) + 27) {
            return d;
         } else {
            int n = 0;
            if (exponent > 510 || exponent < -511) {
               n = exponent;
               d = Math.scalb(d, -exponent);
               d2 = Math.scalb(d2, -exponent);
            }

            double d4 = 0.0;
            if (d > 2.0 * d2) {
               double d5 = Double.longBitsToDouble(Double.doubleToLongBits(d) & -4294967296L);
               double d6 = d - d5;
               d4 = Math.sqrt(d5 * d5 + d2 * d2 + d6 * (d + d5));
            } else {
               double d7 = 2.0 * d;
               double d8 = Double.longBitsToDouble(Double.doubleToLongBits(d7) & -4294967296L);
               double d9 = d7 - d8;
               double d10 = Double.longBitsToDouble(Double.doubleToLongBits(d2) & -4294967296L);
               double d11 = d2 - d10;
               double d12 = d - d2;
               d4 = Math.sqrt(d8 * d10 + d12 * d12 + d8 * d11 + d9 * d2);
            }

            return n == 0 ? d4 : Math.scalb(d4, n);
         }
      } else {
         return Double.NaN;
      }
   }
}
