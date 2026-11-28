package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockBasePressurePlate extends Block {

   public void setBlockBoundsBasedOnState0(IBlockState state) {
      boolean flag = this.getRedstoneStrength(state) > 0;
      float f = 0.0625F;
      if (flag) {
         this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.03125F, 0.9375F);
      } else {
         this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.0625F, 0.9375F);
      }
   }

   @Override
   public void setBlockBoundsForItemRender() {
      float f = 0.5F;
      float f2 = 0.125F;
      float f3 = 0.5F;
      this.setBlockBounds(0.0F, 0.375F, 0.0F, 1.0F, 0.625F, 1.0F);
   }

   public abstract int computeRedstoneStrength(World world, BlockPos pos);

   @Override
   public int getStrongPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      return facing == EnumFacing.UP ? this.getRedstoneStrength(state) : 0;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      this.setBlockBoundsBasedOnState0(world.getBlockState2(n, n2, n3));
   }

   @Override
   public int getMobilityFlag() {
      return 1;
   }

   public abstract int getRedstoneStrength(IBlockState state);

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!this.canBePlacedOn(world, pos.down())) {
         this.dropBlockAsItem(world, pos, state, 0);
         world.setBlockToAir(pos);
      }
   }

   private boolean canBePlacedOn(World world, BlockPos pos) {
      return World.doesBlockHaveSolidTopSurface(world, pos.getX(), pos.getY(), pos.getZ()) || world.getBlockState(pos).getBlock() instanceof BlockFence;
   }

   public abstract IBlockState setRedstoneStrength(IBlockState state, int n);

   public void updateNeighbors(World world, BlockPos pos) {
      world.notifyNeighborsOfStateChange(pos, this);
      world.notifyNeighborsOfStateChange(pos.down(), this);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public boolean isPassable(IBlockAccess world, int n, int n2, int n3) {
      return true;
   }

   public BlockBasePressurePlate(Material material, MapColor mapColor) {
      super(material, mapColor);
      this.setCreativeTab(CreativeTabs.tabRedstone);
      this.setTickRandomly(true);
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      if (this.getRedstoneStrength(state) > 0) {
         this.updateNeighbors(world, pos);
      }

      super.breakBlock(world, pos, state);
   }

   @Override
   public int tickRate(World world) {
      return 20;
   }

   @Override
   public boolean canSpawnInBlock() {
      return true;
   }

   @Override
   public int getWeakPower(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing facing) {
      return this.getRedstoneStrength(state);
   }

   public void updateState(World world, BlockPos pos, IBlockState state, int n) {
      int n2 = this.computeRedstoneStrength(world, pos);
      boolean flag = n > 0;
      boolean flag2 = n2 > 0;
      if (n != n2) {
         state = this.setRedstoneStrength(state, n2);
         world.setBlockState(pos, state, 2);
         this.updateNeighbors(world, pos);
         world.markBlockRangeForRenderUpdate(pos, pos);
      }

      if (!flag2 && flag) {
         world.playSoundEffect((double)pos.getX() + 0.5, (double)pos.getY() + 0.1, (double)pos.getZ() + 0.5, "random.click", 0.3F, 0.5F);
      } else if (flag2 && !flag) {
         world.playSoundEffect((double)pos.getX() + 0.5, (double)pos.getY() + 0.1, (double)pos.getZ() + 0.5, "random.click", 0.3F, 0.6F);
      }

      if (flag2) {
         world.scheduleUpdate(pos, this, this.tickRate(world));
      }
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   public AxisAlignedBB getSensitiveAABB(BlockPos pos) {
      float f = 0.125F;
      return new AxisAlignedBB(
         (double)((float)pos.getX() + 0.125F),
         (double)pos.getY(),
         (double)((float)pos.getZ() + 0.125F),
         (double)((float)(pos.getX() + 1) - 0.125F),
         (double)pos.getY() + 0.25,
         (double)((float)(pos.getZ() + 1) - 0.125F)
      );
   }

   @Override
   public void randomTick(World world, BlockPos pos, IBlockState state, Random rand) {
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return this.canBePlacedOn(world, pos.down());
   }

   @Override
   public boolean canProvidePower() {
      return true;
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
      if (!world.isRemote) {
         int redstoneStrength = this.getRedstoneStrength(state);
         if (redstoneStrength == 0) {
            this.updateState(world, pos, state, redstoneStrength);
         }
      }
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!world.isRemote) {
         int redstoneStrength = this.getRedstoneStrength(state);
         if (redstoneStrength > 0) {
            this.updateState(world, pos, state, redstoneStrength);
         }
      }
   }

   @Override
   public boolean isPassable(IBlockAccess world, BlockPos pos) {
      return true;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return null;
   }

   public BlockBasePressurePlate(Material material) {
      this(material, material.getMaterialMapColor());
   }
}
