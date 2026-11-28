package net.augustus.modules.modes.clicking;

import net.augustus.utils.TuplePair;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.utils.TimeHelper;

public class SimpleClickPatternSimulator {
   private long nextClickTimeMs;
   private int doubleClickCycleCount;
   private long simulatedTimeMs = 0L;
   private long releaseTimeMs;

   public TuplePair simulateTick(int n, double d, double d2, boolean flag, boolean flag2, double d3) {
      if (n <= 0) {
         return new TuplePair(0, false);
      } else {
         int n2 = 1000 / n;
         int n3 = 0;
         int n4 = n2 / 2;

         while (this.simulatedTimeMs >= this.nextClickTimeMs) {
            if (flag) {
               int n5 = (int)(this.simulatedTimeMs - this.nextClickTimeMs);
               if (n5 > n4) {
                  double d4 = (double)n5;
                  double d5 = (double)n5;
                  double d6 = 7.0;
                  double d7 = 6.0;
                  n5 = (int)(d4 - d5 / DefaultRandomGenerator.instance.nextDouble(d7, d6));
                  n2 = n5;
                  this.doubleClickCycleCount++;
               } else if (flag2 && this.doubleClickCycleCount % 4 == 0) {
                  n2 = (int)((double)n2 * d3);
               }
            }

            int n6 = (int)(((double)n2) * DefaultRandomGenerator.instance.nextDouble(d, d2));
            n3++;
            this.releaseTimeMs = this.nextClickTimeMs;
            this.releaseTimeMs += (long)n6;
            this.nextClickTimeMs += (long)n2;
         }

         boolean flag3 = this.releaseTimeMs > this.simulatedTimeMs;
         this.simulatedTimeMs += 50L;
         return new TuplePair(n3, flag3);
      }
   }

   public SimpleClickPatternSimulator() {
      this.nextClickTimeMs = 20L;
      this.releaseTimeMs = 0L;
      this.doubleClickCycleCount = 0;
   }
}
