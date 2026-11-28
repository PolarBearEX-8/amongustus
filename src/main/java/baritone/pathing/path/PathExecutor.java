package baritone.pathing.path;

import baritone.api.pathing.path.IPathExecutor;
import baritone.Baritone;
import baritone.api.pathing.calc.IPath;
import baritone.api.pathing.movement.IMovement;
import baritone.api.pathing.movement.MovementStatus;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.Helper;
import baritone.api.utils.IPlayerContext;
import baritone.api.utils.RotationUtils;
import baritone.api.utils.VecUtils;
import baritone.api.utils.input.Input;
import baritone.behavior.PathingBehavior;
import baritone.pathing.calc.AbstractNodeCostSearch;
import baritone.pathing.movement.CalculationContext;
import baritone.pathing.movement.Movement;
import baritone.pathing.movement.MovementHelper;
import baritone.pathing.movement.movements.MovementAscend;
import baritone.pathing.movement.movements.MovementDescend;
import baritone.pathing.movement.movements.MovementDiagonal;
import baritone.pathing.movement.movements.MovementFall;
import baritone.pathing.movement.movements.MovementTraverse;
import baritone.utils.BlockStateInterface;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import net.minecraft.block.BlockLiquid;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Tuple;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;

public class PathExecutor implements IPathExecutor, Helper {
   private static final double MAX_MAX_DIST_FROM_PATH = 3.0;
   private static final double MAX_DIST_FROM_PATH = 2.0;
   private static final double MAX_TICKS_AWAY = 200.0;
   private final IPath path;
   private int pathPosition;
   private int ticksAway;
   private int ticksOnCurrent;
   private Double currentMovementOriginalCostEstimate;
   private Integer costEstimateIndex;
   private boolean failed;
   private boolean recalcBP = true;
   private HashSet toBreak = new HashSet();
   private HashSet toPlace = new HashSet();
   private HashSet toWalkInto = new HashSet();
   private final PathingBehavior behavior;
   private final IPlayerContext ctx;
   private boolean sprintNextTick;

   public PathExecutor(PathingBehavior pathingBehavior, IPath path2) {
      this.behavior = pathingBehavior;
      this.ctx = pathingBehavior.ctx;
      this.path = path2;
      this.pathPosition = 0;
   }

   private Tuple closestPathPos(IPath path2) {
      double d = -1.0;
      BlockPos pos = null;

      for (IMovement movement : (Iterable<IMovement>)(path2.movements())) {
         for (BlockPos pos2 : (Iterable<BlockPos>)(((Movement)movement).getValidPositions())) {
            double d2 = VecUtils.entityDistanceToCenter(this.ctx.player(), pos2);
            if (d2 < d || d == -1.0) {
               d = d2;
               pos = pos2;
            }
         }
      }

      return new Tuple(d, pos);
   }

   public Set toWalkInto() {
      return Collections.unmodifiableSet(this.toWalkInto);
   }

   private boolean shouldPause() {
      Optional inProgress = this.behavior.getInProgress();
      if (!inProgress.isPresent()) {
         return false;
      } else if (!this.ctx.player().onGround) {
         return false;
      } else if (!MovementHelper.canWalkOn2(this.ctx, this.ctx.playerFeet().down2())) {
         return false;
      } else if (!MovementHelper.canWalkThrough(this.ctx, this.ctx.playerFeet()) || !MovementHelper.canWalkThrough(this.ctx, this.ctx.playerFeet().up2())) {
         return false;
      } else if (!((IMovement)this.path.movements().get(this.pathPosition)).safeToCancel()) {
         return false;
      } else {
         Optional optional = ((AbstractNodeCostSearch)inProgress.get()).bestPathSoFar();
         if (!optional.isPresent()) {
            return false;
         } else {
            List list = ((IPath)optional.get()).positions();
            if (list.size() < 3) {
               return false;
            } else {
               list = list.subList(1, list.size());
               return list.contains(this.ctx.playerFeet());
            }
         }
      }
   }

