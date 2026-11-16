package client.util;

import client.event.PostUpdateObserver;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CurveSerializer implements PostUpdateObserver {
   @Override
   public RenderChunk resolveRenderChunk(World world, RenderGlobal renderGlobal, BlockPos blockPos, int count) {
      return new RenderChunk(world, renderGlobal, blockPos, count);
   }
}
