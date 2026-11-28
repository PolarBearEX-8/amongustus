package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class WorldGenHugeTrees extends WorldGenAbstractTree {
   public final int baseHeight;
   public final IBlockState woodMetadata;
   public final IBlockState leavesMetadata;
   public int extraRandomHeight;

   public void growLeavesLayerStrict(World world, BlockPos pos, int n) {
      int n2 = n * n;

      for (int i = -n; i <= n + 1; i++) {
         for (int j = -n; j <= n + 1; j++) {
            int n3 = i - 1;
            int n4 = j - 1;
            if (i * i + j * j <= n2
               || n3 * n3 + n4 * n4 <= n2
               || i * i + n4 * n4 <= n2
               || n3 * n3 + j * j <= n2) {
               BlockPos pos2 = pos.add(i, 0, j);
               Material material = world.getBlockState(pos2).getBlock().getMaterial();
               if (material == Material.air || material == Material.leaves) {
                  this.setBlockAndNotifyAdequately(world, pos2, this.leavesMetadata);
               }
            }
         }
      }
   }

   public WorldGenHugeTrees(boolean flag, int n, int n2, IBlockState state, IBlockState state2) {
      super(flag);
      this.baseHeight = n;
      this.extraRandomHeight = n2;
      this.woodMetadata = state;
      this.leavesMetadata = state2;
   }

   public void growLeavesLayer(World world, BlockPos pos, int n) {
      int n2 = n * n;

      for (int i = -n; i <= n; i++) {
         for (int j = -n; j <= n; j++) {
            if (i * i + j * j <= n2) {
               BlockPos pos2 = pos.add(i, 0, j);
               Material material = world.getBlockState(pos2).getBlock().getMaterial();
               if (material == Material.air || material == Material.leaves) {
                  this.setBlockAndNotifyAdequately(world, pos2, this.leavesMetadata);
               }
            }
         }
      }
   }

   private boolean ensureDirtsUnderneath(BlockPos pos, World world) {
      BlockPos pos2 = pos.down();
      Block block = world.getBlockState(pos2).getBlock();
      if ((block == Blocks.grass || block == Blocks.dirt) && pos.getY() >= 2) {
         this.setDirtAt(world, pos2);
         this.setDirtAt(world, pos2.east());
         this.setDirtAt(world, pos2.south());
         this.setDirtAt(world, pos2.south().east());
         return true;
      } else {
         return false;
      }
   }

   public boolean ensureGrowable(World world, Random rand, BlockPos pos, int n) {
      return this.isSpaceAt(world, pos, n) && this.ensureDirtsUnderneath(pos, world);
   }

   private boolean isSpaceAt(World world, BlockPos pos, int n) {
      boolean flag = true;
      if (pos.getY() >= 1 && pos.getY() + n + 1 <= 256) {
         for (int i = 0; i <= 1 + n; i++) {
            byte b = 2;
            if (i == 0) {
               b = 1;
            } else if (i >= 1 + n - 2) {
               b = 2;
            }

            for (int j = -b; j <= b && flag; j++) {
               for (int k = -b; k <= b && flag; k++) {
                  if (pos.getY() + i < 0 || pos.getY() + i >= 256 || !this.canGrowInto(world.getBlockState(pos.add(j, i, k)).getBlock())) {
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

   public int getHeight(Random rand) {
      int n = rand.nextInt(3) + this.baseHeight;
      if (this.extraRandomHeight > 1) {
         n += rand.nextInt(this.extraRandomHeight);
      }

      return n;
   }
}
