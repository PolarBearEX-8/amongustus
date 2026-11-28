package net.minecraft.world.gen.feature;

import net.minecraft.block.BlockDoublePlant;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenDoublePlant extends WorldGenerator {
   private BlockDoublePlant.EnumPlantType plantType;

   public void setPlantType(BlockDoublePlant.EnumPlantType enumPlantType) {
      this.plantType = enumPlantType;
   }

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      boolean flag = false;

      for (int i = 0; i < 64; i++) {
         BlockPos pos2 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (world.isAirBlock(pos2) && (!world.provider.getHasNoSky() || pos2.getY() < 254) && Blocks.double_plant.canPlaceBlockAt(world, pos2)) {
            Blocks.double_plant.placeAt(world, pos2, this.plantType, 2);
            flag = true;
         }
      }

      return flag;
   }
}
