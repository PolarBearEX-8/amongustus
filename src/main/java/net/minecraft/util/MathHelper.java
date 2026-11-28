package net.minecraft.util;

import java.util.Random;
import java.util.UUID;
import net.augustus.Augustus;
import net.optifine.util.MathUtils;

public class MathHelper {
   public static final float SQRT_2 = sqrt_float(2.0F);
   private static final int SIN_BITS = 12;
   private static final int SIN_MASK = 4095;
   private static final int SIN_COUNT = 4096;
   public static final float PI = 3.1415927F;
   public static final float PI2 = 6.2831855F;
   public static final float PId2 = 1.5707964F;
   private static final float radFull = 6.2831855F;
   private static final float degFull = 360.0F;
   private static final float radToIndex = 651.8986F;
   private static final float degToIndex = 11.377778F;
   public static final float deg2Rad = 0.017453292F;
   private static final float[] SIN_TABLE_FAST = new float[4096];
   private static final float[] SIN_TABLE_FAST_ROUNDED = new float[4096];
   public static boolean fastMath = false;
   private static final int SIN_HALF_PI_OFFSET = 1024;
   public static final float PI_ROUNDED = MathUtils.roundToFloat(3.141592653589793);
   public static final float TWO_PI_ROUNDED = MathUtils.roundToFloat(6.283185307179586);
   public static final float HALF_PI_ROUNDED = MathUtils.roundToFloat(1.5707963267948966);
   private static final float RAD_TO_INDEX_ROUNDED = MathUtils.roundToFloat(651.8986469044033);
   public static final float DEG_TO_RAD_ROUNDED = MathUtils.roundToFloat(0.017453292519943295);
   private static final float[] SIN_TABLE = new float[65536];
   private static final int[] multiplyDeBruijnBitPosition;
   private static final double FRAC_BIAS;
   private static final double[] ASINE_TAB;
   private static final double[] COS_TAB;

   public static int parseIntWithDefaultAndMax(String str, int n, int n2) {
      return Math.max(n2, parseIntWithDefault(str, n));
   }

   public static boolean epsilonEquals(float f, float f2) {
      return abs(f2 - f) < 1.0E-5F;
   }

   public static double fastInvSqrt(double d) {
      double d2 = 0.5 * d;
      long l = Double.doubleToRawLongBits(d);
      l = 6910469410427058090L - (l >> 1);
      d = Double.longBitsToDouble(l);
      return d * (1.5 - d2 * d * d);
   }

   public static int truncateDoubleToInt(double d) {
      return (int)(d + 1024.0) - 1024;
   }

   private static boolean isPowerOfTwo(int n) {
      return n != 0 && (n & n - 1) == 0;
   }

   public static double atan2(double d, double d2) {
      double d3 = d2 * d2 + d * d;
      if (Double.isNaN(d3)) {
         return 0.0 / 0.0;
      } else {
         boolean flag = d < 0.0;
         if (flag) {
            d = -d;
         }

         boolean flag2 = d2 < 0.0;
         if (flag2) {
            d2 = -d2;
         }

         boolean flag3 = d > d2;
         if (flag3) {
            double d4 = d2;
            d2 = d;
            d = d4;
         }

         double d5 = fastInvSqrt(d3);
         d2 *= d5;
         d *= d5;
         double d6 = FRAC_BIAS + d;
         int n = (int)Double.doubleToRawLongBits(d6);
         double d7 = ASINE_TAB[n];
         double d8 = COS_TAB[n];
         double d9 = d6 - FRAC_BIAS;
         double d10 = d * d8 - d2 * d9;
         double d12 = d7 + ((6.0 + d10 * d10) * d10 * 0.16666666666666666);
         if (flag3) {
            d12 = 1.5707963267948966 - d12;
         }

         if (flag2) {
            d12 = 3.141592653589793 - d12;
         }

         if (flag) {
            d12 = -d12;
         }

         return d12;
      }
   }

   public static UUID getRandomUuid(Random rand) {
      long l = rand.nextLong() & -61441L | 16384L;
      long l2 = rand.nextLong() & 4611686018427387903L | -9223372036854775808L;
      return new UUID(l, l2);
   }

   public static int ceiling_double_int(double d) {
      int n = (int)d;
      return d > (double)n ? n + 1 : n;
   }

   public static double average(long[] longs) {
      long l = 0L;

      for (long l2 : longs) {
         l += l2;
      }

      return (double)l / (double)longs.length;
   }

