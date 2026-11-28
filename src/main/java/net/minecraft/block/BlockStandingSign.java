package net.minecraft.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockStandingSign extends BlockSign {
   public static final PropertyInteger ROTATION;

   static {
      ROTATION = PropertyInteger.create("rotation", 0, 15);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{ROTATION});
   }

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!world.getBlockState(pos.down()).getBlock().getMaterial().isSolid()) {
         this.dropBlockAsItem(world, pos, state, 0);
         world.setBlockToAir(pos);
      }

      super.onNeighborBlockChange(world, pos, state, block);
   }

   public BlockStandingSign() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(ROTATION, 0));
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return (Integer)state.getValue(ROTATION);
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(ROTATION, n);
   }
}
