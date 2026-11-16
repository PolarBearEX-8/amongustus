package client.util;

import client.event.PostUpdateObserver;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.item.ItemArrayEntryAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ListenerController implements PostUpdateObserver {
   @Override
   public RenderChunk resolveRenderChunk(World world, RenderGlobal renderGlobal, BlockPos blockPos, int count) {
      return new ItemArrayEntryAdapter(world, renderGlobal, blockPos, count);
   }
}
