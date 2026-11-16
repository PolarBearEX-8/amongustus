package net.minecraft.util;

import client.Client;
import client.util.AltFloatEntry;
import java.util.Random;
import java.util.UUID;

public class MathHelper {
   public static final float scaleFactor18 = sin(2.0F);
   public static final int indexOffset2 = 12;
   public static final int indexOffset15 = 4095;
   public static final int indexOffset3 = 4096;
   public static final float scaleFactor16 = (float) Math.PI;
   public static final float scaleFactor19 = (float) (Math.PI * 2);
   public static final float scaleFactor4 = (float) (Math.PI / 2);
   public static final float scaleFactor5 = (float) (Math.PI * 2);
   public static final float scaleFactor17 = 360.0F;
   public static final float scaleFactor1 = 651.8986F;
   public static final float scaleFactor11 = 11.377778F;
   public static final float scaleFactor9 = (float) (Math.PI / 180.0);
   public static final float[] scaleFactorArray8 = new float[4096];
   public static final float[] scaleFactorArray = new float[4096];
   public static boolean state = false;
   public static final int indexOffset = 1024;
   public static final float scaleFactor13 = AltFloatEntry.round(Math.PI);
   public static final float scaleFactor12 = AltFloatEntry.round(Math.PI * 2);
   public static final float scaleFactor7 = AltFloatEntry.round(Math.PI / 2);
   public static final float scaleFactor = AltFloatEntry.round(651.8986469044033);
   public static final float scaleFactor14 = AltFloatEntry.round(Math.PI / 180.0);
   public static final float[] scaleFactorArray10 = new float[65536];
   public static final int[] intValueArray;
   public static final double coordOffset;
   public static final double[] coordOffsetArray6;
   public static final double[] coordOffsetArray;

   public static int processAction(int count) {
      int count2 = count - 1;
      int count3 = count2 | count2 >> 1;
      int count4 = count3 | count3 >> 2;
      int count5 = count4 | count4 >> 4;
      int count6 = count5 | count5 >> 8;
      return (count6 | count6 >> 16) + 1;
   }

   public static double wrapAngleTo180_double(double offset) {
      return offset - Math.floor(offset);
   }

   public static float clamp_float(float posX, float posY, float posZ) {
      return posX < posY ? posY : Math.min(posX, posZ);
   }

   public static int computeCountByIndex(int count, int count2) {
      return count < 0 ? -((-count - 1) / count2) - 1 : count / count2;
   }

   public static int floor_float(float factor) {
      int count = (int)factor;
      return factor < (float)count ? count - 1 : count;
   }

   public static double abs_max(double offset) {
      double offset2 = 0.5 * offset;
      long value = Double.doubleToRawLongBits(offset);
      double offset3 = Double.longBitsToDouble(0x5fe6eb50c7b537aaL - (value >> 1));
      return offset3 * (1.5 - offset2 * offset3 * offset3);
   }

   public static int computeCount(int count) {
      return count >= 0 ? count : -count;
   }

   public static double computeValue(long[] valueArray) {
      long value = 0L;

      for (long value2 : valueArray) {
         value += value2;
      }

      return (double)value / (double)valueArray.length;
   }

   public static float sqrt_double(double offset) {
      return (float)Math.sqrt(offset);
   }

   public static float sin(float factor) {
      return (float)Math.sqrt((double)factor);
   }

   public static int computeCount(int count, int count2) {
      int count3 = count2;
      if (count2 == 0) {
         return 0;
      } else if (count == 0) {
         return count2;
      } else {
         if (count < 0) {
            count3 = count2 * -1;
         }

         int count4 = count % count3;
         return count4 == 0 ? count : count + count3 - count4;
      }
   }

   public static int truncateDoubleToInt(double offset) {
      int count = (int)offset;
      return offset > (double)count ? count + 1 : count;
   }

   public static long processAction(int count, int count2, int count3) {
      long keySalt = (long)(count * 3129871) ^ (long)count3 * 116129781L ^ (long)count2;
      return keySalt * keySalt * 42317861L + keySalt * 11L;
   }

   public static UUID processAction(Random random) {
      long value = random.nextLong() & -61441L | 16384L;
      long value2 = random.nextLong() & 0x3fffffffffffffffL | Long.MIN_VALUE;
      return new UUID(value, value2);
   }

   public static int getIndex(String text, int count) {
      try {
         return Integer.parseInt(text);
      } catch (Throwable throwable) {
         return count;
      }
   }

   public static float sqrt_float(float factor) {
      float factor2 = factor % 360.0F;
      if (factor2 >= 180.0F) {
         factor2 -= 360.0F;
      }

      if (factor2 < -180.0F) {
         factor2 += 360.0F;
      }

      return factor2;
   }

