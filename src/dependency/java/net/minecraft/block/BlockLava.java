package net.minecraft.block;

import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockLava extends BlockLiquid {
   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (this.coreItemEntry == Material.material29 && world.getDoFireTickManager().matchesText("doFireTick")) {
         int count = random.nextInt(3);
         if (count > 0) {
            BlockPos blockPos2 = blockPos;

            for (int index = 0; index < count; index++) {
               blockPos2 = blockPos2.add(random.nextInt(3) - 1, 1, random.nextInt(3) - 1);
               Block block = world.getBlockState(blockPos2).getBlock();
               if (block.coreItemEntry == Material.coreItemEntry) {
                  if (this.matchesWorld3(world, blockPos2)) {
                     world.matchesBlockPos(blockPos2, Blocks.blockFire.getDefaultState());
                     return;
                  }
               } else if (block.coreItemEntry.isActiveAsFlag()) {
                  return;
               }
            }
         } else {
            for (int index2 = 0; index2 < 3; index2++) {
               BlockPos blockPos3 = blockPos.add(random.nextInt(3) - 1, 0, random.nextInt(3) - 1);
               if (world.isAirBlock(blockPos3.up()) && this.matchesWorld5(world, blockPos3)) {
                  world.matchesBlockPos(blockPos3.up(), Blocks.blockFire.getDefaultState());
               }
            }
         }
      }
   }

   public boolean matchesWorld3(World world, BlockPos blockPos) {
      for (EnumFacing enumFacing : EnumFacing.getEntries()) {
         if (this.matchesWorld5(world, blockPos.offset(enumFacing))) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      if (!this.matchesWorld(world, blockPos, blockState)) {
         this.applyWorld4(world, blockPos, blockState);
      }
   }

   private void applyWorld4(World world, BlockPos blockPos, IBlockState blockState) {
      BlockFlowingLava blockFlowingLava = resolveBlockFlowingLava(this.coreItemEntry);
      world.matchesBlockPos(blockPos, blockFlowingLava.getDefaultState().resolveIBlockState(auxiliaryImmutableSetEntry, blockState.getComparable(auxiliaryImmutableSetEntry)), 2);
      world.applyBlockPos(blockPos, blockFlowingLava, this.processAction(world));
   }

   private boolean matchesWorld5(World world, BlockPos blockPos) {
      return world.getBlockState(blockPos).getBlock().getMaterial().isEnabled();
   }

   public BlockLava(Material material) {
      super(material);
      this.processAction(false);
      if (material == Material.material29) {
         this.processAction(true);
      }
   }
}
