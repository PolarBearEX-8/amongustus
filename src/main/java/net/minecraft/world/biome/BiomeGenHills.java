package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenHills extends BiomeGenBase {
   private WorldGenerator theWorldGenerator = new WorldGenMinable(
      Blocks.monster_egg.getDefaultState().withProperty(BlockSilverfish.VARIANT, BlockSilverfish.EnumType.STONE), 9
   );
   private WorldGenTaiga2 spruceGenerator = new WorldGenTaiga2(false);
   private int typeNormal = 0;
   private int typeExtraTrees = 1;
   private int typeMutated = 2;
   private int hillsType = this.typeNormal;

   public BiomeGenHills(int n, boolean flag) {
      super(n);
      if (flag) {
         this.theBiomeDecorator.treesPerChunk = 3;
         this.hillsType = this.typeExtraTrees;
      }
   }

   @Override
   public BiomeGenBase createMutatedBiome(int n) {
      return new BiomeGenHills(n, false).mutateHills(this);
   }

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random rand) {
      return (WorldGenAbstractTree)(rand.nextInt(3) > 0 ? this.spruceGenerator : super.genBigTreeChance(rand));
   }

   private BiomeGenHills mutateHills(BiomeGenBase biomeGenBase) {
      this.hillsType = this.typeMutated;
      this.setColor(biomeGenBase.color, true);
      this.setBiomeName(biomeGenBase.biomeName + " M");
      this.setHeight(new BiomeGenBase.Height(biomeGenBase.minHeight, biomeGenBase.maxHeight));
      this.setTemperatureRainfall(biomeGenBase.temperature, biomeGenBase.rainfall);
      return this;
   }

   @Override
   public void decorate(World world, Random rand, BlockPos pos) {
      super.decorate(world, rand, pos);
      int n = 3 + rand.nextInt(6);

      for (int i = 0; i < n; i++) {
         BlockPos pos2 = pos.add(rand.nextInt(16), rand.nextInt(28) + 4, rand.nextInt(16));
         if (world.getBlockState(pos2).getBlock() == Blocks.stone) {
            world.setBlockState(pos2, Blocks.emerald_ore.getDefaultState(), 2);
         }
      }

      for (int j = 0; j < 7; j++) {
         int n5 = rand.nextInt(16);
         int n6 = rand.nextInt(64);
         int n7 = rand.nextInt(16);
         this.theWorldGenerator.generate(world, rand, pos.add(n5, n6, n7));
      }
   }

   @Override
   public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int n, int n2, double d) {
      this.topBlock = Blocks.grass.getDefaultState();
      this.fillerBlock = Blocks.dirt.getDefaultState();
      if ((d < -1.0 || d > 2.0) && this.hillsType == this.typeMutated) {
         this.topBlock = Blocks.gravel.getDefaultState();
         this.fillerBlock = Blocks.gravel.getDefaultState();
      } else if (d > 1.0 && this.hillsType != this.typeExtraTrees) {
         this.topBlock = Blocks.stone.getDefaultState();
         this.fillerBlock = Blocks.stone.getDefaultState();
      }

      this.generateBiomeTerrain(world, rand, chunkPrimer, n, n2, d);
   }
}
