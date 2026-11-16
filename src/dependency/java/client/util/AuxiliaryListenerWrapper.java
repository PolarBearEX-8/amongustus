package client.util;

import client.event.PostUpdateListener;
import net.minecraft.util.BlockPos;

public final class AuxiliaryListenerWrapper implements PostUpdateListener {
   @Override
   public int computeCount(BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager, BlockPos blockPos) {
      return biomeIDIsOutOfBoundsManager.count5;
   }
}
