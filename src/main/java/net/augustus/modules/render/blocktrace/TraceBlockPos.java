package net.augustus.modules.render.blocktrace;

import net.minecraft.util.BlockPos;

public class TraceBlockPos {
   private final BlockPos blockPos;

   public TraceBlockPos(BlockPos pos) {
      this.blockPos = pos;
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }
}
