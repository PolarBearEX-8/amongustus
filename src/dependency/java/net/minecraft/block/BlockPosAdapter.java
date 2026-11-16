package net.minecraft.block;

import net.minecraft.util.BlockPos;

public class BlockPosAdapter extends BlockPos {
   public final int count9;

   public int getCount9() {
      return this.count9;
   }

   public BlockPosAdapter(BlockPos blockPos, int count) {
      super(blockPos.getX(), blockPos.getY(), blockPos.getZ());
      this.count9 = count;
   }
}
