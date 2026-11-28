package net.minecraft.world.gen.feature;

import net.minecraft.block.BlockPlanks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class WorldGenTrees extends WorldGenAbstractTree {
   private static final IBlockState DEFAULT_TRUNK = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);
   private static final IBlockState DEFAULT_LEAF = Blocks.leaves
      .getDefaultState()
      .withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK)
      .withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE);
   private final int minTreeHeight;
   private final boolean vinesGrow;
   private final IBlockState metaWood;
   private final IBlockState metaLeaves;

   private void addVine(World world, BlockPos pos, PropertyBool propertyBool) {
      this.setBlockAndNotifyAdequately(world, pos, Blocks.vine.getDefaultState().withProperty(propertyBool, Boolean.TRUE));
   }

   private void addHangingVine(World world, BlockPos pos, PropertyBool propertyBool) {
      this.addVine(world, pos, propertyBool);
      int n = 4;

      for (BlockPos i = pos.down(); world.getBlockState(i).getBlock().getMaterial() == Material.air && n > 0; n--) {
         this.addVine(world, i, propertyBool);
         i = i.down();
      }
   }

   private void placeCocoa(World world, int n, BlockPos pos, EnumFacing facing) {
      this.setBlockAndNotifyAdequately(world, pos, Blocks.cocoa.getDefaultState().withProperty(BlockCocoa.AGE, n).withProperty(BlockCocoa.FACING, facing));
   }

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      int n = rand.nextInt(3) + this.minTreeHeight;
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

               for (int m = pos.getY() - ((byte)3) + n; m <= pos.getY() + n; m++) {
                  int n2 = m - (pos.getY() + n);
                  int n3 = ((byte)0) + 1 - n2 / 2;

                  for (int idx = pos.getX() - n3; idx <= pos.getX() + n3; idx++) {
                     int n4 = idx - pos.getX();

                     for (int index = pos.getZ() - n3; index <= pos.getZ() + n3; index++) {
                        int n5 = index - pos.getZ();
                        if (Math.abs(n4) != n3 || Math.abs(n5) != n3 || rand.nextInt(2) != 0 && n2 != 0) {
                           BlockPos pos3 = new BlockPos(idx, m, index);
                           Block block2 = world.getBlockState(pos3).getBlock();
                           if (block2.getMaterial() == Material.air || block2.getMaterial() == Material.leaves || block2.getMaterial() == Material.vine) {
                              this.setBlockAndNotifyAdequately(world, pos3, this.metaLeaves);
                           }
                        }
                     }
                  }
               }

               for (int i2 = 0; i2 < n; i2++) {
                  Block block3 = world.getBlockState(pos.up(i2)).getBlock();
                  if (block3.getMaterial() == Material.air || block3.getMaterial() == Material.leaves || block3.getMaterial() == Material.vine) {
                     this.setBlockAndNotifyAdequately(world, pos.up(i2), this.metaWood);
                     if (this.vinesGrow && i2 > 0) {
                        if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(-1, i2, 0))) {
                           this.addVine(world, pos.add(-1, i2, 0), BlockVine.EAST);
                        }

                        if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(1, i2, 0))) {
                           this.addVine(world, pos.add(1, i2, 0), BlockVine.WEST);
                        }

                        if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(0, i2, -1))) {
                           this.addVine(world, pos.add(0, i2, -1), BlockVine.SOUTH);
                        }

                        if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(0, i2, 1))) {
                           this.addVine(world, pos.add(0, i2, 1), BlockVine.NORTH);
                        }
                     }
                  }
               }

               if (this.vinesGrow) {
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
                                 this.addHangingVine(world, pos5, BlockVine.EAST);
                              }

                              if (rand.nextInt(4) == 0 && world.getBlockState(pos6).getBlock().getMaterial() == Material.air) {
                                 this.addHangingVine(world, pos6, BlockVine.WEST);
                              }

                              if (rand.nextInt(4) == 0 && world.getBlockState(pos7).getBlock().getMaterial() == Material.air) {
                                 this.addHangingVine(world, pos7, BlockVine.SOUTH);
                              }

                              if (rand.nextInt(4) == 0 && world.getBlockState(pos8).getBlock().getMaterial() == Material.air) {
                                 this.addHangingVine(world, pos8, BlockVine.NORTH);
                              }
                           }
                        }
                     }
                  }

                  if (rand.nextInt(5) == 0 && n > 5) {
                     for (int i6 = 0; i6 < 2; i6++) {
                        for (Object obj : EnumFacing.Plane.HORIZONTAL) {
                           if (rand.nextInt(4 - i6) == 0) {
                              EnumFacing opposite = ((EnumFacing)obj).getOpposite();
                              this.placeCocoa(world, rand.nextInt(3), pos.add(opposite.getFrontOffsetX(), n - 5 + i6, opposite.getFrontOffsetZ()), (EnumFacing)obj);
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

   public WorldGenTrees(boolean flag) {
      this(flag, 4, DEFAULT_TRUNK, DEFAULT_LEAF, false);
   }

   public WorldGenTrees(boolean flag, int n, IBlockState state, IBlockState state2, boolean flag2) {
      super(flag);
      this.minTreeHeight = n;
      this.metaWood = state;
      this.metaLeaves = state2;
      this.vinesGrow = flag2;
   }
}
