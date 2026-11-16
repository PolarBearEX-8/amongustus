package net.minecraft.block;

import client.setting.RenderOptionProperty;
import client.util.CurveParser;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class SecondaryBlockEntry extends CurveParser {
   public Block block;

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      if (this.block == null) {
         this.block = random.nextBoolean() ? Blocks.block54 : Blocks.block41;
      }

      int count = random.nextInt(3) + 4;
      boolean flag = true;
      if (blockPos.getY() >= 1 && blockPos.getY() + count + 1 < 256) {
         for (int index = blockPos.getY(); index <= blockPos.getY() + 1 + count; index++) {
            byte byteValue = 3;
            if (index <= blockPos.getY() + 3) {
               byteValue = 0;
            }

            AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

            for (int index2 = blockPos.getX() - byteValue; index2 <= blockPos.getX() + byteValue && flag; index2++) {
               for (int index3 = blockPos.getZ() - byteValue; index3 <= blockPos.getZ() + byteValue && flag; index3++) {
                  if (index >= 0 && index < 256) {
                     Block block2 = world.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index2, index, index3)).getBlock();
                     if (block2.getMaterial() != Material.coreItemEntry && block2.getMaterial() != Material.material9) {
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
            Block block3 = world.getBlockState(blockPos.down()).getBlock();
            if (block3 != Blocks.block115 && block3 != Blocks.blockGrass && block3 != Blocks.blockMycelium) {
               return false;
            } else {
               int count2 = blockPos.getY() + count;
               if (this.block == Blocks.block41) {
                  count2 = blockPos.getY() + count - 3;
               }

               while (count2 <= blockPos.getY() + count) {
                  int count3 = 1;
                  if (count2 < blockPos.getY() + count) {
                     count3++;
                  }

                  if (this.block == Blocks.block54) {
                     count3 = 3;
                  }

                  int count4 = blockPos.getX() - count3;
                  int count5 = blockPos.getX() + count3;
                  int count6 = blockPos.getZ() - count3;
                  int count7 = blockPos.getZ() + count3;

                  for (int index4 = count4; index4 <= count5; index4++) {
                     for (int index5 = count6; index5 <= count7; index5++) {
                        int count8 = 5;
                        if (index4 == count4) {
                           count8--;
                        } else if (index4 == count5) {
                           count8++;
                        }

                        if (index5 == count6) {
                           count8 -= 3;
                        } else if (index5 == count7) {
                           count8 += 3;
                        }

                        RenderOptionProperty renderOptionProperty = RenderOptionProperty.resolveRenderOptionProperty(count8);
                        if (this.block == Blocks.block54 || count2 < blockPos.getY() + count) {
                           if ((index4 == count4 || index4 == count5) && (index5 == count6 || index5 == count7)) {
                              continue;
                           }

                           if (index4 == blockPos.getX() - (count3 - 1) && index5 == count6) {
                              renderOptionProperty = RenderOptionProperty.NORTH_WEST;
                           }

                           if (index4 == count4 && index5 == blockPos.getZ() - (count3 - 1)) {
                              renderOptionProperty = RenderOptionProperty.NORTH_WEST;
                           }

                           if (index4 == blockPos.getX() + (count3 - 1) && index5 == count6) {
                              renderOptionProperty = RenderOptionProperty.NORTH_EAST;
                           }

                           if (index4 == count5 && index5 == blockPos.getZ() - (count3 - 1)) {
                              renderOptionProperty = RenderOptionProperty.NORTH_EAST;
                           }

                           if (index4 == blockPos.getX() - (count3 - 1) && index5 == count7) {
                              renderOptionProperty = RenderOptionProperty.SOUTH_WEST;
                           }

                           if (index4 == count4 && index5 == blockPos.getZ() + (count3 - 1)) {
                              renderOptionProperty = RenderOptionProperty.SOUTH_WEST;
                           }

                           if (index4 == blockPos.getX() + (count3 - 1) && index5 == count7) {
                              renderOptionProperty = RenderOptionProperty.SOUTH_EAST;
                           }

                           if (index4 == count5 && index5 == blockPos.getZ() + (count3 - 1)) {
                              renderOptionProperty = RenderOptionProperty.SOUTH_EAST;
                           }
                        }

                        if (renderOptionProperty == RenderOptionProperty.CENTER && count2 < blockPos.getY() + count) {
                           renderOptionProperty = RenderOptionProperty.ALL_INSIDE;
                        }

                        if (blockPos.getY() >= blockPos.getY() + count - 1 || renderOptionProperty != RenderOptionProperty.ALL_INSIDE) {
                           BlockPos blockPos2 = new BlockPos(index4, count2, index5);
                           if (!world.getBlockState(blockPos2).getBlock().isState18()) {
                              this.applyWorld(world, blockPos2, this.block.getDefaultState().resolveIBlockState(BlockRedMushroomBlock.immutableSetEntry, renderOptionProperty));
                           }
                        }
                     }
                  }

                  count2++;
               }

               for (int index6 = 0; index6 < count; index6++) {
                  if (!world.getBlockState(blockPos.resolveBlockPos2(index6)).getBlock().isState18()) {
                     this.applyWorld(
                        world,
                        blockPos.resolveBlockPos2(index6),
                        this.block.getDefaultState().resolveIBlockState(BlockRedMushroomBlock.immutableSetEntry, RenderOptionProperty.STEM)
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

   public SecondaryBlockEntry() {
      super(false);
   }

   public SecondaryBlockEntry(Block block2) {
      super(true);
      this.block = block2;
   }
}
