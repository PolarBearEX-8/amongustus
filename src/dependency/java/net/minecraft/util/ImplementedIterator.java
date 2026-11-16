package net.minecraft.util;

import client.render.RenderGlobalEntry;
import java.util.Iterator;
import net.minecraft.block.ExtendedBlockPosAdapter;
import net.minecraft.client.renderer.chunk.RenderChunk;

public class ImplementedIterator implements Iterator {
   public RenderGlobalEntry renderGlobalEntry;
   public SupportedIterator supportedIterator;
   public ExtendedBlockPosAdapter extendedBlockPosAdapter = new ExtendedBlockPosAdapter(0, 0, 0);

   public ImplementedIterator(RenderGlobalEntry renderGlobalEntry2, BlockPos blockPos, BlockPos blockPos2, int count, int count2) {
      this.renderGlobalEntry = renderGlobalEntry2;
      this.supportedIterator = new SupportedIterator(blockPos, blockPos2, count, count2);
   }

   @Override
   public void remove() {
      throw new RuntimeException("Not implemented");
   }

   @Override
   public boolean hasNext() {
      return this.supportedIterator.hasNext();
   }

   public RenderChunk getRenderChunk() {
      BlockPos blockPos = this.supportedIterator.getBlockPos();
      this.extendedBlockPosAdapter.applyIndex(blockPos.getX() << 4, blockPos.getY() << 4, blockPos.getZ() << 4);
      return this.renderGlobalEntry.resolveRenderChunk(this.extendedBlockPosAdapter);
   }

   @Override
   public Object next() {
      return this.getRenderChunk();
   }
}
