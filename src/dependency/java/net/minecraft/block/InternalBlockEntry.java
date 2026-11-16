package net.minecraft.block;

import client.util.CurveParser;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class InternalBlockEntry extends CurveParser {
   public Block block;

   public InternalBlockEntry(Block block2) {
      this.block = block2;
   }

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      if (world.getBlockState(blockPos.up()).getBlock() != Blocks.stone) {
         return false;
      } else if (world.getBlockState(blockPos.down()).getBlock() != Blocks.stone) {
         return false;
      } else if (world.getBlockState(blockPos).getBlock().getMaterial() != Material.coreItemEntry && world.getBlockState(blockPos).getBlock() != Blocks.stone) {
         return false;
      } else {
         int count = 0;
         if (world.getBlockState(blockPos.getBlockPos()).getBlock() == Blocks.stone) {
            count++;
         }

         if (world.getBlockState(blockPos.getBlockPos3()).getBlock() == Blocks.stone) {
            count++;
         }

         if (world.getBlockState(blockPos.getBlockPosAsBlockPos()).getBlock() == Blocks.stone) {
            count++;
         }

         if (world.getBlockState(blockPos.getBlockPos2()).getBlock() == Blocks.stone) {
            count++;
         }

         int count2 = 0;
         if (world.isAirBlock(blockPos.getBlockPos())) {
            count2++;
         }

         if (world.isAirBlock(blockPos.getBlockPos3())) {
            count2++;
         }

         if (world.isAirBlock(blockPos.getBlockPosAsBlockPos())) {
            count2++;
         }

         if (world.isAirBlock(blockPos.getBlockPos2())) {
            count2++;
         }

         if (count == 3 && count2 == 1) {
            world.matchesBlockPos(blockPos, this.block.getDefaultState(), 2);
            world.applyBlock(this.block, blockPos, random);
         }

         return true;
      }
   }
}
