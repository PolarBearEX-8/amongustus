package net.minecraft.world.biome;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.LongHashMap;

public class BiomeCache {
   private final WorldChunkManager chunkManager;
   private long lastCleanupTime;
   private LongHashMap cacheMap = new LongHashMap();
   private List cache = Lists.newArrayList();

   public BiomeCache(WorldChunkManager worldChunkManager) {
      this.chunkManager = worldChunkManager;
   }

   public void cleanupCache() {
      long currentTimeMillis = MinecraftServer.getCurrentTimeMillis();
      long l = currentTimeMillis - this.lastCleanupTime;
      if (l > 7500L || l < 0L) {
         this.lastCleanupTime = currentTimeMillis;

         for (int i = 0; i < this.cache.size(); i++) {
            BiomeCache.Block block = (BiomeCache.Block)this.cache.get(i);
            long l2 = currentTimeMillis - block.lastAccessTime;
            if (l2 > 30000L || l2 < 0L) {
               this.cache.remove(i--);
               long l3 = (long)block.xPosition & 4294967295L | ((long)block.zPosition & 4294967295L) << 32;
               this.cacheMap.remove(l3);
            }
         }
      }
   }

   public BiomeGenBase getBiome(int n, int n2, BiomeGenBase biomeGenBase) {
      BiomeGenBase biomeGenAt = this.getBiomeCacheBlock(n, n2).getBiomeGenAt(n, n2);
      return biomeGenAt == null ? biomeGenBase : biomeGenAt;
   }

   public static WorldChunkManager accessChunkManager(BiomeCache biomeCache) {
      return biomeCache.chunkManager;
   }

   public BiomeCache.Block getBiomeCacheBlock(int n, int n2) {
      n >>= 4;
      n2 >>= 4;
      long l = (long)n & 4294967295L | ((long)n2 & 4294967295L) << 32;
      BiomeCache.Block valueByKey = (BiomeCache.Block)this.cacheMap.getValueByKey(l);
      if (valueByKey == null) {
         valueByKey = new BiomeCache.Block(this, n, n2);
         this.cacheMap.add(l, valueByKey);
         this.cache.add(valueByKey);
      }

      valueByKey.lastAccessTime = MinecraftServer.getCurrentTimeMillis();
      return valueByKey;
   }

   public BiomeGenBase[] getCachedBiomes(int n, int n2) {
      return this.getBiomeCacheBlock(n, n2).biomes;
   }


   public static class Block {
      public float[] rainfallValues;
      public BiomeGenBase[] biomes;
      public int xPosition;
      public int zPosition;
      public long lastAccessTime;
      public final BiomeCache biomeCache;

      public Block(BiomeCache biomeCache2, int n, int n2) {
         this.biomeCache = biomeCache2;
         this.rainfallValues = new float[256];
         this.biomes = new BiomeGenBase[256];
         this.xPosition = n;
         this.zPosition = n2;
         BiomeCache.accessChunkManager(biomeCache2).getRainfall(this.rainfallValues, n << 4, n2 << 4, 16, 16);
         BiomeCache.accessChunkManager(biomeCache2).getBiomeGenAt(this.biomes, n << 4, n2 << 4, 16, 16, false);
      }

      public BiomeGenBase getBiomeGenAt(int n, int n2) {
         return this.biomes[n & 15 | (n2 & 15) << 4];
      }
   }
}
