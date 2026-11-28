package net.minecraft.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockRail extends BlockRailBase {
   public static final PropertyEnum SHAPE;

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(SHAPE, BlockRailBase.EnumRailDirection.byMetadata(n));
   }

   public BlockRail() {
      super(false);
      this.setDefaultState(this.blockState.getBaseState().withProperty(SHAPE, BlockRailBase.EnumRailDirection.NORTH_SOUTH));
   }

   static {
      SHAPE = PropertyEnum.create("shape", BlockRailBase.EnumRailDirection.class);
   }

   @Override
   public void onNeighborChangedInternal(World world, BlockPos pos, IBlockState state, Block block) {
      if (block.canProvidePower() && new BlockRailBase.Rail(this, world, pos, state).countAdjacentRails() == 3) {
         this.updateDir(world, pos, state, false);
      }
   }

   @Override
   public IProperty getShapeProperty() {
      return SHAPE;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{SHAPE});
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((BlockRailBase.EnumRailDirection)state.getValue(SHAPE)).getMetadata();
   }
}
