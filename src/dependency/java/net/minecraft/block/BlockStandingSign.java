package net.minecraft.block;

import client.util.AuxiliaryImmutableSetEntry;
import client.util.IBlockState;
import client.util.VClipCollisionFilter;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockStandingSign extends VClipCollisionFilter {
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry("rotation", 0, 15);

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(auxiliaryImmutableSetEntry, count);
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (!world.getBlockState(blockPos.down()).getBlock().getMaterial().canExecute2()) {
         this.processAction(world, blockPos, blockState, 0);
         world.matchesBlockPos8(blockPos);
      }

      super.processAction(world, blockPos, blockState, block);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{auxiliaryImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
   }

   public BlockStandingSign() {
      this.setIBlockState(this.blockManager.get().resolveIBlockState(auxiliaryImmutableSetEntry, 0));
   }
}