   public static int hsvToRGB(float f, float f2, float f3) {
      int n = (int)(f * 6.0F) % 6;
      float f4 = f * 6.0F - (float)n;
      float f5 = f3 * (1.0F - f2);
      float f6 = f3 * (1.0F - f4 * f2);
      float f7 = f3 * (1.0F - (1.0F - f4) * f2);
      float f8;
      float f9;
      float f10;
      switch (n) {
         case 0:
            f8 = f3;
            f9 = f7;
            f10 = f5;
            break;
         case 1:
            f8 = f6;
            f9 = f3;
            f10 = f5;
            break;
         case 2:
            f8 = f5;
            f9 = f3;
            f10 = f7;
            break;
         case 3:
            f8 = f5;
            f9 = f6;
            f10 = f3;
            break;
         case 4:
            f8 = f7;
            f9 = f5;
            f10 = f3;
            break;
         case 5:
            f8 = f3;
            f9 = f5;
            f10 = f6;
            break;
         default:
            throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + f + ", " + f2 + ", " + f3);
      }

      return clamp_int((int)(f8 * 255.0F), 0, 255) << 16 | clamp_int((int)(f9 * 255.0F), 0, 255) << 8 | clamp_int((int)(f10 * 255.0F), 0, 255);
   }

   public static int floor_float(float f) {
      int n = (int)f;
      return f < (float)n ? n - 1 : n;
   }

   public static int abs_int(int n) {
      return n >= 0 ? n : -n;
   }

   public static float wrapAngleTo180_float(float f) {
      f %= 360.0F;
      if (f >= 180.0F) {
         f -= 360.0F;
      }

      if (f < -180.0F) {
         f += 360.0F;
      }

      return f;
   }

   public static float clamp_float(float f, float f2, float f3) {
      return f < f2 ? f2 : Math.min(f, f3);
   }

   public static double wrapAngleTo180_double(double d) {
      d %= 360.0;
      if (d >= 180.0) {
         d -= 360.0;
      }

      if (d < -180.0) {
         d += 360.0;
      }

      return d;
   }

   public static int clamp_int(int n, int n2, int n3) {
      return n < n2 ? n2 : (n > n3 ? n3 : n);
   }

   public static double frac(double d) {
      return d - Math.floor(d);
   }

   public static int floor_double(double d) {
      int n = (int)d;
      return d < (double)n ? n - 1 : n;
   }

   public static float randomFloatClamp(Random rand, float f, float f2) {
      return f >= f2 ? f : rand.nextFloat() * (f2 - f) + f;
   }

   public static double parseDoubleWithDefault(String str, double d) {
      try {
         return Double.parseDouble(str);
      } catch (Throwable e) {
         return d;
      }
   }

   public static double fastInverseSqrt(double d) {
      double d2 = 0.5 * d;
      long l = Double.doubleToRawLongBits(d);
      l = 6910469410427058090L - (l >> 1);
      d = Double.longBitsToDouble(l);
      return d * (1.5 - d2 * d * d);
   }

   public static float sin(float f) {
      return fastMath
         ? (Augustus.getInstance().getModuleManager().fixes.isMouseDelayFixEnabled() ? SIN_TABLE_FAST_ROUNDED[(int)(f * RAD_TO_INDEX_ROUNDED) & 4095] : SIN_TABLE_FAST[(int)(f * 651.8986F) & 4095])
         : SIN_TABLE[(int)(f * 10430.378F) & 65535];
   }

   public static int floor_double_abs(double d) {
      return (int)(d >= 0.0 ? d : -d + 1.0);
   }

   public static float abs(float f) {
      return f >= 0.0F ? f : -f;
   }

   public static long floor_double_long(double d) {
      long l = (long)d;
      return d < (double)l ? l - 1L : l;
   }

   static {
      for (int i = 0; i < 65536; i++) {
         SIN_TABLE[i] = (float)Math.sin((double)i * 3.141592653589793 * 2.0 / 65536.0);
      }

      for (int j = 0; j < 4096; j++) {
         SIN_TABLE_FAST[j] = (float)Math.sin((double)(((float)j + 0.5F) / 4096.0F * 6.2831855F));
      }

      for (int k = 0; k < 360; k += 90) {
         SIN_TABLE_FAST[(int)((float)k * 11.377778F) & 4095] = (float)Math.sin((double)((float)k * 0.017453292F));
      }

      for (int m = 0; m < SIN_TABLE_FAST_ROUNDED.length; m++) {
         SIN_TABLE_FAST_ROUNDED[m] = MathUtils.roundToFloat(Math.sin((double)m * 3.141592653589793 * 2.0 / 4096.0));
      }

      multiplyDeBruijnBitPosition = new int[]{
         0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9
      };
      FRAC_BIAS = Double.longBitsToDouble(4805340802404319232L);
      ASINE_TAB = new double[257];
      COS_TAB = new double[257];

      for (int idx = 0; idx < 257; idx++) {
         double d2 = Math.asin((double)idx / 256.0);
         COS_TAB[idx] = Math.cos(d2);
         ASINE_TAB[idx] = d2;
      }
   }

   public static int roundUp(int n, int n2) {
      if (n2 == 0) {
         return 0;
      } else if (n == 0) {
         return n2;
      } else {
         if (n < 0) {
            n2 *= -1;
         }

         int n3 = n % n2;
         return n3 == 0 ? n : n + n2 - n3;
      }
   }

   public static double abs_max(double d, double d2) {
      if (d < 0.0) {
         d = -d;
      }

      if (d2 < 0.0) {
         d2 = -d2;
      }

      return d > d2 ? d : d2;
   }

   public static int calculateLogBaseTwo(int n) {
      return calculateLogBaseTwoDeBruijn(n) - (isPowerOfTwo(n) ? 0 : 1);
   }

   public static double denormalizeClamp(double d, double d2, double d3) {
      return d3 < 0.0 ? d : (d3 > 1.0 ? d2 : d + (d2 - d) * d3);
   }

   public static double parseDoubleWithDefaultAndMax(String str, double d, double d2) {
      return Math.max(d2, parseDoubleWithDefault(str, d));
   }

   public static long getCoordinateRandom(int n, int n2, int n3) {
      long l = (long)(n * 3129871) ^ (long)n3 * 116129781L ^ (long)n2;
      return l * l * 42317861L + l * 11L;
   }

   public static double pct(double d, double d2, double d3) {
      return (d - d2) / (d3 - d2);
   }

   public static int bucketInt(int n, int n2) {
      return n < 0 ? -((-n - 1) / n2) - 1 : n / n2;
   }

   public static float cos(float f) {
      return fastMath
         ? (Augustus.getInstance().getModuleManager().fixes.isMouseDelayFixEnabled() ? SIN_TABLE_FAST_ROUNDED[(int)(f * RAD_TO_INDEX_ROUNDED + 1024.0F) & 4095] : SIN_TABLE_FAST[(int)((f + 1.5707964F) * 651.8986F) & 4095])
         : SIN_TABLE[(int)(f * 10430.378F + 16384.0F) & 65535];
   }

   public static float sqrt_double(double d) {
      return (float)Math.sqrt(d);
   }

   public static float sqrt_float(float f) {
      return (float)Math.sqrt((double)f);
   }

   public static int roundUpToPowerOfTwo(int n) {
      int n2 = n - 1;
      n2 |= n2 >> 1;
      n2 |= n2 >> 2;
      n2 |= n2 >> 4;
      n2 |= n2 >> 8;
      n2 |= n2 >> 16;
      return n2 + 1;
   }

   public static int rgb2(float f, float f2, float f3) {
      return rgb(floor_float(f * 255.0F), floor_float(f2 * 255.0F), floor_float(f3 * 255.0F));
   }

   public static int parseIntWithDefault(String str, int n) {
      try {
         return Integer.parseInt(str);
      } catch (Throwable e) {
         return n;
      }
   }

   public static int ceiling_float_int(float f) {
      int n = (int)f;
      return f > (float)n ? n + 1 : n;
   }

   public static int rgb(int n, int n2, int n3) {
      return (((n << 8) + n2) << 8) + n3;
   }

   public static double clamp_double(double d, double d2, double d3) {
      return d < d2 ? d2 : Math.min(d, d3);
   }

   public static double getRandomDoubleInRange(Random rand, double d, double d2) {
      return d >= d2 ? d : rand.nextDouble() * (d2 - d) + d;
   }

   public static int normalizeAngle(int n, int n2) {
      return (n % n2 + n2) % n2;
   }

   private static int calculateLogBaseTwoDeBruijn(int n) {
      n = isPowerOfTwo(n) ? n : roundUpToPowerOfTwo(n);
      return multiplyDeBruijnBitPosition[(int)((long)n * 125613361L >> 27) & 31];
   }

   public static int getRandomIntegerInRange(Random rand, int n, int n2) {
      return n >= n2 ? n : rand.nextInt(n2 - n + 1) + n;
   }

   public static long getPositionRandom(Vec3i vec) {
      return getCoordinateRandom(vec.getX(), vec.getY(), vec.getZ());
   }

   public static int multiplyColor(int n, int n2) {
      int n3 = (n & 0xFF0000) >> 16;
      int n4 = (n2 & 0xFF0000) >> 16;
      int n5 = (n & 0xFF00) >> 8;
      int n6 = (n2 & 0xFF00) >> 8;
      int n7 = (n & 0xFF) >> 0;
      int n8 = (n2 & 0xFF) >> 0;
      return n & 0xFF000000 | ((int)((float)n3 * (float)n4 / 255.0F)) << 16 | ((int)((float)n5 * (float)n6 / 255.0F)) << 8 | ((int)((float)n7 * (float)n8 / 255.0F));
   }

}
