package client.setting;

public interface SettingProvider {
   float getFloat(double offset, double offset2);

   long getLong(long value, long value2);

   long getLong2(double offset, double offset2);

   double computeValue(double offset, double offset2);

   int getInt(double offset, double offset2);

   double getValue();

   int getInt2(int count, int count2);

   float getFloat2(float factor, float factor2);

   double computeValueByDouble(double offset, double offset2);

   double getDouble4(double offset, double offset2);

   float getFloat3(double offset, double offset2);

   int getInt3(int count, int count2);
}
