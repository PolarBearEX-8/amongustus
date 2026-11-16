package client.setting;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public class SettingProviderImpl implements SettingProvider {
   public static SettingProvider settingProvider = new SettingProviderImpl();

   @Override
   public long getLong2(double offset, double offset2) {
      long value = (long)offset;
      long value2 = (long)offset2;
      long value3 = value;
      return this.getLong(value3, value2);
   }

   @Override
   public long getLong(long value, long value2) {
      long value3 = value;
      long value4 = value2;
      if (value == value2) {
         return value;
      } else {
         if (value > value2) {
            value3 = value2;
            value4 = value;
         }

         return ThreadLocalRandom.current().nextLong(value3, value4 + 1L);
      }
   }

   @Override
   public double computeValue(double offset, double offset2) {
      int count = (int)offset;
      int count2 = (int)offset2;
      int count3 = count;
      return (double)this.getInt3(count3, count2);
   }

   @Override
   public int getInt(double offset, double offset2) {
      int count = (int)offset;
      int count2 = (int)offset2;
      int count3 = count;
      return this.getInt2(count3, count2);
   }

   @Override
   public int getInt2(int count, int count2) {
      int count3 = count;
      int count4 = count2;
      if (count2 == Integer.MAX_VALUE) {
         count4 = count2 - 1;
      }

      if (count == Integer.MAX_VALUE) {
         count3 = count - 1;
      }

      if (count3 == count4) {
         return count3;
      } else {
         if (count3 > count4) {
            int count5 = count3;
            count3 = count4;
            count4 = count5;
         }

         return ThreadLocalRandom.current().nextInt(count3, count4 + 1);
      }
   }

   @Override
   public int getInt3(int count, int count2) {
      int count3 = count;
      int count4 = count2;
      if (count == count2) {
         return count;
      } else {
         if (count > count2) {
            count3 = count2;
            count4 = count;
         }

         SecureRandom secureRandom = new SecureRandom();
         int count5 = count4 - count3;
         return count3 + secureRandom.nextInt(count5 + 1);
      }
   }

   @Override
   public float getFloat2(float factor, float factor2) {
      float factor3 = factor;
      float factor4 = factor2;
      if (factor == factor2) {
         return factor;
      } else {
         if (factor > factor2) {
            factor3 = factor2;
            factor4 = factor;
         }

         return (float)ThreadLocalRandom.current().nextDouble((double)factor3, (double)factor4);
      }
   }

   @Override
   public double getValue() {
      double offset = Math.PI * 2;
      double offset2 = 0.0;
      return Math.sin(settingProvider.getDouble4(offset2, offset));
   }

   @Override
   public double getDouble4(double offset, double offset2) {
      double offset3 = offset;
      double offset4 = offset2;
      if (offset == offset2) {
         return offset;
      } else {
         if (offset > offset2) {
            offset3 = offset2;
            offset4 = offset;
         }

         return ThreadLocalRandom.current().nextDouble(offset3, offset4);
      }
   }

   @Override
   public float getFloat3(double offset, double offset2) {
      byte byteValue = 0;
      float factor = (float)offset;
      float factor2 = (float)offset2;
      float factor3 = factor;
      return this.getFloat2(factor3, factor2);
   }

   @Override
   public double computeValueByDouble(double offset, double offset2) {
      double offset3 = offset;
      double offset4 = offset2;
      if (offset == offset2) {
         return offset;
      } else {
         if (offset > offset2) {
            offset3 = offset2;
            offset4 = offset;
         }

         SecureRandom secureRandom = new SecureRandom();
         double offset5 = offset4 - offset3;
         return offset3 + secureRandom.nextDouble() * offset5;
      }
   }

   @Override
   public float getFloat(double offset, double offset2) {
      double offset3 = offset;
      double offset4 = offset2;
      if (offset == offset2) {
         return (float)offset;
      } else {
         if (offset > offset2) {
            offset3 = offset2;
            offset4 = offset;
         }

         SecureRandom secureRandom = new SecureRandom();
         float factor = (float)(offset4 - offset3);
         return (float)(offset3 + (double)(secureRandom.nextFloat() * factor));
      }
   }
}
