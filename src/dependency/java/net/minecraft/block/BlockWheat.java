package net.minecraft.block;

import client.event.PreMotionFilter;
import client.util.AuxiliaryImmutableSetEntry;
import client.util.IBlockState;
import java.util.Random;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockWheat extends SecondaryBlockAdapter implements PreMotionFilter {
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry("age", 0, 7);

   public Item getWheat() {
      return Items.wheat;
   }

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      super.updateTick(world, blockPos, blockState, random);
      if (world.computeCountByIndex(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ()) >= 9) {
         int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
         if (comparable < 7) {
            float factor = computeFactor(this, world, blockPos);
            if (random.nextInt((int)(25.0F / factor) + 1) == 0) {
               world.matchesBlockPos(blockPos, blockState.resolveIBlockState(auxiliaryImmutableSetEntry, comparable + 1), 2);
            }
         }
      }
   }

   @Override
   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState, float factor, int count) {
      super.applyWorld3(world, blockPos, blockState, factor, 0);
      if (!world.state) {
         int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
         if (comparable >= 7) {
            int count2 = 3 + count;

            for (int index = 0; index < count2; index++) {
               if (world.random.nextInt(15) <= comparable) {
                  applyWorld(world, blockPos, new ItemStack(this.getWheatSeeds(), 1, 0));
               }
            }
         }
      }
   }

   public void applyWorld5(World world, BlockPos blockPos, IBlockState blockState) {
      int count = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) + MathHelper.nextInt(world.random, 2, 5);
      if (count > 7) {
         count = 7;
      }

      world.matchesBlockPos(blockPos, blockState.resolveIBlockState(auxiliaryImmutableSetEntry, count), 2);
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return this.getWheatSeeds();
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(auxiliaryImmutableSetEntry, count);
   }

   @Override
   public boolean matchesBlockByBlock(Block block) {
      return block == Blocks.block7;
   }

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos, IBlockState blockState) {
      this.applyWorld5(world, blockPos, blockState);
   }

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState, boolean flag) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) < 7;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) == 7 ? this.getWheat() : this.getWheatSeeds();
   }

   @Override
   public boolean nextFloat(World world, Random random, BlockPos blockPos, IBlockState blockState) {
      return true;
   }

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      return (world.computeCount(blockPos.getX(), blockPos.getY(), blockPos.getZ()) >= 8 || world.checkIndex2(blockPos.getX(), blockPos.getY(), blockPos.getZ()))
         && this.matchesBlockByBlock(world.getIBlockState(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ()).getBlock());
   }

   public static float computeFactor(Block block, World world, BlockPos blockPos) {
      float factor = 1.0F;
      BlockPos blockPos2 = blockPos.down();

      for (int index = -1; index <= 1; index++) {
         for (int index2 = -1; index2 <= 1; index2++) {
            float factor2 = 0.0F;
            IBlockState blockState = world.getBlockState(blockPos2.add(index, 0, index2));
            if (blockState.getBlock() == Blocks.block7) {
               factor2 = 1.0F;
               if ((Integer)blockState.getComparable(BlockFarmland.auxiliaryImmutableSetEntry) > 0) {
                  factor2 = 3.0F;
               }
            }

            if (index != 0 || index2 != 0) {
               factor2 /= 4.0F;
            }

            factor += factor2;
         }
      }

      BlockPos blockPosAsBlockPos = blockPos.getBlockPosAsBlockPos();
      BlockPos blockPos3 = blockPos.getBlockPos2();
      BlockPos blockPos4 = blockPos.getBlockPos();
      BlockPos blockPos5 = blockPos.getBlockPos3();
      boolean flag = block == world.getBlockState(blockPos4).getBlock() || block == world.getBlockState(blockPos5).getBlock();
      boolean flag2 = block == world.getBlockState(blockPosAsBlockPos).getBlock() || block == world.getBlockState(blockPos3).getBlock();
      if (flag && flag2) {
         factor /= 2.0F;
      } else if (block == world.getBlockState(blockPos4.getBlockPosAsBlockPos()).getBlock()
         || block == world.getBlockState(blockPos5.getBlockPosAsBlockPos()).getBlock()
         || block == world.getBlockState(blockPos5.getBlockPos2()).getBlock()
         || block == world.getBlockState(blockPos4.getBlockPos2()).getBlock()) {
         factor /= 2.0F;
      }

      return factor;
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{auxiliaryImmutableSetEntry});
   }

   public Item getWheatSeeds() {
      return Items.wheatSeeds;
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
   }

   public BlockWheat() {
      this.setIBlockState(this.blockManager.get().resolveIBlockState(auxiliaryImmutableSetEntry, 0));
      this.processAction(true);
      float factor = 0.5F;
      this.setBlockBounds(0.5F - factor, 0.0F, 0.5F - factor, 0.5F + factor, 0.25F, 0.5F + factor);
      this.processAction((ItemGroupManager)null);
      this.getBlock5(0.0F);
      this.processAction(stepManager26);
      this.processAction12();
   }
}
