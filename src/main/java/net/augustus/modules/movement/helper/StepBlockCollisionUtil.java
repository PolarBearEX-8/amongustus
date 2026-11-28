package net.augustus.modules.movement.helper;

import net.augustus.modules.render.blocktrace.TraceBlockPos;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;

public class StepBlockCollisionUtil implements MC {

   public static int calculateStepTransitionCost(TraceBlockPos traceBlockPos, TraceBlockPos traceBlockPos2) {
      BlockPos blockPos = traceBlockPos.getBlockPos();
      BlockPos blockPos2 = traceBlockPos2.getBlockPos();
      int n = blockPos2.getX() - blockPos.getX();
      int n2 = blockPos2.getY() - blockPos.getY();
      int n3 = blockPos2.getZ() - blockPos.getZ();
      if (!blockPos2.equalsXZ(blockPos) && isSolidBlock(blockPos2) && !isSolidBlock(blockPos2.add(0, 1, 0)) && !isSolidBlock(blockPos2.add(0, 2, 0))) {
         if (n != 0 && n3 != 0) {
            if (!isSolidBlock(blockPos.add(0, 1, n3)) && !isSolidBlock(blockPos.add(0, 2, n3))
               || !isSolidBlock(blockPos.add(n, 1, 0)) && !isSolidBlock(blockPos.add(n, 2, 0))) {
               if (n2 == -1) {
                  if (!isSolidBlock(blockPos2.add(0, 3, 0))) {
                     return 14;
                  }
               } else {
                  if (n2 != 1) {
                     byte b = 10;
                     if (n != 0 && n3 != 0) {
                        b = 13;
                     }

                     return b;
                  }

                  if (!isSolidBlock(blockPos.add(0, 3, 0)) && !isSolidBlock(blockPos.add(0, 2, 0))) {
                     return 14;
                  }
               }
            }
         } else if (n2 == -1) {
            if (!isSolidBlock(blockPos2.add(0, 3, 0))) {
               return 14;
            }
         } else {
            if (n2 != 1) {
               byte b2 = 10;
               if (n != 0 && n3 != 0) {
                  b2 = 13;
               }

               return b2;
            }

            if (!isSolidBlock(blockPos.add(0, 3, 0)) && !isSolidBlock(blockPos.add(0, 2, 0))) {
               return 14;
            }
         }
      }

      return Integer.MAX_VALUE;
   }

   public static boolean isSolidBlock(BlockPos pos) {
      IBlockState blockState = mc.theWorld.getBlockState(pos);
      return blockState.getBlock().getCollisionBoundingBox(mc.theWorld, blockState, pos.getX(), pos.getY(), pos.getZ()) != null;
   }
}
