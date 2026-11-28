package net.minecraft.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWallSign extends BlockSign {
   public static final PropertyDirection FACING;

   @Override
   public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block) {
      if (!world.getBlockState(pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite())).getBlock().getMaterial().isSolid()) {
         this.dropBlockAsItem(world, pos, state, 0);
         world.setBlockToAir(pos);
      }

      super.onNeighborBlockChange(world, pos, state, block);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{FACING});
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      EnumFacing front = EnumFacing.getFront(n);
      if (front.getAxis() == EnumFacing.Axis.Y) {
         front = EnumFacing.NORTH;
      }

      return this.getDefaultState().withProperty(FACING, front);
   }

   public BlockWallSign() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      return ((EnumFacing)state.getValue(FACING)).getIndex();
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      EnumFacing value = (EnumFacing)world.getBlockState2(n, n2, n3).getValue(FACING);
      float f = 0.28125F;
      float f2 = 0.78125F;
      float f3 = 0.0F;
      float f4 = 1.0F;
      float f5 = 0.125F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      switch (value) {
         case NORTH:
            this.setBlockBounds(f3, f, 1.0F - f5, f4, f2, 1.0F);
            break;
         case SOUTH:
            this.setBlockBounds(f3, f, 0.0F, f4, f2, f5);
            break;
         case WEST:
            this.setBlockBounds(1.0F - f5, f, f3, 1.0F, f2, f4);
            break;
         case EAST:
            this.setBlockBounds(0.0F, f, f3, f5, f2, f4);
      }
   }

   static {
      FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
   }

}
