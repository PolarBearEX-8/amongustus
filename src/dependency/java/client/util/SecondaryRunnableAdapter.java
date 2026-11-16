package client.util;

import client.render.WorldRenderer;
import client.setting.BlockSolidType;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.item.IronShard;

public class SecondaryRunnableAdapter implements Runnable {
   public final BlockSolidType blockSolidType;
   public final WorldRenderer worldRenderer;
   public final RenderChunk renderChunk;
   public final IronShard ironShard;
   public final TertiaryLoggerHolder tertiaryLoggerHolder;

   public SecondaryRunnableAdapter(TertiaryLoggerHolder tertiaryLoggerHolder2, BlockSolidType blockSolidType2, WorldRenderer worldRenderer2, RenderChunk renderChunk2, IronShard ironShard2) {
      this.tertiaryLoggerHolder = tertiaryLoggerHolder2;
      this.blockSolidType = blockSolidType2;
      this.worldRenderer = worldRenderer2;
      this.renderChunk = renderChunk2;
      this.ironShard = ironShard2;
   }

   @Override
   public void run() {
      this.tertiaryLoggerHolder.resolveListenableFuture(this.blockSolidType, this.worldRenderer, this.renderChunk, this.ironShard);
   }
}
