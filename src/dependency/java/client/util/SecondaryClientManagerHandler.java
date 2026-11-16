package client.util;

import client.setting.MovementFactorProperty;
import client.setting.MovementProperty;
import java.util.Random;
import net.minecraft.block.AuxiliaryBlockLeaves;
import net.minecraft.block.AuxiliaryBlockLog;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class SecondaryClientManagerHandler extends ClientManagerResolver {
   public static final IBlockState iBlockState2 = Blocks.block104
      .getDefaultState()
      .resolveIBlockState(AuxiliaryBlockLog.immutableSetEntry4, MovementProperty.ACACIA);
   public static final IBlockState iBlockState = Blocks.decayableManager
      .getDefaultState()
      .resolveIBlockState(AuxiliaryBlockLeaves.immutableSetEntry, MovementProperty.ACACIA)
      .resolveIBlockState(DecayableManager.internalImmutableSetEntry, Boolean.FALSE);

   private void applyWorld6(World world, BlockPos blockPos) {
      Material material = world.getBlockState(blockPos).getBlock().getMaterial();
      if (material == Material.coreItemEntry || material == Material.material9) {
         this.applyWorld(world, blockPos, iBlockState);
      }
   }

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      int count = random.nextInt(3) + random.nextInt(3) + 5;
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
            if ((block == Blocks.blockGrass || block == Blocks.block115) && blockPos.getY() < 256 - count - 1) {
               this.applyWorldByWorld(world, blockPos.down());
               EnumFacing enumFacing = MovementFactorProperty.HORIZONTAL.resolveEnumFacing(random);
               int count2 = count - random.nextInt(4) - 1;
               int count3 = 3 - random.nextInt(3);
               int coordX = blockPos.getX();
               int coordZ = blockPos.getZ();
               int count4 = 0;

               for (int index4 = 0; index4 < count; index4++) {
                  int count5 = blockPos.getY() + index4;
                  if (index4 >= count2 && count3 > 0) {
                     coordX += enumFacing.getInt2();
                     coordZ += enumFacing.getInt();
                     count3--;
                  }

                  BlockPos blockPos2 = new BlockPos(coordX, count5, coordZ);
                  Material material = world.getBlockState(blockPos2).getBlock().getMaterial();
                  if (material == Material.coreItemEntry || material == Material.material9) {
                     this.applyWorld4(world, blockPos2);
                     count4 = count5;
                  }
               }

               BlockPos blockPos3 = new BlockPos(coordX, count4, coordZ);

               for (int index5 = -3; index5 <= 3; index5++) {
                  for (int index6 = -3; index6 <= 3; index6++) {
                     if (Math.abs(index5) != 3 || Math.abs(index6) != 3) {
                        this.applyWorld6(world, blockPos3.add(index5, 0, index6));
                     }
                  }
               }

               BlockPos blockPos4 = blockPos3.up();

               for (int index7 = -1; index7 <= 1; index7++) {
                  for (int index8 = -1; index8 <= 1; index8++) {
                     this.applyWorld6(world, blockPos4.add(index7, 0, index8));
                  }
               }

               this.applyWorld6(world, blockPos4.resolveBlockPosByIndex(2));
               this.applyWorld6(world, blockPos4.resolveBlockPos5(2));
               this.applyWorld6(world, blockPos4.resolveBlockPos(2));
               this.applyWorld6(world, blockPos4.resolveBlockPos3(2));
               int coordX2 = blockPos.getX();
               int coordZ2 = blockPos.getZ();
               EnumFacing enumFacing2 = MovementFactorProperty.HORIZONTAL.resolveEnumFacing(random);
               if (enumFacing2 != enumFacing) {
                  int count6 = count2 - random.nextInt(2) - 1;
                  int count7 = 1 + random.nextInt(3);

                  int count8;
                  for (count8 = 0; count6 < count && count7 > 0; count7--) {
                     if (count6 >= 1) {
                        int count9 = blockPos.getY() + count6;
                        coordX2 += enumFacing2.getInt2();
                        coordZ2 += enumFacing2.getInt();
                        BlockPos blockPos5 = new BlockPos(coordX2, count9, coordZ2);
                        Material material2 = world.getBlockState(blockPos5).getBlock().getMaterial();
                        if (material2 == Material.coreItemEntry || material2 == Material.material9) {
                           this.applyWorld4(world, blockPos5);
                           count8 = count9;
                        }
                     }

                     count6++;
                  }

                  if (count8 > 0) {
                     BlockPos blockPos6 = new BlockPos(coordX2, count8, coordZ2);

                     for (int index9 = -2; index9 <= 2; index9++) {
                        for (int index10 = -2; index10 <= 2; index10++) {
                           if (Math.abs(index9) != 2 || Math.abs(index10) != 2) {
                              this.applyWorld6(world, blockPos6.add(index9, 0, index10));
                           }
                        }
                     }

                     BlockPos blockPos7 = blockPos6.up();

                     for (int index11 = -1; index11 <= 1; index11++) {
                        for (int index12 = -1; index12 <= 1; index12++) {
                           this.applyWorld6(world, blockPos7.add(index11, 0, index12));
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

   private void applyWorld4(World world, BlockPos blockPos) {
      this.applyWorld(world, blockPos, iBlockState2);
   }

   public SecondaryClientManagerHandler(boolean flag) {
      super(flag);
   }
}
