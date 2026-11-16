package client.render;

import net.minecraft.util.EnumFacing;

public final class ChunkAnimationProgress {
   public long epochMillis;
   public EnumFacing enumFacing;

   public ChunkAnimationProgress(long value, EnumFacing enumFacing2) {
      this.epochMillis = value;
      this.enumFacing = enumFacing2;
   }
}
