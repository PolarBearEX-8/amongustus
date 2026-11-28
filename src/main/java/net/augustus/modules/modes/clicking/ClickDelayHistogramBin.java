package net.augustus.modules.modes.clicking;

public class ClickDelayHistogramBin {
   private int minDelay;
   private int maxDelay;
   private int sampleCount;

   public ClickDelayHistogramBin(int n, int n2) {
      this.minDelay = n;
      this.maxDelay = n2;
   }

   public int getMinDelay() {
      return this.minDelay;
   }

   public int getMaxDelay() {
      return this.maxDelay;
   }

   public int getSampleCount() {
      return this.sampleCount;
   }

   public void setSampleCount(int n) {
      this.sampleCount = n;
   }

   public static int accessSampleCount(ClickDelayHistogramBin clickDelayHistogramBin) {
      return clickDelayHistogramBin.sampleCount;
   }

   public static int accessMinDelay(ClickDelayHistogramBin clickDelayHistogramBin) {
      return clickDelayHistogramBin.minDelay;
   }

   public static int accessMaxDelay(ClickDelayHistogramBin clickDelayHistogramBin) {
      return clickDelayHistogramBin.maxDelay;
   }
}
