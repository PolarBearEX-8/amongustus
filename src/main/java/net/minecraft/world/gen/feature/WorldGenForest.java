package net.minecraft.world.gen.feature;

import net.minecraft.block.BlockPlanks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenForest extends WorldGenAbstractTree {
   private static final IBlockState LOG = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);
   private static final IBlockState LEAF = Blocks.leaves
      .getDefaultState()
      .withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.BIRCH)
      .withProperty(BlockOldLeaf.CHECK_DECAY, Boolean.FALSE);
   private boolean useExtraRandomHeight;

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      int n = rand.nextInt(3) + 5;
      if (this.useExtraRandomHeight) {
         n += rand.nextInt(7);
      }

      boolean flag = true;
      if (pos.getY() >= 1 && pos.getY() + n + 1 <= 256) {
         for (int i = pos.getY(); i <= pos.getY() + 1 + n; i++) {
            byte b = 1;
            if (i == pos.getY()) {
               b = 0;
            }

            if (i >= pos.getY() + 1 + n - 2) {
               b = 2;
            }

            BlockPos.MutableBlockPos pos2 = new BlockPos.MutableBlockPos();

            for (int j = pos.getX() - b; j <= pos.getX() + b && flag; j++) {
               for (int k = pos.getZ() - b; k <= pos.getZ() + b && flag; k++) {
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
            if ((block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland) && pos.getY() < 256 - n - 1) {
               this.setDirtAt(world, pos.down());

               for (int m = pos.getY() - 3 + n; m <= pos.getY() + n; m++) {
                  int n2 = m - (pos.getY() + n);
                  int n3 = 1 - n2 / 2;

                  for (int idx = pos.getX() - n3; idx <= pos.getX() + n3; idx++) {
                     int n4 = idx - pos.getX();

                     for (int index = pos.getZ() - n3; index <= pos.getZ() + n3; index++) {
                        int n5 = index - pos.getZ();
                        if (Math.abs(n4) != n3 || Math.abs(n5) != n3 || rand.nextInt(2) != 0 && n2 != 0) {
                           BlockPos pos3 = new BlockPos(idx, m, index);
                           Block block2 = world.getBlockState(pos3).getBlock();
                           if (block2.getMaterial() == Material.air || block2.getMaterial() == Material.leaves) {
                              this.setBlockAndNotifyAdequately(world, pos3, LEAF);
                           }
                        }
                     }
                  }
               }

               for (int i2 = 0; i2 < n; i2++) {
                  Block block3 = world.getBlockState(pos.up(i2)).getBlock();
                  if (block3.getMaterial() == Material.air || block3.getMaterial() == Material.leaves) {
                     this.setBlockAndNotifyAdequately(world, pos.up(i2), LOG);
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

   public WorldGenForest(boolean flag, boolean flag2) {
      super(flag);
      this.useExtraRandomHeight = flag2;
   }
}
