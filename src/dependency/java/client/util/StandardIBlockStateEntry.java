package client.util;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class StandardIBlockStateEntry extends ClientManagerResolver {
   public final int count2;
   public final IBlockState iBlockState;
   public final IBlockState iBlockState2;
   public int count;

   public void applyWorld(World world, BlockPos blockPos, int count3) {
      int count4 = count3 * count3;

      for (int index = -count3; index <= count3; index++) {
         for (int index2 = -count3; index2 <= count3; index2++) {
            if (index * index + index2 * index2 <= count4) {
               BlockPos blockPos2 = blockPos.add(index, 0, index2);
               Material material = world.getBlockState(blockPos2).getBlock().getMaterial();
               if (material == Material.coreItemEntry || material == Material.material9) {
                  this.applyWorld(world, blockPos2, this.iBlockState2);
               }
            }
         }
      }
   }

   public void applyWorld2(World world, BlockPos blockPos, int count3) {
      int count4 = count3 * count3;

      for (int index = -count3; index <= count3 + 1; index++) {
         for (int index2 = -count3; index2 <= count3 + 1; index2++) {
            int count5 = index - 1;
            int count6 = index2 - 1;
            if (index * index + index2 * index2 <= count4
               || count5 * count5 + count6 * count6 <= count4
               || index * index + count6 * count6 <= count4
               || count5 * count5 + index2 * index2 <= count4) {
               BlockPos blockPos2 = blockPos.add(index, 0, index2);
               Material material = world.getBlockState(blockPos2).getBlock().getMaterial();
               if (material == Material.coreItemEntry || material == Material.material9) {
                  this.applyWorld(world, blockPos2, this.iBlockState2);
               }
            }
         }
      }
   }

   public boolean matchesWorld(World world, Random random, BlockPos blockPos, int count3) {
      return this.matchesWorld(world, blockPos, count3) && this.matchesBlockPosByBlockPos(blockPos, world);
   }

   private boolean matchesBlockPosByBlockPos(BlockPos blockPos, World world) {
      BlockPos blockPos2 = blockPos.down();
      Block block = world.getBlockState(blockPos2).getBlock();
      if ((block == Blocks.blockGrass || block == Blocks.block115) && blockPos.getY() >= 2) {
         this.applyWorldByWorld(world, blockPos2);
         this.applyWorldByWorld(world, blockPos2.getBlockPos3());
         this.applyWorldByWorld(world, blockPos2.getBlockPos2());
         this.applyWorldByWorld(world, blockPos2.getBlockPos2().getBlockPos3());
         return true;
      } else {
         return false;
      }
   }

   private boolean matchesWorld(World world, BlockPos blockPos, int count3) {
      boolean flag = true;
      if (blockPos.getY() >= 1 && blockPos.getY() + count3 + 1 <= 256) {
         for (int index = 0; index <= 1 + count3; index++) {
            byte byteValue = 2;
            if (index == 0) {
               byteValue = 1;
            } else if (index >= 1 + count3 - 2) {
               byteValue = 2;
            }

            for (int index2 = -byteValue; index2 <= byteValue && flag; index2++) {
               for (int index3 = -byteValue; index3 <= byteValue && flag; index3++) {
                  if (blockPos.getY() + index < 0 || blockPos.getY() + index >= 256 || !this.matchesBlock(world.getBlockState(blockPos.add(index2, index, index3)).getBlock())) {
                     flag = false;
                  }
               }
            }
         }

         return flag;
      } else {
         return false;
      }
   }

   public int computeCount(Random random) {
      int count3 = random.nextInt(3) + this.count2;
      if (this.count > 1) {
         count3 += random.nextInt(this.count);
      }

      return count3;
   }

   public StandardIBlockStateEntry(boolean flag, int count3, int count4, IBlockState blockState, IBlockState blockState2) {
      super(flag);
      this.count2 = count3;
      this.count = count4;
      this.iBlockState = blockState;
      this.iBlockState2 = blockState2;
   }
}
