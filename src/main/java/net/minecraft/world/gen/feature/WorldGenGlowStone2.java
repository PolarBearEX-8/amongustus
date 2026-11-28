package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class WorldGenGlowStone2 extends WorldGenerator {
   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      if (!world.isAirBlock(pos)) {
         return false;
      } else if (world.getBlockState(pos.up()).getBlock() != Blocks.netherrack) {
         return false;
      } else {
         world.setBlockState(pos, Blocks.glowstone.getDefaultState(), 2);

         for (int i = 0; i < 1500; i++) {
            BlockPos pos2 = pos.add(rand.nextInt(8) - rand.nextInt(8), -rand.nextInt(12), rand.nextInt(8) - rand.nextInt(8));
            if (world.getBlockState(pos2).getBlock().getMaterial() == Material.air) {
               int n = 0;

               for (EnumFacing facing : EnumFacing.getValues()) {
                  if (world.getBlockState(pos2.offset(facing)).getBlock() == Blocks.glowstone) {
                     n++;
                  }

                  if (n > 1) {
                     break;
                  }
               }

               if (n == 1) {
                  world.setBlockState(pos2, Blocks.glowstone.getDefaultState(), 2);
               }
            }
         }

         return true;
      }
   }
}
