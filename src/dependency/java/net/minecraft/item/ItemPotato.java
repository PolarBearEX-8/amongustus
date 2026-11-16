package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemPotato extends ItemFood {
   public Block block;
   public Block block2;

   @Override
   public boolean processAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (enumFacing != EnumFacing.UP) {
         return false;
      } else if (!player.matchesBlockPos(blockPos.offset(enumFacing), enumFacing, stack)) {
         return false;
      } else if (world.getBlockState(blockPos).getBlock() == this.block2 && world.isAirBlock(blockPos.up())) {
         world.matchesBlockPos(blockPos.up(), this.block.getDefaultState());
         stack.animationsToGo--;
         return true;
      } else {
         return false;
      }
   }

   public ItemPotato(int count, float factor, Block block3, Block block4) {
      super(count, factor, false);
      this.block = block3;
      this.block2 = block4;
   }
}
