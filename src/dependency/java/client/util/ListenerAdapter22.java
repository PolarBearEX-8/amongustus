package client.util;

import client.event.PostUpdateListener;
import net.minecraft.util.BlockPos;

public final class ListenerAdapter22 implements PostUpdateListener {
   @Override
   public int computeCount(BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager, BlockPos blockPos) {
      return biomeIDIsOutOfBoundsManager.computeCountByBlockPos(blockPos);
   }
}
