package baritone.utils;

import baritone.Baritone;
import baritone.api.utils.IPlayerContext;
import baritone.cache.CachedRegion;
import baritone.cache.WorldData;
import baritone.utils.pathing.BetterWorldBorder;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class BlockStateInterface {
   private final Long2ObjectMap loadedChunks;
   private final WorldData worldData;
   public final BlockPos.MutableBlockPos isPassableBlockPos;
   public final IBlockAccess access;
   public final BetterWorldBorder worldBorder;
   private Chunk prev = null;
   private CachedRegion prevCached = null;
   private final boolean useTheRealWorld;
   private static final IBlockState AIR = Blocks.air.getDefaultState();

   public static Block getBlock(IPlayerContext playerContext, BlockPos pos) {
      return get(playerContext, pos).getBlock();
   }

   public IBlockState get0(BlockPos pos) {
      return this.get0(pos.getX(), pos.getY(), pos.getZ());
   }

   public IBlockState get0(int n, int n2, int n3) {
      if (n2 >= 0 && n2 < 256) {
         if (this.useTheRealWorld) {
            Chunk prev2 = this.prev;
            if (prev2 != null && prev2.xPosition == n >> 4 && prev2.zPosition == n3 >> 4) {
               return prev2.getBlockState(n, n2, n3);
            }

            Chunk chunk = (Chunk)this.loadedChunks.get(ChunkPos.asLong(n >> 4, n3 >> 4));
            if (chunk != null && chunk.isLoaded()) {
               this.prev = chunk;
               return chunk.getBlockState(n, n2, n3);
            }
         }

         CachedRegion prevCached2 = this.prevCached;
         if (prevCached2 == null || prevCached2.getX() != n >> 9 || prevCached2.getZ() != n3 >> 9) {
            if (this.worldData == null) {
               return AIR;
            }

            CachedRegion region2 = this.worldData.cache.getRegion2(n >> 9, n3 >> 9);
            if (region2 == null) {
               return AIR;
            }

            this.prevCached = region2;
            prevCached2 = region2;
         }

         IBlockState block = prevCached2.getBlock(n & 511, n2, n3 & 511);
         return block == null ? AIR : block;
      } else {
         return AIR;
      }
   }

   public static IBlockState get(IPlayerContext playerContext, BlockPos pos) {
      return new BlockStateInterface(playerContext).get0(pos.getX(), pos.getY(), pos.getZ());
   }

   public boolean isLoaded(int n, int n2) {
      Chunk prev2 = this.prev;
      if (prev2 != null && prev2.xPosition == n >> 4 && prev2.zPosition == n2 >> 4) {
         return true;
      } else {
         prev2 = (Chunk)this.loadedChunks.get(ChunkPos.asLong(n >> 4, n2 >> 4));
         if (prev2 != null && prev2.isLoaded()) {
            this.prev = prev2;
            return true;
         } else {
            CachedRegion prevCached2 = this.prevCached;
            if (prevCached2 != null && prevCached2.getX() == n >> 9 && prevCached2.getZ() == n2 >> 9) {
               return prevCached2.isCached(n & 511, n2 & 511);
            } else if (this.worldData == null) {
               return false;
            } else {
               prevCached2 = this.worldData.cache.getRegion2(n >> 9, n2 >> 9);
               if (prevCached2 == null) {
                  return false;
               } else {
                  this.prevCached = prevCached2;
                  return prevCached2.isCached(n & 511, n2 & 511);
               }
            }
         }
      }
   }

   public BlockStateInterface(IPlayerContext playerContext, boolean flag) {
      World world = playerContext.world();
      this.worldBorder = new BetterWorldBorder(world.getWorldBorder());
      this.worldData = (WorldData)playerContext.worldData();
      Long2ObjectOpenHashMap long2ObjectOpenHashMap = new Long2ObjectOpenHashMap();

      for (long l : ((ChunkProviderClient)world.getChunkProvider()).chunkMapping.getKeys()) {
         long2ObjectOpenHashMap.put(l, (Chunk)((ChunkProviderClient)world.getChunkProvider()).chunkMapping.getValueByKey(l));
      }

      if (flag) {
         this.loadedChunks = new Long2ObjectOpenHashMap(long2ObjectOpenHashMap);
      } else {
         this.loadedChunks = long2ObjectOpenHashMap;
      }

      this.useTheRealWorld = !(Boolean)Baritone.settings().pathThroughCachedOnly.value;
      if (!playerContext.getMc().isCallingFromMinecraftThread()) {
         throw new IllegalStateException();
      } else {
         this.isPassableBlockPos = new BlockPos.MutableBlockPos();
         this.access = new BlockStateInterfaceAccessWrapper(this, world);
      }
   }

   public boolean worldContainsLoadedChunk(int n, int n2) {
      return this.loadedChunks.containsKey(ChunkPos.asLong(n >> 4, n2 >> 4));
   }

   public BlockStateInterface(IPlayerContext playerContext) {
      this(playerContext, false);
   }
}
