package net.minecraft.block;

import client.setting.BlockSolidType;
import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobspawner;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockMobSpawner extends AdvancedBlockAdapter {
   @Override
   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState, float factor, int count) {
      super.applyWorld3(world, blockPos, blockState, factor, count);
      int count2 = 15 + world.random.nextInt(15) + world.random.nextInt(15);
      this.applyWorld(world, blockPos, count2);
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return null;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return null;
   }

   @Override
   public int processAction(Random random) {
      return 0;
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return new TileEntityMobspawner();
   }

   @Override
   public int processAction13() {
      return 3;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockMobSpawner() {
      super(Material.material4);
   }
}
