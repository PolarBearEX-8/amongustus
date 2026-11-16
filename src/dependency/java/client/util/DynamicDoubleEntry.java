package client.util;

public class DynamicDoubleEntry {
   public double value2;
   public static final DynamicDoubleEntry dynamicDoubleEntry = new DynamicDoubleEntry(0.0, 0.0, 0.0);
   public double value3;
   public double value;

   public double getValue2() {
      return this.value2;
   }

   public double computeValueByDouble(double posX, double posY, double posZ) {
      double offset = this.getValue2() - posX;
      double offset2 = this.getValue3() - posY;
      double offset3 = this.getValue() - posZ;
      return offset * offset + offset2 * offset2 + offset3 * offset3;
   }

   public double getValue3() {
      return this.value3;
   }

   public double getValue() {
      return this.value;
   }

   public double computeValue(DynamicDoubleEntry dynamicDoubleEntry2) {
      double value5 = dynamicDoubleEntry2.getValue2();
      double value6 = dynamicDoubleEntry2.getValue();
      double value7 = dynamicDoubleEntry2.getValue3();
      double offset = value5;
      return this.computeValueByDouble(offset, value7, value6);
   }

   public double computeValue(double posX, double posY, double posZ) {
      double offset = this.getValue2() + 0.5 - posX;
      double offset2 = this.getValue3() + 0.5 - posY;
      double offset3 = this.getValue() + 0.5 - posZ;
      return offset * offset + offset2 * offset2 + offset3 * offset3;
   }

   public DynamicDoubleEntry resolveDynamicDoubleEntry(DynamicDoubleEntry dynamicDoubleEntry2) {
      return new DynamicDoubleEntry(
         this.getValue3() * dynamicDoubleEntry2.getValue() - this.getValue() * dynamicDoubleEntry2.getValue3(),
         this.getValue() * dynamicDoubleEntry2.getValue2() - this.getValue2() * dynamicDoubleEntry2.getValue(),
         this.getValue2() * dynamicDoubleEntry2.getValue3() - this.getValue3() * dynamicDoubleEntry2.getValue2()
      );
   }

   public DynamicDoubleEntry(double posX, double posY, double posZ) {
      this.value2 = posX;
      this.value3 = posY;
      this.value = posZ;
   }

   @Override
   public String toString() {
      return this.getValue2() + "," + this.getValue3() + "," + this.getValue();
   }
}
