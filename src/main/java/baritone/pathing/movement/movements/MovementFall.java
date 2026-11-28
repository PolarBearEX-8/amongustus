package baritone.pathing.movement.movements;

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
import baritone.utils.pathing.MutableMoveResult;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;

public class MovementFall extends Movement {
   private static final ItemStack STACK_BUCKET_WATER = new ItemStack(Items.water_bucket);
   private static final ItemStack STACK_BUCKET_EMPTY = new ItemStack(Items.bucket);

   private static BetterBlockPos[] buildPositionsToBreak(BetterBlockPos betterBlockPos, BetterBlockPos betterBlockPos2) {
      int n = betterBlockPos.getX() - betterBlockPos2.getX();
      int n2 = betterBlockPos.getZ() - betterBlockPos2.getZ();
      BetterBlockPos[] betterBlockPosArr = new BetterBlockPos[(betterBlockPos.getY() - betterBlockPos2.getY()) + 2];

      for (int j = 0; j < betterBlockPosArr.length; j++) {
         betterBlockPosArr[j] = new BetterBlockPos(betterBlockPos.getX() - n, betterBlockPos.getY() + 1 - j, betterBlockPos.getZ() - n2);
      }

      return betterBlockPosArr;
   }

   private EnumFacing avoid() {
      for (int j = 0; j < 15; j++) {
         IBlockState blockState = this.ctx.world().getBlockState(this.ctx.playerFeet().down2(j));
         if (blockState.getBlock() == Blocks.ladder) {
            return (EnumFacing)blockState.getValue(BlockLadder.FACING);
         }
      }

      return null;
   }

   public int getInventorySlot(ItemStack stack) {
      for (int j = 0; j <= 8; j++) {
         ItemStack stackInSlot = this.ctx.player().inventory.getStackInSlot(j);
         if (stackInSlot != null && stackInSlot.isItemEqual(stack)) {
            return j;
         }
      }

      return 0;
   }

   private boolean willPlaceBucket() {
      CalculationContext calculationContext = new CalculationContext(this.baritone);
      MutableMoveResult mutableMoveResult = new MutableMoveResult();
      return MovementDescend.dynamicFallCost(
         calculationContext,
         this.src.x2,
         this.src.y2,
         this.src.z2,
         this.dest.x2,
         this.dest.z2,
         0.0,
         calculationContext.get(this.dest.x2, this.src.y2 - 2, this.dest.z2),
         mutableMoveResult
      );
   }

   public MovementFall(IBaritone baritone2, BetterBlockPos betterBlockPos, BetterBlockPos betterBlockPos2) {
      super(baritone2, betterBlockPos, betterBlockPos2, buildPositionsToBreak(betterBlockPos, betterBlockPos2));
   }

   @Override
   public double calculateCost(
      CalculationContext calculationContext
   ) {
      MutableMoveResult mutableMoveResult = new MutableMoveResult();
      MovementDescend.cost(calculationContext, this.src.x2, this.src.y2, this.src.z2, this.dest.x2, this.dest.z2, mutableMoveResult);
      return mutableMoveResult.y != this.dest.y2 ? 1000000.0 : mutableMoveResult.cost;
   }

   @Override
   public boolean safeToCancel(MovementState movementState) {
      return this.ctx.playerFeet().equals(this.src) || movementState.getStatus() != MovementStatus.RUNNING;
   }

