package cc.baritone.utils;

import cc.SettingsUtil;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class GenericCustom implements SecondaryListener {
   public final int count;
   public final int count2;
   public final int count3;
   public final int count4;
   public final int count5;

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         GenericCustom genericCustom = (GenericCustom)other;
         return this.count == genericCustom.count
            && this.count2 == genericCustom.count2
            && this.count3 == genericCustom.count3
            && this.count4 == genericCustom.count4
            && this.count5 == genericCustom.count5;
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return ((int)BetterBlockPosXSYSZSBlockPos.computeTimestampByIndex(this.count, this.count2, this.count3) * 630627507 + this.count4) * -283028380
         + this.count5;
   }

   public GenericCustom(BlockPos blockPos, EnumFacing enumFacing) {
      this.count = blockPos.getX();
      this.count2 = blockPos.getY();
      this.count3 = blockPos.getZ();
      this.count4 = enumFacing.getInt2();
      this.count5 = enumFacing.getInt();
      if (this.count4 == 0 && this.count5 == 0) {
         throw new IllegalArgumentException(enumFacing + "");
      }
   }

   @Override
   public boolean checkIndex(int count6, int count7, int count8) {
      return false;
   }

   @Override
   public String toString() {
      return String.format(
         "GoalStrictDirection{x=%s, y=%s, z=%s, dx=%s, dz=%s}",
         SettingsUtil.maybeCensor(this.count),
         SettingsUtil.maybeCensor(this.count2),
         SettingsUtil.maybeCensor(this.count3),
         SettingsUtil.maybeCensor(this.count4),
         SettingsUtil.maybeCensor(this.count5)
      );
   }

   @Override
   public double computeValue(int count6, int count7, int count8) {
      int count9 = (count6 - this.count) * this.count4 + (count8 - this.count3) * this.count5;
      int count10 = Math.abs((count6 - this.count) * this.count5) + Math.abs((count8 - this.count3) * this.count4);
      int count11 = Math.abs(count7 - this.count2);
      return (double)(-count9 * 100) + (double)(count10 * 1000) + (double)(count11 * 1000);
   }

   @Override
   public double getValue() {
      return Double.NEGATIVE_INFINITY;
   }
}
