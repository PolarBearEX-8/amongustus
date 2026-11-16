package net.minecraft.item;

import client.util.IBlockState;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnowLayer;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemComparator extends Item {
   public Block block;

   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      BlockPos blockPos2 = blockPos;
      EnumFacing enumFacing2 = enumFacing;
      IBlockState blockState = world.getBlockState(blockPos);
      Block block2 = blockState.getBlock();
      if (block2 == Blocks.block74 && (Integer)blockState.getComparable(BlockSnowLayer.auxiliaryImmutableSetEntry) < 1) {
         enumFacing2 = EnumFacing.UP;
      } else if (!block2.processAction6(world, blockPos)) {
         blockPos2 = blockPos.offset(enumFacing);
      }

      if (!player.matchesBlockPos(blockPos2, enumFacing2, stack)) {
         return false;
      } else if (stack.animationsToGo == 0) {
         return false;
      } else {
         if (world.matchesBlock(this.block, blockPos2, false, enumFacing2, (Entity)null, stack)) {
            IBlockState blockState2 = this.block.onBlockPlaced(world, blockPos2, enumFacing2, posX, posY, posZ, 0, player);
            if (world.matchesBlockPos(blockPos2, blockState2, 3)) {
               IBlockState blockState3 = world.getBlockState(blockPos2);
               if (blockState3.getBlock() == this.block) {
                  ItemBlock.matchesWorld(world, player, blockPos2, stack);
                  blockState3.getBlock().processAction(world, blockPos2, blockState3, player, stack);
               }

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

         return false;
      }
   }

   public ItemComparator(Block block2) {
      this.block = block2;
   }
}
