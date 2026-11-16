package client.util;

import client.setting.MovementColorProperty;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallgrass;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class AuxiliaryIBlockStateEntry extends CurveParser {
   public final IBlockState iBlockState;

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      BlockPos blockPos2 = blockPos;

      Block block;
      while (
         ((block = world.getBlockState(blockPos2).getBlock()).getMaterial() == Material.coreItemEntry || block.getMaterial() == Material.material9) && blockPos2.getY() > 0
      ) {
         blockPos2 = blockPos2.down();
      }

      for (int index = 0; index < 128; index++) {
         BlockPos blockPos3 = blockPos2.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
         if (world.isAirBlock(blockPos3) && Blocks.blockTallgrass.matchesWorld(world, blockPos3, this.iBlockState)) {
            world.matchesBlockPos(blockPos3, this.iBlockState, 2);
         }
      }

      return true;
   }

   public AuxiliaryIBlockStateEntry(MovementColorProperty movementColorProperty) {
      this.iBlockState = Blocks.blockTallgrass.getDefaultState().resolveIBlockState(BlockTallgrass.immutableSetEntry, movementColorProperty);
   }
}
