package baritone.process;

import baritone.Baritone;
import baritone.api.BaritoneAPI;
import baritone.api.pathing.goals.Goal;
import baritone.api.pathing.goals.GoalBlock;
import baritone.api.pathing.goals.GoalComposite;
import baritone.api.pathing.goals.GoalRunAway;
import baritone.api.pathing.goals.GoalTwoBlocks;
import baritone.api.process.IMineProcess;
import baritone.api.process.PathingCommand;
import baritone.api.process.PathingCommandType;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.BlockOptionalMeta;
import baritone.api.utils.BlockOptionalMetaLookup;
import baritone.api.utils.Rotation;
import baritone.api.utils.RotationUtils;
import baritone.api.utils.input.Input;
import baritone.cache.CachedChunk;
import baritone.api.utils.BlockUtils;
import baritone.pathing.movement.CalculationContext;
import baritone.pathing.movement.MovementHelper;
import baritone.utils.BaritoneProcessHelper;
import baritone.utils.BlockStateInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3i;

public final class MineProcess extends BaritoneProcessHelper implements IMineProcess {
   private static final int ORE_LOCATIONS_COUNT = 64;
   private BlockOptionalMetaLookup filter;
   private List<BlockPos> knownOreLocations;
   private List blacklist;
   private Map anticipatedDrops;
   private BlockPos branchPoint;
   private GoalRunAway branchPointRunaway;
   private int desiredQuantity;
   private int tickCount;

   private BlockOptionalMetaLookup selectedFilter() {
      if (this.filter == null) {
         return null;
      } else if (!(Boolean)Baritone.settings().allowBreak.value) {
         BlockOptionalMetaLookup blockOptionalMetaLookup = new BlockOptionalMetaLookup(
            (BlockOptionalMeta[])this.filter.blocks().stream().filter(obj -> ((List)Baritone.settings().allowBreakAnyway.value).contains(((BlockOptionalMeta)obj).getBlock())).toArray(BlockOptionalMeta[]::new)
         );
         if (blockOptionalMetaLookup.blocks().isEmpty()) {
            this.logDirect2("Unable to mine when allowBreak is false and target block is not in allowBreakAnyway!");
            return null;
         } else {
            return blockOptionalMetaLookup;
         }
      } else {
         return this.filter;
      }
   }

   @Override
   public boolean isActive() {
      return this.filter != null;
   }

   @Override
   public PathingCommand onTick(boolean flag, boolean flag2) {
      if (this.desiredQuantity > 0) {
         int n = Arrays.stream(this.ctx.player().inventory.mainInventory).filter(stack -> this.filter.has(stack)).mapToInt(stack2 -> stack2.stackSize).sum();
         System.out.println("Currently have " + n + " valid items");
         if (n >= this.desiredQuantity) {
            this.logDirect2("Have " + n + " valid items");
            this.cancel();
            return null;
         }
      }

      if (flag) {
         if (this.knownOreLocations.isEmpty() || !(Boolean)Baritone.settings().blacklistClosestOnFailure.value) {
            this.logDirect2("Unable to find any path to " + this.filter + ", canceling mine");
            if ((Boolean)Baritone.settings().notificationOnMineFail.value) {
               this.logNotification("Unable to find any path to " + this.filter + ", canceling mine", true);
            }

            this.cancel();
            return null;
         }

         this.logDirect2("Unable to find any path to " + this.filter + ", blacklisting presumably unreachable closest instance...");
         if ((Boolean)Baritone.settings().notificationOnMineFail.value) {
            this.logNotification("Unable to find any path to " + this.filter + ", blacklisting presumably unreachable closest instance...", true);
         }

         this.knownOreLocations.stream().min(Comparator.comparingDouble(this.ctx.player()::getDistanceSq)).ifPresent(this.blacklist::add);
         this.knownOreLocations.removeIf(this.blacklist::contains);
      }

      this.updateLoucaSystem();
      int value = (Integer)Baritone.settings().mineGoalUpdateInterval.value;
      ArrayList list = new ArrayList(this.knownOreLocations);
      if (value != 0 && this.tickCount++ % value == 0) {
         CalculationContext calculationContext = new CalculationContext(this.baritone, true);
         Baritone.getThreadPool().execute(() -> this.rescan(list, calculationContext));
      }

      if ((Boolean)Baritone.settings().legitMine.value && !this.addNearby()) {
         this.cancel();
         return null;
      } else {
         Optional optional = ((List<BlockPos>)list).stream()
            .filter(pos -> pos.getX() == this.ctx.playerFeet().getX() && pos.getZ() == this.ctx.playerFeet().getZ())
            .filter(pos2 -> pos2.getY() >= this.ctx.playerFeet().getY())
            .filter(pos3 -> !(BlockStateInterface.get(this.ctx, pos3).getBlock() instanceof BlockAir))
            .min(Comparator.comparingDouble(this.ctx.player()::getDistanceSq));
         this.baritone.getInputOverrideHandler2().clearAllKeys();
         if (optional.isPresent() && this.ctx.player().onGround) {
            BlockPos pos4 = (BlockPos)optional.get();
            IBlockState state = this.baritone.bsi.get0(pos4);
            if (!MovementHelper.avoidBreaking(this.baritone.bsi, pos4.getX(), pos4.getY(), pos4.getZ(), state)) {
               Optional optional2 = RotationUtils.reachable(this.ctx, pos4);
               if (optional2.isPresent() && flag2) {
                  this.baritone.getLookBehavior2().updateTarget((Rotation)optional2.get(), true);
                  MovementHelper.switchToBestToolFor(this.ctx, this.ctx.world().getBlockState(pos4));
                  if (this.ctx.isLookingAt(pos4) || this.ctx.playerRotations().isReallyCloseTo((Rotation)optional2.get())) {
                     this.baritone.getInputOverrideHandler2().setInputForceState(Input.CLICK_LEFT, true);
                  }

                  return new PathingCommand(null, PathingCommandType.REQUEST_PAUSE);
               }
            }
         }

         PathingCommand pathingCommand = this.updateGoal();
         if (pathingCommand == null) {
            this.cancel();
            return null;
         } else {
            return pathingCommand;
         }
      }
   }