   public static double computeValue(double offset, double offset2) {
      double offset3 = offset;
      double offset4 = offset2;
      if (offset < 0.0) {
         offset3 = -offset;
      }

      if (offset2 < 0.0) {
         offset4 = -offset2;
      }

      return offset3 > offset4 ? offset3 : offset4;
   }

   public static int processAction(int count, int count2) {
      int count3 = (count & 0xFF0000) >> 16;
      int count4 = (count2 & 0xFF0000) >> 16;
      int count5 = (count & 0xFF00) >> 8;
      int count6 = (count2 & 0xFF00) >> 8;
      int count7 = (count & 0xFF) >> 0;
      int count8 = (count2 & 0xFF) >> 0;
      int count9 = (int)((float)count3 * (float)count4 / 255.0F);
      int count10 = (int)((float)count5 * (float)count6 / 255.0F);
      int count11 = (int)((float)count7 * (float)count8 / 255.0F);
      return count & 0xFF000000 | count9 << 16 | count10 << 8 | count11;
   }

   public static float cos(float factor) {
      return state
         ? (
            Client.getClient().getModuleManager().fixes.canExecute10()
               ? scaleFactorArray[(int)(factor * scaleFactor + 1024.0F) & 4095]
               : scaleFactorArray8[(int)((factor + (float) (Math.PI / 2)) * 651.8986F) & 4095]
         )
         : scaleFactorArray10[(int)(factor * 10430.378F + 16384.0F) & 65535];
   }

   public static int computeCount(double offset) {
      return (int)(offset >= 0.0 ? offset : -offset + 1.0);
   }

   public static long computeTimestamp(double offset) {
      long value = (long)offset;
      return offset < (double)value ? value - 1L : value;
   }

   public static float computeFactor(float factor) {
      return factor >= 0.0F ? factor : -factor;
   }

   public static double getDouble4(double offset) {
      double offset2 = offset % 360.0;
      if (offset2 >= 180.0) {
         offset2 -= 360.0;
      }

      if (offset2 < -180.0) {
         offset2 += 360.0;
      }

      return offset2;
   }

   public static double processAction(String text, double offset, double offset2) {
      return Math.max(offset2, getCoordinate(text, offset));
   }

   public static int processAction(String text, int count, int count2) {
      return Math.max(count2, getIndex(text, count));
   }

   public static int processAction(float posX, float posY, float posZ) {
      return clamp_int(floor_float(posX * 255.0F), floor_float(posY * 255.0F), floor_float(posZ * 255.0F));
   }

   public static long processAction(Vec3i vec) {
      return processAction(vec.getX(), vec.getY(), vec.getZ());
   }

   public static int processAction2(int count, int count2) {
      return (count % count2 + count2) % count2;
   }

   private static int computeCount2(int count) {
      int count2 = checkIndex(count) ? count : processAction(count);
      return intValueArray[(int)((long)count2 * 125613361L >> 27) & 31];
   }

   public static int getInt12(int count, int count2, int count3) {
      return count < count2 ? count2 : (count > count3 ? count3 : count);
   }

   public static double computeValue(double posX, double posY, double posZ) {
      return posZ < 0.0 ? posX : (posZ > 1.0 ? posY : posX + (posY - posX) * posZ);
   }

   public static int computeCount(float posX, float posY, float posZ) {
      int count = (int)(posX * 6.0F) % 6;
      float factor = posX * 6.0F - (float)count;
      float factor2 = posZ * (1.0F - posY);
      float factor3 = posZ * (1.0F - factor * posY);
      float factor4 = posZ * (1.0F - (1.0F - factor) * posY);
      float factor5;
      float factor6;
      switch (count) {
         case 0:
            factor5 = posZ;
            factor6 = factor4;
            break;
         case 1:
            factor5 = factor3;
            factor6 = posZ;
            break;
         case 2:
            factor5 = factor2;
            factor6 = posZ;
            factor2 = factor4;
            break;
         case 3:
            factor5 = factor2;
            factor6 = factor3;
            factor2 = posZ;
            break;
         case 4:
            factor5 = factor4;
            factor6 = factor2;
            factor2 = posZ;
            break;
         case 5:
            factor5 = posZ;
            factor6 = factor2;
            factor2 = factor3;
            break;
         default:
            throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + posX + ", " + posY + ", " + posZ);
      }

