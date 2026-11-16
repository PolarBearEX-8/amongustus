package cc.baritone.utils;

import cc.baritone.api.ItemStackEntry;
import client.util.IBlockState;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import net.minecraft.block.Blocks;
import net.minecraft.block.CustomBlockAdapter;
import net.minecraft.util.EnumFacing;

public class HeuristicPlannerAStarPipeline extends AStarPipeline {
   public int count = 0;

   @Override
   public boolean matchesHeuristicPlanner(HeuristicPlanner heuristicPlanner) {
      return heuristicPlanner.getWAITINGCANCELEDEnum() != WAITINGCANCELEDEnum.RUNNING || this.count == 0;
   }

   public boolean isActive2() {
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = this.betterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos2(2);

      for (int index = 0; index < 4; index++) {
         BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2 = betterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos(EnumFacing.resolveEnumFacing(index));
         if (!AStarPredictor.matchesStaticListenerByStaticListener(this.staticListener, betterBlockPosXSYSZSBlockPos2)) {
            return false;
         }
      }

      return true;
   }

   public HeuristicPlannerAStarPipeline(ActiveListener activeListener, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2) {
      super(
         activeListener,
         betterBlockPosXSYSZSBlockPos,
         betterBlockPosXSYSZSBlockPos2,
         new BetterBlockPosXSYSZSBlockPos[]{betterBlockPosXSYSZSBlockPos2, betterBlockPosXSYSZSBlockPos.resolveBetterBlockPosXSYSZSBlockPos2(2), betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos3()},
         betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos4()
      );
   }

   @Override
   public double computeValue(ItemStackEntry itemStackEntry) {
      return computeValue(
         itemStackEntry,
         this.betterBlockPosXSYSZSBlockPos.count9,
         this.betterBlockPosXSYSZSBlockPos.count12,
         this.betterBlockPosXSYSZSBlockPos.count10,
         this.betterBlockPosXSYSZSBlockPos2.count9,
         this.betterBlockPosXSYSZSBlockPos2.count10
      );
   }

