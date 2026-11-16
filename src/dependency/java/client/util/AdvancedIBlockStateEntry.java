package client.util;

import client.setting.MovementOffsetProperty;
import client.setting.MovementProperty;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class AdvancedIBlockStateEntry extends StandardIBlockStateEntry {
   public static final IBlockState iBlockState4 = Blocks.block140.getDefaultState().resolveIBlockState(BlockLog.immutableSetEntry3, MovementProperty.SPRUCE);
   public static final IBlockState iBlockState5 = Blocks.decayableManager2
      .getDefaultState()
      .resolveIBlockState(BlockLeaves.immutableSetEntry, MovementProperty.SPRUCE)
      .resolveIBlockState(DecayableManager.internalImmutableSetEntry, Boolean.FALSE);
   public static final IBlockState iBlockState6 = Blocks.block115
      .getDefaultState()
      .resolveIBlockState(BlockDirt.immutableSetEntry, MovementOffsetProperty.PODZOL);
   public boolean enabled2;

   private void applyWorld4(World world, BlockPos blockPos) {
      for (int index = 2; index >= -3; index--) {
         BlockPos blockPos2 = blockPos.resolveBlockPos2(index);
         Block block = world.getBlockState(blockPos2).getBlock();
         if (block == Blocks.blockGrass || block == Blocks.block115) {
            this.applyWorld(world, blockPos2, iBlockState6);
            break;
         }

         if (block.getMaterial() != Material.coreItemEntry && index < 0) {
            break;
         }
      }
   }

   @Override
   public void applyWorld(World world, Random random, BlockPos blockPos) {
      this.applyWorld6(world, blockPos.getBlockPos().getBlockPosAsBlockPos());
      this.applyWorld6(world, blockPos.resolveBlockPosByIndex(2).getBlockPosAsBlockPos());
      this.applyWorld6(world, blockPos.getBlockPos().resolveBlockPos(2));
      this.applyWorld6(world, blockPos.resolveBlockPosByIndex(2).resolveBlockPos(2));

      for (int index = 0; index < 5; index++) {
         int count = random.nextInt(64);
         int count2 = count % 8;
         int count3 = count / 8;
         if (count2 == 0 || count2 == 7 || count3 == 0 || count3 == 7) {
            this.applyWorld6(world, blockPos.add(-3 + count2, 0, -3 + count3));
         }
      }
   }

   private void applyWorld6(World world, BlockPos blockPos) {
      for (int index = -2; index <= 2; index++) {
         for (int index2 = -2; index2 <= 2; index2++) {
            if (Math.abs(index) != 2 || Math.abs(index2) != 2) {
               this.applyWorld4(world, blockPos.add(index, 0, index2));
            }
         }
      }
   }

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      int count = this.computeCount(random);
      if (!this.matchesWorld(world, random, blockPos, count)) {
         return false;
      } else {
         this.applyWorld(world, blockPos.getX(), blockPos.getZ(), blockPos.getY() + count, 0, random);

         for (int index = 0; index < count; index++) {
            Block block = world.getBlockState(blockPos.resolveBlockPos2(index)).getBlock();
            if (block.getMaterial() == Material.coreItemEntry || block.getMaterial() == Material.material9) {
               this.applyWorld(world, blockPos.resolveBlockPos2(index), this.iBlockState);
            }

            if (index < count - 1) {
               Block block2 = world.getBlockState(blockPos.add(1, index, 0)).getBlock();
               if (block2.getMaterial() == Material.coreItemEntry || block2.getMaterial() == Material.material9) {
                  this.applyWorld(world, blockPos.add(1, index, 0), this.iBlockState);
               }

               Block block3 = world.getBlockState(blockPos.add(1, index, 1)).getBlock();
               if (block3.getMaterial() == Material.coreItemEntry || block3.getMaterial() == Material.material9) {
                  this.applyWorld(world, blockPos.add(1, index, 1), this.iBlockState);
               }

               Block block4 = world.getBlockState(blockPos.add(0, index, 1)).getBlock();
               if (block4.getMaterial() == Material.coreItemEntry || block4.getMaterial() == Material.material9) {
                  this.applyWorld(world, blockPos.add(0, index, 1), this.iBlockState);
               }
            }
         }

         return true;
      }
   }

   private void applyWorld(World world, int count, int count2, int count3, int count4, Random random) {
      int count5 = random.nextInt(5) + (this.enabled2 ? this.count2 : 3);
      int count6 = 0;

      for (int index = count3 - count5; index <= count3; index++) {
         int count7 = count3 - index;
         int count8 = count4 + MathHelper.floor_float((float)count7 / (float)count5 * 3.5F);
         this.applyWorld2(world, new BlockPos(count, index, count2), count8 + (count7 > 0 && count8 == count6 && (index & 1) == 0 ? 1 : 0));
         count6 = count8;
      }
   }

   public AdvancedIBlockStateEntry(boolean flag, boolean flag2) {
      super(flag, 13, 15, iBlockState4, iBlockState5);
      this.enabled2 = flag2;
   }
}
