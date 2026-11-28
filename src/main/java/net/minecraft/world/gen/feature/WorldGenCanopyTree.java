package net.minecraft.world.gen.feature;

import net.minecraft.block.BlockPlanks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class WorldGenCanopyTree extends WorldGenAbstractTree {
   private static final IBlockState DARK_OAK_LOG = Blocks.log2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);
   private static final IBlockState DARK_OAK_LEAVES = Blocks.leaves2
      .getDefaultState()
      .withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.DARK_OAK)
      .withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE);

   private void placeLeafAt(World world, int n, int n2, int n3) {
      BlockPos pos = new BlockPos(n, n2, n3);
      if (world.getBlockState(pos).getBlock().getMaterial() == Material.air) {
         this.setBlockAndNotifyAdequately(world, pos, DARK_OAK_LEAVES);
      }
   }

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      int n = rand.nextInt(3) + rand.nextInt(2) + 6;
      int x = pos.getX();
      int y = pos.getY();
      int z = pos.getZ();
      if (y >= 1 && y + n + 1 < 256) {
         BlockPos pos2 = pos.down();
         Block block = world.getBlockState(pos2).getBlock();
         if (block != Blocks.grass && block != Blocks.dirt) {
            return false;
         } else if (!this.placeTreeOfHeight(world, pos, n)) {
            return false;
         } else {
            this.setDirtAt(world, pos2);
            this.setDirtAt(world, pos2.east());
            this.setDirtAt(world, pos2.south());
            this.setDirtAt(world, pos2.south().east());
            EnumFacing facing = EnumFacing.Plane.HORIZONTAL.random(rand);
            int n2 = n - rand.nextInt(4);
            int n3 = 2 - rand.nextInt(3);
            int n4 = x;
            int n5 = z;
            int n6 = y + n - 1;

            for (int i = 0; i < n; i++) {
               if (i >= n2 && n3 > 0) {
                  n4 += facing.getFrontOffsetX();
                  n5 += facing.getFrontOffsetZ();
                  n3--;
               }

               int n7 = y + i;
               BlockPos pos3 = new BlockPos(n4, n7, n5);
               Material material = world.getBlockState(pos3).getBlock().getMaterial();
               if (material == Material.air || material == Material.leaves) {
                  this.placeLogAt(world, pos3);
                  this.placeLogAt(world, pos3.east());
                  this.placeLogAt(world, pos3.south());
                  this.placeLogAt(world, pos3.east().south());
               }
            }

            for (int j = -2; j <= 0; j++) {
               for (int k = -2; k <= 0; k++) {
                  byte b = -1;
                  this.placeLeafAt(world, n4 + j, n6 + b, n5 + k);
                  this.placeLeafAt(world, 1 + n4 - j, n6 + b, n5 + k);
                  this.placeLeafAt(world, n4 + j, n6 + b, 1 + n5 - k);
                  this.placeLeafAt(world, 1 + n4 - j, n6 + b, 1 + n5 - k);
                  if ((j > -2 || k > -1) && (j != -1 || k != -2)) {
                     b = 1;
                     this.placeLeafAt(world, n4 + j, n6 + b, n5 + k);
                     this.placeLeafAt(world, 1 + n4 - j, n6 + b, n5 + k);
                     this.placeLeafAt(world, n4 + j, n6 + b, 1 + n5 - k);
                     this.placeLeafAt(world, 1 + n4 - j, n6 + b, 1 + n5 - k);
                  }
               }
            }

            if (rand.nextBoolean()) {
               this.placeLeafAt(world, n4, n6 + 2, n5);
               this.placeLeafAt(world, n4 + 1, n6 + 2, n5);
               this.placeLeafAt(world, n4 + 1, n6 + 2, n5 + 1);
               this.placeLeafAt(world, n4, n6 + 2, n5 + 1);
            }

            for (int m = -3; m <= 4; m++) {
               for (int idx = -3; idx <= 4; idx++) {
                  if ((m != -3 || idx != -3)
                     && (m != -3 || idx != 4)
                     && (m != 4 || idx != -3)
                     && (m != 4 || idx != 4)
                     && (Math.abs(m) < 3 || Math.abs(idx) < 3)) {
                     this.placeLeafAt(world, n4 + m, n6, n5 + idx);
                  }
               }
            }

            for (int index = -1; index <= 2; index++) {
               for (int i2 = -1; i2 <= 2; i2++) {
                  if ((index < 0 || index > 1 || i2 < 0 || i2 > 1) && rand.nextInt(3) <= 0) {
                     int n8 = rand.nextInt(3) + 2;

                     for (int i3 = 0; i3 < n8; i3++) {
                        this.placeLogAt(world, new BlockPos(x + index, n6 - i3 - 1, z + i2));
                     }

                     for (int i4 = -1; i4 <= 1; i4++) {
                        for (int i5 = -1; i5 <= 1; i5++) {
                           this.placeLeafAt(world, n4 + index + i4, n6, n5 + i2 + i5);
                        }
                     }

                     for (int i6 = -2; i6 <= 2; i6++) {
                        for (int i7 = -2; i7 <= 2; i7++) {
                           if (Math.abs(i6) != 2 || Math.abs(i7) != 2) {
                              this.placeLeafAt(world, n4 + index + i6, n6 - 1, n5 + i2 + i7);
                           }
                        }
                     }
                  }
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   private boolean placeTreeOfHeight(World world, BlockPos pos, int n) {
      int x = pos.getX();
      int y = pos.getY();
      int z = pos.getZ();
      BlockPos.MutableBlockPos pos2 = new BlockPos.MutableBlockPos();

      for (int i = 0; i <= n + 1; i++) {
         byte b = 1;
         if (i == 0) {
            b = 0;
         }

         if (i >= n - 1) {
            b = 2;
         }

         for (int j = -b; j <= b; j++) {
            for (int k = -b; k <= b; k++) {
               if (!this.canGrowInto(world.getBlockState(pos2.set(x + j, y + i, z + k)).getBlock())) {
                  return false;
               }
            }
         }
      }

      return true;
   }

   private void placeLogAt(World world, BlockPos pos) {
      if (this.canGrowInto(world.getBlockState(pos).getBlock())) {
         this.setBlockAndNotifyAdequately(world, pos, DARK_OAK_LOG);
      }
   }

   public WorldGenCanopyTree(boolean flag) {
      super(flag);
   }
}
