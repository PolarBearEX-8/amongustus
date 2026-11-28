package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemRedstone extends Item {
   @Override
   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      BlockPos pos2 = world.getBlockState(pos).getBlock().isReplaceable(world, pos) ? pos : pos.offset(facing);
      if (!player.canPlayerEdit(pos2, facing, stack)) {
         return false;
      } else {
         if (!world.canBlockBePlaced(world.getBlockState(pos2).getBlock(), pos2, false, facing, (Entity)null, stack)) {
            return false;
         } else if (Blocks.redstone_wire.canPlaceBlockAt(world, pos2)) {
            stack.stackSize--;
            world.setBlockState(pos2, Blocks.redstone_wire.getDefaultState());
            return true;
         } else {
            return false;
         }
      }
   }

   public ItemRedstone() {
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }
}
