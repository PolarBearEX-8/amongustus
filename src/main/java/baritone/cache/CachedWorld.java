package baritone.cache;

import baritone.Baritone;
import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.cache.ICachedRegion;
import baritone.api.cache.ICachedWorld;
import baritone.api.cache.IWorldData;
import baritone.api.utils.Helper;
import baritone.utils.ChunkPos;
import com.google.common.cache.CacheBuilder;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;

public final class CachedWorld implements ICachedWorld, Helper {
   private static final int MAX_REGION_COORD = 58594;
   private Long2ObjectMap regions = new Long2ObjectOpenHashMap();
   private final String directory;
   private final LinkedBlockingQueue toPack = new LinkedBlockingQueue();
   private final Map chunkMap = CacheBuilder.newBuilder().softValues().build().asMap();
   private final int dimension;

   @Override
   public final ArrayList getLocationsOf(String str, int n, int n2, int n3, int n4) {
      ArrayList list = new ArrayList();
      int n5 = n2 >> 9;
      int n6 = n3 >> 9;

      for (int j = 0; j <= n4; j++) {
         for (int k = -j; k <= j; k++) {
            for (int m = -j; m <= j; m++) {
               if ((k * k + m * m) == j) {
                  CachedRegion regionOrLoad = this.getRegionOrLoad(k + n5, m + n6);
                  if (regionOrLoad != null) {
                     list.addAll(regionOrLoad.getLocationsOf(str));
                  }
               }
            }
         }

         if (list.size() >= n) {
            return list;
         }
      }

      return list;
   }

   @Override
   public final void reloadAllFromDisk() {
      long l = System.nanoTime() / 1000000L;
      this.getLoadedRegions().forEach(obj -> {
         if (obj != null) {
            ((CachedRegion)obj).loadFromDisk(this.directory);
         }
      });
      long l2 = System.nanoTime() / 1000000L;
      System.out.println("World load took " + (l2 - l) + "ms");
   }

   public static void putChunk(CachedWorld cachedWorld, CachedChunk cachedChunk) {
      cachedWorld.putChunk(cachedChunk);
   }

   @Override
   public final boolean isCached(int n, int n2) {
      CachedRegion region2 = this.getRegion2(n >> 9, n2 >> 9);
      return region2 == null ? false : region2.isCached(n & 511, n2 & 511);
   }

   public static Map getChunkMap(CachedWorld cachedWorld) {
      return cachedWorld.chunkMap;
   }

   private synchronized void pruneRegions() {
      if ((Boolean)Baritone.settings().pruneRegionsFromRAM.value) {
         BlockPos referencePos = this.getReferencePos();

         for (CachedRegion cachedRegion : (Iterable<CachedRegion>)(this.getLoadedRegions())) {
            if (cachedRegion != null) {
               int n = (cachedRegion.getX() << 9) + 256 - referencePos.getX();
               int n2 = (cachedRegion.getZ() << 9) + 256 - referencePos.getZ();
               if (Math.sqrt((double)(n * n + n2 * n2)) > 1024.0) {
                  if (!(Boolean)Baritone.settings().censorCoordinates.value) {
                     this.logDebug("Deleting cached region " + cachedRegion.getX() + "," + cachedRegion.getZ() + " from ram");
                  }

                  this.regions.remove(this.getRegionKey(cachedRegion.getX(), cachedRegion.getZ()));
               }
            }
         }
      }
   }

   private long getRegionKey(int n, int n2) {
      return !this.isValidRegionCoord(n, n2) ? 0L : (long)n & 4294967295L | ((long)n2 & 4294967295L) << 32;
   }

   public static LinkedBlockingQueue getToPackQueue(CachedWorld cachedWorld) {
      return cachedWorld.toPack;
   }

   @Override
   public final void queueForPacking(Chunk chunk) {
      ChunkPos chunkPos = new ChunkPos(chunk.xPosition, chunk.zPosition);
      if (this.chunkMap.put(chunkPos, chunk) == null) {
         this.toPack.add(chunkPos);
      }
   }

