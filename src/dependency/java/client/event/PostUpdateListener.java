package client.event;

import client.util.BiomeIDIsOutOfBoundsManager;
import net.minecraft.util.BlockPos;

public interface PostUpdateListener {
   int computeCount(BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager, BlockPos blockPos);
}