   @Override
   public HeuristicPlanner resolveHeuristicPlanner(HeuristicPlanner heuristicPlanner) {
      if (this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().count12 < this.betterBlockPosXSYSZSBlockPos.count12) {
         return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.UNREACHABLE);
      } else {
         super.resolveHeuristicPlanner(heuristicPlanner);
         if (heuristicPlanner.getWAITINGCANCELEDEnum() != WAITINGCANCELEDEnum.RUNNING) {
            return heuristicPlanner;
         } else if (!this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().equals(this.betterBlockPosXSYSZSBlockPos2)
            && !this.staticListener
               .getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS()
               .equals(this.betterBlockPosXSYSZSBlockPos2.getBlockPos2(this.getBlockPos().down()))) {
            IBlockState blockState = HeuristicManager.resolveIBlockState(this.staticListener, this.betterBlockPosXSYSZSBlockPos3);
            if (!AStarPredictor.matchesStaticListener(this.staticListener, this.betterBlockPosXSYSZSBlockPos3, blockState)) {
               this.count++;
               if (AStarPredictor.resolveDepthFirstNavigator(
                     heuristicPlanner, this.activeListener, this.betterBlockPosXSYSZSBlockPos2.getBetterBlockPosXSYSZSBlockPos4(), false, true
                  )
                  == DepthFirstNavigator.READY_TO_PLACE) {
                  heuristicPlanner.resolveHeuristicPlanner(BaseCustom.SNEAK, true);
                  if (this.staticListener.getEntityPlayerSP().isSprinting()) {
                     heuristicPlanner.resolveHeuristicPlanner(BaseCustom.CLICK_RIGHT, true);
                  }
               }

               if (this.count > 10) {
                  heuristicPlanner.resolveHeuristicPlanner(BaseCustom.MOVE_BACK, true);
               }

               return heuristicPlanner;
            } else {
               AStarPredictor.applyStaticListener(this.staticListener, heuristicPlanner, this.betterBlockPosXSYSZSBlockPos2);
               if (AStarPredictor.matchesIBlockStateByIBlockState(blockState)
                  && !AStarPredictor.matchesIBlockStateByIBlockState(
                     HeuristicManager.resolveIBlockState(this.staticListener, this.betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos4())
                  )) {
                  return heuristicPlanner;
               } else if (!(Boolean)BaritoneManager.getSettings().assumeStep.dataRef
                  && !this.staticListener
                     .getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS()
                     .equals(this.betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos3())) {
                  int count2 = Math.abs(this.betterBlockPosXSYSZSBlockPos.getX() - this.betterBlockPosXSYSZSBlockPos2.getX());
                  int count3 = Math.abs(this.betterBlockPosXSYSZSBlockPos.getZ() - this.betterBlockPosXSYSZSBlockPos2.getZ());
                  double offset = (double)count2 * Math.abs((double)this.betterBlockPosXSYSZSBlockPos2.getX() + 0.5 - this.staticListener.getEntityPlayerSP().posX)
                     + (double)count3 * Math.abs((double)this.betterBlockPosXSYSZSBlockPos2.getZ() + 0.5 - this.staticListener.getEntityPlayerSP().posZ);
                  double offset2 = (double)count3 * Math.abs((double)this.betterBlockPosXSYSZSBlockPos2.getX() + 0.5 - this.staticListener.getEntityPlayerSP().posX)
                     + (double)count2 * Math.abs((double)this.betterBlockPosXSYSZSBlockPos2.getZ() + 0.5 - this.staticListener.getEntityPlayerSP().posZ);
                  if (Math.abs((double)count2 * this.staticListener.getEntityPlayerSP().motionZ + (double)count3 * this.staticListener.getEntityPlayerSP().motionX)
                     > 0.1) {
                     return heuristicPlanner;
                  } else if (this.isActive2()) {
                     return heuristicPlanner.resolveHeuristicPlanner(BaseCustom.JUMP, true);
                  } else {
                     return !(offset > 1.2) && !(offset2 > 0.2) ? heuristicPlanner.resolveHeuristicPlanner(BaseCustom.JUMP, true) : heuristicPlanner;
                  }
               } else {
                  return heuristicPlanner;
               }
            }
         } else {
            return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.SUCCESS);
         }
      }
   }

   public static double computeValue(ItemStackEntry itemStackEntry, int count2, int count3, int count4, int count5, int count6) {
      IBlockState blockState = itemStackEntry.resolveIBlockState(count5, count3, count6);
      double offset = 0.0;
      if (!AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count5, count3, count6, blockState)) {
         offset = itemStackEntry.computeValueByIBlockState(count5, count3, count6, blockState);
         if (offset >= 1000000.0) {
            return 1000000.0;
         }

         if (!AStarPredictor.matchesIBlockState2(count5, count3, count6, blockState, itemStackEntry.heuristicManager)) {
            return 1000000.0;
         }

         boolean flag = false;

         for (int index = 0; index < 5; index++) {
            int count7 = count5 + enumFacingArray[index].getInt2();
            int count8 = count3 + enumFacingArray[index].getInt3();
            int count9 = count6 + enumFacingArray[index].getInt();
            if ((count7 != count2 || count9 != count4) && AStarPredictor.matchesHeuristicManager2(itemStackEntry.heuristicManager, count7, count8, count9)) {
               flag = true;
               break;
            }
         }

         if (!flag) {
            return 1000000.0;
         }
      }

      IBlockState blockState2 = itemStackEntry.resolveIBlockState(count2, count3 + 2, count4);
      if (!(itemStackEntry.resolveIBlockState(count2, count3 + 3, count4).getBlock() instanceof CustomBlockAdapter)
         || !AStarPredictor.matchesItemStackEntryByItemStackEntry(itemStackEntry, count2, count3 + 1, count4) && blockState2.getBlock() instanceof CustomBlockAdapter) {
         IBlockState blockState3 = itemStackEntry.resolveIBlockState(count2, count3 - 1, count4);
         if (blockState3.getBlock() != Blocks.block81 && blockState3.getBlock() != Blocks.block144) {
            boolean flag2 = AStarPredictor.matchesIBlockStateByIBlockState(blockState3);
            boolean flag3 = AStarPredictor.matchesIBlockStateByIBlockState(blockState);
            if (flag2 && !flag3) {
               return 1000000.0;
            } else {
               double offset2;
               if (flag3) {
                  if (flag2) {
                     offset2 = Math.max(value14, 4.63284688441047) + itemStackEntry.value;
                  } else {
                     offset2 = 4.63284688441047;
                  }
               } else {
                  double offset3;
                  if (blockState.getBlock() == Blocks.block126) {
                     offset3 = 9.26569376882094;
                  } else {
                     offset3 = Math.max(value14, 4.63284688441047);
                  }

                  offset2 = offset3 + itemStackEntry.value;
               }

               double offset4 = offset2 + offset + AStarPredictor.computeValue(itemStackEntry, count2, count3 + 2, count4, blockState2, false);
               if (offset4 >= 1000000.0) {
                  return 1000000.0;
               } else {
                  double offset5 = offset4 + AStarPredictor.computeValue(itemStackEntry, count5, count3 + 1, count6, false);
                  return offset5 >= 1000000.0 ? 1000000.0 : offset5 + AStarPredictor.computeValue(itemStackEntry, count5, count3 + 2, count6, true);
               }
            }
         } else {
            return 1000000.0;
         }
      } else {
         return 1000000.0;
      }
   }

   @Override
   public Set of() {
      BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos = new BetterBlockPosXSYSZSBlockPos(this.betterBlockPosXSYSZSBlockPos.getBlockPos(this.getBlockPos()).up());
      return ImmutableSet.of(
         this.betterBlockPosXSYSZSBlockPos,
         this.betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos3(),
         this.betterBlockPosXSYSZSBlockPos2,
         betterBlockPosXSYSZSBlockPos,
         betterBlockPosXSYSZSBlockPos.getBetterBlockPosXSYSZSBlockPos3()
      );
   }

   @Override
   public void updateState() {
      super.updateState();
      this.count = 0;
   }
}
