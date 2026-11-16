package client.util;

public class StaticFloatEntry {
   public float factor3;
   public float factor2;
   public float factor;
   public static final StaticFloatEntry staticFloatEntry = new StaticFloatEntry(0.0F, 0.0F, 0.0F);

   public float getFactor3() {
      return this.factor3;
   }

   public float getFactor() {
      return this.factor;
   }

   public double computeValue(StaticFloatEntry staticFloatEntry2) {
      double factor4 = (double)staticFloatEntry2.getFactor2();
      double factor5 = (double)staticFloatEntry2.getFactor();
      double factor6 = (double)staticFloatEntry2.getFactor3();
      double offset = factor4;
      return this.computeValueByDouble(offset, factor6, factor5);
   }

   public void setFactor2(float factor4) {
      this.factor2 = factor4;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public double computeValue(double posX, double posY, double posZ) {
      double offset = (double)this.getFactor2() + 0.5 - posX;
      double offset2 = (double)this.getFactor3() + 0.5 - posY;
      double offset3 = (double)this.getFactor() + 0.5 - posZ;
      return offset * offset + offset2 * offset2 + offset3 * offset3;
   }

   public void applyFloat(float posX, float posY, float posZ) {
      this.factor2 = posX;
      this.factor3 = posY;
      this.factor = posZ;
   }

   public StaticFloatEntry resolveStaticFloatEntry(StaticFloatEntry staticFloatEntry2) {
      return new StaticFloatEntry(
         this.getFactor3() * staticFloatEntry2.getFactor() - this.getFactor() * staticFloatEntry2.getFactor3(),
         this.getFactor() * staticFloatEntry2.getFactor2() - this.getFactor2() * staticFloatEntry2.getFactor(),
         this.getFactor2() * staticFloatEntry2.getFactor3() - this.getFactor3() * staticFloatEntry2.getFactor2()
      );
   }

   public double computeValueByDouble(double posX, double posY, double posZ) {
      double offset = (double)this.getFactor2() - posX;
      double offset2 = (double)this.getFactor3() - posY;
      double offset3 = (double)this.getFactor() - posZ;
      return offset * offset + offset2 * offset2 + offset3 * offset3;
   }

   public void setFactor3(float factor4) {
      this.factor3 = factor4;
   }

   public void setFactor(float factor4) {
      this.factor = factor4;
   }

   public StaticFloatEntry(float posX, float posY, float posZ) {
      this.factor2 = posX;
      this.factor3 = posY;
      this.factor = posZ;
   }

   @Override
   public String toString() {
      return this.getFactor2() + "," + this.getFactor3() + "," + this.getFactor();
   }
}
