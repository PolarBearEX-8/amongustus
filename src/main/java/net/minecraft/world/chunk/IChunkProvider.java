package net.minecraft.world.chunk;

import java.util.List;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;

public interface IChunkProvider {
   void saveExtraData();

   boolean canSave();

   Chunk provideChunk(int n, int n2);

   void recreateStructures(Chunk chunk, int n, int n2);

   boolean unloadQueuedChunks();

   boolean populateChunk(IChunkProvider chunkProvider, Chunk chunk, int n, int n2);

   boolean saveChunks(boolean flag, IProgressUpdate progressUpdate);

   String makeString();

   BlockPos getStrongholdGen(World world, String str, BlockPos pos);

   int getLoadedChunkCount();

   void populate(IChunkProvider chunkProvider, int n, int n2);

   List getPossibleCreatures(EnumCreatureType enumCreatureType, BlockPos pos);

   boolean chunkExists(int n, int n2);

   Chunk provideChunk(BlockPos pos);
}
