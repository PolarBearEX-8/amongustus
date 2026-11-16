package client.util;

import client.setting.SettingProvider;

public class VectorAggregator implements SettingProvider {
   @Override
   public float getFloat3(double offset, double offset2) {
      return (float)offset;
   }

   @Override
   public long getLong2(double offset, double offset2) {
      return (long)offset;
   }

   @Override
   public float getFloat2(float factor, float factor2) {
      return factor2;
   }

   @Override
   public float getFloat(double offset, double offset2) {
      return (float)offset2;
   }

   @Override
   public double getDouble4(double offset, double offset2) {
      return offset2;
   }

   @Override
   public double computeValueByDouble(double offset, double offset2) {
      return offset2;
   }

   @Override
   public double getValue() {
      return 0.5;
   }

   @Override
   public int getInt2(int count, int count2) {
      return count;
   }

   @Override
   public double computeValue(double offset, double offset2) {
      return offset;
   }

   @Override
   public int getInt(double offset, double offset2) {
      return (int)offset2;
   }

   @Override
   public int getInt3(int count, int count2) {
      return count;
   }

   @Override
   public long getLong(long value, long value2) {
      return value2;
   }
}