   private Goal coalesce(BlockPos pos, List list, CalculationContext calculationContext) {
      boolean flag = !(this.baritone.bsi.get0(pos.up()).getBlock() instanceof BlockFalling);
      if (!(Boolean)Baritone.settings().forceInternalMining.value) {
         return (Goal)(flag ? new GoalThreeBlocks(pos) : new GoalTwoBlocks(pos));
      } else {
         boolean flag2 = this.internalMiningGoal(pos.up(), calculationContext, list);
         boolean flag3 = this.internalMiningGoal(pos.down(), calculationContext, list);
         boolean flag4 = this.internalMiningGoal(pos.down(2), calculationContext, list);
         if (flag2 == flag3) {
            return (Goal)(flag4 && flag ? new GoalThreeBlocks(pos) : new GoalTwoBlocks(pos));
         } else if (flag2) {
            return new GoalBlock(pos);
         } else {
            return (Goal)(flag4 && flag ? new GoalTwoBlocks(pos.down()) : new GoalBlock(pos.down()));
         }
      }
   }

   private boolean addNearby() {
      List list = this.droppedItemsScan();
      this.knownOreLocations.addAll(list);
      BetterBlockPos betterBlockPos = this.ctx.playerFeet();
      BlockStateInterface blockStateInterface = new BlockStateInterface(this.ctx);
      BlockOptionalMetaLookup blockOptionalMetaLookup = this.selectedFilter();
      if (blockOptionalMetaLookup == null) {
         return false;
      } else {
         byte b = 10;
         double d = 20.0;

         for (int j = betterBlockPos.getX() - b; j <= betterBlockPos.getX() + b; j++) {
            for (int k = betterBlockPos.getY() - b; k <= betterBlockPos.getY() + b; k++) {
               for (int m = betterBlockPos.getZ() - b; m <= betterBlockPos.getZ() + b; m++) {
                  if (blockOptionalMetaLookup.has3(blockStateInterface.get0(j, k, m))) {
                     BlockPos pos = new BlockPos(j, k, m);
                     if ((Boolean)Baritone.settings().legitMineIncludeDiagonals.value && this.knownOreLocations.stream().anyMatch(pos2 -> pos2.distanceSq(pos) <= 2.0)
                        || RotationUtils.reachable3(this.ctx, pos, d).isPresent()) {
                        this.knownOreLocations.add(pos);
                     }
                  }
               }
            }
         }

         this.knownOreLocations = prune(new CalculationContext(this.baritone), this.knownOreLocations, blockOptionalMetaLookup, 64, this.blacklist, list);
         return true;
      }
   }

   private boolean internalMiningGoal(BlockPos pos, CalculationContext calculationContext, List list) {
      if (list.contains(pos)) {
         return true;
      } else {
         IBlockState state = calculationContext.bsi.get0(pos);
         return (Boolean)Baritone.settings().internalMiningAirException.value && state.getBlock() instanceof BlockAir
            ? true
            : this.filter.has3(state) && plausibleToBreak(calculationContext, pos);
      }
   }

