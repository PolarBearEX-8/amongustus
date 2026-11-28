package baritone.api.event.events;

import baritone.utils.ChunkPos;
import java.util.List;

public final class BlockChangeEvent {
   private final ChunkPos chunkPos;
   private final List blocks;

   public ChunkPos getChunkPos() {
      return this.chunkPos;
   }

   public BlockChangeEvent(ChunkPos chunkPos2, List list) {
      this.chunkPos = chunkPos2;
      this.blocks = list;
   }

   public List getBlocks() {
      return this.blocks;
   }
}
