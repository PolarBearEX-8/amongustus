package net.minecraft.block;

import com.google.common.base.Predicate;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockRailPowered extends BlockRailBase {
   public static final PropertyEnum SHAPE = PropertyEnum.create("shape", BlockRailBase.EnumRailDirection.class, new BlockRailPowered.FilterPredicate());
   public static final PropertyBool POWERED = PropertyBool.create("powered");

   @Override
   public void onNeighborChangedInternal(World world, BlockPos pos, IBlockState state, Block block) {
      boolean value = (Boolean)state.getValue(POWERED);
      boolean flag = world.isBlockPowered(pos) || this.findPoweredRailSignal(world, pos, state, true, 0) || this.findPoweredRailSignal(world, pos, state, false, 0);
      if (flag != value) {
         world.setBlockState(pos, state.withProperty(POWERED, flag), 3);
         world.notifyNeighborsOfStateChange(pos.down(), this);
         if (((BlockRailBase.EnumRailDirection)state.getValue(SHAPE)).isAscending()) {
            world.notifyNeighborsOfStateChange(pos.up(), this);
         }
      }
   }

   @Override
   public IBlockState getStateFromMeta(int n) {
      return this.getDefaultState().withProperty(SHAPE, BlockRailBase.EnumRailDirection.byMetadata(n & 7)).withProperty(POWERED, (n & 8) > 0);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, new IProperty[]{SHAPE, POWERED});
   }

   @Override
   public IProperty getShapeProperty() {
      return SHAPE;
   }

   public boolean isSameRailWithPower(World world, BlockPos pos, boolean flag, int n, BlockRailBase.EnumRailDirection enumRailDirection) {
      IBlockState blockState2 = world.getBlockState(pos);
      if (blockState2.getBlock() != this) {
         return false;
      } else {
         BlockRailBase.EnumRailDirection value = (BlockRailBase.EnumRailDirection)blockState2.getValue(SHAPE);
         return enumRailDirection != BlockRailBase.EnumRailDirection.EAST_WEST
               || value != BlockRailBase.EnumRailDirection.NORTH_SOUTH
                  && value != BlockRailBase.EnumRailDirection.ASCENDING_NORTH
                  && value != BlockRailBase.EnumRailDirection.ASCENDING_SOUTH
            ? (
               enumRailDirection != BlockRailBase.EnumRailDirection.NORTH_SOUTH
                     || value != BlockRailBase.EnumRailDirection.EAST_WEST
                        && value != BlockRailBase.EnumRailDirection.ASCENDING_EAST
                        && value != BlockRailBase.EnumRailDirection.ASCENDING_WEST
                  ? ((Boolean)blockState2.getValue(POWERED) ? (world.isBlockPowered(pos) ? true : this.findPoweredRailSignal(world, pos, blockState2, flag, n + 1)) : false)
                  : false
            )
            : false;
      }
   }

   public boolean findPoweredRailSignal(World world, BlockPos pos, IBlockState state, boolean flag, int n) {
      if (n >= 8) {
         return false;
      } else {
         int x = pos.getX();
         int y = pos.getY();
         int z = pos.getZ();
         boolean flag2 = true;
         BlockRailBase.EnumRailDirection value = (BlockRailBase.EnumRailDirection)state.getValue(SHAPE);
         switch (value) {
            case NORTH_SOUTH:
               if (flag) {
                  z++;
               } else {
                  z--;
               }
               break;
            case EAST_WEST:
               if (flag) {
                  x--;
               } else {
                  x++;
               }
               break;
            case ASCENDING_EAST:
               if (flag) {
                  x--;
               } else {
                  x++;
                  y++;
                  flag2 = false;
               }

               value = BlockRailBase.EnumRailDirection.EAST_WEST;
               break;
            case ASCENDING_WEST:
               if (flag) {
                  x--;
                  y++;
                  flag2 = false;
               } else {
                  x++;
               }

               value = BlockRailBase.EnumRailDirection.EAST_WEST;
               break;
            case ASCENDING_NORTH:
               if (flag) {
                  z++;
               } else {
                  z--;
                  y++;
                  flag2 = false;
               }

               value = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
               break;
            case ASCENDING_SOUTH:
               if (flag) {
                  z++;
                  y++;
                  flag2 = false;
               } else {
                  z--;
               }

               value = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
         }

         return this.isSameRailWithPower(world, new BlockPos(x, y, z), flag, n, value)
            ? true
            : flag2 && this.isSameRailWithPower(world, new BlockPos(x, y - 1, z), flag, n, value);
      }
   }

   public BlockRailPowered() {
      super(true);
      this.setDefaultState(this.blockState.getBaseState().withProperty(SHAPE, BlockRailBase.EnumRailDirection.NORTH_SOUTH).withProperty(POWERED, Boolean.FALSE));
   }

   @Override
   public int getMetaFromState(IBlockState state) {
      int n = 0;
      n |= ((BlockRailBase.EnumRailDirection)state.getValue(SHAPE)).getMetadata();
      if ((Boolean)state.getValue(POWERED)) {
         n |= 8;
      }

      return n;
   }

public static final class FilterPredicate implements Predicate {
      public boolean apply(Object obj) {
         return this.apply((BlockRailBase.EnumRailDirection)obj);
      }

      public boolean apply(BlockRailBase.EnumRailDirection enumRailDirection) {
         return enumRailDirection != BlockRailBase.EnumRailDirection.NORTH_EAST
            && enumRailDirection != BlockRailBase.EnumRailDirection.NORTH_WEST
            && enumRailDirection != BlockRailBase.EnumRailDirection.SOUTH_EAST
            && enumRailDirection != BlockRailBase.EnumRailDirection.SOUTH_WEST;
      }
   }

}
