package net.minecraft.world.gen;

import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenFire;
import net.minecraft.world.gen.feature.WorldGenGlowStone1;
import net.minecraft.world.gen.feature.WorldGenGlowStone2;
import net.minecraft.world.gen.feature.WorldGenHellLava;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenNetherBridge;

public class ChunkProviderHell implements IChunkProvider {
   private final World worldObj;
   private final boolean generateStructures;
   private final Random hellRNG;
   private double[] slowsandNoise = new double[256];
   private double[] gravelNoise = new double[256];
   private double[] netherrackExclusivityNoise = new double[256];
   private double[] noiseField;
   private final NoiseGeneratorOctaves netherNoiseGen1;
   private final NoiseGeneratorOctaves netherNoiseGen2;
   private final NoiseGeneratorOctaves netherNoiseGen3;
   private final NoiseGeneratorOctaves slowsandGravelNoiseGen;
   private final NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
   public final NoiseGeneratorOctaves netherNoiseGen6;
   public final NoiseGeneratorOctaves netherNoiseGen7;
   private final WorldGenFire fireFeature = new WorldGenFire();
   private final WorldGenGlowStone1 lightGemGen = new WorldGenGlowStone1();
   private final WorldGenGlowStone2 hellPortalGen = new WorldGenGlowStone2();
   private final WorldGenerator quartzGen = new WorldGenMinable(Blocks.quartz_ore.getDefaultState(), 14, BlockHelper.forBlock(Blocks.netherrack));
   private final WorldGenHellLava lavaTrapGen = new WorldGenHellLava(Blocks.flowing_lava, true);
   private final WorldGenHellLava hellSpringGen = new WorldGenHellLava(Blocks.flowing_lava, false);
   private final GeneratorBushFeature brownMushroomFeature = new GeneratorBushFeature(Blocks.brown_mushroom);
   private final GeneratorBushFeature redMushroomFeature = new GeneratorBushFeature(Blocks.red_mushroom);
   private final MapGenNetherBridge genNetherBridge = new MapGenNetherBridge();
   private final MapGenBase netherCaveGenerator = new MapGenCavesHell();
   public double[] noiseData1;
   public double[] noiseData2;
   public double[] noiseData3;
   public double[] noiseData4;
   public double[] noiseData5;

