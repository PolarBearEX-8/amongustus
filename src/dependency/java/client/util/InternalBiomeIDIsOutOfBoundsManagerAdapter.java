package client.util;

import client.setting.DefaultOptionType;
import java.util.Random;
import net.minecraft.block.BlockMonsterEgg;
import net.minecraft.block.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class InternalBiomeIDIsOutOfBoundsManagerAdapter extends BiomeIDIsOutOfBoundsManager {
   public CurveParser curveParser = new IBlockStateEntry(
      Blocks.block22.getDefaultState().resolveIBlockState(BlockMonsterEgg.immutableSetEntry, DefaultOptionType.STONE), 9
   );
   public ExtendedIBlockStateEntry extendedIBlockStateEntry = new ExtendedIBlockStateEntry(false);
   public int count8 = 0;
   public int count6 = 1;
   public int count7 = 2;
   public int count9 = this.count8;

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos) {
      super.applyWorld(world, random, blockPos);
      int count = 3 + random.nextInt(6);

      for (int index = 0; index < count; index++) {
         int count2 = random.nextInt(16);
         int count3 = random.nextInt(28) + 4;
         int count4 = random.nextInt(16);
         BlockPos blockPos2 = blockPos.add(count2, count3, count4);
         if (world.getBlockState(blockPos2).getBlock() == Blocks.stone) {
            world.matchesBlockPos(blockPos2, Blocks.block70.getDefaultState(), 2);
         }
      }

      for (int index2 = 0; index2 < 7; index2++) {
         int count5 = random.nextInt(16);
         int count10 = random.nextInt(64);
         int count11 = random.nextInt(16);
         this.curveParser.matchesWorld(world, random, blockPos.add(count5, count10, count11));
      }
   }

   @Override
   public void applyWorld(World world, Random random, ShortArrayEntry shortArrayEntry, int count, int count2, double offset) {
      this.iBlockState = Blocks.blockGrass.getDefaultState();
      this.iBlockState2 = Blocks.block115.getDefaultState();
      if ((offset < -1.0 || offset > 2.0) && this.count9 == this.count7) {
         this.iBlockState = Blocks.block45.getDefaultState();
         this.iBlockState2 = Blocks.block45.getDefaultState();
      } else if (offset > 1.0 && this.count9 != this.count6) {
         this.iBlockState = Blocks.stone.getDefaultState();
         this.iBlockState2 = Blocks.stone.getDefaultState();
      }

      this.applyWorld2(world, random, shortArrayEntry, count, count2, offset);
   }

   private InternalBiomeIDIsOutOfBoundsManagerAdapter resolveInternalBiomeIDIsOutOfBounds(BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager) {
      this.count9 = this.count7;
      this.resolveBiomeIDIsOutOfBoundsManager(biomeIDIsOutOfBoundsManager.count3, true);
      this.withLabelText(biomeIDIsOutOfBoundsManager.labelText + " M");
      this.resolveBiomeIDIsOutOfBoundsManager(new CoreFloatEntry(biomeIDIsOutOfBoundsManager.factor2, biomeIDIsOutOfBoundsManager.factor4));
      this.resolveBiomeIDIsOutOfBoundsManager(biomeIDIsOutOfBoundsManager.factor, biomeIDIsOutOfBoundsManager.factor3);
      return this;
   }

   @Override
   public ClientManagerResolver nextInt(Random random) {
      return (ClientManagerResolver)(random.nextInt(3) > 0 ? this.extendedIBlockStateEntry : super.nextInt(random));
   }

   @Override
   public BiomeIDIsOutOfBoundsManager resolveBiomeIDIsOutOfBoundsManagerByIndex(int count) {
      return new InternalBiomeIDIsOutOfBoundsManagerAdapter(count, false).resolveInternalBiomeIDIsOutOfBounds(this);
   }

   public InternalBiomeIDIsOutOfBoundsManagerAdapter(int count, boolean flag) {
      super(count);
      if (flag) {
         this.alreadyDecoratingManager.count9 = 3;
         this.count9 = this.count6;
      }
   }
}
