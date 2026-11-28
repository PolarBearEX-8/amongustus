package wdl.api;

import net.minecraft.block.Block;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.BlockPos;

public interface IBlockEventListener extends IWDLMod {
   void onBlockEvent(WorldClient world, BlockPos pos, Block block, int n, int n2);
}
