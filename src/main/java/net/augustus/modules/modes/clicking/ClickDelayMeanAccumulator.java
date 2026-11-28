package net.augustus.modules.modes.clicking;

public class ClickDelayMeanAccumulator {
   private double totalSum;
   private int sampleCount;

   public void addSample(double d) {
      this.totalSum += d;
      this.sampleCount++;
   }

   public double getMean() {
      return this.totalSum / (double)this.sampleCount;
   }
}
