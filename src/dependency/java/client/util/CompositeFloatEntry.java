package client.util;

public class CompositeFloatEntry {
   public float factor;
   public float factor3;
   public float factor2;

   public void updateState() {
      this.factor = 0.0F;
      this.factor3 = 0.0F;
      this.factor2 = 0.0F;
   }

   public float computeFactor(float factor4, float factor5) {
      this.factor += factor4;
      float factor6 = (this.factor - this.factor3) * factor5;
      this.factor2 = this.factor2 + (factor6 - this.factor2) * 0.5F;
      if (factor6 > 0.0F && factor6 > this.factor2 || factor6 < 0.0F && factor6 < this.factor2) {
         factor6 = this.factor2;
      }

      this.factor3 += factor6;
      return factor6;
   }
}
