package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenFlowers extends WorldGenerator {
   private BlockFlower flower;
   private IBlockState state;

   public void setGeneratedBlock(BlockFlower blockFlower, BlockFlower.EnumFlowerType enumFlowerType) {
      this.flower = blockFlower;
      this.state = blockFlower.getDefaultState().withProperty(blockFlower.getTypeProperty(), enumFlowerType);
   }

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      for (int i = 0; i < 64; i++) {
         BlockPos pos2 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (world.isAirBlock(pos2) && (!world.provider.getHasNoSky() || pos2.getY() < 255) && this.flower.canBlockStay(world, pos2, this.state)) {
            world.setBlockState(pos2, this.state, 2);
         }
      }

      return true;
   }

   public WorldGenFlowers(BlockFlower blockFlower, BlockFlower.EnumFlowerType enumFlowerType) {
      this.setGeneratedBlock(blockFlower, enumFlowerType);
   }
}
