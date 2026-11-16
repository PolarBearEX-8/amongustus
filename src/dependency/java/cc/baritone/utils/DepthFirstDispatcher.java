package cc.baritone.utils;

import net.minecraft.block.Block;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.BlockPos;

public interface DepthFirstDispatcher extends SpecialListener {
   void applyWorldClientByWorldClient(WorldClient world, BlockPos blockPos, Block block, int count, int count2);
}
