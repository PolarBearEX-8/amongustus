package cc.baritone.utils;

import cc.SettingsUtil;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import it.unimi.dsi.fastutil.doubles.DoubleOpenHashSet;
import java.util.Arrays;
import java.util.Objects;
import net.minecraft.util.BlockPos;

public class GoalRunAwayFromListener implements SecondaryListener {
   public final BlockPos[] blockPosArray;
   public final int count;
   public final Integer integer;

   @Override
   public double computeValue(int count2, int count3, int count4) {
      double offset = Double.MAX_VALUE;

      for (BlockPos blockPos : this.blockPosArray) {
         double offset2 = DiagonalEvaluator.computeValue((double)(blockPos.getX() - count2), (double)(blockPos.getZ() - count4));
         if (offset2 < offset) {
            offset = offset2;
         }
      }

      double offset3 = -offset;
      if (this.integer != null) {
         offset3 = offset3 * 0.6 + BreadthFirstInspector.computeValue(this.integer, count3) * 1.5;
      }

      return offset3;
   }

   @Override
   public boolean checkIndex(int count2, int count3, int count4) {
      if (this.integer != null && this.integer != count3) {
         return false;
      } else {
         for (BlockPos blockPos : this.blockPosArray) {
            int count5 = count2 - blockPos.getX();
            int count6 = count4 - blockPos.getZ();
            if (count5 * count5 + count6 * count6 < this.count) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public double getValue() {
      int count2 = (int)Math.ceil(Math.sqrt((double)this.count));
      int count3 = Integer.MAX_VALUE;
      int count4 = Integer.MAX_VALUE;
      int count5 = Integer.MAX_VALUE;
      int count6 = Integer.MIN_VALUE;
      int count7 = Integer.MIN_VALUE;
      int count8 = Integer.MIN_VALUE;

      for (BlockPos blockPos : this.blockPosArray) {
         count3 = Math.min(count3, blockPos.getX() - count2);
         count4 = Math.min(count4, blockPos.getY() - count2);
         count5 = Math.min(count5, blockPos.getZ() - count2);
         count6 = Math.max(count3, blockPos.getX() + count2);
         count7 = Math.max(count4, blockPos.getY() + count2);
         count8 = Math.max(count5, blockPos.getZ() + count2);
      }

      DoubleOpenHashSet doubleOpenHashSet = new DoubleOpenHashSet();

      double offset;
      for (offset = Double.POSITIVE_INFINITY; count3 <= count6; count3++) {
         for (int index = count4; index <= count7; index++) {
            for (int index2 = count5; index2 <= count8; index2++) {
               double offset2 = this.computeValue(count3, index, index2);
               if (offset2 < offset && this.checkIndex(count3, index, index2)) {
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

   public GoalRunAwayFromListener(double offset, Integer count2, BlockPos[] blockPosArray2) {
      if (blockPosArray2.length == 0) {
         throw new IllegalArgumentException();
      } else {
         this.blockPosArray = blockPosArray2;
         this.count = (int)(offset * offset);
         this.integer = count2;
      }
   }

   public GoalRunAwayFromListener(double offset, BlockPos[] blockPosArray2) {
      this(offset, null, blockPosArray2);
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         GoalRunAwayFromListener goalRunAwayFromListener = (GoalRunAwayFromListener)other;
         return this.count == goalRunAwayFromListener.count
            && Arrays.equals((Object[])this.blockPosArray, (Object[])goalRunAwayFromListener.blockPosArray)
            && Objects.equals(this.integer, goalRunAwayFromListener.integer);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return this.integer != null
         ? String.format("GoalRunAwayFromMaintainY y=%s, %s", SettingsUtil.maybeCensor(this.integer), Arrays.asList(this.blockPosArray))
         : "GoalRunAwayFrom" + Arrays.<BlockPos>asList(this.blockPosArray);
   }

   @Override
   public int hashCode() {
      return (Arrays.hashCode((Object[])this.blockPosArray) * 1196803141 + this.count) * -2053788840 + this.integer;
   }
}
