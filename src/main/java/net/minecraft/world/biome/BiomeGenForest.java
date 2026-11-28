package net.minecraft.world.biome;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import java.util.Random;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;

public class BiomeGenForest extends BiomeGenBase {
   private int type;
   public static final WorldGenForest SUPER_BIRCH_TREE = new WorldGenForest(false, true);
   public static final WorldGenForest BIRCH_TREE = new WorldGenForest(false, false);
   public static final WorldGenCanopyTree ROOF_TREE = new WorldGenCanopyTree(false);

   @Override
   public void decorate(World world, Random rand, BlockPos pos) {
      if (this.type == 3) {
         for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
               BlockPos height = world.getHeight(pos.add(i * 4 + 1 + 8 + rand.nextInt(3), 0, j * 4 + 1 + 8 + rand.nextInt(3)));
               if (rand.nextInt(20) == 0) {
                  new WorldGenBigMushroom().generate(world, rand, height);
               } else {
                  WorldGenAbstractTree worldGenAbstractTree = this.genBigTreeChance(rand);
                  worldGenAbstractTree.setDecorationDefaults();
                  if (worldGenAbstractTree.generate(world, rand, height)) {
                     worldGenAbstractTree.generateSaplings(world, rand, height);
                  }
               }
            }
         }
      }

      int n3 = rand.nextInt(5) - 3;
      if (this.type == 1) {
         n3 += 2;
      }

      for (int k = 0; k < n3; k++) {
         int n4 = rand.nextInt(3);
         if (n4 == 0) {
            DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.SYRINGA);
         } else if (n4 == 1) {
            DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.ROSE);
         } else if (n4 == 2) {
            DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.PAEONIA);
         }

         for (int m = 0; m < 5; m++) {
            int n5 = rand.nextInt(16) + 8;
            int n6 = rand.nextInt(16) + 8;
            int n7 = rand.nextInt(world.getHeight(pos.add(n5, 0, n6)).getY() + 32);
            if (DOUBLE_PLANT_GENERATOR.generate(world, rand, new BlockPos(pos.getX() + n5, n7, pos.getZ() + n6))) {
               break;
            }
         }
      }

      super.decorate(world, rand, pos);
   }

   @Override
   public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos) {
      if (this.type == 1) {
         double d = MathHelper.clamp_double(
            (1.0 + GRASS_COLOR_NOISE.getValue((double)pos.getX() / 48.0, (double)pos.getZ() / 48.0)) / 2.0, 0.0, 0.9999
         );
         BlockFlower.EnumFlowerType enumFlowerType = BlockFlower.EnumFlowerType.values()[(int)(d * (double)BlockFlower.EnumFlowerType.values().length)];
         return enumFlowerType == BlockFlower.EnumFlowerType.BLUE_ORCHID ? BlockFlower.EnumFlowerType.POPPY : enumFlowerType;
      } else {
         return super.pickRandomFlower(rand, pos);
      }
   }

   public BiomeGenForest(int n, int n2) {
      super(n);
      this.type = n2;
      this.theBiomeDecorator.treesPerChunk = 10;
      this.theBiomeDecorator.grassPerChunk = 2;
      if (this.type == 1) {
         this.theBiomeDecorator.treesPerChunk = 6;
         this.theBiomeDecorator.flowersPerChunk = 100;
         this.theBiomeDecorator.grassPerChunk = 1;
      }

      this.setFillerBlockMetadata(5159473);
      this.setTemperatureRainfall(0.7F, 0.8F);
      if (this.type == 2) {
         this.modColor = 353825;
         this.color = 3175492;
         this.setTemperatureRainfall(0.6F, 0.6F);
      }

      if (this.type == 0) {
         this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 5, 4, 4));
      }

      if (this.type == 3) {
         this.theBiomeDecorator.treesPerChunk = -999;
      }
   }

   @Override
   public BiomeGenBase createMutatedBiome(int n) {
      if (this.biomeID == BiomeGenBase.forest.biomeID) {
         BiomeGenForest biomeGenForest = new BiomeGenForest(n, 1);
         biomeGenForest.setHeight(new BiomeGenBase.Height(this.minHeight, this.maxHeight + 0.2F));
         biomeGenForest.setBiomeName("Flower Forest");
         biomeGenForest.setColor(6976549, true);
         biomeGenForest.setFillerBlockMetadata(8233509);
         return biomeGenForest;
      } else {
         return (BiomeGenBase)(this.biomeID != BiomeGenBase.birchForest.biomeID && this.biomeID != BiomeGenBase.birchForestHills.biomeID
            ? new BiomeGenForest.DefaultBiomeGenMutated(this, n, this)
            : new BiomeGenForest.DefaultBiomeGenMutated2(this, n, this));
      }
   }

   @Override
   public BiomeGenBase setColor(int n, boolean flag) {
      if (this.type == 2) {
         this.modColor = 353825;
         this.color = n;
         if (flag) {
            this.modColor = (this.modColor & 16711422) >> 1;
         }

         return this;
      } else {
         return super.setColor(n, flag);
      }
   }

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random rand) {
      return (WorldGenAbstractTree)(this.type == 3 && rand.nextInt(3) > 0 ? ROOF_TREE : (this.type != 2 && rand.nextInt(5) != 0 ? this.worldGeneratorTrees : BIRCH_TREE));
   }

   @Override
   public int getGrassColorAtPos(BlockPos pos) {
      int grassColorAtPos = super.getGrassColorAtPos(pos);
      return this.type == 3 ? (grassColorAtPos & 16711422) + 2634762 >> 1 : grassColorAtPos;
   }


   public static class DefaultBiomeGenMutated extends BiomeGenMutated {
      public final BiomeGenForest parentForest;

      public DefaultBiomeGenMutated(BiomeGenForest biomeGenForest, int n, BiomeGenBase biomeGenBase) {
         super(n, biomeGenBase);
         this.parentForest = biomeGenForest;
      }

      @Override
      public void decorate(World world, Random rand, BlockPos pos) {
         this.baseBiome.decorate(world, rand, pos);
      }
   }

   public static class DefaultBiomeGenMutated2 extends BiomeGenMutated {
      public final BiomeGenForest parentForest;

      @Override
      public WorldGenAbstractTree genBigTreeChance(Random rand) {
         return rand.nextBoolean() ? BiomeGenForest.SUPER_BIRCH_TREE : BiomeGenForest.BIRCH_TREE;
      }

      public DefaultBiomeGenMutated2(BiomeGenForest biomeGenForest, int n, BiomeGenBase biomeGenBase) {
         super(n, biomeGenBase);
         this.parentForest = biomeGenForest;
      }
   }
}
