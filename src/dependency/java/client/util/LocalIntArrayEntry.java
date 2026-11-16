package client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.item.GoldenLeggings;
import net.minecraft.util.EnumFacing;

public class LocalIntArrayEntry {
   public final int count;
   public int[] countArray;
   public EnumFacing enumFacing;
   public ServiceStringEntry serviceStringEntry;
   public int[] countArray2 = null;
   public ActiveFloatEntry activeFloatEntry;
   public boolean enabled;
   public LocalIntArrayEntry localIntArrayEntry;

   public int[] getIndexArrayAsIndexArray() {
      if (this.countArray2 == null) {
         this.countArray2 = resolveIndexArray(this.getIndexArray(), this.getServiceStringEntry());
      }

      return this.countArray2;
   }

   public int[] getIndexArray() {
      this.updateState();
      return this.countArray;
   }

   private static int[] resolveIndexArray(int[] countArray3, ServiceStringEntry serviceStringEntry2) {
      int[] countArray4 = (int[])countArray3.clone();
      int count2 = countArray4.length / 4;

      for (int index = 0; index < 4; index++) {
         int count3 = index * count2;
         float factor = Float.intBitsToFloat(countArray4[count3 + 4]);
         float factor2 = Float.intBitsToFloat(countArray4[count3 + 4 + 1]);
         float factor3 = serviceStringEntry2.computeFactorByFloat(factor);
         float factor4 = serviceStringEntry2.computeFactor(factor2);
         countArray4[count3 + 4] = Float.floatToRawIntBits(factor3);
         countArray4[count3 + 4 + 1] = Float.floatToRawIntBits(factor4);
      }

      return countArray4;
   }

   public EnumFacing getEnumFacing() {
      if (this.enumFacing == null) {
         this.enumFacing = SystemFloatEntry.resolveEnumFacing(this.getIndexArray());
      }

      return this.enumFacing;
   }

   public ActiveFloatEntry getActiveFloatEntry() {
      if (this.activeFloatEntry == null) {
         this.activeFloatEntry = new ActiveFloatEntry(this.getIndexArray());
      }

      return this.activeFloatEntry;
   }

   public double getValue() {
      ActiveFloatEntry activeFloatEntry2 = this.getActiveFloatEntry();
      return (double)((activeFloatEntry2.getFactor2() + activeFloatEntry2.getFactor6()) / 2.0F);
   }

   public void updateState() {
      if (BuildManager.canExecute()) {
         if (this.countArray.length == 28) {
            this.countArray = resolveIndexArray(this.countArray);
         }
      } else if (this.countArray.length == 56) {
         this.countArray = resolveIndexArrayByIndexArray(this.countArray);
      }
   }

   private static int[] resolveIndexArray(int[] countArray3) {
      int count2 = countArray3.length / 4;
      int count3 = count2 * 2;
      int[] countArray4 = new int[count3 * 4];

      for (int index = 0; index < 4; index++) {
         System.arraycopy(countArray3, index * count2, countArray4, index * count3, count2);
      }

      return countArray4;
   }

   public double getValueAsDouble() {
      ActiveFloatEntry activeFloatEntry2 = this.getActiveFloatEntry();
      return (double)((activeFloatEntry2.getFactor4() + activeFloatEntry2.getFactor3()) / 2.0F);
   }

   public boolean isActive2() {
      return this.count != -1;
   }

   private static int[] resolveIndexArrayByIndexArray(int[] countArray3) {
      int count2 = countArray3.length / 4;
      int count3 = count2 / 2;
      int[] countArray4 = new int[count3 * 4];

      for (int index = 0; index < 4; index++) {
         System.arraycopy(countArray3, index * count2, countArray4, index * count3, count3);
      }

      return countArray4;
   }

   public boolean isActive3() {
      return this.isActive() && this.isActiveAsFlag();
   }

   public LocalIntArrayEntry getLocalIntArrayEntry() {
      if (this.enabled) {
         return this.localIntArrayEntry;
      } else {
         if (this.localIntArrayEntry == null && this.serviceStringEntry != null && this.serviceStringEntry.serviceStringEntry4 != null) {
            this.localIntArrayEntry = new GoldenLeggings(this, this.serviceStringEntry.serviceStringEntry4);
         }

         this.enabled = true;
         return this.localIntArrayEntry;
      }
   }

   public ServiceStringEntry getServiceStringEntry() {
      if (this.serviceStringEntry == null) {
         this.serviceStringEntry = resolveServiceStringEntry(this.getIndexArray());
      }

      return this.serviceStringEntry;
   }

   public boolean isActiveAsFlag() {
      return this.getActiveFloatEntry().matchesEnumFacing(this.enumFacing);
   }

   private static ServiceStringEntry resolveServiceStringEntry(int[] countArray3) {
      float factor = 1.0F;
      float factor2 = 1.0F;
      float factor3 = 0.0F;
      float factor4 = 0.0F;
      int count2 = countArray3.length / 4;

      for (int index = 0; index < 4; index++) {
         int count3 = index * count2;
         float factor5 = Float.intBitsToFloat(countArray3[count3 + 4]);
         float factor6 = Float.intBitsToFloat(countArray3[count3 + 4 + 1]);
         factor = Math.min(factor, factor5);
         factor2 = Math.min(factor2, factor6);
         factor3 = Math.max(factor3, factor5);
         factor4 = Math.max(factor4, factor6);
      }

      float factor7 = (factor + factor3) / 2.0F;
      float factor8 = (factor2 + factor4) / 2.0F;
      return Minecraft.getMinecraft().getListenerAdapter27().resolveServiceStringEntry((double)factor7, (double)factor8);
   }

   public int getCount() {
      return this.count;
   }

   public boolean isActive() {
      return this.getActiveFloatEntry().matchesEnumFacingByEnumFacing(this.enumFacing);
   }

   public float getFactor() {
      ActiveFloatEntry activeFloatEntry2 = this.getActiveFloatEntry();
      return (activeFloatEntry2.getFactor() + activeFloatEntry2.getFactor5()) / 2.0F;
   }

   public LocalIntArrayEntry(int[] countArray3, int count2, EnumFacing enumFacing2, ServiceStringEntry serviceStringEntry2) {
      this.countArray = countArray3;
      this.count = count2;
      this.enumFacing = enumFacing2;
      this.serviceStringEntry = serviceStringEntry2;
      this.updateState();
   }

   public LocalIntArrayEntry(int[] countArray3, int count2, EnumFacing enumFacing2) {
      this.countArray = countArray3;
      this.count = count2;
      this.enumFacing = enumFacing2;
      this.updateState();
   }

   @Override
   public String toString() {
      return "vertex: " + this.countArray.length / 7 + ", tint: " + this.count + ", facing: " + this.enumFacing + ", sprite: " + this.serviceStringEntry;
   }
}
