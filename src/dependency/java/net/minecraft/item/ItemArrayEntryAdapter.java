package net.minecraft.item;

import client.setting.BlockSolidType;
import client.util.VectorMatcher;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemArrayEntryAdapter extends RenderChunk {
   public final int count6 = VectorMatcher.computeCount(BlockSolidType.values().length);

   @Override
   public void updateState6() {
      super.updateState6();
      VectorMatcher.glDeleteLists(this.count6, BlockSolidType.values().length);
   }

   public int computeCount(BlockSolidType blockSolidType, IronShard ironShard) {
      return !ironShard.ordinal3(blockSolidType) ? this.count6 + blockSolidType.ordinal() : -1;
   }

   public ItemArrayEntryAdapter(World world, RenderGlobal renderGlobal, BlockPos blockPos, int count) {
      super(world, renderGlobal, blockPos, count);
   }
}
