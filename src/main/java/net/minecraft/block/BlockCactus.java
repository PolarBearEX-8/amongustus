package net.minecraft.block;

import java.util.Random;
import net.augustus.modules.Module;
import net.augustus.ClientManagers;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class BlockCactus extends Block {
   public static final PropertyInteger AGE;

   public BlockCactus() {
      super(Material.cactus);
      this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return super.canPlaceBlockAt(world, pos) ? this.canBlockStay(world, pos) : false;
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(AGE, n);
   }

   @Override
   public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos) {
      float f = 0.0625F;
      return new AxisAlignedBB(
         (double)((float)pos.getX() + f),
         (double)pos.getY(),
         (double)((float)pos.getZ() + f),
         (double)((float)(pos.getX() + 1) - f),
         (double)(pos.getY() + 1),
         (double)((float)(pos.getZ() + 1) - f)
      );
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      BlockPos pos2 = pos.up();
      if (world.isAirBlock(pos2)) {
         int n = 1;

         while (world.getBlockState(pos.down(n)).getBlock() == this) {
            n++;
         }

         if (n < 3) {
            int value = (Integer)state.getValue(AGE);
            if (value == 15) {
               world.setBlockState(pos2, this.getDefaultState());
               IBlockState state2 = state.withProperty(AGE, 0);
               world.setBlockState(pos, state2, 4);
               this.onNeighborBlockChange(world, pos2, state2, this);
            } else {
               world.setBlockState(pos, state.withProperty(AGE, value + 1), 4);
            }
         }
      }
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
      entity.attackEntityFrom(DamageSource.cactus, 1.0F);
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!this.canBlockStay(world, pos)) {
         world.destroyBlock(pos, true);
      }
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Integer)state.getValue(AGE);
   }

   static {
      AGE = PropertyInteger.create("age", 0, 15);
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      float f = 0.0625F;
      return ((Module)ClientManagers.moduleManager.antiCactus).isToggled()
         ? new AxisAlignedBB(
            (double)n + this.minX,
            (double)n2 + this.minY,
            (double)n3 + this.minZ,
            (double)n + this.maxX,
            (double)n2 + this.maxY - (double)(ClientManagers.moduleManager.antiCactus.onlySide.getValue() ? f : 0.0F),
            (double)n3 + this.maxZ
         )
         : new AxisAlignedBB(
            (double)((float)n + f),
            (double)n2,
            (double)((float)n3 + f),
            (double)((float)(n + 1) - f),
            (double)((float)(n2 + 1) - f),
            (double)((float)(n3 + 1) - f)
         );
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{AGE});
   }

   public boolean canBlockStay(World world, BlockPos pos) {
      for (EnumFacing facing : (Iterable<EnumFacing>)(EnumFacing.Plane.HORIZONTAL)) {
         if (world.getBlockState(pos.offset(facing)).getBlock().getMaterial().isSolid()) {
            return false;
         }
      }

      Block block = world.getBlockState(pos.down()).getBlock();
      return block == Blocks.cactus || block == Blocks.sand;
   }

}
