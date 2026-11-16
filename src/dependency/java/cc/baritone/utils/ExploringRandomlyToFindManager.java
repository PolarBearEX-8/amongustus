package cc.baritone.utils;

import cc.baritone.api.AuxiliaryINACTIVEHandlerExtension;
import cc.baritone.api.INACTIVEHandlerAdapter;
import cc.baritone.api.ItemStackEntry;
import cc.baritone.api.ItemStackEntryAdapter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.util.BlockPos;

public final class ExploringRandomlyToFindManager extends INACTIVEHandlerAdapter implements AuxiliaryINACTIVEHandlerExtension {
   public PrimaryCustom primaryCustom;
   public List list2;
   public List list;
   public BlockPos blockPos;
   public int count2 = 0;
   public int count = 0;

   @Override
   public String getText() {
      return this.list2.isEmpty()
         ? "Exploring randomly to find " + this.primaryCustom + ", no known locations"
         : "Get To " + this.primaryCustom + ", " + this.list2.size() + " known locations";
   }

   private boolean matchesBlockPos(BlockPos blockPos2, BlockPos blockPos3) {
      int count3 = Math.abs(blockPos2.getX() - blockPos3.getX());
      int count4 = Math.abs(blockPos2.getY() - blockPos3.getY());
      int count5 = Math.abs(blockPos2.getZ() - blockPos3.getZ());
      return count3 + count4 + count5 == 1;
   }

   private void applyList(List list3, ItemStackEntry itemStackEntry) {
      this.applyListByList(list3, itemStackEntry);
   }

   @Override
   public synchronized void updateState() {
      this.primaryCustom = null;
      this.list2 = null;
      this.blockPos = null;
      this.list = null;
      this.baritoneManager.getMapEntry().clear();
   }

   private boolean matchesBlock(Block block) {
      return !(Boolean)BaritoneManager.getSettings().enterPortal.dataRef ? false : block == Blocks.blockPortal;
   }

   @Override
   public boolean isEnabled() {
      return this.primaryCustom != null;
   }

   private SecondaryListener resolveSecondaryListener(BlockPos blockPos2) {
      if (this.matchesBlock(this.primaryCustom.getBlock())) {
         return new HeuristicWatcher(blockPos2);
      } else {
         return (SecondaryListener)(this.matchesBlockByBlock(this.primaryCustom.getBlock())
               && this.baritoneManager.heuristicManager.resolveIBlockState(blockPos2.up()).getBlock().isActive4()
            ? new DepthFirstResolver(blockPos2.up())
            : new AStarManager(blockPos2));
      }
   }

   @Override
   public void applyPrimaryCustom(PrimaryCustom primaryCustom2) {
      this.updateState();
      this.primaryCustom = primaryCustom2;
      this.blockPos = this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS();
      this.list = new java.util.ArrayList();
      this.count = 0;
      this.applyListByList(new java.util.ArrayList(), new ItemStackEntryAdapter(this, false));
   }

   public static BaritoneManager resolveBaritoneManager(ExploringRandomlyToFindManager exploringRandomlyToFindManager) {
      return exploringRandomlyToFindManager.baritoneManager;
   }

   @Override
   public synchronized Listener5Entry resolveListener5Entry(boolean flag, boolean flag2) {
      if (this.list2 == null) {
         this.applyListByList(new java.util.ArrayList(), new ItemStackEntryAdapter(this, false));
      }

      if (this.list2.isEmpty()) {
         if ((Boolean)BaritoneManager.getSettings().exploreForBlocks.dataRef && !flag) {
            return new Listener5Entry(new HeuristicController(this, 1.0, new BlockPos[]{this.blockPos}), HeuristicCoordinator.FORCE_REVALIDATE_GOAL_AND_PATH);
         } else {
            this.applyText("No known locations of " + this.primaryCustom + ", canceling GetToBlock");
            if (flag2) {
               this.updateState();
            }

            return new Listener5Entry(null, HeuristicCoordinator.CANCEL_AND_SET_GOAL);
         }
      } else {
         DepthFirstCoordinator depthFirstCoordinator = new DepthFirstCoordinator(
            (SecondaryListener[])this.list2.stream().map(item -> this.resolveSecondaryListener((BlockPos)item)).toArray(ExploringRandomlyToFindManager::resolveSecondaryListenerArray)
         );
         if (flag) {
            if ((Boolean)BaritoneManager.getSettings().blacklistClosestOnFailure.dataRef) {
               this.applyText("Unable to find any path to " + this.primaryCustom + ", blacklisting presumably unreachable closest instances...");
               this.isActiveAsFlag();
               return this.resolveListener5Entry(false, flag2);
            } else {
               this.applyText("Unable to find any path to " + this.primaryCustom + ", canceling GetToBlock");
               if (flag2) {
                  this.updateState();
               }

               return new Listener5Entry(depthFirstCoordinator, HeuristicCoordinator.CANCEL_AND_SET_GOAL);
            }
         } else {
            int dataRef = (Integer)BaritoneManager.getSettings().mineGoalUpdateInterval.dataRef;
            if (dataRef != 0 && this.count2++ % dataRef == 0) {
               java.util.ArrayList list3 = new java.util.ArrayList(this.list2);
               ItemStackEntryAdapter itemStackEntryAdapter = new ItemStackEntryAdapter(this, true);
               BaritoneManager.getExecutor().execute(() -> this.applyList(list3, itemStackEntryAdapter));
            }

            if (depthFirstCoordinator.matchesBlockPos(this.staticListener.getBetterBlockPosXSYSZSBlockPosAsBetterBlockPosXS())
               && depthFirstCoordinator.matchesBlockPos(this.baritoneManager.getAlreadyDoingItManager().getBetterBlockPosXSYSZSBlockPos())
               && flag2) {
               if (!this.matchesBlock2(this.primaryCustom.getBlock())) {
                  this.updateState();
                  return new Listener5Entry(null, HeuristicCoordinator.CANCEL_AND_SET_GOAL);
               }

               if (this.isActive2()) {
                  this.updateState();
                  return new Listener5Entry(null, HeuristicCoordinator.CANCEL_AND_SET_GOAL);
               }
            }

            return new Listener5Entry(depthFirstCoordinator, HeuristicCoordinator.REVALIDATE_GOAL_AND_PATH);
         }
      }
   }

