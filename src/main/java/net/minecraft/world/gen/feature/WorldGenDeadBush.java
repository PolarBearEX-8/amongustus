package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenDeadBush extends WorldGenerator {
   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      Block block;
      while (((block = world.getBlockState(pos).getBlock()).getMaterial() == Material.air || block.getMaterial() == Material.leaves) && pos.getY() > 0) {
         pos = pos.down();
      }

      for (int i = 0; i < 4; i++) {
         BlockPos pos2 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (world.isAirBlock(pos2) && Blocks.deadbush.canBlockStay(world, pos2, Blocks.deadbush.getDefaultState())) {
            world.setBlockState(pos2, Blocks.deadbush.getDefaultState(), 2);
         }
      }

      return true;
   }
}
