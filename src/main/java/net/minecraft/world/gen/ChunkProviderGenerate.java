package net.minecraft.world.gen;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureOceanMonument;

public class ChunkProviderGenerate implements IChunkProvider {
   private Random rand;
   private NoiseGeneratorOctaves minLimitPerlinNoise;
   private NoiseGeneratorOctaves maxLimitPerlinNoise;
   private NoiseGeneratorOctaves mainPerlinNoise;
   private NoiseGeneratorPerlin surfaceNoise;
   public NoiseGeneratorOctaves noiseGen5;
   public NoiseGeneratorOctaves noiseGen6;
   public NoiseGeneratorOctaves mobSpawnerNoise;
   private World worldObj;
   private final boolean mapFeaturesEnabled;
   private WorldType terrainType;
   private final double[] heightMap;
   private final float[] parabolicField;
   private ChunkProviderSettings settings;
   private Block oceanBlock = Blocks.water;
   private double[] stoneNoise = new double[256];
   private MapGenBase caveGenerator = new MapGenCaves();
   private MapGenStronghold strongholdGenerator = new MapGenStronghold();
   private MapGenVillage villageGenerator = new MapGenVillage();
   private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
   private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();
   private MapGenBase ravineGenerator = new MapGenRavine();
   private StructureOceanMonument oceanMonumentGenerator = new StructureOceanMonument();
   private BiomeGenBase[] biomesForGeneration;
   public double[] mainNoiseRegion;
   public double[] minLimitRegion;
   public double[] maxLimitRegion;
   public double[] depthRegion;

   @Override
   public boolean populateChunk(IChunkProvider chunkProvider, Chunk chunk, int n, int n2) {
      boolean flag = false;
      if (this.settings.useMonuments && this.mapFeaturesEnabled && chunk.getInhabitedTime() < 3600L) {
         flag |= this.oceanMonumentGenerator.generateStructure(this.worldObj, this.rand, new ChunkCoordIntPair(n, n2));
      }

      return flag;
   }

   @Override
   public void saveExtraData() {
   }

   @Override
   public Chunk provideChunk(int n, int n2) {
      this.rand.setSeed((long)n * 341873128712L + (long)n2 * 132897987541L);
      ChunkPrimer chunkPrimer = new ChunkPrimer();
      this.setBlocksInChunk(n, n2, chunkPrimer);
      this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, n * 16, n2 * 16, 16, 16);
      this.replaceBlocksForBiome(n, n2, chunkPrimer, this.biomesForGeneration);
      if (this.settings.useCaves) {
         this.caveGenerator.generate(this, this.worldObj, n, n2, chunkPrimer);
      }

      if (this.settings.useRavines) {
         this.ravineGenerator.generate(this, this.worldObj, n, n2, chunkPrimer);
      }

      if (this.settings.useMineShafts && this.mapFeaturesEnabled) {
         this.mineshaftGenerator.generate(this, this.worldObj, n, n2, chunkPrimer);
      }

      if (this.settings.useVillages && this.mapFeaturesEnabled) {
         this.villageGenerator.generate(this, this.worldObj, n, n2, chunkPrimer);
      }

      if (this.settings.useStrongholds && this.mapFeaturesEnabled) {
         this.strongholdGenerator.generate(this, this.worldObj, n, n2, chunkPrimer);
      }

      if (this.settings.useTemples && this.mapFeaturesEnabled) {
         this.scatteredFeatureGenerator.generate(this, this.worldObj, n, n2, chunkPrimer);
      }

      if (this.settings.useMonuments && this.mapFeaturesEnabled) {
         this.oceanMonumentGenerator.generate(this, this.worldObj, n, n2, chunkPrimer);
      }

      Chunk chunk = new Chunk(this.worldObj, chunkPrimer, n, n2);
      byte[] biomeArray = chunk.getBiomeArray();

      for (int i = 0; i < biomeArray.length; i++) {
         biomeArray[i] = (byte)this.biomesForGeneration[i].biomeID;
      }

