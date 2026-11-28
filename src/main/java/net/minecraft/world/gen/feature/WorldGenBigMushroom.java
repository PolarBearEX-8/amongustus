package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenBigMushroom extends WorldGenerator {
   private Block mushroomType;

   public WorldGenBigMushroom() {
      super(false);
   }

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      if (this.mushroomType == null) {
         this.mushroomType = rand.nextBoolean() ? Blocks.brown_mushroom_block : Blocks.red_mushroom_block;
      }

      int n = rand.nextInt(3) + 4;
      boolean flag = true;
      if (pos.getY() >= 1 && pos.getY() + n + 1 < 256) {
         for (int i = pos.getY(); i <= pos.getY() + 1 + n; i++) {
            byte b = 3;
            if (i <= pos.getY() + 3) {
               b = 0;
            }

            BlockPos.MutableBlockPos pos2 = new BlockPos.MutableBlockPos();

            for (int j = pos.getX() - b; j <= pos.getX() + b && flag; j++) {
               for (int k = pos.getZ() - b; k <= pos.getZ() + b && flag; k++) {
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
            if (block2 != Blocks.dirt && block2 != Blocks.grass && block2 != Blocks.mycelium) {
               return false;
            } else {
               int n2 = pos.getY() + n;
               if (this.mushroomType == Blocks.red_mushroom_block) {
                  n2 = pos.getY() + n - 3;
               }

               for (int m = n2; m <= pos.getY() + n; m++) {
                  int n3 = 1;
                  if (m < pos.getY() + n) {
                     n3++;
                  }

                  if (this.mushroomType == Blocks.brown_mushroom_block) {
                     n3 = 3;
                  }

                  int n4 = pos.getX() - n3;
                  int n5 = pos.getX() + n3;
                  int n6 = pos.getZ() - n3;
                  int n7 = pos.getZ() + n3;

                  for (int idx = n4; idx <= n5; idx++) {
                     for (int index = n6; index <= n7; index++) {
                        int n8 = 5;
                        if (idx == n4) {
                           n8--;
                        } else if (idx == n5) {
                           n8++;
                        }

                        if (index == n6) {
                           n8 -= 3;
                        } else if (index == n7) {
                           n8 += 3;
                        }

                        BlockHugeMushroom.EnumType enumType = BlockHugeMushroom.EnumType.byMetadata(n8);
                        if (this.mushroomType == Blocks.brown_mushroom_block || m < pos.getY() + n) {
                           if ((idx == n4 || idx == n5) && (index == n6 || index == n7)) {
                              continue;
                           }

                           if (idx == pos.getX() - (n3 - 1) && index == n6) {
                              enumType = BlockHugeMushroom.EnumType.NORTH_WEST;
                           }

                           if (idx == n4 && index == pos.getZ() - (n3 - 1)) {
                              enumType = BlockHugeMushroom.EnumType.NORTH_WEST;
                           }

                           if (idx == pos.getX() + (n3 - 1) && index == n6) {
                              enumType = BlockHugeMushroom.EnumType.NORTH_EAST;
                           }

                           if (idx == n5 && index == pos.getZ() - (n3 - 1)) {
                              enumType = BlockHugeMushroom.EnumType.NORTH_EAST;
                           }

                           if (idx == pos.getX() - (n3 - 1) && index == n7) {
                              enumType = BlockHugeMushroom.EnumType.SOUTH_WEST;
                           }

                           if (idx == n4 && index == pos.getZ() + (n3 - 1)) {
                              enumType = BlockHugeMushroom.EnumType.SOUTH_WEST;
                           }

                           if (idx == pos.getX() + (n3 - 1) && index == n7) {
                              enumType = BlockHugeMushroom.EnumType.SOUTH_EAST;
                           }

                           if (idx == n5 && index == pos.getZ() + (n3 - 1)) {
                              enumType = BlockHugeMushroom.EnumType.SOUTH_EAST;
                           }
                        }

                        if (enumType == BlockHugeMushroom.EnumType.CENTER && m < pos.getY() + n) {
                           enumType = BlockHugeMushroom.EnumType.ALL_INSIDE;
                        }

                        if (pos.getY() >= pos.getY() + n - 1 || enumType != BlockHugeMushroom.EnumType.ALL_INSIDE) {
                           BlockPos pos3 = new BlockPos(idx, m, index);
                           if (!world.getBlockState(pos3).getBlock().isFullBlock()) {
                              this.setBlockAndNotifyAdequately(world, pos3, this.mushroomType.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, enumType));
                           }
                        }
                     }
                  }
               }

               for (int i2 = 0; i2 < n; i2++) {
                  if (!world.getBlockState(pos.up(i2)).getBlock().isFullBlock()) {
                     this.setBlockAndNotifyAdequately(
                        world, pos.up(i2), this.mushroomType.getDefaultState().withProperty(BlockHugeMushroom.VARIANT, BlockHugeMushroom.EnumType.STEM)
                     );
                  }
               }

               return true;
            }
         }
      } else {
         return false;
      }
   }

   public WorldGenBigMushroom(Block block) {
      super(true);
      this.mushroomType = block;
   }
}
