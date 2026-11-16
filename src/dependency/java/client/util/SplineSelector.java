package client.util;

public class SplineSelector {
   public float factor;
   public float factor2;
   public float factor3;
   public long timestamp;

   public long getTimestamp() {
      return this.timestamp;
   }

   public float getFactor3() {
      return this.factor3;
   }

   public float getFactor() {
      return this.factor;
   }

   public float computeFactor(float factor4) {
      long timestamp2 = System.currentTimeMillis();
      float factor5 = this.factor;
      long timestamp3 = this.timestamp;
      float factor6 = (float)(timestamp2 - timestamp3) / 1000.0F;
      float factor7 = factor4 >= factor5 ? this.factor2 : this.factor3;
      float factor8 = computeFactor(factor5, factor4, factor6, factor7);
      this.factor = factor8;
      this.timestamp = timestamp2;
      return factor8;
   }

   public static float computeFactor(float posX, float posX2, float posY, float posZ) {
      if (posY <= 0.0F) {
         return posX;
      } else {
         float factor4 = posX2 - posX;
         float factor5;
         if (posZ > 0.0F && posY < posZ && Math.abs(factor4) > 1.0E-6F) {
            float factor6 = posZ / posY;
            float factor7 = 4.61F;
            float factor8 = 0.13F;
            float factor9 = 10.0F;
            float factor10 = factor7 - 1.0F / (factor8 + factor6 / factor9);
            float factor11 = RotationRanker.computeFactor(posY / posZ * factor10, 0.0F, 1.0F);
            factor5 = posX + factor4 * factor11;
         } else {
            factor5 = posX2;
         }

         return factor5;
      }
   }

   public SplineSelector(float factor4, float factor5) {
      this(factor4, factor5, factor5);
   }

   public float computeFactor(float posX, float posY, float posZ) {
      this.factor2 = posY;
      this.factor3 = posZ;
      return this.computeFactor(posX);
   }

   public float getFactor2() {
      return this.factor2;
   }

   public SplineSelector(float posX, float posY, float posZ) {
      this.factor = posX;
      this.factor2 = posY;
      this.factor3 = posZ;
      this.timestamp = System.currentTimeMillis();
   }
}
