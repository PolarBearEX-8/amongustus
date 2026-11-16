package client.event;

import client.util.IBlockState;
import net.minecraft.block.ExtendedBlockPosAdapter;
import net.minecraft.util.BlockPos;

public interface BlockActionListener {
   void onBlockAction(EventBlockAction event);

   static void onBlockAction(ExtendedBlockPosAdapter extendedBlockPosAdapter, IBlockState blockState, EventBlockAction event) {
      EventBlockAction.onBlockAction(event, extendedBlockPosAdapter);
      EventBlockAction.onBlockAction(event, blockState);
      EventBlockAction.onBlockAction(event, (BlockPos)null);
      EventBusManager.getDietrichEvents2().post(30, event);
   }
}
