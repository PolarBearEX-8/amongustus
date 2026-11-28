package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class WorldGenVines extends WorldGenerator {
   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      while (pos.getY() < 128) {
         if (world.isAirBlock(pos)) {
            for (EnumFacing facing : EnumFacing.Plane.HORIZONTAL.facings()) {
               if (Blocks.vine.canPlaceBlockOnSide(world, pos, facing)) {
                  IBlockState state = Blocks.vine
                     .getDefaultState()
                     .withProperty(BlockVine.NORTH, facing == EnumFacing.NORTH)
                     .withProperty(BlockVine.EAST, facing == EnumFacing.EAST)
                     .withProperty(BlockVine.SOUTH, facing == EnumFacing.SOUTH)
                     .withProperty(BlockVine.WEST, facing == EnumFacing.WEST);
                  world.setBlockState(pos, state, 2);
                  break;
               }
            }
         } else {
            pos = pos.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));
         }

         pos = pos.up();
      }

      return true;
   }
}
