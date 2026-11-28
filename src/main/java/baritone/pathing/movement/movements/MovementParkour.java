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
import baritone.utils.pathing.MutableMoveResult;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

public class MovementParkour extends Movement {
   private static final BetterBlockPos[] EMPTY = new BetterBlockPos[0];
   private final EnumFacing direction;
   private final int dist;
   private final boolean ascend;

   public static void cost(CalculationContext calculationContext, int n, int n2, int n3, EnumFacing facing, MutableMoveResult mutableMoveResult) {
      if (calculationContext.allowParkour) {
         if (n2 != 256 || calculationContext.allowJumpAt256) {
            int frontOffsetX = facing.getFrontOffsetX();
            int frontOffsetZ = facing.getFrontOffsetZ();
            if (MovementHelper.fullyPassable(calculationContext, n + frontOffsetX, n2, n3 + frontOffsetZ)) {
               IBlockState state = calculationContext.get(n + frontOffsetX, n2 - 1, n3 + frontOffsetZ);
               if (!MovementHelper.canWalkOn2(calculationContext, n + frontOffsetX, n2 - 1, n3 + frontOffsetZ, state)) {
                  if (!MovementHelper.avoidWalkingInto(state.getBlock()) || state.getBlock() == Blocks.water || state.getBlock() == Blocks.flowing_water) {
                     if (MovementHelper.fullyPassable(calculationContext, n + frontOffsetX, n2 + 1, n3 + frontOffsetZ)) {
                        if (MovementHelper.fullyPassable(calculationContext, n + frontOffsetX, n2 + 2, n3 + frontOffsetZ)) {
                           if (MovementHelper.fullyPassable(calculationContext, n, n2 + 2, n3)) {
                              IBlockState state2 = calculationContext.get(n, n2 - 1, n3);
                              if (state2.getBlock() != Blocks.vine
                                 && state2.getBlock() != Blocks.ladder
                                 && !(state2.getBlock() instanceof BlockStairs)
                                 && !MovementHelper.isBottomSlab(state2)) {
                                 if (!calculationContext.assumeWalkOnWater || !(state2.getBlock() instanceof BlockLiquid)) {
                                    if (!(calculationContext.getBlock(n, n2, n3) instanceof BlockLiquid)) {
                                       byte b;
                                       if (state2.getBlock() == Blocks.soul_sand) {
                                          b = 2;
                                       } else if (calculationContext.canSprint) {
                                          b = 4;
                                       } else {
                                          b = 3;
                                       }

                                       int n4 = 1;

                                       for (int j = 2; j <= b; n4 = j++) {
                                          int n5 = n + frontOffsetX * j;
                                          int n6 = n3 + frontOffsetZ * j;
                                          if (!MovementHelper.fullyPassable(calculationContext, n5, n2 + 1, n6) || !MovementHelper.fullyPassable(calculationContext, n5, n2 + 2, n6)) {
                                             break;
                                          }

                                          IBlockState state3 = calculationContext.bsi.get0(n5, n2, n6);
                                          if (!MovementHelper.fullyPassable(calculationContext, n5, n2, n6, state3)) {
                                             if (j <= 3
                                                && calculationContext.allowParkourAscend
                                                && calculationContext.canSprint
                                                && MovementHelper.canWalkOn2(calculationContext, n5, n2, n6, state3)
                                                && checkOvershootSafety(calculationContext.bsi, n5 + frontOffsetX, n2 + 1, n6 + frontOffsetZ)) {
                                                mutableMoveResult.x = n5;
                                                mutableMoveResult.y = n2 + 1;
                                                mutableMoveResult.z = n6;
                                                mutableMoveResult.cost = (double)j * 3.563791874554526 + calculationContext.jumpPenalty;
                                                return;
                                             }
                                             break;
                                          }

                                          IBlockState state4 = calculationContext.bsi.get0(n5, n2 - 1, n6);
                                          if (state4.getBlock() != Blocks.farmland && MovementHelper.canWalkOn2(calculationContext, n5, n2 - 1, n6, state4)) {
                                             if (checkOvershootSafety(calculationContext.bsi, n5 + frontOffsetX, n2, n6 + frontOffsetZ)) {
                                                mutableMoveResult.x = n5;
                                                mutableMoveResult.y = n2;
                                                mutableMoveResult.z = n6;
                                                mutableMoveResult.cost = costFromJumpDistance(j) + calculationContext.jumpPenalty;
                                                return;
                                             }
                                             break;
                                          }

                                          if (!MovementHelper.fullyPassable(calculationContext, n5, n2 + 3, n6)) {
                                             break;
                                          }
                                       }

                                       if (calculationContext.allowParkourPlace) {
                                          for (int k = n4; k > 1; k--) {
                                             int n7 = n + k * frontOffsetX;
                                             int n8 = n3 + k * frontOffsetZ;
                                             IBlockState state5 = calculationContext.get(n7, n2 - 1, n8);
                                             double d = calculationContext.costOfPlacingAt(n7, n2 - 1, n8, state5);
                                             if (!(d >= 1000000.0)
                                                && MovementHelper.isReplaceable(n7, n2 - 1, n8, state5, calculationContext.bsi)
                                                && checkOvershootSafety(calculationContext.bsi, n7 + frontOffsetX, n2, n8 + frontOffsetZ)) {
                                                for (int m = 0; m < 5; m++) {
                                                   int n9 = n7 + HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[m].getFrontOffsetX();
                                                   int n10 = n2 - 1 + HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[m].getFrontOffsetY();
                                                   int n11 = n8 + HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[m].getFrontOffsetZ();
                                                   if ((n9 != n7 - frontOffsetX || n11 != n8 - frontOffsetZ) && MovementHelper.canPlaceAgainst(calculationContext.bsi, n9, n10, n11)
                                                      )
                                                    {
                                                      mutableMoveResult.x = n7;
                                                      mutableMoveResult.y = n2;
                                                      mutableMoveResult.z = n8;
                                                      mutableMoveResult.cost = costFromJumpDistance(k) + d + calculationContext.jumpPenalty;
                                                      return;
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
                  }
               }
            }
         }
      }
   }

   @Override
   public double calculateCost(
      CalculationContext calculationContext
   ) {
      MutableMoveResult mutableMoveResult = new MutableMoveResult();
      cost(calculationContext, this.src.x2, this.src.y2, this.src.z2, this.direction, mutableMoveResult);
      return mutableMoveResult.x == this.dest.x2 && mutableMoveResult.y == this.dest.y2 && mutableMoveResult.z == this.dest.z2 ? mutableMoveResult.cost : 1000000.0;
   }

   private static double costFromJumpDistance(int n) {
      switch (n) {
         case 2:
            return 9.26569376882094;
         case 3:
            return 13.89854065323141;
         case 4:
            return 14.255167498218103;
         default:
            throw new IllegalStateException("LOL " + n);
      }
   }

   public static MovementParkour cost(CalculationContext calculationContext, BetterBlockPos betterBlockPos, EnumFacing facing) {
      MutableMoveResult mutableMoveResult = new MutableMoveResult();
      cost(calculationContext, betterBlockPos.x2, betterBlockPos.y2, betterBlockPos.z2, facing, mutableMoveResult);
      int n = Math.abs(mutableMoveResult.x - betterBlockPos.x2) + Math.abs(mutableMoveResult.z - betterBlockPos.z2);
      return new MovementParkour(calculationContext.getBaritone(), betterBlockPos, n, facing, mutableMoveResult.y > betterBlockPos.y2);
   }

   @Override
   public boolean safeToCancel(MovementState movementState) {
      return movementState.getStatus() != MovementStatus.RUNNING;
   }

   private static boolean checkOvershootSafety(BlockStateInterface blockStateInterface, int n, int n2, int n3) {
      return !MovementHelper.avoidWalkingInto(blockStateInterface.get0(n, n2, n3).getBlock()) && !MovementHelper.avoidWalkingInto(blockStateInterface.get0(n, n2 + 1, n3).getBlock());
   }

   @Override
   public Set calculateValidPositions() {
      HashSet set = new HashSet();

      for (int j = 0; j <= this.dist; j++) {
         for (int k = 0; k < 2; k++) {
            set.add(this.src.offset2(this.direction, j).up2(k));
         }
      }

      return set;
   }

   private MovementParkour(IBaritone baritone2, BetterBlockPos betterBlockPos, int n, EnumFacing facing, boolean flag) {
      super(baritone2, betterBlockPos, betterBlockPos.offset2(facing, n).up2(flag ? 1 : 0), EMPTY, betterBlockPos.offset2(facing, n).down2(flag ? 0 : 1));
      this.direction = facing;
      this.dist = n;
      this.ascend = flag;
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
      } else if (this.ctx.playerFeet().y2 < this.src.y2) {
         this.logDebug("sorry");
         return movementState.setStatus(MovementStatus.UNREACHABLE);
      } else {
         if (this.dist >= 4 || this.ascend) {
            movementState.setInput(Input.SPRINT, true);
         }

         MovementHelper.moveTowards(this.ctx, movementState, this.dest);
         if (this.ctx.playerFeet().equals(this.dest)) {
            Block block = BlockStateInterface.getBlock(this.ctx, this.dest);
            if (block == Blocks.vine || block == Blocks.ladder) {
               return movementState.setStatus(MovementStatus.SUCCESS);
            }

            if (this.ctx.player().posY - (double)this.ctx.playerFeet().getY() < 0.094) {
               movementState.setStatus(MovementStatus.SUCCESS);
            }
         } else if (!this.ctx.playerFeet().equals(this.src)) {
            if (!this.ctx.playerFeet().equals(this.src.offset2(this.direction)) && !(this.ctx.player().posY - (double)this.src.y2 > 1.0E-4)) {
               if (!this.ctx.playerFeet().equals(this.dest.offset2(this.direction, -1))) {
                  movementState.setInput(Input.SPRINT, false);
                  if (this.ctx.playerFeet().equals(this.src.offset2(this.direction, -1))) {
                     MovementHelper.moveTowards(this.ctx, movementState, this.src);
                  } else {
                     MovementHelper.moveTowards(this.ctx, movementState, this.src.offset2(this.direction, -1));
                  }
               }
            } else {
               if ((Boolean)Baritone.settings().allowPlace.value
                  && ((Baritone)this.baritone).getInventoryBehavior().hasGenericThrowaway()
                  && !MovementHelper.canWalkOn2(this.ctx, this.dest.down2())
                  && !this.ctx.player().onGround
                  && MovementHelper.attemptToPlaceABlock(movementState, this.baritone, this.dest.down2(), true, false) == PlaceResult.READY_TO_PLACE) {
                  movementState.setInput(Input.CLICK_RIGHT, true);
               }

               if (this.dist == 3 && !this.ascend) {
                  double d = (double)this.src.x2 + 0.5 - this.ctx.player().posX;
                  double d2 = (double)this.src.z2 + 0.5 - this.ctx.player().posZ;
                  if (Math.max(Math.abs(d), Math.abs(d2)) < 0.7) {
                     return movementState;
                  }
               }

               movementState.setInput(Input.JUMP, true);
            }
         }

         return movementState;
      }
   }
}
