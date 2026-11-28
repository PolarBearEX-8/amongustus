package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class BlockMobSpawner extends BlockContainer {
   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public int getRenderType() {
      return 3;
   }

   @Override
   public int quantityDropped(Random rand) {
      return 0;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new TileEntityMobSpawner();
   }

   public BlockMobSpawner() {
      super(Material.rock);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return null;
   }

   @Override
   public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float f, int n) {
      super.dropBlockAsItemWithChance(world, pos, state, f, n);
      this.dropXpOnBlockBreak(world, pos, 15 + world.rand.nextInt(15) + world.rand.nextInt(15));
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return null;
   }
}