   private boolean matchesBlock2(Block block) {
      return !(Boolean)BaritoneManager.getSettings().rightClickContainerOnArrival.dataRef
         ? false
         : block == Blocks.block46
            || block == Blocks.block39
            || block == Blocks.block88
            || block == Blocks.block60
            || block == Blocks.blockTrappedChest
            || block == Blocks.block128;
   }

   private synchronized void applyListByList(List list3, ItemStackEntry itemStackEntry) {
      List list4 = MineINACTIVEHandlerAdapter.resolveList(
         itemStackEntry, new HeuristicSystem(new PrimaryCustom[]{this.primaryCustom}), 64, list3, this.list, Collections.emptyList()
      );
      list4.removeIf(this.list::contains);
      this.list2 = list4;
   }

   private static SecondaryListener[] resolveSecondaryListenerArray(int count3) {
      return new SecondaryListener[count3];
   }

   private boolean matchesBlockByBlock(Block block) {
      return !this.matchesBlock2(block) ? false : block == Blocks.block60 || block == Blocks.blockTrappedChest || block == Blocks.block128;
   }

   private boolean isActive2() {
      for (BlockPos blockPos2 : (Iterable<BlockPos>)(Iterable<?>)this.list2) {
         Optional optional = BreadthFirstRecorder.resolveOptional(this.staticListener, blockPos2, this.staticListener.getDefaultListener().getValue());
         if (optional.isPresent()) {
            this.baritoneManager.getTickListenerAdapter().applyDefaultCustom((DefaultCustom)optional.get(), true);
            if (this.list2.contains(this.staticListener.getOptional().orElse(null))) {
               this.baritoneManager.getMapEntry().applyBaseCustom(BaseCustom.CLICK_RIGHT, true);
               System.out.println(this.staticListener.getEntityPlayerSP().container2);
               if (!(this.staticListener.getEntityPlayerSP().container2 instanceof ContainerPlayer)) {
                  return true;
               }
            }

            if (this.count++ > 20) {
               this.applyText("Right click timed out");
               return true;
            }

            return false;
         }
      }

      this.applyText("Arrived but failed to right click open");
      return true;
   }

   @Override
   public synchronized boolean isActiveAsFlag() {
      java.util.ArrayList list3 = new java.util.ArrayList();
      this.list2.stream().min(Comparator.comparingDouble(this.staticListener.getEntityPlayerSP()::computeValue)).ifPresent(list3::add);

      outerLoop:
      while (true) {
         for (BlockPos blockPos2 : (Iterable<BlockPos>)(Iterable<?>)this.list2) {
            for (BlockPos blockPos3 : (Iterable<BlockPos>)(Iterable<?>)list3) {
               if (this.matchesBlockPos(blockPos2, blockPos3)) {
                  list3.add(blockPos2);
                  this.list2.remove(blockPos2);
                  continue outerLoop;
               }
            }
         }

         switch (list3.size()) {
            default:
               this.applyTextByText("Blacklisting unreachable locations " + list3);
               this.list.addAll(list3);
               return !list3.isEmpty();
         }
      }
   }

   public ExploringRandomlyToFindManager(BaritoneManager baritoneManager) {
      super(baritoneManager);
   }
}