   private boolean isFarFromPath(Tuple tuple, double d) {
      double first = (Double)tuple.getFirst();
      if (first > d) {
         if (this.path.movements().get(this.pathPosition) instanceof MovementFall) {
            BlockPos pos = (BlockPos)this.path.positions().get(this.pathPosition + 1);
            return VecUtils.entityFlatDistanceToCenter(this.ctx.player(), pos) >= d;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   private static boolean canSprintAscend(IPlayerContext playerContext, MovementTraverse movementTraverse, MovementAscend movementAscend, IMovement movement) {
      if (!(Boolean)Baritone.settings().sprintAscends.value) {
         return false;
      } else if (!movementTraverse.getDirection().equals(movementAscend.getDirection().down())) {
         return false;
      } else if (movement.getDirection().getX() == movementAscend.getDirection().getX() && movement.getDirection().getZ() == movementAscend.getDirection().getZ()) {
         if (!MovementHelper.canWalkOn2(playerContext, movementTraverse.getDest().down2())) {
            return false;
         } else if (!MovementHelper.canWalkOn2(playerContext, movementAscend.getDest().down2())) {
            return false;
         } else if (!movementAscend.toBreakCached.isEmpty()) {
            return false;
         } else {
            for (int j = 0; j < 2; j++) {
               for (int k = 0; k < 3; k++) {
                  BlockPos pos = movementTraverse.getSrc().up2(k);
                  if (j == 1) {
                     pos = pos.add(movementTraverse.getDirection());
                  }

                  if (!MovementHelper.fullyPassable(playerContext, (BlockPos)pos)) {
                     return false;
                  }
               }
            }

            return MovementHelper.avoidWalkingInto(playerContext.world().getBlockState(movementTraverse.getSrc().up2(3)).getBlock())
               ? false
               : !MovementHelper.avoidWalkingInto(playerContext.world().getBlockState(movementAscend.getDest().up2(2)).getBlock());
         }
      } else {
         return false;
      }
   }

   private Tuple overrideFall(MovementFall movementFall) {
      BlockPos direction = movementFall.getDirection();
      if (direction.getY() < -3) {
         return null;
      } else if (!movementFall.toBreakCached.isEmpty()) {
         return null;
      } else {
         Vec3i vec = new Vec3i(direction.getX(), 0, direction.getZ());

         int n;
         outerLoop:
         for (n = this.pathPosition + 1; n < this.path.length() - 1 && n < this.pathPosition + 3; n++) {
            IMovement movement = (IMovement)this.path.movements().get(n);
            if (!(movement instanceof MovementTraverse) || !vec.equals(movement.getDirection())) {
               break;
            }

            for (int j = movement.getDest().y2; j <= movementFall.getSrc().y2 + 1; j++) {
               BlockPos pos = new BlockPos(movement.getDest().x2, j, movement.getDest().z2);
               if (!MovementHelper.fullyPassable(this.ctx, pos)) {
                  break outerLoop;
               }
            }

            if (!MovementHelper.canWalkOn2(this.ctx, movement.getDest().down2())) {
               break;
            }
         }

         if (--n == this.pathPosition) {
            return null;
         } else {
            double d = (double)(n - this.pathPosition) - 0.4;
            return new Tuple(
               new Vec3(
                  (double)vec.getX() * d + (double)movementFall.getDest().x2 + 0.5,
                  (double)movementFall.getDest().y2,
                  (double)vec.getZ() * d + (double)movementFall.getDest().z2 + 0.5
               ),
               movementFall.getDest().add(vec.getX() * (n - this.pathPosition), 0, vec.getZ() * (n - this.pathPosition))
            );
         }
      }
   }

   private void cancel() {
      this.clearKeys();
      this.behavior.baritone.getInputOverrideHandler2().getBlockBreakHelper().stopBreakingBlock();
      this.pathPosition = this.path.length() + 3;
      this.failed = true;
   }

   public boolean isFailed() {
      return this.failed;
   }

   private static boolean canSprintFromDescendInto(IPlayerContext playerContext, IMovement movement, IMovement movement2) {
      if (movement2 instanceof MovementDescend && movement2.getDirection().equals(movement.getDirection())) {
         return true;
      } else if (!MovementHelper.canWalkOn(playerContext, movement.getDest().add(movement.getDirection()))) {
         return false;
      } else {
         return movement2 instanceof MovementTraverse && movement2.getDirection().down().equals(movement.getDirection())
            ? true
            : movement2 instanceof MovementDiagonal && (Boolean)Baritone.settings().allowOvershootDiagonalDescend.value;
      }
   }

   @Override
   public IPath getPath() {
      return this.path;
   }

   public PathExecutor trySplice(PathExecutor pathExecutor) {
      return pathExecutor == null ? this.cutIfTooLong() : (PathExecutor)SplicedPath.trySplice(this.path, pathExecutor.path, false).map(obj -> {
         if (!((IPath)obj).getDest().equals(pathExecutor.getPath().getDest())) {
            throw new IllegalStateException();
         } else {
            PathExecutor pathExecutor2 = new PathExecutor(this.behavior, (IPath)obj);
            pathExecutor2.pathPosition = this.pathPosition;
            pathExecutor2.currentMovementOriginalCostEstimate = this.currentMovementOriginalCostEstimate;
            pathExecutor2.costEstimateIndex = this.costEstimateIndex;
            pathExecutor2.ticksOnCurrent = this.ticksOnCurrent;
            return pathExecutor2;
         }
      }).orElseGet(this::cutIfTooLong);
   }

   public Set toBreak() {
      return Collections.unmodifiableSet(this.toBreak);
   }

   public boolean finished() {
      return this.pathPosition >= this.path.length();
   }

   private boolean shouldSprintNextTick() {
      boolean isInputForcedDown = this.behavior.baritone.getInputOverrideHandler2().isInputForcedDown(Input.SPRINT);
      this.behavior.baritone.getInputOverrideHandler2().setInputForceState(Input.SPRINT, false);
      if (!(new CalculationContext(this.behavior.baritone, false)).canSprint) {
         return false;
      } else {
         IMovement movement = (IMovement)this.path.movements().get(this.pathPosition);
         if (movement instanceof MovementTraverse && this.pathPosition < this.path.length() - 3) {
            IMovement movement2 = (IMovement)this.path.movements().get(this.pathPosition + 1);
            if (movement2 instanceof MovementAscend
               && canSprintAscend(this.ctx, (MovementTraverse)movement, (MovementAscend)movement2, (IMovement)this.path.movements().get(this.pathPosition + 2))) {
               if (inPositionToSprintAscend(this.ctx, movement)) {
                  this.logDebug("Skipping traverse to straight ascend");
                  this.pathPosition++;
                  this.onChangeInPathPosition();
                  this.onTick();
                  this.behavior.baritone.getInputOverrideHandler2().setInputForceState(Input.JUMP, true);
                  return true;
               }

               this.logDebug("Too far to the side to safely sprint ascend");
            }
         }

         if (isInputForcedDown) {
            return true;
         } else {
            if (movement instanceof MovementDescend) {
               if (((MovementDescend)movement).safeMode() && !((MovementDescend)movement).skipToAscend()) {
                  this.logDebug("Sprinting would be unsafe");
                  return false;
               }

               if (this.pathPosition < this.path.length() - 2) {
                  IMovement movement3 = (IMovement)this.path.movements().get(this.pathPosition + 1);
                  if (movement3 instanceof MovementAscend && movement.getDirection().up().equals(movement3.getDirection().down())) {
                     this.pathPosition++;
                     this.onChangeInPathPosition();
                     this.onTick();
                     this.logDebug("Skipping descend to straight ascend");
                     return true;
                  }

                  if (canSprintFromDescendInto(this.ctx, movement, movement3)) {
                     if (movement3 instanceof MovementDescend && this.pathPosition < this.path.length() - 3) {
                        IMovement movement4 = (IMovement)this.path.movements().get(this.pathPosition + 2);
                        if (movement4 instanceof MovementDescend && !canSprintFromDescendInto(this.ctx, movement3, movement4)) {
                           return false;
                        }
                     }

                     if (this.ctx.playerFeet().equals(movement.getDest())) {
                        this.pathPosition++;
                        this.onChangeInPathPosition();
                        this.onTick();
                     }

                     return true;
                  }
               }
            }

            if (movement instanceof MovementAscend && this.pathPosition != 0) {
               IMovement movement5 = (IMovement)this.path.movements().get(this.pathPosition - 1);
               if (movement5 instanceof MovementDescend && movement5.getDirection().up().equals(movement.getDirection().down())) {
                  BetterBlockPos betterBlockPos = movement.getSrc().up2();
                  if (this.ctx.player().posY >= (double)betterBlockPos.getY() - 0.07) {
                     this.behavior.baritone.getInputOverrideHandler2().setInputForceState(Input.JUMP, false);
                     return true;
                  }
               }

               if (this.pathPosition < this.path.length() - 2
                  && movement5 instanceof MovementTraverse
                  && canSprintAscend(this.ctx, (MovementTraverse)movement5, (MovementAscend)movement, (IMovement)this.path.movements().get(this.pathPosition + 1))) {
                  return true;
               }
            }

            if (movement instanceof MovementFall) {
               Tuple tuple = this.overrideFall((MovementFall)movement);
               if (tuple != null) {
                  BetterBlockPos betterBlockPos2 = new BetterBlockPos((BlockPos)tuple.getSecond());
                  if (!this.path.positions().contains(betterBlockPos2)) {
                     throw new IllegalStateException();
                  }

                  if (this.ctx.playerFeet().equals(betterBlockPos2)) {
                     this.pathPosition = this.path.positions().indexOf(betterBlockPos2);
                     this.onChangeInPathPosition();
                     this.onTick();
                     return true;
                  }

                  this.clearKeys();
                  this.behavior.baritone.getLookBehavior2().updateTarget(RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), (Vec3)tuple.getFirst(), this.ctx.playerRotations()), false);
                  this.behavior.baritone.getInputOverrideHandler2().setInputForceState(Input.MOVE_FORWARD, true);
                  return true;
               }
            }

            return false;
         }
      }
   }

   public Set toPlace() {
      return Collections.unmodifiableSet(this.toPlace);
   }

   public boolean snipsnapifpossible() {
      if (!this.ctx.player().onGround && !(this.ctx.world().getBlockState(this.ctx.playerFeet()).getBlock() instanceof BlockLiquid)) {
         return false;
      } else if (this.ctx.player().motionY < -0.1) {
         return false;
      } else {
         int n = this.path.positions().indexOf(this.ctx.playerFeet());
         if (n == -1) {
            return false;
         } else {
            this.pathPosition = n;
            this.clearKeys();
            return true;
         }
      }
   }

   private static boolean inPositionToSprintAscend(IPlayerContext playerContext, IMovement movement) {
      double d = Math.abs((double)movement.getDirection().getX() * ((double)movement.getSrc().z2 + 0.5 - playerContext.player().posZ))
         + Math.abs((double)movement.getDirection().getZ() * ((double)movement.getSrc().x2 + 0.5 - playerContext.player().posX));
      if (d > 0.1) {
         return false;
      } else {
         BlockPos pos = movement.getSrc().subtract(movement.getDirection()).up(2);
         if (MovementHelper.fullyPassable(playerContext, pos)) {
            return true;
         } else {
            double d2 = Math.abs((double)movement.getDirection().getX() * ((double)pos.getX() + 0.5 - playerContext.player().posX))
               + Math.abs((double)movement.getDirection().getZ() * ((double)pos.getZ() + 0.5 - playerContext.player().posZ));
            return d2 > 0.8;
         }
      }
   }

   private PathExecutor cutIfTooLong() {
      if (this.pathPosition > (Integer)Baritone.settings().maxPathHistoryLength.value) {
         int value = (Integer)Baritone.settings().pathHistoryCutoffAmount.value;
         CutoffPath cutoffPath = new CutoffPath(this.path, value, this.path.length() - 1);
         if (!cutoffPath.getDest().equals(this.path.getDest())) {
            throw new IllegalStateException();
         } else {
            this.logDebug("Discarding earliest segment movements, length cut from " + this.path.length() + " to " + cutoffPath.length());
            PathExecutor pathExecutor = new PathExecutor(this.behavior, cutoffPath);
            pathExecutor.pathPosition = this.pathPosition - value;
            pathExecutor.currentMovementOriginalCostEstimate = this.currentMovementOriginalCostEstimate;
            if (this.costEstimateIndex != null) {
               pathExecutor.costEstimateIndex = this.costEstimateIndex - value;
            }

            pathExecutor.ticksOnCurrent = this.ticksOnCurrent;
            return pathExecutor;
         }
      } else {
         return this;
      }
   }

   private void clearKeys() {
      this.behavior.baritone.getInputOverrideHandler2().clearAllKeys();
   }

   @Override
   public int getPosition() {
      return this.pathPosition;
   }

   public boolean onTick() {
      if (this.pathPosition == this.path.length() - 1) {
         this.pathPosition++;
      }

      if (this.pathPosition >= this.path.length()) {
         return true;
      } else {
         Movement movement = (Movement)this.path.movements().get(this.pathPosition);
         BetterBlockPos betterBlockPos = this.ctx.playerFeet();
         if (!movement.getValidPositions().contains(betterBlockPos)) {
            for (int j = 0; j < this.pathPosition && j < this.path.length(); j++) {
               if (((Movement)this.path.movements().get(j)).getValidPositions().contains(betterBlockPos)) {
                  int pathPosition2 = this.pathPosition;
                  this.pathPosition = j;

                  for (int k = this.pathPosition; k <= pathPosition2; k++) {
                     ((IMovement)this.path.movements().get(k)).reset();
                  }

                  this.onChangeInPathPosition();
                  this.onTick();
                  return false;
               }
            }

            for (int m = this.pathPosition + 3; m < this.path.length() - 1; m++) {
               if (((Movement)this.path.movements().get(m)).getValidPositions().contains(betterBlockPos)) {
                  if (m - this.pathPosition > 2) {
                     this.logDebug("Skipping forward " + (m - this.pathPosition) + " steps, to " + m);
                  }

                  this.pathPosition = m - 1;
                  this.onChangeInPathPosition();
                  this.onTick();
                  return false;
               }
            }
         }

         Tuple tuple = this.closestPathPos(this.path);
         if (this.isFarFromPath(tuple, 2.0)) {
            this.ticksAway++;
            System.out.println("FAR AWAY FROM PATH FOR " + this.ticksAway + " TICKS. Current distance: " + tuple.getFirst() + ". Threshold: " + 2.0);
            if ((double)this.ticksAway > 200.0) {
               this.logDebug("Too far away from path for too long, cancelling path");
               this.cancel();
               return false;
            }
         } else {
            this.ticksAway = 0;
         }

         if (this.isFarFromPath(tuple, 3.0)) {
            this.logDebug("too far from path");
            this.cancel();
            return false;
         } else {
            BlockStateInterface blockStateInterface = new BlockStateInterface(this.ctx);

            for (int idx = this.pathPosition - 10; idx < this.pathPosition + 10; idx++) {
               if (idx >= 0 && idx < this.path.movements().size()) {
                  Movement movement2 = (Movement)this.path.movements().get(idx);
                  List list = movement2.toBreak(blockStateInterface);
                  List list2 = movement2.toPlace(blockStateInterface);
                  List list3 = movement2.toWalkInto(blockStateInterface);
                  movement2.resetBlockCache();
                  if (!list.equals(movement2.toBreak(blockStateInterface))) {
                     this.recalcBP = true;
                  }

                  if (!list2.equals(movement2.toPlace(blockStateInterface))) {
                     this.recalcBP = true;
                  }

                  if (!list3.equals(movement2.toWalkInto(blockStateInterface))) {
                     this.recalcBP = true;
                  }
               }
            }

            if (this.recalcBP) {
               HashSet set = new HashSet();
               HashSet set2 = new HashSet();
               HashSet set3 = new HashSet();

               for (int index = this.pathPosition; index < this.path.movements().size(); index++) {
                  Movement movement3 = (Movement)this.path.movements().get(index);
                  set.addAll(movement3.toBreak(blockStateInterface));
                  set2.addAll(movement3.toPlace(blockStateInterface));
                  set3.addAll(movement3.toWalkInto(blockStateInterface));
               }

               this.toBreak = set;
               this.toPlace = set2;
               this.toWalkInto = set3;
               this.recalcBP = false;
            }

            if (this.pathPosition < this.path.movements().size() - 1) {
               IMovement movement4 = (IMovement)this.path.movements().get(this.pathPosition + 1);
               if (!this.behavior.baritone.bsi.worldContainsLoadedChunk(movement4.getDest().x2, movement4.getDest().z2)) {
                  this.logDebug("Pausing since destination is at edge of loaded chunks");
                  this.clearKeys();
                  return true;
               }
            }

            boolean flag = movement.safeToCancel();
            if (this.costEstimateIndex == null || this.costEstimateIndex != this.pathPosition) {
               this.costEstimateIndex = this.pathPosition;
               this.currentMovementOriginalCostEstimate = movement.getCost();

               for (int i2 = 1; i2 < (Integer)Baritone.settings().costVerificationLookahead.value && this.pathPosition + i2 < this.path.length() - 1; i2++) {
                  if (((Movement)this.path.movements().get(this.pathPosition + i2))
                           .calculateCost(
                              this.behavior.getContext()
                           )
                        >= 1000000.0
                     && flag) {
                     this.logDebug("Something has changed in the world and a future movement has become impossible. Cancelling.");
                     this.cancel();
                     return true;
                  }
               }
            }

            double d = movement.recalculateCost(this.behavior.getContext());
            if (d >= 1000000.0 && flag) {
               this.logDebug("Something has changed in the world and this movement has become impossible. Cancelling.");
               this.cancel();
               return true;
            } else if (!movement.calculatedWhileLoaded() && d - this.currentMovementOriginalCostEstimate > (Double)Baritone.settings().maxCostIncrease.value && flag) {
               this.logDebug("Original cost " + this.currentMovementOriginalCostEstimate + " current cost " + d + ". Cancelling.");
               this.cancel();
               return true;
            } else if (this.shouldPause()) {
               this.logDebug("Pausing since current best path is a backtrack");
               this.clearKeys();
               return true;
            } else {
               MovementStatus movementStatus = movement.update();
               if (movementStatus == MovementStatus.UNREACHABLE || movementStatus == MovementStatus.FAILED) {
                  this.logDebug("Movement returns status " + movementStatus);
                  this.cancel();
                  return true;
               } else if (movementStatus == MovementStatus.SUCCESS) {
                  this.pathPosition++;
                  this.onChangeInPathPosition();
                  this.onTick();
                  return true;
               } else {
                  this.sprintNextTick = this.shouldSprintNextTick();
                  if (!this.sprintNextTick) {
                     this.ctx.player().setSprinting(false);
                  }

                  this.ticksOnCurrent++;
                  if ((double)this.ticksOnCurrent > this.currentMovementOriginalCostEstimate + (double)((Integer)Baritone.settings().movementTimeoutTicks.value).intValue()) {
                     this.logDebug("This movement has taken too long (" + this.ticksOnCurrent + " ticks, expected " + this.currentMovementOriginalCostEstimate + "). Cancelling.");
                     this.cancel();
                     return true;
                  } else {
                     return flag;
                  }
               }
            }
         }
      }
   }

   private void onChangeInPathPosition() {
      this.clearKeys();
      this.ticksOnCurrent = 0;
   }

   public boolean isSprintNextTick() {
      return this.sprintNextTick;
   }

}
