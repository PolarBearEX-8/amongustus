package net.minecraft.item;

import client.util.LocalIntArrayEntry;
import client.util.ModularIntArrayEntry;
import client.util.ServiceStringEntry;
import client.util.SystemFloatEntry;
import java.util.Arrays;

public class GoldenLeggings extends LocalIntArrayEntry {
   public final ServiceStringEntry serviceStringEntry2;

   private void updateState2() {
      for (int index = 0; index < 4; index++) {
         this.applyIndex(index);
      }
   }

   private void applyIndex(int count) {
      int count2 = this.countArray.length / 4 * count;
      float factor = Float.intBitsToFloat(this.countArray[count2]);
      float factor2 = Float.intBitsToFloat(this.countArray[count2 + 1]);
      float factor3 = Float.intBitsToFloat(this.countArray[count2 + 2]);
      float factor4 = 0.0F;
      float factor5 = 0.0F;
      switch (ModularIntArrayEntry.countArray[this.enumFacing.ordinal()]) {
         case 1:
            factor4 = factor * 16.0F;
            factor5 = (1.0F - factor3) * 16.0F;
            break;
         case 2:
            factor4 = factor * 16.0F;
            factor5 = factor3 * 16.0F;
            break;
         case 3:
            factor4 = (1.0F - factor) * 16.0F;
            factor5 = (1.0F - factor2) * 16.0F;
            break;
         case 4:
            factor4 = factor * 16.0F;
            factor5 = (1.0F - factor2) * 16.0F;
            break;
         case 5:
            factor4 = factor3 * 16.0F;
            factor5 = (1.0F - factor2) * 16.0F;
            break;
         case 6:
            factor4 = (1.0F - factor3) * 16.0F;
            factor5 = (1.0F - factor2) * 16.0F;
      }

      this.countArray[count2 + 4] = Float.floatToRawIntBits(this.serviceStringEntry2.computeFactorByDouble((double)factor4));
      this.countArray[count2 + 4 + 1] = Float.floatToRawIntBits(this.serviceStringEntry2.computeFactor((double)factor5));
   }

   public GoldenLeggings(LocalIntArrayEntry localIntArrayEntry, ServiceStringEntry serviceStringEntry) {
      super(Arrays.copyOf(localIntArrayEntry.getIndexArray(), localIntArrayEntry.getIndexArray().length), localIntArrayEntry.count, SystemFloatEntry.resolveEnumFacing(localIntArrayEntry.getIndexArray()));
      this.serviceStringEntry2 = serviceStringEntry;
      this.updateState2();
      this.updateState();
   }
}
