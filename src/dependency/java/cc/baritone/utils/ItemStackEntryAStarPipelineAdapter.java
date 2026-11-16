package cc.baritone.utils;

import cc.baritone.api.ItemStackEntry;
import client.util.IBlockState;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class ItemStackEntryAStarPipelineAdapter extends AStarPipeline {
   public int count = 0;

   public ItemStackEntryAStarPipelineAdapter(ActiveListener activeListener, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos, BetterBlockPosXSYSZSBlockPos betterBlockPosXSYSZSBlockPos2) {
      super(activeListener, betterBlockPosXSYSZSBlockPos, betterBlockPosXSYSZSBlockPos2, new BetterBlockPosXSYSZSBlockPos[]{betterBlockPosXSYSZSBlockPos2});
   }

   @Override
   public void updateState() {
      super.updateState();
      this.count = 0;
   }

   @Override
   public Set of() {
      return ImmutableSet.of(this.betterBlockPosXSYSZSBlockPos, this.betterBlockPosXSYSZSBlockPos2);
   }

   @Override
   public double computeValue(ItemStackEntry itemStackEntry) {
      return computeValue(itemStackEntry, this.betterBlockPosXSYSZSBlockPos.count9, this.betterBlockPosXSYSZSBlockPos.count12, this.betterBlockPosXSYSZSBlockPos.count10);
   }

   public static double computeValue(ItemStackEntry itemStackEntry, int count2, int count3, int count4) {
      if (!itemStackEntry.enabled8) {
         return 1000000.0;
      } else if (!AStarPredictor.matchesItemStackEntry(itemStackEntry, count2, count3 - 2, count4)) {
         return 1000000.0;
      } else {
         IBlockState blockState = itemStackEntry.resolveIBlockState(count2, count3 - 1, count4);
         Block block = blockState.getBlock();
         return block != Blocks.block81 && block != Blocks.block144
            ? valueArray[1] + AStarPredictor.computeValue(itemStackEntry, count2, count3 - 1, count4, blockState, false)
            : 6.666666666666667;
      }
   }

   @Override
   public HeuristicPlanner resolveHeuristicPlanner(HeuristicPlanner heuristicPlanner) {
      super.resolveHeuristicPlanner(heuristicPlanner);
      if (heuristicPlanner.getWAITINGCANCELEDEnum() != WAITINGCANCELEDEnum.RUNNING) {
         return heuristicPlanner;
      } else if (this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS().equals(this.betterBlockPosXSYSZSBlockPos2)) {
         return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.SUCCESS);
      } else if (!this.isActiveAsFlag()) {
         return heuristicPlanner.withWAITINGCANCELEDEnum(WAITINGCANCELEDEnum.UNREACHABLE);
      } else {
         double offset = this.staticListener.getEntityPlayerSP().posX - ((double)this.betterBlockPosXSYSZSBlockPos2.getX() + 0.5);
         double offset2 = this.staticListener.getEntityPlayerSP().posZ - ((double)this.betterBlockPosXSYSZSBlockPos2.getZ() + 0.5);
         double offset3 = Math.sqrt(offset * offset + offset2 * offset2);
         if (this.count++ < 10 && offset3 < 0.2) {
            return heuristicPlanner;
         } else {
            AStarPredictor.applyStaticListener(this.staticListener, heuristicPlanner, this.betterBlockPosXSYSZSBlockPosArray[0]);
            return heuristicPlanner;
         }
      }
   }
}
