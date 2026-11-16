package cc.baritone.utils;

public final class HeuristicCalculator {
   public int count3;
   public int count2;
   public int count;
   public double value;

   public HeuristicCalculator() {
      this.updateState();
   }

   public final void updateState() {
      this.count3 = 0;
      this.count2 = 0;
      this.count = 0;
      this.value = 1000000.0;
   }
}
