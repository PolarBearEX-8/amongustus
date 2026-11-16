package client.util;

import com.google.common.collect.Lists;
import java.util.List;

public class AuxiliaryInvalidBiomeIdManagerEntry {
   public final InvalidBiomeIdManager invalidBiomeIdManager;
   public long timestamp;
   public InternalFloatEntry internalFloatEntry = new InternalFloatEntry();
   public List list = Lists.newArrayList();

   public static InvalidBiomeIdManager resolveInvalidBiomeIdManager(AuxiliaryInvalidBiomeIdManagerEntry auxiliaryInvalidBiomeIdManagerEntry) {
      return auxiliaryInvalidBiomeIdManagerEntry.invalidBiomeIdManager;
   }

   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManager(int count, int count2, BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager) {
      BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager2 = this.resolveExtendedFloatArrayEntry(count, count2).resolveBiomeIDIsOutOfBoundsManager(count, count2);
      return biomeIDIsOutOfBoundsManager2 == null ? biomeIDIsOutOfBoundsManager : biomeIDIsOutOfBoundsManager2;
   }

   public void updateState() {
      long timestamp2 = VanillaRunnable.currentTimeMillis();
      long value = timestamp2 - this.timestamp;
      if (value > 7500L || value < 0L) {
         this.timestamp = timestamp2;

         for (int index = 0; index < this.list.size(); index++) {
            ExtendedFloatArrayEntry extendedFloatArrayEntry = (ExtendedFloatArrayEntry)this.list.get(index);
            long value2 = timestamp2 - extendedFloatArrayEntry.timestamp;
            if (value2 > 30000L || value2 < 0L) {
               this.list.remove(index--);
               long value3 = (long)extendedFloatArrayEntry.count2 & 4294967295L | ((long)extendedFloatArrayEntry.count & 4294967295L) << 32;
               this.internalFloatEntry.resolveTargetByTime(value3);
            }
         }
      }
   }

   public ExtendedFloatArrayEntry resolveExtendedFloatArrayEntry(int count, int count2) {
      int count3 = count >> 4;
      int count4 = count2 >> 4;
      long value = (long)count3 & 4294967295L | ((long)count4 & 4294967295L) << 32;
      ExtendedFloatArrayEntry extendedFloatArrayEntry = (ExtendedFloatArrayEntry)this.internalFloatEntry.resolveTarget(value);
      if (extendedFloatArrayEntry == null) {
         extendedFloatArrayEntry = new ExtendedFloatArrayEntry(this, count3, count4);
         this.internalFloatEntry.applyTarget(value, extendedFloatArrayEntry);
         this.list.add(extendedFloatArrayEntry);
      }

      extendedFloatArrayEntry.timestamp = VanillaRunnable.currentTimeMillis();
      return extendedFloatArrayEntry;
   }

   public BiomeIDIsOutOfBoundsManager[] resolveBiomeIDIsOutOfBoundsManagerA(int count, int count2) {
      return this.resolveExtendedFloatArrayEntry(count, count2).biomeIDIsOutOfBoundsManagerArray;
   }

   public AuxiliaryInvalidBiomeIdManagerEntry(InvalidBiomeIdManager invalidBiomeIdManager2) {
      this.invalidBiomeIdManager = invalidBiomeIdManager2;
   }
}
