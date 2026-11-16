package net.minecraft.block;

import client.util.CurveParser;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BaseBlockEntry extends CurveParser {
   public final Block block;
   public final boolean enabled2;

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      if (world.getBlockState(blockPos.up()).getBlock() != Blocks.block96) {
         return false;
      } else if (world.getBlockState(blockPos).getBlock().getMaterial() != Material.coreItemEntry && world.getBlockState(blockPos).getBlock() != Blocks.block96) {
         return false;
      } else {
         int count = 0;
         if (world.getBlockState(blockPos.getBlockPos()).getBlock() == Blocks.block96) {
            count++;
         }

         if (world.getBlockState(blockPos.getBlockPos3()).getBlock() == Blocks.block96) {
            count++;
         }

         if (world.getBlockState(blockPos.getBlockPosAsBlockPos()).getBlock() == Blocks.block96) {
            count++;
         }

         if (world.getBlockState(blockPos.getBlockPos2()).getBlock() == Blocks.block96) {
            count++;
         }

         if (world.getBlockState(blockPos.down()).getBlock() == Blocks.block96) {
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

         if (world.isAirBlock(blockPos.down())) {
            count2++;
         }

         if (!this.enabled2 && count == 4 && count2 == 1 || count == 5) {
            world.matchesBlockPos(blockPos, this.block.getDefaultState(), 2);
            world.applyBlock(this.block, blockPos, random);
         }

         return true;
      }
   }

   public BaseBlockEntry(Block block2, boolean flag) {
      this.block = block2;
      this.enabled2 = flag;
   }
}
