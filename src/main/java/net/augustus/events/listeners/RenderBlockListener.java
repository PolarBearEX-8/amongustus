package net.augustus.events.listeners;

import net.augustus.events.EventManager;
import net.augustus.events.EventRenderBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.optifine.BlockPosM;

public interface RenderBlockListener {
   void onRenderBlock(EventRenderBlock event);

   static void onRenderBlock(BlockPosM blockPosM, IBlockState state, EventRenderBlock event) {
      EventRenderBlock.setMutablePos(event, blockPosM);
      EventRenderBlock.setBlockState(event, state);
      EventRenderBlock.setCachedBlockPos(event, (BlockPos)null);
      EventManager.getEventBus().post(30, event);
   }
}
