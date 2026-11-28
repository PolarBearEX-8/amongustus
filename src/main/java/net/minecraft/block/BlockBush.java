package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class BlockBush extends Block {
   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      super.onNeighborBlockChange(world, pos, state, block);
      this.checkAndDropBlock(world, pos, state);
   }

   @Override
   public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
      this.checkAndDropBlock(world, pos, state);
   }

   public BlockBush(Material material) {
      this(material, material.getMaterialMapColor());
   }

   public void checkAndDropBlock(World world, BlockPos pos, IBlockState state) {
      if (!this.canBlockStay(world, pos, state)) {
         this.dropBlockAsItem(world, pos, state, 0);
         world.setBlockState(pos, Blocks.air.getDefaultState(), 3);
      }
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public BlockBush(Material material, MapColor mapColor) {
      super(material, mapColor);
      this.setTickRandomly(true);
      float f = 0.2F;
      this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return super.canPlaceBlockAt(world, pos) && this.canPlaceBlockOn(world.getBlockState(pos.down()).getBlock());
   }

   public boolean canPlaceBlockOn(Block block) {
      return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland;
   }

   public BlockBush() {
      this(Material.plants);
   }

   @Override
   public boolean hasCollisionBox() {
      return false;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return null;
   }

   public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
      return this.canPlaceBlockOn(world.getBlockState(pos.down()).getBlock());
   }
}
