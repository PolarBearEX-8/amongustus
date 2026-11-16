package net.minecraft.item;

import client.util.BuildManager;
import client.util.DimensionEntry;
import client.util.FloatEntryRegistry;
import net.minecraft.client.entity.AbstractClientPlayer;

public class SpecialItemArrayEntry {
   public DimensionEntry[] dimensionEntryArray = new DimensionEntry[0];
   public boolean enabled = false;

   public DimensionEntry[] getDimensionEntryArray() {
      return this.dimensionEntryArray;
   }

   public void applyDimensionEntry(DimensionEntry dimensionEntry) {
      this.dimensionEntryArray = (DimensionEntry[])BuildManager.resolveTargetArrayByTargetArray(this.dimensionEntryArray, dimensionEntry);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   public void applyFloatEntryRegistryByFloatEntryRegist(FloatEntryRegistry floatEntryRegistry, AbstractClientPlayer player, float factor, float factor2) {
      if (this.enabled) {
         for (int index = 0; index < this.dimensionEntryArray.length; index++) {
            this.dimensionEntryArray[index].applyFloatEntryRegistryByFloatEntryRegist(floatEntryRegistry, player, factor, factor2);
         }
      }
   }
}
