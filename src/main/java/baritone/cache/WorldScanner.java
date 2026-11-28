package baritone.cache;

import baritone.api.cache.ICachedWorld;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.BlockOptionalMetaLookup;
import baritone.api.utils.IPlayerContext;
import baritone.api.cache.IWorldScanner;
import baritone.utils.ChunkPos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public enum WorldScanner implements IWorldScanner {
   INSTANCE;
   private static final int[] ALL_SECTIONS = IntStream.range(0, 16).toArray();
   @Override
   public int repack(IPlayerContext playerContext) {
      return this.repack(playerContext, 40);
   }

   @Override
   public List scanChunk(IPlayerContext playerContext, BlockOptionalMetaLookup blockOptionalMetaLookup, ChunkPos chunkPos, int n, int n2) {
      if (blockOptionalMetaLookup.blocks().isEmpty()) {
         return Collections.emptyList();
      } else {
         ChunkProviderClient chunkProvider = (ChunkProviderClient)playerContext.world().getChunkProvider();
         Chunk chunk = chunkProvider.provideChunk(chunkPos.x, chunkPos.z);
         int y = playerContext.playerFeet().getY();
         if (chunk != chunkProvider.blankChunk && !chunk.isEmpty()) {
            ArrayList list = new ArrayList();
            this.scanChunk(chunkPos.x << 4, chunkPos.z << 4, chunk, blockOptionalMetaLookup, list, n, n2, y, ALL_SECTIONS);
            return list;
         } else {
            return Collections.emptyList();
         }
      }
   }

   @Override
   public List scanChunkRadius(IPlayerContext playerContext, BlockOptionalMetaLookup blockOptionalMetaLookup, int n, int n2, int n3) {
      ArrayList list = new ArrayList();
      if (blockOptionalMetaLookup.blocks().isEmpty()) {
         return list;
      } else {
         ChunkProviderClient chunkProvider = (ChunkProviderClient)playerContext.world().getChunkProvider();
         int n4 = n3 * n3;
         int n5 = playerContext.playerFeet().getX() >> 4;
         int n6 = playerContext.playerFeet().getZ() >> 4;
         int y = playerContext.playerFeet().getY();
         int n7 = y >> 4;
         int[] ints = IntStream.range(0, 16).boxed().sorted(Comparator.comparingInt(num -> Math.abs(num - n7))).mapToInt(num2 -> num2).toArray();
         int n8 = 0;
         boolean flag = false;

         while (true) {
            boolean flag2 = true;
            boolean flag3 = false;

            for (int i = -n8; i <= n8; i++) {
               for (int j = -n8; j <= n8; j++) {
                  if ((i * i + j * j) == n8) {
                     flag3 = true;
                     int n10 = i + n5;
                     int n11 = j + n6;
                     Chunk chunk = chunkProvider.provideChunk(n10, n11);
                     if (chunk != chunkProvider.blankChunk) {
                        flag2 = false;
                        if (this.scanChunk(n10 << 4, n11 << 4, chunk, blockOptionalMetaLookup, list, n, n2, y, ints)) {
                           flag = true;
                        }
                     }
                  }
               }
            }

            if (flag2 && flag3 || list.size() >= n && (n8 > n4 || n8 > 1 && flag)) {
               return list;
            }

            n8++;
         }
      }
   }

   private boolean scanChunk(int n, int n2, Chunk chunk, BlockOptionalMetaLookup blockOptionalMetaLookup, Collection items, int n3, int n4, int n5, int[] ints) {
      ExtendedBlockStorage[] blockStorageArray = chunk.getBlockStorageArray();

      for (int i = 0; i < 16; i++) {
         ExtendedBlockStorage extendedBlockStorage = blockStorageArray[i];
         if (extendedBlockStorage != null) {
            int n6 = i << 4;

            for (int j = 0; j < 16; j++) {
               for (int k = 0; k < 16; k++) {
                  for (int m = 0; m < 16; m++) {
                     if (blockOptionalMetaLookup.has2(extendedBlockStorage.get(m, j, k).getBlock())) {
                        items.add(new BlockPos(n | m, n6 | j, n2 | k));
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   @Override
   public int repack(IPlayerContext playerContext, int n) {
      ChunkProviderClient chunkProvider = (ChunkProviderClient)playerContext.world().getChunkProvider();
      ICachedWorld cachedWorld = playerContext.worldData().getCachedWorld();
      BetterBlockPos betterBlockPos = playerContext.playerFeet();
      int n2 = betterBlockPos.getX() >> 4;
      int n3 = betterBlockPos.getZ() >> 4;
      int n4 = n2 - n;
      int n5 = n3 - n;
      int n6 = n2 + n;
      int n7 = n3 + n;
      int n8 = 0;

      for (int i = n4; i <= n6; i++) {
         for (int j = n5; j <= n7; j++) {
            Chunk chunk = chunkProvider.provideChunk(i, j);
            if (chunk != chunkProvider.blankChunk && !chunk.isEmpty()) {
               n8++;
               cachedWorld.queueForPacking(chunk);
            }
         }
      }

      return n8;
   }

}
