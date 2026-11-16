package client.util;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPiston;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class RotationReader {
   public final World world;
   public final BlockPos blockPos;
   public final BlockPos blockPos2;
   public final EnumFacing enumFacing;
   public final List list = Lists.newArrayList();
   public final List list2 = Lists.newArrayList();

   private boolean matchesBlockPosByBlockPos(BlockPos blockPos3) {
      for (EnumFacing enumFacing2 : EnumFacing.getEntries()) {
         if (enumFacing2.getEnumFacingXType() != this.enumFacing.getEnumFacingXType() && !this.matchesBlockPos(blockPos3.offset(enumFacing2))) {
            return false;
         }
      }

      return true;
   }

   private void applyIndex(int count, int count2) {
      ArrayList list3 = Lists.newArrayList();
      ArrayList list4 = Lists.newArrayList();
      ArrayList list5 = Lists.newArrayList();
      list3.addAll(this.list.subList(0, count2));
      list4.addAll(this.list.subList(this.list.size() - count, this.list.size()));
      list5.addAll(this.list.subList(count2, this.list.size() - count));
      this.list.clear();
      this.list.addAll(list3);
      this.list.addAll(list4);
      this.list.addAll(list5);
   }

   public RotationReader(World world2, BlockPos blockPos3, EnumFacing enumFacing2, boolean flag) {
      this.world = world2;
      this.blockPos = blockPos3;
      if (flag) {
         this.enumFacing = enumFacing2;
         this.blockPos2 = blockPos3.offset(enumFacing2);
      } else {
         this.enumFacing = enumFacing2.getEnumFacing5();
         this.blockPos2 = blockPos3.getBlockPos(enumFacing2, 2);
      }
   }

   private boolean matchesBlockPos(BlockPos blockPos3) {
      Block block = this.world.getBlockState(blockPos3).getBlock();
      if (block.getMaterial() == Material.coreItemEntry) {
         return true;
      } else if (!BlockPiston.matchesBlock(block, this.world, blockPos3, this.enumFacing, false)) {
         return true;
      } else if (blockPos3.equals(this.blockPos)) {
         return true;
      } else if (this.list.contains(blockPos3)) {
         return true;
      } else {
         int count = 1;
         if (1 + this.list.size() > 12) {
            return false;
         } else {
            while (block == Blocks.block71) {
               BlockPos blockPos4 = blockPos3.getBlockPos(this.enumFacing.getEnumFacing5(), count);
               block = this.world.getBlockState(blockPos4).getBlock();
               if (block.getMaterial() == Material.coreItemEntry
                  || !BlockPiston.matchesBlock(block, this.world, blockPos4, this.enumFacing, false)
                  || blockPos4.equals(this.blockPos)) {
                  break;
               }

               if (++count + this.list.size() > 12) {
                  return false;
               }
            }

            int count2 = 0;

            for (int index = count - 1; index >= 0; index--) {
               this.list.add(blockPos3.getBlockPos(this.enumFacing.getEnumFacing5(), index));
               count2++;
            }

            int count3 = 1;

            while (true) {
               BlockPos blockPos5 = blockPos3.getBlockPos(this.enumFacing, count3);
               int count4 = this.list.indexOf(blockPos5);
               if (count4 > -1) {
                  this.applyIndex(count2, count4);

                  for (int index2 = 0; index2 <= count4 + count2; index2++) {
                     BlockPos blockPos6 = (BlockPos)this.list.get(index2);
                     if (this.world.getBlockState(blockPos6).getBlock() == Blocks.block71 && !this.matchesBlockPosByBlockPos(blockPos6)) {
                        return false;
                     }
                  }

                  return true;
               }

               Block block2 = this.world.getBlockState(blockPos5).getBlock();
               if (block2.getMaterial() == Material.coreItemEntry) {
                  return true;
               }

               if (!BlockPiston.matchesBlock(block2, this.world, blockPos5, this.enumFacing, true) || blockPos5.equals(this.blockPos)) {
                  return false;
               }

               if (block2.processAction8() == 1) {
                  this.list2.add(blockPos5);
                  return true;
               }

               if (this.list.size() >= 12) {
                  return false;
               }

               this.list.add(blockPos5);
               count2++;
               count3++;
            }
         }
      }
   }

   public List getList2() {
      return this.list2;
   }

   public List getList() {
      return this.list;
   }

   public boolean isActive() {
      this.list.clear();
      this.list2.clear();
      Block block = this.world.getBlockState(this.blockPos2).getBlock();
      if (!BlockPiston.matchesBlock(block, this.world, this.blockPos2, this.enumFacing, false)) {
         if (block.processAction8() != 1) {
            return false;
         } else {
            this.list2.add(this.blockPos2);
            return true;
         }
      } else if (!this.matchesBlockPos(this.blockPos2)) {
         return false;
      } else {
         for (int index = 0; index < this.list.size(); index++) {
            BlockPos blockPos3 = (BlockPos)this.list.get(index);
            if (this.world.getBlockState(blockPos3).getBlock() == Blocks.block71 && !this.matchesBlockPosByBlockPos(blockPos3)) {
               return false;
            }
         }

         return true;
      }
   }
}
