package net.minecraft.client.multiplayer;

import com.google.common.collect.Lists;
import java.util.List;
import net.augustus.events.listeners.ChunkUnloadListener;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraft.world.chunk.IChunkProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChunkProviderClient implements IChunkProvider {
   private static final Logger LOGGER = LogManager.getLogger();
   public Chunk blankChunk;
   public LongHashMap chunkMapping = new LongHashMap();
   private List chunkListing = Lists.newArrayList();
   private World worldObj;

   @Override
   public boolean populateChunk(IChunkProvider chunkProvider, Chunk chunk, int n, int n2) {
      return false;
   }

   public ChunkProviderClient(World world) {
      this.blankChunk = new EmptyChunk(world, 0, 0);
      this.worldObj = world;
   }

   @Override
   public List getPossibleCreatures(EnumCreatureType enumCreatureType, BlockPos pos) {
      return null;
   }

   @Override
   public boolean chunkExists(int n, int n2) {
      return true;
   }

   @Override
   public BlockPos getStrongholdGen(World world, String str, BlockPos pos) {
      return null;
   }

   public Chunk loadChunk(int n, int n2) {
      Chunk chunk = new Chunk(this.worldObj, n, n2);
      this.chunkMapping.add(ChunkCoordIntPair.chunkXZ2Int(n, n2), chunk);
      this.chunkListing.add(chunk);
      chunk.setChunkLoaded(true);
      return chunk;
   }

   @Override
   public boolean saveChunks(boolean flag, IProgressUpdate progressUpdate) {
      return true;
   }

   @Override
   public boolean canSave() {
      return false;
   }

   public void unloadChunk(int n, int n2) {
      Chunk chunk = this.provideChunk(n, n2);
      if (!chunk.isEmpty()) {
         chunk.onChunkUnload();
      }

      ChunkUnloadListener.postChunkUnload(ChunkCoordIntPair.chunkXZ2Int(n, n2));
      this.chunkMapping.remove(ChunkCoordIntPair.chunkXZ2Int(n, n2));
      this.chunkListing.remove(chunk);
   }

   @Override
   public void recreateStructures(Chunk chunk, int n, int n2) {
   }

   @Override
   public Chunk provideChunk(int n, int n2) {
      Chunk valueByKey = (Chunk)this.chunkMapping.getValueByKey(ChunkCoordIntPair.chunkXZ2Int(n, n2));
      return valueByKey == null ? this.blankChunk : valueByKey;
   }

   @Override
   public void populate(IChunkProvider chunkProvider, int n, int n2) {
   }

   @Override
   public void saveExtraData() {
   }

   @Override
   public Chunk provideChunk(BlockPos pos) {
      return this.provideChunk(pos.getX() >> 4, pos.getZ() >> 4);
   }

   @Override
   public int getLoadedChunkCount() {
      return this.chunkListing.size();
   }

   @Override
   public boolean unloadQueuedChunks() {
      long l = System.currentTimeMillis();

      for (Chunk chunk : (Iterable<Chunk>)(this.chunkListing)) {
         chunk.onTick(System.currentTimeMillis() - l > 5L);
      }

      if (System.currentTimeMillis() - l > 100L) {
         LOGGER.info("Warning: Clientside chunk ticking took {} ms", new Object[]{System.currentTimeMillis() - l});
      }

      return false;
   }

   @Override
   public String makeString() {
      return "MultiplayerChunkCache: " + this.chunkMapping.getNumHashElements() + ", " + this.chunkListing.size();
   }
}
