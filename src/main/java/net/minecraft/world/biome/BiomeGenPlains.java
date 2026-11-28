package net.minecraft.world.biome;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import java.util.Random;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BiomeGenPlains extends BiomeGenBase {
   public boolean sunflowers;

   @Override
   public BiomeGenBase createMutatedBiome(int n) {
      BiomeGenPlains biomeGenPlains = new BiomeGenPlains(n);
      biomeGenPlains.setBiomeName("Sunflower Plains");
      biomeGenPlains.sunflowers = true;
      biomeGenPlains.setColor(9286496);
      biomeGenPlains.modColor = 14273354;
      return biomeGenPlains;
   }

   public BiomeGenPlains(int n) {
      super(n);
      this.setTemperatureRainfall(0.8F, 0.4F);
      this.setHeight(height_LowPlains);
      this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 5, 2, 6));
      this.theBiomeDecorator.treesPerChunk = -999;
      this.theBiomeDecorator.flowersPerChunk = 4;
      this.theBiomeDecorator.grassPerChunk = 10;
   }

   @Override
   public void decorate(World world, Random rand, BlockPos pos) {
      if (GRASS_COLOR_NOISE.getValue((double)(pos.getX() + 8) / 200.0, (double)(pos.getZ() + 8) / 200.0) < -0.8) {
         this.theBiomeDecorator.flowersPerChunk = 15;
         this.theBiomeDecorator.grassPerChunk = 5;
      } else {
         this.theBiomeDecorator.flowersPerChunk = 4;
         this.theBiomeDecorator.grassPerChunk = 10;
         DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);

         for (int i = 0; i < 7; i++) {
            int n = rand.nextInt(16) + 8;
            int n2 = rand.nextInt(16) + 8;
            int n3 = rand.nextInt(world.getHeight(pos.add(n, 0, n2)).getY() + 32);
            DOUBLE_PLANT_GENERATOR.generate(world, rand, pos.add(n, n3, n2));
         }
      }

      if (this.sunflowers) {
         DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.SUNFLOWER);

         for (int j = 0; j < 10; j++) {
            int n4 = rand.nextInt(16) + 8;
            int n5 = rand.nextInt(16) + 8;
            int n6 = rand.nextInt(world.getHeight(pos.add(n4, 0, n5)).getY() + 32);
            DOUBLE_PLANT_GENERATOR.generate(world, rand, pos.add(n4, n6, n5));
         }
      }

      super.decorate(world, rand, pos);
   }

   @Override
   public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos) {
      if (GRASS_COLOR_NOISE.getValue((double)pos.getX() / 200.0, (double)pos.getZ() / 200.0) < -0.8) {
         switch (rand.nextInt(4)) {
            case 0:
               return BlockFlower.EnumFlowerType.ORANGE_TULIP;
            case 1:
               return BlockFlower.EnumFlowerType.RED_TULIP;
            case 2:
               return BlockFlower.EnumFlowerType.PINK_TULIP;
            case 3:
            default:
               return BlockFlower.EnumFlowerType.WHITE_TULIP;
         }
      } else if (rand.nextInt(3) > 0) {
         int n2 = rand.nextInt(3);
         return n2 == 0 ? BlockFlower.EnumFlowerType.POPPY : (n2 == 1 ? BlockFlower.EnumFlowerType.HOUSTONIA : BlockFlower.EnumFlowerType.OXEYE_DAISY);
      } else {
         return BlockFlower.EnumFlowerType.DANDELION;
      }
   }
}
