package cc.baritone.utils;

import client.util.IBlockState;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.BlockPos;

public final class BreadthFirstWatcher {
   public static final ImmutableSet immutableSet = ImmutableSet.of(
      Blocks.block91,
      Blocks.block9,
      Blocks.block16,
      Blocks.block35,
      Blocks.block70,
      Blocks.block93,
      new Block[]{
         Blocks.block60,
         Blocks.block39,
         Blocks.blockTrappedChest,
         Blocks.block128,
         Blocks.block2,
         Blocks.block52,
         Blocks.block29,
         Blocks.block145,
         Blocks.blockPortal,
         Blocks.blockHopper,
         Blocks.blockBeacon,
         Blocks.block80,
         Blocks.blockSkull,
         Blocks.block102,
         Blocks.block51,
         Blocks.block88,
         Blocks.bed,
         Blocks.block82,
         Blocks.block114,
         Blocks.web,
         Blocks.block78,
         Blocks.block81,
         Blocks.block144
      }
   );
   public static final int count = 131072;
   public static final int count2 = 16384;
   public final int count3;
   public final int count4;
   public final BitSet bitSet;
   public final Int2ObjectOpenHashMap int2ObjectOpenHashMap;
   public final IBlockState[] iBlockStateArray;
   public final int[] countArray;
   public final Map map;
   public final long timestamp;

   private static void size(BitSet bitSet2) {
      if (bitSet2.size() > 131072) {
         throw new IllegalArgumentException("BitSet of invalid length provided");
      }
   }

   private final void updateState() {
      for (Object entryObj : this.map.entrySet()) {
         Entry entry = (Entry)entryObj;
         for (Object blockPosObj : (List)entry.getValue()) {
            BlockPos blockPos = (BlockPos)blockPosObj;
            this.int2ObjectOpenHashMap.put(computeCount(blockPos.getX(), blockPos.getY(), blockPos.getZ()), entry.getKey());
         }
      }
   }

   public BreadthFirstWatcher(int count5, int count6, BitSet bitSet2, IBlockState[] blockStateArray, Map map2, long value) {
      size(bitSet2);
      this.count3 = count5;
      this.count4 = count6;
      this.bitSet = bitSet2;
      this.iBlockStateArray = blockStateArray;
      this.countArray = new int[256];
      this.map = map2;
      this.timestamp = value;
      if (map2.isEmpty()) {
         this.int2ObjectOpenHashMap = null;
      } else {
         this.int2ObjectOpenHashMap = new Int2ObjectOpenHashMap();
         this.updateState();
      }

      this.updateState2();
   }

   public final byte[] toByteArray() {
      return this.bitSet.toByteArray();
   }

   public final java.util.ArrayList resolveArrayList(String text) {
      if (this.map.get(text) == null) {
         return null;
      } else {
         java.util.ArrayList list = new java.util.ArrayList();

         for (Object blockPosObj : (List)this.map.get(text)) {
            BlockPos blockPos = (BlockPos)blockPosObj;
            list.add(new BlockPos(blockPos.getX() + this.count3 * 16, blockPos.getY(), blockPos.getZ() + this.count4 * 16));
         }

         return list;
      }
   }

   public final Map getMap() {
      return this.map;
   }

   private void updateState2() {
      for (int index = 0; index < 16; index++) {
         for (int index2 = 0; index2 < 16; index2++) {
            int count5 = index << 4 | index2;
            this.countArray[count5] = 0;

            for (int index3 = 256; index3 >= 0; index3--) {
               int count6 = computeCount(index2, index3, index);
               if (this.bitSet.get(count6) || this.bitSet.get(count6 + 1)) {
                  this.countArray[count5] = index3;
                  break;
               }
            }
         }
      }
   }

   public final IBlockState resolveIBlockState(int count5, int count6, int count7, int count8) {
      int count9 = computeCount(count5, count6, count7);
      AIRWATEREnum aIRWATEREnum = this.resolveAIRWATEREnum(count9);
      int count10 = count7 << 4 | count5;
      if (this.countArray[count10] == count6 && aIRWATEREnum != AIRWATEREnum.AVOID) {
         return this.iBlockStateArray[count10];
      } else {
         if (this.int2ObjectOpenHashMap != null) {
            String text = (String)this.int2ObjectOpenHashMap.get(count9);
            if (text != null) {
               return EnhancedCustom.resolveBlockByText(text).getDefaultState();
            }
         }

         if (aIRWATEREnum == AIRWATEREnum.SOLID) {
            if (count6 == 127 && count8 == -1) {
               return Blocks.block95.getDefaultState();
            }

            if (count6 < 5 && count8 == 0) {
               return Blocks.block113.getDefaultState();
            }
         }

         return BreadthFirstWatcherHelper.resolveIBlockState(aIRWATEREnum, count8);
      }
   }

   public static int computeCount(int count5, int count6, int count7) {
      return count5 << 1 | count7 << 5 | count6 << 9;
   }

   public final IBlockState[] getIBlockStateArray() {
      return this.iBlockStateArray;
   }

   private AIRWATEREnum resolveAIRWATEREnum(int count5) {
      return AIRWATEREnum.resolveAIRWATEREnum(this.bitSet.get(count5), this.bitSet.get(count5 + 1));
   }
}