   @Override
   public boolean prepared(MovementState movementState) {
      if (movementState.getStatus() == MovementStatus.WAITING) {
         return true;
      } else {
         for (int j = 0; j < 4 && j < this.positionsToBreak.length; j++) {
            if (!MovementHelper.canWalkThrough(this.ctx, this.positionsToBreak[j])) {
               return super.prepared(movementState);
            }
         }

         return true;
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
      } else {
         BetterBlockPos betterBlockPos = this.ctx.playerFeet();
         Rotation rotation = RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), VecUtils.getBlockPosCenter(this.dest), this.ctx.playerRotations());
         Rotation rotation2 = null;
         Block block = this.ctx.world().getBlockState(this.dest).getBlock();
         boolean flag = block == Blocks.water || block == Blocks.flowing_water;
         if (!flag && this.willPlaceBucket() && !betterBlockPos.equals(this.dest)) {
            if (!CalculationContext.hasWaterBucket(this.ctx.player()) || this.ctx.world().provider.getDimensionId() == -1) {
               return movementState.setStatus(MovementStatus.UNREACHABLE);
            }

            if (this.ctx.player().posY - (double)this.dest.getY() < this.ctx.playerController().getBlockReachDistance() && !this.ctx.player().onGround) {
               this.ctx.player().inventory.currentItem = this.getInventorySlot(STACK_BUCKET_WATER);
               rotation2 = new Rotation(rotation.getYaw(), 90.0F);
               if (this.ctx.isLookingAt(this.dest) || this.ctx.isLookingAt(this.dest.down2())) {
                  movementState.setInput(Input.CLICK_RIGHT, true);
               }
            }
         }

         if (rotation2 != null) {
            movementState.setTarget(new MovementTarget(rotation2, true));
         } else {
            movementState.setTarget(new MovementTarget(rotation, false));
         }

         if (betterBlockPos.equals(this.dest) && (this.ctx.player().posY - (double)betterBlockPos.getY() < 0.094 || flag)) {
            if (!flag) {
               return movementState.setStatus(MovementStatus.SUCCESS);
            }

            if (CalculationContext.hasWaterBucket(this.ctx.player())) {
               this.ctx.player().inventory.currentItem = this.getInventorySlot(STACK_BUCKET_EMPTY);
               if (this.ctx.player().motionY >= 0.0) {
                  return movementState.setInput(Input.CLICK_RIGHT, true);
               }

               return movementState;
            }

            if (this.ctx.player().motionY >= 0.0) {
               return movementState.setStatus(MovementStatus.SUCCESS);
            }
         }

         Vec3 blockPosCenter = VecUtils.getBlockPosCenter(this.dest);
         if (Math.abs(this.ctx.player().posX + this.ctx.player().motionX - blockPosCenter.xCoord) > 0.1
            || Math.abs(this.ctx.player().posZ + this.ctx.player().motionZ - blockPosCenter.zCoord) > 0.1) {
            if (!this.ctx.player().onGround && Math.abs(this.ctx.player().motionY) > 0.4) {
               movementState.setInput(Input.SNEAK, true);
            }

            movementState.setInput(Input.MOVE_FORWARD, true);
         }

         Object obj = Optional.ofNullable(this.avoid()).map(EnumFacing::getDirectionVec).orElse(null);
         if (obj == null) {
            obj = this.src.subtract(this.dest);
         } else {
            double d = Math.abs((double)((Vec3i)obj).getX() * (blockPosCenter.xCoord - (double)((Vec3i)obj).getX() / 2.0 - this.ctx.player().posX))
               + Math.abs((double)((Vec3i)obj).getZ() * (blockPosCenter.zCoord - (double)((Vec3i)obj).getZ() / 2.0 - this.ctx.player().posZ));
            if (d < 0.6) {
               movementState.setInput(Input.MOVE_FORWARD, true);
            } else if (!this.ctx.player().onGround) {
               movementState.setInput(Input.SNEAK, false);
            }
         }

         if (rotation2 == null) {
            Vec3 vec = new Vec3(blockPosCenter.xCoord + 0.125 * (double)((Vec3i)obj).getX(), blockPosCenter.yCoord, blockPosCenter.zCoord + 0.125 * (double)((Vec3i)obj).getZ());
            movementState.setTarget(new MovementTarget(RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), vec, this.ctx.playerRotations()), false));
         }

         return movementState;
      }
   }

   @Override
   public Set calculateValidPositions() {
      HashSet set = new HashSet();
      set.add(this.src);

      for (int j = this.src.y2 - this.dest.y2; j >= 0; j--) {
         set.add(this.dest.up2(j));
      }

      return set;
   }
}
