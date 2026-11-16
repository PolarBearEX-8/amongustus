package net.minecraft.block;

import client.setting.BlockSolidType;
import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class SecondaryBlockAdapter extends Block {
   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      this.applyWorld3(world, blockPos, blockState);
   }

   @Override
   public BlockSolidType processAction3() {
      return BlockSolidType.CUTOUT;
   }

   public boolean matchesBlockByBlock(Block block) {
      return block == Blocks.blockGrass || block == Blocks.block115 || block == Blocks.block7;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      super.processAction(world, blockPos, blockState, block);
      this.applyWorld3(world, blockPos, blockState);
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return super.processAction7(world, blockPos) && this.matchesBlockByBlock(world.getBlockState(blockPos.down()).getBlock());
   }

   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      return this.matchesBlockByBlock(world.getBlockState(blockPos.down()).getBlock());
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return null;
   }

   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState) {
      if (!this.matchesWorld(world, blockPos, blockState)) {
         this.processAction(world, blockPos, blockState, 0);
         world.matchesBlockPos(blockPos, Blocks.air.getDefaultState(), 3);
      }
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   @Override
   public boolean processAction17() {
      return false;
   }

   public SecondaryBlockAdapter() {
      this(Material.material24);
   }

   public SecondaryBlockAdapter(Material material, MapColor mapColor) {
      super(material, mapColor);
      this.processAction(true);
      float factor = 0.2F;
      this.setBlockBounds(0.5F - factor, 0.0F, 0.5F - factor, 0.5F + factor, factor * 3.0F, 0.5F + factor);
      this.processAction(ItemGroupManager.itemGroupManager8);
   }

   public SecondaryBlockAdapter(Material material) {
      this(material, material.getMapColor());
   }
}
