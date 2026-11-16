package cc.baritone.utils;

import cc.SettingsUtil;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import it.unimi.dsi.fastutil.doubles.DoubleOpenHashSet;
import net.minecraft.util.BlockPos;

public class AdvancedCustom implements VirtualCustom, SecondaryListener {
   public final int count;
   public final int count2;
   public final int count3;
   public final int count4;

   @Override
   public BlockPos getBlockPos() {
      return new BlockPos(this.count, this.count2, this.count3);
   }

   @Override
   public int hashCode() {
      return (int)BetterBlockPosXSYSZSBlockPos.computeTimestampByIndex(this.count, this.count2, this.count3) + this.count4;
   }

   @Override
   public double getValue() {
      int count5 = (int)Math.ceil(Math.sqrt((double)this.count4));
      DoubleOpenHashSet doubleOpenHashSet = new DoubleOpenHashSet();
      double offset = Double.POSITIVE_INFINITY;

      for (int index = -count5; index <= count5; index++) {
         for (int index2 = -count5; index2 <= count5; index2++) {
            for (int index3 = -count5; index3 <= count5; index3++) {
               double offset2 = this.computeValue(this.count + index, this.count2 + index2, this.count3 + index3);
               if (offset2 < offset && this.checkIndex(this.count + index, this.count2 + index2, this.count3 + index3)) {
                  doubleOpenHashSet.add(offset2);
               } else {
                  offset = Math.min(offset, offset2);
               }
            }
         }
      }

      double offset3 = Double.NEGATIVE_INFINITY;
      DoubleIterator iterator = doubleOpenHashSet.iterator();

      while (iterator.hasNext()) {
         double offset4 = iterator.nextDouble();
         if (offset4 < offset) {
            offset3 = Math.max(offset3, offset4);
         }
      }

      return offset3;
   }

   @Override
   public double computeValue(int count5, int count6, int count7) {
      int count8 = count5 - this.count;
      int count9 = count6 - this.count2;
      int count10 = count7 - this.count3;
      return DepthFirstResolver.computeValue((double)count8, count9, (double)count10);
   }

   @Override
   public String toString() {
      return String.format(
         "GoalNear{x=%s, y=%s, z=%s, rangeSq=%d}",
         SettingsUtil.maybeCensor(this.count),
         SettingsUtil.maybeCensor(this.count2),
         SettingsUtil.maybeCensor(this.count3),
         this.count4
      );
   }

   public AdvancedCustom(BlockPos blockPos, int count5) {
      this.count = blockPos.getX();
      this.count2 = blockPos.getY();
      this.count3 = blockPos.getZ();
      this.count4 = count5 * count5;
   }

   @Override
   public boolean checkIndex(int count5, int count6, int count7) {
      int count8 = count5 - this.count;
      int count9 = count6 - this.count2;
      int count10 = count7 - this.count3;
      return count8 * count8 + count9 * count9 + count10 * count10 <= this.count4;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         AdvancedCustom advancedCustom = (AdvancedCustom)other;
         return this.count == advancedCustom.count && this.count2 == advancedCustom.count2 && this.count3 == advancedCustom.count3 && this.count4 == advancedCustom.count4;
      } else {
         return false;
      }
   }
}
