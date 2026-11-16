package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemRedstone extends Item {
   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      BlockPos blockPos2 = world.getBlockState(blockPos).getBlock().processAction6(world, blockPos) ? blockPos : blockPos.offset(enumFacing);
      if (!player.matchesBlockPos(blockPos2, enumFacing, stack)) {
         return false;
      } else {
         Block block = world.getBlockState(blockPos2).getBlock();
         if (!world.matchesBlock(block, blockPos2, false, enumFacing, (Entity)null, stack)) {
            return false;
         } else if (Blocks.blockRedstoneWire.processAction7(world, blockPos2)) {
            stack.animationsToGo--;
            world.matchesBlockPos(blockPos2, Blocks.blockRedstoneWire.getDefaultState());
            return true;
         } else {
            return false;
         }
      }
   }

   public ItemRedstone() {
      this.processAction(ItemGroupManager.itemGroupManager9);
   }
}
