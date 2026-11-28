package net.minecraft.world.biome;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import java.util.Random;
import net.minecraft.block.BlockDirt;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenTaiga extends BiomeGenBase {
   private static final WorldGenTaiga1 PINE_GENERATOR = new WorldGenTaiga1();
   private static final WorldGenTaiga2 SPRUCE_GENERATOR = new WorldGenTaiga2(false);
   private static final WorldGenMegaPineTree MEGA_PINE_GENERATOR = new WorldGenMegaPineTree(false, false);
   private static final WorldGenMegaPineTree MEGA_SPRUCE_GENERATOR = new WorldGenMegaPineTree(false, true);
   private static final WorldGenBlockBlob FOREST_ROCK_GENERATOR = new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0);
   private int type;

   @Override
   public WorldGenerator getRandomWorldGenForGrass(Random rand) {
      return rand.nextInt(5) > 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
   }

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random rand) {
      return (WorldGenAbstractTree)((this.type == 1 || this.type == 2) && rand.nextInt(3) == 0
         ? (this.type != 2 && rand.nextInt(13) != 0 ? MEGA_PINE_GENERATOR : MEGA_SPRUCE_GENERATOR)
         : (rand.nextInt(3) == 0 ? PINE_GENERATOR : SPRUCE_GENERATOR));
   }

   public BiomeGenTaiga(int n, int n2) {
      super(n);
      this.type = n2;
      this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 8, 4, 4));
      this.theBiomeDecorator.treesPerChunk = 10;
      if (n2 != 1 && n2 != 2) {
         this.theBiomeDecorator.grassPerChunk = 1;
         this.theBiomeDecorator.mushroomsPerChunk = 1;
      } else {
         this.theBiomeDecorator.grassPerChunk = 7;
         this.theBiomeDecorator.deadBushPerChunk = 1;
         this.theBiomeDecorator.mushroomsPerChunk = 3;
      }
   }

   @Override
   public void decorate(World world, Random rand, BlockPos pos) {
      if (this.type == 1 || this.type == 2) {
         int n = rand.nextInt(3);

         for (int i = 0; i < n; i++) {
            BlockPos height = world.getHeight(pos.add(rand.nextInt(16) + 8, 0, rand.nextInt(16) + 8));
            FOREST_ROCK_GENERATOR.generate(world, rand, height);
         }
      }

      DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.FERN);

      for (int j = 0; j < 7; j++) {
         int n4 = rand.nextInt(16) + 8;
         int n5 = rand.nextInt(16) + 8;
         int n6 = rand.nextInt(world.getHeight(pos.add(n4, 0, n5)).getY() + 32);
         DOUBLE_PLANT_GENERATOR.generate(world, rand, pos.add(n4, n6, n5));
      }

      super.decorate(world, rand, pos);
   }

   @Override
   public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, int n, int n2, double d) {
      if (this.type == 1 || this.type == 2) {
         this.topBlock = Blocks.grass.getDefaultState();
         this.fillerBlock = Blocks.dirt.getDefaultState();
         if (d > 1.75) {
            this.topBlock = Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
         } else if (d > -0.95) {
            this.topBlock = Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL);
         }
      }

      this.generateBiomeTerrain(world, rand, chunkPrimer, n, n2, d);
   }

   @Override
   public BiomeGenBase createMutatedBiome(int n) {
      return this.biomeID == BiomeGenBase.megaTaiga.biomeID
         ? new BiomeGenTaiga(n, 2)
            .setColor(5858897, true)
            .setBiomeName("Mega Spruce Taiga")
            .setFillerBlockMetadata(5159473)
            .setTemperatureRainfall(0.25F, 0.8F)
            .setHeight(new BiomeGenBase.Height(this.minHeight, this.maxHeight))
         : super.createMutatedBiome(n);
   }
}
