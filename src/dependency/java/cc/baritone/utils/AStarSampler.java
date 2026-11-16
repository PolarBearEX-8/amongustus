package cc.baritone.utils;

public class AStarSampler {
   public AStarSampler aStarSampler;
   public int count;
   public int count2;
   public int count3;
   public int count4;
   public double value;
   public double value2;
   public double value3;

   public AStarSampler(int x, int y, int z, SecondaryListener secondaryListener) {
      this.count = x;
      this.count2 = y;
      this.count3 = z;
   }

   public boolean isActive() {
      return this.count4 >= 0;
   }
}
