package baritone.process;

import baritone.api.pathing.goals.GoalRunAway;
import baritone.Baritone;
import baritone.api.pathing.goals.Goal;
import baritone.api.pathing.goals.GoalBlock;
import baritone.api.pathing.goals.GoalComposite;
import baritone.api.pathing.goals.GoalGetToBlock;
import baritone.api.pathing.goals.GoalTwoBlocks;
import baritone.api.process.IGetToBlockProcess;
import baritone.api.process.PathingCommand;
import baritone.api.process.PathingCommandType;
import baritone.api.utils.BlockOptionalMeta;
import baritone.api.utils.BlockOptionalMetaLookup;
import baritone.api.utils.Rotation;
import baritone.api.utils.RotationUtils;
import baritone.api.utils.input.Input;
import baritone.pathing.movement.CalculationContext;
import baritone.utils.BaritoneProcessHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.util.BlockPos;

public final class GetToBlockProcess extends BaritoneProcessHelper implements IGetToBlockProcess {
   private BlockOptionalMeta gettingTo;
   private List knownLocations;
   private List blacklist;
   private BlockPos start;
   private int tickCount = 0;
   private int arrivalTickCount = 0;

   private boolean walkIntoBlock(Block block) {
      return !(Boolean)Baritone.settings().enterPortal.value ? false : block == Blocks.portal;
   }

   private boolean rightClickOnArrival(Block block) {
      return !(Boolean)Baritone.settings().rightClickContainerOnArrival.value
         ? false
         : block == Blocks.crafting_table
            || block == Blocks.furnace
            || block == Blocks.lit_furnace
            || block == Blocks.ender_chest
            || block == Blocks.chest
            || block == Blocks.trapped_chest;
   }

   @Override
   public boolean isActive() {
      return this.gettingTo != null;
   }

   @Override
   public synchronized PathingCommand onTick(boolean flag, boolean flag2) {
      if (this.knownLocations == null) {
         this.rescan(new ArrayList(), new GetToBlockCalculationContext(this, false));
      }

      if (this.knownLocations.isEmpty()) {
         if ((Boolean)Baritone.settings().exploreForBlocks.value && !flag) {
            return new PathingCommand(new GetToBlockProcess.DefaultGoalRunAway(this, 1.0, new BlockPos[]{this.start}), PathingCommandType.FORCE_REVALIDATE_GOAL_AND_PATH);
         } else {
            this.logDirect2("No known locations of " + this.gettingTo + ", canceling GetToBlock");
            if (flag2) {
               this.onLostControl();
            }

            return new PathingCommand(null, PathingCommandType.CANCEL_AND_SET_GOAL);
         }
      } else {
         GoalComposite goalComposite = new GoalComposite(
            (Goal[])this.knownLocations
               .stream()
               .map(
                  obj -> {
                     if (this.walkIntoBlock(this.gettingTo.getBlock())) {
                        return new GoalTwoBlocks((BlockPos)obj);
                     } else {
                        return (Goal)(this.blockOnTopMustBeRemoved(this.gettingTo.getBlock()) && this.baritone.bsi.get0(((BlockPos)obj).up()).getBlock().isBlockNormalCube()
                           ? new GoalBlock(((BlockPos)obj).up())
                           : new GoalGetToBlock((BlockPos)obj));
                     }
                  }
               )
               .toArray(Goal[]::new)
         );
         if (flag) {
            if ((Boolean)Baritone.settings().blacklistClosestOnFailure.value) {
               this.logDirect2("Unable to find any path to " + this.gettingTo + ", blacklisting presumably unreachable closest instances...");
               this.blacklistClosest();
               return this.onTick(false, flag2);
            } else {
               this.logDirect2("Unable to find any path to " + this.gettingTo + ", canceling GetToBlock");
               if (flag2) {
                  this.onLostControl();
               }

               return new PathingCommand(goalComposite, PathingCommandType.CANCEL_AND_SET_GOAL);
            }
         } else {
            int value = (Integer)Baritone.settings().mineGoalUpdateInterval.value;
            if (value != 0 && this.tickCount++ % value == 0) {
               ArrayList list = new ArrayList(this.knownLocations);
               GetToBlockCalculationContext context = new GetToBlockCalculationContext(this, true);
               Baritone.getThreadPool().execute(() -> this.rescan(list, context));
            }

            if (goalComposite.isInGoal(this.ctx.playerFeet()) && goalComposite.isInGoal(this.baritone.getPathingBehavior2().pathStart()) && flag2) {
               if (!this.rightClickOnArrival(this.gettingTo.getBlock())) {
                  this.onLostControl();
                  return new PathingCommand(null, PathingCommandType.CANCEL_AND_SET_GOAL);
               }

               if (this.rightClick()) {
                  this.onLostControl();
                  return new PathingCommand(null, PathingCommandType.CANCEL_AND_SET_GOAL);
               }
            }

            return new PathingCommand(goalComposite, PathingCommandType.REVALIDATE_GOAL_AND_PATH);
         }
      }
   }

