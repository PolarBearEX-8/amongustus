package client.util;

import cc.baritone.pathing.PathPoint;
import net.minecraft.util.BlockPos;

public class BlockPosMinecraftAccessor implements MinecraftAccessor {
   public static boolean matchesBlockPos(BlockPos blockPos) {
      IBlockState blockState = MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos);
      return blockState.getBlock().processAction(MinecraftAccessor.minecraft.theWorld, blockState, blockPos.getX(), blockPos.getY(), blockPos.getZ()) != null;
   }

   public static int computeCount(PathPoint pathPoint, PathPoint pathPoint2) {
      BlockPos blockPos = pathPoint.getBlockPos();
      BlockPos blockPos2 = pathPoint2.getBlockPos();
      int count = blockPos2.getX() - blockPos.getX();
      int count2 = blockPos2.getY() - blockPos.getY();
      int count3 = blockPos2.getZ() - blockPos.getZ();
      if (!blockPos2.checkBlockPos(blockPos) && matchesBlockPos(blockPos2) && !matchesBlockPos(blockPos2.add(0, 1, 0)) && !matchesBlockPos(blockPos2.add(0, 2, 0))) {
         if (count != 0 && count3 != 0) {
            if (!matchesBlockPos(blockPos.add(0, 1, count3)) && !matchesBlockPos(blockPos.add(0, 2, count3))
               || !matchesBlockPos(blockPos.add(count, 1, 0)) && !matchesBlockPos(blockPos.add(count, 2, 0))) {
               if (count2 == -1) {
                  if (!matchesBlockPos(blockPos2.add(0, 3, 0))) {
                     return 14;
                  }
               } else {
                  if (count2 != 1) {
                     byte byteValue = 10;
                     if (count != 0 && count3 != 0) {
                        byteValue = 13;
                     }

                     return byteValue;
                  }

                  if (!matchesBlockPos(blockPos.add(0, 3, 0)) && !matchesBlockPos(blockPos.add(0, 2, 0))) {
                     return 14;
                  }
               }
            }
         } else if (count2 == -1) {
            if (!matchesBlockPos(blockPos2.add(0, 3, 0))) {
               return 14;
            }
         } else {
            if (count2 != 1) {
               byte byteValue2 = 10;
               if (count != 0 && count3 != 0) {
                  byteValue2 = 13;
               }

               return byteValue2;
            }

            if (!matchesBlockPos(blockPos.add(0, 3, 0)) && !matchesBlockPos(blockPos.add(0, 2, 0))) {
               return 14;
            }
         }
      }

      return Integer.MAX_VALUE;
   }
}
