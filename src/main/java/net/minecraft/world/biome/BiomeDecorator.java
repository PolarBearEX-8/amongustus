package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.GeneratorBushFeature;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenClay;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecorator {
   public World currentWorld;
   public Random randomGenerator;
   public BlockPos chunkPos;
   public ChunkProviderSettings chunkProviderSettings;
   public WorldGenerator clayGen = new WorldGenClay(4);
   public WorldGenerator sandGen = new WorldGenSand(Blocks.sand, 7);
   public WorldGenerator gravelAsSandGen = new WorldGenSand(Blocks.gravel, 6);
   public WorldGenerator dirtGen;
   public WorldGenerator gravelGen;
   public WorldGenerator graniteGen;
   public WorldGenerator dioriteGen;
   public WorldGenerator andesiteGen;
   public WorldGenerator coalGen;
   public WorldGenerator ironGen;
   public WorldGenerator goldGen;
   public WorldGenerator redstoneGen;
   public WorldGenerator diamondGen;
   public WorldGenerator lapisGen;
   public WorldGenFlowers yellowFlowerGen = new WorldGenFlowers(Blocks.yellow_flower, BlockFlower.EnumFlowerType.DANDELION);
   public WorldGenerator mushroomBrownGen = new GeneratorBushFeature(Blocks.brown_mushroom);
   public WorldGenerator mushroomRedGen = new GeneratorBushFeature(Blocks.red_mushroom);
   public WorldGenerator bigMushroomGen = new WorldGenBigMushroom();
   public WorldGenerator reedGen = new WorldGenReed();
   public WorldGenerator cactusGen = new WorldGenCactus();
   public WorldGenerator waterlilyGen = new WorldGenWaterlily();
   public int waterlilyPerChunk;
   public int treesPerChunk;
   public int flowersPerChunk = 2;
   public int grassPerChunk = 1;
   public int deadBushPerChunk;
   public int mushroomsPerChunk;
   public int reedsPerChunk;
   public int cactiPerChunk;
   public int sandPerChunk = 1;
   public int sandPerChunk2 = 3;
   public int clayPerChunk = 1;
   public int bigMushroomsPerChunk;
   public boolean generateLakes = true;

   public void generateOres() {
      this.genStandardOre1(this.chunkProviderSettings.dirtCount, this.dirtGen, this.chunkProviderSettings.dirtMinHeight, this.chunkProviderSettings.dirtMaxHeight);
      this.genStandardOre1(this.chunkProviderSettings.gravelCount, this.gravelGen, this.chunkProviderSettings.gravelMinHeight, this.chunkProviderSettings.gravelMaxHeight);
      this.genStandardOre1(
         this.chunkProviderSettings.dioriteCount, this.dioriteGen, this.chunkProviderSettings.dioriteMinHeight, this.chunkProviderSettings.dioriteMaxHeight
      );
      this.genStandardOre1(
         this.chunkProviderSettings.graniteCount, this.graniteGen, this.chunkProviderSettings.graniteMinHeight, this.chunkProviderSettings.graniteMaxHeight
      );
      this.genStandardOre1(
         this.chunkProviderSettings.andesiteCount, this.andesiteGen, this.chunkProviderSettings.andesiteMinHeight, this.chunkProviderSettings.andesiteMaxHeight
      );
      this.genStandardOre1(this.chunkProviderSettings.coalCount, this.coalGen, this.chunkProviderSettings.coalMinHeight, this.chunkProviderSettings.coalMaxHeight);
      this.genStandardOre1(this.chunkProviderSettings.ironCount, this.ironGen, this.chunkProviderSettings.ironMinHeight, this.chunkProviderSettings.ironMaxHeight);
      this.genStandardOre1(this.chunkProviderSettings.goldCount, this.goldGen, this.chunkProviderSettings.goldMinHeight, this.chunkProviderSettings.goldMaxHeight);
      this.genStandardOre1(
         this.chunkProviderSettings.redstoneCount, this.redstoneGen, this.chunkProviderSettings.redstoneMinHeight, this.chunkProviderSettings.redstoneMaxHeight
      );
      this.genStandardOre1(
         this.chunkProviderSettings.diamondCount, this.diamondGen, this.chunkProviderSettings.diamondMinHeight, this.chunkProviderSettings.diamondMaxHeight
      );
      this.genStandardOre2(this.chunkProviderSettings.lapisCount, this.lapisGen, this.chunkProviderSettings.lapisCenterHeight, this.chunkProviderSettings.lapisSpread);
   }

   public void genStandardOre1(int n, WorldGenerator worldGenerator, int n2, int n3) {
      if (n3 < n2) {
         int n4 = n2;
         n2 = n3;
         n3 = n4;
      } else if (n3 == n2) {
         if (n2 < 255) {
            n3++;
         } else {
            n2--;
         }
      }

      for (int i = 0; i < n; i++) {
         BlockPos pos = this.chunkPos.add(this.randomGenerator.nextInt(16), this.randomGenerator.nextInt(n3 - n2) + n2, this.randomGenerator.nextInt(16));
         worldGenerator.generate(this.currentWorld, this.randomGenerator, pos);
      }
   }

   public void decorate(World world, Random rand, BiomeGenBase biomeGenBase, BlockPos pos) {
      if (this.currentWorld != null) {
         throw new RuntimeException("Already decorating");
      } else {
         this.currentWorld = world;
         String generatorOptions = world.getWorldInfo().getGeneratorOptions();
         if (generatorOptions != null) {
            this.chunkProviderSettings = ChunkProviderSettings.Factory.jsonToFactory(generatorOptions).build();
         } else {
            this.chunkProviderSettings = ChunkProviderSettings.Factory.jsonToFactory("").build();
         }

         this.randomGenerator = rand;
         this.chunkPos = pos;
         this.dirtGen = new WorldGenMinable(Blocks.dirt.getDefaultState(), this.chunkProviderSettings.dirtSize);
         this.gravelGen = new WorldGenMinable(Blocks.gravel.getDefaultState(), this.chunkProviderSettings.gravelSize);
         this.graniteGen = new WorldGenMinable(
            Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), this.chunkProviderSettings.graniteSize
         );
         this.dioriteGen = new WorldGenMinable(
            Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), this.chunkProviderSettings.dioriteSize
         );
         this.andesiteGen = new WorldGenMinable(
            Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), this.chunkProviderSettings.andesiteSize
         );
         this.coalGen = new WorldGenMinable(Blocks.coal_ore.getDefaultState(), this.chunkProviderSettings.coalSize);
         this.ironGen = new WorldGenMinable(Blocks.iron_ore.getDefaultState(), this.chunkProviderSettings.ironSize);
         this.goldGen = new WorldGenMinable(Blocks.gold_ore.getDefaultState(), this.chunkProviderSettings.goldSize);
         this.redstoneGen = new WorldGenMinable(Blocks.redstone_ore.getDefaultState(), this.chunkProviderSettings.redstoneSize);
         this.diamondGen = new WorldGenMinable(Blocks.diamond_ore.getDefaultState(), this.chunkProviderSettings.diamondSize);
         this.lapisGen = new WorldGenMinable(Blocks.lapis_ore.getDefaultState(), this.chunkProviderSettings.lapisSize);
         this.genDecorations(biomeGenBase);
         this.currentWorld = null;
         this.randomGenerator = null;
      }
   }

   public void genDecorations(BiomeGenBase biomeGenBase) {
      this.generateOres();

      for (int i = 0; i < this.sandPerChunk2; i++) {
         int n = this.randomGenerator.nextInt(16) + 8;
         int n2 = this.randomGenerator.nextInt(16) + 8;
         this.sandGen.generate(this.currentWorld, this.randomGenerator, this.currentWorld.getTopSolidOrLiquidBlock(this.chunkPos.add(n, 0, n2)));
      }

      for (int j = 0; j < this.clayPerChunk; j++) {
         int n3 = this.randomGenerator.nextInt(16) + 8;
         int n4 = this.randomGenerator.nextInt(16) + 8;
         this.clayGen.generate(this.currentWorld, this.randomGenerator, this.currentWorld.getTopSolidOrLiquidBlock(this.chunkPos.add(n3, 0, n4)));
      }

      for (int k = 0; k < this.sandPerChunk; k++) {
         int n5 = this.randomGenerator.nextInt(16) + 8;
         int n6 = this.randomGenerator.nextInt(16) + 8;
         this.gravelAsSandGen.generate(this.currentWorld, this.randomGenerator, this.currentWorld.getTopSolidOrLiquidBlock(this.chunkPos.add(n5, 0, n6)));
      }

      int treesPerChunk2 = this.treesPerChunk;
      if (this.randomGenerator.nextInt(10) == 0) {
         treesPerChunk2++;
      }

      for (int m = 0; m < treesPerChunk2; m++) {
         int n7 = this.randomGenerator.nextInt(16) + 8;
         int n8 = this.randomGenerator.nextInt(16) + 8;
         WorldGenAbstractTree worldGenAbstractTree = biomeGenBase.genBigTreeChance(this.randomGenerator);
         worldGenAbstractTree.setDecorationDefaults();
         BlockPos height = this.currentWorld.getHeight(this.chunkPos.add(n7, 0, n8));
         if (worldGenAbstractTree.generate(this.currentWorld, this.randomGenerator, height)) {
            worldGenAbstractTree.generateSaplings(this.currentWorld, this.randomGenerator, height);
         }
      }

      for (int idx = 0; idx < this.bigMushroomsPerChunk; idx++) {
         int n9 = this.randomGenerator.nextInt(16) + 8;
         int n10 = this.randomGenerator.nextInt(16) + 8;
         this.bigMushroomGen.generate(this.currentWorld, this.randomGenerator, this.currentWorld.getHeight(this.chunkPos.add(n9, 0, n10)));
      }

      for (int index = 0; index < this.flowersPerChunk; index++) {
         int n11 = this.randomGenerator.nextInt(16) + 8;
         int n12 = this.randomGenerator.nextInt(16) + 8;
         int n13 = this.currentWorld.getHeight(this.chunkPos.add(n11, 0, n12)).getY() + 32;
         if (n13 > 0) {
            int n14 = this.randomGenerator.nextInt(n13);
            BlockPos pos = this.chunkPos.add(n11, n14, n12);
            BlockFlower.EnumFlowerType enumFlowerType = biomeGenBase.pickRandomFlower(this.randomGenerator, pos);
            BlockFlower block = enumFlowerType.getBlockType().getBlock();
            if (block.getMaterial() != Material.air) {
               this.yellowFlowerGen.setGeneratedBlock(block, enumFlowerType);
               this.yellowFlowerGen.generate(this.currentWorld, this.randomGenerator, pos);
            }
         }
      }

      for (int i2 = 0; i2 < this.grassPerChunk; i2++) {
         int n15 = this.randomGenerator.nextInt(16) + 8;
         int n16 = this.randomGenerator.nextInt(16) + 8;
         int n17 = this.currentWorld.getHeight(this.chunkPos.add(n15, 0, n16)).getY() * 2;
         if (n17 > 0) {
            int n18 = this.randomGenerator.nextInt(n17);
            biomeGenBase.getRandomWorldGenForGrass(this.randomGenerator).generate(this.currentWorld, this.randomGenerator, this.chunkPos.add(n15, n18, n16));
         }
      }

      for (int i3 = 0; i3 < this.deadBushPerChunk; i3++) {
         int n19 = this.randomGenerator.nextInt(16) + 8;
         int n20 = this.randomGenerator.nextInt(16) + 8;
         int n21 = this.currentWorld.getHeight(this.chunkPos.add(n19, 0, n20)).getY() * 2;
         if (n21 > 0) {
            int n22 = this.randomGenerator.nextInt(n21);
            new WorldGenDeadBush().generate(this.currentWorld, this.randomGenerator, this.chunkPos.add(n19, n22, n20));
         }
      }

      for (int i4 = 0; i4 < this.waterlilyPerChunk; i4++) {
         int n23 = this.randomGenerator.nextInt(16) + 8;
         int n24 = this.randomGenerator.nextInt(16) + 8;
         int n25 = this.currentWorld.getHeight(this.chunkPos.add(n23, 0, n24)).getY() * 2;
         if (n25 > 0) {
            int n26 = this.randomGenerator.nextInt(n25);
            BlockPos pos2 = this.chunkPos.add(n23, n26, n24);

            while (pos2.getY() > 0) {
               BlockPos pos3 = pos2.down();
               if (!this.currentWorld.isAirBlock(pos3)) {
                  break;
               }

               pos2 = pos3;
            }

            this.waterlilyGen.generate(this.currentWorld, this.randomGenerator, pos2);
         }
      }

      for (int i5 = 0; i5 < this.mushroomsPerChunk; i5++) {
         if (this.randomGenerator.nextInt(4) == 0) {
            int n27 = this.randomGenerator.nextInt(16) + 8;
            int n28 = this.randomGenerator.nextInt(16) + 8;
            BlockPos height2 = this.currentWorld.getHeight(this.chunkPos.add(n27, 0, n28));
            this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, height2);
         }

         if (this.randomGenerator.nextInt(8) == 0) {
            int n29 = this.randomGenerator.nextInt(16) + 8;
            int n30 = this.randomGenerator.nextInt(16) + 8;
            int n31 = this.currentWorld.getHeight(this.chunkPos.add(n29, 0, n30)).getY() * 2;
            if (n31 > 0) {
               int n32 = this.randomGenerator.nextInt(n31);
               BlockPos pos4 = this.chunkPos.add(n29, n32, n30);
               this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, pos4);
            }
         }
      }

      if (this.randomGenerator.nextInt(4) == 0) {
         int n33 = this.randomGenerator.nextInt(16) + 8;
         int n34 = this.randomGenerator.nextInt(16) + 8;
         int n35 = this.currentWorld.getHeight(this.chunkPos.add(n33, 0, n34)).getY() * 2;
         if (n35 > 0) {
            int n36 = this.randomGenerator.nextInt(n35);
            this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, this.chunkPos.add(n33, n36, n34));
         }
      }

      if (this.randomGenerator.nextInt(8) == 0) {
         int n37 = this.randomGenerator.nextInt(16) + 8;
         int n38 = this.randomGenerator.nextInt(16) + 8;
         int n39 = this.currentWorld.getHeight(this.chunkPos.add(n37, 0, n38)).getY() * 2;
         if (n39 > 0) {
            int n40 = this.randomGenerator.nextInt(n39);
            this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, this.chunkPos.add(n37, n40, n38));
         }
      }

      for (int i6 = 0; i6 < this.reedsPerChunk; i6++) {
         int n41 = this.randomGenerator.nextInt(16) + 8;
         int n42 = this.randomGenerator.nextInt(16) + 8;
         int n43 = this.currentWorld.getHeight(this.chunkPos.add(n41, 0, n42)).getY() * 2;
         if (n43 > 0) {
            int n44 = this.randomGenerator.nextInt(n43);
            this.reedGen.generate(this.currentWorld, this.randomGenerator, this.chunkPos.add(n41, n44, n42));
         }
      }

      for (int i7 = 0; i7 < 10; i7++) {
         int n45 = this.randomGenerator.nextInt(16) + 8;
         int n46 = this.randomGenerator.nextInt(16) + 8;
         int n47 = this.currentWorld.getHeight(this.chunkPos.add(n45, 0, n46)).getY() * 2;
         if (n47 > 0) {
            int n48 = this.randomGenerator.nextInt(n47);
            this.reedGen.generate(this.currentWorld, this.randomGenerator, this.chunkPos.add(n45, n48, n46));
         }
      }

      if (this.randomGenerator.nextInt(32) == 0) {
         int n49 = this.randomGenerator.nextInt(16) + 8;
         int n50 = this.randomGenerator.nextInt(16) + 8;
         int n51 = this.currentWorld.getHeight(this.chunkPos.add(n49, 0, n50)).getY() * 2;
         if (n51 > 0) {
            int n52 = this.randomGenerator.nextInt(n51);
            new WorldGenPumpkin().generate(this.currentWorld, this.randomGenerator, this.chunkPos.add(n49, n52, n50));
         }
      }

      for (int i8 = 0; i8 < this.cactiPerChunk; i8++) {
         int n53 = this.randomGenerator.nextInt(16) + 8;
         int n54 = this.randomGenerator.nextInt(16) + 8;
         int n55 = this.currentWorld.getHeight(this.chunkPos.add(n53, 0, n54)).getY() * 2;
         if (n55 > 0) {
            int n56 = this.randomGenerator.nextInt(n55);
            this.cactusGen.generate(this.currentWorld, this.randomGenerator, this.chunkPos.add(n53, n56, n54));
         }
      }

      if (this.generateLakes) {
         for (int i9 = 0; i9 < 50; i9++) {
            int n57 = this.randomGenerator.nextInt(16) + 8;
            int n58 = this.randomGenerator.nextInt(16) + 8;
            int n59 = this.randomGenerator.nextInt(248) + 8;
            if (n59 > 0) {
               int n60 = this.randomGenerator.nextInt(n59);
               BlockPos pos5 = this.chunkPos.add(n57, n60, n58);
               new WorldGenLiquids(Blocks.flowing_water).generate(this.currentWorld, this.randomGenerator, pos5);
            }
         }

         for (int i10 = 0; i10 < 20; i10++) {
            int n61 = this.randomGenerator.nextInt(16) + 8;
            int n62 = this.randomGenerator.nextInt(16) + 8;
            int n63 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(240) + 8) + 8);
            BlockPos pos6 = this.chunkPos.add(n61, n63, n62);
            new WorldGenLiquids(Blocks.flowing_lava).generate(this.currentWorld, this.randomGenerator, pos6);
         }
      }
   }

   public void genStandardOre2(int n, WorldGenerator worldGenerator, int n2, int n3) {
      for (int i = 0; i < n; i++) {
         BlockPos pos = this.chunkPos
            .add(
               this.randomGenerator.nextInt(16),
               this.randomGenerator.nextInt(n3) + this.randomGenerator.nextInt(n3) + n2 - n3,
               this.randomGenerator.nextInt(16)
            );
         worldGenerator.generate(this.currentWorld, this.randomGenerator, pos);
      }
   }
}
