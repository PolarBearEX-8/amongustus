package net.augustus.events.listeners;

import net.augustus.events.EventBlockChange;
import net.augustus.events.EventManager;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;

public interface BlockChangeListener {
   static void onBlockChange(int n, int n2, int n3, IBlockState state, BlockPos pos) {
      EventManager.getEventBus().post(55, new EventBlockChange(n, n2, n3, state, pos));
   }

   void onBlockChange(EventBlockChange event);
}
