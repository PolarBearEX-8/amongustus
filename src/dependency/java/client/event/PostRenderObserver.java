package client.event;

import client.util.BiomeIDIsOutOfBoundsManager;
import net.minecraft.util.BlockPos;

public interface PostRenderObserver {
   int getCount2();

   int getCount();

   BlockPos getBlockPos();

   String getText();

   int getCountAsIndex();

   BiomeIDIsOutOfBoundsManager getBiomeIDIsOutOfBoundsManager();
}
