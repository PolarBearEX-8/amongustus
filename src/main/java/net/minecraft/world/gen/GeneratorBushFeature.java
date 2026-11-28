package net.minecraft.world.gen;

import java.util.Random;
import net.minecraft.block.BlockBush;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class GeneratorBushFeature extends WorldGenerator {
   private BlockBush block;

   public GeneratorBushFeature(BlockBush blockBush) {
      this.block = blockBush;
   }

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      for (int i = 0; i < 64; i++) {
         BlockPos pos2 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (world.isAirBlock(pos2) && (!world.provider.getHasNoSky() || pos2.getY() < 255) && this.block.canBlockStay(world, pos2, this.block.getDefaultState())) {
            world.setBlockState(pos2, this.block.getDefaultState(), 2);
         }
      }

      return true;
   }
}
