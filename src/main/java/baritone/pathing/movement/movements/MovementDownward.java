package baritone.pathing.movement.movements;

import baritone.api.IBaritone;
import baritone.api.pathing.movement.MovementStatus;
import baritone.api.utils.BetterBlockPos;
import baritone.pathing.movement.CalculationContext;
import baritone.pathing.movement.Movement;
import baritone.pathing.movement.MovementHelper;
import baritone.pathing.movement.MovementState;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class MovementDownward extends Movement {
   private int numTicks = 0;

   public MovementDownward(IBaritone baritone2, BetterBlockPos betterBlockPos, BetterBlockPos betterBlockPos2) {
      super(baritone2, betterBlockPos, betterBlockPos2, new BetterBlockPos[]{betterBlockPos2});
   }

   @Override
   public MovementState updateState(
      MovementState movementState
   ) {
      super.updateState(
         movementState
      );
      if (movementState.getStatus() != MovementStatus.RUNNING) {
         return movementState;
      } else if (this.ctx.playerFeet().equals(this.dest)) {
         return movementState.setStatus(MovementStatus.SUCCESS);
      } else if (!this.playerInValidPosition()) {
         return movementState.setStatus(MovementStatus.UNREACHABLE);
      } else {
         double d = this.ctx.player().posX - ((double)this.dest.getX() + 0.5);
         double d2 = this.ctx.player().posZ - ((double)this.dest.getZ() + 0.5);
         double d3 = Math.sqrt(d * d + d2 * d2);
         if (this.numTicks++ < 10 && d3 < 0.2) {
            return movementState;
         } else {
            MovementHelper.moveTowards(this.ctx, movementState, this.positionsToBreak[0]);
            return movementState;
         }
      }
   }

   @Override
   public void reset() {
      super.reset();
      this.numTicks = 0;
   }

   @Override
   public Set calculateValidPositions() {
      return ImmutableSet.of(this.src, this.dest);
   }

   public static double cost(CalculationContext calculationContext, int n, int n2, int n3) {
      if (!calculationContext.allowDownward) {
         return 1000000.0;
      } else if (!MovementHelper.canWalkOn2(calculationContext, n, n2 - 2, n3)) {
         return 1000000.0;
      } else {
         IBlockState state = calculationContext.get(n, n2 - 1, n3);
         Block block = state.getBlock();
         return block != Blocks.ladder && block != Blocks.vine ? FALL_N_BLOCKS_COST[1] + MovementHelper.getMiningDurationTicks(calculationContext, n, n2 - 1, n3, state, false) : 6.666666666666667;
      }
   }

   @Override
   public double calculateCost(
      CalculationContext calculationContext
   ) {
      return cost(calculationContext, this.src.x2, this.src.y2, this.src.z2);
   }
}
