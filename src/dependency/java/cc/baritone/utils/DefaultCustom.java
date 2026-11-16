package cc.baritone.utils;

public class DefaultCustom {
   public final float factor;
   public final float factor2;

   public DefaultCustom resolveDefaultCustom(DefaultCustom defaultCustom) {
      return new DefaultCustom(this.factor + defaultCustom.factor, this.factor2 + defaultCustom.factor2);
   }

   public static float computeFactor(float factor3) {
      return Math.max(-90.0F, Math.min(90.0F, factor3));
   }

   public DefaultCustom getDefaultCustom2() {
      return new DefaultCustom(computeFactorByFloat(this.factor), computeFactor(this.factor2));
   }

   public DefaultCustom resolveDefaultCustom(float factor3) {
      return new DefaultCustom(this.factor, factor3);
   }

   public float getFactor() {
      return this.factor;
   }

   public DefaultCustom resolveDefaultCustomByDefaultCustom(DefaultCustom defaultCustom) {
      return new DefaultCustom(this.factor - defaultCustom.factor, this.factor2 - defaultCustom.factor2);
   }

   public boolean matchesDefaultCustom(DefaultCustom defaultCustom) {
      float factor3 = Math.abs(computeFactorByFloat(this.factor) - computeFactorByFloat(defaultCustom.factor));
      return (double)factor3 < 0.01 || (double)factor3 > 359.99;
   }

   public DefaultCustom(float factor3, float factor4) {
      this.factor = factor3;
      this.factor2 = factor4;
   }

   public boolean matchesDefaultCustomByDefaultCustom(DefaultCustom defaultCustom) {
      return this.matchesDefaultCustom(defaultCustom) && (double)Math.abs(this.factor2 - defaultCustom.factor2) < 0.01;
   }

   public float getFactor2() {
      return this.factor2;
   }

   public static float computeFactorByFloat(float factor3) {
      float factor4 = factor3 % 360.0F;
      if (factor4 < -180.0F) {
         factor4 += 360.0F;
      }

      if (factor4 > 180.0F) {
         factor4 -= 360.0F;
      }

      return factor4;
   }

   public DefaultCustom getDefaultCustomAsDefaultCustom() {
      return new DefaultCustom(this.factor, computeFactor(this.factor2));
   }

   public DefaultCustom getDefaultCustom() {
      return new DefaultCustom(computeFactorByFloat(this.factor), this.factor2);
   }

   @Override
   public String toString() {
      return "Yaw: " + this.factor + ", Pitch: " + this.factor2;
   }
}
