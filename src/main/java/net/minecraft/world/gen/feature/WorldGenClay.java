package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenClay extends WorldGenerator {
   private Block block = Blocks.clay;
   private int numberOfBlocks;

   public WorldGenClay(int n) {
      this.numberOfBlocks = n;
   }

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      if (world.getBlockState(pos).getBlock().getMaterial() != Material.water) {
         return false;
      } else {
         int n = rand.nextInt(this.numberOfBlocks - 2) + 2;
         byte b = 1;

         for (int i = pos.getX() - n; i <= pos.getX() + n; i++) {
            for (int j = pos.getZ() - n; j <= pos.getZ() + n; j++) {
               int n2 = i - pos.getX();
               int n3 = j - pos.getZ();
               if (n2 * n2 + n3 * n3 <= n * n) {
                  for (int k = pos.getY() - b; k <= pos.getY() + b; k++) {
                     BlockPos pos2 = new BlockPos(i, k, j);
                     Block block2 = world.getBlockState(pos2).getBlock();
                     if (block2 == Blocks.dirt || block2 == Blocks.clay) {
                        world.setBlockState(pos2, this.block.getDefaultState(), 2);
                     }
                  }
               }
            }
         }

         return true;
      }
   }
}
