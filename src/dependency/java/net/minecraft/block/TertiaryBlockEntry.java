package net.minecraft.block;

import client.util.CurveParser;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class TertiaryBlockEntry extends CurveParser {
   public Block block;
   public int count;

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      if (world.getBlockState(blockPos).getBlock().getMaterial() != Material.coreItemEntry1) {
         return false;
      } else {
         int count2 = random.nextInt(this.count - 2) + 2;

         for (int index = blockPos.getX() - count2; index <= blockPos.getX() + count2; index++) {
            for (int index2 = blockPos.getZ() - count2; index2 <= blockPos.getZ() + count2; index2++) {
               int count3 = index - blockPos.getX();
               int count4 = index2 - blockPos.getZ();
               if (count3 * count3 + count4 * count4 <= count2 * count2) {
                  for (int index3 = blockPos.getY() - 2; index3 <= blockPos.getY() + 2; index3++) {
                     BlockPos blockPos2 = new BlockPos(index, index3, index2);
                     Block block2 = world.getBlockState(blockPos2).getBlock();
                     if (block2 == Blocks.block115 || block2 == Blocks.blockGrass) {
                        world.matchesBlockPos(blockPos2, this.block.getDefaultState(), 2);
                     }
                  }
               }
            }
         }

         return true;
      }
   }

   public TertiaryBlockEntry(Block block2, int count2) {
      this.block = block2;
      this.count = count2;
   }
}
