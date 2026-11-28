package net.minecraft.world.chunk.storage;

import java.io.IOException;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public interface IChunkLoader {
   void saveExtraChunkData(World world, Chunk chunk) throws IOException;

   void saveExtraData();

   void saveChunk(World world, Chunk chunk) throws MinecraftException, IOException;

   void chunkTick();

   Chunk loadChunk(World world, int n, int n2) throws IOException;
}
