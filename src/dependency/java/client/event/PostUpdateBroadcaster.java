package client.event;

import client.util.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface PostUpdateBroadcaster {
   int computeCount(IBlockState blockState, IBlockAccess iBlockAccess, BlockPos blockPos);

   boolean isActive();
}
