package baritone.pathing.movement.movements;

import baritone.Baritone;
import baritone.api.IBaritone;
import baritone.api.pathing.movement.MovementStatus;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.input.Input;
import baritone.pathing.movement.PlaceResult;
import baritone.pathing.movement.CalculationContext;
import baritone.pathing.movement.Movement;
import baritone.pathing.movement.MovementHelper;
import baritone.pathing.movement.MovementState;
import baritone.utils.BlockStateInterface;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

public class MovementAscend extends Movement {
   private int ticksWithoutPlacement = 0;

   public boolean headBonkClear() {
      BetterBlockPos betterBlockPos = this.src.up2(2);

      for (int j = 0; j < 4; j++) {
         BetterBlockPos betterBlockPos2 = betterBlockPos.offset2(EnumFacing.getHorizontal(j));
         if (!MovementHelper.canWalkThrough(this.ctx, betterBlockPos2)) {
            return false;
         }
      }

      return true;
   }

   @Override
   public double calculateCost(
      CalculationContext calculationContext
   ) {
      return cost(calculationContext, this.src.x2, this.src.y2, this.src.z2, this.dest.x2, this.dest.z2);
   }

   @Override
   public MovementState updateState(
      MovementState movementState
   ) {
      if (this.ctx.playerFeet().y2 < this.src.y2) {
         return movementState.setStatus(MovementStatus.UNREACHABLE);
      } else {
         super.updateState(
            movementState
         );
         if (movementState.getStatus() != MovementStatus.RUNNING) {
            return movementState;
         } else if (!this.ctx.playerFeet().equals(this.dest) && !this.ctx.playerFeet().equals(this.dest.add(this.getDirection().down()))) {
            IBlockState state = BlockStateInterface.get(this.ctx, this.positionToPlace);
            if (!MovementHelper.canWalkOn(this.ctx, this.positionToPlace, state)) {
               this.ticksWithoutPlacement++;
               if (MovementHelper.attemptToPlaceABlock(movementState, this.baritone, this.dest.down2(), false, true) == PlaceResult.READY_TO_PLACE) {
                  movementState.setInput(Input.SNEAK, true);
                  if (this.ctx.player().isSneaking()) {
                     movementState.setInput(Input.CLICK_RIGHT, true);
                  }
               }

               if (this.ticksWithoutPlacement > 10) {
                  movementState.setInput(Input.MOVE_BACK, true);
               }

               return movementState;
            } else {
               MovementHelper.moveTowards(this.ctx, movementState, this.dest);
               if (MovementHelper.isBottomSlab(state) && !MovementHelper.isBottomSlab(BlockStateInterface.get(this.ctx, this.src.down2()))) {
                  return movementState;
               } else if (!(Boolean)Baritone.settings().assumeStep.value && !this.ctx.playerFeet().equals(this.src.up2())) {
                  int n = Math.abs(this.src.getX() - this.dest.getX());
                  int n2 = Math.abs(this.src.getZ() - this.dest.getZ());
                  double d = (double)n * Math.abs((double)this.dest.getX() + 0.5 - this.ctx.player().posX)
                     + (double)n2 * Math.abs((double)this.dest.getZ() + 0.5 - this.ctx.player().posZ);
                  double d2 = (double)n2 * Math.abs((double)this.dest.getX() + 0.5 - this.ctx.player().posX)
                     + (double)n * Math.abs((double)this.dest.getZ() + 0.5 - this.ctx.player().posZ);
                  if (Math.abs((double)n * this.ctx.player().motionZ + (double)n2 * this.ctx.player().motionX) > 0.1) {
                     return movementState;
                  } else if (this.headBonkClear()) {
                     return movementState.setInput(Input.JUMP, true);
                  } else {
                     return !(d > 1.2) && !(d2 > 0.2) ? movementState.setInput(Input.JUMP, true) : movementState;
                  }
               } else {
                  return movementState;
               }
            }
         } else {
            return movementState.setStatus(MovementStatus.SUCCESS);
         }
      }
   }

