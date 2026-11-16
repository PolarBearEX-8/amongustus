package cc.baritone.utils;

import client.event.MultiplayerChunkCacheListener;
import client.util.AlertNotificationSound;
import client.util.IBlockState;
import client.util.InternalCharArrayEntry;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import net.minecraft.util.BlockPos;

public enum BreadthFirstSelector implements SharedListener {
    INSTANCE;

   public static final int[] countArray = IntStream.range(0, 16).toArray();
   public static final BreadthFirstSelector[] ENUM_VALUES = new BreadthFirstSelector[]{INSTANCE};

   private static int computeCount(int count, Integer count2) {
      return Math.abs(count2 - count);
   }



   private boolean matchesAlertNotificationSound(
      int count, int count2, AlertNotificationSound alertNotificationSound, HeuristicSystem heuristicSystem, Collection collection, int count3, int count4, int count5, int[] countArray2
   ) {
      InternalCharArrayEntry[] internalCharArrayEntryArray = alertNotificationSound.getInternalCharArrayEntryArray();

      for (int index = 0; index < 16; index++) {
         InternalCharArrayEntry internalCharArrayEntry = internalCharArrayEntryArray[index];
         if (internalCharArrayEntry != null) {
            int count6 = index << 4;

            for (int index2 = 0; index2 < 16; index2++) {
               for (int index3 = 0; index3 < 16; index3++) {
                  for (int index4 = 0; index4 < 16; index4++) {
                     IBlockState blockState = internalCharArrayEntry.resolveIBlockState(index4, index2, index3);
                     if (heuristicSystem.contains(blockState.getBlock())) {
                        collection.add(new BlockPos(count | index4, count6 | index2, count2 | index3));
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   @Override
   public List resolveList(StaticListener staticListener, HeuristicSystem heuristicSystem, int count, int count2, int count3) {
      java.util.ArrayList list = new java.util.ArrayList();
      if (heuristicSystem.asList().isEmpty()) {
         return list;
      } else {
         MultiplayerChunkCacheListener commonListener = (MultiplayerChunkCacheListener)staticListener.getWorld().getCommonListener();
         int count4 = count3 * count3;
         int count5 = staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().getX() >> 4;
         int count6 = staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().getZ() >> 4;
         int coordY = staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().getY();
         int sectionY = coordY >> 4;
         int[] countArray2 = IntStream.range(0, 16)
            .boxed()
            .sorted(Comparator.comparingInt(section -> BreadthFirstSelector.computeCount(sectionY, section)))
            .mapToInt(BreadthFirstSelector::intValue)
            .toArray();
         int count7 = 0;
         boolean flag = false;

         while (true) {
            boolean flag2 = true;
            boolean flag3 = false;

            for (int index = -count7; index <= count7; index++) {
               for (int index2 = -count7; index2 <= count7; index2++) {
                  if (index * index + index2 * index2 == count7) {
                     flag3 = true;
                     int count8 = index + count5;
                     int count9 = index2 + count6;
                     AlertNotificationSound alertNotificationSound = commonListener.resolveAlertNotificationSound(count8, count9);
                     if (alertNotificationSound != commonListener.alertNotificationSound) {
                        flag2 = false;
                        if (this.matchesAlertNotificationSound(count8 << 4, count9 << 4, alertNotificationSound, heuristicSystem, list, count, count2, coordY, countArray2)) {
                           flag = true;
                        }
                     }
                  }
               }
            }

            if (flag2 && flag3 || list.size() >= count && (count7 > count4 || count7 > 1 && flag)) {
               return list;
            }

            count7++;
         }
      }
   }

   private static int intValue(Integer count) {
      return count;
   }

   @Override
   public List resolveList(StaticListener staticListener, HeuristicSystem heuristicSystem, AStarVerifier aStarVerifier, int count, int count2) {
      if (heuristicSystem.asList().isEmpty()) {
         return Collections.emptyList();
      } else {
         MultiplayerChunkCacheListener commonListener = (MultiplayerChunkCacheListener)staticListener.getWorld().getCommonListener();
         AlertNotificationSound alertNotificationSound = commonListener.resolveAlertNotificationSound(aStarVerifier.count, aStarVerifier.count2);
         int coordY = staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().getY();
         if (alertNotificationSound != commonListener.alertNotificationSound && !alertNotificationSound.canExecute()) {
            java.util.ArrayList list = new java.util.ArrayList();
            this.matchesAlertNotificationSound(aStarVerifier.count << 4, aStarVerifier.count2 << 4, alertNotificationSound, heuristicSystem, list, count, count2, coordY, countArray);
            return list;
         } else {
            return Collections.emptyList();
         }
      }
   }

   @Override
   public int computeCount(StaticListener staticListener) {
      return this.computeCount(staticListener, 40);
   }

   @Override
   public int computeCount(StaticListener staticListener, int count) {
      MultiplayerChunkCacheListener commonListener = (MultiplayerChunkCacheListener)staticListener.getWorld().getCommonListener();
      BreadthFirstPipeline breadthFirstPipeline = staticListener.getNativeListener().getBreadthFirstPipeline();
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
      int count2 = betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getX() >> 4;
      int count3 = betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.getZ() >> 4;
      int count4 = count2 - count;
      int count5 = count3 - count;
      int count6 = count2 + count;
      int count7 = count3 + count;

      int count8;
      for (count8 = 0; count4 <= count6; count4++) {
         for (int index = count5; index <= count7; index++) {
            AlertNotificationSound alertNotificationSound = commonListener.resolveAlertNotificationSound(count4, index);
            if (alertNotificationSound != commonListener.alertNotificationSound && !alertNotificationSound.canExecute()) {
               count8++;
               breadthFirstPipeline.applyAlertNotificationSound(alertNotificationSound);
            }
         }
      }

      return count8;
   }
}
