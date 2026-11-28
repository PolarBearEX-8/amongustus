package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class WorldGenLakes extends WorldGenerator {
   private Block block;

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      pos = pos.add(-8, 0, -8);

      while (pos.getY() > 5 && world.isAirBlock(pos)) {
         pos = pos.down();
      }

      if (pos.getY() <= 4) {
         return false;
      } else {
         pos = pos.down(4);
         boolean[] flags = new boolean[2048];
         int n = rand.nextInt(4) + 4;

         for (int i = 0; i < n; i++) {
            double d = rand.nextDouble() * 6.0 + 3.0;
            double d2 = rand.nextDouble() * 4.0 + 2.0;
            double d3 = rand.nextDouble() * 6.0 + 3.0;
            double d4 = rand.nextDouble() * (16.0 - d - 2.0) + 1.0 + d / 2.0;
            double d5 = rand.nextDouble() * (8.0 - d2 - 4.0) + 2.0 + d2 / 2.0;
            double d6 = rand.nextDouble() * (16.0 - d3 - 2.0) + 1.0 + d3 / 2.0;

            for (int j = 1; j < 15; j++) {
               for (int k = 1; k < 15; k++) {
                  for (int m = 1; m < 7; m++) {
                     double d7 = ((double)j - d4) / (d / 2.0);
                     double d8 = ((double)m - d5) / (d2 / 2.0);
                     double d9 = ((double)k - d6) / (d3 / 2.0);
                     if ((d7 * d7 + d8 * d8 + d9 * d9) < 1.0) {
                        flags[(j * 16 + k) * 8 + m] = true;
                     }
                  }
               }
            }
         }

         for (int idx = 0; idx < 16; idx++) {
            for (int index = 0; index < 16; index++) {
               for (int i2 = 0; i2 < 8; i2++) {
                  boolean flag = !flags[(idx * 16 + index) * 8 + i2]
                     && (
                        idx < 15 && flags[((idx + 1) * 16 + index) * 8 + i2]
                           || idx > 0 && flags[((idx - 1) * 16 + index) * 8 + i2]
                           || index < 15 && flags[(idx * 16 + index + 1) * 8 + i2]
                           || index > 0 && flags[(idx * 16 + (index - 1)) * 8 + i2]
                           || i2 < 7 && flags[(idx * 16 + index) * 8 + i2 + 1]
                           || i2 > 0 && flags[(idx * 16 + index) * 8 + (i2 - 1)]
                     );
                  if (flag) {
                     Material material = world.getBlockState(pos.add(idx, i2, index)).getBlock().getMaterial();
                     if (i2 >= 4 && material.isLiquid()) {
                        return false;
                     }

                     if (i2 < 4 && !material.isSolid() && world.getBlockState(pos.add(idx, i2, index)).getBlock() != this.block) {
                        return false;
                     }
                  }
               }
            }
         }

         for (int i3 = 0; i3 < 16; i3++) {
            for (int i4 = 0; i4 < 16; i4++) {
               for (int i5 = 0; i5 < 8; i5++) {
                  if (flags[(i3 * 16 + i4) * 8 + i5]) {
                     world.setBlockState(pos.add(i3, i5, i4), i5 >= 4 ? Blocks.air.getDefaultState() : this.block.getDefaultState(), 2);
                  }
               }
            }
         }

         for (int i6 = 0; i6 < 16; i6++) {
            for (int i7 = 0; i7 < 16; i7++) {
               for (int i8 = 4; i8 < 8; i8++) {
                  if (flags[(i6 * 16 + i7) * 8 + i8]) {
                     BlockPos pos2 = pos.add(i6, i8 - 1, i7);
                     if (world.getBlockState(pos2).getBlock() == Blocks.dirt
                        && world.getLightFor(EnumSkyBlock.SKY, pos.getX() + i6, pos.getY() + i8, pos.getZ() + i7) > 0) {
                        if (world.getBiomeGenForCoords(pos2).topBlock.getBlock() == Blocks.mycelium) {
                           world.setBlockState(pos2, Blocks.mycelium.getDefaultState(), 2);
                        } else {
                           world.setBlockState(pos2, Blocks.grass.getDefaultState(), 2);
                        }
                     }
                  }
               }
            }
         }

         if (this.block.getMaterial() == Material.lava) {
            for (int i9 = 0; i9 < 16; i9++) {
               for (int i10 = 0; i10 < 16; i10++) {
                  for (int i11 = 0; i11 < 8; i11++) {
                     boolean flag2 = !flags[(i9 * 16 + i10) * 8 + i11]
                        && (
                           i9 < 15 && flags[((i9 + 1) * 16 + i10) * 8 + i11]
                              || i9 > 0 && flags[((i9 - 1) * 16 + i10) * 8 + i11]
                              || i10 < 15 && flags[(i9 * 16 + i10 + 1) * 8 + i11]
                              || i10 > 0 && flags[(i9 * 16 + (i10 - 1)) * 8 + i11]
                              || i11 < 7 && flags[(i9 * 16 + i10) * 8 + i11 + 1]
                              || i11 > 0 && flags[(i9 * 16 + i10) * 8 + (i11 - 1)]
                        );
                     if (flag2 && (i11 < 4 || rand.nextInt(2) != 0) && world.getBlockState(pos.add(i9, i11, i10)).getBlock().getMaterial().isSolid()) {
                        world.setBlockState(pos.add(i9, i11, i10), Blocks.stone.getDefaultState(), 2);
                     }
                  }
               }
            }
         }

         if (this.block.getMaterial() == Material.water) {
            for (int i12 = 0; i12 < 16; i12++) {
               for (int i13 = 0; i13 < 16; i13++) {
                  byte b = 4;
                  if (world.canBlockFreezeWater(pos.add(i12, b, i13))) {
                     world.setBlockState(pos.add(i12, b, i13), Blocks.ice.getDefaultState(), 2);
                  }
               }
            }
         }

         return true;
      }
   }

   public WorldGenLakes(Block block2) {
      this.block = block2;
   }
}