   @Override
   public ICachedRegion getRegion(int n, int n2) {
      return this.getRegion2(n, n2);
   }

   @Override
   public final void save() {
      if (!(Boolean)Baritone.settings().chunkCaching.value) {
         System.out.println("Not saving to disk; chunk caching is disabled.");
         this.getLoadedRegions().forEach(obj -> {
            if (obj != null) {
               ((CachedRegion)obj).pruneExpiredChunks();
            }
         });
         this.pruneRegions();
      } else {
         long l = System.nanoTime() / 1000000L;
         this.getLoadedRegions().parallelStream().forEach(obj2 -> {
            if (obj2 != null) {
               ((CachedRegion)obj2).saveToDisk(this.directory);
            }
         });
         long l2 = System.nanoTime() / 1000000L;
         System.out.println("World save took " + (l2 - l) + "ms");
         this.pruneRegions();
      }
   }

   private synchronized CachedRegion getRegionOrLoad(int n, int n2) {
      return (CachedRegion)this.regions.computeIfAbsent(this.getRegionKey(n, n2), l -> {
         CachedRegion cachedRegion = new CachedRegion(n, n2, this.dimension);
         cachedRegion.loadFromDisk(this.directory);
         return cachedRegion;
      });
   }

   private BlockPos getReferencePos() {
      for (IBaritone baritone : (Iterable<IBaritone>)(BaritoneAPI.getProvider().getAllBaritones())) {
         IWorldData currentWorld = baritone.getWorldProvider().getCurrentWorld();
         if (currentWorld != null && currentWorld.getCachedWorld() == this && baritone.getPlayerContext().player() != null) {
            return baritone.getPlayerContext().playerFeet();
         }
      }

      CachedChunk cachedChunk = null;

      for (CachedRegion cachedRegion : (Iterable<CachedRegion>)(this.getLoadedRegions())) {
         if (cachedRegion != null) {
            CachedChunk cachedChunk2 = cachedRegion.getMostRecentlyCachedChunk();
            if (cachedChunk2 != null && (cachedChunk == null || cachedChunk.timestamp < cachedChunk2.timestamp)) {
               cachedChunk = cachedChunk2;
            }
         }
      }

      return cachedChunk == null ? new BlockPos(0, 0, 0) : new BlockPos((cachedChunk.x << 4) + 8, 0, (cachedChunk.z << 4) + 8);
   }

   public final synchronized CachedRegion getRegion2(int n, int n2) {
      return (CachedRegion)this.regions.get(this.getRegionKey(n, n2));
   }

   public void tryLoadFromDisk(int n, int n2) {
      this.getRegionOrLoad(n, n2);
   }

   private boolean isValidRegionCoord(int n, int n2) {
      return n <= 58594 && n >= -58594 && n2 <= 58594 && n2 >= -58594;
   }

   public CachedWorld(Path path, int n) {
      if (!Files.exists(path)) {
         try {
            Files.createDirectories(path);
         } catch (IOException e) {
         }
      }

      this.directory = path.toString();
      this.dimension = n;
      System.out.println("Cached world directory: " + path);
      Baritone.getThreadPool().execute(new CachedWorldPacker(this, null));
      Baritone.getThreadPool().execute(() -> {
         try {
            Thread.sleep(30000L);

            while (true) {
               this.save();
               Thread.sleep(600000L);
            }
         } catch (InterruptedException e2) {
            e2.printStackTrace();
         }
      });
   }

   public final boolean isRegionLoaded(int n, int n2) {
      return this.getRegion2(n >> 9, n2 >> 9) != null;
   }

   private void putChunk(CachedChunk cachedChunk) {
      this.getRegionOrLoad(cachedChunk.x >> 5, cachedChunk.z >> 5).putChunk(cachedChunk.x & 31, cachedChunk.z & 31, cachedChunk);
   }

   private synchronized List getLoadedRegions() {
      return new ArrayList(this.regions.values());
   }



   public static class InnerEntry {
   }
}
