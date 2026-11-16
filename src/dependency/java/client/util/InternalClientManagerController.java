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

public class InternalClientManagerController extends ClientManagerResolver {
   public static final IBlockState iBlockState = Blocks.block104
      .getDefaultState()
      .resolveIBlockState(AuxiliaryBlockLog.immutableSetEntry4, MovementProperty.DARK_OAK);
   public static final IBlockState iBlockState2 = Blocks.decayableManager
      .getDefaultState()
      .resolveIBlockState(AuxiliaryBlockLeaves.immutableSetEntry, MovementProperty.DARK_OAK)
      .resolveIBlockState(DecayableManager.internalImmutableSetEntry, Boolean.FALSE);

   private void applyWorld3(World world, BlockPos blockPos) {
      if (this.matchesBlock(world.getBlockState(blockPos).getBlock())) {
         this.applyWorld(world, blockPos, iBlockState);
      }
   }

   private boolean matchesWorld(World world, BlockPos blockPos, int count) {
      int coordX = blockPos.getX();
      int coordY = blockPos.getY();
      int coordZ = blockPos.getZ();
      AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

      for (int index = 0; index <= count + 1; index++) {
         byte byteValue = 1;
         if (index == 0) {
            byteValue = 0;
         }

         if (index >= count - 1) {
            byteValue = 2;
         }

         for (int index2 = -byteValue; index2 <= byteValue; index2++) {
            for (int index3 = -byteValue; index3 <= byteValue; index3++) {
               if (!this.matchesBlock(world.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(coordX + index2, coordY + index, coordZ + index3)).getBlock())) {
                  return false;
               }
            }
         }
      }

      return true;
   }

   private void applyWorldByWorld(World world, int count, int count2, int count3) {
      BlockPos blockPos = new BlockPos(count, count2, count3);
      if (world.getBlockState(blockPos).getBlock().getMaterial() == Material.coreItemEntry) {
         this.applyWorld(world, blockPos, iBlockState2);
      }
   }

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      int count = random.nextInt(3) + random.nextInt(2) + 6;
      int coordX = blockPos.getX();
      int coordY = blockPos.getY();
      int coordZ = blockPos.getZ();
      if (coordY >= 1 && coordY + count + 1 < 256) {
         BlockPos blockPos2 = blockPos.down();
         Block block = world.getBlockState(blockPos2).getBlock();
         if (block != Blocks.blockGrass && block != Blocks.block115) {
            return false;
         } else if (!this.matchesWorld(world, blockPos, count)) {
            return false;
         } else {
            this.applyWorldByWorld(world, blockPos2);
            this.applyWorldByWorld(world, blockPos2.getBlockPos3());
            this.applyWorldByWorld(world, blockPos2.getBlockPos2());
            this.applyWorldByWorld(world, blockPos2.getBlockPos2().getBlockPos3());
            EnumFacing enumFacing = MovementFactorProperty.HORIZONTAL.resolveEnumFacing(random);
            int count2 = count - random.nextInt(4);
            int count3 = 2 - random.nextInt(3);
            int count4 = coordX;
            int count5 = coordZ;
            int count6 = coordY + count - 1;

            for (int index = 0; index < count; index++) {
               if (index >= count2 && count3 > 0) {
                  count4 += enumFacing.getInt2();
                  count5 += enumFacing.getInt();
                  count3--;
               }

               int count7 = coordY + index;
               BlockPos blockPos3 = new BlockPos(count4, count7, count5);
               Material material = world.getBlockState(blockPos3).getBlock().getMaterial();
               if (material == Material.coreItemEntry || material == Material.material9) {
                  this.applyWorld3(world, blockPos3);
                  this.applyWorld3(world, blockPos3.getBlockPos3());
                  this.applyWorld3(world, blockPos3.getBlockPos2());
                  this.applyWorld3(world, blockPos3.getBlockPos3().getBlockPos2());
               }
            }

            for (int index2 = -2; index2 <= 0; index2++) {
               for (int index3 = -2; index3 <= 0; index3++) {
                  this.applyWorldByWorld(world, count4 + index2, count6 + -1, count5 + index3);
                  this.applyWorldByWorld(world, 1 + count4 - index2, count6 + -1, count5 + index3);
                  this.applyWorldByWorld(world, count4 + index2, count6 + -1, 1 + count5 - index3);
                  this.applyWorldByWorld(world, 1 + count4 - index2, count6 + -1, 1 + count5 - index3);
                  if ((index2 > -2 || index3 > -1) && (index2 != -1 || index3 != -2)) {
                     this.applyWorldByWorld(world, count4 + index2, count6 + 1, count5 + index3);
                     this.applyWorldByWorld(world, 1 + count4 - index2, count6 + 1, count5 + index3);
                     this.applyWorldByWorld(world, count4 + index2, count6 + 1, 1 + count5 - index3);
                     this.applyWorldByWorld(world, 1 + count4 - index2, count6 + 1, 1 + count5 - index3);
                  }
               }
            }

            if (random.nextBoolean()) {
               this.applyWorldByWorld(world, count4, count6 + 2, count5);
               this.applyWorldByWorld(world, count4 + 1, count6 + 2, count5);
               this.applyWorldByWorld(world, count4 + 1, count6 + 2, count5 + 1);
               this.applyWorldByWorld(world, count4, count6 + 2, count5 + 1);
            }

            for (int index4 = -3; index4 <= 4; index4++) {
               for (int index5 = -3; index5 <= 4; index5++) {
                  if ((index4 != -3 || index5 != -3)
                     && (index4 != -3 || index5 != 4)
                     && (index4 != 4 || index5 != -3)
                     && (index4 != 4 || index5 != 4)
                     && (Math.abs(index4) < 3 || Math.abs(index5) < 3)) {
                     this.applyWorldByWorld(world, count4 + index4, count6, count5 + index5);
                  }
               }
            }

            for (int index6 = -1; index6 <= 2; index6++) {
               for (int index7 = -1; index7 <= 2; index7++) {
                  if ((index6 < 0 || index6 > 1 || index7 < 0 || index7 > 1) && random.nextInt(3) <= 0) {
                     int count8 = random.nextInt(3) + 2;

                     for (int index8 = 0; index8 < count8; index8++) {
                        this.applyWorld3(world, new BlockPos(coordX + index6, count6 - index8 - 1, coordZ + index7));
                     }

                     for (int index9 = -1; index9 <= 1; index9++) {
                        for (int index10 = -1; index10 <= 1; index10++) {
                           this.applyWorldByWorld(world, count4 + index6 + index9, count6, count5 + index7 + index10);
                        }
                     }

                     for (int index11 = -2; index11 <= 2; index11++) {
                        for (int index12 = -2; index12 <= 2; index12++) {
                           if (Math.abs(index11) != 2 || Math.abs(index12) != 2) {
                              this.applyWorldByWorld(world, count4 + index6 + index11, count6 - 1, count5 + index7 + index12);
                           }
                        }
                     }
                  }
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public InternalClientManagerController(boolean flag) {
      super(flag);
   }
}
