package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCocoa extends BlockDirectional implements IGrowable {
   public static final PropertyInteger AGE;

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public int getDamageValue(World world, BlockPos pos) {
      return EnumDyeColor.BROWN.getDyeDamage();
   }

   public BlockCocoa() {
      super(Material.plants);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(AGE, 0));
      this.setTickRandomly(true);
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      this.setBlockBoundsBasedOnState(world, n, n2, n3);
      return super.getCollisionBoundingBox(world, state, n, n2, n3);
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(n)).withProperty(AGE, (n & 15) >> 2);
   }

   private void dropBlock(World world, BlockPos pos, IBlockState state) {
      world.setBlockState(pos, Blocks.air.getDefaultState(), 3);
      this.dropBlockAsItem(world, pos, state, 0);
   }

   @Override
   public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
      world.setBlockState(pos, state.withProperty(AGE, (Integer)state.getValue(AGE) + 1), 2);
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      IBlockState blockState2 = world.getBlockState2(n, n2, n3);
      EnumFacing value = (EnumFacing)blockState2.getValue(FACING);
      int value2 = (Integer)blockState2.getValue(AGE);
      int n4 = 4 + value2 * 2;
      int n5 = 5 + value2 * 2;
      float f = (float)n4 / 2.0F;
      switch (value) {
         case SOUTH:
            this.setBlockBounds((8.0F - f) / 16.0F, (12.0F - (float)n5) / 16.0F, (15.0F - (float)n4) / 16.0F, (8.0F + f) / 16.0F, 0.75F, 0.9375F);
            break;
         case NORTH:
            this.setBlockBounds((8.0F - f) / 16.0F, (12.0F - (float)n5) / 16.0F, 0.0625F, (8.0F + f) / 16.0F, 0.75F, (1.0F + (float)n4) / 16.0F);
            break;
         case WEST:
            this.setBlockBounds(0.0625F, (12.0F - (float)n5) / 16.0F, (8.0F - f) / 16.0F, (1.0F + (float)n4) / 16.0F, 0.75F, (8.0F + f) / 16.0F);
            break;
         case EAST:
            this.setBlockBounds((15.0F - (float)n4) / 16.0F, (12.0F - (float)n5) / 16.0F, (8.0F - f) / 16.0F, 0.9375F, 0.75F, (8.0F + f) / 16.0F);
      }
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!this.canBlockStay(world, pos, state)) {
         this.dropBlock(world, pos, state);
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING, AGE});
   }

   public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
      pos = pos.offset((EnumFacing)state.getValue(FACING));
      IBlockState blockState2 = world.getBlockState(pos);
      return blockState2.getBlock() == Blocks.log && blockState2.getValue(BlockPlanks.VARIANT) == BlockPlanks.EnumType.JUNGLE;
   }

   @Override
   public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
      EnumFacing facing = EnumFacing.fromAngle((double)entity.rotationYaw);
      world.setBlockState(pos, state.withProperty(FACING, facing), 2);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
      return n | (Integer)state.getValue(AGE) << 2;
   }

   static {
      AGE = PropertyInteger.create("age", 0, 2);
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      if (!this.canBlockStay(world, pos, state)) {
         this.dropBlock(world, pos, state);
      } else if (world.rand.nextInt(5) == 0) {
         int value = (Integer)state.getValue(AGE);
         if (value < 2) {
            world.setBlockState(pos, state.withProperty(AGE, value + 1), 2);
         }
      }
   }

   @Override
   public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean flag) {
      return (Integer)state.getValue(AGE) < 2;
   }

   @Override
   public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos) {
      this.setBlockBoundsBasedOnState(world, pos.getX(), pos.getY(), pos.getZ());
      return super.getSelectedBoundingBox(world, pos);
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      if (!facing.getAxis().isHorizontal()) {
         facing = EnumFacing.NORTH;
      }

      return this.getDefaultState().withProperty(FACING, facing.getOpposite()).withProperty(AGE, 0);
   }

   @Override
   public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
      return true;
   }

   @Override
   public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float f, int n) {
      int value = (Integer)state.getValue(AGE);
      byte b = 1;
      if (value >= 2) {
         b = 3;
      }

      for (int i = 0; i < b; i++) {
         spawnAsEntity(world, pos, new ItemStack(Items.dye, 1, EnumDyeColor.BROWN.getDyeDamage()));
      }
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Items.dye;
   }

}
