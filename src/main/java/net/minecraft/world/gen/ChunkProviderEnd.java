package net.minecraft.world.gen;

import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderEnd implements IChunkProvider {
   private Random endRNG;
   private NoiseGeneratorOctaves noiseGen1;
   private NoiseGeneratorOctaves noiseGen2;
   private NoiseGeneratorOctaves noiseGen3;
   public NoiseGeneratorOctaves noiseGen4;
   public NoiseGeneratorOctaves noiseGen5;
   private World endWorld;
   private double[] densities;
   private BiomeGenBase[] biomesForGeneration;
   public double[] noiseData1;
   public double[] noiseData2;
   public double[] noiseData3;
   public double[] noiseData4;
   public double[] noiseData5;

   @Override
   public Chunk provideChunk(int n, int n2) {
      this.endRNG.setSeed((long)n * 341873128712L + (long)n2 * 132897987541L);
      ChunkPrimer chunkPrimer = new ChunkPrimer();
      this.biomesForGeneration = this.endWorld.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, n * 16, n2 * 16, 16, 16);
      this.setBlocksInChunk(n, n2, chunkPrimer);
      this.buildSurfaces(chunkPrimer);
      Chunk chunk = new Chunk(this.endWorld, chunkPrimer, n, n2);
      byte[] biomeArray = chunk.getBiomeArray();

      for (int i = 0; i < biomeArray.length; i++) {
         biomeArray[i] = (byte)this.biomesForGeneration[i].biomeID;
      }

      chunk.generateSkylightMap();
      return chunk;
   }

   @Override
   public void populate(IChunkProvider chunkProvider, int n, int n2) {
      BlockFalling.fallInstantly = true;
      BlockPos pos = new BlockPos(n * 16, 0, n2 * 16);
      this.endWorld.getBiomeGenForCoords(pos.add(16, 0, 16)).decorate(this.endWorld, this.endWorld.rand, pos);
      BlockFalling.fallInstantly = false;
   }

   @Override
   public List getPossibleCreatures(EnumCreatureType enumCreatureType, BlockPos pos) {
      return this.endWorld.getBiomeGenForCoords(pos).getSpawnableList(enumCreatureType);
   }

   @Override
   public Chunk provideChunk(BlockPos pos) {
      return this.provideChunk(pos.getX() >> 4, pos.getZ() >> 4);
   }

   @Override
   public boolean canSave() {
      return true;
   }

   @Override
   public boolean chunkExists(int n, int n2) {
      return true;
   }

   public void setBlocksInChunk(int n, int n2, ChunkPrimer chunkPrimer) {
      byte b = 2;
      int n3 = b + 1;
      byte b2 = 33;
      int n4 = b + 1;
      this.densities = this.initializeNoiseField(this.densities, n * b, 0, n2 * b, n3, b2, n4);

      for (int i = 0; i < b; i++) {
         for (int j = 0; j < b; j++) {
            for (int k = 0; k < 32; k++) {
               double d = 0.25;
               double d2 = this.densities[((i + 0) * n4 + j + 0) * b2 + k + 0];
               double d3 = this.densities[((i + 0) * n4 + j + 1) * b2 + k + 0];
               double d4 = this.densities[((i + 1) * n4 + j + 0) * b2 + k + 0];
               double d5 = this.densities[((i + 1) * n4 + j + 1) * b2 + k + 0];
               double d6 = (this.densities[((i + 0) * n4 + j + 0) * b2 + k + 1] - d2) * d;
               double d7 = (this.densities[((i + 0) * n4 + j + 1) * b2 + k + 1] - d3) * d;
               double d8 = (this.densities[((i + 1) * n4 + j + 0) * b2 + k + 1] - d4) * d;
               double d9 = (this.densities[((i + 1) * n4 + j + 1) * b2 + k + 1] - d5) * d;

               for (int m = 0; m < 4; m++) {
                  double d10 = 0.125;
                  double d11 = d2;
                  double d12 = d3;
                  double d13 = (d4 - d2) * d10;
                  double d14 = (d5 - d3) * d10;

                  for (int idx = 0; idx < 8; idx++) {
                     double d15 = 0.125;
                     double d16 = d11;
                     double d17 = (d12 - d11) * d15;

                     for (int index = 0; index < 8; index++) {
                        IBlockState state = null;
                        if (d16 > 0.0) {
                           state = Blocks.end_stone.getDefaultState();
                        }

                        chunkPrimer.setBlockState(idx + i * 8, m + k * 4, index + j * 8, state);
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

   @Override
   public boolean saveChunks(boolean flag, IProgressUpdate progressUpdate) {
      return true;
   }

   @Override
   public void saveExtraData() {
   }

   public void buildSurfaces(ChunkPrimer chunkPrimer) {
      for (int i = 0; i < 16; i++) {
         for (int j = 0; j < 16; j++) {
            byte b = 1;
            int n = -1;
            IBlockState defaultState = Blocks.end_stone.getDefaultState();
            IBlockState defaultState2 = Blocks.end_stone.getDefaultState();

            for (int k = 127; k >= 0; k--) {
               IBlockState blockState = chunkPrimer.getBlockState(i, k, j);
               if (blockState.getBlock().getMaterial() == Material.air) {
                  n = -1;
               } else if (blockState.getBlock() == Blocks.stone) {
                  if (n == -1) {
                     if (b <= 0) {
                        defaultState = Blocks.air.getDefaultState();
                        defaultState2 = Blocks.end_stone.getDefaultState();
                     }

                     n = b;
                     if (k >= 0) {
                        chunkPrimer.setBlockState(i, k, j, defaultState);
                     } else {
                        chunkPrimer.setBlockState(i, k, j, defaultState2);
                     }
                  } else if (n > 0) {
                     n--;
                     chunkPrimer.setBlockState(i, k, j, defaultState2);
                  }
               }
            }
         }
      }
   }

   @Override
   public int getLoadedChunkCount() {
      return 0;
   }

   @Override
   public String makeString() {
      return "RandomLevelSource";
   }

   @Override
   public void recreateStructures(Chunk chunk, int n, int n2) {
   }

   @Override
   public boolean populateChunk(IChunkProvider chunkProvider, Chunk chunk, int n, int n2) {
      return false;
   }

   @Override
   public BlockPos getStrongholdGen(World world, String str, BlockPos pos) {
      return null;
   }

   public ChunkProviderEnd(World world, long l) {
      this.endWorld = world;
      this.endRNG = new Random(l);
      this.noiseGen1 = new NoiseGeneratorOctaves(this.endRNG, 16);
      this.noiseGen2 = new NoiseGeneratorOctaves(this.endRNG, 16);
      this.noiseGen3 = new NoiseGeneratorOctaves(this.endRNG, 8);
      this.noiseGen4 = new NoiseGeneratorOctaves(this.endRNG, 10);
      this.noiseGen5 = new NoiseGeneratorOctaves(this.endRNG, 16);
   }

   private double[] initializeNoiseField(double[] doubles, int n, int n2, int n3, int n4, int n5, int n6) {
      if (doubles == null) {
         doubles = new double[n4 * n5 * n6];
      }

      double d = 684.412;
      double d2 = 684.412;
      this.noiseData4 = this.noiseGen4.generateNoiseOctaves(this.noiseData4, n, n3, n4, n6, 1.121, 1.121, 0.5);
      this.noiseData5 = this.noiseGen5.generateNoiseOctaves(this.noiseData5, n, n3, n4, n6, 200.0, 200.0, 0.5);
      d *= 2.0;
      this.noiseData1 = this.noiseGen3.generateNoiseOctaves(this.noiseData1, n, n2, n3, n4, n5, n6, d / 80.0, d2 / 160.0, d / 80.0);
      this.noiseData2 = this.noiseGen1.generateNoiseOctaves(this.noiseData2, n, n2, n3, n4, n5, n6, d, d2, d);
      this.noiseData3 = this.noiseGen2.generateNoiseOctaves(this.noiseData3, n, n2, n3, n4, n5, n6, d, d2, d);
      int n7 = 0;

      for (int i = 0; i < n4; i++) {
         for (int j = 0; j < n6; j++) {
            float f = (float)(i + n) / 1.0F;
            float f2 = (float)(j + n3) / 1.0F;
            float f3 = 100.0F - MathHelper.sqrt_float(f * f + f2 * f2) * 8.0F;
            if (f3 > 80.0F) {
               f3 = 80.0F;
            }

            if (f3 < -100.0F) {
               f3 = -100.0F;
            }

            for (int k = 0; k < n5; k++) {
               double d3 = 0.0;
               double d4 = this.noiseData2[n7] / 512.0;
               double d5 = this.noiseData3[n7] / 512.0;
               double d6 = (this.noiseData1[n7] / 10.0 + 1.0) / 2.0;
               if (d6 < 0.0) {
                  d3 = d4;
               } else if (d6 > 1.0) {
                  d3 = d5;
               } else {
                  d3 = d4 + (d5 - d4) * d6;
               }

               d3 -= 8.0;
               d3 += (double)f3;
               byte b = 2;
               if (k > n5 / 2 - b) {
                  double d7 = (double)((float)(k - (n5 / 2 - b)) / 64.0F);
                  d7 = MathHelper.clamp_double(d7, 0.0, 1.0);
                  d3 = d3 * (1.0 - d7) + -3000.0 * d7;
               }

               b = 8;
               if (k < b) {
                  double d8 = (double)((float)(b - k) / ((float)b - 1.0F));
                  d3 = d3 * (1.0 - d8) + -30.0 * d8;
               }

               doubles[n7] = d3;
               n7++;
            }
         }
      }

      return doubles;
   }

   @Override
   public boolean unloadQueuedChunks() {
      return false;
   }
}
