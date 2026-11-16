package client.util;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class InternalIBlockStateEntry extends SecondaryIBlockStateEntry {
   public final IBlockState iBlockState5;
   public final IBlockState iBlockState6;

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      BlockPos blockPos2 = blockPos;

      Block block;
      while (
         ((block = world.getBlockState(blockPos2).getBlock()).getMaterial() == Material.coreItemEntry || block.getMaterial() == Material.material9)
            && blockPos2.getY() > 0
      ) {
         blockPos2 = blockPos2.down();
      }

      Block block2 = world.getBlockState(blockPos2).getBlock();
      if (block2 == Blocks.block115 || block2 == Blocks.blockGrass) {
         blockPos2 = blockPos2.up();
         this.applyWorld(world, blockPos2, this.iBlockState6);

         for (int index = blockPos2.getY(); index <= blockPos2.getY() + 2; index++) {
            int count = index - blockPos2.getY();
            int count2 = 2 - count;

            for (int index2 = blockPos2.getX() - count2; index2 <= blockPos2.getX() + count2; index2++) {
               int count3 = index2 - blockPos2.getX();

               for (int index3 = blockPos2.getZ() - count2; index3 <= blockPos2.getZ() + count2; index3++) {
                  int count4 = index3 - blockPos2.getZ();
                  if (Math.abs(count3) != count2 || Math.abs(count4) != count2 || random.nextInt(2) != 0) {
                     BlockPos blockPos3 = new BlockPos(index2, index, index3);
                     if (!world.getBlockState(blockPos3).getBlock().isState18()) {
                        this.applyWorld(world, blockPos3, this.iBlockState5);
                     }
                  }
               }
            }
         }
      }

      return true;
   }

   public InternalIBlockStateEntry(IBlockState blockState, IBlockState blockState2) {
      super(false);
      this.iBlockState6 = blockState;
      this.iBlockState5 = blockState2;
   }
}
