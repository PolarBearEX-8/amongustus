package net.minecraft.block;

import client.util.CurveParser;
import java.util.Random;
import net.minecraft.entity.EntityEndercrystal;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class AdvancedBlockEntry extends CurveParser {
   public Block block;

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      if (world.isAirBlock(blockPos) && world.getBlockState(blockPos.down()).getBlock() == this.block) {
         int count = random.nextInt(32) + 6;
         int count2 = random.nextInt(4) + 1;
         AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

         for (int index = blockPos.getX() - count2; index <= blockPos.getX() + count2; index++) {
            for (int index2 = blockPos.getZ() - count2; index2 <= blockPos.getZ() + count2; index2++) {
               int count3 = index - blockPos.getX();
               int count4 = index2 - blockPos.getZ();
               if (count3 * count3 + count4 * count4 <= count2 * count2 + 1
                  && world.getBlockState(auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index, blockPos.getY() - 1, index2)).getBlock() != this.block) {
                  return false;
               }
            }
         }

         for (int index3 = blockPos.getY(); index3 < blockPos.getY() + count && index3 < 256; index3++) {
            for (int index4 = blockPos.getX() - count2; index4 <= blockPos.getX() + count2; index4++) {
               for (int index5 = blockPos.getZ() - count2; index5 <= blockPos.getZ() + count2; index5++) {
                  int count5 = index4 - blockPos.getX();
                  int count6 = index5 - blockPos.getZ();
                  if (count5 * count5 + count6 * count6 <= count2 * count2 + 1) {
                     world.matchesBlockPos(new BlockPos(index4, index3, index5), Blocks.block113.getDefaultState(), 2);
                  }
               }
            }
         }

         EntityEndercrystal entityEndercrystal = new EntityEndercrystal(world);
         entityEndercrystal.applyDoubleByDouble(
            (double)((float)blockPos.getX() + 0.5F), (double)(blockPos.getY() + count), (double)((float)blockPos.getZ() + 0.5F), random.nextFloat() * 360.0F, 0.0F
         );
         world.matchesEntity(entityEndercrystal);
         world.matchesBlockPos(blockPos.resolveBlockPos2(count), Blocks.block95.getDefaultState(), 2);
         return true;
      } else {
         return false;
      }
   }

   public AdvancedBlockEntry(Block block2) {
      this.block = block2;
   }
}
