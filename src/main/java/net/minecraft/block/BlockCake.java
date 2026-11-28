package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCake extends Block {
   public static final PropertyInteger BITES;

   public BlockCake() {
      super(Material.cake);
      this.setDefaultState(this.blockState.getBaseState().withProperty(BITES, 0));
      this.setTickRandomly(true);
   }

   private boolean canBlockStay(World world, BlockPos pos) {
      return world.getBlockState(pos.down()).getBlock().getMaterial().isSolid();
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!this.canBlockStay(world, pos)) {
         world.setBlockToAir(pos);
      }
   }

   @Override
   public void setBlockBoundsForItemRender() {
      float f = 0.0625F;
      float f2 = 0.5F;
      this.setBlockBounds(f, 0.0F, f, 1.0F - f, f2, 1.0F - f);
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      this.eatCake(world, pos, state, player);
      return true;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      float f = 0.0625F;
      float f2 = (float)(1 + (Integer)world.getBlockState2(n, n2, n3).getValue(BITES) * 2) / 16.0F;
      float f3 = 0.5F;
      this.setBlockBounds(f2, 0.0F, f, 1.0F - f, f3, 1.0F - f);
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return Items.cake;
   }

   @Override
   public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
      this.eatCake(world, pos, world.getBlockState(pos), player);
   }

   @Override
   public int getComparatorInputOverride(World world, BlockPos pos) {
      return (7 - (Integer)world.getBlockState(pos).getValue(BITES)) * 2;
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return super.canPlaceBlockAt(world, pos) ? this.canBlockStay(world, pos) : false;
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      return true;
   }

   @Override
   public boolean hasComparatorInputOverride() {
      return true;
   }

   @Override
   public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos) {
      return this.getCollisionBoundingBox(world, world.getBlockState(pos), pos.getX(), pos.getY(), pos.getZ());
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return null;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      float f = 0.0625F;
      float f2 = (float)(1 + (Integer)state.getValue(BITES) * 2) / 16.0F;
      float f3 = 0.5F;
      return new AxisAlignedBB(
         (double)((float)n + f2),
         (double)n2,
         (double)((float)n3 + f),
         (double)((float)(n + 1) - f),
         (double)((float)n2 + f3),
         (double)((float)(n3 + 1) - f)
      );
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Integer)state.getValue(BITES);
   }

   @Override
   public int quantityDropped(Random rand) {
      return 0;
   }

   private void eatCake(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
      if (player.canEat(false)) {
         player.triggerAchievement(StatList.cakeSlicesEaten);
         player.getFoodStats().addStats(2, 0.1F);
         int value = (Integer)state.getValue(BITES);
         if (value < 6) {
            world.setBlockState(pos, state.withProperty(BITES, value + 1), 3);
         } else {
            world.setBlockToAir(pos);
         }
      }
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(BITES, n);
   }

   static {
      BITES = PropertyInteger.create("bites", 0, 6);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{BITES});
   }

   @Override
   public boolean isFullCube() {
      return false;
   }
}
