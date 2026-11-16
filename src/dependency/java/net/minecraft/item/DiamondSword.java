package net.minecraft.item;

import client.util.IBlockState;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnowLayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class DiamondSword extends ItemBlock {
   @Override
   public int processAction2(int count) {
      return count;
   }

   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (stack.animationsToGo == 0) {
         return false;
      } else if (!player.matchesBlockPos(blockPos, enumFacing, stack)) {
         return false;
      } else {
         IBlockState blockState = world.getBlockState(blockPos);
         Block block = blockState.getBlock();
         BlockPos blockPos2 = blockPos;
         if ((enumFacing != EnumFacing.UP || block != this.block) && !block.processAction6(world, blockPos)) {
            blockPos2 = blockPos.offset(enumFacing);
            blockState = world.getBlockState(blockPos2);
            block = blockState.getBlock();
         }

         if (block == this.block) {
            int comparable = (Integer)blockState.getComparable(BlockSnowLayer.auxiliaryImmutableSetEntry);
            if (comparable <= 7) {
               IBlockState blockState2 = blockState.resolveIBlockState(BlockSnowLayer.auxiliaryImmutableSetEntry, comparable + 1);
               AxisAlignedBB boundingBox = this.block.processAction(world, blockState2, blockPos2.getX(), blockPos2.getY(), blockPos2.getZ());
               if (boundingBox != null && world.matchesAxisAlignedBB(boundingBox) && world.matchesBlockPos(blockPos2, blockState2, 2)) {
                  world.applyText(
                     (double)((float)blockPos2.getX() + 0.5F),
                     (double)((float)blockPos2.getY() + 0.5F),
                     (double)((float)blockPos2.getZ() + 0.5F),
                     this.block.stepManager20.getTextAsText(),
                     (this.block.stepManager20.getFactor2() + 1.0F) / 2.0F,
                     this.block.stepManager20.getFactor() * 0.8F
                  );
                  stack.animationsToGo--;
                  return true;
               }
            }
         }

         return super.processAction(stack, player, world, blockPos2, enumFacing, posX, posY, posZ);
      }
   }

   public DiamondSword(Block block) {
      super(block);
      this.processAction3(0);
      this.processAction(true);
   }
}
