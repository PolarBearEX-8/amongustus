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

public class WorldGenSavannaTree extends WorldGenAbstractTree {
   private static final IBlockState TRUNK = Blocks.log2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA);
   private static final IBlockState LEAF = Blocks.leaves2
      .getDefaultState()
      .withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.ACACIA)
      .withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE);

   public WorldGenSavannaTree(boolean flag) {
      super(flag);
   }

   private void placeLeafAt(World world, BlockPos pos) {
      Material material = world.getBlockState(pos).getBlock().getMaterial();
      if (material == Material.air || material == Material.leaves) {
         this.setBlockAndNotifyAdequately(world, pos, LEAF);
      }
   }

   private void placeLogAt(World world, BlockPos pos) {
      this.setBlockAndNotifyAdequately(world, pos, TRUNK);
   }

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      int n = rand.nextInt(3) + rand.nextInt(3) + 5;
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
            if ((block == Blocks.grass || block == Blocks.dirt) && pos.getY() < 256 - n - 1) {
               this.setDirtAt(world, pos.down());
               EnumFacing facing = EnumFacing.Plane.HORIZONTAL.random(rand);
               int n2 = n - rand.nextInt(4) - 1;
               int n3 = 3 - rand.nextInt(3);
               int x = pos.getX();
               int z = pos.getZ();
               int n4 = 0;

               for (int m = 0; m < n; m++) {
                  int n5 = pos.getY() + m;
                  if (m >= n2 && n3 > 0) {
                     x += facing.getFrontOffsetX();
                     z += facing.getFrontOffsetZ();
                     n3--;
                  }

                  BlockPos pos3 = new BlockPos(x, n5, z);
                  Material material = world.getBlockState(pos3).getBlock().getMaterial();
                  if (material == Material.air || material == Material.leaves) {
                     this.placeLogAt(world, pos3);
                     n4 = n5;
                  }
               }

               BlockPos pos4 = new BlockPos(x, n4, z);

               for (int idx = -3; idx <= 3; idx++) {
                  for (int index = -3; index <= 3; index++) {
                     if (Math.abs(idx) != 3 || Math.abs(index) != 3) {
                        this.placeLeafAt(world, pos4.add(idx, 0, index));
                     }
                  }
               }

               pos4 = pos4.up();

               for (int i2 = -1; i2 <= 1; i2++) {
                  for (int i3 = -1; i3 <= 1; i3++) {
                     this.placeLeafAt(world, pos4.add(i2, 0, i3));
                  }
               }

               this.placeLeafAt(world, pos4.east(2));
               this.placeLeafAt(world, pos4.west(2));
               this.placeLeafAt(world, pos4.south(2));
               this.placeLeafAt(world, pos4.north(2));
               x = pos.getX();
               z = pos.getZ();
               EnumFacing facing2 = EnumFacing.Plane.HORIZONTAL.random(rand);
               if (facing2 != facing) {
                  int n6 = n2 - rand.nextInt(2) - 1;
                  int n7 = 1 + rand.nextInt(3);
                  n4 = 0;

                  for (int i4 = n6; i4 < n && n7 > 0; n7--) {
                     if (i4 >= 1) {
                        int n8 = pos.getY() + i4;
                        x += facing2.getFrontOffsetX();
                        z += facing2.getFrontOffsetZ();
                        BlockPos pos5 = new BlockPos(x, n8, z);
                        Material material2 = world.getBlockState(pos5).getBlock().getMaterial();
                        if (material2 == Material.air || material2 == Material.leaves) {
                           this.placeLogAt(world, pos5);
                           n4 = n8;
                        }
                     }

                     i4++;
                  }

                  if (n4 > 0) {
                     BlockPos pos6 = new BlockPos(x, n4, z);

                     for (int i5 = -2; i5 <= 2; i5++) {
                        for (int i6 = -2; i6 <= 2; i6++) {
                           if (Math.abs(i5) != 2 || Math.abs(i6) != 2) {
                              this.placeLeafAt(world, pos6.add(i5, 0, i6));
                           }
                        }
                     }

                     pos6 = pos6.up();

                     for (int i7 = -1; i7 <= 1; i7++) {
                        for (int i8 = -1; i8 <= 1; i8++) {
                           this.placeLeafAt(world, pos6.add(i7, 0, i8));
                        }
                     }
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
