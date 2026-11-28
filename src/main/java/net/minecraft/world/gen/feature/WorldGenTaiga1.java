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

public class WorldGenTaiga1 extends WorldGenAbstractTree {
   private static final IBlockState TRUNK = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
   private static final IBlockState LEAF = Blocks.leaves
      .getDefaultState()
      .withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE)
      .withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE);

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      int n = rand.nextInt(5) + 7;
      int n2 = n - rand.nextInt(2) - 3;
      int n4 = 1 + rand.nextInt((n - n2) + 1);
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
                  if (i < 0 || i >= 256) {
                     flag = false;
                  } else if (!this.canGrowInto(world.getBlockState(pos2.set(j, i, k)).getBlock())) {
                     flag = false;
                  }
               }
            }
         }

         if (!flag) {
            return false;
         } else {
            Block block = world.getBlockState(pos.down()).getBlock();
            if ((block == Blocks.grass || block == Blocks.dirt) && pos.getY() < 256 - n - 1) {
               this.setDirtAt(world, pos.down());
               int n6 = 0;

               for (int m = pos.getY() + n; m >= pos.getY() + n2; m--) {
                  for (int idx = pos.getX() - n6; idx <= pos.getX() + n6; idx++) {
                     int n7 = idx - pos.getX();

                     for (int index = pos.getZ() - n6; index <= pos.getZ() + n6; index++) {
                        int n8 = index - pos.getZ();
                        if (Math.abs(n7) != n6 || Math.abs(n8) != n6 || n6 <= 0) {
                           BlockPos pos3 = new BlockPos(idx, m, index);
                           if (!world.getBlockState(pos3).getBlock().isFullBlock()) {
                              this.setBlockAndNotifyAdequately(world, pos3, LEAF);
                           }
                        }
                     }
                  }

                  if (n6 >= 1 && m == pos.getY() + n2 + 1) {
                     n6--;
                  } else if (n6 < n4) {
                     n6++;
                  }
               }

               for (int i2 = 0; i2 < n - 1; i2++) {
                  Block block2 = world.getBlockState(pos.up(i2)).getBlock();
                  if (block2.getMaterial() == Material.air || block2.getMaterial() == Material.leaves) {
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

   public WorldGenTaiga1() {
      super(false);
   }
}
