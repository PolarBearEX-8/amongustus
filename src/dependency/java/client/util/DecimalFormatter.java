package client.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import net.minecraft.util.Vec3;

public final class DecimalFormatter {
   public static double computeValue(double posX, double posX2, double posX3, double posY, double posZ) {
      return (posX - posX2) / (posX3 - posX2) * (posZ - posY) + posY;
   }

   public static double abs(double offset, double offset2) {
      return Math.abs(offset - offset2);
   }

   public static double sqrt(double posX, double posY, double posZ) {
      return Math.sqrt(posX * posX + posY * posY + posZ * posZ);
   }

   public static String formatDecimal(double offset, int count) {
      if (Double.isNaN(offset)) {
         return "NaN";
      } else {
         return Double.isInfinite(offset) ? "Infinite" : new BigDecimal(offset).setScale(count, RoundingMode.HALF_UP).toPlainString();
      }
   }

   public static int computeCount(int count, int count2, int count3) {
      return Math.max(count, Math.min(count2, count3));
   }

   public static double computeValue(double posX, double posX2, double posY, double posZ) {
      return HelperManager.computeValueByDouble(posX - posY, posX2 - posZ);
   }

   public static double computeValueByList(List list) {
      double offset = 1000.0;

      for (Number number : (Iterable<Number>)(Iterable<?>)list) {
         offset = Math.min(offset, number.doubleValue());
      }

      return offset;
   }

   public static float abs(float factor, float factor2) {
      float factor3 = Math.abs(factor2 - factor) % 360.0F;
      return factor3 > 180.0F ? 360.0F - factor3 : factor3;
   }

   public static long computeTimestamp(long value, long value2, long value3) {
      return Math.max(value, Math.min(value2, value3));
   }

   public static float computeFactor(float factor, float factor2) {
      float factor3 = factor - factor2 % 360.0F;
      if (factor3 > 180.0F) {
         factor3 = 360.0F - factor3;
      }

      if (factor3 < -180.0F) {
         factor3 += 360.0F;
      }

      return factor3;
   }

   public static double computeValue(List list) {
      double offset = 0.0;

      for (Number number : (Iterable<Number>)(Iterable<?>)list) {
         offset = Math.max(offset, number.doubleValue());
      }

      return offset;
   }

   public static double computeValue(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      return sqrt(posX - posX4, posX2 - posY, posX3 - posZ);
   }

   public static String resolveText(double posX, double posY, double posZ) {
      return formatDecimal(posX, 3) + ", " + formatDecimal(posY, 4) + ", " + formatDecimal(posZ, 3);
   }

   public static double computeValue(double posX, double posY, double posZ) {
      return Math.max(posX, Math.min(posY, posZ));
   }

   public static String resolveText(Vec3 vec) {
      return resolveText(vec.xCoord, vec.yCoord, vec.zCoord);
   }

   public static String resolveTextByDouble(double posX, double posY, double posZ) {
      return (int)posX + "," + (int)posY + "," + (int)posZ;
   }
}
