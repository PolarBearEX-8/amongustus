package client.util;

import client.event.PostUpdateBroadcaster;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public final class SharedClientManager implements PostUpdateBroadcaster {
   @Override
   public int computeCount(IBlockState blockState, IBlockAccess iBlockAccess, BlockPos blockPos) {
      BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = LoadingManager.resolveBiomeIDIsOutOfBoundsManager(iBlockAccess, blockPos);
      return LoadingManager.getTexturesManager3() != null ? LoadingManager.getTexturesManager3().computeCount(biomeIDIsOutOfBoundsManager, blockPos) : biomeIDIsOutOfBoundsManager.count5;
   }

   @Override
   public boolean isActive() {
      return false;
   }
}
