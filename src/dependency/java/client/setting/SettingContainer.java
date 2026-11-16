package client.setting;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public class SettingContainer implements SettingProvider {
   public static SettingProvider settingProvider = new SettingContainer();

   @Override
   public long getLong(long value, long value2) {
      if (value == value2) {
         return value;
      }

      long min = value;
      long max = value2;
      if (value > value2) {
         min = value2;
         max = value;
      }

      return ThreadLocalRandom.current().nextLong(min, max + 1L);
   }

   @Override
   public float getFloat(double offset, double offset2) {
      if (offset == offset2) {
         return (float)offset;
      }

      double min = offset;
      double max = offset2;
      if (offset > offset2) {
         min = offset2;
         max = offset;
      }

      SecureRandom secureRandom = new SecureRandom();
      float span = (float)(max - min);
      return (float)(min + (double)(secureRandom.nextFloat() * span));
   }

   @Override
   public long getLong2(double offset, double offset2) {
      return this.getLong((long)offset, (long)offset2);
   }

   @Override
   public int getInt(double offset, double offset2) {
      return this.getInt2((int)offset, (int)offset2);
   }

   @Override
   public double computeValue(double offset, double offset2) {
      return (double)this.getInt3((int)offset, (int)offset2);
   }

   @Override
   public double getValue() {
      return Math.sin(settingProvider.getDouble4(0.0, Math.PI * 2));
   }

   @Override
   public int getInt2(int count, int count2) {
      int min = count;
      int max = count2;
      if (max == Integer.MAX_VALUE) {
         max--;
      }

      if (min == Integer.MAX_VALUE) {
         min--;
      }

      if (min == max) {
         return min;
      }

      if (min > max) {
         int temp = min;
         min = max;
         max = temp;
      }

      return ThreadLocalRandom.current().nextInt(min, max + 1);
   }

   @Override
   public double computeValueByDouble(double offset, double offset2) {
      if (offset == offset2) {
         return offset;
      }

      double min = offset;
      double max = offset2;
      if (offset > offset2) {
         min = offset2;
         max = offset;
      }

      SecureRandom secureRandom = new SecureRandom();
      double span = max - min;
      return min + secureRandom.nextDouble() * span;
   }

   @Override
   public float getFloat2(float factor, float factor2) {
      if (factor == factor2) {
         return factor;
      }

      float min = factor;
      float max = factor2;
      if (factor > factor2) {
         min = factor2;
         max = factor;
      }

      return (float)ThreadLocalRandom.current().nextDouble((double)min, (double)max);
   }

   @Override
   public double getDouble4(double offset, double offset2) {
      if (offset == offset2) {
         return offset;
      }

      double min = offset;
      double max = offset2;
      if (offset > offset2) {
         min = offset2;
         max = offset;
      }

      return ThreadLocalRandom.current().nextDouble(min, max);
   }

   @Override
   public float getFloat3(double offset, double offset2) {
      return this.getFloat2((float)offset, (float)offset2);
   }

   @Override
   public int getInt3(int count, int count2) {
      if (count == count2) {
         return count;
      }

      int min = count;
      int max = count2;
      if (count > count2) {
         min = count2;
         max = count;
      }

      SecureRandom secureRandom = new SecureRandom();
      int span = max - min;
      return min + secureRandom.nextInt(span + 1);
   }
}
