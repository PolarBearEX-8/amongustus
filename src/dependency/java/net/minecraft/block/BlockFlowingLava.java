package net.minecraft.block;

import client.setting.MovementFactorProperty;
import client.util.IBlockState;
import java.util.EnumSet;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockFlowingLava extends BlockLiquid {
   public int count;

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
      byte byteValue = 1;
      if (this.coreItemEntry == Material.material29 && !world.secondaryFloatArrayEntry.isEnabled2()) {
         byteValue = 2;
      }

      int count2 = this.processAction(world);
      if (comparable > 0) {
         int count3 = -100;
         this.count = 0;

         for (EnumFacing enumFacing : (Iterable<EnumFacing>) (MovementFactorProperty.HORIZONTAL)) {
            count3 = this.computeCountByWorld(world, blockPos.offset(enumFacing), count3);
         }

         int count4 = count3 + byteValue;
         if (count4 >= 8 || count3 < 0) {
            count4 = -1;
         }

         if (this.computeCount(world, blockPos.up()) >= 0) {
            count4 = this.computeCount(world, blockPos.up());
            if (count4 < 8) {
               count4 += 8;
            }
         }

         if (this.count >= 2 && this.coreItemEntry == Material.coreItemEntry1) {
            IBlockState blockState2 = world.getBlockState(blockPos.down());
            if (blockState2.getBlock().getMaterial().canExecute2()) {
               count4 = 0;
            } else if (blockState2.getBlock().getMaterial() == this.coreItemEntry && (Integer)blockState2.getComparable(auxiliaryImmutableSetEntry) == 0) {
               count4 = 0;
            }
         }

         if (this.coreItemEntry == Material.material29 && comparable < 8 && count4 < 8 && count4 > comparable && random.nextInt(4) != 0) {
            count2 *= 4;
         }

         if (count4 == comparable) {
            this.applyWorld4(world, blockPos, blockState);
         } else {
            comparable = count4;
            if (count4 < 0) {
               world.matchesBlockPos8(blockPos);
            } else {
               IBlockState blockState3 = blockState.resolveIBlockState(auxiliaryImmutableSetEntry, count4);
               world.matchesBlockPos(blockPos, blockState3, 2);
               world.applyBlockPos(blockPos, this, count2);
               world.applyBlockPosByBlockPos(blockPos, this);
            }
         }
      } else {
         this.applyWorld4(world, blockPos, blockState);
      }

      IBlockState blockState4 = world.getBlockState(blockPos.down());
      if (this.matchesWorld4(world, blockPos.down(), blockState4)) {
         if (this.coreItemEntry == Material.material29 && world.getBlockState(blockPos.down()).getBlock().getMaterial() == Material.coreItemEntry1) {
            world.matchesBlockPos(blockPos.down(), Blocks.stone.getDefaultState());
            this.applyWorld2(world, blockPos.down());
            return;
         }

         if (comparable >= 8) {
            this.applyWorldByWorld(world, blockPos.down(), blockState4, comparable);
         } else {
            this.applyWorldByWorld(world, blockPos.down(), blockState4, comparable + 8);
         }
      } else if (comparable >= 0 && (comparable == 0 || this.matchesWorld2(world, blockPos.down(), blockState4))) {
         Set set = this.resolveSet(world, blockPos);
         int count5 = comparable + byteValue;
         if (comparable >= 8) {
            count5 = 1;
         }

         if (count5 >= 8) {
            return;
         }

         for (EnumFacing enumFacing2 : (Iterable<EnumFacing>) (set)) {
            this.applyWorldByWorld(world, blockPos.offset(enumFacing2), world.getBlockState(blockPos.offset(enumFacing2)), count5);
         }
      }
   }

   public int computeCountByWorld(World world, BlockPos blockPos, int count2) {
      int count3 = this.computeCount(world, blockPos);
      if (count3 < 0) {
         return count2;
      } else {
         if (count3 == 0) {
            this.count++;
         }

         if (count3 >= 8) {
            count3 = 0;
         }

         return count2 >= 0 && count3 >= count2 ? count2 : count3;
      }
   }

   private boolean matchesWorld4(World world, BlockPos blockPos, IBlockState blockState) {
      Material material = blockState.getBlock().getMaterial();
      return material != this.coreItemEntry && material != Material.material29 && !this.matchesWorld2(world, blockPos, blockState);
   }

   private int computeCount(World world, BlockPos blockPos, int count2, EnumFacing enumFacing) {
      int count3 = 1000;

      for (EnumFacing enumFacing2 : (Iterable<EnumFacing>) (MovementFactorProperty.HORIZONTAL)) {
         if (enumFacing2 != enumFacing) {
            BlockPos blockPos2 = blockPos.offset(enumFacing2);
            IBlockState blockState = world.getBlockState(blockPos2);
            if (!this.matchesWorld2(world, blockPos2, blockState)
               && (blockState.getBlock().getMaterial() != this.coreItemEntry || (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) > 0)) {
               if (!this.matchesWorld2(world, blockPos2.down(), blockState)) {
                  return count2;
               }

               if (count2 < 4) {
                  int count4 = this.computeCount(world, blockPos2, count2 + 1, enumFacing2.getEnumFacing5());
                  if (count4 < count3) {
                     count3 = count4;
                  }
               }
            }
         }
      }

      return count3;
   }

   private boolean matchesWorld2(World world, BlockPos blockPos, IBlockState blockState) {
      Block block = world.getBlockState(blockPos).getBlock();
      return block instanceof BlockDarkOakDoor
         || block == Blocks.block37
         || block == Blocks.block81
         || block == Blocks.blockReeds
         || block.coreItemEntry == Material.material17
         || block.coreItemEntry.isActiveAsFlag();
   }

   private void applyWorldByWorld(World world, BlockPos blockPos, IBlockState blockState, int count2) {
      if (this.matchesWorld4(world, blockPos, blockState)) {
         if (blockState.getBlock() != Blocks.air) {
            if (this.coreItemEntry == Material.material29) {
               this.applyWorld2(world, blockPos);
            } else {
               blockState.getBlock().processAction(world, blockPos, blockState, 0);
            }
         }

         world.matchesBlockPos(blockPos, this.getDefaultState().resolveIBlockState(auxiliaryImmutableSetEntry, count2), 3);
      }
   }

   private void applyWorld4(World world, BlockPos blockPos, IBlockState blockState) {
      world.matchesBlockPos(
         blockPos,
         resolveBlockLava(this.coreItemEntry)
            .getDefaultState()
            .resolveIBlockState(auxiliaryImmutableSetEntry, blockState.getComparable(auxiliaryImmutableSetEntry)),
         2
      );
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState) {
      if (!this.matchesWorld(world, blockPos, blockState)) {
         world.applyBlockPos(blockPos, this, this.processAction(world));
      }
   }

   private Set resolveSet(World world, BlockPos blockPos) {
      int count2 = 1000;
      EnumSet enumSet = EnumSet.noneOf(EnumFacing.class);

      for (EnumFacing enumFacing : (Iterable<EnumFacing>) (MovementFactorProperty.HORIZONTAL)) {
         BlockPos blockPos2 = blockPos.offset(enumFacing);
         IBlockState blockState = world.getBlockState(blockPos2);
         if (!this.matchesWorld2(world, blockPos2, blockState)
            && (blockState.getBlock().getMaterial() != this.coreItemEntry || (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) > 0)) {
            int count3;
            if (this.matchesWorld2(world, blockPos2.down(), world.getBlockState(blockPos2.down()))) {
               count3 = this.computeCount(world, blockPos2, 1, enumFacing.getEnumFacing5());
            } else {
               count3 = 0;
            }

            if (count3 < count2) {
               enumSet.clear();
            }

            if (count3 <= count2) {
               enumSet.add(enumFacing);
               count2 = count3;
            }
         }
      }

      return enumSet;
   }

   public BlockFlowingLava(Material material) {
      super(material);
   }
}
