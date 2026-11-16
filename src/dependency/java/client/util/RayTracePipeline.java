package client.util;

import client.setting.MovementOffsetProperty;
import java.util.Random;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.Blocks;
import net.minecraft.item.GoldenHoe;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class RayTracePipeline extends GoldenHoe {
   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos) {
      this.alreadyDecoratingManager.applyWorldByWorld(world, random, this, blockPos);
   }

   @Override
   public void applyWorld(World world, Random random, ShortArrayEntry shortArrayEntry, int count, int count2, double offset) {
      this.iBlockState = Blocks.blockGrass.getDefaultState();
      this.iBlockState2 = Blocks.block115.getDefaultState();
      if (offset > 1.75) {
         this.iBlockState = Blocks.stone.getDefaultState();
         this.iBlockState2 = Blocks.stone.getDefaultState();
      } else if (offset > -0.5) {
         this.iBlockState = Blocks.block115.getDefaultState().resolveIBlockState(BlockDirt.immutableSetEntry, MovementOffsetProperty.COARSE_DIRT);
      }

      this.applyWorld2(world, random, shortArrayEntry, count, count2, offset);
   }

   public RayTracePipeline(int count, BiomeIDIsOutOfBoundsManager biomeIDIsOutOfBoundsManager) {
      super(count, biomeIDIsOutOfBoundsManager);
      this.alreadyDecoratingManager.count9 = 2;
      this.alreadyDecoratingManager.count8 = 2;
      this.alreadyDecoratingManager.count5 = 5;
   }
}
