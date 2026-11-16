package client.util;

import client.event.PostUpdateBroadcaster;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public final class AuxiliaryListenerHandler implements PostUpdateBroadcaster {
   @Override
   public boolean isActive() {
      return LoadingManager.getTexturesManager() == null;
   }

   @Override
   public int computeCount(IBlockState blockState, IBlockAccess iBlockAccess, BlockPos blockPos) {
      return LoadingManager.getTexturesManager() != null ? LoadingManager.getTexturesManager().computeCount(iBlockAccess, blockPos) : CoreIntArrayEntry.getCount2();
   }
}
