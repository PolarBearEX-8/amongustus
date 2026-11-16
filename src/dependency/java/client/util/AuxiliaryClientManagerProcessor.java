package client.util;

import client.setting.MovementProperty;
import java.util.Random;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockVine;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class AuxiliaryClientManagerProcessor extends ClientManagerResolver {
   public static final IBlockState iBlockState = Blocks.block140.getDefaultState().resolveIBlockState(BlockLog.immutableSetEntry3, MovementProperty.OAK);
   public static final IBlockState iBlockState2 = Blocks.decayableManager2
      .getDefaultState()
      .resolveIBlockState(BlockLeaves.immutableSetEntry, MovementProperty.OAK)
      .resolveIBlockState(BlockLeaves.internalImmutableSetEntry, Boolean.FALSE);

   private void applyWorldByWorld(World world, BlockPos blockPos, InternalImmutableSetEntry internalImmutableSetEntry) {
      IBlockState blockState = Blocks.block144.getDefaultState().resolveIBlockState(internalImmutableSetEntry, Boolean.TRUE);
      this.applyWorld(world, blockPos, blockState);
      int count = 4;

      for (BlockPos blockPos2 = blockPos.down(); world.getBlockState(blockPos2).getBlock().getMaterial() == Material.coreItemEntry && count > 0; count--) {
         this.applyWorld(world, blockPos2, blockState);
         blockPos2 = blockPos2.down();
      }
   }

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      BlockPos blockPos2 = blockPos;
      int count = random.nextInt(4) + 5;

      while (world.getBlockState(blockPos2.down()).getBlock().getMaterial() == Material.coreItemEntry1) {
         blockPos2 = blockPos2.down();
      }

      boolean flag = true;
      if (blockPos2.getY() >= 1 && blockPos2.getY() + count + 1 <= 256) {
         for (int index = blockPos2.getY(); index <= blockPos2.getY() + 1 + count; index++) {
            byte byteValue = 1;
            if (index == blockPos2.getY()) {
               byteValue = 0;
            }

            if (index >= blockPos2.getY() + 1 + count - 2) {
               byteValue = 3;
            }

            AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

            for (int index2 = blockPos2.getX() - byteValue; index2 <= blockPos2.getX() + byteValue && flag; index2++) {
               for (int index3 = blockPos2.getZ() - byteValue; index3 <= blockPos2.getZ() + byteValue && flag; index3++) {
                  if (index >= 0 && index < 256) {
                     Block block = world.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index2, index, index3)).getBlock();
                     if (block.getMaterial() != Material.coreItemEntry && block.getMaterial() != Material.material9) {
                        if (block != Blocks.blockLava2 && block != Blocks.blockFlowingLava) {
                           flag = false;
                        } else if (index > blockPos2.getY()) {
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
            Block block2 = world.getBlockState(blockPos2.down()).getBlock();
            if ((block2 == Blocks.blockGrass || block2 == Blocks.block115) && blockPos2.getY() < 256 - count - 1) {
               this.applyWorldByWorld(world, blockPos2.down());

               for (int index4 = blockPos2.getY() - 3 + count; index4 <= blockPos2.getY() + count; index4++) {
                  int count2 = index4 - (blockPos2.getY() + count);
                  int count3 = 2 - count2 / 2;

                  for (int index5 = blockPos2.getX() - count3; index5 <= blockPos2.getX() + count3; index5++) {
                     int count4 = index5 - blockPos2.getX();

                     for (int index6 = blockPos2.getZ() - count3; index6 <= blockPos2.getZ() + count3; index6++) {
                        int count5 = index6 - blockPos2.getZ();
                        if (Math.abs(count4) != count3 || Math.abs(count5) != count3 || random.nextInt(2) != 0 && count2 != 0) {
                           BlockPos blockPos3 = new BlockPos(index5, index4, index6);
                           if (!world.getBlockState(blockPos3).getBlock().isState18()) {
                              this.applyWorld(world, blockPos3, iBlockState2);
                           }
                        }
                     }
                  }
               }

               for (int index7 = 0; index7 < count; index7++) {
                  Block block3 = world.getBlockState(blockPos2.resolveBlockPos2(index7)).getBlock();
                  if (block3.getMaterial() == Material.coreItemEntry
                     || block3.getMaterial() == Material.material9
                     || block3 == Blocks.blockFlowingLava
                     || block3 == Blocks.blockLava2) {
                     this.applyWorld(world, blockPos2.resolveBlockPos2(index7), iBlockState);
                  }
               }

               for (int index8 = blockPos2.getY() - 3 + count; index8 <= blockPos2.getY() + count; index8++) {
                  int count6 = index8 - (blockPos2.getY() + count);
                  int count7 = 2 - count6 / 2;
                  AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter2 = new AuxiliaryBlockPosAdapter();

                  for (int index9 = blockPos2.getX() - count7; index9 <= blockPos2.getX() + count7; index9++) {
                     for (int index10 = blockPos2.getZ() - count7; index10 <= blockPos2.getZ() + count7; index10++) {
                        auxiliaryBlockPosAdapter2.resolveAuxiliaryBlockPosAdapter(index9, index8, index10);
                        if (world.getBlockState(auxiliaryBlockPosAdapter2).getBlock().getMaterial() == Material.material9) {
                           BlockPos blockPos4 = auxiliaryBlockPosAdapter2.getBlockPos();
                           BlockPos blockPos5 = auxiliaryBlockPosAdapter2.getBlockPos3();
                           BlockPos blockPosAsBlockPos = auxiliaryBlockPosAdapter2.getBlockPosAsBlockPos();
                           BlockPos blockPos6 = auxiliaryBlockPosAdapter2.getBlockPos2();
                           if (random.nextInt(4) == 0 && world.getBlockState(blockPos4).getBlock().getMaterial() == Material.coreItemEntry) {
                              this.applyWorldByWorld(world, blockPos4, BlockVine.internalImmutableSetEntry4);
                           }

                           if (random.nextInt(4) == 0 && world.getBlockState(blockPos5).getBlock().getMaterial() == Material.coreItemEntry) {
                              this.applyWorldByWorld(world, blockPos5, BlockVine.internalImmutableSetEntry3);
                           }

                           if (random.nextInt(4) == 0 && world.getBlockState(blockPosAsBlockPos).getBlock().getMaterial() == Material.coreItemEntry) {
                              this.applyWorldByWorld(world, blockPosAsBlockPos, BlockVine.internalImmutableSetEntry2);
                           }

                           if (random.nextInt(4) == 0 && world.getBlockState(blockPos6).getBlock().getMaterial() == Material.coreItemEntry) {
                              this.applyWorldByWorld(world, blockPos6, BlockVine.internalImmutableSetEntry);
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

   public AuxiliaryClientManagerProcessor() {
      super(false);
   }
}
