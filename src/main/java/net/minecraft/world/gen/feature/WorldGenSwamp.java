package net.minecraft.world.gen.feature;

import net.minecraft.block.BlockPlanks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenSwamp extends WorldGenAbstractTree {
   private static final IBlockState TRUNK = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);
   private static final IBlockState LEAF = Blocks.leaves
      .getDefaultState()
      .withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK)
      .withProperty(BlockOldLeaf.CHECK_DECAY, Boolean.FALSE);

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      int n = rand.nextInt(4) + 5;

      while (world.getBlockState(pos.down()).getBlock().getMaterial() == Material.water) {
         pos = pos.down();
      }

      boolean flag = true;
      if (pos.getY() >= 1 && pos.getY() + n + 1 <= 256) {
         for (int i = pos.getY(); i <= pos.getY() + 1 + n; i++) {
            byte b = 1;
            if (i == pos.getY()) {
               b = 0;
            }

            if (i >= pos.getY() + 1 + n - 2) {
               b = 3;
            }

            BlockPos.MutableBlockPos pos2 = new BlockPos.MutableBlockPos();

            for (int j = pos.getX() - b; j <= pos.getX() + b && flag; j++) {
               for (int k = pos.getZ() - b; k <= pos.getZ() + b && flag; k++) {
                  if (i >= 0 && i < 256) {
                     Block block = world.getBlockState(pos2.set(j, i, k)).getBlock();
                     if (block.getMaterial() != Material.air && block.getMaterial() != Material.leaves) {
                        if (block != Blocks.water && block != Blocks.flowing_water) {
                           flag = false;
                        } else if (i > pos.getY()) {
                           flag = false;
                        }
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
            if ((block2 == Blocks.grass || block2 == Blocks.dirt) && pos.getY() < 256 - n - 1) {
               this.setDirtAt(world, pos.down());

               for (int m = pos.getY() - 3 + n; m <= pos.getY() + n; m++) {
                  int n2 = m - (pos.getY() + n);
                  int n3 = 2 - n2 / 2;

                  for (int idx = pos.getX() - n3; idx <= pos.getX() + n3; idx++) {
                     int n4 = idx - pos.getX();

                     for (int index = pos.getZ() - n3; index <= pos.getZ() + n3; index++) {
                        int n5 = index - pos.getZ();
                        if (Math.abs(n4) != n3 || Math.abs(n5) != n3 || rand.nextInt(2) != 0 && n2 != 0) {
                           BlockPos pos3 = new BlockPos(idx, m, index);
                           if (!world.getBlockState(pos3).getBlock().isFullBlock()) {
                              this.setBlockAndNotifyAdequately(world, pos3, LEAF);
                           }
                        }
                     }
                  }
               }

               for (int i2 = 0; i2 < n; i2++) {
                  Block block3 = world.getBlockState(pos.up(i2)).getBlock();
                  if (block3.getMaterial() == Material.air || block3.getMaterial() == Material.leaves || block3 == Blocks.flowing_water || block3 == Blocks.water) {
                     this.setBlockAndNotifyAdequately(world, pos.up(i2), TRUNK);
                  }
               }

               for (int i3 = pos.getY() - 3 + n; i3 <= pos.getY() + n; i3++) {
                  int n7 = 2 - (i3 - (pos.getY() + n)) / 2;
                  BlockPos.MutableBlockPos pos4 = new BlockPos.MutableBlockPos();

                  for (int i4 = pos.getX() - n7; i4 <= pos.getX() + n7; i4++) {
                     for (int i5 = pos.getZ() - n7; i5 <= pos.getZ() + n7; i5++) {
                        pos4.set(i4, i3, i5);
                        if (world.getBlockState(pos4).getBlock().getMaterial() == Material.leaves) {
                           BlockPos pos5 = pos4.west();
                           BlockPos pos6 = pos4.east();
                           BlockPos pos7 = pos4.north();
                           BlockPos pos8 = pos4.south();
                           if (rand.nextInt(4) == 0 && world.getBlockState(pos5).getBlock().getMaterial() == Material.air) {
                              this.addVine(world, pos5, BlockVine.EAST);
                           }

                           if (rand.nextInt(4) == 0 && world.getBlockState(pos6).getBlock().getMaterial() == Material.air) {
                              this.addVine(world, pos6, BlockVine.WEST);
                           }

                           if (rand.nextInt(4) == 0 && world.getBlockState(pos7).getBlock().getMaterial() == Material.air) {
                              this.addVine(world, pos7, BlockVine.SOUTH);
                           }

                           if (rand.nextInt(4) == 0 && world.getBlockState(pos8).getBlock().getMaterial() == Material.air) {
                              this.addVine(world, pos8, BlockVine.NORTH);
                           }
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

   public WorldGenSwamp() {
      super(false);
   }

   private void addVine(World world, BlockPos pos, PropertyBool propertyBool) {
      IBlockState state = Blocks.vine.getDefaultState().withProperty(propertyBool, Boolean.TRUE);
      this.setBlockAndNotifyAdequately(world, pos, state);
      int n = 4;

      for (BlockPos i = pos.down(); world.getBlockState(i).getBlock().getMaterial() == Material.air && n > 0; n--) {
         this.setBlockAndNotifyAdequately(world, i, state);
         i = i.down();
      }
   }
}
