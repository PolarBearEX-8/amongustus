package client.util;

import client.setting.EnumFacingXType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class WorldEntry {
   public final World world;
   public final EnumFacingXType enumFacingXType;
   public final EnumFacing enumFacing;
   public final EnumFacing enumFacing2;
   public int count = 0;
   public BlockPos blockPos;
   public int count2;
   public int count3;

   public WorldEntry(World world2, BlockPos blockPos2, EnumFacingXType enumFacingXType2) {
      this.world = world2;
      this.enumFacingXType = enumFacingXType2;
      if (enumFacingXType2 == EnumFacingXType.X) {
         this.enumFacing2 = EnumFacing.EAST;
         this.enumFacing = EnumFacing.WEST;
      } else {
         this.enumFacing2 = EnumFacing.NORTH;
         this.enumFacing = EnumFacing.SOUTH;
      }

      BlockPos blockPos3 = blockPos2;

      while (blockPos2.getY() > blockPos3.getY() - 21 && blockPos2.getY() > 0 && this.matchesBlock(world2.getBlockState(blockPos2.down()).getBlock())) {
         blockPos2 = blockPos2.down();
      }

      int count4 = this.computeCount(blockPos2, this.enumFacing2) - 1;
      if (count4 >= 0) {
         this.blockPos = blockPos2.getBlockPos(this.enumFacing2, count4);
         this.count3 = this.computeCount(this.blockPos, this.enumFacing);
         if (this.count3 < 2 || this.count3 > 21) {
            this.blockPos = null;
            this.count3 = 0;
         }
      }

      if (this.blockPos != null) {
         this.count2 = this.getCount();
      }
   }

   public int computeCount(BlockPos blockPos2, EnumFacing enumFacing3) {
      int count4;
      for (count4 = 0; count4 < 22; count4++) {
         BlockPos blockPos3 = blockPos2.getBlockPos(enumFacing3, count4);
         if (!this.matchesBlock(this.world.getBlockState(blockPos3).getBlock()) || this.world.getBlockState(blockPos3.down()).getBlock() != Blocks.block113) {
            break;
         }
      }

      return this.world.getBlockState(blockPos2.getBlockPos(enumFacing3, count4)).getBlock() == Blocks.block113 ? count4 : 0;
   }

   public static int computeCountByWorldEntry(WorldEntry worldEntry) {
      return worldEntry.count;
   }

   public static EnumFacing resolveEnumFacing(WorldEntry worldEntry) {
      return worldEntry.enumFacing;
   }

   public boolean isActive() {
      return this.blockPos != null && this.count3 >= 2 && this.count3 <= 21 && this.count2 >= 3 && this.count2 <= 21;
   }

   public int getCount() {
      outerLoop:
      for (this.count2 = 0; this.count2 < 21; this.count2++) {
         for (int index = 0; index < this.count3; index++) {
            BlockPos blockPos2 = this.blockPos.getBlockPos(this.enumFacing, index).resolveBlockPos2(this.count2);
            Block block = this.world.getBlockState(blockPos2).getBlock();
            if (!this.matchesBlock(block)) {
               break outerLoop;
            }

            if (block == Blocks.blockPortal) {
               this.count++;
            }

            if (index == 0
               ? this.world.getBlockState(blockPos2.offset(this.enumFacing2)).getBlock() != Blocks.block113
               : index == this.count3 - 1 && this.world.getBlockState(blockPos2.offset(this.enumFacing)).getBlock() != Blocks.block113) {
               break outerLoop;
            }
         }
      }

      for (int index2 = 0; index2 < this.count3; index2++) {
         if (this.world.getBlockState(this.blockPos.getBlockPos(this.enumFacing, index2).resolveBlockPos2(this.count2)).getBlock() != Blocks.block113) {
            this.count2 = 0;
            break;
         }
      }

      if (this.count2 <= 21 && this.count2 >= 3) {
         return this.count2;
      } else {
         this.blockPos = null;
         this.count3 = 0;
         this.count2 = 0;
         return 0;
      }
   }

   public static int computeCount2(WorldEntry worldEntry) {
      return worldEntry.count3;
   }

   public int getCount3() {
      return this.count3;
   }

   public void updateState() {
      for (int index = 0; index < this.count3; index++) {
         BlockPos blockPos2 = this.blockPos.getBlockPos(this.enumFacing, index);

         for (int index2 = 0; index2 < this.count2; index2++) {
            this.world
               .matchesBlockPos(
                  blockPos2.resolveBlockPos2(index2), Blocks.blockPortal.getDefaultState().resolveIBlockState(BlockPortal.immutableSetEntry, this.enumFacingXType), 2
               );
         }
      }
   }

   public static BlockPos resolveBlockPos(WorldEntry worldEntry) {
      return worldEntry.blockPos;
   }

   public int getCount2() {
      return this.count2;
   }

   public boolean matchesBlock(Block block) {
      return block.coreItemEntry == Material.coreItemEntry || block == Blocks.blockFire || block == Blocks.blockPortal;
   }

   public static int computeCount(WorldEntry worldEntry) {
      return worldEntry.count2;
   }
}
