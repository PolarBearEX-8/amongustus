package net.minecraft.block.state;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockPistonStructureHelper {
   private final World world;
   private final BlockPos pistonPos;
   private final BlockPos blockToMove;
   private final EnumFacing moveDirection;
   private final List toMove = Lists.newArrayList();
   private final List toDestroy = Lists.newArrayList();

   public List getBlocksToMove() {
      return this.toMove;
   }

   private void reorderListAtCollision(int n, int n2) {
      ArrayList list = Lists.newArrayList();
      ArrayList list2 = Lists.newArrayList();
      ArrayList list3 = Lists.newArrayList();
      list.addAll(this.toMove.subList(0, n2));
      list2.addAll(this.toMove.subList(this.toMove.size() - n, this.toMove.size()));
      list3.addAll(this.toMove.subList(n2, this.toMove.size() - n));
      this.toMove.clear();
      this.toMove.addAll(list);
      this.toMove.addAll(list2);
      this.toMove.addAll(list3);
   }

   public List getBlocksToDestroy() {
      return this.toDestroy;
   }

   private boolean addBlockLine(BlockPos pos) {
      Block block = this.world.getBlockState(pos).getBlock();
      if (block.getMaterial() == Material.air) {
         return true;
      } else if (!BlockPistonBase.canPush(block, this.world, pos, this.moveDirection, false)) {
         return true;
      } else if (pos.equals(this.pistonPos)) {
         return true;
      } else if (this.toMove.contains(pos)) {
         return true;
      } else {
         int n = 1;
         if (n + this.toMove.size() > 12) {
            return false;
         } else {
            while (block == Blocks.slime_block) {
               BlockPos pos2 = pos.offset(this.moveDirection.getOpposite(), n);
               block = this.world.getBlockState(pos2).getBlock();
               if (block.getMaterial() == Material.air
                  || !BlockPistonBase.canPush(block, this.world, pos2, this.moveDirection, false)
                  || pos2.equals(this.pistonPos)) {
                  break;
               }

               if (++n + this.toMove.size() > 12) {
                  return false;
               }
            }

            int n2 = 0;

            for (int i = n - 1; i >= 0; i--) {
               this.toMove.add(pos.offset(this.moveDirection.getOpposite(), i));
               n2++;
            }

            int n3 = 1;

            while (true) {
               BlockPos pos3 = pos.offset(this.moveDirection, n3);
               int n4 = this.toMove.indexOf(pos3);
               if (n4 > -1) {
                  this.reorderListAtCollision(n2, n4);

                  for (int j = 0; j <= n4 + n2; j++) {
                     BlockPos pos4 = (BlockPos)this.toMove.get(j);
                     if (this.world.getBlockState(pos4).getBlock() == Blocks.slime_block && !this.addBranchingBlocks(pos4)) {
                        return false;
                     }
                  }

                  return true;
               }

               block = this.world.getBlockState(pos3).getBlock();
               if (block.getMaterial() == Material.air) {
                  return true;
               }

               if (!BlockPistonBase.canPush(block, this.world, pos3, this.moveDirection, true) || pos3.equals(this.pistonPos)) {
                  return false;
               }

               if (block.getMobilityFlag() == 1) {
                  this.toDestroy.add(pos3);
                  return true;
               }

               if (this.toMove.size() >= 12) {
                  return false;
               }

               this.toMove.add(pos3);
               n2++;
               n3++;
            }
         }
      }
   }

   public BlockPistonStructureHelper(World world2, BlockPos pos, EnumFacing facing, boolean flag) {
      this.world = world2;
      this.pistonPos = pos;
      if (flag) {
         this.moveDirection = facing;
         this.blockToMove = pos.offset(facing);
      } else {
         this.moveDirection = facing.getOpposite();
         this.blockToMove = pos.offset(facing, 2);
      }
   }

   public boolean canMove() {
      this.toMove.clear();
      this.toDestroy.clear();
      Block block = this.world.getBlockState(this.blockToMove).getBlock();
      if (!BlockPistonBase.canPush(block, this.world, this.blockToMove, this.moveDirection, false)) {
         if (block.getMobilityFlag() != 1) {
            return false;
         } else {
            this.toDestroy.add(this.blockToMove);
            return true;
         }
      } else if (!this.addBlockLine(this.blockToMove)) {
         return false;
      } else {
         for (int i = 0; i < this.toMove.size(); i++) {
            BlockPos pos = (BlockPos)this.toMove.get(i);
            if (this.world.getBlockState(pos).getBlock() == Blocks.slime_block && !this.addBranchingBlocks(pos)) {
               return false;
            }
         }

         return true;
      }
   }

   private boolean addBranchingBlocks(BlockPos pos) {
      for (EnumFacing facing : EnumFacing.getValues()) {
         if (facing.getAxis() != this.moveDirection.getAxis() && !this.addBlockLine(pos.offset(facing))) {
            return false;
         }
      }

      return true;
   }
}
