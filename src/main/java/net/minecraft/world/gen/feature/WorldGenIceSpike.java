package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WorldGenIceSpike extends WorldGenerator {
   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      while (world.isAirBlock(pos) && pos.getY() > 2) {
         pos = pos.down();
      }

      if (world.getBlockState(pos).getBlock() != Blocks.snow) {
         return false;
      } else {
         pos = pos.up(rand.nextInt(4));
         int n = rand.nextInt(4) + 7;
         int n2 = n / 4 + rand.nextInt(2);
         if (n2 > 1 && rand.nextInt(60) == 0) {
            pos = pos.up(10 + rand.nextInt(30));
         }

         for (int i = 0; i < n; i++) {
            float f = (1.0F - (float)i / (float)n) * (float)n2;
            int n3 = MathHelper.ceiling_float_int(f);

            for (int j = -n3; j <= n3; j++) {
               float f2 = (float)MathHelper.abs_int(j) - 0.25F;

               for (int k = -n3; k <= n3; k++) {
                  float f3 = (float)MathHelper.abs_int(k) - 0.25F;
                  if ((j == 0 && k == 0 || f2 * f2 + f3 * f3 <= f * f)
                     && (j != -n3 && j != n3 && k != -n3 && k != n3 || rand.nextFloat() <= 0.75F)) {
                     Block block = world.getBlockState(pos.add(j, i, k)).getBlock();
                     if (block.getMaterial() == Material.air || block == Blocks.dirt || block == Blocks.snow || block == Blocks.ice) {
                        this.setBlockAndNotifyAdequately(world, pos.add(j, i, k), Blocks.packed_ice.getDefaultState());
                     }

                     if (i != 0 && n3 > 1) {
                        block = world.getBlockState(pos.add(j, -i, k)).getBlock();
                        if (block.getMaterial() == Material.air || block == Blocks.dirt || block == Blocks.snow || block == Blocks.ice) {
                           this.setBlockAndNotifyAdequately(world, pos.add(j, -i, k), Blocks.packed_ice.getDefaultState());
                        }
                     }
                  }
               }
            }
         }

         int n4 = n2 - 1;
         if (n4 < 0) {
            n4 = 0;
         } else if (n4 > 1) {
            n4 = 1;
         }

         for (int m = -n4; m <= n4; m++) {
            for (int idx = -n4; idx <= n4; idx++) {
               BlockPos pos2 = pos.add(m, -1, idx);
               int n5 = 50;
               if (Math.abs(m) == 1 && Math.abs(idx) == 1) {
                  n5 = rand.nextInt(5);
               }

               while (pos2.getY() > 50) {
                  Block block2 = world.getBlockState(pos2).getBlock();
                  if (block2.getMaterial() != Material.air && block2 != Blocks.dirt && block2 != Blocks.snow && block2 != Blocks.ice && block2 != Blocks.packed_ice) {
                     break;
                  }

                  this.setBlockAndNotifyAdequately(world, pos2, Blocks.packed_ice.getDefaultState());
                  pos2 = pos2.down();
                  if (--n5 <= 0) {
                     pos2 = pos2.down(rand.nextInt(5) + 1);
                     n5 = rand.nextInt(5);
                  }
               }
            }
         }

         return true;
      }
   }
}
