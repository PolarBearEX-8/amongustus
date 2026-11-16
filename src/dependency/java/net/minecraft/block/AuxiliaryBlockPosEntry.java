package net.minecraft.block;

import net.minecraft.util.BlockPos;

public class AuxiliaryBlockPosEntry {
   public final int count3;
   public final BlockPos blockPos;
   public int count;
   public int count2;

   public void setCount2(int count4) {
      this.count2 = count4;
   }

   public void applyIndex(int count4) {
      int count5 = count4;
      if (count4 > 10) {
         count5 = 10;
      }

      this.count = count5;
   }

   public int getCount() {
      return this.count;
   }

   public int getCount2() {
      return this.count2;
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public AuxiliaryBlockPosEntry(int count4, BlockPos blockPos2) {
      this.count3 = count4;
      this.blockPos = blockPos2;
   }
}
