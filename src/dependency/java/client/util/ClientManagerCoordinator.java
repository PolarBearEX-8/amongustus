package client.util;

import client.setting.MovementProperty;
import java.util.Random;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ClientManagerCoordinator extends ClientManagerResolver {
   public static final IBlockState iBlockState = Blocks.block140.getDefaultState().resolveIBlockState(BlockLog.immutableSetEntry3, MovementProperty.BIRCH);
   public static final IBlockState iBlockState2 = Blocks.decayableManager2
      .getDefaultState()
      .resolveIBlockState(BlockLeaves.immutableSetEntry, MovementProperty.BIRCH)
      .resolveIBlockState(BlockLeaves.internalImmutableSetEntry, Boolean.FALSE);
   public boolean enabled2;

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      int count = random.nextInt(3) + 5;
      if (this.enabled2) {
         count += random.nextInt(7);
      }

      boolean flag = true;
      if (blockPos.getY() >= 1 && blockPos.getY() + count + 1 <= 256) {
         for (int index = blockPos.getY(); index <= blockPos.getY() + 1 + count; index++) {
            byte byteValue = 1;
            if (index == blockPos.getY()) {
               byteValue = 0;
            }

            if (index >= blockPos.getY() + 1 + count - 2) {
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
            if ((block == Blocks.blockGrass || block == Blocks.block115 || block == Blocks.block7) && blockPos.getY() < 256 - count - 1) {
               this.applyWorldByWorld(world, blockPos.down());

               for (int index4 = blockPos.getY() - 3 + count; index4 <= blockPos.getY() + count; index4++) {
                  int count2 = index4 - (blockPos.getY() + count);
                  int count3 = 1 - count2 / 2;

                  for (int index5 = blockPos.getX() - count3; index5 <= blockPos.getX() + count3; index5++) {
                     int count4 = index5 - blockPos.getX();

                     for (int index6 = blockPos.getZ() - count3; index6 <= blockPos.getZ() + count3; index6++) {
                        int count5 = index6 - blockPos.getZ();
                        if (Math.abs(count4) != count3 || Math.abs(count5) != count3 || random.nextInt(2) != 0 && count2 != 0) {
                           BlockPos blockPos2 = new BlockPos(index5, index4, index6);
                           Block block2 = world.getBlockState(blockPos2).getBlock();
                           if (block2.getMaterial() == Material.coreItemEntry || block2.getMaterial() == Material.material9) {
                              this.applyWorld(world, blockPos2, iBlockState2);
                           }
                        }
                     }
                  }
               }

               for (int index7 = 0; index7 < count; index7++) {
                  Block block3 = world.getBlockState(blockPos.resolveBlockPos2(index7)).getBlock();
                  if (block3.getMaterial() == Material.coreItemEntry || block3.getMaterial() == Material.material9) {
                     this.applyWorld(world, blockPos.resolveBlockPos2(index7), iBlockState);
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

   public ClientManagerCoordinator(boolean flag, boolean flag2) {
      super(flag);
      this.enabled2 = flag2;
   }
}
