package net.minecraft.util;

import java.util.Random;

public class RandomHelper {
   public static double value = 0.0;
   public static final double value2 = 0.1;
   public static final double value3 = 0.1;
   public static double value4 = 0.0;
   public static double value5 = 0.0;
   public static double value6 = 0.0;
   public static double value7 = 0.0;
   public static final double value8 = 0.1;
   public static double value9 = 0.0;
   public static final double value10 = 60.0;
   public static final double value11 = 30.0;
   public static final Random random = new Random();

   private static double computeValue(double posX, double posY, double posZ) {
      return Math.max(posY, Math.min(posZ, posX));
   }

   private static double computeValue(double offset) {
      double offset2 = offset % 360.0;
      if (offset2 > 180.0) {
         offset2 -= 360.0;
      } else if (offset2 < -180.0) {
         offset2 += 360.0;
      }

      return offset2;
   }

   public static double[] resolveDoubleArray(double posX, double posX2, double posX3, double posY, double posZ, AxisAlignedBB boundingBox) {
      double offset = value7 + value4 + (random.nextDouble() - 0.5) * 2.0 * 0.1;
      double offset2 = value + value5 + (random.nextDouble() - 0.5) * 2.0 * 0.1;
      double offset3 = value6 + value9 + (random.nextDouble() - 0.5) * 2.0 * 0.1;
      if (random.nextDouble() >= 0.1) {
         offset = computeValue(offset, boundingBox.minX, boundingBox.maxX);
         offset2 = computeValue(offset2, boundingBox.minY, boundingBox.maxY);
         offset3 = computeValue(offset3, boundingBox.minZ, boundingBox.maxZ);
      }

      double offset4 = offset - posX;
      double offset5 = offset2 - posX2;
      double offset6 = offset3 - posX3;
      double offset7 = Math.toDegrees(Math.atan2(offset6, offset4)) - 90.0;
      double offset8 = -Math.toDegrees(Math.atan2(offset5, Math.sqrt(offset4 * offset4 + offset6 * offset6)));
      double[] offsetArray = resolveDoubleArray(posY, posZ, offset7, offset8);
      double offset9 = Math.sqrt(offsetArray[0] * offsetArray[0] + offsetArray[1] * offsetArray[1]);
      if (offset9 > 60.0) {
         double offset10 = 60.0 / offset9;
         offsetArray[0] *= offset10;
         offsetArray[1] *= offset10;
      }

      value4 *= 0.9;
      value5 *= 0.9;
      value9 *= 0.9;
      value7 = offset;
      value = offset2;
      value6 = offset3;
      return offsetArray;
   }

   public static double[] resolveDoubleArray(double posX, double posX2, double posY, double posZ) {
      double offset = computeValue(posY - posX);
      double offset2 = computeValue(posZ - posX2);
      double offset3 = computeValue(offset, -30.0, 30.0);
      double offset4 = computeValue(offset2, -30.0, 30.0);
      double offset5 = offset3 + (random.nextDouble() - 0.5) * 2.0 * 0.1;
      double offset6 = offset4 + (random.nextDouble() - 0.5) * 2.0 * 0.1;
      return new double[]{offset5, offset6};
   }
}
