package net.minecraft.world.biome;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import java.util.Random;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenJungle extends BiomeGenBase {
   private boolean isEdge;
   private static final IBlockState JUNGLE_LOG = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
   private static final IBlockState JUNGLE_LEAF = Blocks.leaves
      .getDefaultState()
      .withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE)
      .withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE);
   private static final IBlockState OAK_LEAF = Blocks.leaves
      .getDefaultState()
      .withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK)
      .withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE);

   @Override
   public void decorate(World world, Random rand, BlockPos pos) {
      super.decorate(world, rand, pos);
      int n = rand.nextInt(16) + 8;
      int n2 = rand.nextInt(16) + 8;
      int n3 = rand.nextInt(world.getHeight(pos.add(n, 0, n2)).getY() * 2);
      new WorldGenMelon().generate(world, rand, pos.add(n, n3, n2));
      WorldGenVines worldGenVines = new WorldGenVines();

      for (int i = 0; i < 50; i++) {
         n3 = rand.nextInt(16) + 8;
         worldGenVines.generate(world, rand, pos.add(n3, 128, rand.nextInt(16) + 8));
      }
   }

   public BiomeGenJungle(int n, boolean flag) {
      super(n);
      this.isEdge = flag;
      if (flag) {
         this.theBiomeDecorator.treesPerChunk = 2;
      } else {
         this.theBiomeDecorator.treesPerChunk = 50;
      }

      this.theBiomeDecorator.grassPerChunk = 25;
      this.theBiomeDecorator.flowersPerChunk = 4;
      if (!flag) {
         this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityOcelot.class, 2, 1, 1));
      }

      this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 10, 4, 4));
   }

   @Override
   public WorldGenerator getRandomWorldGenForGrass(Random rand) {
      return rand.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
   }

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random rand) {
      return (WorldGenAbstractTree)(rand.nextInt(10) == 0
         ? this.worldGeneratorBigTree
         : (
            rand.nextInt(2) == 0
               ? new WorldGenShrub(JUNGLE_LOG, OAK_LEAF)
               : (
                  !this.isEdge && rand.nextInt(3) == 0
                     ? new WorldGenMegaJungle(false, 10, 20, JUNGLE_LOG, JUNGLE_LEAF)
                     : new WorldGenTrees(false, 4 + rand.nextInt(7), JUNGLE_LOG, JUNGLE_LEAF, true)
               )
         ));
   }
}
