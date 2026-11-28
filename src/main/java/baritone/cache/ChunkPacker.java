package baritone.cache;

import baritone.api.utils.BlockUtils;
import baritone.pathing.movement.MovementHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.BitSet;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public final class ChunkPacker {
   private static CachedBlockType getBlockType(IBlockState state, Chunk chunk, int n, int n2, int n3) {
      Block block = state.getBlock();
      if (block != Blocks.water && block != Blocks.flowing_water) {
         if (MovementHelper.avoidWalkingInto(block) || MovementHelper.isBottomSlab(state)) {
            return CachedBlockType.AVOID;
         } else {
            return block != Blocks.air && !(block instanceof BlockTallGrass) && !(block instanceof BlockDoublePlant) && !(block instanceof BlockFlower)
               ? CachedBlockType.SOLID
               : CachedBlockType.AIR;
         }
      } else if (MovementHelper.possiblyFlowing(state)) {
         return CachedBlockType.AVOID;
      } else if ((n == 15 || !MovementHelper.possiblyFlowing(chunk.getBlockState(n + 1, n2, n3)))
         && (n == 0 || !MovementHelper.possiblyFlowing(chunk.getBlockState(n - 1, n2, n3)))
         && (n3 == 15 || !MovementHelper.possiblyFlowing(chunk.getBlockState(n, n2, n3 + 1)))
         && (n3 == 0 || !MovementHelper.possiblyFlowing(chunk.getBlockState(n, n2, n3 - 1)))) {
         return n != 0 && n != 15 && n3 != 0 && n3 != 15 ? CachedBlockType.WATER : CachedBlockType.AVOID;
      } else {
         return CachedBlockType.AVOID;
      }
   }

   private ChunkPacker() {
   }

   public static CachedChunk pack(Chunk chunk) {
      HashMap map = new HashMap();
      BitSet bitSet = new BitSet(131072);

      try {
         ExtendedBlockStorage[] blockStorageArray = chunk.getBlockStorageArray();

         for (int i = 0; i < 16; i++) {
            if (blockStorageArray[i] != null) {
               int n = i << 4;

               for (int j = 0; j < 16; j++) {
                  int n2 = j | n;

                  for (int k = 0; k < 16; k++) {
                     for (int m = 0; m < 16; m++) {
                        int positionIndex = CachedChunk.getPositionIndex(m, n2, k);
                        IBlockState blockState = chunk.getBlockState(m, j, k);
                        boolean[] bits = getBlockType(blockState, chunk, m, n2, k).getBits();
                        bitSet.set(positionIndex, bits[0]);
                        bitSet.set(positionIndex + 1, bits[1]);
                        Block block = blockState.getBlock();
                        if (CachedChunk.BLOCKS_TO_KEEP_TRACK_OF.contains(block)) {
                           ((List)map.computeIfAbsent(BlockUtils.blockToString(block), obj -> new ArrayList<>())).add(new BlockPos(m, n2, k));
                        }
                     }
                  }
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      IBlockState[] states = new IBlockState[256];

      for (int idx = 0; idx < 16; idx++) {
         outerLoop:
         for (int index = 0; index < 16; index++) {
            for (int i2 = 255; i2 >= 0; i2--) {
               int positionIndex2 = CachedChunk.getPositionIndex(index, i2, idx);
               if (bitSet.get(positionIndex2) || bitSet.get(positionIndex2 + 1)) {
                  states[idx << 4 | index] = chunk.getBlockState(index, i2, idx);
                  continue outerLoop;
               }
            }

            states[idx << 4 | index] = Blocks.air.getDefaultState();
         }
      }

      return new CachedChunk(chunk.xPosition, chunk.zPosition, bitSet, states, map, System.currentTimeMillis());
   }

   public static IBlockState getBlockState(CachedBlockType cachedBlockType, int n) {
      switch (cachedBlockType) {
         case AIR:
            return Blocks.air.getDefaultState();
         case WATER:
            return Blocks.water.getDefaultState();
         case AVOID:
            return Blocks.lava.getDefaultState();
         case SOLID:
            switch (n) {
               case -1:
                  return Blocks.netherrack.getDefaultState();
               case 0:
               default:
                  return Blocks.stone.getDefaultState();
               case 1:
                  return Blocks.end_stone.getDefaultState();
            }
         default:
            return null;
      }
   }

}
