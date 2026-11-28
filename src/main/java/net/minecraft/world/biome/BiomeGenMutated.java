package net.minecraft.world.biome;

import com.google.common.collect.Lists;
import java.util.Random;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenMutated extends BiomeGenBase {
   public BiomeGenBase baseBiome;

   public BiomeGenMutated(int n, BiomeGenBase biomeGenBase) {
      super(n);
      this.baseBiome = biomeGenBase;
      this.setColor(biomeGenBase.color, true);
      this.biomeName = biomeGenBase.biomeName + " M";
      this.topBlock = biomeGenBase.topBlock;
      this.fillerBlock = biomeGenBase.fillerBlock;
      this.fillerBlockMetadata = biomeGenBase.fillerBlockMetadata;
      this.minHeight = biomeGenBase.minHeight;
      this.maxHeight = biomeGenBase.maxHeight;
      this.temperature = biomeGenBase.temperature;
      this.rainfall = biomeGenBase.rainfall;
      this.waterColorMultiplier = biomeGenBase.waterColorMultiplier;
      this.enableSnow = biomeGenBase.enableSnow;
      this.enableRain = biomeGenBase.enableRain;
      this.spawnableCreatureList = Lists.newArrayList(biomeGenBase.spawnableCreatureList);
      this.spawnableMonsterList = Lists.newArrayList(biomeGenBase.spawnableMonsterList);
      this.spawnableCaveCreatureList = Lists.newArrayList(biomeGenBase.spawnableCaveCreatureList);
      this.spawnableWaterCreatureList = Lists.newArrayList(biomeGenBase.spawnableWaterCreatureList);
      this.temperature = biomeGenBase.temperature;
      this.rainfall = biomeGenBase.rainfall;
      this.minHeight = biomeGenBase.minHeight + 0.1F;
      this.maxHeight = biomeGenBase.maxHeight + 0.2F;
   }

   @Override
   public Class getBiomeClass() {
      return this.baseBiome.getBiomeClass();
   }

   @Override
   public int getGrassColorAtPos(BlockPos pos) {
      return this.baseBiome.getGrassColorAtPos(pos);
   }

   @Override
   public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int n, int n2, double d) {
      this.baseBiome.genTerrainBlocks(world, rand, chunkPrimer, n, n2, d);
   }

   @Override
   public BiomeGenBase.TempCategory getTempCategory() {
      return this.baseBiome.getTempCategory();
   }

   @Override
   public float getSpawningChance() {
      return this.baseBiome.getSpawningChance();
   }

   @Override
   public int getFoliageColorAtPos(BlockPos pos) {
      return this.baseBiome.getFoliageColorAtPos(pos);
   }

   @Override
   public void decorate(World world, Random rand, BlockPos pos) {
      this.baseBiome.theBiomeDecorator.decorate(world, rand, this, pos);
   }

   @Override
   public boolean isEqualTo(BiomeGenBase biomeGenBase) {
      return this.baseBiome.isEqualTo(biomeGenBase);
   }

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random rand) {
      return this.baseBiome.genBigTreeChance(rand);
   }

}
