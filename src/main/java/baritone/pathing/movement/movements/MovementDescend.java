package baritone.pathing.movement.movements;

import baritone.api.IBaritone;
import baritone.api.pathing.movement.MovementStatus;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.RotationUtils;
import baritone.api.utils.input.Input;
import baritone.pathing.movement.MovementTarget;
import baritone.pathing.movement.CalculationContext;
import baritone.pathing.movement.Movement;
import baritone.pathing.movement.MovementHelper;
import baritone.pathing.movement.MovementState;
import baritone.utils.BlockStateInterface;
import baritone.utils.pathing.MutableMoveResult;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class MovementDescend extends Movement {
   private int numTicks = 0;
   public boolean forceSafeMode = false;

   public void forceSafeMode() {
      this.forceSafeMode = true;
   }

   public boolean skipToAscend() {
      BlockPos pos = this.dest.subtract(this.src.down2()).add(this.dest);
      return !MovementHelper.canWalkThrough(this.ctx, new BetterBlockPos(pos))
         && MovementHelper.canWalkThrough(this.ctx, new BetterBlockPos(pos).up2())
         && MovementHelper.canWalkThrough(this.ctx, new BetterBlockPos(pos).up2(2));
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
      } else {
         BetterBlockPos betterBlockPos = this.ctx.playerFeet();
         BlockPos pos = new BlockPos(this.dest.getX() * 2 - this.src.getX(), this.dest.getY(), this.dest.getZ() * 2 - this.src.getZ());
         if (!betterBlockPos.equals(this.dest) && !betterBlockPos.equals(pos)
            || !MovementHelper.isLiquid(this.ctx, (BlockPos)this.dest) && !(this.ctx.player().posY - (double)this.dest.getY() < 0.5)) {
            if (this.safeMode()) {
               double d = ((double)this.src.getX() + 0.5) * 0.17 + ((double)this.dest.getX() + 0.5) * 0.83;
               double d2 = ((double)this.src.getZ() + 0.5) * 0.17 + ((double)this.dest.getZ() + 0.5) * 0.83;
               movementState.setTarget(
                     new MovementTarget(
                        RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), new Vec3(d, (double)this.dest.getY(), d2), this.ctx.playerRotations()).withPitch(this.ctx.playerRotations().getPitch()),
                        false
                     )
                  )
                  .setInput(Input.MOVE_FORWARD, true);
               return movementState;
            } else {
               double d3 = this.ctx.player().posX - ((double)this.dest.getX() + 0.5);
               double d4 = this.ctx.player().posZ - ((double)this.dest.getZ() + 0.5);
               double d5 = Math.sqrt(d3 * d3 + d4 * d4);
               double d6 = this.ctx.player().posX - ((double)this.src.getX() + 0.5);
               double d7 = this.ctx.player().posZ - ((double)this.src.getZ() + 0.5);
               double d8 = Math.sqrt(d6 * d6 + d7 * d7);
               if (!betterBlockPos.equals(this.dest) || d5 > 0.25) {
                  if (this.numTicks++ < 20 && d8 < 1.25) {
                     MovementHelper.moveTowards(this.ctx, movementState, pos);
                  } else {
                     MovementHelper.moveTowards(this.ctx, movementState, this.dest);
                  }
               }

               return movementState;
            }
         } else {
            return movementState.setStatus(MovementStatus.SUCCESS);
         }
      }
   }

   public MovementDescend(IBaritone baritone2, BetterBlockPos betterBlockPos, BetterBlockPos betterBlockPos2) {
      super(baritone2, betterBlockPos, betterBlockPos2, new BetterBlockPos[]{betterBlockPos2.up2(2), betterBlockPos2.up2(), betterBlockPos2}, betterBlockPos2.down2());
   }

   @Override
   public void reset() {
      super.reset();
      this.numTicks = 0;
      this.forceSafeMode = false;
   }

   public static boolean dynamicFallCost(CalculationContext calculationContext, int n, int n2, int n3, int n4, int n5, double d, IBlockState state, MutableMoveResult mutableMoveResult) {
      if (d != 0.0 && calculationContext.get(n4, n2 + 2, n5).getBlock() instanceof BlockFalling) {
         return false;
      } else if (!MovementHelper.canWalkThrough2(calculationContext, n4, n2 - 2, n5, state)) {
         return false;
      } else {
         double d2 = 0.0;
         int n6 = n2;
         int n7 = 3;

         while (true) {
            int n8 = n2 - n7;
            if (n8 < 0) {
               return false;
            }

            boolean flag = n7 >= calculationContext.minFallHeight;
            IBlockState state2 = calculationContext.get(n4, n8, n5);
            int n9 = n7 - (n2 - n6);
            double d3 = 3.7062775075283763 + FALL_N_BLOCKS_COST[n9] + d + d2;
            if (flag && MovementHelper.isWater(state2.getBlock())) {
               if (!MovementHelper.canWalkThrough2(calculationContext, n4, n8, n5, state2)) {
                  return false;
               }

               if (calculationContext.assumeWalkOnWater) {
                  return false;
               }

               if (MovementHelper.isFlowing(n4, n8, n5, state2, calculationContext.bsi)) {
                  return false;
               }

               if (!MovementHelper.canWalkOn2(calculationContext, n4, n8 - 1, n5)) {
                  return false;
               }

               mutableMoveResult.x = n4;
               mutableMoveResult.y = n8;
               mutableMoveResult.z = n5;
               mutableMoveResult.cost = d3;
               return false;
            }

            if (flag && calculationContext.allowFallIntoLava && MovementHelper.isLava(state2.getBlock())) {
               mutableMoveResult.x = n4;
               mutableMoveResult.y = n8;
               mutableMoveResult.z = n5;
               mutableMoveResult.cost = d3;
               return false;
            }

            if (n9 > 11 || state2.getBlock() != Blocks.vine && state2.getBlock() != Blocks.ladder) {
               if (!MovementHelper.canWalkThrough2(calculationContext, n4, n8, n5, state2)) {
                  if (!MovementHelper.canWalkOn2(calculationContext, n4, n8, n5, state2)) {
                     return false;
                  }

                  if (MovementHelper.isBottomSlab(state2)) {
                     return false;
                  }

                  if (flag && n9 <= calculationContext.maxFallHeightNoWater + 1) {
                     mutableMoveResult.x = n4;
                     mutableMoveResult.y = n8 + 1;
                     mutableMoveResult.z = n5;
                     mutableMoveResult.cost = d3;
                     return false;
                  }

                  if (flag && calculationContext.hasWaterBucket && n9 <= calculationContext.maxFallHeightBucket + 1) {
                     mutableMoveResult.x = n4;
                     mutableMoveResult.y = n8 + 1;
                     mutableMoveResult.z = n5;
                     mutableMoveResult.cost = d3 + calculationContext.getPlaceBlockCost();
                     return true;
                  }

                  return false;
               }
            } else {
               d2 += FALL_N_BLOCKS_COST[n9 - 1];
               d2 += 6.666666666666667;
               n6 = n8;
            }

            n7++;
         }
      }
   }

   @Override
   public double calculateCost(
      CalculationContext calculationContext
   ) {
      MutableMoveResult mutableMoveResult = new MutableMoveResult();
      cost(calculationContext, this.src.x2, this.src.y2, this.src.z2, this.dest.x2, this.dest.z2, mutableMoveResult);
      return mutableMoveResult.y != this.dest.y2 ? 1000000.0 : mutableMoveResult.cost;
   }

   public boolean safeMode() {
      if (this.forceSafeMode) {
         return true;
      } else {
         BlockPos pos = this.dest.subtract(this.src.down2()).add(this.dest);
         if (this.skipToAscend()) {
            return true;
         } else {
            for (int j = 0; j <= 2; j++) {
               if (MovementHelper.avoidWalkingInto(BlockStateInterface.getBlock(this.ctx, pos.up(j)))) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   @Override
   public Set calculateValidPositions() {
      return ImmutableSet.of(this.src, this.dest.up2(), this.dest);
   }

   public static void cost(CalculationContext calculationContext, int n, int n2, int n3, int n4, int n5, MutableMoveResult mutableMoveResult) {
      double d = 0.0;
      IBlockState state = calculationContext.get(n4, n2 - 1, n5);
      d += MovementHelper.getMiningDurationTicks(calculationContext, n4, n2 - 1, n5, state, false);
      if (!(d >= 1000000.0)) {
         d += MovementHelper.getMiningDurationTicks(calculationContext, n4, n2, n5, false);
         if (!(d >= 1000000.0)) {
            d += MovementHelper.getMiningDurationTicks(calculationContext, n4, n2 + 1, n5, true);
            if (!(d >= 1000000.0)) {
               Block block = calculationContext.get(n, n2 - 1, n3).getBlock();
               if (block != Blocks.ladder && block != Blocks.vine) {
                  IBlockState state2 = calculationContext.get(n4, n2 - 2, n5);
                  if (!MovementHelper.canWalkOn2(calculationContext, n4, n2 - 2, n5, state2)) {
                     dynamicFallCost(calculationContext, n, n2, n3, n4, n5, d, state2, mutableMoveResult);
                  } else if (state.getBlock() != Blocks.ladder && state.getBlock() != Blocks.vine) {
                     double d2 = 3.7062775075283763;
                     if (block == Blocks.soul_sand) {
                        d2 *= 2.0;
                     }

                     d += d2 + Math.max(FALL_N_BLOCKS_COST[1], 0.9265693768820937);
                     mutableMoveResult.x = n4;
                     mutableMoveResult.y = n2 - 1;
                     mutableMoveResult.z = n5;
                     mutableMoveResult.cost = d;
                  }
               }
            }
         }
      }
   }
}