   public MovementAscend(IBaritone baritone2, BetterBlockPos betterBlockPos, BetterBlockPos betterBlockPos2) {
      super(baritone2, betterBlockPos, betterBlockPos2, new BetterBlockPos[]{betterBlockPos2, betterBlockPos.up2(2), betterBlockPos2.up2()}, betterBlockPos2.down2());
   }

   @Override
   public Set calculateValidPositions() {
      BetterBlockPos betterBlockPos = new BetterBlockPos(this.src.subtract(this.getDirection()).up());
      return ImmutableSet.of(this.src, this.src.up2(), this.dest, betterBlockPos, betterBlockPos.up2());
   }

   @Override
   public void reset() {
      super.reset();
      this.ticksWithoutPlacement = 0;
   }

   @Override
   public boolean safeToCancel(MovementState movementState) {
      return movementState.getStatus() != MovementStatus.RUNNING || this.ticksWithoutPlacement == 0;
   }

   public static double cost(CalculationContext calculationContext, int n, int n2, int n3, int n4, int n5) {
      IBlockState state = calculationContext.get(n4, n2, n5);
      double d = 0.0;
      if (!MovementHelper.canWalkOn2(calculationContext, n4, n2, n5, state)) {
         d = calculationContext.costOfPlacingAt(n4, n2, n5, state);
         if (d >= 1000000.0) {
            return 1000000.0;
         }

         if (!MovementHelper.isReplaceable(n4, n2, n5, state, calculationContext.bsi)) {
            return 1000000.0;
         }

         boolean flag = false;

         for (int j = 0; j < 5; j++) {
            int n6 = n4 + HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[j].getFrontOffsetX();
            int n7 = n2 + HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[j].getFrontOffsetY();
            int n8 = n5 + HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[j].getFrontOffsetZ();
            if ((n6 != n || n8 != n3) && MovementHelper.canPlaceAgainst(calculationContext.bsi, n6, n7, n8)) {
               flag = true;
               break;
            }
         }

         if (!flag) {
            return 1000000.0;
         }
      }

      IBlockState state2 = calculationContext.get(n, n2 + 2, n3);
      if (!(calculationContext.get(n, n2 + 3, n3).getBlock() instanceof BlockFalling)
         || !MovementHelper.canWalkThrough(calculationContext, n, n2 + 1, n3) && state2.getBlock() instanceof BlockFalling) {
         IBlockState state3 = calculationContext.get(n, n2 - 1, n3);
         if (state3.getBlock() != Blocks.ladder && state3.getBlock() != Blocks.vine) {
            boolean isBottomSlab = MovementHelper.isBottomSlab(state3);
            boolean isBottomSlab2 = MovementHelper.isBottomSlab(state);
            if (isBottomSlab && !isBottomSlab2) {
               return 1000000.0;
            } else {
               double d2;
               if (isBottomSlab2) {
                  if (isBottomSlab) {
                     d2 = Math.max(JUMP_ONE_BLOCK_COST, 4.63284688441047);
                     d2 += calculationContext.jumpPenalty;
                  } else {
                     d2 = 4.63284688441047;
                  }
               } else {
                  if (state.getBlock() == Blocks.soul_sand) {
                     d2 = 9.26569376882094;
                  } else {
                     d2 = Math.max(JUMP_ONE_BLOCK_COST, 4.63284688441047);
                  }

                  d2 += calculationContext.jumpPenalty;
               }

               double d3 = d2 + d;
               d3 += MovementHelper.getMiningDurationTicks(calculationContext, n, n2 + 2, n3, state2, false);
               if (d3 >= 1000000.0) {
                  return 1000000.0;
               } else {
                  d3 += MovementHelper.getMiningDurationTicks(calculationContext, n4, n2 + 1, n5, false);
                  return d3 >= 1000000.0 ? 1000000.0 : d3 + MovementHelper.getMiningDurationTicks(calculationContext, n4, n2 + 2, n5, true);
               }
            }
         } else {
            return 1000000.0;
         }
      } else {
         return 1000000.0;
      }
   }
}
