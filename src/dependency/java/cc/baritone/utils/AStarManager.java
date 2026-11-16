package cc.baritone.utils;

import cc.SettingsUtil;
import net.minecraft.util.BlockPos;

public class AStarManager implements SecondaryListener, VirtualCustom {
   public final int count2;
   public final int count;
   public final int count3;

   @Override
   public String toString() {
      return String.format(
         "GoalGetToBlock{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.count2), SettingsUtil.maybeCensor(this.count), SettingsUtil.maybeCensor(this.count3)
      );
   }

   @Override
   public boolean checkIndex(int count4, int count5, int count6) {
      int count7 = count4 - this.count2;
      int count8 = count5 - this.count;
      int count9 = count6 - this.count3;
      return Math.abs(count7) + Math.abs(count8 < 0 ? count8 + 1 : count8) + Math.abs(count9) <= 1;
   }

   @Override
   public int hashCode() {
      return (int)BetterBlockPosXSYSZSBlockPos.computeTimestampByIndex(this.count2, this.count, this.count3) * -49639096;
   }

   public AStarManager(BlockPos blockPos) {
      this.count2 = blockPos.getX();
      this.count = blockPos.getY();
      this.count3 = blockPos.getZ();
   }

   @Override
   public double computeValue(int count4, int count5, int count6) {
      int count7 = count4 - this.count2;
      int count8 = count5 - this.count;
      int count9 = count6 - this.count3;
      return DepthFirstResolver.computeValue((double)count7, count8 < 0 ? count8 + 1 : count8, (double)count9);
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         AStarManager aStarManager = (AStarManager)other;
         return this.count2 == aStarManager.count2 && this.count == aStarManager.count && this.count3 == aStarManager.count3;
      } else {
         return false;
      }
   }

   @Override
   public BlockPos getBlockPos() {
      return new BlockPos(this.count2, this.count, this.count3);
   }
}
