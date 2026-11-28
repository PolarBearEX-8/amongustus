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
import baritone.pathing.movement.CalculationContext;
import baritone.pathing.movement.Movement;
import baritone.pathing.movement.MovementHelper;
import baritone.pathing.movement.MovementState;
import baritone.utils.BlockStateInterface;
import com.google.common.collect.ImmutableSet;
import java.util.Objects;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class MovementPillar extends Movement {
   @Override
   public double calculateCost(
      CalculationContext calculationContext
   ) {
      return cost(calculationContext, this.src.x2, this.src.y2, this.src.z2);
   }

   @Override
   public Set calculateValidPositions() {
      return ImmutableSet.of(this.src, this.dest);
   }

   public static double cost(CalculationContext calculationContext, int n, int n2, int n3) {
      IBlockState state = calculationContext.get(n, n2, n3);
      Block block = state.getBlock();
      boolean flag = block == Blocks.ladder || block == Blocks.vine;
      IBlockState state2 = calculationContext.get(n, n2 - 1, n3);
      if (!flag) {
         if (state2.getBlock() == Blocks.ladder || state2.getBlock() == Blocks.vine) {
            return 1000000.0;
         }

         if (state2.getBlock() instanceof BlockSlab && !((BlockSlab)state2.getBlock()).isDouble() && state2.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.BOTTOM) {
            return 1000000.0;
         }
      }

      if (block == Blocks.vine && !hasAgainst(calculationContext, n, n2, n3)) {
         return 1000000.0;
      } else {
         IBlockState state3 = calculationContext.get(n, n2 + 2, n3);
         Block block2 = state3.getBlock();
         if (block2 instanceof BlockFenceGate) {
            return 1000000.0;
         } else {
            Block block3 = null;
            if (MovementHelper.isWater(block2) && MovementHelper.isWater(block)) {
               block3 = calculationContext.get(n, n2 + 1, n3).getBlock();
               if (MovementHelper.isWater(block3)) {
                  return 8.51063829787234;
               }
            }

            double d = 0.0;
            if (!flag) {
               d = calculationContext.costOfPlacingAt(n, n2, n3, state);
               if (d >= 1000000.0) {
                  return 1000000.0;
               }

               if (state2.getBlock() == Blocks.air) {
                  d += 0.1;
               }
            }

            if (block instanceof BlockLiquid || state2.getBlock() instanceof BlockLiquid && calculationContext.assumeWalkOnWater) {
               return 1000000.0;
            } else if ((block == Blocks.waterlily || block == Blocks.carpet) && state2.getBlock() instanceof BlockLiquid) {
               return 1000000.0;
            } else {
               double miningDurationTicks = MovementHelper.getMiningDurationTicks(calculationContext, n, n2 + 2, n3, state3, true);
               if (miningDurationTicks >= 1000000.0) {
                  return 1000000.0;
               } else {
                  if (miningDurationTicks != 0.0) {
                     if (block2 != Blocks.ladder && block2 != Blocks.vine) {
                        if (calculationContext.get(n, n2 + 3, n3).getBlock() instanceof BlockFalling) {
                           if (block3 == null) {
                              block3 = calculationContext.get(n, n2 + 1, n3).getBlock();
                           }

                           if (!(block2 instanceof BlockFalling) || !(block3 instanceof BlockFalling)) {
                              return 1000000.0;
                           }
                        }
                     } else {
                        miningDurationTicks = 0.0;
                     }
                  }

                  return flag ? 8.51063829787234 + miningDurationTicks * 5.0 : JUMP_ONE_BLOCK_COST + d + calculationContext.jumpPenalty + miningDurationTicks;
               }
            }
         }
      }
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
         return movementState.setStatus(MovementStatus.UNREACHABLE);
      } else {
         IBlockState state = BlockStateInterface.get(this.ctx, this.src);
         if (MovementHelper.isWater(state.getBlock()) && MovementHelper.isWater(this.ctx, this.dest)) {
            movementState.setTarget(new MovementTarget(RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), VecUtils.getBlockPosCenter(this.dest), this.ctx.playerRotations()), false));
            Vec3 blockPosCenter = VecUtils.getBlockPosCenter(this.dest);
            if (Math.abs(this.ctx.player().posX - blockPosCenter.xCoord) > 0.2 || Math.abs(this.ctx.player().posZ - blockPosCenter.zCoord) > 0.2) {
               movementState.setInput(Input.MOVE_FORWARD, true);
            }

            return this.ctx.playerFeet().equals(this.dest) ? movementState.setStatus(MovementStatus.SUCCESS) : movementState;
         } else {
            boolean flag = state.getBlock() == Blocks.ladder || state.getBlock() == Blocks.vine;
            boolean flag2 = state.getBlock() == Blocks.vine;
            Rotation rotation = RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), VecUtils.getBlockPosCenter(this.positionToPlace), this.ctx.playerRotations());
            if (!flag) {
               movementState.setTarget(new MovementTarget(this.ctx.playerRotations().withPitch(rotation.getPitch()), true));
            }

            boolean flag3 = MovementHelper.canWalkOn2(this.ctx, this.src) || flag;
            if (flag) {
               BlockPos pos = flag2
                  ? getAgainst(new CalculationContext(this.baritone), this.src)
                  : this.src.offset2(((EnumFacing)state.getValue(BlockLadder.FACING)).getOpposite());
               if (pos == null) {
                  this.logDirect2("Unable to climb vines. Consider disabling allowVines.");
                  return movementState.setStatus(MovementStatus.UNREACHABLE);
               } else if (!this.ctx.playerFeet().equals(pos.up()) && !this.ctx.playerFeet().equals(this.dest)) {
                  if (MovementHelper.isBottomSlab(BlockStateInterface.get(this.ctx, this.src.down2()))) {
                     movementState.setInput(Input.JUMP, true);
                  }

                  MovementHelper.moveTowards(this.ctx, movementState, (BlockPos)pos);
                  return movementState;
               } else {
                  return movementState.setStatus(MovementStatus.SUCCESS);
               }
            } else if (!((Baritone)this.baritone).getInventoryBehavior().throwaway(true, this.src.x2, this.src.y2, this.src.z2)) {
               return movementState.setStatus(MovementStatus.UNREACHABLE);
            } else {
               movementState.setInput(Input.SNEAK, this.ctx.player().posY > (double)this.dest.getY() || this.ctx.player().posY < (double)this.src.getY() + 0.2);
               double d = this.ctx.player().posX - ((double)this.dest.getX() + 0.5);
               double d2 = this.ctx.player().posZ - ((double)this.dest.getZ() + 0.5);
               double d3 = Math.sqrt(d * d + d2 * d2);
               double d4 = Math.sqrt(this.ctx.player().motionX * this.ctx.player().motionX + this.ctx.player().motionZ * this.ctx.player().motionZ);
               if (d3 > 0.17) {
                  movementState.setInput(Input.MOVE_FORWARD, true);
                  movementState.setTarget(new MovementTarget(rotation, true));
               } else if (d4 < 0.05) {
                  movementState.setInput(Input.JUMP, this.ctx.player().posY < (double)this.dest.getY());
               }

               if (!flag3) {
                  IBlockState state2 = BlockStateInterface.get(this.ctx, this.src);
                  if (!(state2.getBlock() instanceof BlockAir) && !state2.getBlock().getMaterial().isReplaceable()) {
                     RotationUtils.reachable3(this.ctx, this.src, this.ctx.playerController().getBlockReachDistance()).map(obj -> new MovementTarget((Rotation)obj, true)).ifPresent(obj2 -> movementState.setTarget((MovementTarget)obj2));
                     movementState.setInput(Input.JUMP, false);
                     movementState.setInput(Input.CLICK_LEFT, true);
                     flag3 = false;
                  } else if (this.ctx.player().isSneaking()
                     && (Objects.equals(this.src.down2(), this.ctx.objectMouseOver().getBlockPos()) || Objects.equals(this.src, this.ctx.objectMouseOver().getBlockPos()))
                     && this.ctx.player().posY > (double)this.dest.getY() + 0.1) {
                     movementState.setInput(Input.CLICK_RIGHT, true);
                  }
               }

               return this.ctx.playerFeet().equals(this.dest) && flag3 ? movementState.setStatus(MovementStatus.SUCCESS) : movementState;
            }
         }
      }
   }

   public static BlockPos getAgainst(CalculationContext calculationContext, BetterBlockPos betterBlockPos) {
      if (calculationContext.get(betterBlockPos.north2()).getBlock().isBlockNormalCube()) {
         return betterBlockPos.north2();
      } else if (calculationContext.get(betterBlockPos.south2()).getBlock().isBlockNormalCube()) {
         return betterBlockPos.south2();
      } else if (calculationContext.get(betterBlockPos.east2()).getBlock().isBlockNormalCube()) {
         return betterBlockPos.east2();
      } else {
         return calculationContext.get(betterBlockPos.west2()).getBlock().isBlockNormalCube() ? betterBlockPos.west2() : null;
      }
   }

   public MovementPillar(IBaritone baritone2, BetterBlockPos betterBlockPos, BetterBlockPos betterBlockPos2) {
      super(baritone2, betterBlockPos, betterBlockPos2, new BetterBlockPos[]{betterBlockPos.up2(2)}, betterBlockPos);
   }

   @Override
   public boolean prepared(MovementState movementState) {
      if (this.ctx.playerFeet().equals(this.src) || this.ctx.playerFeet().equals(this.src.down2())) {
         Block block = BlockStateInterface.getBlock(this.ctx, this.src.down2());
         if (block == Blocks.ladder || block == Blocks.vine) {
            movementState.setInput(Input.SNEAK, true);
         }
      }

      return MovementHelper.isWater(this.ctx, this.dest.up2()) ? true : super.prepared(movementState);
   }

   public static boolean hasAgainst(CalculationContext calculationContext, int n, int n2, int n3) {
      return calculationContext.get(n + 1, n2, n3).getBlock().isBlockNormalCube()
         || calculationContext.get(n - 1, n2, n3).getBlock().isBlockNormalCube()
         || calculationContext.get(n, n2, n3 + 1).getBlock().isBlockNormalCube()
         || calculationContext.get(n, n2, n3 - 1).getBlock().isBlockNormalCube();
   }

}
