package client.util;

import java.util.Comparator;
import net.minecraft.util.MathHelper;

public class StandardComparatorAdapter implements Comparator {
   public int count;
   public int count2;
   public double value;
   public double value2;

   public int computeCount(InterpolationCollector interpolationCollector, InterpolationCollector interpolationCollector2) {
      int count3 = this.computeCount(interpolationCollector);
      int count4 = this.computeCount(interpolationCollector2);
      return count3 - count4;
   }

   @Override
   public int compare(Object entry, Object entry2) {
      return this.computeCount((InterpolationCollector)entry, (InterpolationCollector)entry2);
   }

   private int computeCount(InterpolationCollector interpolationCollector) {
      int count3 = interpolationCollector.count3 - this.count;
      int count4 = interpolationCollector.count2 - this.count2;
      int count5 = count3 * count3 + count4 * count4;
      double offset = Math.abs(MathHelper.getDouble6((double)count4, (double)count3) - this.value);
      if (offset > Math.PI) {
         offset = (Math.PI * 2) - offset;
      }

      return (int)((double)count5 * 1000.0 * this.value2 * offset * offset);
   }

   public StandardComparatorAdapter(int count3, int count4, double offset, double offset2) {
      this.count = count3;
      this.count2 = count4;
      this.value = offset;
      this.value2 = 1.0 - MathHelper.getDouble7(Math.abs(offset2) / (Math.PI / 2), 0.0, 1.0);
   }
}
