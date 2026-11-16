package net.minecraft.block;

import client.util.AuxiliaryImmutableSetEntry;
import client.util.IBlockState;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockHeavyWeightedPressurePlate extends TertiaryBlockAdapter {
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry("power", 0, 15);
   public final int count;

   @Override
   public IBlockState processAction(int count2) {
      return this.getDefaultState().resolveIBlockState(auxiliaryImmutableSetEntry, count2);
   }

   @Override
   public int computeCount(IBlockState blockState) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
   }

   @Override
   public int processAction(World world) {
      return 10;
   }

   @Override
   public IBlockState resolveIBlockState(IBlockState blockState, int count2) {
      return blockState.resolveIBlockState(auxiliaryImmutableSetEntry, count2);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{auxiliaryImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
   }

   @Override
   public int computeCount2(World world, BlockPos blockPos) {
      int count2 = Math.min(world.resolveList(Entity.class, this.resolveAxisAlignedBB(blockPos)).size(), this.count);
      return count2 > 0 ? MathHelper.computeCount((float)Math.min(this.count, count2) / (float)this.count * 15.0F) : 0;
   }

   public BlockHeavyWeightedPressurePlate(Material material, int count2, MapColor mapColor) {
      super(material, mapColor);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(auxiliaryImmutableSetEntry, 0));
      this.count = count2;
   }

   public BlockHeavyWeightedPressurePlate(Material material, int count2) {
      this(material, count2, material.getMapColor());
   }
}
