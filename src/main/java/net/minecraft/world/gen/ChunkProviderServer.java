package net.minecraft.world.gen;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChunkProviderServer implements IChunkProvider {
   private static final Logger LOGGER = LogManager.getLogger();
   private Set droppedChunksSet = Collections.newSetFromMap(new ConcurrentHashMap());
   private Chunk dummyChunk;
   private IChunkProvider serverChunkGenerator;
   public IChunkLoader chunkLoader;
   public boolean chunkLoadOverride = true;
   public LongHashMap id2ChunkMap = new LongHashMap();
   private List loadedChunks = Lists.newArrayList();
   private WorldServer worldObj;

   private void saveChunkData(Chunk chunk) {
      if (this.chunkLoader != null) {
         try {
            chunk.setLastSaveTime(this.worldObj.getTotalWorldTime());
            this.chunkLoader.saveChunk(this.worldObj, chunk);
         } catch (IOException e) {
            LOGGER.error("Couldn't save chunk", e);
         } catch (MinecraftException e2) {
            LOGGER.error("Couldn't save chunk; already in use by another instance of Minecraft?", e2);
         }
      }
   }

   private void saveChunkExtraData(Chunk chunk) {
      if (this.chunkLoader != null) {
         try {
            this.chunkLoader.saveExtraChunkData(this.worldObj, chunk);
         } catch (Exception e) {
            LOGGER.error("Couldn't save entities", e);
         }
      }
   }

   @Override
   public int getLoadedChunkCount() {
      return this.id2ChunkMap.getNumHashElements();
   }

   public List getLoadedChunks() {
      return this.loadedChunks;
   }

   @Override
   public boolean unloadQueuedChunks() {
      if (!this.worldObj.disableLevelSaving) {
         for (int i = 0; i < 100; i++) {
            if (!this.droppedChunksSet.isEmpty()) {
               Long val = (Long)this.droppedChunksSet.iterator().next();
               Chunk valueByKey = (Chunk)this.id2ChunkMap.getValueByKey(val);
               if (valueByKey != null) {
                  valueByKey.onChunkUnload();
                  this.saveChunkData(valueByKey);
                  this.saveChunkExtraData(valueByKey);
                  this.id2ChunkMap.remove(val);
                  this.loadedChunks.remove(valueByKey);
               }

               this.droppedChunksSet.remove(val);
            }
         }

         if (this.chunkLoader != null) {
            this.chunkLoader.chunkTick();
         }
      }

      return this.serverChunkGenerator.unloadQueuedChunks();
   }

   private Chunk loadChunkFromFile(int n, int n2) {
      if (this.chunkLoader == null) {
         return null;
      } else {
         try {
            Chunk chunk = this.chunkLoader.loadChunk(this.worldObj, n, n2);
            if (chunk != null) {
               chunk.setLastSaveTime(this.worldObj.getTotalWorldTime());
               if (this.serverChunkGenerator != null) {
                  this.serverChunkGenerator.recreateStructures(chunk, n, n2);
               }
            }

            return chunk;
         } catch (Exception e) {
            LOGGER.error("Couldn't load chunk", e);
            return null;
         }
      }
   }

   @Override
   public boolean saveChunks(boolean flag, IProgressUpdate progressUpdate) {
      int n = 0;
      ArrayList list = Lists.newArrayList(this.loadedChunks);

      for (int i = 0; i < list.size(); i++) {
         Chunk chunk = (Chunk)list.get(i);
         if (flag) {
            this.saveChunkExtraData(chunk);
         }

         if (chunk.needsSaving(flag)) {
            this.saveChunkData(chunk);
            chunk.setModified(false);
            if (++n == 24 && !flag) {
               return false;
            }
         }
      }

      return true;
   }

   @Override
   public boolean populateChunk(IChunkProvider chunkProvider, Chunk chunk, int n, int n2) {
      if (this.serverChunkGenerator != null && this.serverChunkGenerator.populateChunk(chunkProvider, chunk, n, n2)) {
         this.provideChunk(n, n2).setChunkModified();
         return true;
      } else {
         return false;
      }
   }

   @Override
   public Chunk provideChunk(int n, int n2) {
      Chunk valueByKey = (Chunk)this.id2ChunkMap.getValueByKey(ChunkCoordIntPair.chunkXZ2Int(n, n2));
      return valueByKey == null ? (!this.worldObj.isFindingSpawnPoint() && !this.chunkLoadOverride ? this.dummyChunk : this.loadChunk(n, n2)) : valueByKey;
   }

   public ChunkProviderServer(WorldServer world, IChunkLoader chunkLoader2, IChunkProvider chunkProvider) {
      this.dummyChunk = new EmptyChunk(world, 0, 0);
      this.worldObj = world;
      this.chunkLoader = chunkLoader2;
      this.serverChunkGenerator = chunkProvider;
   }

   @Override
   public BlockPos getStrongholdGen(World world, String str, BlockPos pos) {
      return this.serverChunkGenerator.getStrongholdGen(world, str, pos);
   }

   public void dropChunk(int n, int n2) {
      if (this.worldObj.provider.canRespawnHere()) {
         if (!this.worldObj.isSpawnChunk(n, n2)) {
            this.droppedChunksSet.add(ChunkCoordIntPair.chunkXZ2Int(n, n2));
         }
      } else {
         this.droppedChunksSet.add(ChunkCoordIntPair.chunkXZ2Int(n, n2));
      }
   }

   @Override
   public boolean chunkExists(int n, int n2) {
      return this.id2ChunkMap.containsItem(ChunkCoordIntPair.chunkXZ2Int(n, n2));
   }

   @Override
   public Chunk provideChunk(BlockPos pos) {
      return this.provideChunk(pos.getX() >> 4, pos.getZ() >> 4);
   }

   @Override
   public void saveExtraData() {
      if (this.chunkLoader != null) {
         this.chunkLoader.saveExtraData();
      }
   }

   public void unloadAllChunks() {
      for (Chunk chunk : (Iterable<Chunk>)(this.loadedChunks)) {
         this.dropChunk(chunk.xPosition, chunk.zPosition);
      }
   }

   @Override
   public void recreateStructures(Chunk chunk, int n, int n2) {
   }

   @Override
   public String makeString() {
      return "ServerChunkCache: " + this.id2ChunkMap.getNumHashElements() + " Drop: " + this.droppedChunksSet.size();
   }

   @Override
   public void populate(IChunkProvider chunkProvider, int n, int n2) {
      Chunk chunk = this.provideChunk(n, n2);
      if (!chunk.isTerrainPopulated()) {
         chunk.checkLight();
         if (this.serverChunkGenerator != null) {
            this.serverChunkGenerator.populate(chunkProvider, n, n2);
            chunk.setChunkModified();
         }
      }
   }

   @Override
   public boolean canSave() {
      return !this.worldObj.disableLevelSaving;
   }

   @Override
   public List getPossibleCreatures(EnumCreatureType enumCreatureType, BlockPos pos) {
      return this.serverChunkGenerator.getPossibleCreatures(enumCreatureType, pos);
   }

   public Chunk loadChunk(int n, int n2) {
      long l = ChunkCoordIntPair.chunkXZ2Int(n, n2);
      this.droppedChunksSet.remove(l);
      Chunk valueByKey = (Chunk)this.id2ChunkMap.getValueByKey(l);
      if (valueByKey == null) {
         valueByKey = this.loadChunkFromFile(n, n2);
         if (valueByKey == null) {
            if (this.serverChunkGenerator == null) {
               valueByKey = this.dummyChunk;
            } else {
               try {
                  valueByKey = this.serverChunkGenerator.provideChunk(n, n2);
               } catch (Throwable e) {
                  CrashReport crashReport = CrashReport.makeCrashReport(e, "Exception generating new chunk");
                  CrashReportCategory crashReportCategory = crashReport.makeCategory("Chunk to be generated");
                  crashReportCategory.addCrashSection("Location", String.format("%d,%d", n, n2));
                  crashReportCategory.addCrashSection("Position hash", l);
                  crashReportCategory.addCrashSection("Generator", this.serverChunkGenerator.makeString());
                  throw new ReportedException(crashReport);
               }
            }
         }

         this.id2ChunkMap.add(l, valueByKey);
         this.loadedChunks.add(valueByKey);
         valueByKey.onChunkLoad();
         valueByKey.populateChunk(this, this, n, n2);
      }

      return valueByKey;
   }
}
