package net.minecraft.block;

import client.event.PreMotionFilter;
import client.setting.MovementOffsetProperty;
import client.util.IBlockState;
import java.util.Random;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockRedMushroom extends SecondaryBlockAdapter implements PreMotionFilter {
   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      BlockPos blockPos2 = blockPos;
      if (random.nextInt(25) == 0) {
         int count = 5;

         for (BlockPos blockPos3 : (Iterable<BlockPos>) (Iterable) (BlockPos.resolveIterableByBlockPos(blockPos.add(-4, -1, -4), blockPos.add(4, 1, 4)))) {
            if (world.getBlockState(blockPos3).getBlock() == this) {
               if (--count <= 0) {
                  return;
               }
            }
         }

         BlockPos blockPos4 = blockPos.add(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);

         for (int index = 0; index < 4; index++) {
            if (world.isAirBlock(blockPos4) && this.matchesWorld(world, blockPos4, this.getDefaultState())) {
               blockPos2 = blockPos4;
            }

            blockPos4 = blockPos2.add(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
         }

         if (world.isAirBlock(blockPos4) && this.matchesWorld(world, blockPos4, this.getDefaultState())) {
            world.matchesBlockPos(blockPos4, this.getDefaultState(), 2);
         }
      }
   }

   @Override
   public boolean matchesBlockByBlock(Block block) {
      return block.isState18();
   }

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos, IBlockState blockState) {
      this.matchesWorld(world, blockPos, blockState, random);
   }

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState, boolean flag) {
      return true;
   }

   @Override
   public boolean nextFloat(World world, Random random, BlockPos blockPos, IBlockState blockState) {
      return (double)random.nextFloat() < 0.4;
   }

   @Override
   public boolean processAction7(World world, BlockPos blockPos) {
      return super.processAction7(world, blockPos) && this.matchesWorld(world, blockPos, this.getDefaultState());
   }

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      if (blockPos.getY() >= 0 && blockPos.getY() < 256) {
         IBlockState blockState2 = world.getBlockState(blockPos.down());
         return blockState2.getBlock() == Blocks.blockMycelium
            ? true
            : (
               blockState2.getBlock() == Blocks.block115 && blockState2.getComparable(BlockDirt.immutableSetEntry) == MovementOffsetProperty.PODZOL
                  ? true
                  : world.computeCount(blockPos.getX(), blockPos.getY(), blockPos.getZ()) < 13 && this.matchesBlockByBlock(blockState2.getBlock())
            );
      } else {
         return false;
      }
   }

   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      world.matchesBlockPos8(blockPos);
      SecondaryBlockEntry secondaryBlockEntry = null;
      if (this == Blocks.secondaryBlockAdapter2) {
         secondaryBlockEntry = new SecondaryBlockEntry(Blocks.block54);
      } else if (this == Blocks.secondaryBlockAdapter) {
         secondaryBlockEntry = new SecondaryBlockEntry(Blocks.block41);
      }

      if (secondaryBlockEntry != null && secondaryBlockEntry.matchesWorld(world, random, blockPos)) {
         return true;
      } else {
         world.matchesBlockPos(blockPos, blockState, 3);
         return false;
      }
   }

   public BlockRedMushroom() {
      float factor = 0.2F;
      this.setBlockBounds(0.5F - factor, 0.0F, 0.5F - factor, 0.5F + factor, factor * 2.0F, 0.5F + factor);
      this.processAction(true);
   }
}
