package net.minecraft.block;

import client.util.WorldServerEntry;
import net.minecraft.util.BlockPos;

public class InternalBlockPosAdapter extends BlockPos {
   public long timestamp4;
   public final WorldServerEntry worldServerEntry;

   public InternalBlockPosAdapter(WorldServerEntry worldServerEntry2, BlockPos blockPos, long value) {
      super(blockPos.getX(), blockPos.getY(), blockPos.getZ());
      this.worldServerEntry = worldServerEntry2;
      this.timestamp4 = value;
   }
}
