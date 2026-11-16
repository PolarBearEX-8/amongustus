package client.util;

public final class HelperManager {
   public static final double value = Double.longBitsToDouble(0x1110000000000000L);
   public static final double value2 = Double.longBitsToDouble(0x5c10000000000000L);
   public static final double value3 = Double.longBitsToDouble(0x6ed0000000000000L);
   public static final double value4 = Double.longBitsToDouble(0x23d0000000000000L);

   public static double computeValueByDouble(double offset, double offset2) {
      double offset3 = Math.abs(offset);
      double offset4 = Math.abs(offset2);
      if (offset4 < offset3) {
         double offset5 = offset3;
         offset3 = offset4;
         offset4 = offset5;
      } else if (!(offset4 >= offset3)) {
         if (offset3 == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
         }

         return Double.NaN;
      }

      if (offset4 - offset3 == offset4) {
         return offset4;
      } else {
         double offset6;
         if (offset3 > value2) {
            offset3 *= value;
            offset4 *= value;
            offset6 = value3;
         } else if (offset4 < value4) {
            offset3 *= value3;
            offset4 *= value3;
            offset6 = value;
         } else {
            offset6 = 1.0;
         }

         return offset6 * Math.sqrt(offset3 * offset3 + offset4 * offset4);
      }
   }

   public static double computeValue(double offset, double offset2) {
      if (Double.isInfinite(offset) || Double.isInfinite(offset2)) {
         return Double.POSITIVE_INFINITY;
      } else if (!Double.isNaN(offset) && !Double.isNaN(offset2)) {
         double offset3 = Math.abs(offset);
         double offset4 = Math.abs(offset2);
         if (offset3 < offset4) {
            double offset5 = offset3;
            offset3 = offset4;
            offset4 = offset5;
         }

         int exponent = Math.getExponent(offset3);
         int exponent2 = Math.getExponent(offset4);
         if (exponent > exponent2 + 27) {
            return offset3;
         } else {
            int count = 0;
            if (exponent > 510 || exponent < -511) {
               count = exponent;
               offset3 = Math.scalb(offset3, -exponent);
               offset4 = Math.scalb(offset4, -exponent);
            }

            double offset6;
            if (offset3 > 2.0 * offset4) {
               double offset7 = Double.longBitsToDouble(Double.doubleToLongBits(offset3) & -4294967296L);
               double offset8 = offset3 - offset7;
               offset6 = Math.sqrt(offset7 * offset7 + offset4 * offset4 + offset8 * (offset3 + offset7));
            } else {
               double offset9 = 2.0 * offset3;
               double offset10 = Double.longBitsToDouble(Double.doubleToLongBits(offset9) & -4294967296L);
               double offset11 = offset9 - offset10;
               double offset12 = Double.longBitsToDouble(Double.doubleToLongBits(offset4) & -4294967296L);
               double offset13 = offset4 - offset12;
               double offset14 = offset3 - offset4;
               offset6 = Math.sqrt(offset10 * offset12 + offset14 * offset14 + offset10 * offset13 + offset11 * offset4);
            }

            return count == 0 ? offset6 : Math.scalb(offset6, count);
         }
      } else {
         return Double.NaN;
      }
   }
}
