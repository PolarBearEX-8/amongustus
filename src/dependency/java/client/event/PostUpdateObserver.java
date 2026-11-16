package client.event;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public interface PostUpdateObserver {
   RenderChunk resolveRenderChunk(World world, RenderGlobal renderGlobal, BlockPos blockPos, int count);
}
