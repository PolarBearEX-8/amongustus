package net.minecraft.item;

import client.event.PostUpdateListener;
import client.util.AuxiliaryListenerWrapper;
import client.util.DistanceFactory;
import client.util.ListenerAdapter22;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GoldenShovel {
   public static final PostUpdateListener postUpdateListener = new ListenerAdapter22();
   public static final PostUpdateListener postUpdateListener2 = new DistanceFactory();
   public static final PostUpdateListener postUpdateListener3 = new AuxiliaryListenerWrapper();

   private static int computeCount(IBlockAccess iBlockAccess, BlockPos blockPos, PostUpdateListener postUpdateListener4) {
      int count = 0;
      int count2 = 0;
      int count3 = 0;

      for (Object auxiliaryBlockPosAdapterObj : BlockPos.resolveIterableByBlockPos(blockPos.add(-1, 0, -1), blockPos.add(1, 0, 1))) {
         AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = (AuxiliaryBlockPosAdapter)auxiliaryBlockPosAdapterObj;
         int count4 = postUpdateListener4.computeCount(iBlockAccess.resolveBiomeIDIsOutOfBoundsManager(auxiliaryBlockPosAdapter), auxiliaryBlockPosAdapter);
         count += (count4 & 0xFF0000) >> 16;
         count2 += (count4 & 0xFF00) >> 8;
         count3 += count4 & 0xFF;
      }

      return (count / 9 & 0xFF) << 16 | (count2 / 9 & 0xFF) << 8 | count3 / 9 & 0xFF;
   }

   public static int computeCount2(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return computeCount(iBlockAccess, blockPos, postUpdateListener2);
   }

   public static int computeCount(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return computeCount(iBlockAccess, blockPos, postUpdateListener);
   }

   public static int computeCountByIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos) {
      return computeCount(iBlockAccess, blockPos, postUpdateListener3);
   }
}
