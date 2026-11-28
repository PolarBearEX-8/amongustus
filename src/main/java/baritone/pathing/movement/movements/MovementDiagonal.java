package baritone.pathing.movement.movements;

import baritone.Baritone;
import baritone.api.IBaritone;
import baritone.api.pathing.movement.MovementStatus;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.input.Input;
import baritone.pathing.movement.CalculationContext;
import baritone.pathing.movement.Movement;
import baritone.pathing.movement.MovementHelper;
import baritone.pathing.movement.MovementState;
import baritone.utils.BlockStateInterface;
import baritone.utils.pathing.MutableMoveResult;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class MovementDiagonal extends Movement {
   private static final double SQRT_2 = Math.sqrt(2.0);

   @Override
   public boolean prepared(MovementState movementState) {
      return true;
   }

   public static void cost(CalculationContext calculationContext, int n, int n2, int n3, int n4, int n5, MutableMoveResult mutableMoveResult) {
      if (MovementHelper.canWalkThrough(calculationContext, n4, n2 + 1, n5)) {
         IBlockState state = calculationContext.get(n4, n2, n5);
         boolean flag = false;
         boolean flag2 = false;
         IBlockState state2;
         IBlockState state3;
         if (!MovementHelper.canWalkThrough2(calculationContext, n4, n2, n5, state)) {
            flag = true;
            if (!calculationContext.allowDiagonalAscend
               || !MovementHelper.canWalkThrough(calculationContext, n, n2 + 2, n3)
               || !MovementHelper.canWalkOn2(calculationContext, n4, n2, n5, state)
               || !MovementHelper.canWalkThrough(calculationContext, n4, n2 + 2, n5)) {
               return;
            }

            state3 = state;
            state2 = calculationContext.get(n, n2 - 1, n3);
         } else {
            state3 = calculationContext.get(n4, n2 - 1, n5);
            state2 = calculationContext.get(n, n2 - 1, n3);
            if (!MovementHelper.canWalkOn2(calculationContext, n4, n2 - 1, n5, state3)) {
               flag2 = true;
               if (!calculationContext.allowDiagonalDescend || !MovementHelper.canWalkOn2(calculationContext, n4, n2 - 2, n5) || !MovementHelper.canWalkThrough2(calculationContext, n4, n2 - 1, n5, state3)) {
                  return;
               }
            }
         }

         double d = 4.63284688441047;
         if (state3.getBlock() == Blocks.soul_sand) {
            d += 2.316423442205235;
         } else if (state3.getBlock() == Blocks.water) {
            d += calculationContext.walkOnWaterOnePenalty * SQRT_2;
         }

         Block block = state2.getBlock();
         if (block != Blocks.ladder && block != Blocks.vine) {
            if (block == Blocks.soul_sand) {
               d += 2.316423442205235;
            }

            if (!MovementHelper.isLava(calculationContext.get(n, n2 - 1, n5).getBlock())) {
               if (!MovementHelper.isLava(calculationContext.get(n4, n2 - 1, n3).getBlock())) {
                  Block block4 = calculationContext.getBlock(n, n2, n3);
                  boolean flag3 = false;
                  if (MovementHelper.isWater(block4) || MovementHelper.isWater(state.getBlock())) {
                     if (flag) {
                        return;
                     }

                     d = calculationContext.waterWalkSpeed;
                     flag3 = true;
                  }

                  IBlockState state4 = calculationContext.get(n, n2, n5);
                  IBlockState state5 = calculationContext.get(n4, n2, n3);
                  if (flag) {
                     boolean flag4 = MovementHelper.canWalkThrough(calculationContext, n, n2 + 2, n5);
                     boolean flag5 = MovementHelper.canWalkThrough(calculationContext, n, n2 + 1, n5);
                     boolean flag6 = MovementHelper.canWalkThrough2(calculationContext, n, n2, n5, state4);
                     boolean flag7 = MovementHelper.canWalkThrough(calculationContext, n4, n2 + 2, n3);
                     boolean flag8 = MovementHelper.canWalkThrough(calculationContext, n4, n2 + 1, n3);
                     boolean flag9 = MovementHelper.canWalkThrough2(calculationContext, n4, n2, n3, state5);
                     if ((flag4 && flag5 && flag6 || flag7 && flag8 && flag9)
                        && !MovementHelper.avoidWalkingInto(state4.getBlock())
                        && !MovementHelper.avoidWalkingInto(state5.getBlock())
                        && (!flag4 || !flag5 || !MovementHelper.canWalkOn2(calculationContext, n, n2, n5, state4))
                        && (!flag7 || !flag8 || !MovementHelper.canWalkOn2(calculationContext, n4, n2, n3, state5))
                        && (flag4 || !flag5 || !flag6)
                        && (flag7 || !flag8 || !flag9)) {
                        mutableMoveResult.cost = d * SQRT_2 + JUMP_ONE_BLOCK_COST;
                        mutableMoveResult.x = n4;
                        mutableMoveResult.z = n5;
                        mutableMoveResult.y = n2 + 1;
                     }
                  } else {
                     double miningDurationTicks = MovementHelper.getMiningDurationTicks(calculationContext, n, n2, n5, state4, false);
                     double miningDurationTicks2 = MovementHelper.getMiningDurationTicks(calculationContext, n4, n2, n3, state5, false);
                     if (miningDurationTicks == 0.0 || miningDurationTicks2 == 0.0) {
                        IBlockState state6 = calculationContext.get(n, n2 + 1, n5);
                        miningDurationTicks += MovementHelper.getMiningDurationTicks(calculationContext, n, n2 + 1, n5, state6, true);
                        if (miningDurationTicks == 0.0 || miningDurationTicks2 == 0.0) {
                           IBlockState state7 = calculationContext.get(n4, n2 + 1, n3);
                           if (miningDurationTicks != 0.0
                              || (!MovementHelper.avoidWalkingInto(state5.getBlock()) || state5.getBlock() == Blocks.water) && !MovementHelper.avoidWalkingInto(state7.getBlock())) {
                              miningDurationTicks2 += MovementHelper.getMiningDurationTicks(calculationContext, n4, n2 + 1, n3, state7, true);
                              if (miningDurationTicks == 0.0 || miningDurationTicks2 == 0.0) {
                                 if (miningDurationTicks2 != 0.0
                                    || (!MovementHelper.avoidWalkingInto(state4.getBlock()) || state4.getBlock() == Blocks.water)
                                       && !MovementHelper.avoidWalkingInto(state6.getBlock())) {
                                    if (miningDurationTicks != 0.0 || miningDurationTicks2 != 0.0) {
                                       d *= SQRT_2 - 0.001;
                                       if (block4 == Blocks.ladder || block4 == Blocks.vine) {
                                          return;
                                       }
                                    } else if (calculationContext.canSprint && !flag3) {
                                       d *= 0.7692444761225944;
                                    }

                                    mutableMoveResult.cost = d * SQRT_2;
                                    if (flag2) {
                                       mutableMoveResult.cost = mutableMoveResult.cost + Math.max(FALL_N_BLOCKS_COST[1], 0.9265693768820937);
                                       mutableMoveResult.y = n2 - 1;
                                    } else {
                                       mutableMoveResult.y = n2;
                                    }

                                    mutableMoveResult.x = n4;
                                    mutableMoveResult.z = n5;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private boolean sprint() {
      if (MovementHelper.isLiquid(this.ctx, (BlockPos)this.ctx.playerFeet()) && !(Boolean)Baritone.settings().sprintInWater.value) {
         return false;
      } else {
         for (int j = 0; j < 4; j++) {
            if (!MovementHelper.canWalkThrough(this.ctx, this.positionsToBreak[j])) {
               return false;
            }
         }

         return true;
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
      } else if (this.playerInValidPosition() || MovementHelper.isLiquid(this.ctx, (BlockPos)this.src) && this.getValidPositions().contains(this.ctx.playerFeet().up2())) {
         if (this.dest.y2 > this.src.y2) {
            double y2 = (double)this.src.y2;
            if (this.ctx.player().posY < y2 + 0.1 && this.ctx.player().isCollidedHorizontally) {
               movementState.setInput(Input.JUMP, true);
            }
         }

         if (this.sprint()) {
            movementState.setInput(Input.SPRINT, true);
         }

         MovementHelper.moveTowards(this.ctx, movementState, this.dest);
         return movementState;
      } else {
         return movementState.setStatus(MovementStatus.UNREACHABLE);
      }
   }

   @Override
   public List toBreak(BlockStateInterface blockStateInterface) {
      if (this.toBreakCached != null) {
         return this.toBreakCached;
      } else {
         ArrayList list = new ArrayList();

         for (int j = 4; j < 6; j++) {
            if (!MovementHelper.canWalkThrough2(blockStateInterface, this.positionsToBreak[j].x2, this.positionsToBreak[j].y2, this.positionsToBreak[j].z2)) {
               list.add(this.positionsToBreak[j]);
            }
         }

         this.toBreakCached = list;
         return list;
      }
   }

   public MovementDiagonal(IBaritone baritone2, BetterBlockPos betterBlockPos, EnumFacing facing, EnumFacing facing2, int n) {
      this(baritone2, betterBlockPos, betterBlockPos.offset2(facing), betterBlockPos.offset2(facing2), facing2, n);
   }

   @Override
   public Set calculateValidPositions() {
      BetterBlockPos betterBlockPos = new BetterBlockPos(this.src.x2, this.src.y2, this.dest.z2);
      BetterBlockPos betterBlockPos2 = new BetterBlockPos(this.dest.x2, this.src.y2, this.src.z2);
      if (this.dest.y2 < this.src.y2) {
         return ImmutableSet.of(this.src, this.dest.up2(), betterBlockPos, betterBlockPos2, this.dest, betterBlockPos.down2(), new BetterBlockPos[]{betterBlockPos2.down2()});
      } else {
         return this.dest.y2 > this.src.y2
            ? ImmutableSet.of(this.src, this.src.up2(), betterBlockPos, betterBlockPos2, this.dest, betterBlockPos.up2(), new BetterBlockPos[]{betterBlockPos2.up2()})
            : ImmutableSet.of(this.src, this.dest, betterBlockPos, betterBlockPos2);
      }
   }

   @Override
   public boolean safeToCancel(MovementState movementState) {
      EntityPlayerSP player = this.ctx.player();
      double d = 0.25;
      double posX = player.posX;
      double d2 = player.posY - 1.0;
      double posZ = player.posZ;
      if (this.ctx.playerFeet().equals(this.src)) {
         return true;
      } else if (MovementHelper.canWalkOn(this.ctx, new BlockPos(this.src.x2, this.src.y2 - 1, this.dest.z2))
         && MovementHelper.canWalkOn(this.ctx, new BlockPos(this.dest.x2, this.src.y2 - 1, this.src.z2))) {
         return true;
      } else {
         return !this.ctx.playerFeet().equals(new BetterBlockPos(this.src.x2, this.src.y2, this.dest.z2))
               && !this.ctx.playerFeet().equals(new BetterBlockPos(this.dest.x2, this.src.y2, this.src.z2))
            ? true
            : MovementHelper.canWalkOn2(this.ctx, new BetterBlockPos(posX + d, d2, posZ + d))
               || MovementHelper.canWalkOn2(this.ctx, new BetterBlockPos(posX + d, d2, posZ - d))
               || MovementHelper.canWalkOn2(this.ctx, new BetterBlockPos(posX - d, d2, posZ + d))
               || MovementHelper.canWalkOn2(this.ctx, new BetterBlockPos(posX - d, d2, posZ - d));
      }
   }

   private MovementDiagonal(IBaritone baritone2, BetterBlockPos betterBlockPos, BetterBlockPos betterBlockPos2, BetterBlockPos betterBlockPos3, EnumFacing facing, int n) {
      this(baritone2, betterBlockPos, betterBlockPos2.offset2(facing).up2(n), betterBlockPos2, betterBlockPos3);
   }

   private MovementDiagonal(IBaritone baritone2, BetterBlockPos betterBlockPos, BetterBlockPos betterBlockPos2, BetterBlockPos betterBlockPos3, BetterBlockPos betterBlockPos4) {
      super(baritone2, betterBlockPos, betterBlockPos2, new BetterBlockPos[]{betterBlockPos3, betterBlockPos3.up2(), betterBlockPos4, betterBlockPos4.up2(), betterBlockPos2, betterBlockPos2.up2()});
   }

   @Override
   public List toWalkInto(BlockStateInterface blockStateInterface) {
      if (this.toWalkIntoCached == null) {
         this.toWalkIntoCached = new ArrayList();
      }

      ArrayList list = new ArrayList();

      for (int j = 0; j < 4; j++) {
         if (!MovementHelper.canWalkThrough2(blockStateInterface, this.positionsToBreak[j].x2, this.positionsToBreak[j].y2, this.positionsToBreak[j].z2)) {
            list.add(this.positionsToBreak[j]);
         }
      }

      this.toWalkIntoCached = list;
      return this.toWalkIntoCached;
   }
}
