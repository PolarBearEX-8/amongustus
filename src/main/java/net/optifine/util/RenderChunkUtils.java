package net.optifine.util;

import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.MathHelper;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class RenderChunkUtils {
   public static double getRelativeBufferSize2(RenderChunk renderChunk) {
      return getRelativeBufferSize(getCountBlocks(renderChunk));
   }

   public static double getRelativeBufferSize(int n) {
      double d = (double)n / 4096.0;
      d *= 0.995;
      double d2 = d * 2.0 - 1.0;
      d2 = MathHelper.clamp_double(d2, -1.0, 1.0);
      return (double)MathHelper.sqrt_double(1.0 - d2 * d2);
   }

   public static int getCountBlocks(RenderChunk renderChunk) {
      ExtendedBlockStorage[] blockStorageArray = renderChunk.getChunk().getBlockStorageArray();
      if (blockStorageArray == null) {
         return 0;
      } else {
         ExtendedBlockStorage extendedBlockStorage = blockStorageArray[renderChunk.getPosition().getY() >> 4];
         return extendedBlockStorage == null ? 0 : extendedBlockStorage.getBlockRefCount();
      }
   }
}
