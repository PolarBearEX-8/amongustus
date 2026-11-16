package client.util;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class SmoothTransformer {
   public final BlockPos blockPos;
   public final BlockPos blockPos2;
   public final EnumFacing enumFacing;
   public int count;
   public boolean enabled;
   public int count2;

   public int getCount() {
      return this.enumFacing.getInt2() * 2;
   }

   public int getCount2() {
      return this.count2;
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   private static EnumFacing resolveEnumFacing(int count3, int count4) {
      return count3 < 0 ? EnumFacing.WEST : (count3 > 0 ? EnumFacing.EAST : (count4 < 0 ? EnumFacing.NORTH : EnumFacing.SOUTH));
   }

   public void updateState() {
      this.count2++;
   }

   public SmoothTransformer(BlockPos blockPos3, EnumFacing enumFacing2, int count3) {
      this.blockPos = blockPos3;
      this.enumFacing = enumFacing2;
      this.blockPos2 = blockPos3.getBlockPos(enumFacing2, 2);
      this.count = count3;
   }

   public int computeCount(int count3, int count4, int count5) {
      return (int)this.blockPos.computeValue((double)count3, (double)count4, (double)count5);
   }

   public int getCount3() {
      return this.enumFacing.getInt() * 2;
   }

   public boolean matchesBlockPos(BlockPos blockPos3) {
      int count3 = blockPos3.getX() - this.blockPos.getX();
      int count4 = blockPos3.getZ() - this.blockPos.getY();
      return count3 * this.enumFacing.getInt2() + count4 * this.enumFacing.getInt() >= 0;
   }

   public int getCountAsIndex() {
      return this.count;
   }

   public BlockPos getBlockPos2() {
      return this.blockPos2;
   }

   public void setCount(int count3) {
      this.count = count3;
   }

   public SmoothTransformer(BlockPos blockPos3, int count3, int count4, int count5) {
      this(blockPos3, resolveEnumFacing(count3, count4), count5);
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   public void updateState2() {
      this.count2 = 0;
   }

   public int computeCountByBlockPos(BlockPos blockPos3) {
      return (int)this.blockPos2.getCoordinate(blockPos3);
   }

   public int computeCount(BlockPos blockPos3) {
      return (int)blockPos3.getCoordinate(this.getBlockPos());
   }
}
