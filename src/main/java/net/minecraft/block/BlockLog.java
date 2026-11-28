package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class BlockLog extends BlockRotatedPillar {
   public static final PropertyEnum LOG_AXIS;

   public BlockLog() {
      super(Material.wood);
      this.setCreativeTab(CreativeTabs.tabBlock);
      this.setHardness(2.0F);
      this.setStepSound(soundTypeWood);
   }

   static {
      LOG_AXIS = PropertyEnum.create("axis", BlockLog.EnumAxis.class);
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      byte b = 4;
      int n = b + 1;
      if (world.isAreaLoaded(pos.add(-n, -n, -n), pos.add(n, n, n))) {
         for (BlockPos pos2 : (Iterable<BlockPos>)(BlockPos.getAllInBox(pos.add(-b, -b, -b), pos.add(b, b, b)))) {
            IBlockState blockState2 = world.getBlockState(pos2);
            if (blockState2.getBlock().getMaterial() == Material.leaves && !(Boolean)blockState2.getValue(BlockLeaves.CHECK_DECAY)) {
               world.setBlockState(pos2, blockState2.withProperty(BlockLeaves.CHECK_DECAY, Boolean.TRUE), 4);
            }
         }
      }
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3, int n, EntityLivingBase entity) {
      return super.onBlockPlaced(world, pos, facing, f, f2, f3, n, entity).withProperty(LOG_AXIS, BlockLog.EnumAxis.fromFacingAxis(facing.getAxis()));
   }

public enum EnumAxis implements IStringSerializable {
      X("x"),
      Y("y"),
      Z("z"),
      NONE("none");

   private final String name;
   @Override
      public String toString() {
         return this.name;
      }

      @Override
      public String getName() {
         return this.name;
      }

      private EnumAxis(String str) {
         this.name = str;
      }

      public static EnumAxis fromFacingAxis(EnumFacing.Axis axis) {
         switch (axis) {
            case X:
               return X;
            case Y:
               return Y;
            case Z:
               return Z;
            default:
               return NONE;
         }
      }
   }
}
