package net.augustus.events;

import net.augustus.events.listeners.RenderBlockListener;
import de.florianmichael.dietrichevents2.AbstractEvent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.optifine.BlockPosM;

public class EventRenderBlock extends AbstractEvent<RenderBlockListener> {
   private BlockPosM mutablePos;
   private IBlockState blockState;
   public static final int ID = 30;
   private BlockPos cachedBlockPos;

   public static IBlockState setBlockState(EventRenderBlock event, IBlockState state) {
      return event.blockState = state;
   }


   public static BlockPosM setMutablePos(EventRenderBlock event, BlockPosM blockPosM) {
      return event.mutablePos = blockPosM;
   }

   @Override
   public void call(RenderBlockListener listener) {
      listener.onRenderBlock(this);
   }

   public IBlockState getBlockState() {
      return this.blockState;
   }

   public static BlockPos setCachedBlockPos(EventRenderBlock event, BlockPos pos) {
      return event.cachedBlockPos = pos;
   }

   public BlockPos getMutablePos() {
      return this.mutablePos;
   }

   public EventRenderBlock(BlockPosM blockPosM, IBlockState state) {
      this.mutablePos = blockPosM;
      this.cachedBlockPos = null;
      this.blockState = state;
   }

   public BlockPos getImmutableBlockPos() {
      return this.cachedBlockPos != null ? this.cachedBlockPos : (this.cachedBlockPos = new BlockPos(this.mutablePos.getX(), this.mutablePos.getY(), this.mutablePos.getZ()));
   }
}
