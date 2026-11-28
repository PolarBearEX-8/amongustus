package net.optifine.shaders;

import java.util.Iterator;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;
import net.optifine.BlockPosM;

public class IteratorRenderChunks implements Iterator {
   private ViewFrustum viewFrustum;
   private Iterator3d iterator3d;
   private BlockPosM posBlock = new BlockPosM(0, 0, 0);

   public IteratorRenderChunks(ViewFrustum viewFrustum2, BlockPos pos, BlockPos pos2, int n, int n2) {
      this.viewFrustum = viewFrustum2;
      this.iterator3d = new Iterator3d(pos, pos2, n, n2);
   }

   @Override
   public Object next() {
      return this.nextChunk();
   }

   public RenderChunk nextChunk() {
      BlockPos pos = this.iterator3d.nextPos();
      this.posBlock.setXyz(pos.getX() << 4, pos.getY() << 4, pos.getZ() << 4);
      return this.viewFrustum.getRenderChunk(this.posBlock);
   }

   @Override
   public void remove() {
      throw new RuntimeException("Not implemented");
   }

   @Override
   public boolean hasNext() {
      return this.iterator3d.hasNext();
   }
}
