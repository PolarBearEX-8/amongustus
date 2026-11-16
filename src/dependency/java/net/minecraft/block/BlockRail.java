package net.minecraft.block;

import client.setting.MovementProfileProperty;
import client.util.IBlockState;
import client.util.ImmutableSetEntry;
import client.util.WorldHolder;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockRail extends SpecialBlockAdapter {
   public static final ImmutableSetEntry immutableSetEntry = ImmutableSetEntry.resolveImmutableSetEntry("shape", MovementProfileProperty.class);

   @Override
   public void applyWorldByWorld(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (block.processAction7() && new WorldHolder(this, world, blockPos, blockState).getCount() == 3) {
         this.resolveIBlockStateByWorld(world, blockPos, blockState, false);
      }
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(immutableSetEntry, MovementProfileProperty.resolveMovementProfileProperty(count));
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{immutableSetEntry});
   }

   @Override
   public IProperty getIProperty() {
      return immutableSetEntry;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return ((MovementProfileProperty)blockState.getComparable(immutableSetEntry)).getCount();
   }

   public BlockRail() {
      super(false);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(immutableSetEntry, MovementProfileProperty.NORTH_SOUTH));
   }
}
