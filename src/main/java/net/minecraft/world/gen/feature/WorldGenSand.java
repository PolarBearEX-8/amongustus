package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenSand extends WorldGenerator {
   private Block block;
   private int radius;

   public WorldGenSand(Block block2, int n) {
      this.block = block2;
      this.radius = n;
   }

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      if (world.getBlockState(pos).getBlock().getMaterial() != Material.water) {
         return false;
      } else {
         int n = rand.nextInt(this.radius - 2) + 2;
         byte b = 2;

         for (int i = pos.getX() - n; i <= pos.getX() + n; i++) {
            for (int j = pos.getZ() - n; j <= pos.getZ() + n; j++) {
               int n2 = i - pos.getX();
               int n3 = j - pos.getZ();
               if (n2 * n2 + n3 * n3 <= n * n) {
                  for (int k = pos.getY() - b; k <= pos.getY() + b; k++) {
                     BlockPos pos2 = new BlockPos(i, k, j);
                     Block block2 = world.getBlockState(pos2).getBlock();
                     if (block2 == Blocks.dirt || block2 == Blocks.grass) {
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
