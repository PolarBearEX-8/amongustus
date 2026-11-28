package net.augustus.modules.modes.clicking;

public class MinKurtosisTracker {
   private double minValue = Double.MAX_VALUE;

   public void recordSample(double d) {
      if (d < this.minValue) {
         this.minValue = d;
      }
   }

   public double getMinValue() {
      return this.minValue;
   }
}
