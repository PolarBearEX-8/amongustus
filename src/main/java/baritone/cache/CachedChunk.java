package baritone.cache;

import baritone.api.utils.BlockUtils;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;

public final class CachedChunk {
   public static final ImmutableSet BLOCKS_TO_KEEP_TRACK_OF = ImmutableSet.of(
      Blocks.diamond_block,
      Blocks.coal_block,
      Blocks.iron_block,
      Blocks.gold_block,
      Blocks.emerald_ore,
      Blocks.emerald_block,
      new Block[]{
         Blocks.ender_chest,
         Blocks.furnace,
         Blocks.chest,
         Blocks.trapped_chest,
         Blocks.end_portal,
         Blocks.end_portal_frame,
         Blocks.mob_spawner,
         Blocks.barrier,
         Blocks.portal,
         Blocks.hopper,
         Blocks.beacon,
         Blocks.brewing_stand,
         Blocks.skull,
         Blocks.enchanting_table,
         Blocks.anvil,
         Blocks.lit_furnace,
         Blocks.bed,
         Blocks.dragon_egg,
         Blocks.jukebox,
         Blocks.web,
         Blocks.nether_wart,
         Blocks.ladder,
         Blocks.vine
      }
   );
   public static final int BITS_PER_CHUNK = 131072;
   public static final int BYTES_PER_CHUNK = 16384;
   public final int x;
   public final int z;
   private final BitSet data;
   private final Int2ObjectOpenHashMap specialBlockLookup;
   private final IBlockState[] topBlocks;
   private final int[] heightMap;
   private final Map specialBlocks;
   public final long timestamp;

   public final byte[] toByteArray() {
      return this.data.toByteArray();
   }

   public final IBlockState getBlock(int n, int n2, int n3, int n4) {
      int positionIndex = getPositionIndex(n, n2, n3);
      CachedBlockType blockType = this.getBlockType(positionIndex);
      int n5 = n3 << 4 | n;
      if (this.heightMap[n5] == n2 && blockType != CachedBlockType.AVOID) {
         return this.topBlocks[n5];
      } else {
         if (this.specialBlockLookup != null) {
            String str = (String)this.specialBlockLookup.get(positionIndex);
            if (str != null) {
               return BlockUtils.stringToBlockRequired(str).getDefaultState();
            }
         }

         if (blockType == CachedBlockType.SOLID) {
            if (n2 == 127 && n4 == -1) {
               return Blocks.bedrock.getDefaultState();
            }

            if (n2 < 5 && n4 == 0) {
               return Blocks.obsidian.getDefaultState();
            }
         }

         return ChunkPacker.getBlockState(blockType, n4);
      }
   }

   public final IBlockState[] getTopBlocks() {
      return this.topBlocks;
   }

   private final void populateSpecialBlockLookup() {
      for (Entry entry : (Iterable<Entry>)(this.specialBlocks.entrySet())) {
         for (BlockPos pos : (Iterable<BlockPos>)((List)entry.getValue())) {
            this.specialBlockLookup.put(getPositionIndex(pos.getX(), pos.getY(), pos.getZ()), entry.getKey());
         }
      }
   }

   public final ArrayList getLocationsOf(String str) {
      if (this.specialBlocks.get(str) == null) {
         return null;
      } else {
         ArrayList list = new ArrayList();

         for (BlockPos pos : (Iterable<BlockPos>)((List)this.specialBlocks.get(str))) {
            list.add(new BlockPos(pos.getX() + this.x * 16, pos.getY(), pos.getZ() + this.z * 16));
         }

         return list;
      }
   }

   public static int getPositionIndex(int n, int n2, int n3) {
      return n << 1 | n3 << 5 | n2 << 9;
   }

   private void calculateHeightMap() {
      for (int i = 0; i < 16; i++) {
         for (int j = 0; j < 16; j++) {
            int n = i << 4 | j;
            this.heightMap[n] = 0;

            for (int k = 256; k >= 0; k--) {
               int positionIndex = getPositionIndex(j, k, i);
               if (this.data.get(positionIndex) || this.data.get(positionIndex + 1)) {
                  this.heightMap[n] = k;
                  break;
               }
            }
         }
      }
   }

   public final Map getSpecialBlocks() {
      return this.specialBlocks;
   }

   public CachedChunk(int n, int n2, BitSet bitSet, IBlockState[] states, Map map, long l) {
      validate(bitSet);
      this.x = n;
      this.z = n2;
      this.data = bitSet;
      this.topBlocks = states;
      this.heightMap = new int[256];
      this.specialBlocks = map;
      this.timestamp = l;
      if (map.isEmpty()) {
         this.specialBlockLookup = null;
      } else {
         this.specialBlockLookup = new Int2ObjectOpenHashMap();
         this.populateSpecialBlockLookup();
      }

      this.calculateHeightMap();
   }

   private static void validate(BitSet bitSet) {
      if (bitSet.size() > 131072) {
         throw new IllegalArgumentException("BitSet of invalid length provided");
      }
   }

   private CachedBlockType getBlockType(int n) {
      return CachedBlockType.getByBits(this.data.get(n), this.data.get(n + 1));
   }
}
