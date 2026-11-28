package net.minecraft.world.biome;

import java.util.Arrays;
import java.util.Random;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenMesa extends BiomeGenBase {
   private IBlockState[] clayBands;
   private long worldSeed;
   private NoiseGeneratorPerlin pillarNoise;
   private NoiseGeneratorPerlin pillarRoofNoise;
   private NoiseGeneratorPerlin clayBandsOffsetNoise;
   private boolean brycePillars;
   private boolean hasForest;

   private void generateBands(long l) {
      this.clayBands = new IBlockState[64];
      Arrays.fill(this.clayBands, Blocks.hardened_clay.getDefaultState());
      Random rand = new Random(l);
      this.clayBandsOffsetNoise = new NoiseGeneratorPerlin(rand, 1);

      for (int i = 0; i < 64; i++) {
         i += rand.nextInt(5) + 1;
         if (i < 64) {
            this.clayBands[i] = Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.ORANGE);
         }
      }

      int n = rand.nextInt(4) + 2;

      for (int j = 0; j < n; j++) {
         int n2 = rand.nextInt(3) + 1;
         int n3 = rand.nextInt(64);

         for (int k = 0; n3 + k < 64 && k < n2; k++) {
            this.clayBands[n3 + k] = Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
         }
      }

      int n4 = rand.nextInt(4) + 2;

      for (int m = 0; m < n4; m++) {
         int n5 = rand.nextInt(3) + 2;
         int n6 = rand.nextInt(64);

         for (int idx = 0; n6 + idx < 64 && idx < n5; idx++) {
            this.clayBands[n6 + idx] = Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.BROWN);
         }
      }

      int n7 = rand.nextInt(4) + 2;

      for (int index = 0; index < n7; index++) {
         int n8 = rand.nextInt(3) + 1;
         int n9 = rand.nextInt(64);

         for (int i2 = 0; n9 + i2 < 64 && i2 < n8; i2++) {
            this.clayBands[n9 + i2] = Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.RED);
         }
      }

      int n10 = rand.nextInt(3) + 3;
      int n11 = 0;

      for (int i3 = 0; i3 < n10; i3++) {
         byte b = 1;
         n11 += rand.nextInt(16) + 4;

         for (int i4 = 0; n11 + i4 < 64 && i4 < b; i4++) {
            this.clayBands[n11 + i4] = Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.WHITE);
            if (n11 + i4 > 1 && rand.nextBoolean()) {
               this.clayBands[n11 + i4 - 1] = Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.SILVER);
            }

            if (n11 + i4 < 63 && rand.nextBoolean()) {
               this.clayBands[n11 + i4 + 1] = Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.SILVER);
            }
         }
      }
   }

   @Override
   public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int n, int n2, double d) {
      if (this.clayBands == null || this.worldSeed != world.getSeed()) {
         this.generateBands(world.getSeed());
      }

      if (this.pillarNoise == null || this.pillarRoofNoise == null || this.worldSeed != world.getSeed()) {
         Random rand2 = new Random(this.worldSeed);
         this.pillarNoise = new NoiseGeneratorPerlin(rand2, 4);
         this.pillarRoofNoise = new NoiseGeneratorPerlin(rand2, 1);
      }

      this.worldSeed = world.getSeed();
      double d2 = 0.0;
      if (this.brycePillars) {
         int n3 = (n & -16) + (n2 & 15);
         int n4 = (n2 & -16) + (n & 15);
         double d3 = Math.min(Math.abs(d), this.pillarNoise.getValue((double)n3 * 0.25, (double)n4 * 0.25));
         if (d3 > 0.0) {
            double d4 = 0.001953125;
            double d5 = Math.abs(this.pillarRoofNoise.getValue((double)n3 * d4, (double)n4 * d4));
            d2 = d3 * d3 * 2.5;
            double d6 = Math.ceil(d5 * 50.0) + 14.0;
            if (d2 > d6) {
               d2 = d6;
            }

            d2 += 64.0;
         }
      }

      int n5 = n & 15;
      int n6 = n2 & 15;
      int seaLevel = world.getSeaLevel();
      IBlockState defaultState = Blocks.stained_hardened_clay.getDefaultState();
      IBlockState fillerBlock2 = this.fillerBlock;
      int n7 = (int)(d / 3.0 + 3.0 + rand.nextDouble() * 0.25);
      boolean flag = Math.cos(d / 3.0 * Math.PI) > 0.0;
      int n8 = -1;
      boolean flag2 = false;

      for (int i = 255; i >= 0; i--) {
         if (chunkPrimer.getBlockState(n6, i, n5).getBlock().getMaterial() == Material.air && i < (int)d2) {
            chunkPrimer.setBlockState(n6, i, n5, Blocks.stone.getDefaultState());
         }

         if (i <= rand.nextInt(5)) {
            chunkPrimer.setBlockState(n6, i, n5, Blocks.bedrock.getDefaultState());
         } else {
            IBlockState blockState = chunkPrimer.getBlockState(n6, i, n5);
            if (blockState.getBlock().getMaterial() == Material.air) {
               n8 = -1;
            } else if (blockState.getBlock() == Blocks.stone) {
               if (n8 == -1) {
                  flag2 = false;
                  if (n7 <= 0) {
                     defaultState = null;
                     fillerBlock2 = Blocks.stone.getDefaultState();
                  } else if (i >= seaLevel - 4 && i <= seaLevel + 1) {
                     defaultState = Blocks.stained_hardened_clay.getDefaultState();
                     fillerBlock2 = this.fillerBlock;
                  }

                  if (i < seaLevel && (defaultState == null || defaultState.getBlock().getMaterial() == Material.air)) {
                     defaultState = Blocks.water.getDefaultState();
                  }

                  n8 = n7 + Math.max(0, i - seaLevel);
                  if (i < seaLevel - 1) {
                     chunkPrimer.setBlockState(n6, i, n5, fillerBlock2);
                     if (fillerBlock2.getBlock() == Blocks.stained_hardened_clay) {
                        chunkPrimer.setBlockState(n6, i, n5, fillerBlock2.getBlock().getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.ORANGE));
                     }
                  } else if (!this.hasForest || i <= 86 + n7 * 2) {
                     if (i <= seaLevel + 3 + n7) {
                        chunkPrimer.setBlockState(n6, i, n5, this.topBlock);
                        flag2 = true;
                     } else {
                        IBlockState state;
                        if (i < 64 || i > 127) {
                           state = Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.ORANGE);
                        } else if (flag) {
                           state = Blocks.hardened_clay.getDefaultState();
                        } else {
                           state = this.getBand(n, i, n2);
                        }

                        chunkPrimer.setBlockState(n6, i, n5, state);
                     }
                  } else if (flag) {
                     chunkPrimer.setBlockState(n6, i, n5, Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT));
                  } else {
                     chunkPrimer.setBlockState(n6, i, n5, Blocks.grass.getDefaultState());
                  }
               } else if (n8 > 0) {
                  n8--;
                  if (flag2) {
                     chunkPrimer.setBlockState(
                        n6, i, n5, Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.ORANGE)
                     );
                  } else {
                     chunkPrimer.setBlockState(n6, i, n5, this.getBand(n, i, n2));
                  }
               }
            }
         }
      }
   }

   @Override
   public int getFoliageColorAtPos(BlockPos pos) {
      return 10387789;
   }

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random rand) {
      return this.worldGeneratorTrees;
   }

   @Override
   public void decorate(World world, Random rand, BlockPos pos) {
      super.decorate(world, rand, pos);
   }

   @Override
   public int getGrassColorAtPos(BlockPos pos) {
      return 9470285;
   }

   private IBlockState getBand(int n, int n2, int n3) {
      int n4 = (int)Math.round(this.clayBandsOffsetNoise.getValue((double)n * 1.0 / 512.0, (double)n * 1.0 / 512.0) * 2.0);
      return this.clayBands[(n2 + n4 + 64) % 64];
   }

   public BiomeGenMesa(int n, boolean flag, boolean flag2) {
      super(n);
      this.brycePillars = flag;
      this.hasForest = flag2;
      this.setDisableRain();
      this.setTemperatureRainfall(2.0F, 0.0F);
      this.spawnableCreatureList.clear();
      this.topBlock = Blocks.sand.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
      this.fillerBlock = Blocks.stained_hardened_clay.getDefaultState();
      this.theBiomeDecorator.treesPerChunk = -999;
      this.theBiomeDecorator.deadBushPerChunk = 20;
      this.theBiomeDecorator.reedsPerChunk = 3;
      this.theBiomeDecorator.cactiPerChunk = 5;
      this.theBiomeDecorator.flowersPerChunk = 0;
      this.spawnableCreatureList.clear();
      if (flag2) {
         this.theBiomeDecorator.treesPerChunk = 5;
      }
   }

   @Override
   public BiomeGenBase createMutatedBiome(int n) {
      boolean flag = this.biomeID == BiomeGenBase.mesa.biomeID;
      BiomeGenMesa biomeGenMesa = new BiomeGenMesa(n, flag, this.hasForest);
      if (!flag) {
         biomeGenMesa.setHeight(height_LowHills);
         biomeGenMesa.setBiomeName(this.biomeName + " M");
      } else {
         biomeGenMesa.setBiomeName(this.biomeName + " (Bryce)");
      }

      biomeGenMesa.setColor(this.color, true);
      return biomeGenMesa;
   }
}
