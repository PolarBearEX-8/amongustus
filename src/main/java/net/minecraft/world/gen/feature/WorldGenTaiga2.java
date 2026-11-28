package net.minecraft.world.gen.feature;

import net.minecraft.block.BlockPlanks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenTaiga2 extends WorldGenAbstractTree {
   private static final IBlockState TRUNK = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
   private static final IBlockState LEAF = Blocks.leaves
      .getDefaultState()
      .withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE)
      .withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE);

   public WorldGenTaiga2(boolean flag) {
      super(flag);
   }

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      int n = rand.nextInt(4) + 6;
      int n2 = 1 + rand.nextInt(2);
      int n3 = n - n2;
      int n4 = 2 + rand.nextInt(2);
      boolean flag = true;
      if (pos.getY() >= 1 && pos.getY() + n + 1 <= 256) {
         for (int i = pos.getY(); i <= pos.getY() + 1 + n && flag; i++) {
            int n5 = 1;
            if (i - pos.getY() < n2) {
               n5 = 0;
            } else {
               n5 = n4;
            }

            BlockPos.MutableBlockPos pos2 = new BlockPos.MutableBlockPos();

            for (int j = pos.getX() - n5; j <= pos.getX() + n5 && flag; j++) {
               for (int k = pos.getZ() - n5; k <= pos.getZ() + n5 && flag; k++) {
                  if (i >= 0 && i < 256) {
                     Block block = world.getBlockState(pos2.set(j, i, k)).getBlock();
                     if (block.getMaterial() != Material.air && block.getMaterial() != Material.leaves) {
                        flag = false;
                     }
                  } else {
                     flag = false;
                  }
               }
            }
         }

         if (!flag) {
            return false;
         } else {
            Block block2 = world.getBlockState(pos.down()).getBlock();
            if ((block2 == Blocks.grass || block2 == Blocks.dirt || block2 == Blocks.farmland) && pos.getY() < 256 - n - 1) {
               this.setDirtAt(world, pos.down());
               int n6 = rand.nextInt(2);
               int n7 = 1;
               byte b = 0;

               for (int m = 0; m <= n3; m++) {
                  int n8 = pos.getY() + n - m;

                  for (int idx = pos.getX() - n6; idx <= pos.getX() + n6; idx++) {
                     int n9 = idx - pos.getX();

                     for (int index = pos.getZ() - n6; index <= pos.getZ() + n6; index++) {
                        int n10 = index - pos.getZ();
                        if (Math.abs(n9) != n6 || Math.abs(n10) != n6 || n6 <= 0) {
                           BlockPos pos3 = new BlockPos(idx, n8, index);
                           if (!world.getBlockState(pos3).getBlock().isFullBlock()) {
                              this.setBlockAndNotifyAdequately(world, pos3, LEAF);
                           }
                        }
                     }
                  }

                  if (n6 >= n7) {
                     n6 = b;
                     b = 1;
                     if (++n7 > n4) {
                        n7 = n4;
                     }
                  } else {
                     n6++;
                  }
               }

               int n11 = rand.nextInt(3);

               for (int i2 = 0; i2 < n - n11; i2++) {
                  Block block3 = world.getBlockState(pos.up(i2)).getBlock();
                  if (block3.getMaterial() == Material.air || block3.getMaterial() == Material.leaves) {
                     this.setBlockAndNotifyAdequately(world, pos.up(i2), TRUNK);
                  }
               }

               return true;
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }
}
