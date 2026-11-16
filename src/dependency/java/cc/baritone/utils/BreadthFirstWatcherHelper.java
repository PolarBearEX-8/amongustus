package cc.baritone.utils;

import client.util.AlertNotificationSound;
import client.util.IBlockState;
import client.util.ImmutableSetEntry2Entry;
import client.util.InternalCharArrayEntry;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallgrass;
import net.minecraft.block.Blocks;
import net.minecraft.util.BlockPos;

public final class BreadthFirstWatcherHelper {
   public static BreadthFirstWatcher resolveBreadthFirstWatcher(AlertNotificationSound alertNotificationSound) {
      HashMap map = new HashMap();
      BitSet bitSet = new BitSet(131072);

      try {
         InternalCharArrayEntry[] internalCharArrayEntryArray = alertNotificationSound.getInternalCharArrayEntryArray();

         for (int index = 0; index < 16; index++) {
            if (internalCharArrayEntryArray[index] != null) {
               int count = index << 4;

               for (int index2 = 0; index2 < 16; index2++) {
                  int count2 = index2 | count;

                  for (int index3 = 0; index3 < 16; index3++) {
                     for (int index4 = 0; index4 < 16; index4++) {
                        int count3 = BreadthFirstWatcher.computeCount(index4, count2, index3);
                        IBlockState blockState = alertNotificationSound.resolveIBlockState(index4, index2, index3);
                        boolean[] enabledArray = resolveAIRWATEREnum(blockState, alertNotificationSound, index4, count2, index3).getEnabledArray();
                        bitSet.set(count3, enabledArray[0]);
                        bitSet.set(count3 + 1, enabledArray[1]);
                        Block block = blockState.getBlock();
                        if (BreadthFirstWatcher.immutableSet.contains(block)) {
                           String text = EnhancedCustom.resolveText(block);
                           ((List)map.computeIfAbsent(text, item -> BreadthFirstWatcherHelper.resolveList((String)item))).add(new BlockPos(index4, count2, index3));
                        }
                     }
                  }
               }
            }
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }

      IBlockState[] blockStateArray = new IBlockState[256];

      for (int index5 = 0; index5 < 16; index5++) {
         innerLoop:
         for (int index6 = 0; index6 < 16; index6++) {
            for (int index7 = 255; index7 >= 0; index7--) {
               int count4 = BreadthFirstWatcher.computeCount(index6, index7, index5);
               if (bitSet.get(count4) || bitSet.get(count4 + 1)) {
                  blockStateArray[index5 << 4 | index6] = alertNotificationSound.resolveIBlockState(index6, index7, index5);
                  continue innerLoop;
               }
            }

            blockStateArray[index5 << 4 | index6] = Blocks.air.getDefaultState();
         }
      }

      return new BreadthFirstWatcher(alertNotificationSound.indexOffset1, alertNotificationSound.indexOffset, bitSet, blockStateArray, map, System.currentTimeMillis());
   }

   public static IBlockState resolveIBlockState(AIRWATEREnum aIRWATEREnum, int count) {
      switch (BreadthFirstWatcherHelpeAIRWATEREnumSwitchMap.countArray[aIRWATEREnum.ordinal()]) {
         case 1:
            return Blocks.air.getDefaultState();
         case 2:
            return Blocks.blockLava2.getDefaultState();
         case 3:
            return Blocks.blockLava.getDefaultState();
         case 4:
            switch (count) {
               case -1:
                  return Blocks.block96.getDefaultState();
               case 0:
               default:
                  return Blocks.stone.getDefaultState();
               case 1:
                  return Blocks.block28.getDefaultState();
            }
         default:
            return null;
      }
   }

   private static AIRWATEREnum resolveAIRWATEREnum(IBlockState blockState, AlertNotificationSound alertNotificationSound, int count, int count2, int count3) {
      Block block = blockState.getBlock();
      if (block != Blocks.blockLava2 && block != Blocks.blockFlowingLava) {
         if (AStarPredictor.matchesBlockByBlock(block) || AStarPredictor.matchesIBlockStateByIBlockState(blockState)) {
            return AIRWATEREnum.AVOID;
         } else {
            return block != Blocks.air && !(block instanceof BlockTallgrass) && !(block instanceof BlockDoublePlant) && !(block instanceof ImmutableSetEntry2Entry)
               ? AIRWATEREnum.SOLID
               : AIRWATEREnum.AIR;
         }
      } else if (AStarPredictor.matchesIBlockState(blockState)) {
         return AIRWATEREnum.AVOID;
      } else if ((count == 15 || !AStarPredictor.matchesIBlockState(alertNotificationSound.resolveIBlockState(count + 1, count2, count3)))
         && (count == 0 || !AStarPredictor.matchesIBlockState(alertNotificationSound.resolveIBlockState(count - 1, count2, count3)))
         && (count3 == 15 || !AStarPredictor.matchesIBlockState(alertNotificationSound.resolveIBlockState(count, count2, count3 + 1)))
         && (count3 == 0 || !AStarPredictor.matchesIBlockState(alertNotificationSound.resolveIBlockState(count, count2, count3 - 1)))) {
         return count != 0 && count != 15 && count3 != 0 && count3 != 15 ? AIRWATEREnum.WATER : AIRWATEREnum.AVOID;
      } else {
         return AIRWATEREnum.AVOID;
      }
   }

   private static List resolveList(String text) {
      return new java.util.ArrayList<>();
   }

   private BreadthFirstWatcherHelper() {
   }
}
