package client.util;

import net.minecraft.item.DynamicItemEntry;

public class IntArrayEntry12Entry {
   public final CustomIntArrayHolder customIntArrayHolder;
   public final LongArrayHolder longArrayHolder = new LongArrayHolder();
   public final RayTracePicker rayTracePicker;
   public final AuxiliaryLongArrayEntry auxiliaryLongArrayEntry = new AuxiliaryLongArrayEntry();
   public final VectorEstimator vectorEstimator = new VectorEstimator();

   public void applyTimeArray(long[][] valueMatrix) {
      int count = 0;
      count = 0;

      do {
         this.longArrayHolder.applyTimeArrayByTimeArray(valueMatrix);
         this.customIntArrayHolder.applyTimeArray(valueMatrix);
         this.rayTracePicker.applyTimeArray(valueMatrix);
         this.vectorEstimator.applyTimeArray(valueMatrix);
         this.auxiliaryLongArrayEntry.applyTimeArray(valueMatrix, count);
      } while (++count < 24);
   }

   public IntArrayEntry12Entry() {
      this.rayTracePicker = new RayTracePicker();
      this.customIntArrayHolder = new CustomIntArrayHolder();
   }
}
