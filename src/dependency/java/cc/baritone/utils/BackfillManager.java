package cc.baritone.utils;

import cc.baritone.api.INACTIVEHandlerAdapter;
import client.util.LoggerEntryAdapter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.block.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public final class BackfillManager extends INACTIVEHandlerAdapter {
   public HashMap hashMap = new HashMap();

   private boolean matchesBlockPos3(BlockPos blockPos) {
      return this.staticListener.getWorld().getBlockState(blockPos).getBlock() == Blocks.air;
   }

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public String getText() {
      return "Backfill";
   }

   private void updateState2() {
      if (this.staticListener.getOptional().isPresent() && this.baritoneManager.getAlreadyDoingItManager().isActiveAsFlag()) {
         this.hashMap
            .put(this.staticListener.getOptional().get(), this.staticListener.getWorld().getBlockState((BlockPos)this.staticListener.getOptional().get()));
      }
   }

   private boolean matchesBlockPosByBlockPos(BlockPos blockPos) {
      return this.staticListener.getWorld().matchesBlock(Blocks.block115, blockPos, false, EnumFacing.UP, null, null);
   }

   private boolean matchesBlockPos(BlockPos blockPos) {
      DepthFirstCalculator depthFirstCalculator = this.baritoneManager.getAlreadyDoingItManager().getDepthFirstCalculator2();
      return depthFirstCalculator != null && !depthFirstCalculator.isActiveAsFlag() && !depthFirstCalculator.isEnabled()
         ? Arrays.asList(((AStarPipeline)depthFirstCalculator.getCustom().unmodifiableListAsList().get(depthFirstCalculator.getCount())).getBlockPosArray()).contains(blockPos)
         : false;
   }

   @Override
   public void updateState() {
      if (this.hashMap != null && !this.hashMap.isEmpty()) {
         this.hashMap.clear();
      }
   }

   @Override
   public double getValue() {
      return 5.0;
   }

   @Override
   public boolean isEnabled() {
      if (this.staticListener.getEntityPlayerSP() != null && this.staticListener.getWorld() != null) {
         if (!(Boolean)BaritoneManager.getSettings().backfill.dataRef) {
            return false;
         } else if ((Boolean)BaritoneManager.getSettings().allowParkour.dataRef) {
            this.applyText("Backfill cannot be used with allowParkour true");
            BaritoneManager.getSettings().backfill.dataRef = false;
            return false;
         } else {
            for (Object blockPosObj : new java.util.ArrayList(this.hashMap.keySet())) {
               BlockPos blockPos = (BlockPos)blockPosObj;
               if (this.staticListener.getWorld().getLoggerEntry(blockPos) instanceof LoggerEntryAdapter
                  || this.staticListener.getWorld().getBlockState(blockPos).getBlock() != Blocks.air) {
                  this.hashMap.remove(blockPos);
               }
            }

            this.updateState2();
            this.baritoneManager.getMapEntry().clear();
            return !this.getList().isEmpty();
         }
      } else {
         return false;
      }
   }

   public List getList() {
      return ((java.util.stream.Stream<BlockPos>)this.hashMap.keySet().stream())
         .filter(this::matchesBlockPos3)
         .filter(this::matchesBlockPosByBlockPos)
         .filter(this::matchesBlockPos2)
         .sorted(Comparator.comparingDouble(this.staticListener.getEntityPlayerSP()::computeValue).reversed())
         .collect(Collectors.toList());
   }

   @Override
   public Listener5Entry resolveListener5Entry(boolean flag, boolean flag2) {
      if (!flag2) {
         return new Listener5Entry(null, HeuristicCoordinator.REQUEST_PAUSE);
      } else {
         this.baritoneManager.getMapEntry().clear();

         for (Object blockPosObj : this.getList()) {
            BlockPos blockPos = (BlockPos)blockPosObj;
            HeuristicPlanner heuristicPlanner = new HeuristicPlanner();
            switch (CommonCustom.countArray[AStarPredictor.resolveDepthFirstNavigator(heuristicPlanner, this.baritoneManager, blockPos, false, false).ordinal()]) {
               case 1:
                  break;
               case 2:
                  this.baritoneManager.getMapEntry().applyBaseCustom(BaseCustom.CLICK_RIGHT, true);
                  return new Listener5Entry(null, HeuristicCoordinator.REQUEST_PAUSE);
               case 3:
                  this.baritoneManager.getTickListenerAdapter().applyDefaultCustom((DefaultCustom)heuristicPlanner.getAStarHandler().ofNullable().get(), true);
                  return new Listener5Entry(null, HeuristicCoordinator.REQUEST_PAUSE);
               default:
                  throw new IllegalStateException();
            }
         }

         return new Listener5Entry(null, HeuristicCoordinator.DEFER);
      }
   }

   private boolean matchesBlockPos2(BlockPos blockPos) {
      return !this.matchesBlockPos(blockPos);
   }

   public BackfillManager(BaritoneManager baritoneManager) {
      super(baritoneManager);
   }
}
