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

public class ExtendedIBlockStateEntry extends ClientManagerResolver {
   public static final IBlockState iBlockState2 = Blocks.block140.getDefaultState().resolveIBlockState(BlockLog.immutableSetEntry3, MovementProperty.SPRUCE);
   public static final IBlockState iBlockState = Blocks.decayableManager2
      .getDefaultState()
      .resolveIBlockState(BlockLeaves.immutableSetEntry, MovementProperty.SPRUCE)
      .resolveIBlockState(DecayableManager.internalImmutableSetEntry, Boolean.FALSE);

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      int count = random.nextInt(4) + 6;
      int count2 = 1 + random.nextInt(2);
      int count3 = count - count2;
      int count4 = 2 + random.nextInt(2);
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
                  if (index >= 0 && index < 256) {
                     Block block = world.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index2, index, index3)).getBlock();
                     if (block.getMaterial() != Material.coreItemEntry && block.getMaterial() != Material.material9) {
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
            Block block2 = world.getBlockState(blockPos.down()).getBlock();
            if ((block2 == Blocks.blockGrass || block2 == Blocks.block115 || block2 == Blocks.block7) && blockPos.getY() < 256 - count - 1) {
               this.applyWorldByWorld(world, blockPos.down());
               int count6 = random.nextInt(2);
               int count7 = 1;
               byte byteValue = 0;

               for (int index4 = 0; index4 <= count3; index4++) {
                  int count8 = blockPos.getY() + count - index4;

                  for (int index5 = blockPos.getX() - count6; index5 <= blockPos.getX() + count6; index5++) {
                     int count9 = index5 - blockPos.getX();

                     for (int index6 = blockPos.getZ() - count6; index6 <= blockPos.getZ() + count6; index6++) {
                        int count10 = index6 - blockPos.getZ();
                        if (Math.abs(count9) != count6 || Math.abs(count10) != count6 || count6 <= 0) {
                           BlockPos blockPos2 = new BlockPos(index5, count8, index6);
                           if (!world.getBlockState(blockPos2).getBlock().isState18()) {
                              this.applyWorld(world, blockPos2, iBlockState);
                           }
                        }
                     }
                  }

                  if (count6 >= count7) {
                     count6 = byteValue;
                     byteValue = 1;
                     if (++count7 > count4) {
                        count7 = count4;
                     }
                  } else {
                     count6++;
                  }
               }

               int count11 = random.nextInt(3);

               for (int index7 = 0; index7 < count - count11; index7++) {
                  Block block3 = world.getBlockState(blockPos.resolveBlockPos2(index7)).getBlock();
                  if (block3.getMaterial() == Material.coreItemEntry || block3.getMaterial() == Material.material9) {
                     this.applyWorld(world, blockPos.resolveBlockPos2(index7), iBlockState2);
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

   public ExtendedIBlockStateEntry(boolean flag) {
      super(flag);
   }
}
