package net.augustus.utils.math;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public class DefaultRandomGenerator implements RandomNumberGenerator {
   public static RandomNumberGenerator instance = new DefaultRandomGenerator();

   @Override
   public float nextSecureFloat(double min, double max) {
      if (min == max) {
         return (float)min;
      } else {
         if (min > max) {
            double d3 = min;
            min = max;
            max = d3;
         }

         SecureRandom rand = new SecureRandom();
         float f = (float)(max - min);
         return (float)(min + (double)(rand.nextFloat() * f));
      }
   }

   @Override
   public float nextFloat(double min, double max) {
      return this.nextFloat((float)min, (float)max);
   }

   @Override
   public double nextRandomSin() {
      double d = Math.PI * 2;
      double d2 = 0.0;
      return Math.sin(instance.nextDouble(d2, d));
   }

   @Override
   public int nextInt(int min, int max) {
      if (max == Integer.MAX_VALUE) {
         max--;
      }

      if (min == Integer.MAX_VALUE) {
         min--;
      }

      if (min == max) {
         return min;
      } else {
         if (min > max) {
            int n3 = min;
            min = max;
            max = n3;
         }

         return ThreadLocalRandom.current().nextInt(min, max + 1);
      }
   }

   @Override
   public long nextLong(double min, double max) {
      long l;
      l = (long)min;
      return this.nextLong(l, (long)max);
   }


   @Override
   public int nextInt(double min, double max) {
      return this.nextInt((int)min, (int)max);
   }

   @Override
   public long nextLong(long min, long max) {
      if (min == max) {
         return min;
      } else {
         if (min > max) {
            long l3 = min;
            min = max;
            max = l3;
         }

         return ThreadLocalRandom.current().nextLong(min, max + 1L);
      }
   }

   @Override
   public double nextDouble(double min, double max) {
      if (min == max) {
         return min;
      } else {
         if (min > max) {
            double d3 = min;
            min = max;
            max = d3;
         }

         return ThreadLocalRandom.current().nextDouble(min, max);
      }
   }

   @Override
   public float nextFloat(float min, float max) {
      if (min == max) {
         return min;
      } else {
         if (min > max) {
            float f3 = min;
            min = max;
            max = f3;
         }

         return (float)ThreadLocalRandom.current().nextDouble((double)min, (double)max);
      }
   }

   @Override
   public double nextSecureIntAsDouble(double d, double d2) {
      return (double)this.nextSecureInt((int)d, (int)d2);
   }

   @Override
   public int nextSecureInt(int min, int max) {
      if (min == max) {
         return min;
      } else {
         if (min > max) {
            int n3 = min;
            min = max;
            max = n3;
         }

         return min + new SecureRandom().nextInt((max - min) + 1);
      }
   }

   @Override
   public double nextSecureDouble(double min, double max) {
      if (min == max) {
         return min;
      } else {
         if (min > max) {
            double d3 = min;
            min = max;
            max = d3;
         }

         SecureRandom rand = new SecureRandom();
         double d4 = max - min;
         return min + rand.nextDouble() * d4;
      }
   }


}
