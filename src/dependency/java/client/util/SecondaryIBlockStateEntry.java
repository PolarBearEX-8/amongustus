package client.util;

import client.setting.MovementFactorProperty;
import client.setting.MovementProperty;
import java.util.Random;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockVine;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class SecondaryIBlockStateEntry extends ClientManagerResolver {
   public static final IBlockState iBlockState4 = Blocks.block140.getDefaultState().resolveIBlockState(BlockLog.immutableSetEntry3, MovementProperty.OAK);
   public static final IBlockState iBlockState3 = Blocks.decayableManager2
      .getDefaultState()
      .resolveIBlockState(BlockLeaves.immutableSetEntry, MovementProperty.OAK)
      .resolveIBlockState(DecayableManager.internalImmutableSetEntry, Boolean.FALSE);
   public final int count;
   public final boolean enabled2;
   public final IBlockState iBlockState;
   public final IBlockState iBlockState2;

   private void applyWorldByWorld(World world, BlockPos blockPos, InternalImmutableSetEntry internalImmutableSetEntry) {
      this.applyWorld3(world, blockPos, internalImmutableSetEntry);
      int count2 = 4;

      for (BlockPos blockPos2 = blockPos.down(); world.getBlockState(blockPos2).getBlock().getMaterial() == Material.coreItemEntry && count2 > 0; count2--) {
         this.applyWorld3(world, blockPos2, internalImmutableSetEntry);
         blockPos2 = blockPos2.down();
      }
   }

   private void applyWorldByWorld(World world, int count2, BlockPos blockPos, EnumFacing enumFacing) {
      this.applyWorld(
         world,
         blockPos,
         Blocks.block90.getDefaultState().resolveIBlockState(BlockCocoa.auxiliaryImmutableSetEntry, count2).resolveIBlockState(BlockCocoa.autoWalkPath, enumFacing)
      );
   }

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      int count2 = random.nextInt(3) + this.count;
      boolean flag = true;
      if (blockPos.getY() >= 1 && blockPos.getY() + count2 + 1 <= 256) {
         for (int index = blockPos.getY(); index <= blockPos.getY() + 1 + count2; index++) {
            byte byteValue = 1;
            if (index == blockPos.getY()) {
               byteValue = 0;
            }

            if (index >= blockPos.getY() + 1 + count2 - 2) {
               byteValue = 2;
            }

            AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

            for (int index2 = blockPos.getX() - byteValue; index2 <= blockPos.getX() + byteValue && flag; index2++) {
               for (int index3 = blockPos.getZ() - byteValue; index3 <= blockPos.getZ() + byteValue && flag; index3++) {
                  if (index < 0 || index >= 256) {
                     flag = false;
                  } else if (!this.matchesBlock(world.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index2, index, index3)).getBlock())) {
                     flag = false;
                  }
               }
            }
         }

         if (!flag) {
            return false;
         } else {
            Block block = world.getBlockState(blockPos.down()).getBlock();
            if ((block == Blocks.blockGrass || block == Blocks.block115 || block == Blocks.block7) && blockPos.getY() < 256 - count2 - 1) {
               this.applyWorldByWorld(world, blockPos.down());

               for (int index4 = blockPos.getY() - 3 + count2; index4 <= blockPos.getY() + count2; index4++) {
                  int count3 = index4 - (blockPos.getY() + count2);
                  int count4 = 1 - count3 / 2;

                  for (int index5 = blockPos.getX() - count4; index5 <= blockPos.getX() + count4; index5++) {
                     int count5 = index5 - blockPos.getX();

                     for (int index6 = blockPos.getZ() - count4; index6 <= blockPos.getZ() + count4; index6++) {
                        int count6 = index6 - blockPos.getZ();
                        if (Math.abs(count5) != count4 || Math.abs(count6) != count4 || random.nextInt(2) != 0 && count3 != 0) {
                           BlockPos blockPos2 = new BlockPos(index5, index4, index6);
                           Block block2 = world.getBlockState(blockPos2).getBlock();
                           if (block2.getMaterial() == Material.coreItemEntry
                              || block2.getMaterial() == Material.material9
                              || block2.getMaterial() == Material.material7) {
                              this.applyWorld(world, blockPos2, this.iBlockState2);
                           }
                        }
                     }
                  }
               }

               for (int index7 = 0; index7 < count2; index7++) {
                  Block block3 = world.getBlockState(blockPos.resolveBlockPos2(index7)).getBlock();
                  if (block3.getMaterial() == Material.coreItemEntry || block3.getMaterial() == Material.material9 || block3.getMaterial() == Material.material7) {
                     this.applyWorld(world, blockPos.resolveBlockPos2(index7), this.iBlockState);
                     if (this.enabled2 && index7 > 0) {
                        if (random.nextInt(3) > 0 && world.isAirBlock(blockPos.add(-1, index7, 0))) {
                           this.applyWorld3(world, blockPos.add(-1, index7, 0), BlockVine.internalImmutableSetEntry4);
                        }

                        if (random.nextInt(3) > 0 && world.isAirBlock(blockPos.add(1, index7, 0))) {
                           this.applyWorld3(world, blockPos.add(1, index7, 0), BlockVine.internalImmutableSetEntry3);
                        }

                        if (random.nextInt(3) > 0 && world.isAirBlock(blockPos.add(0, index7, -1))) {
                           this.applyWorld3(world, blockPos.add(0, index7, -1), BlockVine.internalImmutableSetEntry2);
                        }

                        if (random.nextInt(3) > 0 && world.isAirBlock(blockPos.add(0, index7, 1))) {
                           this.applyWorld3(world, blockPos.add(0, index7, 1), BlockVine.internalImmutableSetEntry);
                        }
                     }
                  }
               }

               if (this.enabled2) {
                  for (int index8 = blockPos.getY() - 3 + count2; index8 <= blockPos.getY() + count2; index8++) {
                     int count7 = index8 - (blockPos.getY() + count2);
                     int count8 = 2 - count7 / 2;
                     AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter2 = new AuxiliaryBlockPosAdapter();

                     for (int index9 = blockPos.getX() - count8; index9 <= blockPos.getX() + count8; index9++) {
                        for (int index10 = blockPos.getZ() - count8; index10 <= blockPos.getZ() + count8; index10++) {
                           auxiliaryBlockPosAdapter2.resolveAuxiliaryBlockPosAdapter(index9, index8, index10);
                           if (world.getBlockState(auxiliaryBlockPosAdapter2).getBlock().getMaterial() == Material.material9) {
                              BlockPos blockPos3 = auxiliaryBlockPosAdapter2.getBlockPos();
                              BlockPos blockPos4 = auxiliaryBlockPosAdapter2.getBlockPos3();
                              BlockPos blockPosAsBlockPos = auxiliaryBlockPosAdapter2.getBlockPosAsBlockPos();
                              BlockPos blockPos5 = auxiliaryBlockPosAdapter2.getBlockPos2();
                              if (random.nextInt(4) == 0 && world.getBlockState(blockPos3).getBlock().getMaterial() == Material.coreItemEntry) {
                                 this.applyWorldByWorld(world, blockPos3, BlockVine.internalImmutableSetEntry4);
                              }

                              if (random.nextInt(4) == 0 && world.getBlockState(blockPos4).getBlock().getMaterial() == Material.coreItemEntry) {
                                 this.applyWorldByWorld(world, blockPos4, BlockVine.internalImmutableSetEntry3);
                              }

                              if (random.nextInt(4) == 0 && world.getBlockState(blockPosAsBlockPos).getBlock().getMaterial() == Material.coreItemEntry) {
                                 this.applyWorldByWorld(world, blockPosAsBlockPos, BlockVine.internalImmutableSetEntry2);
                              }

                              if (random.nextInt(4) == 0 && world.getBlockState(blockPos5).getBlock().getMaterial() == Material.coreItemEntry) {
                                 this.applyWorldByWorld(world, blockPos5, BlockVine.internalImmutableSetEntry);
                              }
                           }
                        }
                     }
                  }

                  if (random.nextInt(5) == 0 && count2 > 5) {
                     for (int index11 = 0; index11 < 2; index11++) {
                        for (Object element : MovementFactorProperty.HORIZONTAL) {
                           if (random.nextInt(4 - index11) == 0) {
                              EnumFacing enumFacing = ((EnumFacing)element).getEnumFacing5();
                              this.applyWorldByWorld(world, random.nextInt(3), blockPos.add(enumFacing.getInt2(), count2 - 5 + index11, enumFacing.getInt()), (EnumFacing)element);
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

   private void applyWorld3(World world, BlockPos blockPos, InternalImmutableSetEntry internalImmutableSetEntry) {
      this.applyWorld(world, blockPos, Blocks.block144.getDefaultState().resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE));
   }

   public SecondaryIBlockStateEntry(boolean flag) {
      this(flag, 4, iBlockState4, iBlockState3, false);
   }

   public SecondaryIBlockStateEntry(boolean flag, int count2, IBlockState blockState, IBlockState blockState2, boolean flag2) {
      super(flag);
      this.count = count2;
      this.iBlockState = blockState;
      this.iBlockState2 = blockState2;
      this.enabled2 = flag2;
   }
}
