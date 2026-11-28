package baritone.pathing.movement;

import baritone.Baritone;
import baritone.api.IBaritone;
import baritone.api.pathing.movement.IMovement;
import baritone.api.pathing.movement.MovementStatus;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.IPlayerContext;
import baritone.api.utils.Rotation;
import baritone.api.utils.RotationUtils;
import baritone.api.utils.VecUtils;
import baritone.api.utils.input.Input;
import baritone.behavior.PathingBehavior;
import baritone.utils.BlockStateInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public abstract class Movement implements MovementHelper, IMovement {
   public static final EnumFacing[] HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP = new EnumFacing[]{EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST, EnumFacing.DOWN};
   public final IBaritone baritone;
   public final IPlayerContext ctx;
   private MovementState currentState = new MovementState().setStatus(MovementStatus.PREPPING);
   public final BetterBlockPos src;
   public final BetterBlockPos dest;
   public final BetterBlockPos[] positionsToBreak;
   public final BetterBlockPos positionToPlace;
   private Double cost;
   public List toBreakCached = null;
   public List toPlaceCached = null;
   public List toWalkIntoCached = null;
   private Set validPositionsCached = null;
   private Boolean calculatedWhileLoaded;

   public Movement(IBaritone baritone2, BetterBlockPos betterBlockPos, BetterBlockPos betterBlockPos2, BetterBlockPos[] betterBlockPosArr) {
      this(baritone2, betterBlockPos, betterBlockPos2, betterBlockPosArr, null);
   }

   public abstract Set calculateValidPositions();

   public BlockPos[] getPositionsToBreak() {
      return this.positionsToBreak;
   }

   @Override
   public boolean safeToCancel() {
      return this.safeToCancel(this.currentState);
   }

   @Override
   public BetterBlockPos getDest() {
      return this.dest;
   }

   public Set getValidPositions() {
      if (this.validPositionsCached == null) {
         this.validPositionsCached = this.calculateValidPositions();
         Objects.requireNonNull(this.validPositionsCached);
      }

      return this.validPositionsCached;
   }

   public abstract double calculateCost(
      CalculationContext calculationContext
   );

   @Override
   public boolean calculatedWhileLoaded() {
      return this.calculatedWhileLoaded;
   }

   public double getCost(CalculationContext calculationContext) {
      if (this.cost == null) {
         this.cost = this.calculateCost(
            calculationContext
         );
      }

      return this.cost;
   }

   public double recalculateCost(CalculationContext calculationContext) {
      this.cost = null;
      return this.getCost(calculationContext);
   }

   @Override
   public BetterBlockPos getSrc() {
      return this.src;
   }

   public List toWalkInto(BlockStateInterface blockStateInterface) {
      if (this.toWalkIntoCached == null) {
         this.toWalkIntoCached = new ArrayList();
      }

      return this.toWalkIntoCached;
   }

   @Override
   public void resetBlockCache() {
      this.toBreakCached = null;
      this.toPlaceCached = null;
      this.toWalkIntoCached = null;
   }

   @Override
   public BlockPos getDirection() {
      return this.getDest().subtract(this.getSrc());
   }

   public boolean safeToCancel(MovementState movementState) {
      return true;
   }

   public boolean prepared(MovementState movementState) {
      if (movementState.getStatus() == MovementStatus.WAITING) {
         return true;
      } else {
         boolean flag = false;

         for (BetterBlockPos betterBlockPos : this.positionsToBreak) {
            if (!this.ctx
                  .world()
                  .getEntitiesWithinAABB(
                     EntityFallingBlock.class, new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.1, 1.0).offset((double)betterBlockPos.x2, (double)betterBlockPos.y2, (double)betterBlockPos.z2)
                  )
                  .isEmpty()
               && (Boolean)Baritone.settings().pauseMiningForFallingBlocks.value) {
               return false;
            }

            if (!MovementHelper.canWalkThrough(this.ctx, betterBlockPos) && !(BlockStateInterface.getBlock(this.ctx, betterBlockPos) instanceof BlockLiquid)) {
               flag = true;
               MovementHelper.switchToBestToolFor(this.ctx, BlockStateInterface.get(this.ctx, betterBlockPos));
               Optional optional = RotationUtils.reachable3(this.ctx, betterBlockPos, this.ctx.playerController().getBlockReachDistance());
               if (!optional.isPresent()) {
                  movementState.setTarget(new MovementTarget(RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), VecUtils.getBlockPosCenter(betterBlockPos), this.ctx.playerRotations()), true));
                  movementState.setInput(Input.CLICK_LEFT, true);
                  return false;
               }

               Rotation rotation = (Rotation)optional.get();
               movementState.setTarget(new MovementTarget(rotation, true));
               if (this.ctx.isLookingAt(betterBlockPos) || this.ctx.playerRotations().isReallyCloseTo(rotation)) {
                  movementState.setInput(Input.CLICK_LEFT, true);
               }

               return false;
            }
         }

         if (flag) {
            movementState.setStatus(MovementStatus.UNREACHABLE);
            return true;
         } else {
            return true;
         }
      }
   }

   public List toPlace(BlockStateInterface blockStateInterface) {
      if (this.toPlaceCached != null) {
         return this.toPlaceCached;
      } else {
         ArrayList list = new ArrayList();
         if (this.positionToPlace != null && !MovementHelper.canWalkOn(blockStateInterface, this.positionToPlace.x2, this.positionToPlace.y2, this.positionToPlace.z2)) {
            list.add(this.positionToPlace);
         }

         this.toPlaceCached = list;
         return list;
      }
   }

   public MovementState updateState(
      MovementState movementState
   ) {
      if (!this.prepared(movementState)) {
         return movementState.setStatus(MovementStatus.PREPPING);
      } else {
         if (movementState.getStatus() == MovementStatus.PREPPING) {
            movementState.setStatus(MovementStatus.WAITING);
         }

         if (movementState.getStatus() == MovementStatus.WAITING) {
            movementState.setStatus(MovementStatus.RUNNING);
         }

         return movementState;
      }
   }

   public List toBreak(BlockStateInterface blockStateInterface) {
      if (this.toBreakCached != null) {
         return this.toBreakCached;
      } else {
         ArrayList list = new ArrayList();

         for (BetterBlockPos betterBlockPos : this.positionsToBreak) {
            if (!MovementHelper.canWalkThrough2(blockStateInterface, betterBlockPos.x2, betterBlockPos.y2, betterBlockPos.z2)) {
               list.add(betterBlockPos);
            }
         }

         this.toBreakCached = list;
         return list;
      }
   }

   @Override
   public double getCost() throws NullPointerException {
      return this.cost;
   }

   public boolean playerInValidPosition() {
      return this.getValidPositions().contains(this.ctx.playerFeet()) || this.getValidPositions().contains(((PathingBehavior)this.baritone.getPathingBehavior()).pathStart());
   }

   @Override
   public void reset() {
      this.currentState = new MovementState().setStatus(MovementStatus.PREPPING);
   }

   public void checkLoadedChunk(CalculationContext calculationContext) {
      this.calculatedWhileLoaded = calculationContext.bsi.worldContainsLoadedChunk(this.dest.x2, this.dest.z2);
   }

   @Override
   public MovementStatus update() {
      this.ctx.player().capabilities.isFlying = false;
      this.currentState = this.updateState(
         this.currentState
      );
      if (MovementHelper.isLiquid(this.ctx, (BlockPos)this.ctx.playerFeet())) {
         this.currentState.setInput(Input.JUMP, true);
      }

      if (this.ctx.player().isEntityInsideOpaqueBlock()) {
         this.ctx.getSelectedBlock().ifPresent(obj -> MovementHelper.switchToBestToolFor(this.ctx, BlockStateInterface.get(this.ctx, (BlockPos)obj)));
         this.currentState.setInput(Input.CLICK_LEFT, true);
      }

      this.currentState.getTarget().getRotation().ifPresent(obj2 -> this.baritone.getLookBehavior().updateTarget((Rotation)obj2, this.currentState.getTarget().isForceRotations()));
      this.baritone.getInputOverrideHandler().clearAllKeys();
      this.currentState.getInputState().forEach((obj3, obj4) -> this.baritone.getInputOverrideHandler().setInputForceState((Input)obj3, (Boolean)obj4));
      this.currentState.getInputState().clear();
      if (this.currentState.getStatus().isComplete()) {
         this.baritone.getInputOverrideHandler().clearAllKeys();
      }

      return this.currentState.getStatus();
   }

   public void override(double d) {
      this.cost = d;
   }

   public Movement(IBaritone baritone2, BetterBlockPos betterBlockPos, BetterBlockPos betterBlockPos2, BetterBlockPos[] betterBlockPosArr, BetterBlockPos betterBlockPos3) {
      this.baritone = baritone2;
      this.ctx = baritone2.getPlayerContext();
      this.src = betterBlockPos;
      this.dest = betterBlockPos2;
      this.positionsToBreak = betterBlockPosArr;
      this.positionToPlace = betterBlockPos3;
   }
}