      int intItem = getInt12((int)(factor5 * 255.0F), 0, 255);
      int intItem2 = getInt12((int)(factor6 * 255.0F), 0, 255);
      int intItem3 = getInt12((int)(factor2 * 255.0F), 0, 255);
      return intItem << 16 | intItem2 << 8 | intItem3;
   }

   public static double getDouble6(double offset, double offset2) {
      double offset3 = offset;
      double offset4 = offset2;
      double offset5 = offset2 * offset2 + offset * offset;
      if (Double.isNaN(offset5)) {
         return Double.NaN;
      } else {
         boolean flag = offset < 0.0;
         if (flag) {
            offset3 = -offset;
         }

         boolean flag2 = offset2 < 0.0;
         if (flag2) {
            offset4 = -offset2;
         }

         boolean flag3 = offset3 > offset4;
         if (flag3) {
            double offset6 = offset4;
            offset4 = offset3;
            offset3 = offset6;
         }

         double offset7 = abs_max(offset5);
         offset4 *= offset7;
         offset3 *= offset7;
         double offset8 = coordOffset + offset3;
         int count = (int)Double.doubleToRawLongBits(offset8);
         double offset9 = coordOffsetArray6[count];
         double offset10 = coordOffsetArray[count];
         double offset11 = offset8 - coordOffset;
         double offset12 = offset3 * offset10 - offset4 * offset11;
         double offset13 = (6.0 + offset12 * offset12) * offset12 * 0.16666666666666666;
         double offset14 = offset9 + offset13;
         if (flag3) {
            offset14 = (Math.PI / 2) - offset14;
         }

         if (flag2) {
            offset14 = Math.PI - offset14;
         }

         if (flag) {
            offset14 = -offset14;
         }

         return offset14;
      }
   }

   public static double getDouble7(double posX, double posY, double posZ) {
      return posX < posY ? posY : Math.min(posX, posZ);
   }

   public static double getCoordinate(String text, double offset) {
      try {
         return Double.parseDouble(text);
      } catch (Throwable throwable) {
         return offset;
      }
   }

   public static int floor_double(double offset) {
      return (int)(offset + 1024.0) - 1024;
   }

   public static float nextFloat(Random random, float factor, float factor2) {
      return factor >= factor2 ? factor : random.nextFloat() * (factor2 - factor) + factor;
   }

   public static int ceiling_double_int(double offset) {
      int count = (int)offset;
      return offset < (double)count ? count - 1 : count;
   }

   public static int clamp_int(int count, int count2, int count3) {
      return ((count << 8) + count2 << 8) + count3;
   }

   public static boolean checkFloat(float factor, float factor2) {
      return computeFactor(factor2 - factor) < 1.0E-5F;
   }

   public static double nextDouble(Random random, double offset, double offset2) {
      return offset >= offset2 ? offset : random.nextDouble() * (offset2 - offset) + offset;
   }

   public static float abs(float factor) {
      return state
         ? (
            Client.getClient().getModuleManager().fixes.canExecute10()
               ? scaleFactorArray[(int)(factor * scaleFactor) & 4095]
               : scaleFactorArray8[(int)(factor * 651.8986F) & 4095]
         )
         : scaleFactorArray10[(int)(factor * 10430.378F) & 65535];
   }

   public static int computeCount(float factor) {
      int count = (int)factor;
      return factor > (float)count ? count + 1 : count;
   }

   public static double clamp_double(double posX, double posY, double posZ) {
      return (posX - posY) / (posZ - posY);
   }

   private static boolean checkIndex(int count) {
      return count != 0 && (count & count - 1) == 0;
   }

   public static int computeCountByIndex(int count) {
      return computeCount2(count) - (checkIndex(count) ? 0 : 1);
   }

   public static double processAction5(double offset) {
      double offset2 = 0.5 * offset;
      long value = Double.doubleToRawLongBits(offset);
      double offset3 = Double.longBitsToDouble(0x5fe6eb50c7b537aaL - (value >> 1));
      return offset3 * (1.5 - offset2 * offset3 * offset3);
   }

   public static int nextInt(Random random, int count, int count2) {
      return count >= count2 ? count : random.nextInt(count2 - count + 1) + count;
   }

   static {
      for (int index = 0; index < 65536; index++) {
         scaleFactorArray10[index] = (float)Math.sin((double)index * Math.PI * 2.0 / 65536.0);
      }

      for (int index2 = 0; index2 < 4096; index2++) {
         scaleFactorArray8[index2] = (float)Math.sin((double)(((float)index2 + 0.5F) / 4096.0F * (float) (Math.PI * 2)));
      }

      for (int byteValue = 0; byteValue < 360; byteValue += 90) {
         scaleFactorArray8[(int)((float)byteValue * 11.377778F) & 4095] = (float)Math.sin((double)((float)byteValue * (float) (Math.PI / 180.0)));
      }

      for (int index3 = 0; index3 < scaleFactorArray.length; index3++) {
         scaleFactorArray[index3] = AltFloatEntry.round(Math.sin((double)index3 * Math.PI * 2.0 / 4096.0));
      }

      intValueArray = new int[]{0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9};
      coordOffset = Double.longBitsToDouble(0x42b0000000000000L);
      coordOffsetArray6 = new double[257];
      coordOffsetArray = new double[257];

      for (int index4 = 0; index4 < 257; index4++) {
         double offset = Math.asin((double)index4 / 256.0);
         coordOffsetArray[index4] = Math.cos(offset);
         coordOffsetArray6[index4] = offset;
      }
   }
}
