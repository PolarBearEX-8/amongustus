package client.util;

import client.event.PostUpdateBroadcaster;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public final class AnglePicker implements PostUpdateBroadcaster {
   @Override
   public int computeCount(IBlockState blockState, IBlockAccess iBlockAccess, BlockPos blockPos) {
      return LoadingManager.getTexturesManager7() != null ? LoadingManager.getTexturesManager7().computeCount(iBlockAccess, blockPos) : CoreIntArrayEntry.getCountAsIndex();
   }

   @Override
   public boolean isActive() {
      return LoadingManager.getTexturesManager7() == null;
   }
}