   private void updateLoucaSystem() {
      HashMap map = new HashMap(this.anticipatedDrops);
      this.ctx.getSelectedBlock().ifPresent(obj -> {
         if (this.knownOreLocations.contains(obj)) {
            map.put(obj, System.currentTimeMillis() + (Long)Baritone.settings().mineDropLoiterDurationMSThanksLouca.value);
         }
      });

      for (BlockPos pos : (Iterable<BlockPos>)(this.anticipatedDrops.keySet())) {
         if ((Long)map.get(pos) < System.currentTimeMillis()) {
            map.remove(pos);
         }
      }

      this.anticipatedDrops = map;
   }

   public static boolean isExposed(CalculationContext calculationContext, BlockPos pos) {
      int value = (Integer)Baritone.settings().allowOnlyExposedOresDistance.value;

      for (int j = -value; j <= value; j++) {
         for (int k = -value; k <= value; k++) {
            for (int m = -value; m <= value; m++) {
               if (Math.abs(j) + Math.abs(k) + Math.abs(m) <= value
                  && MovementHelper.isTransparent(calculationContext.getBlock(pos.getX() + j, pos.getY() + k, pos.getZ() + m))) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static boolean plausibleToBreak(CalculationContext calculationContext, BlockPos pos) {
      return MovementHelper.getMiningDurationTicks(calculationContext, pos.getX(), pos.getY(), pos.getZ(), calculationContext.bsi.get0(pos), true) >= 1000000.0
         ? false
         : calculationContext.bsi.get0(pos.up()).getBlock() != Blocks.bedrock || calculationContext.bsi.get0(pos.down()).getBlock() != Blocks.bedrock;
   }

   public MineProcess(Baritone baritone2) {
      super(baritone2);
   }

   private void rescan(List list, CalculationContext calculationContext) {
      BlockOptionalMetaLookup blockOptionalMetaLookup = this.selectedFilter();
      if (blockOptionalMetaLookup != null) {
         if (!(Boolean)Baritone.settings().legitMine.value) {
            List list2 = this.droppedItemsScan();
            List list3 = searchWorld(calculationContext, blockOptionalMetaLookup, 64, list, this.blacklist, list2);
            list3.addAll(list2);
            if (list3.isEmpty() && !(Boolean)Baritone.settings().exploreForBlocks.value) {
               this.logDirect2("No locations for " + blockOptionalMetaLookup + " known, cancelling");
               if ((Boolean)Baritone.settings().notificationOnMineFail.value) {
                  this.logNotification("No locations for " + blockOptionalMetaLookup + " known, cancelling", true);
               }

               this.cancel();
            } else {
               this.knownOreLocations = list3;
            }
         }
      }
   }

   @Override
   public void mineByName(int n, String[] strings) {
      this.mine(n, new BlockOptionalMetaLookup(strings));
   }

   @Override
   public void onLostControl() {
      this.mine(0, (BlockOptionalMetaLookup)null);
   }

   private static List prune(CalculationContext calculationContext, List list, BlockOptionalMetaLookup blockOptionalMetaLookup, int n, List list2, List list3) {
      list3.removeIf(obj -> {
         for (BlockPos pos : (Iterable<BlockPos>)(list)) {
            if (pos.distanceSq((Vec3i)obj) <= 9.0 && blockOptionalMetaLookup.has3(calculationContext.get(pos.getX(), pos.getY(), pos.getZ())) && plausibleToBreak(calculationContext, pos)) {
               return true;
            }
         }

         return false;
      });
      List list4 = (List)list.stream()
         .distinct()
         .filter(
            obj2 -> !calculationContext.bsi.worldContainsLoadedChunk(((BlockPos)obj2).getX(), ((BlockPos)obj2).getZ()) || blockOptionalMetaLookup.has3(calculationContext.get(((BlockPos)obj2).getX(), ((BlockPos)obj2).getY(), ((BlockPos)obj2).getZ())) || list3.contains(obj2)
         )
         .filter(obj3 -> plausibleToBreak(calculationContext, (BlockPos)obj3))
         .filter(obj4 -> (Boolean)Baritone.settings().allowOnlyExposedOres.value ? isExposed(calculationContext, (BlockPos)obj4) : true)
         .filter(obj5 -> ((BlockPos)obj5).getY() >= (Integer)Baritone.settings().minYLevelWhileMining.value)
         .filter(obj6 -> ((BlockPos)obj6).getY() <= (Integer)Baritone.settings().maxYLevelWhileMining.value)
         .filter(obj7 -> !list2.contains(obj7))
         .sorted(Comparator.comparingDouble(obj8 -> calculationContext.getBaritone().getPlayerContext().player().getDistanceSq((BlockPos)obj8)))
         .collect(Collectors.toList());
      return list4.size() > n ? list4.subList(0, n) : list4;
   }

   public static List searchWorld(CalculationContext calculationContext, BlockOptionalMetaLookup blockOptionalMetaLookup, int n, List list, List list2, List list3) {
      ArrayList list4 = new ArrayList();
      ArrayList list5 = new ArrayList();

      for (BlockOptionalMeta blockOptionalMeta : (Iterable<BlockOptionalMeta>)(blockOptionalMetaLookup.blocks())) {
         Block block = blockOptionalMeta.getBlock();
         if (CachedChunk.BLOCKS_TO_KEEP_TRACK_OF.contains(block)) {
            BetterBlockPos betterBlockPos = calculationContext.baritone.getPlayerContext().playerFeet();
            list4.addAll(calculationContext.worldData.getCachedWorld().getLocationsOf(BlockUtils.blockToString(block), (Integer)Baritone.settings().maxCachedWorldScanCount.value, betterBlockPos.x2, betterBlockPos.z2, 2));
         } else {
            list5.add(block);
         }
      }

      List list6 = prune(calculationContext, list4, blockOptionalMetaLookup, n, list2, list3);
      if (!list5.isEmpty() || (Boolean)Baritone.settings().extendCacheOnThreshold.value && list6.size() < n) {
         list6.addAll(BaritoneAPI.getProvider().getInstance3().scanChunkRadius(calculationContext.getBaritone().getPlayerContext(), blockOptionalMetaLookup, n, 10, 32));
      }

      list6.addAll(list);
      return prune(calculationContext, list6, blockOptionalMetaLookup, n, list2, list3);
   }

   private PathingCommand updateGoal() {
      BlockOptionalMetaLookup blockOptionalMetaLookup = this.selectedFilter();
      if (blockOptionalMetaLookup == null) {
         return null;
      } else {
         boolean value = (Boolean)Baritone.settings().legitMine.value;
         List knownOreLocations2 = this.knownOreLocations;
         if (!knownOreLocations2.isEmpty()) {
            CalculationContext calculationContext = new CalculationContext(this.baritone);
            List list = prune(calculationContext, new ArrayList(knownOreLocations2), blockOptionalMetaLookup, 64, this.blacklist, this.droppedItemsScan());
            GoalComposite goalComposite = new GoalComposite((Goal[])list.stream().map(obj -> this.coalesce((BlockPos)obj, list, calculationContext)).toArray(Goal[]::new));
            this.knownOreLocations = list;
            return new PathingCommand(goalComposite, value ? PathingCommandType.FORCE_REVALIDATE_GOAL_AND_PATH : PathingCommandType.REVALIDATE_GOAL_AND_PATH);
         } else if (!value && !(Boolean)Baritone.settings().exploreForBlocks.value) {
            return null;
         } else {
            int value2 = (Integer)Baritone.settings().legitMineYLevel.value;
            if (this.branchPoint == null) {
               this.branchPoint = this.ctx.playerFeet();
            }

            if (this.branchPointRunaway == null) {
               this.branchPointRunaway = new MineProcess.DefaultGoalRunAway(this, 1.0, value2, new BlockPos[]{this.branchPoint});
            }

            return new PathingCommand(this.branchPointRunaway, PathingCommandType.REVALIDATE_GOAL_AND_PATH);
         }
      }
   }

   @Override
   public String displayName0() {
      return "Mine " + this.filter;
   }

   @Override
   public void mine(int n, BlockOptionalMetaLookup blockOptionalMetaLookup) {
      this.filter = blockOptionalMetaLookup;
      if (this.selectedFilter() == null) {
         this.filter = null;
      }

      this.desiredQuantity = n;
      this.knownOreLocations = new ArrayList();
      this.blacklist = new ArrayList();
      this.branchPoint = null;
      this.branchPointRunaway = null;
      this.anticipatedDrops = new HashMap();
      if (blockOptionalMetaLookup != null) {
         this.rescan(new ArrayList(), new CalculationContext(this.baritone));
      }
   }

   public List droppedItemsScan() {
      if (!(Boolean)Baritone.settings().mineScanDroppedItems.value) {
         return Collections.emptyList();
      } else {
         ArrayList list = new ArrayList();

         for (Entity entity : (Iterable<Entity>)(this.ctx.world().loadedEntityList)) {
            if (entity instanceof EntityItem) {
               EntityItem entityItem = (EntityItem)entity;
               if (this.filter.has(entityItem.getEntityItem())) {
                  list.add(new BlockPos(entity));
               }
            }
         }

         list.addAll(this.anticipatedDrops.keySet());
         return list;
      }
   }



   public static class DefaultGoalRunAway extends GoalRunAway {
      public final MineProcess mineProcess;

      @Override
      public double heuristic() {
         return Double.NEGATIVE_INFINITY;
      }

      @Override
      public boolean isInGoal(int n, int n2, int n3) {
         return false;
      }

      public DefaultGoalRunAway(MineProcess mineProcess2, double d, Integer num, BlockPos[] posArr) {
         super(d, num, posArr);
         this.mineProcess = mineProcess2;
      }
   }
}
