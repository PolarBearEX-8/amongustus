package client.util;

import java.util.Random;
import net.minecraft.block.BlockVine;
import net.minecraft.block.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class IBlockStateEntryBridge extends StandardIBlockStateEntry {
   private void applyWorld(World world, Random random, BlockPos blockPos, InternalImmutableSetEntry internalImmutableSetEntry) {
      if (random.nextInt(3) > 0 && world.isAirBlock(blockPos)) {
         this.applyWorld(world, blockPos, Blocks.block144.getDefaultState().resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE));
      }
   }

   private void applyWorld4(World world, BlockPos blockPos, int count) {
      for (int index = -2; index <= 0; index++) {
         this.applyWorld2(world, blockPos.resolveBlockPos2(index), count + 1 - index);
      }
   }

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      int count = this.computeCount(random);
      if (!this.matchesWorld(world, random, blockPos, count)) {
         return false;
      } else {
         this.applyWorld4(world, blockPos.resolveBlockPos2(count), 2);

         for (int index = blockPos.getY() + count - 2 - random.nextInt(4); index > blockPos.getY() + count / 2; index -= 2 + random.nextInt(4)) {
            float factor = random.nextFloat() * (float) Math.PI * 2.0F;
            int count2 = blockPos.getX() + (int)(0.5F + MathHelper.cos(factor) * 4.0F);
            int count3 = blockPos.getZ() + (int)(0.5F + MathHelper.abs(factor) * 4.0F);

            for (int index2 = 0; index2 < 5; index2++) {
               count2 = blockPos.getX() + (int)(1.5F + MathHelper.cos(factor) * (float)index2);
               count3 = blockPos.getZ() + (int)(1.5F + MathHelper.abs(factor) * (float)index2);
               this.applyWorld(world, new BlockPos(count2, index - 3 + index2 / 2, count3), this.iBlockState);
            }

            int count4 = 1 + random.nextInt(2);

            for (int index3 = index - count4; index3 <= index; index3++) {
               int count5 = index3 - index;
               this.applyWorld(world, new BlockPos(count2, index3, count3), 1 - count5);
            }
         }

         for (int index4 = 0; index4 < count; index4++) {
            BlockPos blockPos2 = blockPos.resolveBlockPos2(index4);
            if (this.matchesBlock(world.getBlockState(blockPos2).getBlock())) {
               this.applyWorld(world, blockPos2, this.iBlockState);
               if (index4 > 0) {
                  this.applyWorld(world, random, blockPos2.getBlockPos(), BlockVine.internalImmutableSetEntry4);
                  this.applyWorld(world, random, blockPos2.getBlockPosAsBlockPos(), BlockVine.internalImmutableSetEntry2);
               }
            }

            if (index4 < count - 1) {
               BlockPos blockPos3 = blockPos2.getBlockPos3();
               if (this.matchesBlock(world.getBlockState(blockPos3).getBlock())) {
                  this.applyWorld(world, blockPos3, this.iBlockState);
                  if (index4 > 0) {
                     this.applyWorld(world, random, blockPos3.getBlockPos3(), BlockVine.internalImmutableSetEntry3);
                     this.applyWorld(world, random, blockPos3.getBlockPosAsBlockPos(), BlockVine.internalImmutableSetEntry2);
                  }
               }

               BlockPos blockPos4 = blockPos2.getBlockPos2().getBlockPos3();
               if (this.matchesBlock(world.getBlockState(blockPos4).getBlock())) {
                  this.applyWorld(world, blockPos4, this.iBlockState);
                  if (index4 > 0) {
                     this.applyWorld(world, random, blockPos4.getBlockPos3(), BlockVine.internalImmutableSetEntry3);
                     this.applyWorld(world, random, blockPos4.getBlockPos2(), BlockVine.internalImmutableSetEntry);
                  }
               }

               BlockPos blockPos5 = blockPos2.getBlockPos2();
               if (this.matchesBlock(world.getBlockState(blockPos5).getBlock())) {
                  this.applyWorld(world, blockPos5, this.iBlockState);
                  if (index4 > 0) {
                     this.applyWorld(world, random, blockPos5.getBlockPos(), BlockVine.internalImmutableSetEntry4);
                     this.applyWorld(world, random, blockPos5.getBlockPos2(), BlockVine.internalImmutableSetEntry);
                  }
               }
            }
         }

         return true;
      }
   }

   public IBlockStateEntryBridge(boolean flag, int count, int count2, IBlockState blockState, IBlockState blockState2) {
      super(flag, count, count2, blockState, blockState2);
   }
}
