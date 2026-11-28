package net.minecraft.world.gen.feature;

import com.google.common.base.Predicates;
import java.util.Random;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class WorldGenDesertWells extends WorldGenerator {
   private static final BlockStateHelper IS_SAND = BlockStateHelper.forBlock(Blocks.sand).where(BlockSand.VARIANT, Predicates.equalTo(BlockSand.EnumType.SAND));
   private final IBlockState sandSlab = Blocks.stone_slab
      .getDefaultState()
      .withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.SAND)
      .withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM);
   private final IBlockState sandstone = Blocks.sandstone.getDefaultState();
   private final IBlockState water = Blocks.flowing_water.getDefaultState();

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      while (world.isAirBlock(pos) && pos.getY() > 2) {
         pos = pos.down();
      }

      if (!IS_SAND.apply(world.getBlockState(pos))) {
         return false;
      } else {
         for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
               if (world.isAirBlock(pos.add(i, -1, j)) && world.isAirBlock(pos.add(i, -2, j))) {
                  return false;
               }
            }
         }

         for (int k = -1; k <= 0; k++) {
            for (int m = -2; m <= 2; m++) {
               for (int idx = -2; idx <= 2; idx++) {
                  world.setBlockState(pos.add(m, k, idx), this.sandstone, 2);
               }
            }
         }

         world.setBlockState(pos, this.water, 2);

         for (Object obj : EnumFacing.Plane.HORIZONTAL) {
            world.setBlockState(pos.offset((EnumFacing)obj), this.water, 2);
         }

         for (int index = -2; index <= 2; index++) {
            for (int i2 = -2; i2 <= 2; i2++) {
               if (index == -2 || index == 2 || i2 == -2 || i2 == 2) {
                  world.setBlockState(pos.add(index, 1, i2), this.sandstone, 2);
               }
            }
         }

         world.setBlockState(pos.add(2, 1, 0), this.sandSlab, 2);
         world.setBlockState(pos.add(-2, 1, 0), this.sandSlab, 2);
         world.setBlockState(pos.add(0, 1, 2), this.sandSlab, 2);
         world.setBlockState(pos.add(0, 1, -2), this.sandSlab, 2);

         for (int i3 = -1; i3 <= 1; i3++) {
            for (int i4 = -1; i4 <= 1; i4++) {
               if (i3 == 0 && i4 == 0) {
                  world.setBlockState(pos.add(i3, 4, i4), this.sandstone, 2);
               } else {
                  world.setBlockState(pos.add(i3, 4, i4), this.sandSlab, 2);
               }
            }
         }

         for (int i5 = 1; i5 <= 3; i5++) {
            world.setBlockState(pos.add(-1, i5, -1), this.sandstone, 2);
            world.setBlockState(pos.add(-1, i5, 1), this.sandstone, 2);
            world.setBlockState(pos.add(1, i5, -1), this.sandstone, 2);
            world.setBlockState(pos.add(1, i5, 1), this.sandstone, 2);
         }

         return true;
      }
   }
}
