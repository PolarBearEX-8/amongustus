package client.util;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class DampenerSynchronizer extends DampenerEngine {
   @Override
   public ResourceLocationAdapter resolveResourceLocationAdapter(IBlockState blockState) {
      return new ResourceLocationAdapter((ResourceLocation)Block.iterableExtensionBridge.getObject2(blockState.getBlock()), this.resolveText(blockState.getImmutableMap()));
   }
}
