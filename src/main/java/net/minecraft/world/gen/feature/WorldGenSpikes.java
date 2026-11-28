package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenSpikes extends WorldGenerator {
   private Block baseBlockRequired;

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      if (world.isAirBlock(pos) && world.getBlockState(pos.down()).getBlock() == this.baseBlockRequired) {
         int n = rand.nextInt(32) + 6;
         int n2 = rand.nextInt(4) + 1;
         BlockPos.MutableBlockPos pos2 = new BlockPos.MutableBlockPos();

         for (int i = pos.getX() - n2; i <= pos.getX() + n2; i++) {
            for (int j = pos.getZ() - n2; j <= pos.getZ() + n2; j++) {
               int n3 = i - pos.getX();
               int n4 = j - pos.getZ();
               if (n3 * n3 + n4 * n4 <= n2 * n2 + 1
                  && world.getBlockState(pos2.set(i, pos.getY() - 1, j)).getBlock() != this.baseBlockRequired) {
                  return false;
               }
            }
         }

         for (int k = pos.getY(); k < pos.getY() + n && k < 256; k++) {
            for (int m = pos.getX() - n2; m <= pos.getX() + n2; m++) {
               for (int idx = pos.getZ() - n2; idx <= pos.getZ() + n2; idx++) {
                  int n5 = m - pos.getX();
                  int n6 = idx - pos.getZ();
                  if (n5 * n5 + n6 * n6 <= n2 * n2 + 1) {
                     world.setBlockState(new BlockPos(m, k, idx), Blocks.obsidian.getDefaultState(), 2);
                  }
               }
            }
         }

         EntityEnderCrystal entityEnderCrystal = new EntityEnderCrystal(world);
         entityEnderCrystal.setLocationAndAngles(
            (double)((float)pos.getX() + 0.5F), (double)(pos.getY() + n), (double)((float)pos.getZ() + 0.5F), rand.nextFloat() * 360.0F, 0.0F
         );
         world.spawnEntityInWorld(entityEnderCrystal);
         world.setBlockState(pos.up(n), Blocks.bedrock.getDefaultState(), 2);
         return true;
      } else {
         return false;
      }
   }

   public WorldGenSpikes(Block block) {
      this.baseBlockRequired = block;
   }
}
