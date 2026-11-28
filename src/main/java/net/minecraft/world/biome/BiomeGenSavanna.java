package net.minecraft.world.biome;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.ChunkPrimer;
import java.util.Random;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;

public class BiomeGenSavanna extends BiomeGenBase {
   private static final WorldGenSavannaTree SAVANNA_TREE = new WorldGenSavannaTree(false);

   @Override
   public void decorate(World world, Random rand, BlockPos pos) {
      DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);

      for (int i = 0; i < 7; i++) {
         int n = rand.nextInt(16) + 8;
         int n2 = rand.nextInt(16) + 8;
         int n3 = rand.nextInt(world.getHeight(pos.add(n, 0, n2)).getY() + 32);
         DOUBLE_PLANT_GENERATOR.generate(world, rand, pos.add(n, n3, n2));
      }

      super.decorate(world, rand, pos);
   }

   public BiomeGenSavanna(int n) {
      super(n);
      this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 1, 2, 6));
      this.theBiomeDecorator.treesPerChunk = 1;
      this.theBiomeDecorator.flowersPerChunk = 4;
      this.theBiomeDecorator.grassPerChunk = 20;
   }

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random rand) {
      return (WorldGenAbstractTree)(rand.nextInt(5) > 0 ? SAVANNA_TREE : this.worldGeneratorTrees);
   }

   @Override
   public BiomeGenBase createMutatedBiome(int n) {
      BiomeGenSavanna.Mutated mutated = new BiomeGenSavanna.Mutated(n, this);
      mutated.temperature = (this.temperature + 1.0F) * 0.5F;
      mutated.minHeight = this.minHeight * 0.5F + 0.3F;
      mutated.maxHeight = this.maxHeight * 0.5F + 1.2F;
      return mutated;
   }


   public static class Mutated extends BiomeGenMutated {
      @Override
      public void decorate(World world, Random rand, BlockPos pos) {
         this.theBiomeDecorator.decorate(world, rand, this, pos);
      }

      public Mutated(int n, BiomeGenBase biomeGenBase) {
         super(n, biomeGenBase);
         this.theBiomeDecorator.treesPerChunk = 2;
         this.theBiomeDecorator.flowersPerChunk = 2;
         this.theBiomeDecorator.grassPerChunk = 5;
      }

      @Override
      public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int n, int n2, double d) {
         this.topBlock = Blocks.grass.getDefaultState();
         this.fillerBlock = Blocks.dirt.getDefaultState();
         if (d > 1.75) {
            this.topBlock = Blocks.stone.getDefaultState();
            this.fillerBlock = Blocks.stone.getDefaultState();
         } else if (d > -0.5) {
            this.topBlock = Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
         }

         this.generateBiomeTerrain(world, rand, chunkPrimer, n, n2, d);
      }
   }
}
