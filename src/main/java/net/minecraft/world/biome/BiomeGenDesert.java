package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenDesertWells;

public class BiomeGenDesert extends BiomeGenBase {
   public BiomeGenDesert(int n) {
      super(n);
      this.spawnableCreatureList.clear();
      this.topBlock = Blocks.sand.getDefaultState();
      this.fillerBlock = Blocks.sand.getDefaultState();
      this.theBiomeDecorator.treesPerChunk = -999;
      this.theBiomeDecorator.deadBushPerChunk = 2;
      this.theBiomeDecorator.reedsPerChunk = 50;
      this.theBiomeDecorator.cactiPerChunk = 10;
      this.spawnableCreatureList.clear();
   }

   @Override
   public void decorate(World world, Random rand, BlockPos pos) {
      super.decorate(world, rand, pos);
      if (rand.nextInt(1000) == 0) {
         BlockPos pos2 = world.getHeight(pos.add(rand.nextInt(16) + 8, 0, rand.nextInt(16) + 8)).up();
         new WorldGenDesertWells().generate(world, rand, pos2);
      }
   }
}
