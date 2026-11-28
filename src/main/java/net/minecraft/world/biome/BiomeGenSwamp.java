package net.minecraft.world.biome;

import net.minecraft.block.BlockFlower;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenSwamp extends BiomeGenBase {
   @Override
   public int getGrassColorAtPos(BlockPos pos) {
      return GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225, (double)pos.getZ() * 0.0225) < -0.1 ? 5011004 : 6975545;
   }

   @Override
   public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int n, int n2, double d) {
      double value = GRASS_COLOR_NOISE.getValue((double)n * 0.25, (double)n2 * 0.25);
      if (value > 0.0) {
         int n3 = n & 15;
         int n4 = n2 & 15;

         for (int i = 255; i >= 0; i--) {
            if (chunkPrimer.getBlockState(n4, i, n3).getBlock().getMaterial() != Material.air) {
               if (i == 62 && chunkPrimer.getBlockState(n4, i, n3).getBlock() != Blocks.water) {
                  chunkPrimer.setBlockState(n4, i, n3, Blocks.water.getDefaultState());
                  if (value < 0.12) {
                     chunkPrimer.setBlockState(n4, i + 1, n3, Blocks.waterlily.getDefaultState());
                  }
               }
               break;
            }
         }
      }

      this.generateBiomeTerrain(world, rand, chunkPrimer, n, n2, d);
   }

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random rand) {
      return this.worldGeneratorSwamp;
   }

   public BiomeGenSwamp(int n) {
      super(n);
      this.theBiomeDecorator.treesPerChunk = 2;
      this.theBiomeDecorator.flowersPerChunk = 1;
      this.theBiomeDecorator.deadBushPerChunk = 1;
      this.theBiomeDecorator.mushroomsPerChunk = 8;
      this.theBiomeDecorator.reedsPerChunk = 10;
      this.theBiomeDecorator.clayPerChunk = 1;
      this.theBiomeDecorator.waterlilyPerChunk = 4;
      this.theBiomeDecorator.sandPerChunk2 = 0;
      this.theBiomeDecorator.sandPerChunk = 0;
      this.theBiomeDecorator.grassPerChunk = 5;
      this.waterColorMultiplier = 14745518;
      this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 1, 1, 1));
   }

   @Override
   public int getFoliageColorAtPos(BlockPos pos) {
      return 6975545;
   }

   @Override
   public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos) {
      return BlockFlower.EnumFlowerType.BLUE_ORCHID;
   }
}