   @Override
   public synchronized void onLostControl() {
      this.gettingTo = null;
      this.knownLocations = null;
      this.start = null;
      this.blacklist = null;
      this.baritone.getInputOverrideHandler2().clearAllKeys();
   }

   @Override
   public synchronized boolean blacklistClosest() {
      ArrayList list = new ArrayList();
      this.knownLocations.stream().min(Comparator.comparingDouble(obj -> this.ctx.player().getDistanceSq((BlockPos)obj))).ifPresent(list::add);

      outerLoop:
      while (true) {
         for (BlockPos pos : (Iterable<BlockPos>)(this.knownLocations)) {
            for (BlockPos pos2 : (Iterable<BlockPos>)(list)) {
               if (this.areAdjacent(pos, pos2)) {
                  list.add(pos);
                  this.knownLocations.remove(pos);
                  continue outerLoop;
               }
            }
         }

         switch (list.size()) {
            default:
               this.logDebug("Blacklisting unreachable locations " + list);
               this.blacklist.addAll(list);
               return !list.isEmpty();
         }
      }
   }

   public static Baritone getBaritone(GetToBlockProcess getToBlockProcess) {
      return getToBlockProcess.baritone;
   }

   @Override
   public void getToBlock(BlockOptionalMeta blockOptionalMeta) {
      this.onLostControl();
      this.gettingTo = blockOptionalMeta;
      this.start = this.ctx.playerFeet();
      this.blacklist = new ArrayList();
      this.arrivalTickCount = 0;
      this.rescan(new ArrayList(), new GetToBlockCalculationContext(this, false));
   }

   public GetToBlockProcess(Baritone baritone2) {
      super(baritone2);
   }

   private synchronized void rescan(List list, CalculationContext calculationContext) {
      List list2 = MineProcess.searchWorld(calculationContext, new BlockOptionalMetaLookup(new BlockOptionalMeta[]{this.gettingTo}), 64, list, this.blacklist, Collections.emptyList());
      list2.removeIf(this.blacklist::contains);
      this.knownLocations = list2;
   }

   private boolean areAdjacent(BlockPos pos, BlockPos pos2) {
      return Math.abs(pos.getX() - pos2.getX()) + Math.abs(pos.getY() - pos2.getY()) + Math.abs(pos.getZ() - pos2.getZ()) == 1;
   }

   @Override
   public String displayName0() {
      return this.knownLocations.isEmpty()
         ? "Exploring randomly to find " + this.gettingTo + ", no known locations"
         : "Get To " + this.gettingTo + ", " + this.knownLocations.size() + " known locations";
   }

   private boolean blockOnTopMustBeRemoved(Block block) {
      return !this.rightClickOnArrival(block) ? false : block == Blocks.ender_chest || block == Blocks.chest || block == Blocks.trapped_chest;
   }

   private boolean rightClick() {
      for (BlockPos pos : (Iterable<BlockPos>)(this.knownLocations)) {
         Optional optional = RotationUtils.reachable3(this.ctx, pos, this.ctx.playerController().getBlockReachDistance());
         if (optional.isPresent()) {
            this.baritone.getLookBehavior2().updateTarget((Rotation)optional.get(), true);
            if (this.knownLocations.contains(this.ctx.getSelectedBlock().orElse(null))) {
               this.baritone.getInputOverrideHandler2().setInputForceState(Input.CLICK_RIGHT, true);
               System.out.println(this.ctx.player().openContainer);
               if (!(this.ctx.player().openContainer instanceof ContainerPlayer)) {
                  return true;
               }
            }

            if (this.arrivalTickCount++ > 20) {
               this.logDirect2("Right click timed out");
               return true;
            }

            return false;
         }
      }

      this.logDirect2("Arrived but failed to right click open");
      return true;
   }



   public static class DefaultGoalRunAway extends GoalRunAway {
      public final GetToBlockProcess getToBlockProcess;

      public DefaultGoalRunAway(GetToBlockProcess getToBlockProcess2, double d, BlockPos[] posArr) {
         super(d, posArr);
         this.getToBlockProcess = getToBlockProcess2;
      }

      @Override
      public boolean isInGoal(int n, int n2, int n3) {
         return false;
      }

      @Override
      public double heuristic() {
         return Double.NEGATIVE_INFINITY;
      }
   }
}
