package client.util;

import client.event.PostUpdateBroadcaster;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public final class ListenerAdapter4 implements PostUpdateBroadcaster {
   @Override
   public int computeCount(IBlockState blockState, IBlockAccess iBlockAccess, BlockPos blockPos) {
      BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = LoadingManager.resolveBiomeIDIsOutOfBoundsManager(iBlockAccess, blockPos);
      return LoadingManager.getTexturesManager15() != null && biomeIDIsOutOfBoundsManager == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager16
         ? LoadingManager.getTexturesManager15().computeCount(biomeIDIsOutOfBoundsManager, blockPos)
         : biomeIDIsOutOfBoundsManager.computeCountByBlockPos(blockPos);
   }

   @Override
   public boolean isActive() {
      return false;
   }
}
