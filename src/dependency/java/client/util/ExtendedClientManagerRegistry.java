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

public class ExtendedClientManagerRegistry extends ClientManagerResolver {
   public static final IBlockState iBlockState = Blocks.block140.getDefaultState().resolveIBlockState(BlockLog.immutableSetEntry3, MovementProperty.SPRUCE);
   public static final IBlockState iBlockState2 = Blocks.decayableManager2
      .getDefaultState()
      .resolveIBlockState(BlockLeaves.immutableSetEntry, MovementProperty.SPRUCE)
      .resolveIBlockState(DecayableManager.internalImmutableSetEntry, Boolean.FALSE);

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      int count = random.nextInt(5) + 7;
      int count2 = count - random.nextInt(2) - 3;
      int count3 = count - count2;
      int count4 = 1 + random.nextInt(count3 + 1);
      boolean flag = true;
      if (blockPos.getY() >= 1 && blockPos.getY() + count + 1 <= 256) {
         for (int index = blockPos.getY(); index <= blockPos.getY() + 1 + count && flag; index++) {
            int count5;
            if (index - blockPos.getY() < count2) {
               count5 = 0;
            } else {
               count5 = count4;
            }

            AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

            for (int index2 = blockPos.getX() - count5; index2 <= blockPos.getX() + count5 && flag; index2++) {
               for (int index3 = blockPos.getZ() - count5; index3 <= blockPos.getZ() + count5 && flag; index3++) {
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
            if ((block == Blocks.blockGrass || block == Blocks.block115) && blockPos.getY() < 256 - count - 1) {
               this.applyWorldByWorld(world, blockPos.down());
               int count6 = 0;

               for (int index4 = blockPos.getY() + count; index4 >= blockPos.getY() + count2; index4--) {
                  for (int index5 = blockPos.getX() - count6; index5 <= blockPos.getX() + count6; index5++) {
                     int count7 = index5 - blockPos.getX();

                     for (int index6 = blockPos.getZ() - count6; index6 <= blockPos.getZ() + count6; index6++) {
                        int count8 = index6 - blockPos.getZ();
                        if (Math.abs(count7) != count6 || Math.abs(count8) != count6 || count6 <= 0) {
                           BlockPos blockPos2 = new BlockPos(index5, index4, index6);
                           if (!world.getBlockState(blockPos2).getBlock().isState18()) {
                              this.applyWorld(world, blockPos2, iBlockState2);
                           }
                        }
                     }
                  }

                  if (count6 >= 1 && index4 == blockPos.getY() + count2 + 1) {
                     count6--;
                  } else if (count6 < count4) {
                     count6++;
                  }
               }

               for (int index7 = 0; index7 < count - 1; index7++) {
                  Block block2 = world.getBlockState(blockPos.resolveBlockPos2(index7)).getBlock();
                  if (block2.getMaterial() == Material.coreItemEntry || block2.getMaterial() == Material.material9) {
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

   public ExtendedClientManagerRegistry() {
      super(false);
   }
}
