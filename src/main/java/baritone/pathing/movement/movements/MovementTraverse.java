package baritone.pathing.movement.movements;

import baritone.Baritone;
import baritone.api.IBaritone;
import baritone.api.pathing.movement.MovementStatus;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.Rotation;
import baritone.api.utils.RotationUtils;
import baritone.api.utils.VecUtils;
import baritone.api.utils.input.Input;
import baritone.pathing.movement.MovementTarget;
import baritone.pathing.movement.PlaceResult;
import baritone.pathing.movement.CalculationContext;
import baritone.pathing.movement.Movement;
import baritone.pathing.movement.MovementHelper;
import baritone.pathing.movement.MovementState;
import baritone.utils.BlockStateInterface;
import com.google.common.collect.ImmutableSet;
import java.util.Optional;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class MovementTraverse extends Movement {
   private boolean wasTheBridgeBlockAlwaysThere = true;

   public MovementTraverse(IBaritone baritone2, BetterBlockPos betterBlockPos, BetterBlockPos betterBlockPos2) {
      super(baritone2, betterBlockPos, betterBlockPos2, new BetterBlockPos[]{betterBlockPos2.up2(), betterBlockPos2}, betterBlockPos2.down2());
   }

   @Override
   public MovementState updateState(
      MovementState movementState
   ) {
      super.updateState(
         movementState
      );
      IBlockState state = BlockStateInterface.get(this.ctx, this.positionsToBreak[0]);
      IBlockState state2 = BlockStateInterface.get(this.ctx, this.positionsToBreak[1]);
      if (movementState.getStatus() != MovementStatus.RUNNING) {
         if (!(Boolean)Baritone.settings().walkWhileBreaking.value) {
            return movementState;
         } else if (movementState.getStatus() != MovementStatus.PREPPING) {
            return movementState;
         } else if (MovementHelper.avoidWalkingInto(state.getBlock())) {
            return movementState;
         } else if (MovementHelper.avoidWalkingInto(state2.getBlock())) {
            return movementState;
         } else {
            if (Math.max(
               Math.abs(this.ctx.player().posX - ((double)this.dest.getX() + 0.5)), Math.abs(this.ctx.player().posZ - ((double)this.dest.getZ() + 0.5))
            ) < 0.83) {
               return movementState;
            } else if (!movementState.getTarget().getRotation().isPresent()) {
               return movementState;
            } else {
               float yaw = RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), VecUtils.calculateBlockCenter(this.ctx.world(), this.dest), this.ctx.playerRotations()).getYaw();
               float pitch = ((Rotation)movementState.getTarget().getRotation().get()).getPitch();
               if (state.getBlock().isFullCube() || state.getBlock() instanceof BlockAir && (state2.getBlock().isFullCube() || state2.getBlock() instanceof BlockAir)
                  )
                {
                  pitch = 26.0F;
               }

               return movementState.setTarget(new MovementTarget(new Rotation(yaw, pitch), true)).setInput(Input.MOVE_FORWARD, true).setInput(Input.SPRINT, true);
            }
         }
      } else {
         movementState.setInput(Input.SNEAK, false);
         Block block = BlockStateInterface.get(this.ctx, this.src.down2()).getBlock();
         boolean flag = block == Blocks.ladder || block == Blocks.vine;
         if (state.getBlock() instanceof BlockDoor || state2.getBlock() instanceof BlockDoor) {
            boolean flag2 = state.getBlock() instanceof BlockDoor && !MovementHelper.isDoorPassable(this.ctx, this.src, this.dest)
               || state2.getBlock() instanceof BlockDoor && !MovementHelper.isDoorPassable(this.ctx, this.dest, this.src);
            boolean flag3 = !Blocks.iron_door.equals(state.getBlock()) && !Blocks.iron_door.equals(state2.getBlock());
            if (flag2 && flag3) {
               return movementState.setTarget(new MovementTarget(RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), VecUtils.calculateBlockCenter(this.ctx.world(), this.positionsToBreak[0]), this.ctx.playerRotations()), true))
                  .setInput(Input.CLICK_RIGHT, true);
            }
         }

         if (state.getBlock() instanceof BlockFenceGate || state2.getBlock() instanceof BlockFenceGate) {
            BetterBlockPos betterBlockPos = !MovementHelper.isGatePassable(this.ctx, this.positionsToBreak[0], this.src.up2())
               ? this.positionsToBreak[0]
               : (!MovementHelper.isGatePassable(this.ctx, this.positionsToBreak[1], this.src) ? this.positionsToBreak[1] : null);
            if (betterBlockPos != null) {
               Optional optional = RotationUtils.reachable(this.ctx, betterBlockPos);
               if (optional.isPresent()) {
                  return movementState.setTarget(new MovementTarget((Rotation)optional.get(), true)).setInput(Input.CLICK_RIGHT, true);
               }
            }
         }

         boolean flag4 = MovementHelper.canWalkOn2(this.ctx, this.positionToPlace) || flag;
         BetterBlockPos betterBlockPos2 = this.ctx.playerFeet();
         if (betterBlockPos2.getY() != this.dest.getY() && !flag) {
            this.logDebug("Wrong Y coordinate");
            return betterBlockPos2.getY() < this.dest.getY() ? movementState.setInput(Input.JUMP, true) : movementState;
         } else if (flag4) {
            if (betterBlockPos2.equals(this.dest)) {
               return movementState.setStatus(MovementStatus.SUCCESS);
            } else if (!(Boolean)Baritone.settings().overshootTraverse.value
               || !betterBlockPos2.equals(this.dest.add(this.getDirection())) && !betterBlockPos2.equals(this.dest.add(this.getDirection()).add(this.getDirection()))) {
               Block block2 = BlockStateInterface.get(this.ctx, this.src).getBlock();
               Block block3 = BlockStateInterface.get(this.ctx, this.src.up2()).getBlock();
               double y2 = (double)this.src.y2;
               if (!(this.ctx.player().posY > y2 + 0.1)
                  || this.ctx.player().onGround
                  || block2 != Blocks.vine && block2 != Blocks.ladder && block3 != Blocks.vine && block3 != Blocks.ladder) {
                  BlockPos pos = this.dest.subtract(this.src).add(this.dest);
                  Block block4 = BlockStateInterface.get(this.ctx, pos).getBlock();
                  Block block5 = BlockStateInterface.get(this.ctx, pos.up()).getBlock();
                  if (this.wasTheBridgeBlockAlwaysThere
                     && (!MovementHelper.isLiquid(this.ctx, (BlockPos)betterBlockPos2) || (Boolean)Baritone.settings().sprintInWater.value)
                     && (!MovementHelper.avoidWalkingInto(block4) || MovementHelper.isWater(block4))
                     && !MovementHelper.avoidWalkingInto(block5)) {
                     movementState.setInput(Input.SPRINT, true);
                  }

                  IBlockState state3 = BlockStateInterface.get(this.ctx, this.dest.down2());
                  Object obj = this.positionsToBreak[0];
                  if (betterBlockPos2.getY() != this.dest.getY() && flag && (state3.getBlock() == Blocks.vine || state3.getBlock() == Blocks.ladder)) {
                     obj = state3.getBlock() == Blocks.vine
                        ? MovementPillar.getAgainst(new CalculationContext(this.baritone), this.dest.down2())
                        : this.dest.offset2(((EnumFacing)state3.getValue(BlockLadder.FACING)).getOpposite());
                     if (obj == null) {
                        this.logDirect2("Unable to climb vines. Consider disabling allowVines.");
                        return movementState.setStatus(MovementStatus.UNREACHABLE);
                     }
                  }

                  MovementHelper.moveTowards(this.ctx, movementState, (BlockPos)obj);
                  return movementState;
               } else {
                  return movementState;
               }
            } else {
               return movementState.setStatus(MovementStatus.SUCCESS);
            }
         } else {
            this.wasTheBridgeBlockAlwaysThere = false;
            Block block6 = BlockStateInterface.get(this.ctx, betterBlockPos2.down()).getBlock();
            if (block6.equals(Blocks.soul_sand) || block6 instanceof BlockSlab) {
               if (Math.max(
                  Math.abs((double)this.dest.getX() + 0.5 - this.ctx.player().posX), Math.abs((double)this.dest.getZ() + 0.5 - this.ctx.player().posZ)
               ) < 0.85) {
                  MovementHelper.moveTowards(this.ctx, movementState, this.dest);
                  return movementState.setInput(Input.MOVE_FORWARD, false).setInput(Input.MOVE_BACK, true);
               }
            }

            double d3 = Math.max(
               Math.abs(this.ctx.player().posX - ((double)this.dest.getX() + 0.5)), Math.abs(this.ctx.player().posZ - ((double)this.dest.getZ() + 0.5))
            );
            PlaceResult placeResult = MovementHelper.attemptToPlaceABlock(movementState, this.baritone, this.dest.down2(), false, true);
            if ((placeResult == PlaceResult.READY_TO_PLACE || d3 < 0.6) && !(Boolean)Baritone.settings().assumeSafeWalk.value) {
               movementState.setInput(Input.SNEAK, true);
            }

            switch (placeResult) {
               case READY_TO_PLACE:
                  if (this.ctx.player().isSneaking() || (Boolean)Baritone.settings().assumeSafeWalk.value) {
                     movementState.setInput(Input.CLICK_RIGHT, true);
                  }

                  return movementState;
               case ATTEMPTING:
                  if (d3 > 0.83) {
                     float yaw2 = RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), VecUtils.getBlockPosCenter(this.dest), this.ctx.playerRotations()).getYaw();
                     if ((double)Math.abs(movementState.getTarget().rotation.getYaw() - yaw2) < 0.1) {
                        return movementState.setInput(Input.MOVE_FORWARD, true);
                     }
                  } else if (this.ctx.playerRotations().isReallyCloseTo(movementState.getTarget().rotation)) {
                     return movementState.setInput(Input.CLICK_LEFT, true);
                  }

                  return movementState;
               default:
                  if (betterBlockPos2.equals(this.dest)) {
                     double d4 = ((double)(this.dest.getX() + this.src.getX()) + 1.0) * 0.5;
                     double d5 = ((double)(this.dest.getY() + this.src.getY()) - 1.0) * 0.5;
                     double d6 = ((double)(this.dest.getZ() + this.src.getZ()) + 1.0) * 0.5;
                     BetterBlockPos betterBlockPos3 = this.src.down2();
                     Rotation rotation = RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), new Vec3(d4, d5, d6), this.ctx.playerRotations());
                     float pitch2 = rotation.getPitch();
                     if (Math.max(Math.abs(this.ctx.player().posX - d4), Math.abs(this.ctx.player().posZ - d6)) < 0.29) {
                        float yaw3 = RotationUtils.calcRotationFromVec3d(VecUtils.getBlockPosCenter(this.dest), this.ctx.playerHead(), this.ctx.playerRotations()).getYaw();
                        movementState.setTarget(new MovementTarget(new Rotation(yaw3, pitch2), true));
                        movementState.setInput(Input.MOVE_BACK, true);
                     } else {
                        movementState.setTarget(new MovementTarget(rotation, true));
                     }

                     if (this.ctx.isLookingAt(betterBlockPos3)) {
                        return movementState.setInput(Input.CLICK_RIGHT, true);
                     } else {
                        if (this.ctx.playerRotations().isReallyCloseTo(movementState.getTarget().rotation)) {
                           movementState.setInput(Input.CLICK_LEFT, true);
                        }

                        return movementState;
                     }
                  } else {
                     MovementHelper.moveTowards(this.ctx, movementState, this.positionsToBreak[0]);
                     return movementState;
                  }
            }
         }
      }
   }

   public static double cost(CalculationContext calculationContext, int n, int n2, int n3, int n4, int n5) {
      IBlockState state = calculationContext.get(n4, n2 + 1, n5);
      IBlockState state2 = calculationContext.get(n4, n2, n5);
      IBlockState state3 = calculationContext.get(n4, n2 - 1, n5);
      IBlockState state4 = calculationContext.get(n, n2 - 1, n3);
      Block block = state4.getBlock();
      boolean flag = MovementHelper.mustBeSolidToWalkOn(calculationContext, n, n2 - 1, n3, state4);
      if (MovementHelper.canWalkOn2(calculationContext, n4, n2 - 1, n5, state3)) {
         double d = 4.63284688441047;
         boolean flag2 = false;
         if (!MovementHelper.isWater(state.getBlock()) && !MovementHelper.isWater(state2.getBlock())) {
            if (state3.getBlock() == Blocks.soul_sand) {
               d += 2.316423442205235;
            } else if (state3.getBlock() == Blocks.water) {
               d += calculationContext.walkOnWaterOnePenalty;
            }

            if (block == Blocks.soul_sand) {
               d += 2.316423442205235;
            }
         } else {
            d = calculationContext.waterWalkSpeed;
            flag2 = true;
         }

         double miningDurationTicks = MovementHelper.getMiningDurationTicks(calculationContext, n4, n2, n5, state2, false);
         if (miningDurationTicks >= 1000000.0) {
            return 1000000.0;
         } else {
            double miningDurationTicks2 = MovementHelper.getMiningDurationTicks(calculationContext, n4, n2 + 1, n5, state, true);
            if (miningDurationTicks == 0.0 && miningDurationTicks2 == 0.0) {
               if (!flag2 && calculationContext.canSprint) {
                  d *= 0.7692444761225944;
               }

               return d;
            } else {
               if (block == Blocks.ladder || block == Blocks.vine) {
                  miningDurationTicks *= 5.0;
                  miningDurationTicks2 *= 5.0;
               }

               return d + miningDurationTicks + miningDurationTicks2;
            }
         }
      } else if (block != Blocks.ladder && block != Blocks.vine) {
         if (!MovementHelper.isReplaceable(n4, n2 - 1, n5, state3, calculationContext.bsi)) {
            return 1000000.0;
         } else {
            boolean flag3 = MovementHelper.isWater(state.getBlock()) || MovementHelper.isWater(state2.getBlock());
            if (MovementHelper.isWater(state3.getBlock()) && flag3) {
               return 1000000.0;
            } else {
               double d2 = calculationContext.costOfPlacingAt(n4, n2 - 1, n5, state3);
               if (d2 >= 1000000.0) {
                  return 1000000.0;
               } else {
                  double miningDurationTicks3 = MovementHelper.getMiningDurationTicks(calculationContext, n4, n2, n5, state2, false);
                  if (miningDurationTicks3 >= 1000000.0) {
                     return 1000000.0;
                  } else {
                     double miningDurationTicks4 = MovementHelper.getMiningDurationTicks(calculationContext, n4, n2 + 1, n5, state, true);
                     double d3 = flag3 ? calculationContext.waterWalkSpeed : 4.63284688441047;

                     for (int j = 0; j < 5; j++) {
                        int n6 = n4 + HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[j].getFrontOffsetX();
                        int n7 = n2 - 1 + HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[j].getFrontOffsetY();
                        int n8 = n5 + HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[j].getFrontOffsetZ();
                        if ((n6 != n || n8 != n3) && MovementHelper.canPlaceAgainst(calculationContext.bsi, n6, n7, n8)) {
                           return d3 + d2 + miningDurationTicks3 + miningDurationTicks4;
                        }
                     }

                     if (block == Blocks.soul_sand || block instanceof BlockSlab && !((BlockSlab)block).isDouble()) {
                        return 1000000.0;
                     } else if (!flag) {
                        return 1000000.0;
                     } else {
                        Block block2 = calculationContext.getBlock(n, n2, n3);
                        if ((block2 == Blocks.waterlily || block2 == Blocks.carpet) && block instanceof BlockLiquid) {
                           return 1000000.0;
                        } else {
                           d3 *= 3.3207692307692307;
                           return d3 + d2 + miningDurationTicks3 + miningDurationTicks4;
                        }
                     }
                  }
               }
            }
         }
      } else {
         return 1000000.0;
      }
   }

   @Override
   public boolean safeToCancel(MovementState movementState) {
      return movementState.getStatus() != MovementStatus.RUNNING || MovementHelper.canWalkOn2(this.ctx, this.dest.down2());
   }

   @Override
   public boolean prepared(MovementState movementState) {
      if (this.ctx.playerFeet().equals(this.src) || this.ctx.playerFeet().equals(this.src.down2())) {
         Block block = BlockStateInterface.getBlock(this.ctx, this.src.down2());
         if (block == Blocks.ladder || block == Blocks.vine) {
            movementState.setInput(Input.SNEAK, true);
         }
      }

      return super.prepared(movementState);
   }

   @Override
   public Set calculateValidPositions() {
      return ImmutableSet.of(this.src, this.dest);
   }

   @Override
   public void reset() {
      super.reset();
      this.wasTheBridgeBlockAlwaysThere = true;
   }

   @Override
   public double calculateCost(
      CalculationContext calculationContext
   ) {
      return cost(calculationContext, this.src.x2, this.src.y2, this.src.z2, this.dest.x2, this.dest.z2);
   }

}
