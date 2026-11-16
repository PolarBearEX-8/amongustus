package client.util;

import client.setting.ExternalOptionType;
import client.setting.MovementColorProperty;
import client.setting.MovementOffsetProperty;
import java.util.Random;
import net.minecraft.block.AuxiliaryBlockEntry;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityWolf;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class MegaSpruceTaigaManager extends BiomeIDIsOutOfBoundsManager {
   public static final ExtendedClientManagerRegistry extendedClientManagerRegistry = new ExtendedClientManagerRegistry();
   public static final ExtendedIBlockStateEntry extendedIBlockStateEntry = new ExtendedIBlockStateEntry(false);
   public static final AdvancedIBlockStateEntry advancedIBlockStateEntry = new AdvancedIBlockStateEntry(false, false);
   public static final AdvancedIBlockStateEntry advancedIBlockStateEntry2 = new AdvancedIBlockStateEntry(false, true);
   public static final AuxiliaryBlockEntry auxiliaryBlockEntry = new AuxiliaryBlockEntry(Blocks.block131, 0);
   public int count7;

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos) {
      if (this.count7 == 1 || this.count7 == 2) {
         int count = random.nextInt(3);

         for (int index = 0; index < count; index++) {
            int count2 = random.nextInt(16) + 8;
            int count3 = random.nextInt(16) + 8;
            BlockPos blockPos2 = world.resolveBlockPos2(blockPos.add(count2, 0, count3));
            auxiliaryBlockEntry.matchesWorld(world, random, blockPos2);
         }
      }

      modularItemEntry.setExternalOptionType(ExternalOptionType.FERN);

      for (int index2 = 0; index2 < 7; index2++) {
         int count4 = random.nextInt(16) + 8;
         int count5 = random.nextInt(16) + 8;
         int count6 = random.nextInt(world.resolveBlockPos2(blockPos.add(count4, 0, count5)).getY() + 32);
         modularItemEntry.matchesWorld(world, random, blockPos.add(count4, count6, count5));
      }

      super.applyWorld(world, random, blockPos);
   }

   @Override
   public CurveParser nextIntByRandom(Random random) {
      return random.nextInt(5) > 0 ? new AuxiliaryIBlockStateEntry(MovementColorProperty.FERN) : new AuxiliaryIBlockStateEntry(MovementColorProperty.GRASS);
   }

   @Override
   public void applyWorld(World world, Random random, ShortArrayEntry shortArrayEntry, int count, int count2, double offset) {
      if (this.count7 == 1 || this.count7 == 2) {
         this.iBlockState = Blocks.blockGrass.getDefaultState();
         this.iBlockState2 = Blocks.block115.getDefaultState();
         if (offset > 1.75) {
            this.iBlockState = Blocks.block115.getDefaultState().resolveIBlockState(BlockDirt.immutableSetEntry, MovementOffsetProperty.COARSE_DIRT);
         } else if (offset > -0.95) {
            this.iBlockState = Blocks.block115.getDefaultState().resolveIBlockState(BlockDirt.immutableSetEntry, MovementOffsetProperty.PODZOL);
         }
      }

      this.applyWorld2(world, random, shortArrayEntry, count, count2, offset);
   }

   @Override
   public ClientManagerResolver nextInt(Random random) {
      return (ClientManagerResolver)((this.count7 == 1 || this.count7 == 2) && random.nextInt(3) == 0
         ? (this.count7 != 2 && random.nextInt(13) != 0 ? advancedIBlockStateEntry : advancedIBlockStateEntry2)
         : (random.nextInt(3) == 0 ? extendedClientManagerRegistry : extendedIBlockStateEntry));
   }

   @Override
   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManagerByIndex(int count) {
      return this.count == BiomeIDIsOutOfBoundsManager.biomeIDIsOutOfBoundsManager33.count
         ? new MegaSpruceTaigaManager(count, 2)
            .resolveBiomeIDIsOutOfBoundsManager(5858897, true)
            .withLabelText("Mega Spruce Taiga")
            .withCount2(5159473)
            .resolveBiomeIDIsOutOfBoundsManager(0.25F, 0.8F)
            .resolveBiomeIDIsOutOfBoundsManager(new CoreFloatEntry(this.factor2, this.factor4))
         : super.resolveBiomeIDIsOutOfBoundsManagerByIndex(count);
   }

   public MegaSpruceTaigaManager(int count, int count2) {
      super(count);
      this.count7 = count2;
      this.list4.add(new ClassHolder(EntityWolf.class, 8, 4, 4));
      this.alreadyDecoratingManager.count9 = 10;
      if (count2 != 1 && count2 != 2) {
         this.alreadyDecoratingManager.count5 = 1;
         this.alreadyDecoratingManager.count2 = 1;
      } else {
         this.alreadyDecoratingManager.count5 = 7;
         this.alreadyDecoratingManager.count = 1;
         this.alreadyDecoratingManager.count2 = 3;
      }
   }
}
