package client.util;

public class SplineMonitor {
   public float factor = 1.0F;
   public float factor2 = 0.0F;
   public float factor3 = 0.0F;

   @Override
   public String toString() {
      return "" + this.factor + ", " + this.factor2 + ", " + this.factor3;
   }

   public SplineMonitor(float posX, float posY, float posZ) {
      this.factor = posX;
      this.factor2 = posY;
      this.factor3 = posZ;
   }

   public float getFactor3() {
      return this.factor3;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public float getFactor() {
      return this.factor;
   }
}