   public void buildSurfaces(int n, int n2, ChunkPrimer chunkPrimer) {
      int n3 = this.worldObj.getSeaLevel() + 1;
      double d = 0.03125;
      this.slowsandNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.slowsandNoise, n * 16, n2 * 16, 0, 16, 16, 1, d, d, 1.0);
      this.gravelNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.gravelNoise, n * 16, 109, n2 * 16, 16, 1, 16, d, 1.0, d);
      this.netherrackExclusivityNoise = this.netherrackExculsivityNoiseGen
         .generateNoiseOctaves(this.netherrackExclusivityNoise, n * 16, n2 * 16, 0, 16, 16, 1, d * 2.0, d * 2.0, d * 2.0);

      for (int i = 0; i < 16; i++) {
         for (int j = 0; j < 16; j++) {
            boolean flag = this.slowsandNoise[i + j * 16] + this.hellRNG.nextDouble() * 0.2 > 0.0;
            boolean flag2 = this.gravelNoise[i + j * 16] + this.hellRNG.nextDouble() * 0.2 > 0.0;
            int n4 = (int)(this.netherrackExclusivityNoise[i + j * 16] / 3.0 + 3.0 + this.hellRNG.nextDouble() * 0.25);
            int n5 = -1;
            IBlockState defaultState = Blocks.netherrack.getDefaultState();
            IBlockState defaultState2 = Blocks.netherrack.getDefaultState();

            for (int k = 127; k >= 0; k--) {
               if (k < 127 - this.hellRNG.nextInt(5) && k > this.hellRNG.nextInt(5)) {
                  IBlockState blockState = chunkPrimer.getBlockState(j, k, i);
                  if (blockState.getBlock() == null || blockState.getBlock().getMaterial() == Material.air) {
                     n5 = -1;
                  } else if (blockState.getBlock() == Blocks.netherrack) {
                     if (n5 == -1) {
                        if (n4 <= 0) {
                           defaultState = null;
                           defaultState2 = Blocks.netherrack.getDefaultState();
                        } else if (k >= n3 - 4 && k <= n3 + 1) {
                           defaultState = Blocks.netherrack.getDefaultState();
                           defaultState2 = Blocks.netherrack.getDefaultState();
                           if (flag2) {
                              defaultState = Blocks.gravel.getDefaultState();
                              defaultState2 = Blocks.netherrack.getDefaultState();
                           }

                           if (flag) {
                              defaultState = Blocks.soul_sand.getDefaultState();
                              defaultState2 = Blocks.soul_sand.getDefaultState();
                           }
                        }

                        if (k < n3 && (defaultState == null || defaultState.getBlock().getMaterial() == Material.air)) {
                           defaultState = Blocks.lava.getDefaultState();
                        }

                        n5 = n4;
                        if (k >= n3 - 1) {
                           chunkPrimer.setBlockState(j, k, i, defaultState);
                        } else {
                           chunkPrimer.setBlockState(j, k, i, defaultState2);
                        }
                     } else if (n5 > 0) {
                        n5--;
                        chunkPrimer.setBlockState(j, k, i, defaultState2);
                     }
                  }
               } else {
                  chunkPrimer.setBlockState(j, k, i, Blocks.bedrock.getDefaultState());
               }
            }
         }
      }
   }

   @Override
   public boolean canSave() {
      return true;
   }

   @Override
   public void populate(IChunkProvider chunkProvider, int n, int n2) {
      BlockFalling.fallInstantly = true;
      BlockPos pos = new BlockPos(n * 16, 0, n2 * 16);
      ChunkCoordIntPair chunkCoordIntPair = new ChunkCoordIntPair(n, n2);
      this.genNetherBridge.generateStructure(this.worldObj, this.hellRNG, chunkCoordIntPair);

      for (int i = 0; i < 8; i++) {
         this.hellSpringGen.generate(this.worldObj, this.hellRNG, pos.add(this.hellRNG.nextInt(16) + 8, this.hellRNG.nextInt(120) + 4, this.hellRNG.nextInt(16) + 8));
      }

      for (int j = 0; j < this.hellRNG.nextInt(this.hellRNG.nextInt(10) + 1) + 1; j++) {
         this.fireFeature.generate(this.worldObj, this.hellRNG, pos.add(this.hellRNG.nextInt(16) + 8, this.hellRNG.nextInt(120) + 4, this.hellRNG.nextInt(16) + 8));
      }

      for (int k = 0; k < this.hellRNG.nextInt(this.hellRNG.nextInt(10) + 1); k++) {
         this.lightGemGen.generate(this.worldObj, this.hellRNG, pos.add(this.hellRNG.nextInt(16) + 8, this.hellRNG.nextInt(120) + 4, this.hellRNG.nextInt(16) + 8));
      }

      for (int m = 0; m < 10; m++) {
         this.hellPortalGen.generate(this.worldObj, this.hellRNG, pos.add(this.hellRNG.nextInt(16) + 8, this.hellRNG.nextInt(128), this.hellRNG.nextInt(16) + 8));
      }

      if (this.hellRNG.nextBoolean()) {
         this.brownMushroomFeature.generate(this.worldObj, this.hellRNG, pos.add(this.hellRNG.nextInt(16) + 8, this.hellRNG.nextInt(128), this.hellRNG.nextInt(16) + 8));
      }

      if (this.hellRNG.nextBoolean()) {
         this.redMushroomFeature.generate(this.worldObj, this.hellRNG, pos.add(this.hellRNG.nextInt(16) + 8, this.hellRNG.nextInt(128), this.hellRNG.nextInt(16) + 8));
      }

      for (int idx = 0; idx < 16; idx++) {
         this.quartzGen.generate(this.worldObj, this.hellRNG, pos.add(this.hellRNG.nextInt(16), this.hellRNG.nextInt(108) + 10, this.hellRNG.nextInt(16)));
      }

      for (int index = 0; index < 16; index++) {
         this.lavaTrapGen.generate(this.worldObj, this.hellRNG, pos.add(this.hellRNG.nextInt(16), this.hellRNG.nextInt(108) + 10, this.hellRNG.nextInt(16)));
      }

      BlockFalling.fallInstantly = false;
   }

   @Override
   public boolean populateChunk(IChunkProvider chunkProvider, Chunk chunk, int n, int n2) {
      return false;
   }

   @Override
   public boolean unloadQueuedChunks() {
      return false;
   }

   @Override
   public String makeString() {
      return "HellRandomLevelSource";
   }

   @Override
   public void saveExtraData() {
   }

   private double[] initializeNoiseField(double[] doubles, int n, int n2, int n3, int n4, int n5, int n6) {
      if (doubles == null) {
         doubles = new double[n4 * n5 * n6];
      }

      double d = 684.412;
      double d2 = 2053.236;
      this.noiseData4 = this.netherNoiseGen6.generateNoiseOctaves(this.noiseData4, n, n2, n3, n4, 1, n6, 1.0, 0.0, 1.0);
      this.noiseData5 = this.netherNoiseGen7.generateNoiseOctaves(this.noiseData5, n, n2, n3, n4, 1, n6, 100.0, 0.0, 100.0);
      this.noiseData1 = this.netherNoiseGen3.generateNoiseOctaves(this.noiseData1, n, n2, n3, n4, n5, n6, d / 80.0, d2 / 60.0, d / 80.0);
      this.noiseData2 = this.netherNoiseGen1.generateNoiseOctaves(this.noiseData2, n, n2, n3, n4, n5, n6, d, d2, d);
      this.noiseData3 = this.netherNoiseGen2.generateNoiseOctaves(this.noiseData3, n, n2, n3, n4, n5, n6, d, d2, d);
      int n7 = 0;
      double[] doubles2 = new double[n5];

      for (int i = 0; i < n5; i++) {
         doubles2[i] = Math.cos((double)i * Math.PI * 6.0 / (double)n5) * 2.0;
         double d3 = (double)i;
         if (i > n5 / 2) {
            d3 = (double)(n5 - 1 - i);
         }

         if (d3 < 4.0) {
            d3 = 4.0 - d3;
            doubles2[i] -= d3 * d3 * d3 * 10.0;
         }
      }

      for (int j = 0; j < n4; j++) {
         for (int k = 0; k < n6; k++) {
            double d4 = 0.0;

            for (int m = 0; m < n5; m++) {
               double d5 = 0.0;
               double d6 = doubles2[m];
               double d7 = this.noiseData2[n7] / 512.0;
               double d8 = this.noiseData3[n7] / 512.0;
               double d9 = (this.noiseData1[n7] / 10.0 + 1.0) / 2.0;
               if (d9 < 0.0) {
                  d5 = d7;
               } else if (d9 > 1.0) {
                  d5 = d8;
               } else {
                  d5 = d7 + (d8 - d7) * d9;
               }

               d5 -= d6;
               if (m > n5 - 4) {
                  double d10 = (double)((float)(m - (n5 - 4)) / 3.0F);
                  d5 = d5 * (1.0 - d10) + -10.0 * d10;
               }

               if ((double)m < d4) {
                  double d11 = (d4 - (double)m) / 4.0;
                  d11 = MathHelper.clamp_double(d11, 0.0, 1.0);
                  d5 = d5 * (1.0 - d11) + -10.0 * d11;
               }

               doubles[n7] = d5;
               n7++;
            }
         }
      }

      return doubles;
   }

   @Override
   public int getLoadedChunkCount() {
      return 0;
   }

   @Override
   public List getPossibleCreatures(EnumCreatureType enumCreatureType, BlockPos pos) {
      if (enumCreatureType == EnumCreatureType.MONSTER) {
         if (this.genNetherBridge.isInsideStructure(pos)) {
            return this.genNetherBridge.getSpawnList();
         }

         if (this.genNetherBridge.isPositionInStructure(this.worldObj, pos) && this.worldObj.getBlockState(pos.down()).getBlock() == Blocks.nether_brick) {
            return this.genNetherBridge.getSpawnList();
         }
      }

      return this.worldObj.getBiomeGenForCoords(pos).getSpawnableList(enumCreatureType);
   }

   @Override
   public Chunk provideChunk(int n, int n2) {
      this.hellRNG.setSeed((long)n * 341873128712L + (long)n2 * 132897987541L);
      ChunkPrimer chunkPrimer = new ChunkPrimer();
      this.prepareHeights(n, n2, chunkPrimer);
      this.buildSurfaces(n, n2, chunkPrimer);
      this.netherCaveGenerator.generate(this, this.worldObj, n, n2, chunkPrimer);
      if (this.generateStructures) {
         this.genNetherBridge.generate(this, this.worldObj, n, n2, chunkPrimer);
      }

      Chunk chunk = new Chunk(this.worldObj, chunkPrimer, n, n2);
      BiomeGenBase[] biomeGenBases = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, n * 16, n2 * 16, 16, 16);
      byte[] biomeArray = chunk.getBiomeArray();

      for (int i = 0; i < biomeArray.length; i++) {
         biomeArray[i] = (byte)biomeGenBases[i].biomeID;
      }

      chunk.resetRelightChecks();
      return chunk;
   }

   @Override
   public void recreateStructures(Chunk chunk, int n, int n2) {
      this.genNetherBridge.generate(this, this.worldObj, n, n2, (ChunkPrimer)null);
   }

   @Override
   public boolean chunkExists(int n, int n2) {
      return true;
   }

   @Override
   public Chunk provideChunk(BlockPos pos) {
      return this.provideChunk(pos.getX() >> 4, pos.getZ() >> 4);
   }

   @Override
   public BlockPos getStrongholdGen(World world, String str, BlockPos pos) {
      return null;
   }

   public ChunkProviderHell(World world, boolean flag, long l) {
      this.worldObj = world;
      this.generateStructures = flag;
      this.hellRNG = new Random(l);
      this.netherNoiseGen1 = new NoiseGeneratorOctaves(this.hellRNG, 16);
      this.netherNoiseGen2 = new NoiseGeneratorOctaves(this.hellRNG, 16);
      this.netherNoiseGen3 = new NoiseGeneratorOctaves(this.hellRNG, 8);
      this.slowsandGravelNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
      this.netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
      this.netherNoiseGen6 = new NoiseGeneratorOctaves(this.hellRNG, 10);
      this.netherNoiseGen7 = new NoiseGeneratorOctaves(this.hellRNG, 16);
      world.setSeaLevel(63);
   }

   @Override
   public boolean saveChunks(boolean flag, IProgressUpdate progressUpdate) {
      return true;
   }

   public void prepareHeights(int n, int n2, ChunkPrimer chunkPrimer) {
      byte b = 4;
      int n3 = this.worldObj.getSeaLevel() / 2 + 1;
      int n4 = b + 1;
      byte b2 = 17;
      int n5 = b + 1;
      this.noiseField = this.initializeNoiseField(this.noiseField, n * b, 0, n2 * b, n4, b2, n5);

      for (int i = 0; i < b; i++) {
         for (int j = 0; j < b; j++) {
            for (int k = 0; k < 16; k++) {
               double d = 0.125;
               double d2 = this.noiseField[((i + 0) * n5 + j + 0) * b2 + k + 0];
               double d3 = this.noiseField[((i + 0) * n5 + j + 1) * b2 + k + 0];
               double d4 = this.noiseField[((i + 1) * n5 + j + 0) * b2 + k + 0];
               double d5 = this.noiseField[((i + 1) * n5 + j + 1) * b2 + k + 0];
               double d6 = (this.noiseField[((i + 0) * n5 + j + 0) * b2 + k + 1] - d2) * d;
               double d7 = (this.noiseField[((i + 0) * n5 + j + 1) * b2 + k + 1] - d3) * d;
               double d8 = (this.noiseField[((i + 1) * n5 + j + 0) * b2 + k + 1] - d4) * d;
               double d9 = (this.noiseField[((i + 1) * n5 + j + 1) * b2 + k + 1] - d5) * d;

               for (int m = 0; m < 8; m++) {
                  double d10 = 0.25;
                  double d11 = d2;
                  double d12 = d3;
                  double d13 = (d4 - d2) * d10;
                  double d14 = (d5 - d3) * d10;

                  for (int idx = 0; idx < 4; idx++) {
                     double d15 = 0.25;
                     double d16 = d11;
                     double d17 = (d12 - d11) * d15;

                     for (int index = 0; index < 4; index++) {
                        IBlockState state = null;
                        if (k * 8 + m < n3) {
                           state = Blocks.lava.getDefaultState();
                        }

                        if (d16 > 0.0) {
                           state = Blocks.netherrack.getDefaultState();
                        }

                        chunkPrimer.setBlockState(idx + i * 4, m + k * 8, index + j * 4, state);
                        d16 += d17;
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
}
