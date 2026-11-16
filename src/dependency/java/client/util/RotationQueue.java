package client.util;

import client.setting.MovementFactorProperty;
import java.util.Random;
import net.minecraft.block.BlockVine;
import net.minecraft.block.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class RotationQueue extends CurveParser {
   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      for (BlockPos blockPos2 = blockPos; blockPos2.getY() < 128; blockPos2 = blockPos2.up()) {
         if (world.isAirBlock(blockPos2)) {
            for (EnumFacing enumFacing : MovementFactorProperty.HORIZONTAL.getEnumFacingArray()) {
               if (Blocks.block144.processAction(world, blockPos2, enumFacing)) {
                  IBlockState blockState = Blocks.block144
                     .getDefaultState()
                     .resolveIBlockState(BlockVine.internalImmutableSetEntry, enumFacing == EnumFacing.NORTH)
                     .resolveIBlockState(BlockVine.internalImmutableSetEntry4, enumFacing == EnumFacing.EAST)
                     .resolveIBlockState(BlockVine.internalImmutableSetEntry2, enumFacing == EnumFacing.SOUTH)
                     .resolveIBlockState(BlockVine.internalImmutableSetEntry3, enumFacing == EnumFacing.WEST);
                  world.matchesBlockPos(blockPos2, blockState, 2);
                  break;
               }
            }
         } else {
            blockPos2 = blockPos2.add(random.nextInt(4) - random.nextInt(4), 0, random.nextInt(4) - random.nextInt(4));
         }
      }

      return true;
   }
}