      chunk.generateSkylightMap();
      return chunk;
   }

   @Override
   public boolean chunkExists(int n, int n2) {
      return true;
   }

   public void replaceBlocksForBiome(int n, int n2, ChunkPrimer chunkPrimer, BiomeGenBase[] biomeGenBases) {
      double d = 0.03125;
      this.stoneNoise = this.surfaceNoise.getRegion(this.stoneNoise, (double)(n * 16), (double)(n2 * 16), 16, 16, d * 2.0, d * 2.0, 1.0);

      for (int i = 0; i < 16; i++) {
         for (int j = 0; j < 16; j++) {
            biomeGenBases[j + i * 16].genTerrainBlocks(this.worldObj, this.rand, chunkPrimer, n * 16 + i, n2 * 16 + j, this.stoneNoise[j + i * 16]);
         }
      }
   }

   @Override
   public void populate(IChunkProvider chunkProvider, int n, int n2) {
      BlockFalling.fallInstantly = true;
      int n3 = n * 16;
      int n4 = n2 * 16;
      BlockPos pos = new BlockPos(n3, 0, n4);
      BiomeGenBase biomeGenForCoords = this.worldObj.getBiomeGenForCoords(pos.add(16, 0, 16));
      this.rand.setSeed(this.worldObj.getSeed());
      long l = this.rand.nextLong() / 2L * 2L + 1L;
      long l2 = this.rand.nextLong() / 2L * 2L + 1L;
      this.rand.setSeed((long)n * l + (long)n2 * l2 ^ this.worldObj.getSeed());
      boolean flag = false;
      ChunkCoordIntPair chunkCoordIntPair = new ChunkCoordIntPair(n, n2);
      if (this.settings.useMineShafts && this.mapFeaturesEnabled) {
         this.mineshaftGenerator.generateStructure(this.worldObj, this.rand, chunkCoordIntPair);
      }

      if (this.settings.useVillages && this.mapFeaturesEnabled) {
         flag = this.villageGenerator.generateStructure(this.worldObj, this.rand, chunkCoordIntPair);
      }

      if (this.settings.useStrongholds && this.mapFeaturesEnabled) {
         this.strongholdGenerator.generateStructure(this.worldObj, this.rand, chunkCoordIntPair);
      }

      if (this.settings.useTemples && this.mapFeaturesEnabled) {
         this.scatteredFeatureGenerator.generateStructure(this.worldObj, this.rand, chunkCoordIntPair);
      }

      if (this.settings.useMonuments && this.mapFeaturesEnabled) {
         this.oceanMonumentGenerator.generateStructure(this.worldObj, this.rand, chunkCoordIntPair);
      }

      if (biomeGenForCoords != BiomeGenBase.desert
         && biomeGenForCoords != BiomeGenBase.desertHills
         && this.settings.useWaterLakes
         && !flag
         && this.rand.nextInt(this.settings.waterLakeChance) == 0) {
         int n5 = this.rand.nextInt(16) + 8;
         int n6 = this.rand.nextInt(256);
         int n7 = this.rand.nextInt(16) + 8;
         new WorldGenLakes(Blocks.water).generate(this.worldObj, this.rand, pos.add(n5, n6, n7));
      }

      if (!flag && this.rand.nextInt(this.settings.lavaLakeChance / 10) == 0 && this.settings.useLavaLakes) {
         int n8 = this.rand.nextInt(16) + 8;
         int n9 = this.rand.nextInt(this.rand.nextInt(248) + 8);
         int n10 = this.rand.nextInt(16) + 8;
         if (n9 < this.worldObj.getSeaLevel() || this.rand.nextInt(this.settings.lavaLakeChance / 8) == 0) {
            new WorldGenLakes(Blocks.lava).generate(this.worldObj, this.rand, pos.add(n8, n9, n10));
         }
      }

      if (this.settings.useDungeons) {
         for (int i = 0; i < this.settings.dungeonChance; i++) {
            int n11 = this.rand.nextInt(16) + 8;
            int n12 = this.rand.nextInt(256);
            int n13 = this.rand.nextInt(16) + 8;
            new WorldGenDungeons().generate(this.worldObj, this.rand, pos.add(n11, n12, n13));
         }
      }

      biomeGenForCoords.decorate(this.worldObj, this.rand, new BlockPos(n3, 0, n4));
      SpawnerAnimals.performWorldGenSpawning(this.worldObj, biomeGenForCoords, n3 + 8, n4 + 8, 16, 16, this.rand);
      pos = pos.add(8, 0, 8);

      for (int j = 0; j < 16; j++) {
         for (int k = 0; k < 16; k++) {
            BlockPos precipitationHeight = this.worldObj.getPrecipitationHeight(pos.add(j, 0, k));
            BlockPos pos2 = precipitationHeight.down();
            if (this.worldObj.canBlockFreezeWater(pos2)) {
               this.worldObj.setBlockState(pos2, Blocks.ice.getDefaultState(), 2);
            }

            if (this.worldObj.canSnowAt(precipitationHeight, true)) {
               this.worldObj.setBlockState(precipitationHeight, Blocks.snow_layer.getDefaultState(), 2);
            }
         }
      }

      BlockFalling.fallInstantly = false;
   }

   @Override
   public List getPossibleCreatures(EnumCreatureType enumCreatureType, BlockPos pos) {
      BiomeGenBase biomeGenForCoords = this.worldObj.getBiomeGenForCoords(pos);
      if (this.mapFeaturesEnabled) {
         if (enumCreatureType == EnumCreatureType.MONSTER && this.scatteredFeatureGenerator.isSwampHut(pos)) {
            return this.scatteredFeatureGenerator.getScatteredFeatureSpawnList();
         }

         if (enumCreatureType == EnumCreatureType.MONSTER && this.settings.useMonuments && this.oceanMonumentGenerator.isPositionInStructure(this.worldObj, pos)) {
            return this.oceanMonumentGenerator.getMonumentEnemies();
         }
      }

      return biomeGenForCoords.getSpawnableList(enumCreatureType);
   }

   @Override
   public BlockPos getStrongholdGen(World world, String str, BlockPos pos) {
      return "Stronghold".equals(str) && this.strongholdGenerator != null ? this.strongholdGenerator.getClosestStrongholdPos(world, pos) : null;
   }

   private void generateHeightmap(int n, int n2, int n3) {
      this.depthRegion = this.noiseGen6
         .generateNoiseOctaves(
            this.depthRegion,
            n,
            n3,
            5,
            5,
            (double)this.settings.depthNoiseScaleX,
            (double)this.settings.depthNoiseScaleZ,
            (double)this.settings.depthNoiseScaleExponent
         );
      float coordinateScale = this.settings.coordinateScale;
      float heightScale = this.settings.heightScale;
      this.mainNoiseRegion = this.mainPerlinNoise
         .generateNoiseOctaves(
            this.mainNoiseRegion,
            n,
            n2,
            n3,
            5,
            33,
            5,
            (double)(coordinateScale / this.settings.mainNoiseScaleX),
            (double)(heightScale / this.settings.mainNoiseScaleY),
            (double)(coordinateScale / this.settings.mainNoiseScaleZ)
         );
      this.minLimitRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minLimitRegion, n, n2, n3, 5, 33, 5, (double)coordinateScale, (double)heightScale, (double)coordinateScale);
      this.maxLimitRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxLimitRegion, n, n2, n3, 5, 33, 5, (double)coordinateScale, (double)heightScale, (double)coordinateScale);
      boolean flag = false;
      boolean flag2 = false;
      int n4 = 0;
      int n5 = 0;

      for (int i = 0; i < 5; i++) {
         for (int j = 0; j < 5; j++) {
            float f = 0.0F;
            float f2 = 0.0F;
            float f3 = 0.0F;
            byte b = 2;
            BiomeGenBase biomeGenBase = this.biomesForGeneration[i + 2 + (j + 2) * 10];

            for (int k = -b; k <= b; k++) {
               for (int m = -b; m <= b; m++) {
                  BiomeGenBase biomeGenBase2 = this.biomesForGeneration[i + k + 2 + (j + m + 2) * 10];
                  float f4 = this.settings.biomeDepthOffSet + biomeGenBase2.minHeight * this.settings.biomeDepthWeight;
                  float f5 = this.settings.biomeScaleOffset + biomeGenBase2.maxHeight * this.settings.biomeScaleWeight;
                  if (this.terrainType == WorldType.AMPLIFIED && f4 > 0.0F) {
                     f4 = 1.0F + f4 * 2.0F;
                     f5 = 1.0F + f5 * 4.0F;
                  }

                  float f6 = this.parabolicField[k + 2 + (m + 2) * 5] / (f4 + 2.0F);
                  if (biomeGenBase2.minHeight > biomeGenBase.minHeight) {
                     f6 /= 2.0F;
                  }

                  f += f5 * f6;
                  f2 += f4 * f6;
                  f3 += f6;
               }
            }

            f /= f3;
            f2 /= f3;
            f = f * 0.9F + 0.1F;
            f2 = (f2 * 4.0F - 1.0F) / 8.0F;
            double d = this.depthRegion[n5] / 8000.0;
            if (d < 0.0) {
               d = -d * 0.3;
            }

            d = d * 3.0 - 2.0;
            if (d < 0.0) {
               d /= 2.0;
               if (d < -1.0) {
                  d = -1.0;
               }

               d /= 1.4;
               d /= 2.0;
            } else {
               if (d > 1.0) {
                  d = 1.0;
               }

               d /= 8.0;
            }

            n5++;
            double d2 = (double)f2;
            double d3 = (double)f;
            d2 += d * 0.2;
            d2 = d2 * (double)this.settings.baseSize / 8.0;
            double d4 = (double)this.settings.baseSize + d2 * 4.0;

            for (int idx = 0; idx < 33; idx++) {
               double d5 = ((double)idx - d4) * (double)this.settings.stretchY * 128.0 / 256.0 / d3;
               if (d5 < 0.0) {
                  d5 *= 4.0;
               }

               double d9 = MathHelper.denormalizeClamp(this.minLimitRegion[n4] / (double)this.settings.lowerLimitScale, this.maxLimitRegion[n4] / (double)this.settings.upperLimitScale, (this.mainNoiseRegion[n4] / 10.0 + 1.0) / 2.0) - d5;
               if (idx > 29) {
                  double d10 = (double)((float)(idx - 29) / 3.0F);
                  d9 = d9 * (1.0 - d10) + -10.0 * d10;
               }

               this.heightMap[n4] = d9;
               n4++;
            }
         }
      }
   }

   @Override
   public Chunk provideChunk(BlockPos pos) {
      return this.provideChunk(pos.getX() >> 4, pos.getZ() >> 4);
   }

   public void setBlocksInChunk(int n, int n2, ChunkPrimer chunkPrimer) {
      this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, n * 4 - 2, n2 * 4 - 2, 10, 10);
      this.generateHeightmap(n * 4, 0, n2 * 4);

      for (int i = 0; i < 4; i++) {
         int n3 = i * 5;
         int n4 = (i + 1) * 5;

         for (int j = 0; j < 4; j++) {
            int n5 = (n3 + j) * 33;
            int n6 = (n3 + j + 1) * 33;
            int n7 = (n4 + j) * 33;
            int n8 = (n4 + j + 1) * 33;

            for (int k = 0; k < 32; k++) {
               double d = 0.125;
               double d2 = this.heightMap[n5 + k];
               double d3 = this.heightMap[n6 + k];
               double d4 = this.heightMap[n7 + k];
               double d5 = this.heightMap[n8 + k];
               double d6 = (this.heightMap[n5 + k + 1] - d2) * d;
               double d7 = (this.heightMap[n6 + k + 1] - d3) * d;
               double d8 = (this.heightMap[n7 + k + 1] - d4) * d;
               double d9 = (this.heightMap[n8 + k + 1] - d5) * d;

               for (int m = 0; m < 8; m++) {
                  double d10 = 0.25;
                  double d11 = d2;
                  double d12 = d3;
                  double d13 = (d4 - d2) * d10;
                  double d14 = (d5 - d3) * d10;

                  for (int idx = 0; idx < 4; idx++) {
                     double d15 = 0.25;
                     double d16 = (d12 - d11) * d15;
                     double d17 = d11 - d16;

                     for (int index = 0; index < 4; index++) {
                        if ((d17 += d16) > 0.0) {
                           chunkPrimer.setBlockState(i * 4 + idx, k * 8 + m, j * 4 + index, Blocks.stone.getDefaultState());
                        } else if (k * 8 + m < this.settings.seaLevel) {
                           chunkPrimer.setBlockState(i * 4 + idx, k * 8 + m, j * 4 + index, this.oceanBlock.getDefaultState());
                        }
                     }

                     d11 += d13;
                     d12 += d14;
                  }

                  d2 += d6;
                  d3 += d7;
                  d4 += d8;
                  d5 += d9;
               }
            }
         }
      }
   }

   @Override
   public void recreateStructures(Chunk chunk, int n, int n2) {
      if (this.settings.useMineShafts && this.mapFeaturesEnabled) {
         this.mineshaftGenerator.generate(this, this.worldObj, n, n2, (ChunkPrimer)null);
      }

      if (this.settings.useVillages && this.mapFeaturesEnabled) {
         this.villageGenerator.generate(this, this.worldObj, n, n2, (ChunkPrimer)null);
      }

      if (this.settings.useStrongholds && this.mapFeaturesEnabled) {
         this.strongholdGenerator.generate(this, this.worldObj, n, n2, (ChunkPrimer)null);
      }

      if (this.settings.useTemples && this.mapFeaturesEnabled) {
         this.scatteredFeatureGenerator.generate(this, this.worldObj, n, n2, (ChunkPrimer)null);
      }

      if (this.settings.useMonuments && this.mapFeaturesEnabled) {
         this.oceanMonumentGenerator.generate(this, this.worldObj, n, n2, (ChunkPrimer)null);
      }
   }

   @Override
   public int getLoadedChunkCount() {
      return 0;
   }

   @Override
   public boolean unloadQueuedChunks() {
      return false;
   }

   public ChunkProviderGenerate(World world, long l, boolean flag, String str) {
      this.worldObj = world;
      this.mapFeaturesEnabled = flag;
      this.terrainType = world.getWorldInfo().getTerrainType();
      this.rand = new Random(l);
      this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
      this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
      this.mainPerlinNoise = new NoiseGeneratorOctaves(this.rand, 8);
      this.surfaceNoise = new NoiseGeneratorPerlin(this.rand, 4);
      this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
      this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
      this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
      this.heightMap = new double[825];
      this.parabolicField = new float[25];

      for (int i = -2; i <= 2; i++) {
         for (int j = -2; j <= 2; j++) {
            float f = 10.0F / MathHelper.sqrt_float((float)(i * i + j * j) + 0.2F);
            this.parabolicField[i + 2 + (j + 2) * 5] = f;
         }
      }

      if (str != null) {
         this.settings = ChunkProviderSettings.Factory.jsonToFactory(str).build();
         this.oceanBlock = this.settings.useLavaOceans ? Blocks.lava : Blocks.water;
         world.setSeaLevel(this.settings.seaLevel);
      }
   }

   @Override
   public boolean canSave() {
      return true;
   }

   @Override
   public String makeString() {
      return "RandomLevelSource";
   }

   @Override
   public boolean saveChunks(boolean flag, IProgressUpdate progressUpdate) {
      return true;
   }
}
