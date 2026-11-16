package client.util;

import client.event.PostUpdateBroadcaster;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public final class AngleSerializer implements PostUpdateBroadcaster {
   @Override
   public int computeCount(IBlockState blockState, IBlockAccess iBlockAccess, BlockPos blockPos) {
      BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager = LoadingManager.resolveBiomeIDIsOutOfBoundsManager(iBlockAccess, blockPos);
      return LoadingManager.getTexturesManager14() != null && biomeIDIsOutOfBoundsManager == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager16
         ? LoadingManager.getTexturesManager14().computeCount(biomeIDIsOutOfBoundsManager, blockPos)
         : biomeIDIsOutOfBoundsManager.computeCount(blockPos);
   }

   @Override
   public boolean isActive() {
      return false;
   }
}
