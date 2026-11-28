package baritone.api.cache;

import baritone.utils.ChunkPos;
import baritone.api.utils.BlockOptionalMetaLookup;
import baritone.api.utils.IPlayerContext;
import java.util.List;
import net.minecraft.block.Block;

public interface IWorldScanner {
   default List scanChunk2(IPlayerContext playerContext, List list, ChunkPos chunkPos, int n, int n2) {
      return this.scanChunk(playerContext, new BlockOptionalMetaLookup(list), chunkPos, n, n2);
   }

   int repack(IPlayerContext playerContext);

   default List scanChunkRadius2(IPlayerContext playerContext, List list, int n, int n2, int n3) {
      return this.scanChunkRadius(playerContext, new BlockOptionalMetaLookup((Block[])list.toArray(new Block[0])), n, n2, n3);
   }

   List scanChunk(IPlayerContext playerContext, BlockOptionalMetaLookup blockOptionalMetaLookup, ChunkPos chunkPos, int n, int n2);

   List scanChunkRadius(IPlayerContext playerContext, BlockOptionalMetaLookup blockOptionalMetaLookup, int n, int n2, int n3);

   int repack(IPlayerContext playerContext, int n);
}
