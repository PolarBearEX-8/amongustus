package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockRedstoneDiode extends BlockDirectional {
   public final boolean isRepeaterPowered;

   public void updateState(World world, BlockPos pos, IBlockState state) {
      if (!this.isLocked(world, pos.getX(), pos.getY(), pos.getZ(), state)) {
         boolean flag = this.shouldBePowered(world, pos, state);
         if ((this.isRepeaterPowered && !flag || !this.isRepeaterPowered && flag) && !world.isBlockTickPending(pos, this)) {
            byte b = -1;
            if (this.isFacingTowardsRepeater(world, pos, state)) {
               b = -3;
            } else if (this.isRepeaterPowered) {
               b = -2;
            }

            world.updateBlockTick(pos, this, this.getDelay(state), b);
         }
      }
   }

   public boolean shouldBePowered(World world, BlockPos pos, IBlockState state) {
      return this.calculateInputStrength(world, pos, state) > 0;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   public int getTickDelay(IBlockState state) {
      return this.getDelay(state);
   }

   public boolean isFacingTowardsRepeater(World world, BlockPos pos, IBlockState state) {
      EnumFacing opposite = ((EnumFacing)state.getValue(FACING)).getOpposite();
      BlockPos pos2 = pos.offset(opposite);
      return isRedstoneRepeaterBlockID(world.getBlockState(pos2).getBlock()) ? world.getBlockState(pos2).getValue(FACING) != opposite : false;
   }

   @Override
   public boolean canProvidePower() {
      return true;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
      if (this.shouldBePowered(world, pos, state)) {
         world.scheduleUpdate(pos, this, 1);
      }
   }

   public void notifyNeighbors(World world, BlockPos pos, IBlockState state) {
      EnumFacing value = (EnumFacing)state.getValue(FACING);
      BlockPos pos2 = pos.offset(value.getOpposite());
      world.notifyBlockOfStateChange(pos2, this);
      world.notifyNeighborsOfStateExcept(pos2, this, value);
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (this.canBlockStay(world, pos)) {
         this.updateState(world, pos, state);
      } else {
         this.dropBlockAsItem(world, pos, state, 0);
         world.setBlockToAir(pos);

         for (EnumFacing facing : EnumFacing.getValues()) {
            world.notifyNeighborsOfStateChange(pos.offset(facing), this);
         }
      }
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ()) ? super.canPlaceBlockAt(world, pos) : false;
   }

   public boolean canBlockStay(World world, BlockPos pos) {
      return World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY() - 1, pos.getZ());
   }

   public boolean isAssociated(Block block) {
      return block == this.getPoweredState(this.getDefaultState()).getBlock() || block == this.getUnpoweredState(this.getDefaultState()).getBlock();
   }

   public BlockRedstoneDiode(boolean flag) {
      super(Material.circuits);
      this.isRepeaterPowered = flag;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
   }

   @Override
   public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
      this.notifyNeighbors(world, pos, state);
   }

   public static boolean isRedstoneRepeaterBlockID(Block block) {
      return Blocks.unpowered_repeater.isAssociated(block) || Blocks.unpowered_comparator.isAssociated(block);
   }

   @Override
   public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
      if (this.isRepeaterPowered) {
         for (EnumFacing facing : EnumFacing.getValues()) {
            world.notifyNeighborsOfStateChange(pos.offset(facing), this);
         }
      }

      super.onBlockDestroyedByPlayer(world, pos, state);
   }

   public boolean isPowered(IBlockState state) {
      return this.isRepeaterPowered;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public boolean canPowerSide(Block block) {
      return block.canProvidePower();
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!this.isLocked(world, pos.getX(), pos.getY(), pos.getZ(), state)) {
         boolean flag = this.shouldBePowered(world, pos, state);
         if (this.isRepeaterPowered && !flag) {
            world.setBlockState(pos, this.getUnpoweredState(state), 2);
         } else if (!this.isRepeaterPowered) {
            world.setBlockState(pos, this.getPoweredState(state), 2);
            if (!flag) {
               world.updateBlockTick(pos, this.getPoweredState(state).getBlock(), this.getTickDelay(state), -1);
            }
         }
      }
   }

   @Override
   public void randomTick(World world, BlockPos pos, IBlockState state, Random rand) {
   }

   public int calculateInputStrength(World world, BlockPos pos, IBlockState state) {
      EnumFacing value = (EnumFacing)state.getValue(FACING);
      BlockPos pos2 = pos.offset(value);
      int redstonePower = world.getRedstonePower(pos2, value);
      if (redstonePower >= 15) {
         return redstonePower;
      } else {
         IBlockState blockState2 = world.getBlockState(pos2);
         return Math.max(redstonePower, blockState2.getBlock() == Blocks.redstone_wire ? (Integer)blockState2.getValue(BlockRedstoneWire.POWER) : 0);
      }
   }

   @Override
   public boolean isAssociatedBlock(Block block) {
      return this.isAssociated(block);
   }

   public int getPowerOnSide(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      IBlockState blockState2 = world.getBlockState2(n, n2, n3);
      Block block = blockState2.getBlock();
      return this.canPowerSide(block)
         ? (block == Blocks.redstone_wire ? (Integer)blockState2.getValue(BlockRedstoneWire.POWER) : world.getStrongPower(new BlockPos(n, n2, n3), facing))
         : 0;
   }

   @Override
   public int getWeakPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      return !this.isPowered(state) ? 0 : (state.getValue(FACING) == facing ? this.getActiveSignal(world, pos, state) : 0);
   }

   public abstract IBlockState getPoweredState(IBlockState state);

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      return this.getDefaultState().withProperty(FACING, entity.getHorizontalFacing().getOpposite());
   }

   public boolean isLocked(IBlockAccess world, int n, int n2, int n3, IBlockState state) {
      return false;
   }

   public abstract IBlockState getUnpoweredState(IBlockState state);

   @Override
   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      return facing.getAxis() != EnumFacing.Axis.Y;
   }

   public int getActiveSignal(IBlockAccess world, BlockPos pos, IBlockState state) {
      return 15;
   }

   public abstract int getDelay(IBlockState state);

   public int getPowerOnSides(IBlockAccess world, int n, int n2, int n3, IBlockState state) {
      EnumFacing value = (EnumFacing)state.getValue(FACING);
      EnumFacing facing = value.rotateY();
      EnumFacing facing2 = value.rotateYCCW();
      return Math.max(
         this.getPowerOnSide(world, n + facing.getFrontOffsetX(), n2 + facing.getFrontOffsetY(), n3 + facing.getFrontOffsetZ(), facing),
         this.getPowerOnSide(world, n + facing2.getFrontOffsetX(), n2 + facing2.getFrontOffsetY(), n3 + facing2.getFrontOffsetZ(), facing2)
      );
   }

   @Override
   public int getStrongPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      return this.getWeakPower(world, pos, state, facing);
   }
}
