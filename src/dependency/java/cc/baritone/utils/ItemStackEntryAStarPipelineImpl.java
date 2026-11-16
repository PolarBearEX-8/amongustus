package cc.baritone.utils;

import cc.baritone.api.ItemStackEntry;
import client.util.IBlockState;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CustomBlockAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class ItemStackEntryAStarPipelineImpl extends AStarPipeline {
   public int count = 0;
   public boolean enabled = false;

   @Override
   public double computeValue(ItemStackEntry itemStackEntry) {
      HeuristicCalculator heuristicCalculator = new HeuristicCalculator();
      applyItemStackEntry(
         itemStackEntry,
         this.betterBlockPosXSYSZSBlockPos.count9,
         this.betterBlockPosXSYSZSBlockPos.count12,
         this.betterBlockPosXSYSZSBlockPos.count10,
         this.betterBlockPosXSYSZSBlockPos2.count9,
         this.betterBlockPosXSYSZSBlockPos2.count10,
         heuristicCalculator
      );
      return heuristicCalculator.count2 != this.betterBlockPosXSYSZSBlockPos2.count12 ? 1000000.0 : heuristicCalculator.value;
   }

   @Override
   public Set of() {
      return ImmutableSet.of(
         this.betterBlockPosXSYSZSBlockPos, this.betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos3(), this.betterBlockPosXSYSZSBlockPos2
      );
   }

   @Override
   public void updateState() {
      super.updateState();
      this.count = 0;
      this.enabled = false;
   }

   public boolean isActive2() {
      if (this.enabled) {
         return true;
      } else {
         BlockPos blockPos = this.betterBlockPosXSYSZSBlockPos2
            .getBlockPos(this.betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos4())
            .getBlockPos2(this.betterBlockPosXSYSZSBlockPos2);
         if (this.isActive3()) {
            return true;
         } else {
            for (int index = 0; index <= 2; index++) {
               if (AStarPredictor.matchesBlockByBlock(HeuristicManager.resolveBlock(this.staticListener, blockPos.resolveBlockPos2(index)))) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   public static boolean matchesItemStackEntry(
      ItemStackEntry itemStackEntry, int count2, int count3, int count4, int count5, int count6, double offset, IBlockState blockState, HeuristicCalculator heuristicCalculator
   ) {
      if (offset != 0.0 && itemStackEntry.resolveIBlockState(count5, count3 + 2, count6).getBlock() instanceof CustomBlockAdapter) {
         return false;
      } else if (!AStarPredictor.matchesItemStackEntry2(itemStackEntry, count5, count3 - 2, count6, blockState)) {
         return false;
      } else {
         double offset2 = 0.0;
         int count7 = count3;
         int count8 = 3;

         while (true) {
            int count9 = count3 - count8;
            if (count9 < 0) {
               return false;
            }

            boolean flag = count8 >= itemStackEntry.count;
            IBlockState blockState2 = itemStackEntry.resolveIBlockState(count5, count9, count6);
            int count10 = count8 - (count3 - count7);
            double offset3 = 3.7062775075283763 + valueArray[count10] + offset + offset2;
            if (flag && AStarPredictor.matchesBlock(blockState2.getBlock())) {
               if (!AStarPredictor.matchesItemStackEntry2(itemStackEntry, count5, count9, count6, blockState2)) {
                  return false;
               }

               if (itemStackEntry.enabled7) {
                  return false;
               }

               if (AStarPredictor.matchesIBlockStateByIBlockState(count5, count9, count6, blockState2, itemStackEntry.heuristicManager)) {
                  return false;
               }

               if (!AStarPredictor.matchesItemStackEntry(itemStackEntry, count5, count9 - 1, count6)) {
                  return false;
               }

               heuristicCalculator.count3 = count5;
               heuristicCalculator.count2 = count9;
               heuristicCalculator.count = count6;
               heuristicCalculator.value = offset3;
               return false;
            }

            if (flag && itemStackEntry.enabled3 && AStarPredictor.matchesBlock2(blockState2.getBlock())) {
               heuristicCalculator.count3 = count5;
               heuristicCalculator.count2 = count9;
               heuristicCalculator.count = count6;
               heuristicCalculator.value = offset3;
               return false;
            }

            if (count10 > 11 || blockState2.getBlock() != Blocks.block144 && blockState2.getBlock() != Blocks.block81) {
               if (!AStarPredictor.matchesItemStackEntry2(itemStackEntry, count5, count9, count6, blockState2)) {
                  if (!AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count5, count9, count6, blockState2)) {
                     return false;
                  }

                  if (AStarPredictor.matchesIBlockStateByIBlockState(blockState2)) {
                     return false;
                  }

                  if (flag && count10 <= itemStackEntry.count3 + 1) {
                     heuristicCalculator.count3 = count5;
                     heuristicCalculator.count2 = count9 + 1;
                     heuristicCalculator.count = count6;
                     heuristicCalculator.value = offset3;
                     return false;
                  }

                  if (flag && itemStackEntry.enabled14 && count10 <= itemStackEntry.count2 + 1) {
                     heuristicCalculator.count3 = count5;
                     heuristicCalculator.count2 = count9 + 1;
                     heuristicCalculator.count = count6;
                     heuristicCalculator.value = offset3 + itemStackEntry.getValue4();
                     return true;
                  }

                  return false;
               }
            } else {
               offset2 = offset2 + valueArray[count10 - 1] + 6.666666666666667;
               count7 = count9;
            }

            count8++;
         }
      }
   }

   public static void applyItemStackEntry(ItemStackEntry itemStackEntry, int count2, int count3, int count4, int count5, int count6, HeuristicCalculator heuristicCalculator) {
      double offset = 0.0;
      IBlockState blockState = itemStackEntry.resolveIBlockState(count5, count3 - 1, count6);
      double offset2 = offset + AStarPredictor.computeValue(itemStackEntry, count5, count3 - 1, count6, blockState, false);
      if (!(offset2 >= 1000000.0)) {
         double offset3 = offset2 + AStarPredictor.computeValue(itemStackEntry, count5, count3, count6, false);
         if (!(offset3 >= 1000000.0)) {
            double offset4 = offset3 + AStarPredictor.computeValue(itemStackEntry, count5, count3 + 1, count6, true);
            if (!(offset4 >= 1000000.0)) {
               Block block = itemStackEntry.resolveIBlockState(count2, count3 - 1, count4).getBlock();
               if (block != Blocks.block81 && block != Blocks.block144) {
                  IBlockState blockState2 = itemStackEntry.resolveIBlockState(count5, count3 - 2, count6);
                  if (!AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count5, count3 - 2, count6, blockState2)) {
                     matchesItemStackEntry(itemStackEntry, count2, count3, count4, count5, count6, offset4, blockState2, heuristicCalculator);
                  } else if (blockState.getBlock() != Blocks.block81 && blockState.getBlock() != Blocks.block144) {
                     double offset5 = 3.7062775075283763;
                     if (block == Blocks.block126) {
                        offset5 *= 2.0;
                     }

                     double offset6 = offset4 + offset5 + Math.max(valueArray[1], 0.9265693768820937);
                     heuristicCalculator.count3 = count5;
                     heuristicCalculator.count2 = count3 - 1;
                     heuristicCalculator.count = count6;
                     heuristicCalculator.value = offset6;
                  }
               }
            }
         }
      }
   }

   public void updateState3() {
      this.enabled = true;
   }

   public boolean isActive3() {
      BlockPos blockPos = this.betterBlockPosXSYSZSBlockPos2
         .getBlockPos(this.betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos4())
         .getBlockPos2(this.betterBlockPosXSYSZSBlockPos2);
      return !AStarPredictor.matchesStaticListenerByStaticListener(this.staticListener, new BetterBlockPosXSYSZSBlockPos(blockPos))
         && AStarPredictor.matchesStaticListenerByStaticListener(this.staticListener, new BetterBlockPosXSYSZSBlockPos(blockPos).getBetterBlockPosXSYSZSBlockPos3())
         && AStarPredictor.matchesStaticListenerByStaticListener(
            this.staticListener, new BetterBlockPosXSYSZSBlockPos(blockPos).resolveBetterBlockPosXSYSZSBlockPos2(2)
         );
   }

   public ItemStackEntryAStarPipelineImpl(ActiveListener activeListener, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2) {
      super(
         activeListener,
         betterBlockPosXSYSZSBlockPos,
         betterBlockPosXSYSZSBlockPos2,
         new BetterBlockPosXSYSZSBlockPos[]{betterBlockPosXSYSZSBlockPos2.resolveBetterBlockPosXSYSZSBlockPos2(2), betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos3(), betterBlockPosXSYSZSBlockPos2},
         betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos4()
      );
   }

   @Override
   public HeuristicPlanner resolveHeuristicPlanner(HeuristicPlanner heuristicPlanner) {
      super.resolveHeuristicPlanner(heuristicPlanner);
      if (heuristicPlanner.getWAITINGCANCELEDEnum() != WAITINGCANCELEDEnum.RUNNING) {
         return heuristicPlanner;
      } else {
         BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS = this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
         BlockPos blockPos = new BlockPos(
            this.betterBlockPosXSYSZSBlockPos2.getX() * 2 - this.betterBlockPosXSYSZSBlockPos.getX(),
            this.betterBlockPosXSYSZSBlockPos2.getY(),
            this.betterBlockPosXSYSZSBlockPos2.getZ() * 2 - this.betterBlockPosXSYSZSBlockPos.getZ()
         );
         if (!betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.equals(this.betterBlockPosXSYSZSBlockPos2) && !betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.equals(blockPos)
            || !AStarPredictor.matchesStaticListener2(this.staticListener, this.betterBlockPosXSYSZSBlockPos2)
               && !(this.staticListener.getEntityPlayerSP().posY - (double)this.betterBlockPosXSYSZSBlockPos2.getY() < 0.5)) {
            if (this.isActive2()) {
               double offset = ((double)this.betterBlockPosXSYSZSBlockPos.getX() + 0.5) * 0.17 + ((double)this.betterBlockPosXSYSZSBlockPos2.getX() + 0.5) * 0.83;
               double offset2 = ((double)this.betterBlockPosXSYSZSBlockPos.getZ() + 0.5) * 0.17 + ((double)this.betterBlockPosXSYSZSBlockPos2.getZ() + 0.5) * 0.83;
               heuristicPlanner.withAStarHandler(
                     new AStarHandler(
                        BreadthFirstRecorder.resolveDefaultCustom(
                              this.staticListener.getVecAsVec3(),
                              new Vec3(offset, (double)this.betterBlockPosXSYSZSBlockPos2.getY(), offset2),
                              this.staticListener.getDefaultCustom()
                           )
                           .resolveDefaultCustom(this.staticListener.getDefaultCustom().getFactor2()),
                        false
                     )
                  )
                  .resolveHeuristicPlanner(BaseCustom.MOVE_FORWARD, true);
               return heuristicPlanner;
            } else {
               double offset3 = this.staticListener.getEntityPlayerSP().posX - ((double)this.betterBlockPosXSYSZSBlockPos2.getX() + 0.5);
               double offset4 = this.staticListener.getEntityPlayerSP().posZ - ((double)this.betterBlockPosXSYSZSBlockPos2.getZ() + 0.5);
               double offset5 = Math.sqrt(offset3 * offset3 + offset4 * offset4);
               double offset6 = this.staticListener.getEntityPlayerSP().posX - ((double)this.betterBlockPosXSYSZSBlockPos.getX() + 0.5);
               double offset7 = this.staticListener.getEntityPlayerSP().posZ - ((double)this.betterBlockPosXSYSZSBlockPos.getZ() + 0.5);
               double offset8 = Math.sqrt(offset6 * offset6 + offset7 * offset7);
               if (!betterBlockPosXSYSZSBlockPosAsBetterBlockPosXS.equals(this.betterBlockPosXSYSZSBlockPos2) || offset5 > 0.25) {
                  if (this.count++ < 20 && offset8 < 1.25) {
                     AStarPredictor.applyStaticListener(this.staticListener, heuristicPlanner, blockPos);
                  } else {
                     AStarPredictor.applyStaticListener(this.staticListener, heuristicPlanner, this.betterBlockPosXSYSZSBlockPos2);
                  }
               }

               return heuristicPlanner;
            }
         } else {
            return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.SUCCESS);
         }
      }
   }
}
