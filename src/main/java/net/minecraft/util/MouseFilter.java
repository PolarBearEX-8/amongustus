package net.minecraft.util;

public class MouseFilter {
   private float targetValue;
   private float remainingValue;
   private float lastAmount;

   public float smooth(float f, float f2) {
      this.targetValue += f;
      f = (this.targetValue - this.remainingValue) * f2;
      this.lastAmount = this.lastAmount + (f - this.lastAmount) * 0.5F;
      if (f > 0.0F && f > this.lastAmount || f < 0.0F && f < this.lastAmount) {
         f = this.lastAmount;
      }

      this.remainingValue += f;
      return f;
   }

   public void reset() {
      this.targetValue = 0.0F;
      this.remainingValue = 0.0F;
      this.lastAmount = 0.0F;
   }
}
